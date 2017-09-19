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
public class TickerVO extends BaseVO {
	public TickerDtlVO getKrw() {
		return krw;
	}
	public void setKrw(TickerDtlVO krw) {
		this.krw = krw;
	}
	public TickerDtlVO getBtc() {
		return btc;
	}
	public void setBtc(TickerDtlVO btc) {
		this.btc = btc;
	}
	public TickerDtlVO getEth() {
		return eth;
	}
	public void setEth(TickerDtlVO eth) {
		this.eth = eth;
	}
	public TickerDtlVO getEtc() {
		return etc;
	}
	public void setEtc(TickerDtlVO etc) {
		this.etc = etc;
	}
	public TickerDtlVO getXrp() {
		return xrp;
	}
	public void setXrp(TickerDtlVO xrp) {
		this.xrp = xrp;
	}
	public TickerDtlVO getBch() {
		return bch;
	}
	public void setBch(TickerDtlVO bch) {
		this.bch = bch;
	}
	public TickerDtlVO getDash() {
		return dash;
	}
	public void setDash(TickerDtlVO dash) {
		this.dash = dash;
	}
	public TickerDtlVO getLtc() {
		return ltc;
	}
	public void setLtc(TickerDtlVO ltc) {
		this.ltc = ltc;
	}
	public TickerDtlVO getRep() {
		return rep;
	}
	public void setRep(TickerDtlVO rep) {
		this.rep = rep;
	}
	public TickerDtlVO getSteem() {
		return steem;
	}
	public void setSteem(TickerDtlVO steem) {
		this.steem = steem;
	}
	public TickerDtlVO getXmr() {
		return xmr;
	}
	public void setXmr(TickerDtlVO xmr) {
		this.xmr = xmr;
	}
	public TickerDtlVO getZec() {
		return zec;
	}
	public void setZec(TickerDtlVO zec) {
		this.zec = zec;
	}
	public TickerDtlVO getQtum() {
		return qtum;
	}
	public void setQtum(TickerDtlVO qtum) {
		this.qtum = qtum;
	}
	TickerDtlVO krw = null;
	TickerDtlVO btc = null;
	TickerDtlVO eth = null;
	TickerDtlVO etc = null;
	TickerDtlVO xrp = null;
	TickerDtlVO bch = null;
	TickerDtlVO dash = null;
	TickerDtlVO ltc = null;
	TickerDtlVO rep = null;
	TickerDtlVO steem = null;
	TickerDtlVO xmr = null;
	TickerDtlVO zec = null;
	TickerDtlVO qtum = null;

}
