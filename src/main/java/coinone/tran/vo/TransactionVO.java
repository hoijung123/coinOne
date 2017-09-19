package coinone.tran.vo;

public class TransactionVO extends BaseVO{
	String completedAt = "";
	String type = "";
	PriceVO fee = null;
	FillsDetailVO fillsDetail = null;
	
	public FillsDetailVO getFillsDetail() {
		return fillsDetail;
	}
	public void setFillsDetail(FillsDetailVO fillsDetail) {
		this.fillsDetail = fillsDetail;
	}
	public String getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PriceVO getFee() {
		return fee;
	}
	public void setFee(PriceVO fee) {
		this.fee = fee;
	}
	String id = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
