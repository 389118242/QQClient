package qq.entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String userId;
	private String userName;
	private String userPassword;
	private String userAvatar;
	private int userAge;
	private String userEmail;
	private int userState;

	public User() {

	}

	public User(Map<String, Object> m) {
		this.userId = m.get("user_id").toString();
		this.userName = m.get("user_name").toString();
		this.userPassword = m.get("user_password").toString();
		this.userAvatar = m.get("user_avatar").toString();
		this.userAge = Integer.parseInt(m.get("user_age").toString());
		this.userEmail = m.get("user_email").toString();
		this.userState = Integer.parseInt(m.get("user_state").toString());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

}
