package mx.infotec.dads.kukulkan.domain.dialogflow;

public class Status {
	
	private String errorType;
	
	private Integer code;

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	@Override
    public String toString() {
        return "Status{" +
            "errorType='" + errorType + '\'' +
            ", code='" + code + '\'' +
            "}";
    }

}
