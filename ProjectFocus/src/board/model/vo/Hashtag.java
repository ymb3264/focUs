package board.model.vo;

public class Hashtag {
	private int tNo;
	private String tName;
	private int bNo;
	
	public Hashtag() {}

	public Hashtag(int tNo, String tName, int bNo) {
		super();
		this.tNo = tNo;
		this.tName = tName;
		this.bNo = bNo;
	}

	public int gettNo() {
		return tNo;
	}

	public void settNo(int tNo) {
		this.tNo = tNo;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	@Override
	public String toString() {
		return "Hashtag [tNo=" + tNo + ", tName=" + tName + ", bNo=" + bNo + "]";
	}
	
	
}
