package yazlab1beta1.business.concretes;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.business.abstracts.UserService;
import yazlab1beta1.dataAccess.abstracts.UserDao;
import yazlab1beta1.entities.concretes.User;

public class UserManager implements UserService {

	private UserDao userDao;
	
	
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add() {
		userDao.add();
	}

	@Override
	public ArrayList<User> getAll() throws SQLException {
		return userDao.getAll();
	}

}
