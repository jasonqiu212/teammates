package teammates.ui.webapi;

import java.time.Instant;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.UpdateOptions;
import teammates.common.exception.EntityDoesNotExistException;
import teammates.common.exception.InvalidParametersException;
import teammates.common.util.Const;
import teammates.ui.output.SupportRequestData;
import teammates.ui.request.InvalidHttpRequestBodyException;
import teammates.ui.request.SupportRequestUpdateRequest;

/**
 * Action: Updates a new Support Request.
 */
public class UpdateSupportRequestAction extends Action {

    @Override
    AuthType getMinAuthLevel() {
        return AuthType.PUBLIC;
    }

    @Override
    void checkSpecificAccessControl() throws UnauthorizedAccessException {
        // Support Request Update is available to everyone
    }

    @Override
    public JsonResult execute() throws InvalidHttpRequestBodyException {
        String supportRequestId = getNonNullRequestParamValue(Const.ParamsNames.SUPPORT_REQUEST_ID);
        SupportRequestUpdateRequest supportRequestRequest = getAndValidateRequestBody(
                SupportRequestUpdateRequest.class);

        Instant createdAt = Instant.ofEpochMilli(supportRequestRequest.getCreatedAt());
        Instant modifiedAt = Instant.ofEpochMilli(supportRequestRequest.getModifiedAt());

        UpdateOptions newSupportRequest = SupportRequestAttributes
                .updateOptionsBuilder(supportRequestId)
                .withEmail(supportRequestRequest.getEmail())
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
            return new JsonResult(new SupportRequestData(logic.updateSupportRequest(newSupportRequest)));
        } catch (InvalidParametersException e) {
            throw new InvalidHttpRequestBodyException(e);
        } catch (EntityDoesNotExistException e) {
            throw new EntityNotFoundException(e);
        }
    }

}
