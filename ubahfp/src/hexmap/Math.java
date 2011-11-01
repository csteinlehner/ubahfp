package hexmap;

import java.math.*;
import processing.core.*;

public class Math {
	

	//  
	//  h = short length (outside)
	//  r = long length (outside)
	//  side = length of a side of the hexagon, all 6 are equal length
	//
	//  h = sin (30 degrees) x side
	//  r = cos (30 degrees) x side
	//
	//		 h
	//	     ---
	//   ----     |r
	//  /    \    |          
	// /      \   |
	// \      /
	//  \____/
	//
	// Flat orientation (scale is off)
	//
	//     /\
	//    /  \
	//   /    \
	//   |    |
	//   |    |
	//   |    |
	//   \    /
	//    \  /
	//     \/
	// Pointy orientation (scale is off)

	public static double degreesToRadians(double degrees)
	{
		//http://en.wikipedia.org/wiki/Radians
		return degrees * PApplet.PI / 180;
	}

	public static double radiansToDegrees(double radians)
	{
		return radians * 180 / PApplet.PI;
	}


	/// <summary>
	/// Outside triangle side (short)
	/// </summary>
	public static float CalculateH(float side)
	{
		return PApplet.sin(PApplet.radians(30)) * side;
	}

	/// <summary>
	/// Outside triangle side (long)
	/// </summary>
	public static float CalculateR(float side)
	{
		return PApplet.cos(PApplet.radians(30)) * side;
	}


	public static boolean InsidePolygon(PVector[] polygon, int N, PVector p)
	{
		//http://astronomy.swin.edu.au/~pbourke/geometry/insidepoly/
		//
		// Slick algorithm that checks if a point is inside a polygon.  Checks how may time a line
		// origination from point will cross each side.  An odd result means inside polygon.
		//
		int counter = 0;
		int i;
		double xinters;
		PVector p1,p2;
		
		p1 = polygon[0];
		for (i=1;i<=N;i++) 
		{
			p2 = polygon[i % N];
			if (p.x > PApplet.min(p1.y,p2.y)) 
			{
				if (p.y <= PApplet.max(p1.y,p2.y)) 
				{
					if (p.x <= PApplet.max(p1.x,p2.x)) 
					{
						if (p1.y != p2.y) 
						{
							xinters = (p.y-p1.y)*(p2.x-p1.x)/(p2.y-p1.y)+p1.x;
							if (p1.x == p2.x || p.x <= xinters)
								counter++;
						}
					}
				}
			}	
			p1 = p2;
		}

		if (counter % 2 == 0)
			return false;
		else
			return true;
	}

	
	
	

}
