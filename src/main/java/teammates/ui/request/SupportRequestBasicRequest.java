package teammates.ui.request;

import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;

public class SupportRequestBasicRequest extends BasicRequest {

    private String id;
    private String email;
    private String title;
    private String description;
    private SupportRequestStatus status;
    private SupportRequestCategory category;
    private String response;
    private boolean hasNewChanges;
    private long createdAt;
    private long modifiedAt;

    @Override
    public void validate() throws InvalidHttpRequestBodyException {
        assertTrue(email != null, "email cannot be null");
        assertTrue(title != null, "title cannot be null");
        assertTrue(description != null, "description cannot be null");
        assertTrue(status != null, "status cannot be null");
        assertTrue(category != null, "category cannot be null");
        assertTrue(response != null, "response cannot be null");
        assertTrue(createdAt > 0L, "Created-at timestamp should be greater than zero");
        assertTrue(modifiedAt > 0L, "Modified-at timestamp should be greater than zero");
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public SupportRequestStatus getSupportRequestStatus() {
        return this.status;
    }

    public SupportRequestCategory getSupportRequestCategory() {
        return this.category;
    }

    public String getResponse() {
        return this.response;
    }

    public boolean getHasNewChanges() {
        return this.hasNewChanges;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public long getModifiedAt() {
        return this.modifiedAt;
    }
    
}
