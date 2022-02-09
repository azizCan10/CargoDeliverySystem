package yazlab1beta1.business.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.User;

public interface UserService {

	void add();
	ArrayList<User> getAll() throws SQLException;
	
}
