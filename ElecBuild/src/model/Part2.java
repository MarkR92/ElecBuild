package model;

import java.awt.Graphics2D;
import java.awt.Point;

public class Part2 {
	
	public boolean wasVisited;
	public int partID;		
	protected Point location;
	private static int lastPartID=0;
	
	protected int output=0;
	
	public Part2(Point location)
	{
		
		this.location=location;
		this.partID=lastPartID;
		lastPartID++;
		wasVisited = false;
		
	}
	public void drawPart(Graphics2D g2d)
	{
		g2d.drawOval(location.x, location.y, 4, 4);
		
	}

}
