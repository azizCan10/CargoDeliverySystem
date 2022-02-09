package yazlab1beta1.business.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.Company;

public interface CompanyService {

	void add();
	void update();
	ArrayList<Company> getAll() throws SQLException;
	
}
