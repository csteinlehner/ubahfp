package ubahfp.singleDataPlots;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import processing.core.*;
import ubahfp.Anlage;
import ubahfp.AnlagenLoader;
import codeanticode.glgraphics.*;
import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.*;

public class SingleDataTrianglePlot extends PApplet {

	de.fhpotsdam.unfolding.Map map;
	de.fhpotsdam.unfolding.Map watermap;
//	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:../data/uba6-13.mbtiles";
	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:../data/UBA-Mikro.mbtiles";
	
	static final int zoomLevel = 12;
	static final Location panLoc = new Location(52.4f, 13.07f);
	
	int switsch = 0; // schaltet zwischen versch. Attributen der Kläranlagen um
	String attribut;

	
	private AnlagenLoader al = new AnlagenLoader(this);
	private Vector<Anlage> positions;
	private Vector<SingleDataTriangle> datas = new Vector<SingleDataTriangle>();
	
	public void setup() {

				
		  size(1400, 820, GLConstants.GLGRAPHICS);
		  smooth();
		  noStroke();
		  map = new de.fhpotsdam.unfolding.Map(this);
		  map.setTweening(false);
		  map.zoomToLevel(zoomLevel);
		  map.panTo(panLoc);
		  map.setZoomRange(zoomLevel, zoomLevel);
		  watermap = new Map(this, 0, 0, width, height, new MBTilesMapProvider(JDBC_CONN_STRING_MAC));
		  watermap.setTweening(false);
		  watermap.zoomToLevel(zoomLevel);
		  watermap.setZoomRange(zoomLevel, zoomLevel);
		  watermap.panTo(panLoc);
		  
		  MapUtils.createDefaultEventDispatcher(this, map, watermap);
		  positions = al.getPositions();
		  for(Anlage a : positions){
			  datas.add(new SingleDataTriangle(this, a));
		  }
		}


	public void draw() {

		// System.out.println(frameRate);
		background(0);
//		map.draw();
		watermap.draw();
		// Draws locations on screen positions according to their geo-locations.
		
		/**
		 * Draw Datas
		 * 
		 */
		for(SingleDataTriangle d : datas){
			d.draw();
		}
		
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
		if(key == 'z'){
			println(watermap.getZoomLevel());
		}
	}

	String timestamp() {
		return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS",
				Calendar.getInstance());
	}

}
