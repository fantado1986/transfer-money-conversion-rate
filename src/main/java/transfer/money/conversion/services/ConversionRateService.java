package transfer.money.conversion.services;

import java.util.Optional;

import transfer.money.conversion.dtos.ConversionRateDTO;

public interface ConversionRateService {
	Optional<ConversionRateDTO> getConversionRateValue(String comingCurrency, String targetCurrency);
}
