import { ComponentFixture, TestBed, waitForAsync } from "@angular/core/testing";
import { UserIconComponent } from "./user-icon.component";
import { UserService } from "../../../../common/service/user/user.service";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { StubUserService } from "../../../../common/service/user/stub-user.service";
import { NzDropDownModule } from "ng-zorro-antd/dropdown";
import { RouterTestingModule } from "@angular/router/testing";
import { HomeComponent } from "../../../../home/component/home.component";

describe("UserIconComponent", () => {
  let component: UserIconComponent;
  let fixture: ComponentFixture<UserIconComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [UserIconComponent],
      providers: [{ provide: UserService, useClass: StubUserService }],
      imports: [
        RouterTestingModule.withRoutes([{ path: "home", component: HomeComponent }]),
        HttpClientTestingModule,
        NzDropDownModule,
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
