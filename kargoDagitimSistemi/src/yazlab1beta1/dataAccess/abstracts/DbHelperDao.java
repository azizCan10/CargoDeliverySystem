package yazlab1beta1.dataAccess.abstracts;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbHelperDao {

	Connection getConnection() throws SQLException;
	void dbConnection() throws SQLException;
}
