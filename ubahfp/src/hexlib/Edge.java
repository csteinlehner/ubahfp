package hexlib;

public class Edge {
	Hex hex;
	int direction;
	
public Edge(Hex hex, int direction){
	this.hex = hex;
	this.direction = direction;
}

public Point start_point(){
	float bias = 1f;
	boolean clockwise = false;
	//int dir = HEX.add( this.direction, (clockwise?1:0) );		// vermutung: checkt nur ob wert innerhalb der range ist
	 return corner_offset( this.hex.centre(), direction, bias );
}

public Point end_point(){
	 return start_point();
}


private Point corner_offset(Point p, int d, float bias){
	float dx = 0f;
	float dy = 0f;
	
	switch(d)
	{
	case Hex.A: dx = Hex.I/2; dy = -Hex.K/2; break;
	case Hex.B: dx = Hex.I/2; dy =  Hex.K/2; break;
	case Hex.C: dx = 0; dy =  Hex.K; break;
	case Hex.D: dx = -Hex.I/2; dy =  Hex.K/2; break;
	case Hex.E: dx = -Hex.I/2; dy =  -Hex.K/2; break;
	case Hex.F: dx = 0; dy =  -Hex.K; break;
	}
	
	if(bias<=1){
		return p.offset(dx*(1+bias), dy*(1+bias));
	}else{
		return p.offset(dx,dy);
	}
}
}
