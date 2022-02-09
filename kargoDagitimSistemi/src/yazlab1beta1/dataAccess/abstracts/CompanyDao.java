package yazlab1beta1.dataAccess.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.Company;

public interface CompanyDao {

	void add();
	void update();
	ArrayList<Company> getAll() throws SQLException;
	
}
