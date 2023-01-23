package teammates.logic.core;

import java.util.List;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.exception.EntityAlreadyExistsException;
import teammates.common.exception.InvalidParametersException;
import teammates.storage.api.SupportRequestsDb;

public final class SupportRequestsLogic {
    private static final SupportRequestsLogic instance = new SupportRequestsLogic();

    private final SupportRequestsDb supportRequestsDb = SupportRequestsDb.inst();

    private SupportRequestsLogic() {
        // prevent initialization
    }

    public static SupportRequestsLogic inst() {
        return instance;
    }

    void initLogicDependencies() {
        // No dependency to any other logic classes
    }

    /*
     * Gets all Support Requests.
     */
    public List<SupportRequestAttributes> getAllSupportRequests() {
        return supportRequestsDb.getAllSupportRequests();
    }

    /*
     * Gets all support requests from the {@code email}
     */
    public List<SupportRequestAttributes> getSupportRequestsForEmail(String email) {
        return supportRequestsDb.getSupportRequestsForEmail(email);
    }

    /**
     * Creates a Support Request.
     * 
     * @return the create support request.
     * @throws InvalidParametersException if supportRequestAttibute is not valid.
     * @throws EntityAlreadyExistsException if the support request already exists in the database.
     */
    public SupportRequestAttributes createSupportRequest(SupportRequestAttributes supportRequestAttributes) 
        throws InvalidParametersException, EntityAlreadyExistsException {
        return supportRequestsDb.createEntity(supportRequestAttributes);
    }
    
}
