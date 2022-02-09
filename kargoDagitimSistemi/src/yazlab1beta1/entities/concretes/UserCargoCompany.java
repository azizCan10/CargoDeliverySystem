package yazlab1beta1.entities.concretes;

import yazlab1beta1.entities.abstracts.UserCargoCompanyEntity;

public class UserCargoCompany implements UserCargoCompanyEntity {

	private int id;
	private int userId;
	private int cargoId;
	private int companyId;
	
	public UserCargoCompany() {}

	public UserCargoCompany(int id, int userId, int cargoId, int companyId) {
		this.id = id;
		this.userId = userId;
		this.cargoId = cargoId;
		this.companyId = companyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCargoId() {
		return cargoId;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
