package hexmap;

import java.util.Vector;
import processing.core.*;

	/// <summary>
	/// Represents a 2D hexagon board
	/// </summary>
	public class Board
	{
	
		
		Hex[][] hexes;
		
		private int width;
		private int height;
		private int xOffset;
		private int yOffset;
		private int side;
		private float pixelWidth;
		private float pixelHeight;
		
		private int orientation;
		
		/// <param name="width">Board width</param>
		/// <param name="height">Board height</param>
		/// <param name="side">Hexagon side length</param>
		/// <param name="orientation">Orientation of the hexagons</param>
		public Board(int width, int height, int side, int orientation)
		{
			Initialize(width, height, side, orientation, 0, 0);
		}

		/// <param name="width">Board width</param>
		/// <param name="height">Board height</param>
		/// <param name="side">Hexagon side length</param>
		/// <param name="orientation">Orientation of the hexagons</param>
		/// <param name="xOffset">X coordinate offset</param>
		/// <param name="yOffset">Y coordinate offset</param>
		public Board(int width, int height, int side, int orientation, int xOffset, int yOffset)
		{
			Initialize(width, height, side, orientation, xOffset, yOffset);
		}


		// getter
		
		public Hex[][] getHexes(){
		return hexes;		
		}
		
		public float getPixelWidth(){
		return pixelWidth;	
		}
		
		public float getPixelHeight(){
		return pixelHeight;	
		}
		
		public int getXOffSet(){
		return xOffset;	
		}
		
		public int getYOffSet(){
		return yOffset;	
		}
		
		public int getWidth(){
		return width;	
		}
		
		public int getHeight(){
		return height;	
		}
		
		
		// ----------------
		
//
//		public System.Drawing.Color BackgroundColor
//		{
//			get
//			{
//				return backgroundColor;
//			}
//			set
//			{
//				backgroundColor = value;
//			}
//		}
//
//		public Hexagonal.BoardState BoardState
//		{
//			get
//			{
//				return boardState;
//			}
//			set
//			{
//				throw new System.NotImplementedException();
//			}
//		}


		/// <summary>
		/// Sets internal fields and creates board
		/// </summary>
		/// <param name="width">Board width</param>
		/// <param name="height">Board height</param>
		/// <param name="side">Hexagon side length</param>
		/// <param name="orientation">Orientation of the hexagons</param>
		/// <param name="xOffset">X coordinate offset</param>
		/// <param name="yOffset">Y coordinate offset</param>
		
		private void Initialize(int theWidth, int theHeight, int side, int orientation, int xOffset, int yOffset)
		{
			this.width = theWidth;
			this.height = theHeight;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.side = side;
			this.orientation = orientation;
			hexes = new Hex[theHeight][theWidth]; //opposite of what we'd expect
			
			
			

			float h = Math.CalculateH(side); // short side
			float r = Math.CalculateR(side); // long side

			//
			// Calculate pixel info..remove?
			// because of staggering, need to add an extra r/h
			float hexWidth = 0;
			float hexHeight = 0;
			switch (orientation)
			{
				case 0:
					hexWidth = side + h;
					hexHeight = r + r;
					this.pixelWidth = (theWidth * hexWidth) + h;
					this.pixelHeight = (theHeight * hexHeight) + r;
					break;
				case 1:
					hexWidth = r + r;
					hexHeight = side + h;
					this.pixelWidth = (theWidth * hexWidth) + r;
					this.pixelHeight = (theHeight * hexHeight) + h;
					break;
				default:
					break;
			}


			boolean inTopRow = false;
			boolean inBottomRow = false;
			boolean inLeftColumn = false;
			boolean inRightColumn = false;
			boolean isTopLeft = false;
			boolean isTopRight = false;
			boolean isBotomLeft = false;
			boolean isBottomRight = false;


			// i = y coordinate (rows), j = x coordinate (columns) of the hex tiles 2D plane
			for (int i = 0; i < theHeight; i++)
			{
				for (int j = 0; j < theWidth; j++)
				{
					// Set position booleans
					//#region Position Booleans
						if (i == 0)
						{
							inTopRow = true;
						}
						else
						{
							inTopRow = false;
						}

						if (i == theHeight - 1)
						{
							inBottomRow = true;
						}
						else
						{
							inBottomRow = false;
						}

						if (j == 0)
						{
							inLeftColumn = true;
						}
						else
						{
							inLeftColumn = false;
						}

						if (j == theWidth - 1)
						{
							inRightColumn = true;
						}
						else
						{
							inRightColumn = false;
						}

						if (inTopRow && inLeftColumn)
						{
							isTopLeft = true;
						}
						else
						{
							isTopLeft = false;
						}

						if (inTopRow && inRightColumn)
						{
							isTopRight = true;
						}
						else
						{
							isTopRight = false;
						}

						if (inBottomRow && inLeftColumn)
						{
							isBotomLeft = true;
						}
						else
						{
							isBotomLeft = false;
						}

						if (inBottomRow && inRightColumn)
						{
							isBottomRight = true;
						}
						else
						{
							isBottomRight = false;
						}
//						#endregion

					//
					// Calculate Hex positions
					//
					if (isTopLeft)
					{
						//First hex
						switch (orientation)
						{ 
							case 0:
								hexes[0][0] = new Hex(0 + h + xOffset, 0 + yOffset, side, orientation);
								break;
							case 1:
								hexes[0][0] = new Hex(0 + r + xOffset, 0 + yOffset, side, orientation);
								break;
							default:
								break;
						}

							

					}
					else
					{
						switch (orientation)
						{
							case 0:																									// Flat Vertice
								if (inLeftColumn)
								{
									// Calculate from hex above		
//									hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.FlatVertice.BottomLeft], side, orientation);
									hexes[i][j] = new Hex(hexes[i - 1][j].getPoints()[4], side, orientation);						// Constructor mit FlatVertice.BottomLeft			
								}	
								else
								{
									// Calculate from Hex to the left and need to stagger the columns
									if (j % 2 == 0)
									{
										// Calculate from Hex to left's Upper Right Vertice plus h and R offset 
//										float x = hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.UpperRight].X;
										float x = hexes[i][j - 1].getPoints()[1].x;														// FlatVertice.UpperRight X
//										float y = hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.UpperRight].Y;
										float y = hexes[i][j - 1].getPoints()[1].y;														// FlatVertice.UpperRight Y

										x += h;
										y -= r;
										
//										hexes[i, j] = new Hex(x, y, side, orientation);
										hexes[i][j] = new Hex(x,y, side, orientation);
									}
									else
									{
										// Calculate from Hex to left's Middle Right Vertice
//										hexes[i, j] = new Hex(hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.MiddleRight], side, orientation);		
										hexes[i][i] = new Hex(hexes[i][j - 1].getPoints()[2], side, orientation);						// Constructor mit FlatVertice.Middle Right
									}
								}
								break;
								
							case 1:																									// Pointy Vertice
								if (inLeftColumn)		
								{
									// Calculate from hex above and need to stagger the rows
									if (i % 2 == 0)
									{
									//	hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.PointyVertice.BottomLeft], side, orientation);
										hexes[i][j] = new Hex(hexes[i - 1][j].getPoints()[4], side, orientation);						// Constructor mit PointyVertice.BottomLeft	
									}
									else
									{
									//	hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.PointyVertice.BottomRight], side, orientation);
										hexes[i][j] = new Hex(hexes[i - 1][j].getPoints()[2], side, orientation);						// Constructor mit PointyVertice.BottomRight	
									}

								}
								else
								{
									// Calculate from Hex to the left
//									float x = hexes[i, j - 1].Points[(int)Hexagonal.PointyVertice.UpperRight].X;
									float x = hexes[i][j - 1].getPoints()[1].x;																// PointyVertice.UpperRight X
//									float y = hexes[i, j - 1].Points[(int)Hexagonal.PointyVertice.UpperRight].Y;
									float y = hexes[i][j - 1].getPoints()[1].y;																// PointyVertice.UpperRight Y
									
									
									x += r;
									y -= h;
									
					//				hexes[i, j] = new Hex(x, y, side, orientation);	
									hexes[i][j] = new Hex(x, y,side, orientation);
								}
								break;
							default:
								break;
						}


					}


				}
			}


			
		}

		public boolean PointInBoardRectangle(PVector point)
		{
			return PointInBoardRectangle((int)point.x,(int)point.y);
		}

		public boolean PointInBoardRectangle(int x, int y)
		{
			//
			// Quick check to see if X,Y coordinate is even in the bounding rectangle of the board.
			// Can produce a false positive because of the staggerring effect of hexes around the edge
			// of the board, but can be used to rule out an x,y point.
			//
			
			int topLeftX = 0 + getXOffSet();
			int topLeftY = 0 + getYOffSet();
			float bottomRightX = topLeftX + getPixelWidth();
			float bottomRightY = topLeftY + getPixelHeight();


			if (x > topLeftX && x < bottomRightX && y > topLeftY && y < bottomRightY)
			{
				return true;
			}
			else 
			{
				return false;
			}

		}
		
		

//		public Hex FindHexMouseClick(PVector point)
//		{
//			return FindHexMouseClick((int)point.x,(int)point.y);
//		}

//		public Hex FindHexMouseClick(int x, int y)
//		{
//			Hex target = null;
//
//			if (PointInBoardRectangle(x, y))
//			{
//				for (int i = 0; i < hexes.length; i++)
////					for (int i = 0; i < hexes.GetLength(0); i++)
//				{
//					for (int j = 0; j < hexes[i].length; j++)
////					for (int j = 0; j < hexes.GetLength(1); j++)
//
//					{
//						if (Math.InsidePolygon(hexes[i][j].getPoints(), 6, new PVector(x,y)))
//						{
//							target = hexes[i][j];
//							break;
//						}
//					}
//
//					if (target != null)
//					{
//						break;
//					}
//				}
//
//			}
//			
//			return target;
//			
//		}

		public boolean findHexMouseClick(int x, int y)
		{
			boolean target = false;

				for (int i = 0; i < hexes.length; i++)
//					for (int i = 0; i < hexes.GetLength(0); i++)
				{
					for (int j = 0; j < hexes[i].length; j++)
//					for (int j = 0; j < hexes.GetLength(1); j++)

					{
						if (Math.InsidePolygon(hexes[i][j].getPoints(), 6, new PVector(x,y)))
						{
							target = true;
							break;
						} else {
							target = false;
							break;
						}
					}
			
				}

				
			return target;
			
		}
		

	}

