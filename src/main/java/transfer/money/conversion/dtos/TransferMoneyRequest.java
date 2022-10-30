package transfer.money.conversion.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferMoneyRequest implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String sourceAccount;
	    private String destionationAccount;
	    private BigDecimal amount;
		public String getSourceAccount() {
			return sourceAccount;
		}
		public void setSourceAccount(String sourceAccount) {
			this.sourceAccount = sourceAccount;
		}
		public String getDestionationAccount() {
			return destionationAccount;
		}
		public void setDestionationAccount(String destionationAccount) {
			this.destionationAccount = destionationAccount;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	    
	    
}
