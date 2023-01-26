import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AjaxLoadingModule } from '../../components/ajax-loading/ajax-loading.module';
import { LoadingSpinnerModule } from '../../components/loading-spinner/loading-spinner.module';
import { AdminSupportRequestsPageComponent } from './admin-support-requests-page.component';

const routes: Routes = [
  {
    path: '',
    component: AdminSupportRequestsPageComponent,
  },
];

/**
 * Module for admin support requests page.
 */
@NgModule({
  declarations: [
    AdminSupportRequestsPageComponent,
  ],
  exports: [
    AdminSupportRequestsPageComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    AjaxLoadingModule,
    LoadingSpinnerModule,
  ],
})
export class AdminSupportRequestsPageModule { }
