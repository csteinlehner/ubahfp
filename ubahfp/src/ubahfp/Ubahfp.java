package ubahfp;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import processing.core.*;
import codeanticode.glgraphics.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;

public class Ubahfp extends PApplet {

	de.fhpotsdam.unfolding.Map map;

	Vector<Anlage> positions = new Vector<Anlage>();

	Iterator<Anlage> itr;

	int switsch = 0; // schaltet zwischen versch. Attributen der Kl�ranlagen um
	String attribut;

	public void setup() {

		size(800, 600, GLConstants.GLGRAPHICS);
		// size(800, 600);

		noStroke();

		smooth();
		hint(ENABLE_OPENGL_4X_SMOOTH);

		map = new de.fhpotsdam.unfolding.Map(this);
		map.setTweening(true);
		map.zoomToLevel(6);
		map.panTo(new Location(51.5f, 11f));
		MapUtils.createDefaultEventDispatcher(this, map);

		String lines[] = loadStrings("pl_dp_join_2008_02_latlong.csv");
		String[][] csv;
		int csvWidth = 0;

		// calculate max width of csv file
		for (int i = 0; i < lines.length; i++) {
			String[] chars = split(lines[i], ';');
			if (chars.length > csvWidth) {
				csvWidth = chars.length;
			}
		}

		// create csv array based on # of rows and columns in csv file
		csv = new String[lines.length][csvWidth];

		// parse values into 2d array
		for (int i = 0; i < lines.length; i++) {
			String[] temp = new String[lines.length];
			temp = split(lines[i], ';');
			for (int j = 0; j < temp.length; j++) {
				csv[i][j] = temp[j];
			}
		}

		println(csv[1][0]);

		// fill positions vector
		for (int j = 1; j < csv.length; j++) {
			try {
				Anlage a = new Anlage(Float.valueOf((csv[j][22]).trim())
						.floatValue(), Float.valueOf((csv[j][23]).trim())
						.floatValue());
				a.setData(csv[j][0], csv[j][1], csv[j][21], 
						Integer.parseInt(csv[j][5]),
						Float.valueOf((csv[j][6]).trim()).floatValue(), 
						Float.valueOf((csv[j][17]).trim()).floatValue(),
						Float.valueOf((csv[j][18]).trim()).floatValue(),
						Integer.valueOf((csv[j][8]).trim()).intValue(),
						Integer.valueOf((csv[j][9]).trim()).intValue(),
						Integer.valueOf((csv[j][10]).trim()).intValue(),
						Integer.valueOf((csv[j][11]).trim()).intValue(),
						Integer.valueOf((csv[j][12]).trim()).intValue(),
						Integer.valueOf((csv[j][13]).trim()).intValue(),
						Integer.valueOf((csv[j][14]).trim()).intValue(),
						Integer.valueOf((csv[j][15]).trim()).intValue()
						);
				positions.add(a);
				// println(Float.valueOf((csv[j][23]).trim()).floatValue());
			} catch (NumberFormatException nfe) {
				System.out.println("NumberFormatException in row " + (j + 1)
						+ " : " + nfe.getMessage());
			}
		}
		println(positions.size() + " Anlagen in positions");
	}

	public void draw() {

		// System.out.println(frameRate);
		background(0);
		map.draw();

		// Draws locations on screen positions according to their geo-locations.

		// draw all positions
		itr = positions.iterator();
		while (itr.hasNext()) {
			Anlage t = itr.next();
			float xyT[] = map.getScreenPositionFromLocation(t);
			// float s = map.getZoom();
			fill(0, 200, 0, 100);

			// fill(0);
			// ellipse(xyT[0],xyT[1], 3,3);

			
			switch (switsch) {

			case 0:
				ellipse(xyT[0], xyT[1], t.getAusbaugroesse() / 10000,
						t.getAusbaugroesse() / 10000);
				break;

			case 1:
				ellipse(xyT[0], xyT[1], t.getJahresabwassermenge() / 1000000,
						t.getJahresabwassermenge() / 1000000);
				break;

			case 2:
				ellipse(xyT[0], xyT[1], t.getnAblauf() / 10000,
						t.getnAblauf() / 10000);
				break;

			case 3:
				ellipse(xyT[0], xyT[1], t.getpAblauf() / 1000,
						t.getpAblauf() / 1000);
				break;
				
			case 4:
				
				if(t.get_pl_n_tech() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
					
				}
				break;	
				
			case 5:
				if(t.get_pl_p_tech() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;	
				
			case 6:
				if(t.get_pl_uv() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;	
				
			case 7:
				if(t.get_pl_chlorination() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;		
				
			case 8:
				if(t.get_pl_ozonation() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;	
				
			case 9:
				if(t.get_pl_sand_filtration() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 10:
				if(t.get_pl_micro_filtration() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 11:
				if(t.get_pl_other() == 1){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;

			case 12:
				if(t.getBundesland().equals("BB")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;	
				
			case 13:
				if(t.getBundesland().equals("BE")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;	
				
			case 14:
				if(t.getBundesland().equals("BW")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 15:
				if(t.getBundesland().equals("BY")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 16:
				if(t.getBundesland().equals("HB")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 17:
				if(t.getBundesland().equals("HE")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 18:
				if(t.getBundesland().equals("HH")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 19:
				if(t.getBundesland().equals("MV")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 20:
				if(t.getBundesland().equals("NI")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 21:
				if(t.getBundesland().equals("NW")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 22:
				if(t.getBundesland().equals("RI")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 23:
				if(t.getBundesland().equals("RP")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 24:
				if(t.getBundesland().equals("SH")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 25:
				if(t.getBundesland().equals("SL")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 26:
				if(t.getBundesland().equals("SN")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 27:
				if(t.getBundesland().equals("ST")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 28:
				if(t.getBundesland().equals("TH")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 29:
				if(t.getFlussgebietseinheit().equals("Donau (normales Gebiet)")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 30:
				if(t.getFlussgebietseinheit().equals("Eider")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 31:
				if(t.getFlussgebietseinheit().equals("Elbe")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 32:
				if(t.getFlussgebietseinheit().equals("Ems")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 33:
				if(t.getFlussgebietseinheit().equals("Maas")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 34:
				if(t.getFlussgebietseinheit().equals("Oder")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 35:
				if(t.getFlussgebietseinheit().equals("Rhein")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 36:
				if(t.getFlussgebietseinheit().equals("Schlei/Trave")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 37:
				if(t.getFlussgebietseinheit().equals("Warnow/Peene")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
				
			case 38:
				if(t.getFlussgebietseinheit().equals("Weser")){
				stroke(0);
				line(xyT[0]-2, xyT[1]-2,xyT[0]+2, xyT[1]+2);
				line(xyT[0]-2, xyT[1]+2,xyT[0]+2, xyT[1]-2);
				noStroke();
				} else {
				
				}
				break;
			}
			

			// // Fixed-size marker
			// float xyBerlin[] =
			// map.getScreenPositionFromLocation(locationBerlin);
			// fill(0, 200, 0, 100);
			// ellipse(xyBerlin[0], xyBerlin[1], 20, 20);
			//
			// // Zoom dependent marker size
			// float xyLondon[] =
			// map.getScreenPositionFromLocation(locationLondon);
			// fill(200, 0, 0, 100);
			// float s = map.getZoom();
			// ellipse(xyLondon[0], xyLondon[1], s, s);

		}

		switch (switsch) {

		case 0:
			attribut = "Ausbaugr��e";
			break;

		case 1:
			attribut = "Jahresabwassermenge";
			break;

		case 2:
			attribut = "N-Ablauf";
			break;

		case 3:
			attribut = "P-Ablauf";
			break;
			
		case 4:
			attribut = "N-Entfernung";
			break;	

		case 5:
			attribut = "P-Entfernung";
			break;	
			
		case 6:
			attribut = "UV";
			break;	
			
		case 7:
			attribut = "Chlor";
			break;		
			
		case 8:
			attribut = "Ozon";
			break;	
			
		case 9:
			attribut = "Sand";
			break;
			
		case 10:
			attribut = "Micro";
			break;
			
		case 11:
			attribut = "Other";
			break;	
			
		case 12:
			attribut = "Brandenburg";
			break;	
			
		case 13:
			attribut = "Berlin";
			break;	
			
		case 14:
			attribut = "Baden-W�rtenberg";
			break;	
			
		case 15:
			attribut = "Bayern";
			break;	
			
		case 16:
			attribut = "Bremen";
			break;	
			
		case 17:
			attribut = "Hessen";
			break;	
			
		case 18:
			attribut = "Hamburg";
			break;	
			
		case 19:
			attribut = "Mecklenburg-Vorpommer";
			break;	
			
		case 20:
			attribut = "Niedersachsen";
			break;	
			
		case 21:
			attribut = "Nordrhein-Westfalen";
			break;	
			
		case 22:
			attribut = "???";
			break;	

		case 23:
			attribut = "Rheinland-Pfalz";
			break;	

		case 24:
			attribut = "Schleswig-Holstein";
			break;	

		case 25:
			attribut = "Saarland";
			break;	
			
		case 26:
			attribut = "Sachsen";
			break;
			
		case 27:
			attribut = "Sachsen-Anhalt";
			break;	
			
		case 28:
			attribut = "Th�ringen";
			break;	
			
		case 29:
			attribut = "Donau";
			break;	
			
		case 30:
			attribut = "Eider";
			break;	
			
		case 31:
			attribut = "Elbe";
			break;	
			
		case 32:
			attribut = "Ems";
			break;	
			
		case 33:
			attribut = "Maas";
			break;	
			
		case 34:
			attribut = "Oder";
			break;	
			
		case 35:
			attribut = "Rhein";
			break;	
			
		case 36:
			attribut = "Schlei/Trave";
			break;	
			
		case 37:
			attribut = "Warnow/Peene";
			break;	

		case 38:
			attribut = "Weser";
			break;	
	
		}

		println(attribut);

		if (switsch > 38)
			switsch = 0;
		if (switsch < 0)
			switsch = 38;
	}

	public void keyReleased() {

		if (key == ' ') {
			saveFrame("screenshots/UBA_KA_" + attribut + "_" + timestamp()
					+ ".png");
		}
		if (key == 'a') {
			switsch--;
		}
		if (key == 'd') {
			switsch++;
		}
	}

	String timestamp() {
		return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS",
				Calendar.getInstance());
	}

}
