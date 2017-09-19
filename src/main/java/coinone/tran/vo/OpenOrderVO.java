package coinone.tran.vo;

public class OpenOrderVO extends BaseVO{
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PriceVO getPrice() {
		return price;
	}
	public void setPrice(PriceVO price) {
		this.price = price;
	}
	String type = "";
	PriceVO price = null;
	PriceVO total = null;
	PriceVO open = null;
	PriceVO native_total = null;
	
	public PriceVO getTotal() {
		return total;
	}
	public void setTotal(PriceVO total) {
		this.total = total;
	}
	public PriceVO getOpen() {
		return open;
	}
	public void setOpen(PriceVO open) {
		this.open = open;
	}
	public PriceVO getNative_total() {
		return native_total;
	}
	public void setNative_total(PriceVO native_total) {
		this.native_total = native_total;
	}
	String id = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
