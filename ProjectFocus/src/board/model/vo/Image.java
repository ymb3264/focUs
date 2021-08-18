package board.model.vo;

import java.sql.Date;

public class Image {
	private int iNo;
	private String iOrigin;
	private String iChange;
	private String iPath;
	private Date iDate;
	private int iLevel;
	private String iStatus;
	private int bNo;
	private int rNo;
	
	public Image() {}

	public Image(int iNo, String iOrigin, String iChange, String iPath, Date iDate, String iStatus, int bNo) {
		super();
		this.iNo = iNo;
		this.iOrigin = iOrigin;
		this.iChange = iChange;
		this.iPath = iPath;
		this.iDate = iDate;
		this.iStatus = iStatus;
		this.bNo = bNo;
	}

	public Image(int iNo, String iOrigin, String iChange, String iPath, Date iDate, int iLevel,
			String iStatus, int bNo) {
		super();
		this.iNo = iNo;
		this.iOrigin = iOrigin;
		this.iChange = iChange;
		this.iPath = iPath;
		this.iDate = iDate;
		this.iLevel = iLevel;
		this.iStatus = iStatus;
		this.bNo = bNo;
	}

	public Image(int iNo, String iOrigin, String iChange, String iPath, Date iDate, String iStatus, int bNo,
			int rNo) {
		super();
		this.iNo = iNo;
		this.iOrigin = iOrigin;
		this.iChange = iChange;
		this.iPath = iPath;
		this.iDate = iDate;
		this.iStatus = iStatus;
		this.bNo = bNo;
		this.rNo = rNo;
	}

	public int getiNo() {
		return iNo;
	}

	public void setiNo(int iNo) {
		this.iNo = iNo;
	}

	public String getiOrigin() {
		return iOrigin;
	}

	public void setiOrigin(String iOrigin) {
		this.iOrigin = iOrigin;
	}

	public String getiChange() {
		return iChange;
	}

	public void setiChange(String iChange) {
		this.iChange = iChange;
	}

	public String getiPath() {
		return iPath;
	}

	public void setiPath(String iPath) {
		this.iPath = iPath;
	}

	public Date getiDate() {
		return iDate;
	}

	public void setiDate(Date iDate) {
		this.iDate = iDate;
	}

	public int getiLevel() {
		return iLevel;
	}

	public void setiLevel(int iLevel) {
		this.iLevel = iLevel;
	}

	public String getiStatus() {
		return iStatus;
	}

	public void setiStatus(String iStatus) {
		this.iStatus = iStatus;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public int getRNo() {
		return rNo;
	}

	public void setRNo(int rNo) {
		this.rNo = rNo;
	}

	@Override
	public String toString() {
		return "Image [iNo=" + iNo + ", iOrigin=" + iOrigin + ", iChange=" + iChange + ", iPath=" + iPath + ", iDate="
				+ iDate + ", iLevel=" + iLevel + ", iStatus=" + iStatus + ", bNo=" + bNo + ", riNo=" + rNo + "]";
	}

}
