package transfer.money.conversion.services;

import transfer.money.conversion.dtos.TransferMoneyRequest;
import transfer.money.conversion.dtos.TransferMoneyResponse;

public interface TransactionService {
	TransferMoneyResponse transferMoney(TransferMoneyRequest transferMoneyRequest) throws InterruptedException;
}
