package transfer.money.conversion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import transfer.money.conversion.dtos.OwnerDto;
import transfer.money.conversion.services.OwnerService;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {
	@Autowired
    private OwnerService ownerService;
	
    @GetMapping(value = "/all")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getOwners());
    }
    
	@GetMapping(value = "/owner/{immatriculationSocialNum}")
	public ResponseEntity<OwnerDto> getOwner(@PathVariable("immatriculationSocialNum") String immatriculationSocialNumber) {
	    return ResponseEntity.ok(ownerService.getOwnerByImmatriculation(immatriculationSocialNumber));
	}

	
	
}
