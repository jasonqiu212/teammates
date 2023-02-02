import { Component, OnInit } from '@angular/core';
import { SupportRequestService } from 'src/web/services/support-request.service';
import { AuthInfo, SupportRequest } from 'src/web/types/api-output';
import { SupportRequestCreateRequest } from 'src/web/types/api-request';
import { ErrorMessageOutput } from '../../error-message-output';
import { SupportRequestFormMode, SupportRequestFormModel } from '../../components/support-request-edit-form/support-request-edit-form-model';
import { StatusMessageService } from 'src/web/services/status-message.service';
import { AuthService } from 'src/web/services/auth.service';

@Component({
  selector: 'tm-instructor-support-request-page',
  templateUrl: './instructor-support-request-page.component.html',
  styleUrls: ['./instructor-support-request-page.component.scss']
})
export class InstructorSupportRequestPageComponent implements OnInit {
  // enum
  SupportRequestFormMode: typeof SupportRequestFormMode = SupportRequestFormMode;

  isLoading: boolean = false;
  sr: SupportRequest | null = null;

  email = "";
  formMode: SupportRequestFormMode = SupportRequestFormMode.USER_ADD;

  constructor(private supportRequestService: SupportRequestService,
              private statusMessageService: StatusMessageService,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.formMode = SupportRequestFormMode.USER_ADD;

    this.isLoading = true;
    this.email = "";
    this.authService.getAuthUser().subscribe({
      next: (res: AuthInfo) => {
        if (res.user) {
          console.log(res.user);
          this.email = res.user?.id || "";
        } 
      },
      error: () => {
        // Fail silently
      },
      complete: () => {
        this.isLoading = false;
      }
    });
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
        this.statusMessageService.showSuccessToast("Successfully submitted a Support Request");
      },
      error: (err: ErrorMessageOutput) => {
        this.statusMessageService.showErrorToast(err.error.message);
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }
}
