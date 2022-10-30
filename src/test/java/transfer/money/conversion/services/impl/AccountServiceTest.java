package transfer.money.conversion.services.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import transfer.money.conversion.TestsBase;
import transfer.money.conversion.domain.Account;
import transfer.money.conversion.domain.AccountType;
import transfer.money.conversion.domain.Currency;
import transfer.money.conversion.domain.Owner;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.mappers.AccountMapper;
import transfer.money.conversion.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest extends TestsBase{
	
	    public static final String TEST_ACCOUNT_NUMBER = "1111333344445555";
	    
	    @Spy
	    @InjectMocks
	    private AccountServiceImpl accountService;
	    
	    @Mock
	    private AccountRepository accountRepository;
	    
	    @Mock
	    private AccountMapper accountMapper;

	    Account testAccount ;
	    Owner owner;
	    AccountDto accountDto;
	    @BeforeEach
		public void setUp() { 
	    	testAccount = new Account(1L, "1111333344445555",AccountType.CURRENT, Currency.EUR , new BigDecimal(1000000));
	    	owner = new Owner(3L,"fadi","fantado","ERFG4321245");
	        testAccount.setOwner(owner);
	        accountDto = new AccountDto(1L, "1111333344445555","CURRENT" ,"EUR" , new BigDecimal(1000000));

		}
	    
	    @Test
	    public void testGetAccountDtoByAccountNumber() {
	      
	       
	        when(accountRepository.findByAccountNumber(TEST_ACCOUNT_NUMBER)).thenReturn(Optional.of(testAccount));
	        when(accountMapper.convertToDto(testAccount)).thenReturn(accountDto);
	        AccountDto resultAccountDto = accountService.getAccountByAccountNumber(TEST_ACCOUNT_NUMBER);

	        assertEquals(resultAccountDto.getBalance(), testAccount.getBalance());
	        assertTrue(resultAccountDto.getNumber().equals(TEST_ACCOUNT_NUMBER));
	    }
	
}
