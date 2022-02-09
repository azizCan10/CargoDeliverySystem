package yazlab1beta1.business.concretes;

import java.sql.SQLException;
import java.util.ArrayList;

import yazlab1beta1.business.abstracts.CargoService;
import yazlab1beta1.dataAccess.abstracts.CargoDao;
import yazlab1beta1.entities.concretes.Cargo;

public class CargoManager implements CargoService {

	private CargoDao cargoDao;
	
	public CargoManager(CargoDao cargoDao) {
		this.cargoDao = cargoDao;
	}

	@Override
	public void add() {
		cargoDao.add();
	}

	@Override
	public void delete() {
		cargoDao.delete();
		
	}

	@Override
	public ArrayList<Cargo> getAll() throws SQLException {
		return cargoDao.getAll();
	}

}
