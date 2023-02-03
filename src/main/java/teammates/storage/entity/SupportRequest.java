package teammates.storage.entity;

import java.time.Instant;
import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Translate;

import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;

/**
 * Represents a SupportRequest entity.
 */
@Entity
@Index
public class SupportRequest extends BaseEntity {

    @Id
    private String id;

    private String email;

    private String title;

    private String description;

    private SupportRequestStatus status;

    private SupportRequestCategory category;

    private String response;

    private Boolean hasNewChanges;

    @Translate(InstantTranslatorFactory.class)
    private Instant modifiedAt;

    @Translate(InstantTranslatorFactory.class)
    private Instant createdAt;

    @SuppressWarnings("unused")
    private SupportRequest() {
        // required by Objectify
    }

    public SupportRequest(String email, String title, String description,
            SupportRequestStatus status,
            SupportRequestCategory category,
            String response, Boolean hasNewChanges) {
        this.setEmail(email);
        this.setTitle(title);
        this.setCreatedAt(Instant.now());
        this.setId(UUID.randomUUID().toString());
        this.setDescription(description);
        this.setSupportRequestStatus(status);
        this.setSupportRequestCategory(category);
        this.setResponse(response);
        this.setHasNewChanges(hasNewChanges);
        this.setModifiedAt(Instant.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.trim();
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

    public SupportRequestStatus getSupportRequestStatus() {
        return status;
    }

    public void setSupportRequestStatus(SupportRequestStatus status) {
        this.status = status;
    }

    public SupportRequestCategory getSupportRequestCategory() {
        return category;
    }

    public void setSupportRequestCategory(SupportRequestCategory category) {
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
}
