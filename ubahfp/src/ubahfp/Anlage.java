package ubahfp;

import de.fhpotsdam.unfolding.geo.Location;
public class Anlage extends Location {

	
	private String name;
	private String bundesland;
	private int ausbaugroesse;
	private float jahresabwassermenge;
	private float nAblauf;
	private float pAblauf;
	private int pl_n_tech, pl_p_tech, pl_uv, pl_chlorination, pl_ozonation, pl_sand_filtration, pl_micro_filtration, pl_other;
	private String flussgebietseinheit;

	
	
	
	// Abwassereinigung, pl_n_tech, pl_p_tech, pl_uv, pl_chlorination, pl_ozonation, pl_sand_filtration, pl_micro_filtration, pl_other, pl_other_comment
	
	public Anlage(float lat, float lon) {
		super(lat, lon);
	}
	
	public void setData(String name, String bundesland, String flussgebietseinheit, int ausbaugroesse, float jahresabwassermenge, float nAblauf, float pAblauf, int pl_n_tech, int pl_p_tech, int pl_uv, int pl_chlorination, int pl_ozonation, int pl_sand_filtration, int pl_micro_filtration, int pl_other ){
		this.name = name;
		this.bundesland = bundesland;
		this.ausbaugroesse = ausbaugroesse;
		this.jahresabwassermenge = jahresabwassermenge;
		this.nAblauf = nAblauf;
		this.pAblauf = pAblauf;
		this.pl_n_tech = pl_n_tech;
		this.pl_p_tech = pl_p_tech;
		this.pl_uv = pl_uv;
		this.pl_chlorination = pl_chlorination;
		this.pl_ozonation = pl_ozonation;
		this.pl_sand_filtration = pl_sand_filtration;
		this.pl_micro_filtration = pl_micro_filtration;
		this.pl_other = pl_other;
		this.flussgebietseinheit = flussgebietseinheit;

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
	
	public int get_pl_n_tech(){
	return pl_n_tech;
	}	
	
	public int get_pl_p_tech(){
	return	pl_p_tech;
	}	
	
	public int get_pl_uv(){
	return	pl_uv;
	}	
	
	public int get_pl_chlorination(){
	return	pl_chlorination;
	}	
	
	public int get_pl_ozonation(){
	return pl_ozonation;	
	}	
	
	public int get_pl_sand_filtration(){
	return	pl_sand_filtration;
	}	
	
	public int get_pl_micro_filtration(){
	return	pl_micro_filtration;
	}	
	
	public int get_pl_other(){
	return	pl_other;
	}	
	
	public String getFlussgebietseinheit(){
	return flussgebietseinheit;	
	}

}
