package goods.model.vo;

import java.util.Date;

public class GoodsBag {
	private String title;
	private String thumbnailImg;
	private int amount;
	private int price;
	private long buyNum;
	private Date date;
	
	public GoodsBag() {}
	
	public GoodsBag(String title, String thumbnailImg, int amount, int price, long buyNum) {
		super();
		this.title = title;
		this.thumbnailImg = thumbnailImg;
		this.amount = amount;
		this.price = price;
		this.buyNum = buyNum;
	}

	public GoodsBag(String title, String thumbnailImg, int amount, int price, long buyNum, Date date) {
		super();
		this.title = title;
		this.thumbnailImg = thumbnailImg;
		this.amount = amount;
		this.price = price;
		this.buyNum = buyNum;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(long buyNum) {
		this.buyNum = buyNum;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GoodsBag [title=" + title + ", thumbnailImg=" + thumbnailImg + ", amount=" + amount + ", price=" + price
				+ ", buyNum=" + buyNum + ", date=" + date + "]";
	}

}

