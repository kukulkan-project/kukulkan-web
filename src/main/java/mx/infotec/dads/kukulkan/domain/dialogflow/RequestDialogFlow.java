package mx.infotec.dads.kukulkan.domain.dialogflow;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDialogFlow implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("sessionId")
    private String sessionId;

    @JsonProperty("result")
    private Result result;

    @JsonProperty("id")
    private String id;

    @JsonProperty("originalRequest")
    private OriginalRequest originalRequest;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OriginalRequest getOriginalRequest() {
        return originalRequest;
    }

    public void setOriginalRequest(OriginalRequest originalRequest) {
        this.originalRequest = originalRequest;
    }

    public String getParameter(String parameter) {
        return result.getParameter(parameter);
    }

    /**
     * Returns the index Message in result
     * 
     * @param index
     * @return a Message if the index exists null if not exists
     */
    public Message getMessage(int index) {
        return result.getMessage(index);
    }

    @Override
    public String toString() {
        return "RequestDialogFlow{" + "lang='" + lang + '\'' + ", status='" + status + '\'' + ", timestamp='"
                + timestamp + '\'' + ", sessionId='" + sessionId + '\'' + ", result='" + result + '\'' + ", id='" + id
                + '\'' + ", originalRequest='" + originalRequest + '\'' + "}";
    }

}
