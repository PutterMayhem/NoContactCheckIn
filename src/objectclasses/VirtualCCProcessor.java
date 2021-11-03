package objectclasses;

import java.util.Date;

public class VirtualCCProcessor {
	private String cardNum;
	private Date expiration;
	private int csc;

	public VirtualCCProcessor(String cardNum, Date expiration, int csc) {
		super();
		this.cardNum = cardNum;
		this.expiration = expiration;
		this.csc = csc;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public int getCsc() {
		return csc;
	}

	public void setCsc(int csc) {
		this.csc = csc;
	}

	@Override
	public int hashCode() {
		int hash = 9;
		String dateString = expiration.toString();
		hash = 37 * hash + cardNum.hashCode();
		hash = 37 * hash + csc;
		hash = 37 * hash + dateString.hashCode();
		return hash;
	}
}
