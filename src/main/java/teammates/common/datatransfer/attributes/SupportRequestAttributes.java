package teammates.common.datatransfer.attributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import teammates.common.util.FieldValidator;
import teammates.common.util.SanitizationHelper;
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
    private Category category;
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
        this.category = Category.OTHERS;
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

        supportRequestAttributes.category = Category.getCategoryEnumValue(sr.getCategory());

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
        return new SupportRequest(email, title, description, status.name().toLowerCase(), category.name().toLowerCase(), response, hasNewChanges);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    @Override
    public List<String> getInvalidityInfo() {
        List<String> errors = new ArrayList<>();

        addNonEmptyError(FieldValidator.getInvalidityInfoForEmail(email), errors);

        addNonEmptyError(FieldValidator.getInvalidityInfoForSupportRequestTitle(title), errors);

        addNonEmptyError(FieldValidator.getInvalidityInfoForSupportRequestDescription(description), errors);

        addNonEmptyError(FieldValidator.getInvalidityInfoForSupportRequestResponse(response), errors);

        assert status != null;

        assert category != null;

        return errors;
    }

    @Override
    public void sanitizeForSaving() {
        this.email = SanitizationHelper.sanitizeEmail(email);
        this.title = SanitizationHelper.sanitizeTitle(title);
        this.description = SanitizationHelper.sanitizeTextField(description);
        this.response = SanitizationHelper.sanitizeTextField(response);
    }

    /**
     * Represents the status of the Support Request.
     */
    public enum Status {
        SUBMITTED,
        PENDING,
        REQUEST_TO_CLOSE, 
        CLOSED;

        public static Status getStatusEnumValue(String status) {
            try {
                return Status.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Status.SUBMITTED;
            }
        }
    }
    
    /*
     * Represents the category of the Support Request. 
     */
    public enum Category {
        BUG_REPORT,
        NEW_FEATURE,
        INQUIRY,
        OTHERS;

        public static Category getCategoryEnumValue(String category) {
            if (category == null) {
                return Category.OTHERS;
            }

            try {
                return Category.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Category.OTHERS;
            }
            
        }
    }

}
