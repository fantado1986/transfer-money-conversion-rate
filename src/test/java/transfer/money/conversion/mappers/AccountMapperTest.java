package transfer.money.conversion.mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import transfer.money.conversion.TestsBase;
import transfer.money.conversion.domain.Account;
import transfer.money.conversion.dtos.AccountDto;
import transfer.money.conversion.dtos.OwnerDto;

public class AccountMapperTest extends TestsBase{
	 

	    @Autowired
	    private AccountMapper accountMapper;
	    
	    AccountDto accountDto;
	    OwnerDto ownerDto;
	    List<AccountDto> accountDtos;
	    @BeforeEach
	  		public void setUp() { 
	            accountDto = new AccountDto(1L, "1111333344445555", "CURRENT","EUR" , new BigDecimal(1000000));
	            ownerDto = new OwnerDto(3L,"fadi","fantado","ERFG4321245");
	            accountDto.setOwnerDto(ownerDto);
	            accountDtos = new ArrayList<>();
	            accountDtos.add(accountDto);
	  		}

	    @Test
	    public void testMapDtoToEntity() {

	    	Account account = accountMapper.convertToEntity(accountDto);
	        assertNotNull(account);
	        assertEquals(account.getAccountNumber(),accountDto.getNumber());
	        assertEquals(account.getBalance(),accountDto.getBalance());
	        assertEquals(account.getCurrency().getLabel(),accountDto.getCurrency());
	        assertEquals(account.getType().getLabel(),accountDto.getType());
	        assertEquals(account.getId(),accountDto.getId());
	        assertEquals(account.getOwner().getLastName(),accountDto.getOwnerDto().getLastName());

	    }
	    
	    @Test
	    public void testMapDtosToEntities() {

	    	List<Account> accounts = accountMapper.convertToEntityList(accountDtos);

	        assertNotNull(accounts);
	        assertEquals(accounts.size(),1);
	        assertEquals(accounts.get(0).getAccountNumber(),accountDto.getNumber());

	    }
	
	
}

