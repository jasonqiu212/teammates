import { Component, EventEmitter, Input, Output } from "@angular/core";
import { NavigationService } from "src/web/services/navigation.service";
import { SortBy, SortOrder } from "../../../types/sort-properties";
import {
  SupportRequestsTableColumn,
  SupportRequestsTableRowModel,
} from "./support-requests-table-model";

/**
 * A table to display a list of support requests.
 */
@Component({
  selector: "tm-support-requests-table",
  templateUrl: "./support-requests-table.component.html",
  styleUrls: ["./support-requests-table.component.scss"],
})
export class SupportRequestsTableComponent {
  // enum
  SortBy: typeof SortBy = SortBy;
  SortOrder: typeof SortOrder = SortOrder;
  SupportRequestsTableColumn: typeof SupportRequestsTableColumn =
    SupportRequestsTableColumn;

  // variable
  rowClicked: number = -1;

  @Input()
  supportRequestsTableRowModels: SupportRequestsTableRowModel[] = [];

  @Input()
  supportRequestsTableRowModelsSortBy: SortBy =
    SortBy.SUPPORT_REQUEST_CREATE_TIME;

  @Input()
  guessTimezone = "UTC";

  @Input()
  supportRequestsTableRowModelsSortOrder: SortOrder = SortOrder.ASC;

  @Output()
  sortSupportRequestsTableRowModelsEvent: EventEmitter<SortBy> =
    new EventEmitter();

  constructor(private navigationService: NavigationService) {}

  /**
   * Sorts the list of support request row.
   */
  sortSupportRequestsTableRowModels(by: SortBy): void {
    this.sortSupportRequestsTableRowModelsEvent.emit(by);
  }


  public navToSupportRequest(srId: string) {
    let url = `/web/admin/requests/edit-request`;
    let params: Record<string, string> = {
      supportRequestId: srId
    }
    this.navigationService.navigateByURL(url, params).then(v => {
      console.log("Navigated to ", url, v);
    });
    
  }
}
