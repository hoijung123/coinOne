package coinone.tran.vo;

/*
 * HTTP/1.1 200 OK
{
  "volume": "0.0",
  "last": "420000",
  "timestamp": "1416895445",
  "high": "420000",
  "result": "success",
  "errorCode": "0",
  "first": "420000",
  "low": "420000",
  "currency": "btc"
}
 */
public class TickerDtlVO extends BaseVO {
	public Float getVolume() {
		return volume;
	}

	public void setVolume(Float volume) {
		this.volume = volume;
	}

	public Long getHigh() {
		return high;
	}

	public void setHigh(Long high) {
		this.high = high;
	}

	public Long getFirst() {
		return first;
	}

	public void setFirst(Long first) {
		this.first = first;
	}

	public Long getLow() {
		return low;
	}

	public void setLow(Long low) {
		this.low = low;
	}

	Float volume = null;
	Long last = null;
	Long high = null;
	Long first = null;
	Long low = null;

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

}
