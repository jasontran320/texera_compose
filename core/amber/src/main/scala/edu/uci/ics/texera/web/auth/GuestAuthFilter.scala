package edu.uci.ics.texera.web.auth

import edu.uci.ics.texera.web.model.jooq.generated.enums.UserRole
import edu.uci.ics.texera.web.model.jooq.generated.tables.pojos.User
import io.dropwizard.auth.AuthFilter

import javax.annotation.Nullable
import javax.annotation.Priority
import javax.ws.rs.Priorities
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.PreMatching
import javax.ws.rs.core.SecurityContext
import java.io.IOException
import java.util.Optional

@PreMatching
@Priority(Priorities.AUTHENTICATION) object GuestAuthFilter {
  class Builder extends AuthFilter.AuthFilterBuilder[String, SessionUser, GuestAuthFilter] {
    override protected def newInstance = new GuestAuthFilter
  }
}

@PreMatching
@Priority(Priorities.AUTHENTICATION) class GuestAuthFilter extends AuthFilter[String, SessionUser] {
  @throws[IOException]
  override def filter(requestContext: ContainerRequestContext): Unit =
    authenticate(requestContext, "", "")

  override protected def authenticate(
      requestContext: ContainerRequestContext,
      @Nullable credentials: String,
      scheme: String
  ): Boolean = {

    val principal =
      Optional.of(new SessionUser(new User(null, "guest", null, null, null, UserRole.REGULAR)))
    val securityContext = requestContext.getSecurityContext
    val secure = securityContext != null && securityContext.isSecure
    requestContext.setSecurityContext(new SecurityContext() {
      override def getUserPrincipal: SessionUser = principal.get

      override def isUserInRole(role: String): Boolean = authorizer.authorize(principal.get, role)

      override def isSecure: Boolean = secure

      override def getAuthenticationScheme: String = scheme
    })
    true
  }
}
