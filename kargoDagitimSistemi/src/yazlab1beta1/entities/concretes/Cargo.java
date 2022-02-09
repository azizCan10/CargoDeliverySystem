package yazlab1beta1.entities.concretes;

import yazlab1beta1.entities.abstracts.CargoEntity;

public class Cargo implements CargoEntity {

	private int cargoId;
	private String cargoName;
	private boolean teslimat;
	
	public Cargo() {}

	public Cargo(int cargoId, String cargoName, boolean teslimat) {
		this.cargoId = cargoId;
		this.cargoName = cargoName;
		this.teslimat = teslimat;
	}

	public int getCargoId() {
		return cargoId;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public boolean isTeslimat() {
		return teslimat;
	}

	public void setTeslimat(boolean teslimat) {
		this.teslimat = teslimat;
	}
	
}
