package coinone.tran.vo;

public class TranLogVO {
	String order_id = "";
	String cont_id = "";
	String currency = "";
	String date = "";
	String status = "";
	String tran_yn = "";
	Float price = null;
	Float total = null;
	Float fee = null;
	Float units = null;
	String tran_type = "";
	String log_date = "";
	
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCont_id() {
		return cont_id;
	}
	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Float getFee() {
		return fee;
	}
	public void setFee(Float fee) {
		this.fee = fee;
	}
	
	public void setUnits(Float units) {
		this.units = units;
	}
	
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}	
	
	public String getTran_yn() {
		return tran_yn;
	}
	public void setTran_yn(String tran_yn) {
		this.tran_yn = tran_yn;
	}
	public Float getUnits() {
		return units;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
