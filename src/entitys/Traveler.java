package entitys;

import java.io.Serializable;

public class Traveler implements Comparable<Traveler>,Serializable{
	private int passportId; //unique id
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
	//compare by passport id
	@Override
	public int compareTo(Traveler t) {
		return this.passportId-t.getPassportId();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Traveler t = (Traveler)obj;
		return t.passportId == this.passportId;
	}
}