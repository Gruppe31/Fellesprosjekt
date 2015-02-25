package model;

public class Rom {
	private String romnavn;
	private int antall;
	
	public Rom(String romnavn, int antall){
		this.setRomnavn(romnavn);
		this.setAntall(antall);
	}

	public String getRomnavn() {
		return romnavn;
	}

	public void setRomnavn(String romnavn) {
		this.romnavn = romnavn;
	}

	public int getAntall() {
		return antall;
	}

	public void setAntall(int antall) {
		this.antall = antall;
	}
	
	
	
}
