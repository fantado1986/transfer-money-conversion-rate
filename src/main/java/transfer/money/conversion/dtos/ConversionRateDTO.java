package transfer.money.conversion.dtos;

	import java.io.Serializable;

	public class ConversionRateDTO implements Serializable {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String comingCurrency;
	    private String targetCurrency;
	    private Double conversionRate;

	    public String getComingCurrency() {
	        return comingCurrency;
	    }

	    public void setComingCurrency(String comingCurrency) {
	        this.comingCurrency = comingCurrency;
	    }

	    public String getTargetCurrency() {
	        return targetCurrency;
	    }

	    public void setTargetCurrency(String targetCurrency) {
	        this.targetCurrency = targetCurrency;
	    }

	    public Double getConversionRate() {
	        return conversionRate;
	    }

	    public void setConversionRate(Double conversionRate) {
	        this.conversionRate = conversionRate;
	    }

	    @Override
	    public String toString() {
	        return "ConversionRateDTO{" +
	                "comingCurrency='" + comingCurrency + '\'' +
	                ", targetCurrency='" + targetCurrency + '\'' +
	                ", conversionRate=" + conversionRate +
	                '}';
	    }
}
