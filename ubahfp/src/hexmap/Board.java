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


//		public Hexagonal.Hex[,] Hexes
//		{
//			get
//			{
//				return hexes;
//			}
//			set
//			{
//			}
//		}
//
//		public float PixelWidth
//		{
//			get
//			{
//				return pixelWidth;
//			}
//			set
//			{
//			}
//		}
//
//		public float PixelHeight
//		{
//			get
//			{
//				return pixelHeight;
//			}
//			set
//			{
//			}
//		}
//
//		public int XOffset
//		{
//			get
//			{
//				return xOffset;
//			}
//			set
//			{
//			}
//		}
//
//		public int YOffset
//		{
//			get
//			{
//				return xOffset;
//			}
//			set
//			{
//			}
//		}
//
//		public int Width
//		{
//			get
//			{
//				return width;
//			}
//			set
//			{
//			}
//		}
//
//		public int Height
//		{
//			get
//			{
//				return height;
//			}
//			set
//			{
//			}
//		}
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
		
		private void Initialize(int width, int height, int side, int orientation, int xOffset, int yOffset)
		{
			this.width = width;
			this.height = height;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.side = side;
			this.orientation = orientation;
			hexes = new Hex[height][width]; //opposite of what we'd expect
			
			
			

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
					this.pixelWidth = (width * hexWidth) + h;
					this.pixelHeight = (height * hexHeight) + r;
					break;
				case 1:
					hexWidth = r + r;
					hexHeight = side + h;
					this.pixelWidth = (width * hexWidth) + r;
					this.pixelHeight = (height * hexHeight) + h;
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
			for (int i = 0; i < height; i++)
			{
				for (int j = 0; j < width; j++)
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

						if (i == height - 1)
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

						if (j == width - 1)
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
							case 0:
								if (inLeftColumn)
								{
									// Calculate from hex above
									
									hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.FlatVertice.BottomLeft], side, orientation);

									
//									hexes[i][j] = new Hex(hexes[i - 1][j].getPoints()[4], side, orientation);						// ohne enum
									
									
									
								}	
								else
								{
									// Calculate from Hex to the left and need to stagger the columns
									if (j % 2 == 0)
									{
										// Calculate from Hex to left's Upper Right Vertice plus h and R offset 
										float x = hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.UpperRight].X;
										float y = hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.UpperRight].Y;
										x += h;
										y -= r;
										hexes[i, j] = new Hex(x, y, side, orientation);
									}
									else
									{
										// Calculate from Hex to left's Middle Right Vertice
										hexes[i, j] = new Hex(hexes[i, j - 1].Points[(int)Hexagonal.FlatVertice.MiddleRight], side, orientation);
									}
								}
								break;
							case 1:
								if (inLeftColumn)
								{
									// Calculate from hex above and need to stagger the rows
									if (i % 2 == 0)
									{
										hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.PointyVertice.BottomLeft], side, orientation);
									}
									else
									{
										hexes[i, j] = new Hex(hexes[i - 1, j].Points[(int)Hexagonal.PointyVertice.BottomRight], side, orientation);
									}

								}
								else
								{
									// Calculate from Hex to the left
									float x = hexes[i, j - 1].Points[(int)Hexagonal.PointyVertice.UpperRight].X;
									float y = hexes[i, j - 1].Points[(int)Hexagonal.PointyVertice.UpperRight].Y;
									x += r;
									y -= h;
									hexes[i, j] = new Hex(x, y, side, orientation);	
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
			return PointInBoardRectangle(point.x,point.y);
		}

		public boolean PointInBoardRectangle(int x, int y)
		{
			//
			// Quick check to see if X,Y coordinate is even in the bounding rectangle of the board.
			// Can produce a false positive because of the staggerring effect of hexes around the edge
			// of the board, but can be used to rule out an x,y point.
			//
			int topLeftX = 0 + XOffset;
			int topLeftY = 0 + yOffset;
			float bottomRightX = topLeftX + pixelWidth;
			float bottomRightY = topLeftY + PixelHeight;


			if (x > topLeftX && x < bottomRightX && y > topLeftY && y < bottomRightY)
			{
				return true;
			}
			else 
			{
				return false;
			}

		}

		public Hex FindHexMouseClick(System.Drawing.Point point)
		{
			return FindHexMouseClick(point.X,point.Y);
		}

		public Hex FindHexMouseClick(int x, int y)
		{
			Hex target = null;

			if (PointInBoardRectangle(x, y))
			{
				for (int i = 0; i < hexes.GetLength(0); i++)
				{
					for (int j = 0; j < hexes.GetLength(1); j++)
					{
						if (Math.InsidePolygon(hexes[i, j].Points, 6, new System.Drawing.PointF(x, y)))
						{
							target = hexes[i, j];
							break;
						}
					}

					if (target != null)
					{
						break;
					}
				}

			}
			
			return target;
			
		}

		

	}

