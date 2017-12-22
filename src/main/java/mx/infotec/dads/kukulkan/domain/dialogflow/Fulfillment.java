package mx.infotec.dads.kukulkan.domain.dialogflow;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fulfillment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("speech")
    private String speech;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    /**
     * Returns the index message in messages
     * 
     * @param index
     * @return a Message if the index exists
     */
    public Message getMessage(int index) {
        if ((index >= 0 && index < messages.size()) && !messages.isEmpty()) {
            return messages.get(index);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Fulfillment{" + "messages='" + messages + '\'' + ", speech='" + speech + '\'' + "}";
    }

}
