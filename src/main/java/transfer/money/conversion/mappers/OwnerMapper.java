package transfer.money.conversion.mappers;

import org.springframework.stereotype.Component;

import transfer.money.conversion.domain.Owner;
import transfer.money.conversion.dtos.OwnerDto;

@Component
public class OwnerMapper extends BaseAbstractMapper<Owner, OwnerDto>{
 

	@Override
	public Owner convertToEntity(OwnerDto ownerDto) {
		Owner owner = null;
		if(ownerDto != null) {
			owner = new Owner(ownerDto.getId(),ownerDto.getFirstName(),ownerDto.getLastName(),ownerDto.getImmatriculationSocialNumber());	
		}
		return owner;
	}

	@Override
	public OwnerDto convertToDto(Owner owner) {
		OwnerDto ownerDto = null;
		if(owner != null) {
			ownerDto = new OwnerDto(owner.getId(),owner.getFirstName(),owner.getLastName(),owner.getImmatriculationSocialNumber());	
		}
		return ownerDto;
	}

}
