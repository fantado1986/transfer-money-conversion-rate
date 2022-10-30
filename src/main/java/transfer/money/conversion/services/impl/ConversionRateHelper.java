package transfer.money.conversion.services.impl;

import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import transfer.money.conversion.exception.BusinessException;

@Component
public class ConversionRateHelper {
	
    private final static String CONVERSION_RATE_API_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    private static HashMap<String, Double> exsitingCurrencyMap = new HashMap<String, Double>(40);
    
    public static Double getConversionRate(String fromCurrency, String toCurrency) {
        HashMap<String, Double> conversionRateInMap = getConversionRateInMap();
        Double conversionRateValue = conversionRateInMap.get(toCurrency) / conversionRateInMap.get(fromCurrency);
        return conversionRateValue;
    }
    
    private static HashMap<String, Double> getConversionRateInMap() {
    	exsitingCurrencyMap.clear();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            //Parsing the Xml of the API through a handler to bring the currencies 
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) {
                    if (localName.equals("Cube")) {
                        String currency = attributes.getValue("currency");
                        String rate = attributes.getValue("rate");
                        if (currency != null && rate != null) {
                            try {
                            	exsitingCurrencyMap.put(currency, Double.parseDouble(rate));
                            } catch (Exception e) {
                               throw new BusinessException("Cannot parse currency exchange rate.");
                            }
                        }
                    }
                }
            };
            URL url = new URL(CONVERSION_RATE_API_URL);
            InputSource inputSource = new InputSource(url.openStream());
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(handler);
            xmlReader.parse(inputSource);
            // For EURO AS A REFERENCE BECAUSE IT IS COMING FROM EUROPEAN CENTRAL BANK.
            exsitingCurrencyMap.put("EUR", 1.0);
        } catch (Exception e) {
        	throw new BusinessException("Cannot Extract the exchange rate.");
        }
        return exsitingCurrencyMap;
    }
}
