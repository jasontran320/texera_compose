import { Component } from "@angular/core";
import { UserService } from "../../../../common/service/user/user.service";
import { User } from "../../../../common/type/user";
import { UntilDestroy } from "@ngneat/until-destroy";

/**
 * UserIconComponent is used to control user system on the top right corner
 * It includes the button for login/registration/logout
 * It also includes what is shown on the top right
 */
@UntilDestroy()
@Component({
  selector: "texera-user-icon",
  templateUrl: "./user-icon.component.html",
  styleUrls: ["./user-icon.component.scss"],
})
export class UserIconComponent {
  public user: User | undefined;

  constructor(private userService: UserService) {
    this.user = this.userService.getCurrentUser();
  }

  /**
   * handle the event when user click on the logout button
   */
  public onClickLogout(): void {
    this.userService.logout();
    window.location.href = "home";
  }
}
