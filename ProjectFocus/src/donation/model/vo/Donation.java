package donation.model.vo;

import java.sql.Date;

public class Donation {
	private int bNo;
	private int bType;
	private int dCategory;
	private String dCatename;
	private int dPay;
	private Date dPeriod;
	private String dEtc1;
	private String dEtc2;
	private String mNick;
	private int percent;
	
	
	public Donation() {}


	public Donation(int bNo, int bType, int dCategory, String dCatename, int dPay, Date dPeriod, String dEtc1,
			String dEtc2, String mNick) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.dCategory = dCategory;
		this.dCatename = dCatename;
		this.dPay = dPay;
		this.dPeriod = dPeriod;
		this.dEtc1 = dEtc1;
		this.dEtc2 = dEtc2;
		this.mNick = mNick;
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


	public int getdCategory() {
		return dCategory;
	}


	public void setdCategory(int dCategory) {
		this.dCategory = dCategory;
	}


	public String getdCatename() {
		return dCatename;
	}


	public void setdCatename(String dCatename) {
		this.dCatename = dCatename;
	}


	public int getdPay() {
		return dPay;
	}


	public void setdPay(int dPay) {
		this.dPay = dPay;
	}


	public Date getdPeriod() {
		return dPeriod;
	}


	public void setdPeriod(Date dPeriod) {
		this.dPeriod = dPeriod;
	}


	public String getdEtc1() {
		return dEtc1;
	}


	public void setdEtc1(String dEtc1) {
		this.dEtc1 = dEtc1;
	}


	public String getdEtc2() {
		return dEtc2;
	}


	public void setdEtc2(String dEtc2) {
		this.dEtc2 = dEtc2;
	}


	public String getmNick() {
		return mNick;
	}


	public void setmNick(String mNick) {
		this.mNick = mNick;
	}
	
	


	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}


	@Override
	public String toString() {
		return "Donation [bNo=" + bNo + ", bType=" + bType + ", dCategory=" + dCategory + ", dCatename=" + dCatename
				+ ", dPay=" + dPay + ", dPeriod=" + dPeriod + ", dEtc1=" + dEtc1 + ", dEtc2=" + dEtc2 + ", mNick="
				+ mNick + "]";
	}

	
	
}
