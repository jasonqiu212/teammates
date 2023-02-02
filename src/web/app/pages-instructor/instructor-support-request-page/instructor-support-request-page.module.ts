import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AjaxLoadingModule } from '../../components/ajax-loading/ajax-loading.module';
import { AjaxPreloadModule } from '../../components/ajax-preload/ajax-preload.module';
import { LoadingRetryModule } from '../../components/loading-retry/loading-retry.module';
import { LoadingSpinnerModule } from '../../components/loading-spinner/loading-spinner.module';
import { ProgressBarModule } from '../../components/progress-bar/progress-bar.module';
import { NgModule } from '@angular/core';
import { InstructorSupportRequestPageComponent } from './instructor-support-request-page.component';
import { SupportRequestEditFormModule } from '../../components/support-request-edit-form/support-request-edit-form.module';

const routes: Routes = [
  {
    path: '',
    component: InstructorSupportRequestPageComponent,
  },
];

/**
 * Module for Instructor Support Request page.
 */
@NgModule({
  declarations: [
    InstructorSupportRequestPageComponent,
  ],
  exports: [
    InstructorSupportRequestPageComponent,
  ],
  imports: [
    CommonModule,
    AjaxPreloadModule,
    RouterModule.forChild(routes),
    SupportRequestEditFormModule,
    AjaxLoadingModule,
    LoadingRetryModule,
    ProgressBarModule,
    LoadingSpinnerModule,
  ],
})
export class InstructorSupportRequestPageModule { }
