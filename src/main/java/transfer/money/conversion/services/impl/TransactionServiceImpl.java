package transfer.money.conversion.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transfer.money.conversion.domain.Account;
import transfer.money.conversion.domain.Transaction;
import transfer.money.conversion.domain.TransactionType;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.dtos.TransferMoneyRequest;
import transfer.money.conversion.dtos.TransferMoneyResponse;
import transfer.money.conversion.exception.BusinessException;
import transfer.money.conversion.exception.ResourceNotFoundException;
import transfer.money.conversion.mappers.AccountMapper;
import transfer.money.conversion.repository.AccountRepository;
import transfer.money.conversion.repository.TransactionRepository;
import transfer.money.conversion.services.AccountService;
import transfer.money.conversion.services.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	private int count = 0;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	public TransferMoneyResponse transferMoney(TransferMoneyRequest transferMoneyRequest) throws InterruptedException {
		try {
			AccountDto sourceAccount = accountService
					.getAccountByAccountNumber(transferMoneyRequest.getSourceAccount());
			AccountDto destinationAccount = accountService
					.getAccountByAccountNumber(transferMoneyRequest.getDestionationAccount());

			verifySourceAccountBalance(sourceAccount, transferMoneyRequest.getAmount());
			double conversionRate = 1;
			if(!sourceAccount.getCurrency().equals(destinationAccount.getCurrency())) {
				conversionRate = verifyExtractingConversionRate(sourceAccount,destinationAccount);
			}
			String referenceId = transfer(sourceAccount, destinationAccount, transferMoneyRequest.getAmount(), conversionRate);
			return new TransferMoneyResponse(referenceId, "Transfer money successeded");

		} catch (ResourceNotFoundException e) {
			throw new BusinessException("Transaction could not be done due to non existing account");
		}

	}

	private double verifyExtractingConversionRate(AccountDto sourceAccount, AccountDto destinationAccount) {
		try {
			return ConversionRateHelper.getConversionRate(sourceAccount.getCurrency(),destinationAccount.getCurrency());
		} catch(BusinessException ex){
			throw new BusinessException("Transfer money is not be possible due to unability to extract the "
					+ "exchange rate");
		}
	}

	private synchronized String transfer(AccountDto sourceAccount, AccountDto destinationAccount, BigDecimal amount, double conversionRate)
			throws InterruptedException {

		Account fromAccount = accountMapper.convertToEntity(sourceAccount);
		Account toAccount = accountMapper.convertToEntity(destinationAccount);
		
		 //generate any String representing a unified transaction Reference between the debit and credit transactions
		String generatedReferenceId =UUID.randomUUID().toString();
		
		System.out.println(generatedReferenceId);
		
		// Multiply original amount with conversion rate and assign result to converted amount
        BigDecimal convertedAmount  = amount.multiply(new BigDecimal(conversionRate));
		fromAccount.setBalance(fromAccount.getBalance().subtract(convertedAmount));
		accountRepository.save(fromAccount);

		Transaction debitTransaction = new Transaction(LocalDateTime.now(), TransactionType.DEBIT, convertedAmount.negate(),
				fromAccount, generatedReferenceId);
		transactionRepository.save(debitTransaction);

		toAccount.setBalance(toAccount.getBalance().add(convertedAmount));
		accountRepository.save(toAccount);

		Transaction creditTransaction = new Transaction(LocalDateTime.now(), TransactionType.CREDIT, convertedAmount, toAccount,
				generatedReferenceId);

		transactionRepository.save(creditTransaction);
		count++;
		return generatedReferenceId;

	}

	private void verifySourceAccountBalance(AccountDto accountDto, BigDecimal amount) {
		if(accountDto.getType().equals("SAVING")) {
			throw new BusinessException("You can not do transfer fron saving account");
		}
		if (accountDto.getBalance().compareTo(BigDecimal.ZERO) < 0 || accountDto.getBalance().compareTo(amount) < 0) {
			throw new BusinessException("There is no sufficient balance to debit from");
		}
	}

	public int getCount() {
		return count;
	}

}
