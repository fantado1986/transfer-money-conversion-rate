package transfer.money.conversion.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transfer.money.conversion.domain.Owner;
import transfer.money.conversion.dtos.OwnerDto;
import transfer.money.conversion.exception.ResourceNotFoundException;
import transfer.money.conversion.mappers.OwnerMapper;
import transfer.money.conversion.repository.OwnerRepository;
import transfer.money.conversion.services.OwnerService;


@Service
public class OwnerServiceImpl implements OwnerService{
@Autowired
private OwnerMapper ownerMapper;

@Autowired
private OwnerRepository ownerRepository;
@Transactional
public List<OwnerDto> getOwners() {
	List<Owner> owners = ownerRepository.findAll();
    return ownerMapper.convertToDtoList(owners);
 }
@Transactional
public OwnerDto getOwnerByImmatriculation(String immatriculationSocialNumber) {
    Owner owner = ownerRepository.findByImmatriculationSocialNumber(immatriculationSocialNumber).orElseThrow(()-> new ResourceNotFoundException("Can not found owner with this immatriculation Social Number"));
    return ownerMapper.convertToDto(owner);
}

}
