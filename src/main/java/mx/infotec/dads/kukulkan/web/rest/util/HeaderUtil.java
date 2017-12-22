package mx.infotec.dads.kukulkan.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "kukulkancraftsmanApp";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String angularMessageId, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-kukulkancraftsmanApp-alert", angularMessageId);
        headers.add("X-kukulkancraftsmanApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders generateSuccessStatus(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".action.generate.success", param);
    }
    
    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createSuccessDataStoreStatus(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".action.connection.success", param);
    }

    public static HttpHeaders createSchemaSuccessStatus(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".action.createSchema.success", param);
    }

    public static HttpHeaders createSchemaFailureStatus(String entityName, String defaultMessage) {
        return createModalFailureAlert(entityName, APPLICATION_NAME + "." + entityName + ".action.createSchema.failure.general",
                defaultMessage);
    }
    
    public static HttpHeaders dropSchemaSuccessStatus(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".action.dropSchema.success", param);
    }

    public static HttpHeaders dropSchemaFailureStatus(String entityName, String defaultMessage) {
        return createModalFailureAlert(entityName, APPLICATION_NAME + "." + entityName + ".action.dropSchema.failure.general",
                defaultMessage);
    }
    
    public static HttpHeaders createFailureDataStoreStatus(String entityName, String defaultMessage) {
        return createModalFailureAlert(entityName, APPLICATION_NAME + "." + entityName + ".action.connection.failure",
                defaultMessage);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-kukulkancraftsmanApp-error", "error." + errorKey);
        headers.add("X-kukulkancraftsmanApp-params", entityName);
        return headers;
    }

    public static HttpHeaders createModalFailureAlert(String entityName, String angularMessageId,
            String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-kukulkancraftsmanApp-error", angularMessageId);
        headers.add("X-kukulkancraftsmanApp-params", entityName);
        return headers;
    }

}
