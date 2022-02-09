package yazlab1beta1.dataAccess.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.User;

public interface UserDao {

	void add();
	ArrayList<User> getAll() throws SQLException;
}
