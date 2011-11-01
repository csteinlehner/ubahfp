package ubahfp;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.media.opengl.GL;

import org.apache.log4j.Logger;

import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PGraphicsOpenGL;
import processing.xml.XMLElement;
import codeanticode.glgraphics.GLConstants;
import codeanticode.glgraphics.GLGraphicsOffScreen;
import codeanticode.glgraphics.GLTexture;
import codeanticode.glgraphics.GLTextureFilter;
import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.events.EventDispatcher;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.GeoMapApp;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
//import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.Yahoo;
import de.fhpotsdam.unfolding.utils.DebugDisplay;
import de.fhpotsdam.unfolding.utils.MapUtils;


//import toxi.sim.fluids.*;

//import SearchGeometry.*;

@SuppressWarnings("serial")
public class TileMill extends PApplet {

//	public static Logger log = Logger.getLogger(isoTest.class);

	Map map;
	EventDispatcher eventDispatcher;

	List<Location> rssGeoLocations = new ArrayList<Location>();

	// ISO
//	IsoContour iso;
//	int isoThreshold = 100;

	// TOXI
//	int NUM_PARTICLES = 8000;
//	VerletPhysics2D physics;
//	AttractionBehavior mouseAttractor;
//
//	List<AttractionBehavior> geoAttractor = new ArrayList<AttractionBehavior>();
//
//	ArrayList<NodeAttr> nodes = new ArrayList<NodeAttr>();

	// Map<Location, AttractionBehavior> attractionMap = new HashMap();

//	Vec2D mousePos;

	DebugDisplay debugDisplay;

	GLGraphicsOffScreen layer;
	GLTexture srcTex, bloomMask, destTex, darkLayer;
	GLTexture tex0, tex2, tex4, tex8, tex16;
	GLTexture tmp2, tmp4, tmp8, tmp16;
	GLTextureFilter extractBloom, blur,blur2, blend4, toneMap, maskFilter;

	PVector lastPos;
	
	GL gl;
	PGraphicsOpenGL pgl;
	
	float maskFactor=0f;
	float bloomFactor=1.5f;
	float zoom, lastZoom,particleZoom, bloomZoom;

	boolean zoomChange=false;

	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:data/controlroom.mbtiles";

//	public static void main(String[] args) {
//
//		PApplet.main(new String[] { "TileMill" }); // Der letzte
//																// String muss
//																// der Pfad zu
//																// Deiner Klasse
//																// sein
//
//	}

	public void setup() {
		// Padding in Abstrac
		size(1024, 768, GLConstants.GLGRAPHICS);
		
		pgl = (PGraphicsOpenGL) g;
		gl = pgl.gl;
		
		/*
		hint(DISABLE_OPENGL_2X_SMOOTH);
		hint(ENABLE_OPENGL_4X_SMOOTH);
		hint(ENABLE_NATIVE_FONTS);
		hint(ENABLE_ACCURATE_TEXTURES);
		hint(DISABLE_DEPTH_TEST);
		hint(DISABLE_OPENGL_ERROR_REPORT);
		*/
		
		// smooth();
		frameRate(700);
		// Creates default mapDisplay
		// map = new Map(this, "map", 50, 50, 700, 500);
		 map = new Map(this, 0, 0, width, height, new MBTilesMapProvider(JDBC_CONN_STRING_MAC));
//		map = new Map(this, 0, 0, width, height, new Microsoft.RoadProvider());

		// map.setTweening(true);
		// map.zoomToLevel(12);
		map.zoomAndPanTo(new Location(52.52317f, 13.4116f), 13);
		map.mapDisplay.grid_padding = 1;

		// Creates default dispatcher
		eventDispatcher = MapUtils.createDefaultEventDispatcher(this, map);


		// TOXI
//		physics = new VerletPhysics2D();
//		physics.setDrag(0.05f);
//		physics.setWorldBounds(new Rect(0, 0, width, height));
		// the NEW way to add gravity to the simulation, using behaviors
		// physics.addBehavior(new GravityBehavior(new Vec2D(0, 0.15f)));
		//addParticle();
		noStroke();
		fill(0, 0, 0, 100);

		// BUFFER
		layer = new GLGraphicsOffScreen(this, width, height);

//		iso = new IsoContour(this, new PVector(0, 0),
//				new PVector(width, height), 40, 40);
		debugDisplay = new DebugDisplay(this, map.mapDisplay, 600, 200, 250,
				150);

		// Loading required filters.
//		extractBloom = new GLTextureFilter(this, "ExtractBloomBG.xml");
//		blur = new GLTextureFilter(this, "Blur.xml");
//		blur2 = new GLTextureFilter(this, "Blur1D.xml");
//		blend4 = new GLTextureFilter(this, "Blend4.xml");
//		toneMap = new GLTextureFilter(this, "ToneMap.xml");
//
//		destTex = new GLTexture(this, width, height);
//		darkLayer = new GLTexture(this, width, height);
//
//		maskFilter = new GLTextureFilter(this, "Mask.xml");
//
//		// Initializing bloom mask and blur textures.
//		bloomMask = new GLTexture(this, width, height, GLTexture.FLOAT);
//		tex0 = new GLTexture(this, width, height, GLTexture.FLOAT);
//		tex2 = new GLTexture(this, width / 2, height / 2, GLTexture.FLOAT);
//		tmp2 = new GLTexture(this, width / 2, height / 2, GLTexture.FLOAT);
//		tex4 = new GLTexture(this, width / 4, height / 4, GLTexture.FLOAT);
//		tmp4 = new GLTexture(this, width / 4, height / 4, GLTexture.FLOAT);
//		tex8 = new GLTexture(this, width / 8, height / 8, GLTexture.FLOAT);
//		tmp8 = new GLTexture(this, width / 8, height / 8, GLTexture.FLOAT);
//		tex16 = new GLTexture(this, width / 16, height / 16, GLTexture.FLOAT);
//		tmp16 = new GLTexture(this, width / 16, height / 16, GLTexture.FLOAT);
		
		
	
		
		//gl.glDisable(GLConstants.GL_DEPTH_TEST);
	}

	

	public void draw() {
		
		zoomChange=false;
		// background(0);
		// iso.clear();
		map.draw();
		zoom = map.getZoomLevel();
		
		// image(darkLayer,0,0);

		
		
	}

	public void keyPressed() {

	}

	/*
	 * public void mousePressed() { mousePos = new Vec2D(mouseX, mouseY); //
	 * create a new positive attraction force field around the mouse position
	 * (radius=250px) mouseAttractor = new AttractionBehavior(mousePos, 250,
	 * 0.9f); physics.addBehavior(mouseAttractor); }
	 */

}
