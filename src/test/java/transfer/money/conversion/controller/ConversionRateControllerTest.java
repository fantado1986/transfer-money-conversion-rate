package transfer.money.conversion.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import transfer.money.conversion.TestsBase;
import transfer.money.conversion.dtos.ConversionRateDTO;



class ConversionRateControllerTest extends TestsBase{

	  
	@Autowired
	public ConversionRateController controller;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	   @Test
	    public void testGetConversionRateEndPoint() throws Exception {
	        HttpEntity<ConversionRateDTO> entity = new HttpEntity<ConversionRateDTO>(null, headers);
	        ResponseEntity<ConversionRateDTO> response = restTemplate.exchange(createURLWithPort("/conversionRateApi/conversionRate/currency?comingCurrency=USD&targetCurrency=EUR"), HttpMethod.GET,
	                entity, ConversionRateDTO.class);
	        ConversionRateDTO actual = response.getBody();

	        assertTrue("USD".equals(actual.getComingCurrency()));
	        assertTrue("EUR".equals(actual.getTargetCurrency()));
	        assertNotNull(actual.getConversionRate());
	    }
	   
	   @Test
	    public void testGetConversionRateEndPointWithFail() throws Exception {
		   
		    expectedEx.expect(IllegalArgumentException.class);
		    expectedEx.expectMessage("incorrect currency.");
		    
	        HttpEntity<ConversionRateDTO> entity = new HttpEntity<ConversionRateDTO>(null, headers);
	        ResponseEntity<ConversionRateDTO> response = restTemplate.exchange(createURLWithPort("/conversionRateApi/conversionRate/currency?comingCurrency=USL&targetCurrency=EUR"), HttpMethod.GET,
	                entity, ConversionRateDTO.class);
	        ConversionRateDTO actual = response.getBody();
	        assertNull(actual.getConversionRate());
	    }
	
}
