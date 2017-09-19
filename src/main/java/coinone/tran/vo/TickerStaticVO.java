package coinone.tran.vo;

/**/
public class TickerStaticVO extends BaseVO {
	String currency = "";
	String date_type = "";
	public String getDate_type() {
		return date_type;
	}
	public void setDate_type(String date_type) {
		this.date_type = date_type;
	}
	String date = "";
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
	String status = "";
	Long max_closing_price = null;
	Long min_closing_price = null;
	Long avg_closing_price = null;
	Long std_closing_price = null;
	public Long getStd_closing_price() {
		return std_closing_price;
	}
	public void setStd_closing_price(Long std_closing_price) {
		this.std_closing_price = std_closing_price;
	}
	Integer cnt = null;
	Long std_avg_closing_price = null;

	public Long getMax_closing_price() {
		return max_closing_price;
	}
	public void setMax_closing_price(Long max_closing_price) {
		this.max_closing_price = max_closing_price;
	}
	public Long getMin_closing_price() {
		return min_closing_price;
	}
	public void setMin_closing_price(Long min_closing_price) {
		this.min_closing_price = min_closing_price;
	}
	public Long getAvg_closing_price() {
		return avg_closing_price;
	}
	public void setAvg_closing_price(Long avg_closing_price) {
		this.avg_closing_price = avg_closing_price;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Long getStd_avg_closing_price() {
		return std_avg_closing_price;
	}
	public void setStd_avg_closing_price(Long std_avg_closing_price) {
		this.std_avg_closing_price = std_avg_closing_price;
	}
}
