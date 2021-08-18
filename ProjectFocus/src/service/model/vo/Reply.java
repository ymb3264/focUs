package service.model.vo;

import java.sql.Date;

public class Reply {
	private int rNo;
	private int bNo;
	private int mNo;
	private Date rDate;
	private Date rModify;
	private String rContent;
	private String rStatus;
	private String mNick;
	
	public Reply() {}

	public Reply(int rNo, int bNo, int mNo, Date rDate, Date rModify, String rContent, String rStatus, String mNick) {
		super();
		this.rNo = rNo;
		this.bNo = bNo;
		this.mNo = mNo;
		this.rDate = rDate;
		this.rModify = rModify;
		this.rContent = rContent;
		this.rStatus = rStatus;
		this.mNick = mNick;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public Date getrModify() {
		return rModify;
	}

	public void setrModify(Date rModify) {
		this.rModify = rModify;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	@Override
	public String toString() {
		return "Reply [rNo=" + rNo + ", bNo=" + bNo + ", mNo=" + mNo + ", rDate=" + rDate + ", rModify=" + rModify
				+ ", rContent=" + rContent + ", rStatus=" + rStatus + ", mNick=" + mNick + "]";
	}

	
}
