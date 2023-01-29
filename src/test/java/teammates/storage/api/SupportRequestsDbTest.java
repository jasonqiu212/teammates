package teammates.storage.api;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.test.BaseTestCaseWithLocalDatabaseAccess;

/**
 * SUT: {@link SupportRequestsDb}.
 */
public class SupportRequestsDbTest extends BaseTestCaseWithLocalDatabaseAccess {

    private final SupportRequestsDb supportRequestsDb = SupportRequestsDb.inst();
    private final Map<String, SupportRequestAttributes> typicalSupportRequests = getTypicalDataBundle().supportRequests;

    @BeforeMethod
    public void setup() throws Exception {
        for (SupportRequestAttributes n : typicalSupportRequests.values()) {
            supportRequestsDb.createEntity(n);
        }
    }

    /**
     * Removes all support requests created by each test.
     */
    @AfterMethod
    public void cleanUp() {
        // TO DO: call deleteSupportRequest().

        // List<SupportRequestAttributes> retrieved = supportRequestsDb.getAllSupportRequests();
        // retrieved.forEach(sr -> supportRequestsDb.deleteSupportRequest(sr.getId()));
    }

    @Test
    public void testGetSupportRequestForEmail() throws Exception {
        // TO DO: test getSupportRequestsForEmail()
    }

    @Test
    public void testGetAllSupportRequests() throws Exception {
        // TO DO: test getAllSupportRequests()
    }

    @Test
    public void testCreateSupportRequest() throws Exception {
        // TO DO: test createSupportRequest()
    }
    
}
