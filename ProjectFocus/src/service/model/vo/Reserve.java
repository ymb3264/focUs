package service.model.vo;

public class Reserve {
	
	private int rUserNo;
	private int rBNo;
	private String rTitle;
	private String rWriter;
	private String rLocation;
	private String rTime;
	//추가
	private int rNo;
	private String rName;
	private String rPhone;
	private String rEmail;
	private String rStatus;
	
	
	public Reserve() {}


	public Reserve(int rUserNo, int rBNo, String rTitle, String rWriter, String rLocation, String rTime, int rNo,
			String rName, String rPhone, String rEmail, String rStatus) {
		super();
		this.rUserNo = rUserNo;
		this.rBNo = rBNo;
		this.rTitle = rTitle;
		this.rWriter = rWriter;
		this.rLocation = rLocation;
		this.rTime = rTime;
		this.rNo = rNo;
		this.rName = rName;
		this.rPhone = rPhone;
		this.rEmail = rEmail;
		this.rStatus = rStatus;
	}


	public int getrUserNo() {
		return rUserNo;
	}


	public void setrUserNo(int rUserNo) {
		this.rUserNo = rUserNo;
	}


	public int getrBNo() {
		return rBNo;
	}


	public void setrBNo(int rBNo) {
		this.rBNo = rBNo;
	}


	public String getrTitle() {
		return rTitle;
	}


	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}


	public String getrWriter() {
		return rWriter;
	}


	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
	}


	public String getrLocation() {
		return rLocation;
	}


	public void setrLocation(String rLocation) {
		this.rLocation = rLocation;
	}


	public String getrTime() {
		return rTime;
	}


	public void setrTime(String rTime) {
		this.rTime = rTime;
	}


	public int getrNo() {
		return rNo;
	}


	public void setrNo(int rNo) {
		this.rNo = rNo;
	}


	public String getrName() {
		return rName;
	}


	public void setrName(String rName) {
		this.rName = rName;
	}


	public String getrPhone() {
		return rPhone;
	}


	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}


	public String getrEmail() {
		return rEmail;
	}


	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}


	public String getrStatus() {
		return rStatus;
	}


	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}


	@Override
	public String toString() {
		return "Reserve [rUserNo=" + rUserNo + ", rBNo=" + rBNo + ", rTitle=" + rTitle + ", rWriter=" + rWriter
				+ ", rLocation=" + rLocation + ", rTime=" + rTime + ", rNo=" + rNo + ", rName=" + rName + ", rPhone="
				+ rPhone + ", rEmail=" + rEmail + ", rStatus=" + rStatus + "]";
	}
	
}	


