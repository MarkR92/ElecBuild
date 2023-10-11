package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class LED extends Parts {
	private Color onColor;
	public LED(Point location)
	{
		super(location);
		this.onColor=Color.GREEN;
	}
	@Override
	public void drawComponent(Graphics2D g2d)
	{
		g2d.drawLine(location.x-5, location.y,location.x-10, location.y);
		g2d.drawLine(location.x+10, location.y,location.x+5, location.y);
		
		if(getOutput()==0)
		{
			g2d.setColor(normalColor);
		}else
		{
			g2d.setColor(onColor);
			g2d.fillOval(location.x-5, location.y-5, 10, 10);
		}
		if(inputTop==0)
		{	g2d.setColor(normalColor);
			g2d.drawOval(location.x-5, location.y-5, 10, 10);
			
		}
		else
			
		{	
			g2d.setColor(onColor);
			g2d.fillOval(location.x-5, location.y-5, 10, 10);
		}
		if(showInputTop)
		{
			g2d.drawOval(location.x-12, location.y-2, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+8, location.y-2, 4, 4);
		}
		
	
	}
	@Override
	public Rectangle2D getBounds() 
	{
	    return new Rectangle2D.Double(location.x-5, location.y-5, 10, 10);
	}
	@Override
	public Rectangle2D getTopInputBounds() 
	{
		
	    return new Rectangle2D.Double(location.x-12, location.y-2, 4, 4);
	}
	@Override
	public Rectangle2D getOutputBounds() 
	{
	    return new Rectangle2D.Double(location.x+8, location.y-2, 4, 4);
	}
	
	@Override
	public Point getLocationTop()
	{
		Point p = new Point(location.x-10, location.y);
		
		return p;
	}
	@Override
	public Point getLocationOutput()
	{
		Point p = new Point(location.x+10, location.y);
		
		return p;
	}
}
