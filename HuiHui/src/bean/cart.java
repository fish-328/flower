package bean;

public class cart {
	String product;
	int quantity;
	public cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public cart(String product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(long l) {
		this.quantity = (int) l;
	}
	
}
