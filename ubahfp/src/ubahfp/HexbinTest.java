package ubahfp;

import java.util.Iterator;

import hexlib.Hexbin;
import processing.core.PApplet;


public class HexbinTest extends PApplet{
	private Hexbin hx;
	private AnlagenLoader al = new AnlagenLoader(this);
	public void setup(){
		size(800,800);
		
		hx = new Hexbin(al.getPositions(), this);
//		for (int i = 0; i <hx.hexSet.size(); i++) {
//			println("x: "+hx.hexSet.get(i).points.get(0).x);
//			println("y: "+hx.hexSet.get(i).points.get(0).y);
//		}
		
	}
	
	public void draw(){
		background(0);
		stroke(255);
		for(int i=0; i<hx.hexSet.size(); i++){
			beginShape();
			for(int j=0; j<hx.hexSet.get(i).points.size();j++){
				float tx = hx.hexSet.get(i).points.get(j).x;
				float ty = hx.hexSet.get(i).points.get(j).y;
				vertex(map(tx,6,15.1f,0,500),map(ty,47.4f,55,0,500));
			}
			endShape();
		}
	}
}
