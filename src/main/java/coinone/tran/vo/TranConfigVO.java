package coinone.tran.vo;

public class TranConfigVO {

	String currency = "";
	String date = "";
	String status = "";
	String tran_yn = "";
	Float target_price = null;
	Float units = null;
	public void setTarget_price(Float target_price) {
		this.target_price = target_price;
	}
	public void setUnits(Float units) {
		this.units = units;
	}
	String tran_type = "";
	
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
	public Float getTarget_price() {
		return target_price;
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
