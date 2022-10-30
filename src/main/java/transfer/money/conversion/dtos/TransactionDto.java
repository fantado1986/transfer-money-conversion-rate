package transfer.money.conversion.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   
	    private Long id;
	    private BigDecimal amount;
	    private AccountDto accountDto;
	    private String transactionType;
	    private String transactionDate;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public AccountDto getAccountDto() {
			return accountDto;
		}
		public void setAccountDto(AccountDto accountDto) {
			this.accountDto = accountDto;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public String getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(String transactionDate) {
			this.transactionDate = transactionDate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "TransactionDto [id=" + id + ", amount=" + amount + ", accountDto=" + accountDto
					+ ", transactionType=" + transactionType + ", transactionDate=" + transactionDate + "]";
		}
	    
	    
}
