import { SupportRequest } from "../../../types/api-output";

/**
 * The model for a row of the support requests table.
 */
export interface SupportRequestsTableRowModel {
  supportRequest: SupportRequest;
}

/**
 * The column of the support requests table
 */
export enum SupportRequestsTableColumn {
  /**
   * Subject column.
   */
  SUBJECT,

  /**
   * Date of the support request column.
   */
  DATE,

  /**
   * Category of the support request column.
   */
  CATEGORY,

  /**
   * Status of the support request column.
   */
  STATUS,
}
