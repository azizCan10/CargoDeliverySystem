package yazlab1beta1.entities.concretes;

import yazlab1beta1.entities.abstracts.CompanyEntity;

public class Company implements CompanyEntity {

	private int companyId;
	private String companyName;
	private String password;
	
	public Company() {}

	public Company(int companyId, String companyName, String password) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.password = password;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
