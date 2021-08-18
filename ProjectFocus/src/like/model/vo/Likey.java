package like.model.vo;

public class Likey {
	private int lNo;
	private int mNo;
	private int bNo;
	private String lStatus;
	
	public Likey() {}

	public Likey(int lNo, int mNo, int bNo, String lStatus) {
		super();
		this.lNo = lNo;
		this.mNo = mNo;
		this.bNo = bNo;
		this.lStatus = lStatus;
	}

	public int getlNo() {
		return lNo;
	}

	public void setlNo(int lNo) {
		this.lNo = lNo;
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

	public String getlStatus() {
		return lStatus;
	}

	public void setlStatus(String lStatus) {
		this.lStatus = lStatus;
	}

	@Override
	public String toString() {
		return "Likey [lNo=" + lNo + ", mNo=" + mNo + ", bNo=" + bNo + ", lStatus=" + lStatus + "]";
	}
	
	
	
}
