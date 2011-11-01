package hexmap;
import processing.core.*;

public class Hex {
	
	
	
	PVector[] points;
	
	private float side;
	private float h;
	private float r;
		
	private int orientation; // Float = 0 | Pointy = 1
	
	private float x;
	private float y;
	private HexState hexState;
	

	public Hex(float x, float y, float side, int orientation)
	{
		this.x = x;
		this.y = y;
		this.side = side;
		this.orientation = orientation;
		this.hexState = new HexState();
		
		CalculateVertices();

		
	}

	public Hex(PVector point, float side, int orientation)
	{
		this.x = point.x;
		this.y = point.y;
		this.side = side;
		this.orientation = orientation;
		this.hexState = new HexState();

		CalculateVertices();


	}
	
	public Hex(PVector[] points, float side, int orientation)
	{
		
		this.points = points;
		this.side = side;
		this.orientation = orientation;
		this.hexState = new HexState();
		
		CalculateVertices();



	}

	/// <summary>
	/// Calculates the vertices of the hex based on orientation. Assumes that points[0] contains a value.
	/// </summary>
	private void CalculateVertices()
	{
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
		
		h = Math.CalculateH(side);
		r = Math.CalculateR(side);

		switch (orientation)
		{ 
			case 0:								// Flat
				// x,y coordinates are top left point
				points = new PVector[6];
				points[0] = new PVector(x, y);
				points[1] = new PVector(x + side, y);
				points[2] = new PVector(x + side + h, y + r);
				points[3] = new PVector(x + side, y + r + r);
				points[4] = new PVector(x, y + r + r);
				points[5] = new PVector(x - h, y + r );
				break;
			case 1:						// Pointy
				//x,y coordinates are top center point
				points = new PVector[6];
				points[0] = new PVector(x, y);
				points[1] = new PVector(x + r, y + h);
				points[2] = new PVector(x + r, y + side + h);
				points[3] = new PVector(x, y + side + h + h);
				points[4] = new PVector(x - r, y + side + h);
				points[5] = new PVector(x - r, y + h);
				break;
		}
		
	}

	// get & set orientation
	public void setOrientation(int val){
		orientation = val;	
	}
	
	public int getOrientation(){	
		return orientation;
	}
	

	// get points
	public PVector[] getPoints(){
		return points;	
	}

	// get length of side
	public float getSide(){
			return side;
	}
	
	// get h
	public float getH(){
			return h;
	}

	// get r
	public float getR(){
			return r;

	}

	public HexState getHexState(){
			return hexState;
	}

}


