import { Component, OnInit } from '@angular/core';
import { SupportRequestService } from 'src/web/services/support-request.service';
import { AuthInfo, SupportRequest } from 'src/web/types/api-output';
import { SupportRequestCreateRequest } from 'src/web/types/api-request';
import { ErrorMessageOutput } from '../../error-message-output';
import { SupportRequestFormMode, SupportRequestFormModel } from '../../components/support-request-edit-form/support-request-edit-form-model';
import { StatusMessageService } from 'src/web/services/status-message.service';
import { AuthService } from 'src/web/services/auth.service';
import { LinkService } from 'src/web/services/link.service';

@Component({
  selector: 'tm-student-support-request-page',
  templateUrl: './student-support-request-page.component.html',
  styleUrls: ['./student-support-request-page.component.scss']
})
export class StudentSupportRequestPageComponent implements OnInit {
  // enum
  SupportRequestFormMode: typeof SupportRequestFormMode = SupportRequestFormMode;

  isLoading: boolean = false;
  sr: SupportRequest | null = null;

  email = "";
  formMode: SupportRequestFormMode = SupportRequestFormMode.USER_ADD;

  successMsg: string[] = [];
  private SUCCESSFUL_CREATE_MESSAGE: string[] = ["Successfully submitted a SupportRequest!", "Please save this link to view/make updates to your support request:"]; 


  constructor(private supportRequestService: SupportRequestService,
              private statusMessageService: StatusMessageService,
              private authService: AuthService,
              private linkService: LinkService) { }

  ngOnInit(): void {
    this.formMode = SupportRequestFormMode.USER_ADD;
    this.successMsg = [];

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
      next: (sr: SupportRequest) => {
        let sr_url = this.linkService.generateEditSupportRequestUrl(sr.id);
        this.successMsg = this.SUCCESSFUL_CREATE_MESSAGE
        this.successMsg.push(sr_url);
      },
      error: (err: ErrorMessageOutput) => {
        this.statusMessageService.showErrorToast(err.error.message);
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }

  onDismissSuccessMessage(): void {
    this.successMsg = [];
  }
}
