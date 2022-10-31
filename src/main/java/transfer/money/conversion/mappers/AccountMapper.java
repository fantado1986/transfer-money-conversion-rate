package transfer.money.conversion.mappers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import transfer.money.conversion.domain.Account;
import transfer.money.conversion.domain.AccountType;
import transfer.money.conversion.domain.Currency;
import transfer.money.conversion.dtos.AccountDto;
@Component
public class AccountMapper extends BaseAbstractMapper<Account, AccountDto>{
	  @Autowired
	  private OwnerMapper ownerMapper;
	@Override
	public Account convertToEntity(AccountDto accountDto) {
		Account account = null;
		if(accountDto != null) {
			AtomicReference< Currency> currency = new AtomicReference<>();
			 Arrays.stream(Currency.values())
					.filter(c -> c.getLabel() == accountDto.getCurrency()).findFirst().ifPresent(cu -> currency.set(cu));
			AtomicReference<AccountType> type = new AtomicReference<>();
			 Arrays.stream(AccountType.values())
				.filter(t -> t.getLabel() == accountDto.getType()).findFirst().ifPresent(ty -> type.set(ty));
			account = new Account(accountDto.getId(),accountDto.getNumber(),type.get(),currency.get(),accountDto.getBalance());
			account.setOwner(ownerMapper.convertToEntity(accountDto.getOwnerDto()));

		}
		return account;
	}

	@Override
	public AccountDto convertToDto(Account account) {
		AccountDto accountDto = null;
		if(account != null) {
			accountDto = new AccountDto(account.getId(),account.getAccountNumber(),account.getType().getLabel(),account.getCurrency().getLabel(),account.getBalance());
			accountDto.setOwnerDto(ownerMapper.convertToDto(account.getOwner()));
		}
		return accountDto;
	}


   
	
}

