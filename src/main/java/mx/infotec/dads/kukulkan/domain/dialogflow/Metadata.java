package mx.infotec.dads.kukulkan.domain.dialogflow;

public class Metadata {
	
	private String intentId;
	
	private Boolean webhookForSlotFillingUsed;
	
	private String intentName;
	
	private Boolean webhookUsed;

	public String getIntentId() {
		return intentId;
	}

	public void setIntentId(String intentId) {
		this.intentId = intentId;
	}

	public Boolean getWebhookForSlotFillingUsed() {
		return webhookForSlotFillingUsed;
	}

	public void setWebhookForSlotFillingUsed(Boolean webhookForSlotFillingUsed) {
		this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
	}

	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public Boolean getWebhookUsed() {
		return webhookUsed;
	}

	public void setWebhookUsed(Boolean webhookUsed) {
		this.webhookUsed = webhookUsed;
	}
	
	 @Override
	    public String toString() {
	        return "Metadata{" +
	            "intentId='" + intentId + '\'' +
	            ", webhookForSlotFillingUsed='" + webhookForSlotFillingUsed + '\'' +
	            ", intentName='" + intentName + '\'' +
	            ", webhookUsed='" + webhookUsed + '\'' +
	            "}";
	    }

}
