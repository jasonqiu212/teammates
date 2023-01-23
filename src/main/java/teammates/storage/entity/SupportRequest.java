package teammates.storage.entity;

import java.time.Instant;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Translate;

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

    /* Only accepts: 'submitted', 'pending', 'request_to_close', 'closed' */
    private String status; 

    /* Only accepts: 'bug_report', 'new_feature', 'inquiry', 'others' */
    private String category;

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

    public SupportRequest(String email, String title, String description, String status, String category, String response, Boolean hasNewChanges) {
        this.setEmail(email);
        this.setTitle(title);
        this.setCreatedAt(Instant.now());
        this.setId(generateId(email, createdAt));
        this.setDescription(description);
        this.setStatus(status);
        this.setCategory(category);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    /**
     * Generates an unique ID for the support request.
     */
    public static String generateId(String email, Instant createdAt) {
        // Format: email%createdAt e.g., adam@gmail.com%2022-06-21T19:34:50.630Z
        return email + '%' + createdAt.toString();
    }
}
