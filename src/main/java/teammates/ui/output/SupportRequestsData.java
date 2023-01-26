package teammates.ui.output;

import java.util.List;
import java.util.stream.Collectors;

import teammates.common.datatransfer.attributes.SupportRequestAttributes;

/**
 * The API output for a list of support requests.
 */
public class SupportRequestsData extends ApiOutput {
    private final List<SupportRequestData> supportRequests;

    public SupportRequestsData(List<SupportRequestAttributes> supportRequestAttributes) {
        supportRequests = supportRequestAttributes.stream().map(SupportRequestData::new).collect(Collectors.toList());
    }

    public List<SupportRequestData> getSupportRequests() {
        return supportRequests;
    }
    
}
