package ubahfp;

import de.fhpotsdam.unfolding.geo.Location;
public class Anlage extends Location {

	
	private String name;
	private String bundesland;
	private int ausbaugroesse;
	private float jahresabwassermenge;
	private float nAblauf;
	private float pAblauf;
	
	public Anlage(float lat, float lon) {
		super(lat, lon);
	}
	
	public void setData(String name, String bundesland, int ausbaugroesse, float jahresabwassermenge, float nAblauf, float pAblauf){
		this.name = name;
		this.bundesland = bundesland;
		this.ausbaugroesse = ausbaugroesse;
		this.jahresabwassermenge = jahresabwassermenge;
		this.nAblauf = nAblauf;
		this.pAblauf = pAblauf;
	}
	public String getBundesland() {
		return bundesland;
	}

	public float getnAblauf() {
		return nAblauf;
	}

	public float getpAblauf() {
		return pAblauf;
	}

	public String getName(){
		return name;
	}
	public int getAusbaugroesse(){
		return ausbaugroesse;
	}
	public float getJahresabwassermenge(){
		return jahresabwassermenge;
	}

}
