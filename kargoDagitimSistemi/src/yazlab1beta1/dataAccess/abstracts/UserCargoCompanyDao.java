package yazlab1beta1.dataAccess.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.UserCargoCompany;

public interface UserCargoCompanyDao {

	void add() throws SQLException;
	void delete();
	ArrayList<UserCargoCompany> getAll() throws SQLException;
	
}
