import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { ResourceEndpoints } from '../types/api-const';
import { SupportRequestBasicRequest, SupportRequestStatus, SupportRequestCategory } from '../types/api-request';
import { HttpRequestService } from './http-request.service';
import { SupportRequestService } from './support-request.service';

const requestBody: SupportRequestBasicRequest = {
    id: '',
    email: "abc@gmail.com",
    title: '',
    description: '',
    status: SupportRequestStatus.SUBMITTED,
    category: SupportRequestCategory.OTHERS,
    response: '',
    hasNewChanges: true,
    createdAt: 1,
    modifiedAt: 1
};

describe('SupportRequestService', () => {
    let spyHttpRequestService: any;
    let service: SupportRequestService;
  
    beforeEach(() => {
      spyHttpRequestService = {
        get: jest.fn(),
        post: jest.fn(),
        put: jest.fn(),
        delete: jest.fn(),
      };
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [{ provide: HttpRequestService, useValue: spyHttpRequestService }],
      });
      service = TestBed.inject(SupportRequestService);
    });
  
    it('should be created', () => {
      expect(service).toBeTruthy();
    });

    it('should execute POST when creating support requests', () => {
    const paramsMap: Record<string, string> = {};
    service.createSupportRequest(requestBody);
    expect(spyHttpRequestService.post).toHaveBeenCalledWith(
        ResourceEndpoints.SUPPORT_REQUEST,
        paramsMap,
        requestBody,
    );
    });
});
