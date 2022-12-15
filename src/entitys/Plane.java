package entitys;

public class Plane {
	private String model;
	private int seatsNum;
	
	public Plane(String model, int seatsNum) {
		super();
		this.model = model;
		this.seatsNum = seatsNum;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getSeatsNum() {
		return seatsNum;
	}
	public void setSeatsNum(int seatsNum) {
		this.seatsNum = seatsNum;
	}

	@Override
	public String toString() {
		return "Plane: model=" + model + ", seatsNum=" + seatsNum;
	}
	
	
	
	

}