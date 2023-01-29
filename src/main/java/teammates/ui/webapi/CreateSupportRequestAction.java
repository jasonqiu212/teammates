package teammates.ui.webapi;

import java.time.Instant;
import java.util.UUID;

import org.apache.http.HttpStatus;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.exception.EntityAlreadyExistsException;
import teammates.common.exception.InvalidParametersException;
import teammates.ui.output.SupportRequestData;
import teammates.ui.request.InvalidHttpRequestBodyException;
import teammates.ui.request.SupportRequestCreateRequest;

/**
 * Action: Creates a new Support Request.
 */
public class CreateSupportRequestAction extends Action {

    @Override
    AuthType getMinAuthLevel() {
        return AuthType.PUBLIC;
    }

    @Override
    void checkSpecificAccessControl() throws UnauthorizedAccessException {
        // Support Request Creation is available to everyone
    }

    @Override
    public JsonResult execute() throws InvalidHttpRequestBodyException, InvalidOperationException {
        SupportRequestCreateRequest supportRequestRequest = getAndValidateRequestBody(SupportRequestCreateRequest.class);

        Instant createdAt = Instant.ofEpochMilli(supportRequestRequest.getCreatedAt());
        Instant modifiedAt = Instant.ofEpochMilli(supportRequestRequest.getModifiedAt());

        SupportRequestAttributes newSupportRequest = SupportRequestAttributes.builder(UUID.randomUUID().toString()).withEmail(supportRequestRequest.getEmail())
                .withCreatedAt(createdAt)
                .withModifiedAt(modifiedAt)
                .withSupportRequestStatus(supportRequestRequest.getSupportRequestStatus())
                .withSupportRequestCategory(supportRequestRequest.getSupportRequestCategory())
                .withTitle(supportRequestRequest.getTitle())
                .withDescription(supportRequestRequest.getDescription())
                .withResponse(supportRequestRequest.getResponse())
                .withHasNewChanges(supportRequestRequest.getHasNewChanges())
                .build();

        try {
            return new JsonResult(new SupportRequestData(logic.createSupportRequest(newSupportRequest)));
        } catch (InvalidParametersException e) {
            throw new InvalidHttpRequestBodyException(e);
        } catch (EntityAlreadyExistsException e) {
            return new JsonResult(e.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
