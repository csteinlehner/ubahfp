package hexmap;

import processing.core.*;

public class HexMap extends PApplet{
	
	private Board board;
	private float boardPixelWidth;
	private float boardPixelHeight;
	private int boardXOffset;
	private int boardYOffset;
	
	public void setup(){
		
	size(400, 400);	
	
	board = new Board(10,10,20, 1);
		
	}
	
	
	public void draw(){
		
		background(0);
		
		//
		// Draw Hex Grid
		//
				
		for (int i = 0; i < board.getHexes().length; i++)
		{
		
			
			
			for (int j = 0; j < board.getHexes()[i].length; j++)
			{
				//bitmapGraphics.DrawPolygon(p, board.Hexes[i, j].Points);
				//bitmapGraphics.FillPolygon(new SolidBrush(board.Hexes[i,j].HexState.BackgroundColor), board.Hexes[i, j].Points);
				
				drawPolygon(board.getHexes()[i][j].points);
				
						
			}
		}

		//
		// Draw Active Hex, if present
		//
//		if (board.BoardState.ActiveHex != null)
//		{
//			p.Color = board.BoardState.ActiveHexBorderColor;
//			p.Width = board.BoardState.ActiveHexBorderWidth;
//			bitmapGraphics.DrawPolygon(p, board.BoardState.ActiveHex.Points);
//		}
		
	}

	
	private void Initialize(Board board, int xOffset, int yOffset)
	{
		this.board = board;
		this.boardXOffset = xOffset;
		this.boardYOffset = yOffset;
	}
	
	
	private void drawPolygon(PVector[] hexPoints){
				
	stroke(255);
	
	
	// should check if mouse over polygon
	if(board.findHexMouseClick(mouseX, mouseY)){
	fill(255);
	System.out.println("hit");		
	}
	else{
	fill(0);	
	}
	// ---
		
	beginShape();	
	vertex(hexPoints[0].x, hexPoints[0].y);	
	for(int i = 1; i < hexPoints.length; i++){	
	vertex(hexPoints[i].x, hexPoints[i].y);	
	}	
	vertex(hexPoints[0].x, hexPoints[0].y);	
	endShape();
	
	}
	

	
}
