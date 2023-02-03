package teammates.ui.webapi;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.util.Const;
import teammates.ui.output.SupportRequestData;

/**
 * Action: Gets a support request by ID.
 */
public class GetSupportRequestAction extends Action {

    @Override
    AuthType getMinAuthLevel() {
        return AuthType.PUBLIC;
    }

    @Override
    void checkSpecificAccessControl() throws InvalidHttpParameterException {

    }

    @Override
    public JsonResult execute() {
        String supportRequestId = getNonNullRequestParamValue(Const.ParamsNames.SUPPORT_REQUEST_ID);

        SupportRequestAttributes supportRequest = logic.getSupportRequest(supportRequestId);

        if (supportRequest == null) {
            throw new EntityNotFoundException("Support request does not exist.");
        }

        return new JsonResult(new SupportRequestData(supportRequest));
    }
}
