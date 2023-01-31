import { Pipe, PipeTransform } from "@angular/core";
import { SupportRequestStatus } from "../../../types/api-output";

/**
 * Pipe to handle the transformation of a SupportRequestStatus to a css class.
 */
@Pipe({
  name: "supportRequestStatusClass",
})
export class SupportRequestStatusClassPipe implements PipeTransform {
  transform(status: SupportRequestStatus): string {
    switch (status) {
      case SupportRequestStatus.SUBMITTED:
        return "alert alert-primary";
      case SupportRequestStatus.PENDING:
        return "alert alert-warning";
      case SupportRequestStatus.REQUEST_TO_CLOSE:
        return "alert alert-info";
      case SupportRequestStatus.CLOSED:
        return "alert alert-secondary";
      default:
        return "";
    }
  }
}
