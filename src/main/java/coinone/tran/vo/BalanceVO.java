package coinone.tran.vo;

import java.util.List;

public class BalanceVO extends BaseVO {
	BalanceDtlVO krw = null;
	BalanceDtlVO btc = null;
	BalanceDtlVO eth = null;
	BalanceDtlVO etc = null;
	BalanceDtlVO xrp = null;
	BalanceDtlVO bch = null;
	BalanceDtlVO dash = null;
	BalanceDtlVO ltc = null;
	BalanceDtlVO rep = null;
	BalanceDtlVO steem = null;
	BalanceDtlVO xmr = null;
	BalanceDtlVO zec = null;
	BalanceDtlVO qtum = null;
	List<NormalWalletVO> normalWallets = null;
	
	public List<NormalWalletVO> getNormalWallets() {
		return normalWallets;
	}
	public void setNormalWallets(List<NormalWalletVO> normalWallets) {
		this.normalWallets = normalWallets;
	}
	public BalanceDtlVO getZec() {
		return zec;
	}
	public void setZec(BalanceDtlVO zec) {
		this.zec = zec;
	}
	public BalanceDtlVO getXmr() {
		return xmr;
	}
	public void setXmr(BalanceDtlVO xmr) {
		this.xmr = xmr;
	}
	public BalanceDtlVO getSteem() {
		return steem;
	}
	public void setSteem(BalanceDtlVO steem) {
		this.steem = steem;
	}
	public BalanceDtlVO getRep() {
		return rep;
	}
	public void setRep(BalanceDtlVO rep) {
		this.rep = rep;
	}
	public BalanceDtlVO getLtc() {
		return ltc;
	}
	public void setLtc(BalanceDtlVO ltc) {
		this.ltc = ltc;
	}
	public BalanceDtlVO getDash() {
		return dash;
	}
	public void setDash(BalanceDtlVO dash) {
		this.dash = dash;
	}
	public BalanceDtlVO getBch() {
		return bch;
	}
	public void setBch(BalanceDtlVO bch) {
		this.bch = bch;
	}
	public BalanceDtlVO getKrw() {
		return krw;
	}
	public void setKrw(BalanceDtlVO krw) {
		this.krw = krw;
	}
	public BalanceDtlVO getBtc() {
		return btc;
	}
	public void setBtc(BalanceDtlVO btc) {
		this.btc = btc;
	}
	public BalanceDtlVO getEth() {
		return eth;
	}
	public void setEth(BalanceDtlVO eth) {
		this.eth = eth;
	}
	public BalanceDtlVO getEtc() {
		return etc;
	}
	public void setEtc(BalanceDtlVO etc) {
		this.etc = etc;
	}
	public BalanceDtlVO getXrp() {
		return xrp;
	}
	public void setXrp(BalanceDtlVO xrp) {
		this.xrp = xrp;
	}
	public BalanceDtlVO getQtum() {
		return qtum;
	}
	public void setQtum(BalanceDtlVO qtum) {
		this.qtum = qtum;
	}
	
}
