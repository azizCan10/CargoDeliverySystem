package yazlab1beta1.dataAccess.concretes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import yazlab1beta1.dataAccess.abstracts.DbHelperDao;

public class HibernateDbHelper implements DbHelperDao {

	private String userName = "root";
	private String password = "password";
	private String dbUrl = "jdbc:mysql://34.122.127.139:3306/yazlab";
	
	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, userName, password);
	}

	@Override
	public void dbConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			connection = getConnection();
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		finally {
			connection.close();
		}
		
	}

}
