package coinone.tran.vo;

public class OrderVO extends BaseVO {


	Integer index = null;
	String type = "";

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	Integer seq = null;
	Long price = null;
	Double qty = null;
	String buyDate = "";
	String orderId = "";
	String result = "";

	public Integer getReqCnt() {
		return reqCnt;
	}

	public void setReqCnt(Integer reqCnt) {
		this.reqCnt = reqCnt;
	}

	String errorCode = "";
	Integer reqCnt = null;

	public Float getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Float feeRate) {
		this.feeRate = feeRate;
	}

	Float feeRate = null;


	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}
	
}
