package ubahfp.singleDataPlots;

import processing.core.PApplet;
import ubahfp.Anlage;

public class SingleDataCircle2 {
	private Anlage anlage;
	private PApplet p5;
	
	public SingleDataCircle2(PApplet p, Anlage a){
		anlage = a;
		p5 = p;
	}
	
	public void draw(){
		float ausbaugroesse = PApplet.sqrt(anlage.getAusbaugroesse()/1000/PApplet.PI)*10;
		float jahresabwassermenge = PApplet.sqrt(anlage.getJahresabwassermenge()/50000/PApplet.PI)*10;
		float nAblauf = PApplet.sqrt(anlage.getnAblauf()/1000/PApplet.PI)*10;
		float pAblauf = PApplet.sqrt(anlage.getpAblauf()/100/PApplet.PI)*10;
		
		float xyT[] = ((SingleDataCirclePlot2)p5).map.getScreenPositionFromLocation(anlage);
		float[] werte = {ausbaugroesse, jahresabwassermenge, nAblauf, pAblauf};
		float abstand = PApplet.max(werte)/2;
		p5.pushStyle();
//		p5.fill(0, 80, 100);
////		p5.noFill();
//		p5.ellipse(xyT[0], xyT[1], abstand*5, abstand*5);
//		p5.noStroke();

		p5.fill(235,132,69);
		p5.ellipse(xyT[0]-abstand, xyT[1]-abstand, ausbaugroesse, ausbaugroesse);
		p5.fill(100,191,181);
		p5.ellipse(xyT[0]+abstand, xyT[1]-abstand, jahresabwassermenge, jahresabwassermenge);
		p5.fill(141,168,60);
		p5.ellipse(xyT[0]+abstand, xyT[1]+abstand, nAblauf, nAblauf);
		p5.fill(220,186,70);
		p5.ellipse(xyT[0]-abstand, xyT[1]+abstand, pAblauf, pAblauf);

		p5.popStyle();
	}

}
