package teammates.common.datatransfer.attributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import teammates.common.util.FieldValidator;
import teammates.common.util.SanitizationHelper;
import teammates.storage.entity.SupportRequest;

/**
 * The data transfer object for {@link SupportRequest} entities.
 */
public final class SupportRequestAttributes extends EntityAttributes<SupportRequest> {

    private String id;
    private String email;
    private String title;
    private String description;
    private SupportRequestStatus status;
    private SupportRequestCategory category;
    private String response;
    private Boolean hasNewChanges;
    private Instant createdAt;
    private Instant modifiedAt;

    private SupportRequestAttributes(String id) {
        this.id = id;
        this.email = "";
        this.title = "";
        this.description = "";
        this.status = SupportRequestStatus.PENDING;
        this.category = SupportRequestCategory.OTHERS;
        this.response = "";
        this.hasNewChanges = true;
        this.createdAt = Instant.now();
        this.modifiedAt = Instant.now();
    }

    /**
     * Gets the {@link SupportRequestAttributes} instance of the given
     * {@link SupportRequest}.
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

        supportRequestAttributes.status = sr.getSupportRequestStatus();

        supportRequestAttributes.category = sr.getSupportRequestCategory();

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

    /**
     * Returns a builder for {@link SupportRequestAttributes}.
     */
    public static Builder builder(String id) {
        return new Builder(id);
    }

    @Override
    public SupportRequest toEntity() {
        return new SupportRequest(email, title, description, status, category, response, hasNewChanges);
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

    /**
     * Sorts the list of support requests by createdAt, with the latest as the first
     * element.
     */
    public static void sortByCreatedAt(List<SupportRequestAttributes> supportRequestAttributes) {
        supportRequestAttributes.sort(Comparator.comparing(SupportRequestAttributes::getCreatedAt));
        Collections.reverse(supportRequestAttributes);
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
     * Updates with {@link UpdateOptions}.
     */
    public void update(UpdateOptions updateOptions) {
        updateOptions.emailOption.ifPresent(e -> email = e);
        updateOptions.createdAtOption.ifPresent(c -> createdAt = c);
        updateOptions.modifiedAtOption.ifPresent(m -> modifiedAt = m);
        updateOptions.statusOption.ifPresent(s -> status = s);
        updateOptions.categoryOption.ifPresent(c -> category = c);
        updateOptions.titleOption.ifPresent(t -> title = t);
        updateOptions.descriptionOption.ifPresent(d -> description = d);
        updateOptions.responseOption.ifPresent(d -> response = d);
        updateOptions.hasNewChangesOption.ifPresent(h -> hasNewChanges = h);
    }

    /**
     * Represents the status of the Support Request.
     */
    public enum SupportRequestStatus {
        SUBMITTED,
        PENDING,
        REQUEST_TO_CLOSE,
        CLOSED;

        public static SupportRequestStatus getSupportRequestStatusEnumValue(String status) {
            try {
                return SupportRequestStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                return SupportRequestStatus.SUBMITTED;
            }
        }
    }

    /*
     * Represents the category of the Support Request.
     */
    public enum SupportRequestCategory {
        BUG_REPORT,
        NEW_FEATURE,
        INQUIRY,
        OTHERS;

        public static SupportRequestCategory getSupportRequestCategoryEnumValue(String category) {
            if (category == null) {
                return SupportRequestCategory.OTHERS;
            }

            try {
                return SupportRequestCategory.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                return SupportRequestCategory.OTHERS;
            }

        }
    }

    /**
     * Returns a {@link UpdateOptions.Builder} to build {@link UpdateOptions} for a
     * support request.
     */
    public static UpdateOptions.Builder updateOptionsBuilder(String id) {
        return new UpdateOptions.Builder(id);
    }

    /**
     * Returns a {@link UpdateOptions.Builder} to build on top of
     * {@code updateOptions}.
     */
    public static UpdateOptions.Builder updateOptionsBuilder(UpdateOptions updateOptions) {
        return new UpdateOptions.Builder(updateOptions);
    }

    /**
     * A builder for {@link SupportRequestAttributes}.
     */
    public static class Builder extends BasicBuilder<SupportRequestAttributes, Builder> {
        private final SupportRequestAttributes supportRequestAttributes;

        private Builder(String id) {
            super(new UpdateOptions(id));
            thisBuilder = this;

            supportRequestAttributes = new SupportRequestAttributes(id);
        }

        @Override
        public SupportRequestAttributes build() {
            supportRequestAttributes.update(updateOptions);

            return supportRequestAttributes;
        }
    }

    /**
     * Helper class to specific the fields to update in
     * {@link SupportRequestAttributes}.
     */
    public static class UpdateOptions {
        private String id;

        private UpdateOption<String> emailOption = UpdateOption.empty();
        private UpdateOption<Instant> createdAtOption = UpdateOption.empty();
        private UpdateOption<Instant> modifiedAtOption = UpdateOption.empty();
        private UpdateOption<SupportRequestStatus> statusOption = UpdateOption.empty();
        private UpdateOption<SupportRequestCategory> categoryOption = UpdateOption.empty();
        private UpdateOption<String> titleOption = UpdateOption.empty();
        private UpdateOption<String> descriptionOption = UpdateOption.empty();
        private UpdateOption<String> responseOption = UpdateOption.empty();
        private UpdateOption<Boolean> hasNewChangesOption = UpdateOption.empty();

        private UpdateOptions(String id) {
            assert id != null;

            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "SupportRequestAttributes.UpdateOptions ["
                    + "email = " + emailOption
                    + ", createdAt = " + createdAtOption
                    + ", modifiedAt = " + modifiedAtOption
                    + ", status = " + statusOption
                    + ", category = " + categoryOption
                    + ", title = " + titleOption
                    + ", description = " + descriptionOption
                    + ", response = " + responseOption
                    + ", hasNewChanges = " + hasNewChangesOption
                    + "]";
        }

        /**
         * Builder class to build {@link UpdateOptions}.
         */
        public static class Builder extends BasicBuilder<UpdateOptions, Builder> {

            private Builder(UpdateOptions updateOptions) {
                super(updateOptions);
                assert updateOptions != null;
                thisBuilder = this;
            }

            private Builder(String id) {
                super(new UpdateOptions(id));
                thisBuilder = this;
            }

            @Override
            public UpdateOptions build() {
                return updateOptions;
            }

        }

    }

    /**
     * Basic builder to build {@link SupportRequestAttributes} related classes.
     *
     * @param <T> type to be built
     * @param <B> type of the builder
     */
    private abstract static class BasicBuilder<T, B extends BasicBuilder<T, B>> {

        UpdateOptions updateOptions;
        B thisBuilder;

        BasicBuilder(UpdateOptions updateOptions) {
            this.updateOptions = updateOptions;
        }

        public B withEmail(String email) {
            assert email != null;

            updateOptions.emailOption = UpdateOption.of(email);
            return thisBuilder;
        }

        public B withCreatedAt(Instant createdAt) {
            assert createdAt != null;

            updateOptions.createdAtOption = UpdateOption.of(createdAt);
            return thisBuilder;
        }

        public B withModifiedAt(Instant modifiedAt) {
            assert modifiedAt != null;

            updateOptions.modifiedAtOption = UpdateOption.of(modifiedAt);
            return thisBuilder;
        }

        public B withSupportRequestStatus(SupportRequestStatus status) {
            assert status != null;

            updateOptions.statusOption = UpdateOption.of(status);
            return thisBuilder;
        }

        public B withSupportRequestCategory(SupportRequestCategory category) {
            assert category != null;

            updateOptions.categoryOption = UpdateOption.of(category);
            return thisBuilder;
        }

        public B withTitle(String title) {
            assert title != null;

            updateOptions.titleOption = UpdateOption.of(title);
            return thisBuilder;
        }

        public B withDescription(String description) {
            assert description != null;

            updateOptions.descriptionOption = UpdateOption.of(description);
            return thisBuilder;
        }

        public B withResponse(String response) {
            assert response != null;

            updateOptions.responseOption = UpdateOption.of(response);
            return thisBuilder;
        }

        public B withHasNewChanges(Boolean hasNewChanges) {
            assert hasNewChanges != null;

            updateOptions.hasNewChangesOption = UpdateOption.of(hasNewChanges);
            return thisBuilder;
        }

        public abstract T build();

    }

}
