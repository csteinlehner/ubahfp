package hexlib;

import java.util.Vector;

import processing.core.PVector;

public class Hex {
		
////////////////////////////////////////////////////////////////////////////////
//
//HEX.Hex
/** Hex location class.
*
*  Possible constructors:
*    new HEX.Hex(i,j)            - i,j are hex's I & J indeces.
*    new HEX.Hex(hex)            - copy contructor
*    new HEX.Hex(new Point(x,y)) - x,y are cartesian coordinates
*    new HEX.Hex(str)            - str is a string like '2_5'
*/
	
public int i;
public int j;
Grid grid;

public Vector<PVector> points = new Vector<PVector>();

public static final int A=0;
public static final int B=1;
public static final int C=2;
public static final int D=3;
public static final int E=4;
public static final int F=5;
public static final char[] DIRECTIONS ={'A', 'B', 'C', 'D', 'E', 'F'};

public static final float M_SQRT3 =1.73205080756887729352744634150587236f; // sqrt(3)
public static final float I =1.0f;             ///< Distance between centres of adjacent hexes.
public static final float J = M_SQRT3/2.0f; ///< Distance between adjacent hex rows.
public static final float K =1.0f/M_SQRT3; ///< Length of hex's edge.

public Hex(Grid grid, int a, int b){		
		
this.grid = grid;	
this.i = a;
this.j = b;
		
}

public Edge edge(int direction){
	
	return new Edge(this, direction);
}

public Point centre(){
	
	Point result = new Point();
	if(j%2==1){
		result.x = Hex.I*(1+i);
	}else{
		result.x = Hex.I/2 + Hex.I * i;
	}
	result.y = Hex.K + Hex.J * j;
	return result;
}

/** Helper: set value from Point. */
//void set_from_point(Point p) {
////(Note I==1.0, so the factor of I has been omitted.)
//var K_2 = HEX.K / 2.0;
////BI is unit vector in direction B
//var BIx = 0.5;
//var BIy = 1.5 * HEX.K;
////CI is unit vector in direction C
//var CIx = -BIx;
//var CIy = BIy;
//
////Calculate the 'simple' solution.
//var x = p.x;
//var y = p.y - HEX.K;
//this.j = Math.round(y / HEX.J);
//if (this.j % 2) x -= 1.0; // odd rows
//else x -= 0.5; // even rows
//this.i = Math.round(x); //   x / I
////Now calculate the x,y offsets (in units of (I,J) )
//var dx = x - this.i; //   i * I
//var dy = y - this.j * HEX.J;
////Only need more work if |dy| > K/2
//if (dy < -K_2 || K_2 < dy) {
//var BId = (BIx * dx) + (BIy * dy);
//var CId = (CIx * dx) + (CIy * dy);
//
//if (BId > 0.5) HEX.go(this, HEX.B);
//else if (BId < -0.5) HEX.go(this, HEX.E);
//else if (CId > 0.5) HEX.go(this, HEX.C);
//else if (CId < -0.5) HEX.go(this, HEX.F);
//}
//}

//public static void add(int d, int i){
//	
//	
//}
//
//HEX.add = function(d,i)
//{
//  if(typeof d === 'string')
//      d=HEX.to_direction(d);
//  d= (d+i) % HEX.DIRECTIONS.length;
//  while(d<0)
//    d += HEX.DIRECTIONS.length;
//  return d;
//}
//}
//
//
///** Helper: set value from Point. */
//_set_from_point: function (p) {
//// (Note I==1.0, so the factor of I has been omitted.)
//var K_2 = HEX.K / 2.0;
//// BI is unit vector in direction B
//var BIx = 0.5;
//var BIy = 1.5 * HEX.K;
//// CI is unit vector in direction C
//var CIx = -BIx;
//var CIy = BIy;
//
//// Calculate the 'simple' solution.
//var x = p.x;
//var y = p.y - HEX.K;
//this.j = Math.round(y / HEX.J);
//if (this.j % 2) x -= 1.0; // odd rows
//else x -= 0.5; // even rows
//this.i = Math.round(x); //   x / I
//// Now calculate the x,y offsets (in units of (I,J) )
//var dx = x - this.i; //   i * I
//var dy = y - this.j * HEX.J;
//// Only need more work if |dy| > K/2
//if (dy < -K_2 || K_2 < dy) {
//var BId = (BIx * dx) + (BIy * dy);
//var CId = (CIx * dx) + (CIy * dy);
//
//if (BId > 0.5) HEX.go(this, HEX.B);
//else if (BId < -0.5) HEX.go(this, HEX.E);
//else if (CId > 0.5) HEX.go(this, HEX.C);
//else if (CId < -0.5) HEX.go(this, HEX.F);
//}
//},

///** Obtain this hex's Edge object in the given direction. */
//edge: function (direction) {
//return new HEX.Edge(this, direction);
//}
//
///** Get the centre of this hex as a HEX.Point object. */
//centre: function () {
//var result = new HEX.Point();
//if (this.j % 2) result.x = HEX.I * (1 + this.i); // odd rows
//else result.x = HEX.I / 2.0 + HEX.I * this.i; // even rows
//result.y = HEX.K + HEX.J * this.j;
//return result;
//},
//
///** Get a NEW HEX.Hex object, translated from this one by steps/distance. */
//go: function (steps, distance) {
//var result = new HEX.Hex(this);
//HEX.go(result, steps, distance);
//return result;
//},
//
//toString: function () {
//return '' + this.i + '_' + this.j;
//},
//
//valueOf: function () {
//return 10000 * this.i + this.j + 1;
//}
//



}
