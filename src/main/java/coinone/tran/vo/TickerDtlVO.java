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
	Long yesterday_last = null;
	Long yesterday_low = null;
	Long yesterday_first = null;
	Float yesterday_volume = null;
	Long yesterday_high = null;
	
	public Long getYesterday_low() {
		return yesterday_low;
	}

	public void setYesterday_low(Long yesterday_low) {
		this.yesterday_low = yesterday_low;
	}

	public Long getYesterday_first() {
		return yesterday_first;
	}

	public void setYesterday_first(Long yesterday_first) {
		this.yesterday_first = yesterday_first;
	}

	public Float getYesterday_volume() {
		return yesterday_volume;
	}

	public void setYesterday_volume(Float yesterday_volume) {
		this.yesterday_volume = yesterday_volume;
	}

	public Long getYesterday_high() {
		return yesterday_high;
	}

	public void setYesterday_high(Long yesterday_high) {
		this.yesterday_high = yesterday_high;
	}

	public Long getYesterday_last() {
		return yesterday_last;
	}

	public void setYesterday_last(Long yesterday_last) {
		this.yesterday_last = yesterday_last;
	}

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

}
