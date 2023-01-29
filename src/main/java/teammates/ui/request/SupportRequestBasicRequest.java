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

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SupportRequestStatus getSupportRequestStatus() {
        return this.status;
    }

    public void setSupportRequestStatus(SupportRequestStatus status) {
        this.status = status;
    }

    public SupportRequestCategory getSupportRequestCategory() {
        return this.category;
    }

    public void setSupportRequestCategory(SupportRequestCategory category) {
        this.category = category;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean getHasNewChanges() {
        return this.hasNewChanges;
    }

    public void setHasNewChanges(boolean hasNewChanges) {
        this.hasNewChanges = hasNewChanges;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(long modifiedAt) {
        this.modifiedAt = modifiedAt;
    } 
}
