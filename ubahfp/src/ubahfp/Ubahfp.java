package ubahfp;

import java.util.Iterator;
import java.util.Vector;

import processing.core.PApplet;
import codeanticode.glgraphics.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;

public class Ubahfp extends PApplet {

	de.fhpotsdam.unfolding.Map map;
	
	  Vector<Anlage> positions = new Vector<Anlage>();
	
	public void setup() {
		
		
		// SIMON WAS HERE
		
		// AGAIN!
		
		  size(800, 600, GLConstants.GLGRAPHICS);
		  noStroke();

		  map = new de.fhpotsdam.unfolding.Map(this);
		  map.setTweening(true);
		  map.zoomToLevel(6);
		  map.panTo(new Location(51.5f, 11f));
		  MapUtils.createDefaultEventDispatcher(this, map);

		  String lines[] = loadStrings("pl_dp_join_2008_02_latlong.csv");
		  String [][] csv;
		  int csvWidth=0;
		  
		  //calculate max width of csv file
		for (int i=0; i < lines.length; i++) {
		  String [] chars=split(lines[i],';');
		  if (chars.length>csvWidth){
		    csvWidth=chars.length;
		  }
		}
		
		//create csv array based on # of rows and columns in csv file
		csv = new String [lines.length][csvWidth];

		//parse values into 2d array
		for (int i=0; i < lines.length; i++) {
		  String [] temp = new String [lines.length];
		  temp= split(lines[i], ';');
		  for (int j=0; j < temp.length; j++){
		   csv[i][j]=temp[j];
		  }
		}

		
		// fill positions vector
		for(int j=1; j < csv.length; j++){
		   try{
			   Anlage a = new Anlage(Float.valueOf((csv[j][22]).trim()).floatValue(),Float.valueOf((csv[j][23]).trim()).floatValue());
			   a.setData(csv[j][0], csv[j][1], Integer.parseInt(csv[j][5]), Float.valueOf((csv[j][6]).trim()).floatValue(), Float.valueOf((csv[j][17]).trim()).floatValue(), Float.valueOf((csv[j][18]).trim()).floatValue());
		      positions.add(a);
//		      println(Float.valueOf((csv[j][23]).trim()).floatValue());
		  }catch (NumberFormatException nfe){
		    System.out.println("NumberFormatException in row "+(j+1)+" : " + nfe.getMessage());
		  }	
		}
		println(positions.size()+" Anlagen in positions");
	}

	public void draw() {
		  background(0);
		  map.draw();

		  // Draws locations on screen positions according to their geo-locations.

		  // draw all positions
		  fill(0, 200, 0, 100);
		   Iterator<Anlage> itr = positions.iterator();
		    while(itr.hasNext()){
		   Anlage t = itr.next();
		   float xyT[] = map.getScreenPositionFromLocation(t);
		//   float s = map.getZoom();
		   ellipse(xyT[0],xyT[1], t.getnAblauf()/10000,t.getpAblauf()/1000);
		    }

		//  // Fixed-size marker
		//  float xyBerlin[] = map.getScreenPositionFromLocation(locationBerlin);
		//  fill(0, 200, 0, 100);
		//  ellipse(xyBerlin[0], xyBerlin[1], 20, 20);
		//
		//  // Zoom dependent marker size
		//  float xyLondon[] = map.getScreenPositionFromLocation(locationLondon);
		//  fill(200, 0, 0, 100);
		//  float s = map.getZoom();
		//  ellipse(xyLondon[0], xyLondon[1], s, s);
	}
}
