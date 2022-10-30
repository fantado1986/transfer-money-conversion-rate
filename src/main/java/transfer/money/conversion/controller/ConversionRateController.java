package transfer.money.conversion.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import transfer.money.conversion.dtos.ConversionRateDTO;
import transfer.money.conversion.services.ConversionRateService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/conversionRateApi")
public class ConversionRateController {

	@Autowired
	ConversionRateService conversionRateService;

	@GetMapping("/conversionRate/currency")
	@ResponseBody
	public ResponseEntity<ConversionRateDTO> getConversionRate(@RequestParam(required = true) String comingCurrency,
			@RequestParam(required = true) String targetCurrency) {
		Optional<ConversionRateDTO> optConversionRateDTO = conversionRateService.getConversionRateValue(comingCurrency,
				targetCurrency);
		if (optConversionRateDTO.isPresent()) {
			return new ResponseEntity<>(optConversionRateDTO.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
