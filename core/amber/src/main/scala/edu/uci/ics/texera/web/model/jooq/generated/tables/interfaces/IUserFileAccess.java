/*
 * This file is generated by jOOQ.
 */
package edu.uci.ics.texera.web.model.jooq.generated.tables.interfaces;


import edu.uci.ics.texera.web.model.jooq.generated.enums.UserFileAccessPrivilege;

import java.io.Serializable;

import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IUserFileAccess extends Serializable {

    /**
     * Setter for <code>texera_db.user_file_access.uid</code>.
     */
    public void setUid(UInteger value);

    /**
     * Getter for <code>texera_db.user_file_access.uid</code>.
     */
    public UInteger getUid();

    /**
     * Setter for <code>texera_db.user_file_access.fid</code>.
     */
    public void setFid(UInteger value);

    /**
     * Getter for <code>texera_db.user_file_access.fid</code>.
     */
    public UInteger getFid();

    /**
     * Setter for <code>texera_db.user_file_access.privilege</code>.
     */
    public void setPrivilege(UserFileAccessPrivilege value);

    /**
     * Getter for <code>texera_db.user_file_access.privilege</code>.
     */
    public UserFileAccessPrivilege getPrivilege();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IUserFileAccess
     */
    public void from(edu.uci.ics.texera.web.model.jooq.generated.tables.interfaces.IUserFileAccess from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IUserFileAccess
     */
    public <E extends edu.uci.ics.texera.web.model.jooq.generated.tables.interfaces.IUserFileAccess> E into(E into);
}
