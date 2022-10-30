package transfer.money.conversion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import transfer.money.conversion.TestsBase;
import transfer.money.conversion.dtos.OwnerDto;
import transfer.money.conversion.services.OwnerService;


class OwnerControllerTest extends TestsBase{

	   
	   @Test
	    public void testGetAllOwnersTest() throws Exception {
		   List<OwnerDto> owners = getOwnerList();
		    assertNotNull(owners);
		    assertEquals(owners.size(),2);
	    }
	   
	   @Test
	    public void testGetOwnerByImmatriculationTest() throws Exception {
		   OwnerDto ownerDto = getOwner("E7361211A");
		    assertNotNull(ownerDto);
		    assertEquals(ownerDto.getImmatriculationSocialNumber(),"E7361211A");
	    }
	   
	   
	   private List<OwnerDto> getOwnerList() {
	            HttpEntity<List<OwnerDto>> entity = new HttpEntity<List<OwnerDto>>(null, headers);
	            ResponseEntity<List<OwnerDto>> response = restTemplate.exchange(
	                    createURLWithPort("/owners/all/"), HttpMethod.GET, entity,
	                    new ParameterizedTypeReference<List<OwnerDto>>() {
	                    });
	            return response.getBody();
	    }
	   
	   private OwnerDto getOwner(String immatriculation) {
           HttpEntity<OwnerDto> entity = new HttpEntity<OwnerDto>(null, headers);
           ResponseEntity<OwnerDto> response = restTemplate.exchange(
                   createURLWithPort("/owners/owner/"+immatriculation), HttpMethod.GET, entity,
                   OwnerDto.class);
           return response.getBody();
   }
	   
	
}
