import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ResourceEndpoints } from "../types/api-const";
import { SupportRequest, SupportRequests } from "../types/api-output";
import {
  SupportRequestCreateRequest,
  SupportRequestUpdateRequest,
} from "../types/api-request";
import { HttpRequestService } from "./http-request.service";

/**
 * Handles support request related logic injection
 */
@Injectable({
  providedIn: "root",
})
export class SupportRequestService {
  constructor(private httpRequestService: HttpRequestService) {}

  /**
   * Creates a support request by calling API.
   */
  createSupportRequest(
    request: SupportRequestCreateRequest
  ): Observable<SupportRequest> {
    return this.httpRequestService.post(
      ResourceEndpoints.SUPPORT_REQUEST,
      {},
      request
    );
  }

  /**
   * Retrieves all support requests by calling API.
   */
  getSupportRequests(): Observable<SupportRequests> {
    return this.httpRequestService.get(ResourceEndpoints.SUPPORT_REQUESTS);
  }

  /**
   * Retrieves all support requests for an email
   */
  getSupportRequestsForEmail(email: string): Observable<SupportRequests> {
    const paramsMap: Record<string, string> = {
      email: email,
    };
    return this.httpRequestService.get(
      ResourceEndpoints.SUPPORT_REQUESTS,
      paramsMap
    );
  }

  /**
   * Retrieve a support request by id by calling API.
   */
  getSupportRequest(supportRequestId: string): Observable<SupportRequest> {
    const paramMap: Record<string, string> = {
      supportrequestid: supportRequestId,
    };
    return this.httpRequestService.get(
      ResourceEndpoints.SUPPORT_REQUEST,
      paramMap
    );
  }

  /**
   * Updates a support request by calling API.
   */
  updateSupportRequest(
    request: SupportRequestUpdateRequest,
    supportRequestId: string
  ): Observable<SupportRequest> {
    const paramMap: Record<string, string> = {
      supportrequestid: supportRequestId,
    };
    return this.httpRequestService.put(
      ResourceEndpoints.SUPPORT_REQUEST,
      paramMap,
      request
    );
  }
}
