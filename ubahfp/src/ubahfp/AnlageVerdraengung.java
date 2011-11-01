package ubahfp;

import processing.core.PVector;

public class AnlageVerdraengung extends Anlage {
	private float radius = 0;
	private float maxRadius = 0;
	public boolean isScaling = true;
	
	private PVector pos = new PVector();
	
	public AnlageVerdraengung(float lat, float lon) {
		super(lat, lon);
	}
	
	public void setRadius(float r){	radius = r; }
	public float getRadius(){ return radius; }
	public void setPos(float[] xy){ pos.x = xy[0]; pos.y = xy[1];}
	public void setPos(PVector v){ pos = v;}
	public PVector getPos(){ return pos;}
	public void setMaxRadius(float maxRadius) {	this.maxRadius = maxRadius;	}
	public float getMaxRadius() {return maxRadius;}
}
