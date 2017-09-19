package coinone.tran.vo;

public class OrdersBuyVO {

	String currency_pair = "";
	Integer buy_seq = null;
	String type = "";
	Long price = null;
	Float coin_amount = null;
	Float fiat_amount = null;
	String status = "";
	String order_id = "";
	
	
	
	public Integer getBuy_seq() {
		return buy_seq;
	}
	public void setBuy_seq(Integer buy_seq) {
		this.buy_seq = buy_seq;
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
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	public Float getFiat_amount() {
		return fiat_amount;
	}
	public void setFiat_amount(Float fiat_amount) {
		this.fiat_amount = fiat_amount;
	}
	
	
}
