package hexlib;

public class Point {
	public float x, y;
	
public Point(float a, float b){
	x=a;
	y=b;
}
public Point() {
	x=0;
	y=0;
}
public Point offset(float dx, float dy){
	return new Point(x+dx,y+dy);
}
}
