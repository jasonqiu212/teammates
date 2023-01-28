package teammates.ui.webapi;

import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;
import teammates.common.util.Const;
import teammates.ui.output.SupportRequestData;
import teammates.ui.request.InvalidHttpRequestBodyException;
import teammates.ui.request.SupportRequestCreateRequest;

/**
 * SUT: {@link CreateSupportRequestAction}.
 */
public class CreateSupportRequestActionTest extends BaseActionTest<CreateSupportRequestAction> {
    private static final String TEST_SUPPORT_REQUEST = "supportRequest1";
    private final SupportRequestAttributes testSupportRequestAttribute = typicalBundle.supportRequests.get(TEST_SUPPORT_REQUEST);

    @Override
    String getActionUri() {
        return Const.ResourceURIs.SUPPORT_REQUEST;
    }

    @Override
    String getRequestMethod() {
        return POST;
    }

    // TO DO: test CreateSupportRequestAction is incomplete!
    @Test
    @Override
    protected void testExecute() throws Exception {
        String id = testSupportRequestAttribute.getId();
        long createdAt = testSupportRequestAttribute.getCreatedAt().toEpochMilli();
        long modifiedAt = testSupportRequestAttribute.getModifiedAt().toEpochMilli();
        SupportRequestStatus status = testSupportRequestAttribute.getSupportRequestStatus();
        SupportRequestCategory category = testSupportRequestAttribute.getSupportRequestCategory();
        String title = testSupportRequestAttribute.getTitle();
        String description = testSupportRequestAttribute.getDescription();
        String response = testSupportRequestAttribute.getResponse();
        String email = testSupportRequestAttribute.getEmail();
        Boolean hasNewChanges = testSupportRequestAttribute.getHasNewChanges();

        String invalidTitle = "";

        // ______TS("Typical Case: Add support request successfully");
        SupportRequestCreateRequest req = getTypicalCreateRequest();
        CreateSupportRequestAction action = getAction(req);
        SupportRequestData res = (SupportRequestData) action.execute().getOutput();

        // SupportRequestAttributes createdSupportRequest = logic.getSupportRequest(res.getId());

        // // check that support request returned has same properties as support request created
        // assertEquals(createdSupportRequest.getId(), res.getId());
        // assertEquals(createdSupportRequest.getEmail(), res.getEmail());
        // assertEquals(createdSupportRequest.getCreatedAt().toEpochMilli(), res.getCreatedAt());
        // assertEquals(createdSupportRequest.getModifiedAt().toEpochMilli(), res.getModifiedAt());
        // assertEquals(createdSupportRequest.getSupportRequestStatus(), res.getSupportRequestStatus());
        // assertEquals(createdSupportRequest.getSupportRequestCategory(), res.getSupportRequestCategory());
        // assertEquals(createdSupportRequest.getTitle(), res.getTitle());
        // assertEquals(createdSupportRequest.getDescription(), res.getDescription());
        // assertEquals(createdSupportRequest.getResponse(), res.getResponse());
        // assertEquals(createdSupportRequest.getHasNewChanges(), res.getHasNewChanges());

        // // check DB correctly processed request
        // assertEquals(id, createdSupportRequest.getId());
        // assertEquals(createdAt, createdSupportRequest.getCreatedAt().toEpochMilli());
        // assertEquals(modifiedAt, createdSupportRequest.getModifiedAt().toEpochMilli());
        // assertEquals(status, createdSupportRequest.getSupportRequestStatus());
        // assertEquals(category, createdSupportRequest.getSupportRequestCategory());
        // assertEquals(title, createdSupportRequest.getTitle());
        // assertEquals(description, createdSupportRequest.getDescription());
        // assertEquals(email, createdSupportRequest.getEmail());
        // assertEquals(response, createdSupportRequest.getResponse());
        // assertEquals(hasNewChanges, createdSupportRequest.getHasNewChanges());

        ______TS("Parameters cannot be null");
        req = getTypicalCreateRequest();
        req.setEmail(null);
        InvalidHttpRequestBodyException ex = verifyHttpRequestBodyFailure(req);
        assertEquals("email cannot be null", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setTitle(null);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("title cannot be null", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setDescription(null);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("description cannot be null", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setResponse(null);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("response cannot be null", ex.getMessage());

        ______TS("Timestamps should be greater than 0");
        req = getTypicalCreateRequest();
        req.setCreatedAt(-1);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("Created-at timestamp should be greater than zero", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setModifiedAt(-1);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("Modified-at timestamp should be greater than zero", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setSupportRequestStatus(null);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("status cannot be null", ex.getMessage());

        req = getTypicalCreateRequest();
        req.setSupportRequestCategory(null);
        ex = verifyHttpRequestBodyFailure(req);
        assertEquals("category cannot be null", ex.getMessage());

        ______TS("Invalid parameter should throw an error");
        req = getTypicalCreateRequest();
        req.setTitle(invalidTitle);
        verifyHttpRequestBodyFailure(req);
    }

    @Test
    @Override
    protected void testAccessControl() throws Exception {
        verifyAnyUserCanAccess();
    }

    private SupportRequestCreateRequest getTypicalCreateRequest() {
        SupportRequestCreateRequest req = new SupportRequestCreateRequest();

        req.setId(testSupportRequestAttribute.getId());
        req.setEmail(testSupportRequestAttribute.getEmail());
        req.setTitle(testSupportRequestAttribute.getTitle());
        req.setDescription(testSupportRequestAttribute.getDescription());
        req.setSupportRequestStatus(testSupportRequestAttribute.getSupportRequestStatus());
        req.setSupportRequestCategory(testSupportRequestAttribute.getSupportRequestCategory());
        req.setCreatedAt(testSupportRequestAttribute.getCreatedAt().toEpochMilli());
        req.setModifiedAt(testSupportRequestAttribute.getModifiedAt().toEpochMilli());
        req.setHasNewChanges(testSupportRequestAttribute.getHasNewChanges());
        req.setResponse(testSupportRequestAttribute.getResponse());

        return req;
    }
    
}
