package coinone.tran.vo;

public class BalanceDtlVO extends BaseVO {
	public Double getAvail() {
		return avail;
	}
	public void setAvail(Double avail) {
		this.avail = avail;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	Double avail = null;

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

	Double balance = null;
	Long last = null;

	public Long getKrwBalance() {
		return krwBalance;
	}

	public void setKrwBalance(Long krwBalance) {
		this.krwBalance = krwBalance;
	}

	Long krwBalance = null;

}
