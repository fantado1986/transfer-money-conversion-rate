package transfer.money.conversion.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDto implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
	    private String number;
	    private String type;
	    private String currency;
	    private BigDecimal balance;
	    private OwnerDto ownerDto;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
		public OwnerDto getOwnerDto() {
			return ownerDto;
		}
		public void setOwnerDto(OwnerDto ownerDto) {
			this.ownerDto = ownerDto;
		}
	    public AccountDto() {
		super();
	}
		
		public AccountDto(Long id, String number, String type,String currency, BigDecimal balance) {
			super();
			this.id = id;
			this.number = number;
			this.type = type;
			this.currency = currency;
			this.balance = balance;
		}
		@Override
		public String toString() {
			return "AccountDto [id=" + id + ", number=" + number + ", type=" + type +", currency=" + currency + ", balance=" + balance
					+ ", ownerDto=" + ownerDto + "]";
		}

	    
	    
}
