package yazlab1beta1.business.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.Cargo;

public interface CargoService {

	void add();
	void delete();
	ArrayList<Cargo> getAll() throws SQLException;
	
}
