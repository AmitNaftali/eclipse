package entitys;

public class Traveler implements Comparable<Traveler>{
	private int passportId;
	private String fullName;
	
	public Traveler(int passportId, String fullName) {
		this.passportId = passportId;
		this.fullName = fullName;
	}

	public int getPassportId() {
		return passportId;
	}

	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Traveller: passportId=" + passportId + ", fullName=" + fullName;
	}

	@Override
	public int compareTo(Traveler t) {
		return this.passportId-t.getPassportId();
	}
	
}