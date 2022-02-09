package yazlab1beta1.business.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.business.abstracts.CompanyService;
import yazlab1beta1.dataAccess.abstracts.CompanyDao;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.entities.concretes.Company;
import yazlab1beta1.ui.FrmChangePassword;

public class CompanyManager implements CompanyService {

	private CompanyDao companyDao;
	
	public CompanyManager(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public void add() {
		companyDao.add();
	}
	

	@Override
	public void update() {
		companyDao.update();		
	}

	@Override
	public ArrayList<Company> getAll() throws SQLException {
		return companyDao.getAll();
	}

}
