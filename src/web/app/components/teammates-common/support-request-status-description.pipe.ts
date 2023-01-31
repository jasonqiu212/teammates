import { Pipe, PipeTransform } from "@angular/core";
import { SupportRequestStatus } from "../../../types/api-request";

/**
 * Maps between support request status and description text.
 */
const descriptionMapping: Record<SupportRequestStatus, string> = {
  SUBMITTED: "Submitted",
  PENDING: "Pending",
  REQUEST_TO_CLOSE: "Requested to Close",
  CLOSED: "Closed",
};

/**
 * Pipe to handle the transformation of a SupportRequestStatus to a string description.
 */
@Pipe({
  name: "supportRequestStatusDescription",
})
export class SupportRequestStatusDescriptionPipe implements PipeTransform {
  transform(status: SupportRequestStatus): string {
    return descriptionMapping[status];
  }
}
