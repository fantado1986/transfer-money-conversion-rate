package transfer.money.conversion.services.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import transfer.money.conversion.TestsBase;
import transfer.money.conversion.domain.Account;
import transfer.money.conversion.domain.AccountType;
import transfer.money.conversion.domain.Currency;
import transfer.money.conversion.domain.Transaction;
import transfer.money.conversion.domain.TransactionType;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.dtos.TransferMoneyRequest;
import transfer.money.conversion.dtos.TransferMoneyResponse;
import transfer.money.conversion.mappers.AccountMapper;
import transfer.money.conversion.repository.AccountRepository;
import transfer.money.conversion.repository.TransactionRepository;
import transfer.money.conversion.services.AccountService;

import static org.mockito.ArgumentMatchers.any;

public class TransactionServiceTest extends TestsBase {
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private TransactionRepository transactionRepository;
	@InjectMocks
	@Spy
	private TransactionServiceImpl transactionService;
	
    @Mock
    private AccountService accountService;
    
    @Mock
    private AccountMapper accountMapper;
    
	private Account account1;
	private Account account2;
	
	private TransferMoneyResponse transferMoneyResponse;
	@BeforeEach
	private void init() {
		account1 = new Account(1L, "1111", AccountType.CURRENT, Currency.EUR , new BigDecimal(10));
		account2 = new Account(2L, "2222", AccountType.CURRENT, Currency.EUR , new BigDecimal(10));
		AccountDto accountDto1 = new AccountDto(1L, "1111", "CURRENT","EUR" , new BigDecimal(10));
		AccountDto accountDto2 = new AccountDto(2L, "2222", "CURRENT","EUR" , new BigDecimal(10));
		Mockito.when(accountRepository.findByAccountNumber("1111")).thenReturn(Optional.of(account1));
		Mockito.when(accountRepository.findByAccountNumber("2222")).thenReturn(Optional.of(account1));
		Mockito.when(accountService.getAccountByAccountNumber("1111")).thenReturn(accountDto1);
		Mockito.when(accountService.getAccountByAccountNumber("2222")).thenReturn(accountDto2);

		Mockito.when(accountRepository.save(account1)).thenReturn(account1);
		Mockito.when(accountRepository.save(account2)).thenReturn(account2);
		Mockito.when(accountRepository.save(account2)).thenReturn(account2);
		Mockito.when(accountMapper.convertToEntity(accountDto1)).thenReturn(account1);
		Mockito.when(accountMapper.convertToEntity(accountDto2)).thenReturn(account2);
		Mockito.when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction(LocalDateTime.now(),TransactionType.CREDIT,new BigDecimal(3),account2,"333123RT"));
	}
	@Test
	public void testTransferWithConcurrency() throws InterruptedException {
		TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest();

		BigDecimal amount = new BigDecimal(3);
		transferMoneyRequest.setAmount(amount);
		transferMoneyRequest.setSourceAccount("1111");
		transferMoneyRequest.setDestionationAccount("2222");
	    int numberOfThreads = 2;
	    ExecutorService service = Executors.newFixedThreadPool(10);
	    CountDownLatch latch = new CountDownLatch(numberOfThreads);
	    for (int i = 0; i < numberOfThreads; i++) {
	        service.submit(() -> {
	            try {
	              transferMoneyResponse =transactionService.transferMoney(transferMoneyRequest);
	            } catch (InterruptedException e) {
	                // Handle exception
	              e.printStackTrace();
	            }
	            latch.countDown();
	        });
	    }
	    latch.await();
	    assertEquals(numberOfThreads, transactionService.getCount());
	    assertNotNull(transferMoneyResponse);
	    // 4 times as we do two Synchronized Transfer and for each one we save two transactions for debit and credit
	    Mockito.verify(transactionRepository, Mockito.times(4)).save(any(Transaction.class));
	}
	 

}
