import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { SupportRequestEditFormComponent } from "./support-request-edit-form.component";


/**
 * Module for all support request edit UI.
 */
@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [
    SupportRequestEditFormComponent,
  ],
  exports: [
    SupportRequestEditFormComponent,
  ],
})
export class SupportRequestEditFormModule { }
