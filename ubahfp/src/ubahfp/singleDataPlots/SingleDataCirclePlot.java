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

public class SingleDataCirclePlot extends PApplet {

	de.fhpotsdam.unfolding.Map map;
	de.fhpotsdam.unfolding.Map watermap;
	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:../data/uba6-13.mbtiles";
	
	int switsch = 0; // schaltet zwischen versch. Attributen der Kläranlagen um
	String attribut;

	
	private AnlagenLoader al = new AnlagenLoader(this);
	private Vector<Anlage> positions;
	private Vector<SingleDataCircle> datas = new Vector<SingleDataCircle>();
	
	public void setup() {

				
		  size(820, 820, GLConstants.GLGRAPHICS);
		  smooth();
		  noStroke();
		  map = new de.fhpotsdam.unfolding.Map(this);
		  map.setTweening(false);
		  map.zoomToLevel(6);
		  map.panTo(new Location(51.5f, 11f));
		  map.setZoomRange(6, 15);
		  watermap = new Map(this, 0, 0, width, height, new MBTilesMapProvider(JDBC_CONN_STRING_MAC));
		  watermap.setTweening(false);
		  watermap.zoomToLevel(6);
		  watermap.setZoomRange(6, 15);
		  watermap.panTo(new Location(51.5f, 11f));
		  
		  MapUtils.createDefaultEventDispatcher(this, map, watermap);
		  positions = al.getPositions();
		  for(Anlage a : positions){
			  datas.add(new SingleDataCircle(this, a));
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
		for(SingleDataCircle d : datas){
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
