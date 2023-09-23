import { Component } from "@angular/core";
import { UserService } from "../../../../common/service/user/user.service";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { UntilDestroy, untilDestroyed } from "@ngneat/until-destroy";
import { ActivatedRoute, Router } from "@angular/router";
import { HttpErrorResponse } from "@angular/common/http";
import { NotificationService } from "../../../../common/service/notification/notification.service";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";
@UntilDestroy()
@Component({
  selector: "texera-local-login",
  templateUrl: "./local-login.component.html",
  styleUrls: ["./local-login.component.scss"],
})
export class LocalLoginComponent {
  public loginErrorMessage: string | undefined;
  public registerErrorMessage: string | undefined;
  public allForms: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private notificationService: NotificationService,
    private router: Router
  ) {
    this.allForms = this.formBuilder.group({
      loginUsername: new FormControl("", [Validators.required]),
      registerUsername: new FormControl("", [Validators.required]),
      loginPassword: new FormControl("", [Validators.required, Validators.minLength(6)]),
      registerPassword: new FormControl("", [Validators.required, Validators.minLength(6)]),
      registerConfirmationPassword: new FormControl("", [Validators.required, this.confirmationValidator]),
    });
  }

  public updateConfirmValidator(): void {
    // immediately update validator (asynchronously to wait for value to refresh)
    setTimeout(() => this.allForms.controls.registerConfirmationPassword.updateValueAndValidity(), 0);
  }

  // validator for confirm password in sign up page
  public confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (this.allForms && control.value !== this.allForms.controls.registerPassword.value) {
      return { confirm: true };
    }
    return {};
  };

  /**
   * This method responds to the sign-in button
   * It will send data inside the text entry to the user service to login
   */
  public login(): void {
    // validate the credentials format
    this.loginErrorMessage = undefined;
    const validation = UserService.validateUsername(this.allForms.get("loginUsername")?.value);
    if (!validation.result) {
      this.loginErrorMessage = validation.message;
      return;
    }

    const username = this.allForms.get("loginUsername")?.value.trim();
    const password = this.allForms.get("loginPassword")?.value;

    this.userService
      .login(username, password)
      .pipe(
        catchError((err: unknown) => {
          if (err instanceof HttpErrorResponse) {
            this.notificationService.error(err.error.message, {
              nzDuration: 10,
            });
          }
          return throwError(() => err);
        }),
        untilDestroyed(this)
      )
      .subscribe(
        // The new page will append to the bottom of the page, and the original page does not remove, zone solves this issue
        Zone.current.wrap(() => {
          this.router.navigateByUrl(this.route.snapshot.queryParams["returnUrl"] || "/dashboard/workflow");
        }, "")
      );
  }

  /**
   * This method responds to the sign-up button
   * It will send data inside the text entry to the user service to register
   */
  public register(): void {
    // validate the credentials format
    this.registerErrorMessage = undefined;
    const registerPassword = this.allForms.get("registerPassword")?.value;
    const registerConfirmationPassword = this.allForms.get("registerConfirmationPassword")?.value;
    const registerUsername = this.allForms.get("registerUsername")?.value.trim();
    const validation = UserService.validateUsername(registerUsername);
    if (registerPassword.length < 6) {
      this.registerErrorMessage = "Password length should be greater than 5";
      return;
    }
    if (registerPassword !== registerConfirmationPassword) {
      this.registerErrorMessage = "Passwords do not match";
      return;
    }
    if (!validation.result) {
      this.registerErrorMessage = validation.message;
      return;
    }
    // register the credentials with backend
    this.userService
      .register(registerUsername, registerPassword)
      .pipe(
        catchError((err: unknown) => {
          if (err instanceof HttpErrorResponse) {
            this.notificationService.error(err.error.message, {
              nzDuration: 10,
            });
          }
          return throwError(() => err);
        }),
        untilDestroyed(this)
      )
      .subscribe(
        // The new page will append to the bottom of the page, and the original page does not remove, zone solves this issue
        Zone.current.wrap(() => {
          this.router.navigateByUrl(this.route.snapshot.queryParams["returnUrl"] || "/dashboard/workflow");
        }, "")
      );
  }
}
