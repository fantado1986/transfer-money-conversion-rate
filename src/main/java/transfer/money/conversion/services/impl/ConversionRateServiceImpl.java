package transfer.money.conversion.services.impl;

import java.util.Optional;

import org.apache.commons.lang3.EnumUtils;

import transfer.money.conversion.domain.Currency;
import transfer.money.conversion.dtos.ConversionRateDTO;
import transfer.money.conversion.exception.BusinessException;
import transfer.money.conversion.services.ConversionRateService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@Service
public class ConversionRateServiceImpl implements ConversionRateService{

    private boolean iscurrencyValidAndCorrect(String currency) {
        return EnumUtils.isValidEnum(Currency.class, currency);
    }
    
    @Cacheable("conversion_rate")
    public Optional<ConversionRateDTO> getConversionRateValue(String fromCurrency, String toCurrency) throws BusinessException {
    	if (!iscurrencyValidAndCorrect(fromCurrency) || !iscurrencyValidAndCorrect(toCurrency)) {
    		throw new IllegalArgumentException("incorrect currency.");
    		}
    	
    	Double conversionRate = ConversionRateHelper.getConversionRate(fromCurrency, toCurrency);
    	if(conversionRate == null) {
    		throw new BusinessException("Failed to bring the conversion rate.");
    	}
        ConversionRateDTO conversionRateDTO = new ConversionRateDTO();
        conversionRateDTO.setComingCurrency(fromCurrency);
        conversionRateDTO.setTargetCurrency(toCurrency);
        conversionRateDTO.setConversionRate(conversionRate);
        
        return Optional.of(conversionRateDTO);
    }

}
