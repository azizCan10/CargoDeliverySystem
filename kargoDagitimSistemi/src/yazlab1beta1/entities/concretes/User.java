package yazlab1beta1.entities.concretes;

import yazlab1beta1.entities.abstracts.UserEntity;

public class User implements UserEntity {

	private int userId;
	private String userName;
	private String adress;
	
	public User() {}

	public User(int userId, String userName, String adress) {
		this.userId = userId;
		this.userName = userName;
		this.adress = adress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
}
