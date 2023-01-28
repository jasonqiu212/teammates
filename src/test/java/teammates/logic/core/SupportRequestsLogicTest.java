package teammates.logic.core;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.storage.api.SupportRequestsDb;

/**
 * SUT: {@link SupportRequestsLogic}.
 */
public class SupportRequestsLogicTest extends BaseLogicTest {

    private SupportRequestAttributes sr;
    private final SupportRequestsDb srDb = SupportRequestsDb.inst();
    private final SupportRequestsLogic srLogic = SupportRequestsLogic.inst();
    private final Map<String, SupportRequestAttributes> typicalSupportRequests = getTypicalDataBundle().supportRequests;

    @Override
    protected void prepareTestData() {
        // test data is refreshed before each test case
    }

    @BeforeMethod
    public void refreshTestData() {
        dataBundle = getTypicalDataBundle();
        removeAndRestoreTypicalDataBundle();
    }

    @Test
    public void testAll() throws Exception {
        // include all tests that do not modify the database.
        // tests such as create, delete and update are tested separately.
        testGetSupportRequestsForEmail();
        testGetAllSupportRequests();
    }

    @Test
    private void testGetSupportRequestsForEmail() {
        // TO DO: test getSupportRequestsForEmail()
    }

    @Test
    private void testGetAllSupportRequests() {
        // TO DO: test getAllSupportRequests()
    }

    @Test
    public void testCreateSupportRequest() throws Exception {
        // TO DO: test createSupportRequest()
    }
}