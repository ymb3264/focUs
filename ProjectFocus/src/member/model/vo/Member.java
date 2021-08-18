package member.model.vo;

public class Member {
	private int mNo;
	private String mId;
	private String mPw;
	private String mName;
	private String mNick;
	private String mPhone;
	private String mEmail;
	private String mAddress;
	private String mStatus;
	private String mAdmin;
	private String mPost;
	private String mAddress2;
	private double xAddress;
	private double yAddress;
	private int pMtotalpay;
	
	public Member() {}

	public Member(String mId, String mPw) {
		super();
		this.mId = mId;
		this.mPw = mPw;
	}

	public Member(String mId, String mPw, String mName, String mNick, String mPhone, String mEmail, String mAddress,
			String mPost, String mAddress2) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mNick = mNick;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		this.mPost = mPost;
		this.mAddress2 = mAddress2;
	}

	public Member(int mNo, String mId, String mPw, String mName, String mNick, String mPhone, String mEmail,
			String mAddress, String mStatus, String mAdmin, String mPost, String mAddress2) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mNick = mNick;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		this.mStatus = mStatus;
		this.mAdmin = mAdmin;
		this.mPost = mPost;
		this.mAddress2 = mAddress2;
	}

	public Member(int mNo, String mId, String mPw, String mName, String mNick, String mPhone, String mEmail,
			String mAddress, String mStatus, String mAdmin, String mPost, String mAddress2, double xAddress,
			double yAddress) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mNick = mNick;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		this.mStatus = mStatus;
		this.mAdmin = mAdmin;
		this.mPost = mPost;
		this.mAddress2 = mAddress2;
		this.xAddress = xAddress;
		this.yAddress = yAddress;
	}

	
	public Member(int mNo, String mId, String mPw, String mName, String mNick, String mPhone, String mEmail,
			String mAddress, String mStatus, String mAdmin, String mPost, String mAddress2, int pMtotalpay) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mNick = mNick;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		this.mStatus = mStatus;
		this.mAdmin = mAdmin;
		this.mPost = mPost;
		this.mAddress2 = mAddress2;
		this.pMtotalpay = pMtotalpay;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public String getmAdmin() {
		return mAdmin;
	}

	public void setmAdmin(String mAdmin) {
		this.mAdmin = mAdmin;
	}

	public String getmPost() {
		return mPost;
	}

	public void setmPost(String mPost) {
		this.mPost = mPost;
	}

	public String getmAddress2() {
		return mAddress2;
	}

	public void setmAddress2(String mAddress2) {
		this.mAddress2 = mAddress2;
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
	
	public int getpMtotalpay() {
		return pMtotalpay;
	}

	public void setpMtotalpay(int pMtotalpay) {
		this.pMtotalpay = pMtotalpay;
	}

	@Override
	public String toString() {
		return "Member [mNo=" + mNo + ", mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mNick=" + mNick
				+ ", mPhone=" + mPhone + ", mEmail=" + mEmail + ", mAddress=" + mAddress + ", mStatus=" + mStatus
				+ ", mAdmin=" + mAdmin + ", mPost=" + mPost + ", mAddress2=" + mAddress2 + ", xAddress=" + xAddress
				+ ", yAddress=" + yAddress + ", pMtotalpay=" + pMtotalpay + "]";
	}

}
