package ubahfp;

import java.util.Iterator;
import java.util.Vector;

import codeanticode.glgraphics.GLConstants;

import processing.core.PApplet;
import processing.core.PVector;

public class ManualPlot extends PApplet {
	private AnlagenVerdraengungLoader al = new AnlagenVerdraengungLoader(this);
	private Vector<AnlageVerdraengung> positions;


	public void setup(){
		  size(800, 600, GLConstants.GLGRAPHICS);
		  
		  background(0);
		  fill(255);
		  positions = al.getPositions();
		  Iterator<AnlageVerdraengung> itr =  positions.iterator();
		  while(itr.hasNext()){
		   	AnlageVerdraengung t = itr.next();
		   	t.setPos(t);
			t.setRadius(t.getAusbaugroesse() / 10000);
			checkHit(t);
			println(t.getPos());
			  
		  }
		
	}
	
	public void draw(){
		  Iterator<AnlageVerdraengung> itr2 =  positions.iterator();
		  while(itr2.hasNext()){
		   	AnlageVerdraengung t = itr2.next();
		  ellipse(map(t.getPos().x,40,60,50,500), map(t.getPos().y,0,15,50,500), t.getRadius(), t.getRadius());
		  }
		
	}
	void checkHit(AnlageVerdraengung t){
		 Iterator<AnlageVerdraengung> itr =  positions.iterator();
		  while(itr.hasNext()){
			  AnlageVerdraengung t2 = itr.next();
			  if(t!=t2){
				  if(t.getPos().dist(t2.getPos())<t.getRadius()+t2.getRadius()){
					     PVector distanceV = PVector.sub(t.getPos(), t2.getPos());
					        float theta  = atan2(distanceV.y, distanceV.x);
					        PVector mDistance = new PVector(t.getRadius()+t2.getRadius(),0);
					        mDistance.rotate(theta);
					        mDistance.limit(t.getRadius()+t2.getRadius());
					        t2.setPos(PVector.sub(t.getPos(),mDistance));
				  }  
			  }
		  }
		  }
}
