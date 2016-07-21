package RESTCase.app.domain;

public class Product {

	private Integer id;
	private String name;
	private Pricing currentPrice;
	
	public Product() {}
	
	public Product(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.currentPrice = null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pricing getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Pricing currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    @Override
    public int hashCode() {
        return this.name.hashCode() * 31 +
        		this.id * 31 +
        		(this.currentPrice != null ? this.currentPrice.hashCode() * 31 : 0);
    }

	
	public boolean equals(Object obj) {
		if(obj instanceof Product) {
			Product product = (Product)obj;
			if(product.getId() != this.getId()) {
				return false;
			}
			if(!product.getName().equals(this.getName())) {
				return false;
			}
			if(product.getCurrentPrice() != null && this.getCurrentPrice() != null && !product.getCurrentPrice().equals(this.getCurrentPrice())) {
				return false;
			}
			return true;
		}
		return false;
	}
}
