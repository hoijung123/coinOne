package coinone.tran.vo;

public class OrderLogVO extends BaseVO {


	Integer index = null;
	String type = "";

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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

	public Integer getBase() {
		return base;
	}

	public void setBase(Integer base) {
		this.base = base;
	}

	Integer base = null;
	Long price = null;
	Double qty = null;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	String orderDate = "";
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
