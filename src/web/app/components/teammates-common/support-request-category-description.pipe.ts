import { Pipe, PipeTransform } from "@angular/core";
import { SupportRequestCategory } from "../../../types/api-request";

/**
 * Maps between support request category and description text.
 */
const descriptionMapping: Record<SupportRequestCategory, string> = {
  BUG_REPORT: "Bug Report",
  NEW_FEATURE: "New Feature",
  INQUIRY: "Inquiry",
  OTHERS: "Others",
};

/**
 * Pipe to handle the transformation of a SupportRequestCategory to a string description.
 */
@Pipe({
  name: "supportRequestCategoryDescription",
})
export class SupportRequestCategoryDescriptionPipe implements PipeTransform {
  transform(category: SupportRequestCategory): string {
    return descriptionMapping[category];
  }
}
