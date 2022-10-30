package transfer.money.conversion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import transfer.money.conversion.TestsBase;
import transfer.money.conversion.dtos.TransferMoneyRequest;
import transfer.money.conversion.dtos.TransferMoneyResponse;
import transfer.money.conversion.exception.BusinessException;



class TransactionControllerTest extends TestsBase{

	  
	@Autowired
	private  TransactionController controller;
	
	private TransferMoneyRequest transferMoneyRequest;
	
	@Rule
	public ExpectedException expectedEx; 
	
	 @BeforeEach
		public void setUp() { 
		 expectedEx = ExpectedException .none();
		 transferMoneyRequest = new TransferMoneyRequest();
		 transferMoneyRequest.setAmount(new BigDecimal(300));
		 transferMoneyRequest.setSourceAccount("988774656778");
		 transferMoneyRequest.setDestionationAccount("345677899990");
		}
	 

	   @Test
	    public void testTransferMoneyEndPoint() throws Exception {
		   ResponseEntity<TransferMoneyResponse> response = controller.transferMoney(transferMoneyRequest);
		   assertNotNull(response.getBody().getMessage());
		   assertEquals(response.getBody().getMessage(),"Transfer money successeded" );
	    }
	   
	   @Test
	    public void testTransferMoneyEndPointWithFailNoAccount() throws Exception {
		   expectedEx.expect(BusinessException.class);
		    expectedEx.expectMessage("Transaction could not be done due to non existing account");
		   transferMoneyRequest.setSourceAccount("988774656773");//Does Not Exist
	       assertThrows(BusinessException.class, () -> controller.transferMoney(transferMoneyRequest));
	    }
	   
	   @Test
	    public void testTransferMoneyEndPointWithFailNoSufficientBalance() throws Exception {
		   expectedEx.expect(BusinessException.class);
		    expectedEx.expectMessage("There is no sufficient balance to debit from");
		   transferMoneyRequest.setAmount(new BigDecimal(100001));//Does Not Exist
	       assertThrows(BusinessException.class, () -> controller.transferMoney(transferMoneyRequest));
	    }
	   
	   @Test
	    public void testTransferMoneyEndPointWithFailForSavingAccount() throws Exception {
		   expectedEx.expect(BusinessException.class);
		    expectedEx.expectMessage("You can not do transfer fron saving account");
		   transferMoneyRequest.setSourceAccount("456778834566");//Does Not Exist
	       assertThrows(BusinessException.class, () -> controller.transferMoney(transferMoneyRequest));
	    }
	
}
