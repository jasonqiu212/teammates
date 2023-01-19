package teammates.common.datatransfer.attributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import teammates.common.util.FieldValidator;
import teammates.common.util.SanitizationHelper;
import teammates.common.util.StringHelper;
import teammates.storage.entity.SupportRequest;

/**
 * The data transfer object for {@link StudentProfile} entities.
 */
public final class SupportRequestAttributes extends EntityAttributes<SupportRequest> {

    private String id;
    private String email;
    private String title;
    private String description;
    private Status status;
    private String response;
    private Boolean hasNewChanges;
    private Instant createdAt;
    private Instant modifiedAt;

    private SupportRequestAttributes(String id) {
        this.id = id;
        this.email = "";
        this.title = "";
        this.description = "";
        this.status = Status.PENDING;
        this.response = "";
        this.hasNewChanges = true;
        this.createdAt = Instant.now();
        this.modifiedAt = Instant.now();
    }

    /**
     * Gets the {@link SupportRequestAttributes} instance of the given {@link SupportRequest}.
     */
    public static SupportRequestAttributes valueOf(SupportRequest sr) {
        SupportRequestAttributes supportRequestAttributes = new SupportRequestAttributes(sr.getId());

        if (sr.getEmail() != null) {
            supportRequestAttributes.email = sr.getEmail();
        }
        if (sr.getTitle() != null) {
            supportRequestAttributes.title = sr.getTitle();
        }
        if (sr.getDescription() != null) {
            supportRequestAttributes.description = sr.getDescription();
        }

        supportRequestAttributes.status = Status.getStatusEnumValue(sr.getStatus());
        
        if (sr.getResponse() != null) {
            supportRequestAttributes.response = sr.getResponse();
        }
        if (sr.getHasNewChanges() != null) {
            supportRequestAttributes.hasNewChanges = sr.getHasNewChanges();
        }
        if (sr.getCreatedAt() != null) {
            supportRequestAttributes.createdAt = sr.getCreatedAt();
        }
        if (sr.getModifiedAt() != null) {
            supportRequestAttributes.modifiedAt = sr.getModifiedAt();
        }

        return supportRequestAttributes;
    }

    @Override
    public SupportRequest toEntity() {
        return new SupportRequest(email, title, description, status.name().toLowerCase(), response, hasNewChanges);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response.trim();
    }

    public Boolean getHasNewChanges() {
        return hasNewChanges;
    }

    public void setHasNewChanges(Boolean hasNewChanges) {
        this.hasNewChanges = hasNewChanges;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    // TODO: 
    // Need to create Field Validators for Support Request Title and Support Request description
    // (See: common.util.FieldValidator class)
    @Override
    public List<String> getInvalidityInfo() {
        List<String> errors = new ArrayList<>();

        if (!StringHelper.isEmpty(email)) { // if condition – allows empty field (ie. email = "")
            addNonEmptyError(FieldValidator.getInvalidityInfoForEmail(email), errors);
        }

        // ADD MORE FIELD VALIDATORS HERE

        assert status != null;

        return errors;
    }

    @Override
    public void sanitizeForSaving() {
        this.email = SanitizationHelper.sanitizeEmail(email);
        this.title = SanitizationHelper.sanitizeTitle(title);
        this.description = SanitizationHelper.sanitizeForRichText(description);
    }

    /**
     * Represents the status of the Support Request.
     */
    public enum Status {
        SUBMITTED,
        PENDING,
        REQUEST_TO_CLOSE, 
        RESOLVED;

        // DK if need to keep this function; Gender has it cus they have the default OTHERS option, bu
        // 
        public static Status getStatusEnumValue(String status) {
            try {
                return Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Status.SUBMITTED;  // currently set default to 'submitted', may want to change this or add another status like 'invalid'
            }
        }
    }
    
}
