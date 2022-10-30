package transfer.money.conversion.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import transfer.money.conversion.TestsBase;
import transfer.money.conversion.domain.Owner;
import transfer.money.conversion.dtos.OwnerDto;
import transfer.money.conversion.mappers.OwnerMapper;
import transfer.money.conversion.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;


@ExtendWith(MockitoExtension.class)
public class OwnerServiceImplTest extends TestsBase{
	
	public static final String TEST_IMMATRICULATION_NUMBER = "ERFG4321245";
	
	@Spy
	@InjectMocks
	private OwnerServiceImpl ownerService;
	
	@Mock
	private OwnerMapper ownerMapper;

	@Mock
	private OwnerRepository ownerRepository;
	Owner testOwner;
	OwnerDto ownerDto;
	 @BeforeEach
		public void setUp() { 
		 testOwner = new Owner(3L,"fadi","fantado","ERFG4321245");
	    	ownerDto =new OwnerDto(3L,"fadi","fantado","ERFG4321245");

		}
	
    @Test
    public void testGetOwnerDtoByImmatriculationNumber() {
               
        when(ownerRepository.findByImmatriculationSocialNumber(TEST_IMMATRICULATION_NUMBER)).thenReturn(Optional.of(testOwner));
        when(ownerMapper.convertToDto(testOwner)).thenReturn(ownerDto);
        OwnerDto resultOwnerDto = ownerService.getOwnerByImmatriculation(TEST_IMMATRICULATION_NUMBER);

        assertEquals(resultOwnerDto.getLastName(), testOwner.getLastName());
        assertTrue(resultOwnerDto.getImmatriculationSocialNumber().equals(TEST_IMMATRICULATION_NUMBER));
    }
    
    
    @Test
    public void testGetAllOwnerDto() {
               
        when(ownerRepository.findAll()).thenReturn(Arrays.asList(testOwner));
        when(ownerMapper.convertToDtoList(Arrays.asList(testOwner))).thenReturn(Arrays.asList(ownerDto));
        List<OwnerDto> resultOwnerDtos = ownerService.getOwners();

        assertNotNull(resultOwnerDtos);
        assertTrue(resultOwnerDtos.contains(ownerDto));
    }
	
	
	
}
