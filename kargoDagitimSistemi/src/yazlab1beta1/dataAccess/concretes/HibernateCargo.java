package yazlab1beta1.dataAccess.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import yazlab1beta1.dataAccess.abstracts.CargoDao;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.entities.concretes.Cargo;
import yazlab1beta1.ui.FrmCargos;

public class HibernateCargo implements CargoDao {

	private DbHelperDao dbHelperDao;
	
	public HibernateCargo(DbHelperDao dbHelperDao) {
		this.dbHelperDao = dbHelperDao;
	}
	
	@Override
	public void add() {

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dbHelperDao.getConnection();
			String sql = "insert into Cargo (CargoName, Teslimat) values (?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, FrmCargos.txtCargoName.getText());
			statement.setBoolean(2, false);
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
			String sql = "delete from Cargo where CargoId = ?";
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
	public ArrayList<Cargo> getAll() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		
		ArrayList<Cargo> cargos = null;
				
		try {
			connection = dbHelperDao.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from Cargo");
			cargos = new ArrayList<Cargo>();
			
			while (resultSet.next()) {
				cargos.add(new Cargo(resultSet.getInt("CargoId"), resultSet.getString("CargoName"), resultSet.getBoolean("Teslimat")));
			}
		} 
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			statement.close();
			connection.close();
		}
		
		return cargos;
	}

}
