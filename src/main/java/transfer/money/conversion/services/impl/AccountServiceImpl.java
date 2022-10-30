package transfer.money.conversion.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transfer.money.conversion.domain.Account;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.exception.ResourceNotFoundException;
import transfer.money.conversion.mappers.AccountMapper;
import transfer.money.conversion.repository.AccountRepository;
import transfer.money.conversion.services.AccountService;


@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private AccountRepository accountRepository;

	public AccountDto getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException("Can not found account with this account Number"));
        return accountMapper.convertToDto(account);
    }
}
