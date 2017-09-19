package coinone.tran.vo;

public class NormalWalletVO extends BaseVO {
	public Float getAvail() {
		return avail;
	}
	public void setAvail(Float avail) {
		this.avail = avail;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	Float avail = null;
	Float balance = null;
	

}
