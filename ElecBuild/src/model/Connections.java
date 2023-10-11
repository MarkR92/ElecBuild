package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Connections {
	
	private Point p1;
	private Point p2;
	private Color color;
	private Color color2;
	private int live;
	public Connections(Point p1, Point p2,int live)
	{
		this.p1=p1;
		this.p2=p2;
		this.color=Color.BLACK;
		this.color2=Color.RED;
		this.live=live;
		
	}
	public void drawConnection(Graphics2D g2d)
	{
		if(live==0)
		{
			g2d.setColor(color2);
		}
		else
		{
			g2d.setColor(color);
		}
		
		if(p1.x !=p2.x)
		{
			g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
			g2d.drawLine(p2.x, p1.y, p2.x, p1.y-(p1.y-p2.y));
		}
		else {
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
		
	}
	public void changeColor()
	{
		color=Color.red;
	}

}
