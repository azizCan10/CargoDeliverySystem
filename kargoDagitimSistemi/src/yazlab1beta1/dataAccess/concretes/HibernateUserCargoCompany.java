package yazlab1beta1.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import yazlab1beta1.business.abstracts.CargoService;
import yazlab1beta1.business.concretes.CargoManager;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.dataAccess.abstracts.UserCargoCompanyDao;
import yazlab1beta1.entities.concretes.Cargo;
import yazlab1beta1.entities.concretes.UserCargoCompany;
import yazlab1beta1.ui.FrmCargos;
import yazlab1beta1.ui.FrmLogin;

public class HibernateUserCargoCompany implements UserCargoCompanyDao {

	private DbHelperDao dbHelperDao;
	private CargoService cargoService;
	
	public HibernateUserCargoCompany(DbHelperDao dbHelperDao) {
		this.dbHelperDao = dbHelperDao;
	}

	@Override
	public void add() throws SQLException {

		int cargoId = 0;
		cargoService = new CargoManager(new HibernateCargo(dbHelperDao));
		
		ArrayList<Cargo> cargos = cargoService.getAll();
		
		for (Cargo cargo: cargos) {
			cargoId = cargo.getCargoId();
		}

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "insert into UserCargoCompany (UserId, CargoId, CompanyId) values (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, FrmCargos.userId);
			statement.setInt(2, cargoId);
			statement.setInt(3, FrmLogin.userId);
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
	public void delete() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "delete from UserCargoCompany where UserCargoCompany.CargoId = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, Integer.valueOf(FrmCargos.txtCargoId.getText()));
			statement.executeUpdate();
		} 
		
		catch (SQLException e3) {
			System.out.println(e3);
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
	public ArrayList<UserCargoCompany> getAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		
		ArrayList<UserCargoCompany> userCargoCompanies = null;
				
		try {
			connection = dbHelperDao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from UserCargoCompany where CompanyId = " + FrmLogin.userId + "");
			userCargoCompanies = new ArrayList<UserCargoCompany>();
			
			while (resultSet.next()) {
				userCargoCompanies.add(new UserCargoCompany(resultSet.getInt("Id"), resultSet.getInt("UserId"), resultSet.getInt("CargoId"), resultSet.getInt("CompanyId")));
			}
		} 
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			statement.close();
			connection.close();
		}
		
		return userCargoCompanies;
	}

}
