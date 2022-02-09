package yazlab1beta1.business.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.UserCargoCompany;

public interface UserCargoCompanyService {

	void add() throws SQLException;
	void delete();
	ArrayList<UserCargoCompany> getAll() throws SQLException;
	
}
