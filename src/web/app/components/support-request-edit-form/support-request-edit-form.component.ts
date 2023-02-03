import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { StatusMessageService } from "src/web/services/status-message.service";
import { SupportRequestService } from "src/web/services/support-request.service";
import {
  SupportRequest,
  SupportRequestCategory,
  SupportRequestStatus,
} from "src/web/types/api-output";
import { ErrorMessageOutput } from "../../error-message-output";
import {
  SupportRequestFormModel,
  SupportRequestFormMode,
} from "./support-request-edit-form-model";

/**
 * The support request edit form component.
 */
@Component({
  selector: "tm-support-request-edit-form",
  templateUrl: "./support-request-edit-form.component.html",
  styleUrls: ["./support-request-edit-form.component.scss"],
})
export class SupportRequestEditFormComponent implements OnInit {
  // enum
  SupportRequestFormMode: typeof SupportRequestFormMode =
    SupportRequestFormMode;

  @Input()
  supportRequestId: string = "";

  @Input()
  email: string = "";

  @Input()
  formMode: SupportRequestFormMode = SupportRequestFormMode.USER_ADD;

  formModel: SupportRequestFormModel = {
    id: "",
    email: "",
    title: "",
    description: "",
    status: SupportRequestStatus.SUBMITTED,
    category: SupportRequestCategory.OTHERS,
    response: "",
    hasNewChanges: true,
    createdAt: 1,
    modifiedAt: 1,
  };

  @Output()
  onCreateSupportRequestEvent: EventEmitter<SupportRequestFormModel> =
    new EventEmitter();

  @Output()
  onUpdateSupportRequestEvent: EventEmitter<SupportRequestFormModel> =
    new EventEmitter();

  public categories: String[] = Object.values(SupportRequestCategory);
  public statuses: String[] = Object.values(SupportRequestStatus);

  constructor(
    private route: ActivatedRoute,
    private statusMessageService: StatusMessageService,
    private supportRequestService: SupportRequestService
  ) {}

  ngOnInit(): void {
    this.formModel.email = this.email;
    let srId = "";

    if (this.formMode === SupportRequestFormMode.ADMIN_EDIT) {
      srId = this.supportRequestId;
    } else if (this.formMode === SupportRequestFormMode.USER_EDIT) {
      this.route.queryParams.subscribe({
        next: (queryParams: any) => {
          if (queryParams != null) {
            srId = queryParams.supportRequestId;
          }
        },
        error: (err: ErrorMessageOutput) => {
          this.statusMessageService.showErrorToast(err.error.message);
        },
      });
    }

    if (srId !== "") {
      this.supportRequestService
        .getSupportRequest(this.supportRequestId)
        .subscribe({
          next: (sr: SupportRequest) => {
            this.formModel = {
              id: sr.id,
              email: sr.email,
              title: sr.title,
              description: sr.description,
              status: sr.status,
              category: sr.category,
              response: sr.response,
              hasNewChanges: sr.hasNewChanges,
              createdAt: sr.createdAt,
              modifiedAt: sr.modifiedAt,
            };
          },
          error: (err: ErrorMessageOutput) => {
            this.statusMessageService.showErrorToast(err.error.message);
          },
        });
    }
  }

  public enumToOption(str: String) {
    return str
      .split("_")
      .map((word) => word.charAt(0) + word.slice(1).toLowerCase())
      .join(" ");
  }

  public onCreate(): void {
    let now = new Date().getTime();
    this.formModel.createdAt = now;
    this.formModel.modifiedAt = now;
    this.onCreateSupportRequestEvent.emit(this.formModel);
  }

  public onUpdate(): void {
    this.formModel.modifiedAt = new Date().getTime();
    this.onUpdateSupportRequestEvent.emit(this.formModel);
  }
}
