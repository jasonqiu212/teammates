import { Component } from "@angular/core";
import { StatusMessageService } from "src/web/services/status-message.service";
import { SupportRequestService } from "../../../services/support-request.service";
import { TableComparatorService } from "../../../services/table-comparator.service";
import { SupportRequest, SupportRequests } from "../../../types/api-output";
import { SortBy, SortOrder } from "../../../types/sort-properties";
import {
  SupportRequestsTableColumn,
  SupportRequestsTableRowModel,
} from "../../components/support-requests-table/support-requests-table-model";
import { ErrorMessageOutput } from "../../error-message-output";

/**
 * Data model for the support requests tab.
 */
export interface SupportRequestsTabModel {
  supportRequestsTableRowModels: SupportRequestsTableRowModel[];
  supportRequestsTableRowModelsSortBy: SortBy;
  supportRequestsTableRowModelsSortOrder: SortOrder;

  hasPopulated: boolean;
  isAjaxSuccess: boolean;
  hasLoadingFailed: boolean;
}

/**
 * Admin support requests page.
 */
@Component({
  selector: "tm-admin-support-requests-page",
  templateUrl: "./admin-support-requests-page.component.html",
  styleUrls: ["./admin-support-requests-page.component.scss"],
})
export class AdminSupportRequestsPageComponent {
  // enum
  SupportRequestsTableColumn: typeof SupportRequestsTableColumn =
    SupportRequestsTableColumn;
  SortBy: typeof SortBy = SortBy;

  // data
  supportRequestsTabModel: SupportRequestsTabModel = {
    supportRequestsTableRowModels: [],
    supportRequestsTableRowModelsSortBy: SortBy.SUPPORT_REQUEST_CREATE_TIME,
    supportRequestsTableRowModelsSortOrder: SortOrder.ASC,
    hasPopulated: false,
    isAjaxSuccess: false,
    hasLoadingFailed: false,
  };

  constructor(
    private tableComparatorService: TableComparatorService,
    private supportRequestService: SupportRequestService,
    private statusMessageService: StatusMessageService
  ) {}

  ngOnInit(): void {
    this.loadSupportRequests();
  }

  /**
   * Loads support requests.
   */
  loadSupportRequests(): void {
    this.supportRequestsTabModel = {
      supportRequestsTableRowModels: [],
      supportRequestsTableRowModelsSortBy: SortBy.SUPPORT_REQUEST_CREATE_TIME,
      supportRequestsTableRowModelsSortOrder: SortOrder.ASC,
      hasPopulated: false,
      isAjaxSuccess: false,
      hasLoadingFailed: false,
    };
    this.supportRequestService
      .getSupportRequestsForEmail("alice@abc.com")
      .subscribe({
        next: (response: SupportRequests) => {
          response.supportRequests.forEach((supportRequest: SupportRequest) => {
            const m: SupportRequestsTableRowModel = {
              supportRequest,
            };
            this.supportRequestsTabModel.supportRequestsTableRowModels.push(m);
          });
          this.supportRequestsTabModel.hasPopulated = true;
          if (!this.supportRequestsTabModel.isAjaxSuccess) {
            this.supportRequestsTabModel.isAjaxSuccess = true;
          }
        },
        error: (resp: ErrorMessageOutput) => {
          this.supportRequestsTabModel.hasLoadingFailed = true;
          this.statusMessageService.showErrorToast(resp.error.message);
        },
      });
  }

  /**
   * Sorts the list of support request row.
   */
  sortSupportRequestsTableRowModelsEvent(by: SortBy): void {
    this.supportRequestsTabModel.supportRequestsTableRowModelsSortBy = by;

    // reverse the sort order
    this.supportRequestsTabModel.supportRequestsTableRowModelsSortOrder =
      this.supportRequestsTabModel.supportRequestsTableRowModelsSortOrder ===
      SortOrder.DESC
        ? SortOrder.ASC
        : SortOrder.DESC;

    this.supportRequestsTabModel.supportRequestsTableRowModels.sort(
      this.sortModelsBy(
        by,
        this.supportRequestsTabModel.supportRequestsTableRowModelsSortOrder
      )
    );
  }

  /**
   * Generates a sorting function.
   */
  protected sortModelsBy(
    by: SortBy,
    order: SortOrder
  ): (
    a: { supportRequest: SupportRequest },
    b: { supportRequest: SupportRequest }
  ) => number {
    return (
      a: { supportRequest: SupportRequest },
      b: { supportRequest: SupportRequest }
    ): number => {
      let strA: string;
      let strB: string;
      switch (by) {
        case SortBy.SUPPORT_REQUEST_TITLE:
          strA = a.supportRequest.title;
          strB = b.supportRequest.title;
          break;
        case SortBy.SUPPORT_REQUEST_CREATE_TIME:
          strA = a.supportRequest.createdAt.toString();
          strB = b.supportRequest.createdAt.toString();
          break;
        case SortBy.SUPPORT_REQUEST_CATEGORY:
          strA = String(a.supportRequest.category);
          strB = String(b.supportRequest.category);
          break;
        case SortBy.SUPPORT_REQUEST_STATUS:
          strA = String(a.supportRequest.status);
          strB = String(b.supportRequest.status);
          break;
        default:
          strA = "";
          strB = "";
      }
      return this.tableComparatorService.compare(by, order, strA, strB);
    };
  }
}
