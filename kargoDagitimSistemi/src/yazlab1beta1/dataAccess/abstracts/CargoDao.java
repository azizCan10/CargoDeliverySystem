package yazlab1beta1.dataAccess.abstracts;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.entities.concretes.Cargo;

public interface CargoDao {

	void add();
	void delete();
	ArrayList<Cargo> getAll() throws SQLException ;
	
}
