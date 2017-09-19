package coinone.tran.vo;

import coinone.tran.util.Constants;
import coinone.tran.util.Utils;

public class BaseVO {
	String timestamp = "";
	String dateTime = "";
	String result = "";
	String errorCode = "";
	String currency = "";
	String errorMessage = "";
	
	public String getErrorMessage() {
		String ret = "";
		if ("103".equals(this.errorCode))
		{
			ret = Constants.ERR_103_MESSAGE;
		}
		return ret;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDateTime() {
		return Utils.timeStamp2Date(this.timestamp);
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
