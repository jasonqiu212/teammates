import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { StatusMessageService } from "src/web/services/status-message.service";
import { SupportRequestFormMode, SupportRequestFormModel } from "../../components/support-request-edit-form/support-request-edit-form-model";
// import { SupportRequestService } from "src/web/services/support-request.service"; 
import { ErrorMessageOutput } from "../../error-message-output";

@Component({
    selector: 'tm-admin-edit-support-request-page',
    templateUrl: './admin-edit-support-request-page.component.html',
    styleUrls: ['./admin-edit-support-request-page.component.scss']
})
export class AdminEditSupportRequestPageComponent implements OnInit {
    
    constructor(private route: ActivatedRoute,
                private statusMessageService: StatusMessageService) {
    }

    supportRequestId: String = "";
    formMode: SupportRequestFormMode = SupportRequestFormMode.ADMIN_EDIT;
    isLoading: boolean = false;

    ngOnInit() {
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
            }
        })
    }

    public updateSupportRequest(_formModel: SupportRequestFormModel) {
        this.isLoading = true;
        console.log("Updating DB...");
        this.isLoading = false;
    }
}
