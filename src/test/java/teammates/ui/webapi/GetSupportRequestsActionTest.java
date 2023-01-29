package teammates.ui.webapi;

import java.util.List;

import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.util.Const;
import teammates.ui.output.SupportRequestData;
import teammates.ui.output.SupportRequestsData;

public class GetSupportRequestsActionTest extends BaseActionTest<GetSupportRequestsAction>  {
    @Override
    String getActionUri() {
        return Const.ResourceURIs.SUPPORT_REQUESTS;
    }

    @Override
    String getRequestMethod() {
        return GET;
    }

    @Test
    @Override
    protected void testExecute() {
        // See independent test cases
    }

    @Test
    @Override
    protected void testAccessControl() {
        SupportRequestAttributes supportRequest = typicalBundle.supportRequests.get("supportRequest1");

        ______TS("unknown user can access if provide email");
        String[] requestParams = new String[] {
                Const.ParamsNames.SUPPORT_REQUEST_EMAIL, supportRequest.getEmail(),
        };
        verifyAnyUserCanAccess(requestParams);

        ______TS("accessible to admin");
        loginAsAdmin();
        requestParams = new String[] {};
        verifyCanAccess(requestParams);
    }

    // TO DO: test execute functions 
    //   1. for admins: no email
    //   2. for public: must provide email (a. valid email, b. invalid email / null)
    @Test
    public void testExecute_withoutEmailForAdmin_shouldReturnAllSupportRequests() {
        // ______TS("Admin request to fetch all support requests");
        // int expectedNumberOfSupportRequests = typicalBundle.supportRequests.size();
        // loginAsAdmin();
        // SupportRequestAttributes supportRequest = typicalBundle.supportRequests.get("notStartedNotification2");

        // String[] requestParams = new String[] {};

        // GetSupportRequestsAction action = getAction(requestParams);
        // JsonResult jsonResult = getJsonResult(action);

        // SupportRequestsData output = (SupportRequestsData) jsonResult.getOutput();
        // List<SupportRequestData> supportRequests = output.getSupportRequests();

        // assertEquals(expectedNumberOfSupportRequests,
        //         logic.getAllSupportRequests().size());
        // assertEquals(expectedNumberOfSupportRequests, supportRequests.size());

        // SupportRequestData expected = new SupportRequestData(supportRequest);
        // SupportRequestData firstSupportRequest = supportRequests.get(0);
        // verifySupportRequestEquals(expected, firstSupportRequest);
    }

    @Test
    public void testExecute_withValidEmailForPublic_shouldReturnAllSupportRequestsForEmail() {
        // TO DO
    }

    @Test
    public void testExecute_withInvalidEmailForPublic_shouldThrowError() {
        // TO DO
    }

    private void verifySupportRequestEquals(SupportRequestData expected, SupportRequestData actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expected.getModifiedAt(), actual.getModifiedAt());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getResponse(), actual.getResponse());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getSupportRequestCategory(), actual.getSupportRequestCategory());
        assertEquals(expected.getSupportRequestStatus(), actual.getSupportRequestStatus());
        assertEquals(expected.getHasNewChanges(), actual.getHasNewChanges());
    }
}
