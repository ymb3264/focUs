package service.model.vo;

import java.util.ArrayList;

public class Service {
	private int bNo;
	private int bType;
	private int sCategory;
	private String sLocation;
	private String sTime;
	private String mNick;
	private String sCatename;
	private int sNo;
	private String bTitle;
	
	public Service() {}

	public Service(int bNo, int bType, int sCategory, String sLocation, String sTime) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.sCategory = sCategory;
		this.sLocation = sLocation;
		this.sTime = sTime;
	}

	public Service(int bNo, int bType, int sCategory, String sLocation, String sTime, String mNick) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.sCategory = sCategory;
		this.sLocation = sLocation;
		this.sTime = sTime;
		this.mNick = mNick;
	}

	public Service(int bNo, int bType, int sCategory, String sLocation, String sTime, String mNick, String sCatename) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.sCategory = sCategory;
		this.sLocation = sLocation;
		this.sTime = sTime;
		this.mNick = mNick;
		this.sCatename = sCatename;
	}

	
	
	public Service(int bNo, int bType, int sCategory, String sLocation, String sTime, String mNick, String sCatename,
			int sNo) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.sCategory = sCategory;
		this.sLocation = sLocation;
		this.sTime = sTime;
		this.mNick = mNick;
		this.sCatename = sCatename;
		this.sNo = sNo;
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

	public int getsCategory() {
		return sCategory;
	}

	public void setsCategory(int sCategory) {
		this.sCategory = sCategory;
	}

	public String getsLocation() {
		return sLocation;
	}

	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	public String getsCatename() {
		return sCatename;
	}

	public void setsCatename(String sCatename) {
		this.sCatename = sCatename;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	@Override
	public String toString() {
		return "Service [bNo=" + bNo + ", bType=" + bType + ", sCategory=" + sCategory + ", sLocation=" + sLocation
				+ ", sTime=" + sTime + ", mNick=" + mNick + ", sCatename=" + sCatename + ", sNo=" + sNo + ", bTitle="
				+ bTitle + "]";
	}

	

	
}

	