package teammates.storage.api;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
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
