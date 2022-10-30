package transfer.money.conversion.domain;

public enum TransactionType {
	
	DEBIT("DEBIT"), CREDIT("CREDIT");
	
	private String value;

	TransactionType(String val) {
		value = val;
	}

	public String getValue() {
		return value;
	}
}
