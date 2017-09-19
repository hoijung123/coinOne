package coinone.tran.vo;

public class OrdersSellVO {

	String currency_pair = "";
	String type = "";
	Long price = null;
	Float coin_amount = null;
	String status = "";
	String order_id = "";
	Integer sell_seq = null;
	
	
	
	public Integer getSell_seq() {
		return sell_seq;
	}
	public void setSell_seq(Integer sell_seq) {
		this.sell_seq = sell_seq;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String orderId) {
		this.order_id = orderId;
	}
	public String getCurrency_pair() {
		return currency_pair;
	}
	public void setCurrency_pair(String currency_pair) {
		this.currency_pair = currency_pair;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Float getCoin_amount() {
		return coin_amount;
	}
	public void setCoin_amount(Float coin_amount) {
		this.coin_amount = coin_amount;
	}	
	
}
