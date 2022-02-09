package yazlab1beta1.business.concretes;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.business.abstracts.UserCargoCompanyService;
import yazlab1beta1.dataAccess.abstracts.UserCargoCompanyDao;
import yazlab1beta1.entities.concretes.UserCargoCompany;

public class UserCargoCompanyManager implements UserCargoCompanyService {

	private UserCargoCompanyDao userCargoCompanyDao;
	
	public UserCargoCompanyManager(UserCargoCompanyDao userCargoCompanyDao) {
		this.userCargoCompanyDao = userCargoCompanyDao;
	}

	@Override
	public void add() throws SQLException {
		userCargoCompanyDao.add();
		
	}

	@Override
	public void delete() {
		userCargoCompanyDao.delete();
		
	}

	@Override
	public ArrayList<UserCargoCompany> getAll() throws SQLException {
		return userCargoCompanyDao.getAll();
	}

}
