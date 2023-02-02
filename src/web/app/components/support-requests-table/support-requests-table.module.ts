import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import {
  NgbDropdownModule,
  NgbTooltipModule,
} from "@ng-bootstrap/ng-bootstrap";
import { AjaxLoadingModule } from "../ajax-loading/ajax-loading.module";
import { AjaxPreloadModule } from "../ajax-preload/ajax-preload.module";
import { CopySessionModalModule } from "../copy-session-modal/copy-session-modal.module";
import { TeammatesCommonModule } from "../teammates-common/teammates-common.module";
import { TeammatesRouterModule } from "../teammates-router/teammates-router.module";
import { SupportRequestsTableComponent } from "./support-requests-table.component";

/**
 * Module for sessions table.
 */
@NgModule({
  declarations: [SupportRequestsTableComponent],
  imports: [
    CommonModule,
    AjaxLoadingModule,
    AjaxPreloadModule,
    TeammatesCommonModule,
    NgbDropdownModule,
    NgbTooltipModule,
    FormsModule,
    CopySessionModalModule,
    RouterModule,
    TeammatesCommonModule,
    TeammatesRouterModule,
  ],
  exports: [SupportRequestsTableComponent],
})
export class SupportRequestsTableModule {}
