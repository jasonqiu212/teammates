package teammates.storage.api;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.storage.entity.SupportRequest;

/**
 * Handles CRUD operations for support requests.
 *
 * @see SupportRequest
 * @see SupportRequestAttributes
 */
public final class SupportRequestsDb extends EntitiesDb<SupportRequest, SupportRequestAttributes> {

    private static final SupportRequestsDb instance = new SupportRequestsDb();

    private SupportRequestsDb() {
        // prevent initialization
    }

    public static SupportRequestsDb inst() {
        return instance;
    }

    /*
     * Gets all Support Requests.
     */
    public List<SupportRequestAttributes> getAllSupportRequests() {
        List<SupportRequest> supportRequests = load().list();
        List<SupportRequestAttributes> supportRequestAttributes = makeAttributes(supportRequests);
        SupportRequestAttributes.sortByCreatedAt(supportRequestAttributes);
        return supportRequestAttributes;
    }

    public List<SupportRequestAttributes> getSupportRequestsForEmail(String email) {
        assert email != null;

        List<SupportRequest> supportRequests = getSupportRequestEntitiesForEmail(email);
        List<SupportRequestAttributes> supportRequestAttributes = makeAttributes(supportRequests);
        SupportRequestAttributes.sortByCreatedAt(supportRequestAttributes);

        return supportRequestAttributes;
    }

    private List<SupportRequest> getSupportRequestEntitiesForEmail(String email) {
        return load().filter("email =", email).list();
    }

    public SupportRequestAttributes updateSupportRequest(SupportRequestAttributes.UpdateOptions updateOptions)
            throws InvalidParametersException, EntityDoesNotExistException {
        assert updateOptions != null;

        SupportRequest supportRequest = getSupportRequestEntity(updateOptions.getId());
        if (supportRequest == null) {
            throw new EntityDoesNotExistException(ERROR_UPDATE_NON_EXISTENT + updateOptions);
        }

        SupportRequestAttributes newAttributes = makeAttributes(supportRequest);

        newAttributes.update(updateOptions);
        newAttributes.sanitizeForSaving();

        if (!newAttributes.isValid()) {
            throw new InvalidParametersException(newAttributes.getInvalidityInfo());
        }

        // update only if change
        boolean hasSameAttributes = this.<String>hasSameValue(supportRequest.getTitle(), newAttributes.getTitle())
                && this.<String>hasSameValue(supportRequest.getDescription(), newAttributes.getDescription())
                && this.<String>hasSameValue(supportRequest.getEmail(), newAttributes.getEmail())
                && this.<String>hasSameValue(supportRequest.getResponse(), newAttributes.getResponse())
                && this.<SupportRequestStatus>hasSameValue(supportRequest.getSupportRequestStatus(),
                        newAttributes.getSupportRequestStatus())
                && this.<SupportRequestCategory>hasSameValue(supportRequest.getSupportRequestCategory(),
                        newAttributes.getSupportRequestCategory());

        if (hasSameAttributes) {
            log.info(String.format(OPTIMIZED_SAVING_POLICY_APPLIED, SupportRequest.class.getSimpleName(),
                    updateOptions));
            return newAttributes;
        }

        saveEntity(newAttributes.toEntity());
        return newAttributes;
    }

    private SupportRequest getSupportRequestEntity(String id) {
        return load().id(id).now();
    }

    @Override
    LoadType<SupportRequest> load() {
        return ofy().load().type(SupportRequest.class);
    }

    @Override
    boolean hasExistingEntities(SupportRequestAttributes entityToCreate) {
        Key<SupportRequest> keyToFind = Key.create(SupportRequest.class,
                entityToCreate.getId());
        return !load().filterKey(keyToFind).keys().list().isEmpty();
    }

    @Override
    SupportRequestAttributes makeAttributes(SupportRequest entity) {
        assert entity != null;

        return SupportRequestAttributes.valueOf(entity);
    }
}
