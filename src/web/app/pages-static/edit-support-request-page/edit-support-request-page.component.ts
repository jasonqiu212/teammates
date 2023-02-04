import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { StatusMessageService } from "src/web/services/status-message.service";
import { SupportRequestService } from "src/web/services/support-request.service";
import {
  SupportRequestFormMode,
  SupportRequestFormModel,
} from "../../components/support-request-edit-form/support-request-edit-form-model";
import { ErrorMessageOutput } from "../../error-message-output";

@Component({
  selector: "tm-edit-support-request-page",
  templateUrl: "./edit-support-request-page.component.html",
  styleUrls: ["./edit-support-request-page.component.scss"],
})
export class EditSupportRequestPageComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private statusMessageService: StatusMessageService,
    private supportRequestService: SupportRequestService
  ) {}

  supportRequestId: String = "";
  formMode: SupportRequestFormMode = SupportRequestFormMode.USER_EDIT;
  isLoading: boolean = false;

  ngOnInit() {
    this.onLoad();
  }

  private onLoad(): void {
    this.isLoading = true;
    this.route.queryParams.subscribe({
      next: (queryParams: any) => {
        this.isLoading = false;
        if (queryParams != null) {
          this.supportRequestId = queryParams.supportRequestId || "";
        } else {
          this.supportRequestId = "";
        }
      },
      error: (err: ErrorMessageOutput) => {
        this.isLoading = false;
        this.supportRequestId = "";
        this.statusMessageService.showErrorToast(err.error.message);
      },
    });
  }

  public updateSupportRequest(formModel: SupportRequestFormModel) {
    this.isLoading = true;

    this.supportRequestService
      .updateSupportRequest(
        {
          id: formModel.id,
          email: formModel.email,
          title: formModel.title,
          description: formModel.description,
          status: formModel.status,
          category: formModel.category,
          response: formModel.response,
          hasNewChanges: formModel.hasNewChanges,
          createdAt: formModel.createdAt,
          modifiedAt: formModel.modifiedAt,
        },
        formModel.id
      )
      .subscribe({
        next: () => {
          this.statusMessageService.showSuccessToast(
            "Support request updated successfully."
          );
          this.onLoad();
        },
        error: (resp: ErrorMessageOutput) => {
          this.statusMessageService.showErrorToast(resp.error.message);
        },
        complete: () => {
          this.isLoading = false;
        }
      });
  }
}
