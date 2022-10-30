package transfer.money.conversion.services;

import java.util.List;


import transfer.money.conversion.dtos.OwnerDto;

public interface OwnerService {
	List<OwnerDto> getOwners();
	
	OwnerDto getOwnerByImmatriculation(String immatriculationSocialNumber);
}
