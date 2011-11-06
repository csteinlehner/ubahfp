package ubahfp.singleDataPlots;

import processing.core.PApplet;
import ubahfp.Anlage;

public class SingleDataCircle {
	private Anlage anlage;
	private PApplet p5;
	
	public SingleDataCircle(PApplet p, Anlage a){
		anlage = a;
		p5 = p;
	}
	
	public void draw(){
		float ausbaugroesse = PApplet.sqrt(anlage.getAusbaugroesse()/1000/PApplet.PI);
		float jahresabwassermenge = PApplet.sqrt(anlage.getJahresabwassermenge()/50000/PApplet.PI);
		float nAblauf = PApplet.sqrt(anlage.getnAblauf()/1000/PApplet.PI);
		float pAblauf = PApplet.sqrt(anlage.getpAblauf()/100/PApplet.PI);
		
		float xyT[] = ((SingleDataCirclePlot)p5).map.getScreenPositionFromLocation(anlage);
		float[] werte = {ausbaugroesse, jahresabwassermenge, nAblauf, pAblauf};
		float abstand = PApplet.max(werte)/2;
		p5.pushStyle();
		p5.fill(0, 80, 100);
//		p5.noFill();
		p5.ellipse(xyT[0], xyT[1], abstand*5, abstand*5);
		p5.noStroke();
		p5.fill(179,240,255);
		p5.ellipse(xyT[0]-abstand, xyT[1]-abstand, ausbaugroesse, ausbaugroesse);
		p5.ellipse(xyT[0]+abstand, xyT[1]-abstand, jahresabwassermenge, jahresabwassermenge);
		p5.ellipse(xyT[0]+abstand, xyT[1]+abstand, nAblauf, nAblauf);
		p5.ellipse(xyT[0]-abstand, xyT[1]+abstand, pAblauf, pAblauf);
		p5.popStyle();
	}

}
