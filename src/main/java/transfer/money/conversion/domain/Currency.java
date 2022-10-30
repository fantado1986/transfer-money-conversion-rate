package transfer.money.conversion.domain;

public enum Currency {
	    EUR("EUR",1),
	    USD("USD",2),
	    JPY("JPY",3),
	    BGN("BGN",4),
	    CZK("CZK",5),
	    DKK("DKK",6),
	    GBP("GBP",7),
	    HUF("HUF",8),
	    PLN("PLN",9),
	    RON("RON",10),
	    SEK("SEK",11),
	    CHF("CHF",12),
	    NOK("NOK",13),
	    HRK("HRK",14),
	    RUB("RUB",15),
	    TRY("TRY",16),
	    AUD("AUD",17),
	    BRL("BRL",18),
	    CAD("CAD",19),
	    CNY("CNY",20),
	    HKD("HKD",21),
	    IDR("IDR",22),
	    ILS("ILS",23),
	    INR("INR",24),
	    KRW("KRW",25),
	    MXN("MXN",26),
	    MYR("MYR",27),
	    NZD("NZD",28),
	    PHP("PHP",29),
	    SGD("SGD",30),
	    THB("THB",31),
	    ZAR("ZAR",32);
	    
	    public final String label;
	    private final Integer sortOrder;
	    
	    private Currency(String label, int sortOrder) {
	        this.label = label;
	        this.sortOrder = sortOrder;
	    }
	    public String getLabel() {
	        return label;
	    }

	    public Integer getSortOrder() {
	        return sortOrder;
	    }
	}
