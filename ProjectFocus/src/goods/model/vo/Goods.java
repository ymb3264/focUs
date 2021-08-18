package goods.model.vo;

public class Goods {
	private int bNo;
	private int bType;
	private int gNo;
	private int gPrice;
	private int gAmount;
	private String gCompany;
	
	public Goods() {}

	public Goods(int bNo, int bType, int gNo, int gPrice, int gAmount, String gCompany) {
		super();
		this.bNo = bNo;
		this.bType = bType;
		this.gNo = gNo;
		this.gPrice = gPrice;
		this.gAmount = gAmount;
		this.gCompany = gCompany;
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

	public int getgNo() {
		return gNo;
	}

	public void setgNo(int gNo) {
		this.gNo = gNo;
	}

	public int getgPrice() {
		return gPrice;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public int getgAmount() {
		return gAmount;
	}

	public void setgAmount(int gAmount) {
		this.gAmount = gAmount;
	}

	public String getgCompany() {
		return gCompany;
	}

	public void setgCompany(String gCompany) {
		this.gCompany = gCompany;
	}

	@Override
	public String toString() {
		return "Goods [bNo=" + bNo + ", bType=" + bType + ", gNo=" + gNo + ", gPrice=" + gPrice + ", gAmount=" + gAmount
				+ ", gCompany=" + gCompany + "]";
	}
	
}
