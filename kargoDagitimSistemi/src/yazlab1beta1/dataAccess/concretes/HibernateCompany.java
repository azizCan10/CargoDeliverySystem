package yazlab1beta1.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import yazlab1beta1.dataAccess.abstracts.CompanyDao;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.entities.concretes.Company;
import yazlab1beta1.ui.FrmChangePassword;
import yazlab1beta1.ui.FrmLogin;
import yazlab1beta1.ui.FrmRegister;

public class HibernateCompany implements CompanyDao {

	private DbHelperDao dbHelperDao;
	
	public HibernateCompany(DbHelperDao dbHelperDao) {
		this.dbHelperDao = dbHelperDao;
	}

	@Override
	public void add() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "insert into Company (CompanyName, Password) values (?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, FrmRegister.txtUserName.getText());
			statement.setString(2, FrmRegister.txtPassword.getText());
			statement.executeUpdate();
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		finally {
			try {
				statement.close();
				connection.close();
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void update() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "update Company set Password ='" + FrmChangePassword.txtPassword.getText() + "' where CompanyId = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, FrmLogin.userId);
			statement.executeUpdate();
		} 
		
		catch (SQLException e4) {
			e4.printStackTrace();
		}
		
		finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public ArrayList<Company> getAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		
		ArrayList<Company> companies = null;
				
		try {
			connection = dbHelperDao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from Company");
			companies = new ArrayList<Company>();
			
			while (resultSet.next()) {
				companies.add(new Company(resultSet.getInt("CompanyId"), resultSet.getString("CompanyName"), resultSet.getString("Password")));
			}
		} 
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			statement.close();
			connection.close();
		}
		
		return companies;
	}

}
