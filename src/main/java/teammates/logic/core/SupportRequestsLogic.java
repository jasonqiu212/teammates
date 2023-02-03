package teammates.logic.core;

import java.util.List;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.exception.EntityAlreadyExistsException;
import teammates.common.exception.EntityDoesNotExistException;
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
     * Gets all support requests.
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
     * Gets support requests associated with the {@code supportRequestId}.
     *
     * @return null if no match found.
     */
    public SupportRequestAttributes getSupportRequest(String supportRequestId) {
        return supportRequestsDb.getSupportRequest(supportRequestId);
    }

    /**
     * Creates a support request.
     * 
     * @return the create support request.
     * @throws InvalidParametersException if supportRequestAttribute is not valid.
     * @throws EntityAlreadyExistsException if the support request already exists in the database.
     */
    public SupportRequestAttributes createSupportRequest(SupportRequestAttributes supportRequestAttributes) 
        throws InvalidParametersException, EntityAlreadyExistsException {
        return supportRequestsDb.createEntity(supportRequestAttributes);
    }

    /**
     * Updates a support request.
     *
     * @return the updated support request
     * @throws InvalidParametersException if the updated support request is not valid
     * @throws EntityDoesNotExistException if the support request to update does not exist
     */
    public SupportRequestAttributes updateSupportRequest(SupportRequestAttributes.UpdateOptions updateOptions)
            throws InvalidParametersException, EntityDoesNotExistException {
        return supportRequestsDb.updateSupportRequest(updateOptions);
    }
}
