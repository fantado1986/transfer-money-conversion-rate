package transfer.money.conversion.dtos;

import java.io.Serializable;

public class TransferMoneyResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String referenceId;
    
    public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	private String message;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TransferMoneyResponse(String referenceId, String message) {
		super();
		this.referenceId = referenceId;
		this.message = message;
	}
	
	
	
}
