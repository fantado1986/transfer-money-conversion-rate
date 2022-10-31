package transfer.money.conversion.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import transfer.money.conversion.TestsBase;
import transfer.money.conversion.domain.Owner;
import transfer.money.conversion.dtos.OwnerDto;


public class OwnerMapperTest  extends TestsBase{

	 @Autowired
	    private OwnerMapper ownerMapper;
	    
	    Owner owner;
	    List<Owner> owners;
	    @BeforeEach
	  		public void setUp() { 
	            owner = new Owner(3L,"fadi","fantado","ERFG4321245");
	            owners = new ArrayList<>();
	            owners.add(owner);
	  		}

	    @Test
	    public void testMapEntityToDto() {

	    	OwnerDto ownerDto = ownerMapper.convertToDto(owner);
	        assertNotNull(ownerDto);
	        assertEquals(ownerDto.getFirstName(),owner.getFirstName());
	        assertEquals(ownerDto.getImmatriculationSocialNumber(),owner.getImmatriculationSocialNumber());
	        assertEquals(ownerDto.getLastName(),owner.getLastName());
	        assertEquals(ownerDto.getId(),owner.getId());

	    }
	    
	    @Test
	    public void testMapEntitiesToDtos() {

	    	List<OwnerDto> ownerDtos = ownerMapper.convertToDtoList(owners);

	        assertNotNull(ownerDtos);
	        assertEquals(ownerDtos.size(),1);
	        assertEquals(ownerDtos.get(0).getImmatriculationSocialNumber(),owner.getImmatriculationSocialNumber());

	    }
	
}
