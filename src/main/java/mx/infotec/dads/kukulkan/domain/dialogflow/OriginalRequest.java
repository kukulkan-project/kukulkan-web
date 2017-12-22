package mx.infotec.dads.kukulkan.domain.dialogflow;

public class OriginalRequest {
	
	private String source;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
    public String toString() {
        return "OriginalRequest{" +
            "source='" + source + '\'' +
            "}";
    }

}
