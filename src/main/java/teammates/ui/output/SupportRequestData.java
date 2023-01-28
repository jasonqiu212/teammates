package teammates.ui.output;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;

/**
 * The API Output format of a support request.
 */
public class SupportRequestData extends ApiOutput {

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

    public SupportRequestData(SupportRequestAttributes supportRequestAttributes) {
        this.id = supportRequestAttributes.getId();
        this.email = supportRequestAttributes.getEmail();
        this.title = supportRequestAttributes.getTitle();
        this.description = supportRequestAttributes.getDescription();
        this.status = supportRequestAttributes.getSupportRequestStatus();
        this.category = supportRequestAttributes.getSupportRequestCategory();
        this.response = supportRequestAttributes.getResponse();
        this.hasNewChanges = supportRequestAttributes.getHasNewChanges();
        this.createdAt = supportRequestAttributes.getCreatedAt().toEpochMilli();
        this.modifiedAt = supportRequestAttributes.getModifiedAt().toEpochMilli();
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
