package qq.entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class News implements Serializable {
	private String senderId;
	private String receiverId;
	private String sendDate;
	private String newsType;
	private String newsContent;
	private int newsState;

	public News() {

	}

	public News(Map<String, Object> m) {
		this.senderId = m.get("sender_id").toString();
		this.receiverId = m.get("receiver_id").toString();
		this.sendDate = m.get("send_date").toString();
		this.newsType = m.get("news_type").toString();
		this.newsContent = m.get("news_content").toString();
		this.newsState = Integer.parseInt(m.get("news_state").toString());
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public int getNewsState() {
		return newsState;
	}

	public void setNewsState(int newsState) {
		this.newsState = newsState;
	}

}
