package coinone.tran.vo;

public class FillsDetailVO {
	PriceVO price = null;
	PriceVO amount = null;
	PriceVO native_amount = null;
	String orderId = "";
	public PriceVO getPrice() {
		return price;
	}
	public void setPrice(PriceVO price) {
		this.price = price;
	}
	public PriceVO getAmount() {
		return amount;
	}
	public void setAmount(PriceVO amount) {
		this.amount = amount;
	}
	public PriceVO getNative_amount() {
		return native_amount;
	}
	public void setNative_amount(PriceVO native_amount) {
		this.native_amount = native_amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
