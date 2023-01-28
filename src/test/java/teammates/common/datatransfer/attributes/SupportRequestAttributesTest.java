package teammates.common.datatransfer.attributes;

import java.time.Instant;

import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestCategory;
import teammates.common.datatransfer.attributes.SupportRequestAttributes.SupportRequestStatus;
import teammates.storage.entity.SupportRequest;
import teammates.test.BaseTestCase;

/**
 * SUT: {@link SupportRequestAttributes}
 */
public class SupportRequestAttributesTest extends BaseTestCase {
    @Test
    public void testValueOf_withAllFieldPopulatedSupportRequestAttributes_shouldGenerateAttributesCorrectly() {
        SupportRequest sr = new SupportRequest("valid_email@gmail.com", "Valid Support Request Title", "valid sr description", "PENDING", "BUG_REPORT", "valid support request response", false);
        SupportRequestAttributes sra = SupportRequestAttributes.valueOf(sr);

        verifySupportRequestsEquals(sra, sr);
    }   

    @Test
    public void testBuilder_withNullArguments_shouldThrowException() {
        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withEmail(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withTitle(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withDescription(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withResponse(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withCreatedAt(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withModifiedAt(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withHasNewChanges(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withSupportRequestStatus(null)
                    .build();
        });

        assertThrows(AssertionError.class, () -> {
            SupportRequestAttributes
                    .builder("supportRequestId")
                    .withSupportRequestCategory(null)
                    .build();
        });
    }

    @Test
    public void testBuilder_withTypicalData_shouldBuildCorrectAttributes() {
        SupportRequestAttributes sra = generateTypicalSupportRequestAttributesObject();
        assertEquals("supportRequestId", sra.getId());
        assertEquals(Instant.ofEpochSecond(1234567890), sra.getCreatedAt());
        assertEquals(Instant.ofEpochSecond(1234567890).plusSeconds(7200), sra.getModifiedAt());
        assertEquals(SupportRequestStatus.PENDING, sra.getSupportRequestStatus());
        assertEquals(SupportRequestCategory.BUG_REPORT, sra.getSupportRequestCategory());
        assertEquals("valid support request title", sra.getTitle());
        assertEquals("valid description", sra.getDescription());
        assertEquals(false, sra.getHasNewChanges());
        assertEquals("valid_email@gmail.com", sra.getEmail());
        assertEquals("valid response", sra.getResponse());
    }

    private SupportRequestAttributes generateTypicalSupportRequestAttributesObject() {
        return SupportRequestAttributes.builder("supportRequestId")
                .withCreatedAt(Instant.ofEpochSecond(1234567890))
                .withModifiedAt(Instant.ofEpochSecond(1234567890).plusSeconds(7200))
                .withSupportRequestStatus(SupportRequestStatus.PENDING)
                .withSupportRequestCategory(SupportRequestCategory.BUG_REPORT)
                .withTitle("valid support request title")
                .withDescription("valid description")
                .withHasNewChanges(false)
                .withEmail("valid_email@gmail.com")
                .withResponse("valid response")
                .build();
    }

    private void verifySupportRequestsEquals(SupportRequestAttributes sra, SupportRequest supportRequest) {
        assertEquals(supportRequest.getId(), sra.getId());
        assertEquals(supportRequest.getCreatedAt(), sra.getCreatedAt());
        assertEquals(supportRequest.getModifiedAt(), sra.getModifiedAt());
        assertEquals(supportRequest.getSupportRequestStatus(), sra.getSupportRequestStatus().toString());
        assertEquals(supportRequest.getSupportRequestCategory(), sra.getSupportRequestCategory().toString());
        assertEquals(supportRequest.getTitle(), sra.getTitle());
        assertEquals(supportRequest.getDescription(), sra.getDescription());
        assertEquals(supportRequest.getResponse(), sra.getResponse());
        assertEquals(supportRequest.getHasNewChanges(), sra.getHasNewChanges());
        assertEquals(supportRequest.getEmail(), sra.getEmail());
    }
}
