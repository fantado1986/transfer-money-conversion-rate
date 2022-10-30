package transfer.money.conversion.domain;

public enum AccountType {
	CURRENT("CURRENT"), SAVING("SAVING"), CREDIT("CREDIT");

	public final String label;
	
	private AccountType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
