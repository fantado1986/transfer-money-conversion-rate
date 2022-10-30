package transfer.money.conversion.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import transfer.money.conversion.TestsBase;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.dtos.ConversionRateDTO;



class AccountControllerTest extends TestsBase{

	  
	@Autowired
	public ConversionRateController controller;
	
	
	   @Test
	    public void testGetAccountByAccountNumber() throws Exception {
	        HttpEntity<ConversionRateDTO> entity = new HttpEntity<ConversionRateDTO>(null, headers);
	        String accountNumber ="988774656778"; 
	        ResponseEntity<AccountDto> response = restTemplate.exchange(createURLWithPort("/accounts/account/"+accountNumber), HttpMethod.GET,
	                entity, AccountDto.class);
	        AccountDto actual = response.getBody();
	        assertNotNull(actual);
	        assertTrue(accountNumber.equals(actual.getNumber()));
	        assertTrue("EUR".equals(actual.getCurrency()));
	    }
	
}
