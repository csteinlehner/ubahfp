package ubahfp;

import java.util.Iterator;
import java.util.Vector;

import processing.core.PApplet;
import codeanticode.glgraphics.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;

public class Ubahfp extends PApplet {

	de.fhpotsdam.unfolding.Map map;
	
	private AnlagenLoader al = new AnlagenLoader(this);
	private Vector<Anlage> positions;
	
	public void setup() {
				
		  size(800, 600, GLConstants.GLGRAPHICS);
		  noStroke();

		  map = new de.fhpotsdam.unfolding.Map(this);
		  map.setTweening(true);
		  map.zoomToLevel(6);
		  map.panTo(new Location(51.5f, 11f));
		  MapUtils.createDefaultEventDispatcher(this, map);
		  positions = al.getPositions();
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
