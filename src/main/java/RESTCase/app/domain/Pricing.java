package RESTCase.app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

//Specify the collection for the mongoTemplate
@Document(collection = "productPriceCollection")
public class Pricing {

	private double amount;
	private CurrencyCode currencyCode;

	public Pricing() {}
	
	public Pricing(double amount, CurrencyCode currencyCode) {
		this.amount = amount;
		this.currencyCode = currencyCode;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public CurrencyCode getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyType(CurrencyCode currencyCode) {
		this.currencyCode = currencyCode;
	}

	//public static Pricing fromDocument(Document doc) {
	//	return new Pricing(doc.getDouble("amount"), CurrencyCode.valueOf(doc.getString("currencyCode")));
	//}
	
	@Override
	public int hashCode() {
		return (int)Double.doubleToLongBits(this.amount) * 31 +
				currencyCode.hashCode() * 31;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Pricing) {
			Pricing pricing = (Pricing)obj;
			if(!pricing.getCurrencyCode().equals(this.getCurrencyCode())) {
				return false;
			}
			if(pricing.getAmount() != this.getAmount()) {
				return false;
			}
			return true;
		}
		return false;
	}
}
