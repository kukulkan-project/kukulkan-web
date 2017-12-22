package mx.infotec.dads.kukulkan.domain.dialogflow;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import mx.infotec.dads.kukulkan.domain.Context;

public class Result implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("parameters")
    private Map<String, String> parameters;

    @JsonProperty("contexts")
    private List<Context> contexts;

    @JsonProperty("resolvedQuery")
    private String resolvedQuery;

    @JsonProperty("source")
    private String source;

    @JsonProperty("score")
    private Float score;

    @JsonProperty("speech")
    private String speech;

    @JsonProperty("fulfillment")
    private Fulfillment fulfillment;

    @JsonProperty("actionIncomplete")
    private Boolean actionIncomplete;

    @JsonProperty("action")
    private String action;

    @JsonProperty("metadata")
    private Metadata metadata;

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public void setResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Fulfillment getFullfillment() {
        return fulfillment;
    }

    public void setFullfillment(Fulfillment fullfillment) {
        this.fulfillment = fullfillment;
    }

    public Boolean getActionIncomplete() {
        return actionIncomplete;
    }

    public void setActionIncomplete(Boolean actionIncomplete) {
        this.actionIncomplete = actionIncomplete;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Returns the index message in fulfillment
     * 
     * @param index
     * @return a Message if the index exists
     */
    public Message getMessage(int index) {
        return fulfillment.getMessage(index);
    }

    /**
     * Search the value of given parameter if this exists in parameters or
     * contexts in the request
     * 
     * @param parameter
     * @return the value of given parameter or empty String if not exists
     */
    public String getParameter(String parameter) {
        if (parameter == null)
            return null;
        String value = parameters.get(parameter);
        if (value != null)
            return value;
        Iterator<Context> iterator = contexts.iterator();
        while (iterator.hasNext()) {
            Context context = iterator.next();
            value = context.getParameters().get(parameter);
            if (value != null)
                return value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Result{" + "parameters='" + parameters + '\'' + ", contexts='" + contexts + '\'' + ", resolvedQuery='"
                + resolvedQuery + '\'' + ", source='" + source + '\'' + ", score='" + score + '\'' + ", speech='"
                + speech + '\'' + ", fullfillment='" + fulfillment + '\'' + ", actionIncomplete='" + actionIncomplete
                + '\'' + ", metadata='" + metadata + '\'' + "}";
    }

}
