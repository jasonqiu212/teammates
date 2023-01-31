import { Component, EventEmitter, Input, Output } from "@angular/core";
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
  supportRequestsTableRowModelsSortOrder: SortOrder = SortOrder.ASC;

  @Output()
  sortSupportRequestsTableRowModelsEvent: EventEmitter<SortBy> =
    new EventEmitter();

  constructor() {}

  /**
   * Sorts the list of support request row.
   */
  sortSupportRequestsTableRowModels(by: SortBy): void {
    this.sortSupportRequestsTableRowModelsEvent.emit(by);
  }
}
