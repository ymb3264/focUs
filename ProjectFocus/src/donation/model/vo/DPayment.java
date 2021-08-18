package donation.model.vo;

import java.sql.Date;

public class DPayment {
	private int pNo;
	private int mNo;
	private int bNo;
	private Date pDate;
	private String pTitle;
	private String pPayKakao;
	private String mNick;
	private int pMtotalpay;
	private String dCatename;
	private String bWriter;
	
	public DPayment() {}

	public DPayment(int pNo, int mNo, int bNo, Date pDate, String pTitle, String pPayKakao, String mNick,
			int pMtotalpay, String dCatename, String bWriter) {
		super();
		this.pNo = pNo;
		this.mNo = mNo;
		this.bNo = bNo;
		this.pDate = pDate;
		this.pTitle = pTitle;
		this.pPayKakao = pPayKakao;
		this.mNick = mNick;
		this.pMtotalpay = pMtotalpay;
		this.dCatename = dCatename;
		this.bWriter = bWriter;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpPayKakao() {
		return pPayKakao;
	}

	public void setpPayKakao(String pPayKakao) {
		this.pPayKakao = pPayKakao;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	public int getpMtotalpay() {
		return pMtotalpay;
	}

	public void setpMtotalpay(int pMtotalpay) {
		this.pMtotalpay = pMtotalpay;
	}

	public String getdCatename() {
		return dCatename;
	}

	public void setdCatename(String dCatename) {
		this.dCatename = dCatename;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	@Override
	public String toString() {
		return "DPayment [pNo=" + pNo + ", mNo=" + mNo + ", bNo=" + bNo + ", pDate=" + pDate + ", pTitle=" + pTitle
				+ ", pPayKakao=" + pPayKakao + ", mNick=" + mNick + ", pMtotalpay=" + pMtotalpay + ", dCatename="
				+ dCatename + ", bWriter=" + bWriter + "]";
	}

	
	
}
