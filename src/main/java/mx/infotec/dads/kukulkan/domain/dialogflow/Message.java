package mx.infotec.dads.kukulkan.domain.dialogflow;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("speech")
	private String speech;
	
	@JsonProperty("type")
	private Integer type;

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
    public String toString() {
        return "Message{" +
            "speech='" + speech + '\'' +
            ", type='" + type + '\'' +
            "}";
    }

}
