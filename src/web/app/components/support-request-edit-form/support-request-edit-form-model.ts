import { SupportRequestCategory, SupportRequestStatus } from '../../../types/api-output';

/**
 * The mode of operation for Support request edit form.
 */
export enum SupportRequestFormMode {
    /**
     * Adding a new supportRequest (User).
     */
    USER_ADD,

    /**
     * Editing the existing supportRequest as a User.
     */
    USER_EDIT,

    /**
     * Editing the existing supportRequest as an Admin.
     */
    ADMIN_EDIT,
}

/**
 * The form model of supportRequest form.
 */
export interface SupportRequestFormModel {
    id: string,
    email: string,
    title: string,
    description: string,
    status: SupportRequestStatus,
    category: SupportRequestCategory,
    response: string,
    hasNewChanges: boolean,
    createdAt: number,
    modifiedAt: number,
};
