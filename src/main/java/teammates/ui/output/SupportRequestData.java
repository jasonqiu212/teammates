package teammates.ui.output;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.Category;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.Status;

/**
 * The API Output format of a support request.
 */
public class SupportRequestData extends ApiOutput {

    private String id;
    private String email;
    private String title;
    private String description;
    private Status status;
    private Category category;
    private String response;
    private boolean hasNewChanges;
    private long createdAt;
    private long modifiedAt;

    public SupportRequestData(SupportRequestAttributes supportRequestAttributes) {
        this.id = supportRequestAttributes.getId();
        this.email = supportRequestAttributes.getEmail();
        this.title = supportRequestAttributes.getTitle();
        this.description = supportRequestAttributes.getDescription();
        this.status = supportRequestAttributes.getStatus();
        this.category = supportRequestAttributes.getCategory();
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

    public Status getStatus() {
        return this.status;
    }
    
    public Category getCategory() {
        return this.category;
    }

    public String response() {
        return this.response;
    }

    public boolean gethasNewChanges() {
        return this.hasNewChanges;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public long getModifiedAt() {
        return this.modifiedAt;
    }
}
