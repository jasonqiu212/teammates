package teammates.ui.webapi;

import java.util.List;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;
import teammates.common.util.Const;
import teammates.common.util.FieldValidator;
import teammates.ui.output.SupportRequestsData;

/**
 * Action: Gets a list of Support Requests.
 *   - If admin: get all Support Requests.
 *   - otherwise: get all Support Requests with matching email.
 */
public class GetSupportRequestsAction extends Action {

    @Override
    AuthType getMinAuthLevel() {
        return AuthType.PUBLIC;
    }

    @Override
    void checkSpecificAccessControl() throws InvalidHttpParameterException {
        if (userInfo != null && userInfo.isAdmin) {
            return;
        }
        String emailString = getRequestParamValue(Const.ParamsNames.SUPPORT_REQUEST_EMAIL);
        String emailErrorMessage = FieldValidator.getInvalidityInfoForEmail(emailString);
        if (!emailErrorMessage.isEmpty()) {
            throw new InvalidHttpParameterException(emailErrorMessage);
        }
        return;
    }

    @Override
    public JsonResult execute() {
        String emailString = getRequestParamValue(Const.ParamsNames.SUPPORT_REQUEST_EMAIL);
        List<SupportRequestAttributes> supportRequestAttributes;

        if (emailString == null && userInfo != null && userInfo.isAdmin) {
            // if request is from admin and email is not specified, retrieve all support requests
            supportRequestAttributes = logic.getAllSupportRequests();
            return new JsonResult(new SupportRequestsData(supportRequestAttributes));
        } 

        // retrieve all support requests for specified email
        String emailErrorMessage = FieldValidator.getInvalidityInfoForEmail(emailString);
        if (!emailErrorMessage.isEmpty()) {
            throw new InvalidHttpParameterException(emailErrorMessage);
        }
        supportRequestAttributes = logic.getSupportRequestsForEmail(emailString);
        return new JsonResult(new SupportRequestsData(supportRequestAttributes));
    }
}
