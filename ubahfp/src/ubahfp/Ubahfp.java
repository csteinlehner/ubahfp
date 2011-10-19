package ubahfp;

import java.util.Iterator;
import java.util.Vector;

import processing.core.PApplet;
import processing.opengl.*;
import codeanticode.glgraphics.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;

public class Ubahfp extends PApplet {

	de.fhpotsdam.unfolding.Map map;
	
	  Vector<Location> positions = new Vector<Location>();
	
	public void setup() {
		
		
		// SIMON WAS HERE
		
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
		      positions.add(new Location(Float.valueOf((csv[j][22]).trim()).floatValue(),Float.valueOf((csv[j][23]).trim()).floatValue()));
//		      println(Float.valueOf((csv[j][23]).trim()).floatValue());
		  }catch (NumberFormatException nfe){
		    System.out.println("NumberFormatException: " + nfe.getMessage());
		  }	
		}
	}

	public void draw() {
		  background(0);
		  map.draw();

		  // Draws locations on screen positions according to their geo-locations.

		  // draw all positions
		  fill(0, 200, 0, 100);
		   Iterator<Location> itr = positions.iterator();
		    while(itr.hasNext()){
		   Location t = itr.next();
		   float xyT[] = map.getScreenPositionFromLocation(t);
		//   float s = map.getZoom();
		   ellipse(xyT[0],xyT[1], 5,5);
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
