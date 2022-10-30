package transfer.money.conversion.services;

import transfer.money.conversion.dtos.AccountDto;

public interface AccountService {
	public AccountDto getAccountByAccountNumber(String accountNumber);
}
