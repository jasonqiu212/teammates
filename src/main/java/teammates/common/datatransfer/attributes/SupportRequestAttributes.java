package teammates.common.datatransfer.attributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import teammates.common.util.FieldValidator;
import teammates.common.util.JsonUtils;
import teammates.common.util.SanitizationHelper;
import teammates.common.util.StringHelper;
import teammates.storage.entity.SupportRequest;

/**
 * The data transfer object for {@link StudentProfile} entities.
 */
public final class SupportRequestAttributes extends EntityAttributes<SupportRequest> {


    @Override
    public SupportRequest toEntity() {
        return new SupportRequest(googleId, shortName, email, institute, nationality, gender.name().toLowerCase(),
                                  moreInfo);
    }

    @Override
    public void sanitizeForSaving() {
        this.googleId = SanitizationHelper.sanitizeGoogleId(this.googleId);
    }

    private enum SupportRequestStatus {
        SUBMITTED,
        PENDING,
        REQUEST_TO_CLOSE, 
        RESOLVED
    }
    
}
