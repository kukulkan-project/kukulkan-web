package mx.infotec.dads.kukulkan.domain.enumeration;

/**
 * The TableTypes enumeration.
 */
public enum TableTypes {
	TABLE("TABLE"), TABLE_VIEW("TABLE,VIEW"), VIEW("VIEW");
	private String value;

	private TableTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
