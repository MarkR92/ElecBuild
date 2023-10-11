package model;

import java.awt.Graphics;
import java.awt.Point;

public class ComponentConnections {

	private Point start= new Point() ;
	private Point end= new Point() ;
	public ComponentConnections(Point start,Point end) {
		this.start=start;
		this.end=end;

	}
	
	public void drawConnection(Graphics g2d)
	{
		g2d.drawLine(start.x,start.y,end.x,end.y);
	}
}
