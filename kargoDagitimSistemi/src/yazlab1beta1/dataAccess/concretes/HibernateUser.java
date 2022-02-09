package yazlab1beta1.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.dataAccess.abstracts.UserDao;
import yazlab1beta1.entities.concretes.User;
import yazlab1beta1.ui.FrmCargos;

public class HibernateUser implements UserDao {
	
	private DbHelperDao dbHelperDao;
	
	public HibernateUser(DbHelperDao dbHelperDao) {
		this.dbHelperDao = dbHelperDao;
	}

	@Override
	public void add() {

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "insert into User (UserName, Adress) values (?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, FrmCargos.txtUserName.getText());
			statement.setString(2, FrmCargos.txtAdress.getText());
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
	public ArrayList<User> getAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		
		ArrayList<User> users = null;
				
		try {
			connection = dbHelperDao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from User");
			users = new ArrayList<User>();
			
			while (resultSet.next()) {
				users.add(new User(resultSet.getInt("UserId"), resultSet.getString("UserName"), resultSet.getString("Adress")));
			}
		} 
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			statement.close();
			connection.close();
		}
		
		return users;
	}

}
