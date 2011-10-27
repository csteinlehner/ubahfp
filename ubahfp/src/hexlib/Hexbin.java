package hexlib;
import java.util.*;
import java.math.*;
import processing.core.*;
import ubahfp.Anlage;

import hexlib.*;

public class Hexbin {
	
PApplet p;	
	
float xValuer;
float yValuer;

float[] xRanger;
float[] yRanger;

float[]xRange = new float[2];
float[]yRange = new float[2];

float hexI; // distance between centrees of adjacent hexes

public Vector<Hex> hexSet = new Vector<Hex>();

Grid grid;

/**
 * @constructor
 * @param data An array of 2d points
 * 
 * @returns An array of hex objects, with important properties, including data and pointString
 * 
 */

public Hexbin(Vector<Anlage> data, PApplet p){
	
this.p = p;	

xRanger = new float[data.size()];
yRanger = new float[data.size()];


for(int i= 0; i < data.size(); i ++){

xRanger[i] = data.get(i).getLon();
yRanger[i] = data.get(i).getLat();
		
}
	
xRange = sortIt(xRanger); // gibt min und max von x zurŸck
yRange = sortIt(yRanger); // gibt min und myx von y zurŸck

hexI = ( ( xRange[1] - xRange[0] ) + ( yRange[1] - yRange[0] ) ) / 2 / 15;	// stellt grš§e der Hexagone ein (variable mitgeben)

int cols = (int)(Math.ceil((xRange[1] - xRange[0]) / hexI + 0.5f));
int rows = (int)(Math.ceil( yRange[1] - yRange[0] ) / ((PApplet.sqrt(3)/2) * hexI) + 0.5 );
grid = new Grid(cols, rows);


for (int i = 0; i<cols; i++){
	for(int j = 0; j<rows; j++){
		Hex hex = grid.hex(i, j);
		hex.points = layout_hexbinGeneratePoints(hex, hexI, xRange[0], yRange[1]);
		hexSet.add(hex);
		// sortieren?
	}
}

for(int i = 0; i < data.size(); i++){
	Anlage d = data.get(i);
	Point pt = new Point(((d.getLon() - xRange[0]) + (0.5f * hexI))/hexI,
				((yRange[1])- d.getLat()) + (0.5f * hexI)/hexI);
	
//	Hex hex = Hex.hex(pt);
	
}



}

// --- end constructor ---





float[] sortIt(float[] data){			// hier muss der Vector sortiert werden
	
float min = PApplet.min(data);
float max = PApplet.max(data);	
float[] minMax = new float[2];	

minMax[0] = min;
minMax[1] = max;

return minMax;
	
}	


private Vector<PVector> layout_hexbinGeneratePoints(Hex d, float hexI, float xMin, float yMax){
	
int dir = Hex.A;
Point pt = d.edge(dir).start_point();
float x = xMin + ( pt.x * hexI - .5f * hexI ); 
float y = yMax - ( pt.y * hexI - .5f * hexI );
Vector<PVector> pts = new Vector<PVector>();
pts.add(new PVector(x, y));

int i=-1;
while(++i < Hex.DIRECTIONS.length){
	dir = Hex.DIRECTIONS[i];
	pt = d.edge(dir).end_point();
	x = xMin + ( pt.x * hexI - .5f * hexI );
	y = yMax - ( pt.y * hexI - .5f * hexI );
	
	pts.add(new PVector(x,y));
}
return pts;
}

}



