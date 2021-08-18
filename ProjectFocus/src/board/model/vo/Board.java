package board.model.vo;

import java.sql.Date;

public class Board {
	private int bNo;
	private int bType;
	private String bTitle;
	private String bContent;
	private Date bDate;
	private Date bModify;
	private int bCount;
	private String bStatus;
	private int mNo;
	private String bETC;
	private int bLike;
	private String bLocation;
	private double xAddress;
	private double yAddress;
	private int pBtotalpay;
	private String bWriter;
	private String bYoutube;
	private String mPhone;
	private String mNick;
	
	public Board() {}

	public Board(int bNo, int bType, String bTitle, String bContent, Date bDate, Date bModify, int bCount,
			String bStatus, int mNo, String bETC, int bLike) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bModify = bModify;
		this.bCount = bCount;
		this.bStatus = bStatus;
		this.mNo = mNo;
		this.bETC = bETC;
		this.bLike = bLike;
	}

	public Board(int bNo, int bType, String bTitle, String bContent, Date bDate, Date bModify, int bCount,
			String bStatus, int mNo, String bETC, int bLike, String bLocation) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bModify = bModify;
		this.bCount = bCount;
		this.bStatus = bStatus;
		this.mNo = mNo;
		this.bETC = bETC;
		this.bLike = bLike;
		this.bLocation = bLocation;
	}
	
	public Board(int bNo, int bType, String bTitle, String bContent, Date bDate, Date bModify, int bCount,
			String bStatus, int mNo, String bETC, int bLike, String bLocation, String bWriter, String bYoutube) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bModify = bModify;
		this.bCount = bCount;
		this.bStatus = bStatus;
		this.mNo = mNo;
		this.bETC = bETC;
		this.bLike = bLike;
		this.bLocation = bLocation;
		this.bWriter = bWriter;
		this.bYoutube = bYoutube;
	}

	public Board(int bNo, int bType, String bTitle, String bContent, Date bDate, Date bModify, int bCount,
			String bStatus, int mNo, String bETC, int bLike, String bLocation, String bWriter) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bModify = bModify;
		this.bCount = bCount;
		this.bStatus = bStatus;
		this.mNo = mNo;
		this.bETC = bETC;
		this.bLike = bLike;
		this.bLocation = bLocation;
		this.bWriter = bWriter;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public int getbType() {
		return bType;
	}

	public void setbType(int bType) {
		this.bType = bType;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Date getbModify() {
		return bModify;
	}

	public void setbModify(Date bModify) {
		this.bModify = bModify;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getbETC() {
		return bETC;
	}

	public void setbETC(String bETC) {
		this.bETC = bETC;
	}

	public int getbLike() {
		return bLike;
	}

	public void setbLike(int bLike) {
		this.bLike = bLike;
	}

	public String getbLocation() {
		return bLocation;
	}

	public void setbLocation(String bLocation) {
		this.bLocation = bLocation;
	}

	public double getxAddress() {
		return xAddress;
	}

	public void setxAddress(double xAddress) {
		this.xAddress = xAddress;
	}

	public double getyAddress() {
		return yAddress;
	}

	public void setyAddress(double yAddress) {
		this.yAddress = yAddress;
	}
	
	public int getpBtotalpay() {
		return pBtotalpay;
	}

	public void setpBtotalpay(int pBtotalpay) {
		this.pBtotalpay = pBtotalpay;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	
	public String getbYoutube() {
		return bYoutube;
	}

	public void setbYoutube(String bYoutube) {
		this.bYoutube = bYoutube;
	}
	
	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", bType=" + bType + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bDate="
				+ bDate + ", bModify=" + bModify + ", bCount=" + bCount + ", bStatus=" + bStatus + ", mNo=" + mNo
				+ ", bETC=" + bETC + ", bLike=" + bLike + ", bLocation=" + bLocation + ", xAddress=" + xAddress
				+ ", yAddress=" + yAddress + ", pBtotalpay=" + pBtotalpay + ", bWriter=" + bWriter + ", bYoutube="
				+ bYoutube + ", mPhone=" + mPhone + ", mNick=" + mNick + "]";
	}
	
}

