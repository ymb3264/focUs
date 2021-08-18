package goods.model.vo;

import java.util.Date;

public class Reply {
	private int rNo;
	private int bNo;
	private String content;
	private int mNo;
	private Date date;
	private String mNick;
	
	public Reply() {}

	public Reply(int bNo, String content, int mNo) {
		super();
		this.bNo = bNo;
		this.content = content;
		this.mNo = mNo;
	}
	
	public Reply(int bNo, String content, int mNo, String mNick) {
		super();
		this.bNo = bNo;
		this.content = content;
		this.mNo = mNo;
		this.mNick = mNick;
	}
	
	public Reply(int bNo, String content, int mNo, Date date) {
		super();
		this.bNo = bNo;
		this.content = content;
		this.mNo = mNo;
		this.date = date;
	}

	public Reply(int rNo, int bNo, String content, int mNo, Date date) {
		super();
		this.rNo = rNo;
		this.bNo = bNo;
		this.content = content;
		this.mNo = mNo;
		this.date = date;
	}

	public Reply(int rNo, int bNo, String content, int mNo, Date date, String mNick) {
		super();
		this.rNo = rNo;
		this.bNo = bNo;
		this.content = content;
		this.mNo = mNo;
		this.date = date;
		this.mNick = mNick;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getmNick() {
		return mNick;
	}

	public void setmNick(String mNick) {
		this.mNick = mNick;
	}

	@Override
	public String toString() {
		return "Reply [rNo=" + rNo + ", bNo=" + bNo + ", content=" + content + ", mNo=" + mNo + ", date=" + date
				+ ", mNick=" + mNick + "]";
	}

}
