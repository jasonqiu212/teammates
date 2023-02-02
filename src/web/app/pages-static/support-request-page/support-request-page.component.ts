import { Component, OnInit } from '@angular/core';
import { SupportRequestService } from 'src/web/services/support-request.service';
import { SupportRequest } from 'src/web/types/api-output';
import { SupportRequestCreateRequest } from 'src/web/types/api-request';
import { ErrorMessageOutput } from '../../error-message-output';
import { SupportRequestFormMode, SupportRequestFormModel } from '../../components/support-request-edit-form/support-request-edit-form-model';
import { StatusMessageService } from 'src/web/services/status-message.service';

@Component({
  selector: 'tm-support-request-page',
  templateUrl: './support-request-page.component.html',
  styleUrls: ['./support-request-page.component.scss']
})
export class SupportRequestPageComponent implements OnInit {
  // enum
  SupportRequestFormMode: typeof SupportRequestFormMode = SupportRequestFormMode;

  isLoading: boolean = false;
  sr: SupportRequest | null = null;

  email = "";
  formMode: SupportRequestFormMode = SupportRequestFormMode.USER_ADD;

  constructor(private supportRequestService: SupportRequestService,
              private statusMessageService: StatusMessageService) { }

  ngOnInit(): void {
    this.email = "";
    this.formMode = SupportRequestFormMode.USER_ADD;
  }

  public createNewSupportRequest(formModel: SupportRequestFormModel) {
    let req: SupportRequestCreateRequest = {
        id: formModel.id,
        email: formModel.email,
        title: formModel.title,
        description: formModel.description,
        createdAt: new Date().getTime(),
        modifiedAt: new Date().getTime(),
        status: formModel.status,
        category: formModel.category,
        response: formModel.response,
        hasNewChanges: formModel.hasNewChanges   
    };

    this.isLoading = true;
    this.supportRequestService.createSupportRequest(req).subscribe({
      next: (_sr: SupportRequest) => {
        this.isLoading = false;
        this.statusMessageService.showSuccessToast("Successfully submitted a SupportRequest");
      },
      error: (err: ErrorMessageOutput) => {
        this.isLoading = false;
        this.statusMessageService.showErrorToast(err.error.message);
      }
    });
  }
}
