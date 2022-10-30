package transfer.money.conversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import transfer.money.conversion.dtos.TransferMoneyRequest;
import transfer.money.conversion.dtos.TransferMoneyResponse;
import transfer.money.conversion.services.TransactionService;
@RestController
@RequestMapping(value = "/core-business")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	 @PostMapping("/transfer-money")
	    public ResponseEntity<TransferMoneyResponse> transferMoney(@RequestBody TransferMoneyRequest transferMoneyRequest) throws InterruptedException {
	        return ResponseEntity.ok(transactionService.transferMoney(transferMoneyRequest));
	    }
}
