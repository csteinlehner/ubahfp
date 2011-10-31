package hexmap;

import processing.core.*;

public class HexState {
	
	private int backgroundColor;	

	public void setBackgroundColor(int value){
		backgroundColor = value;
	}
	
	public int getBackgroundColor(){
		return backgroundColor;
	}	

	public HexState()
	{
		this.backgroundColor = 255;
	}

}
