package model;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Switch extends Parts{
	private String type;
	private int selectedcount=0;
	
	public Switch(Point location)
	{
		super(location);
		this.type="Switch";
	}
	@Override
	public Rectangle2D getBounds() 
	{
	    return new Rectangle2D.Double(location.x, location.y-10, 4, 10);
	}
	@Override
	public Point getLocationTop()
	{
		Point p = new Point(location.x-3, location.y-3);
		
		return p;
	}
	@Override
	public Point getLocationOutput()
	{
		Point p = new Point(location.x+7, location.y-3);
		
		return p;
	}
	@Override
	public void drawComponent(Graphics2D g2d)
	{
		if(getOutput()==0)
		{
			g2d.setColor(normalColor);
		}else
		{
			g2d.setColor(liveColor);
		}
		
		if(selected)
		{
			selectedcount++;
			selected=false;
			setOutput(1);
			System.out.println(selectedcount);
		}
		if(selectedcount==0 |selectedcount==2)
		{
				g2d.drawOval(location.x-5, location.y-5, 4, 4);
			    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-8);
			    g2d.drawOval(location.x+5, location.y-5, 4, 4);
			    //g2d.drawRect(location.x, location.y-10, 4, 10);
		}
		else
		{
				g2d.drawOval(location.x-5, location.y-5, 4, 4);
			    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-5);
			    g2d.drawOval(location.x+5, location.y-5, 4, 4);
		}
		if(selectedcount==2)
		{
			selectedcount=0;
			setOutput(0);
		}
		
		if(showInputTop)
		{
			g2d.fillOval(location.x-5, location.y-5, 4, 4);
		}
		else if(showOutput)
		{
			g2d.fillOval(location.x+5, location.y-5, 4, 4);
		}
	}
	
	@Override
	public Rectangle2D getTopInputBounds() 
	{
		
	    return new Rectangle2D.Double(location.x-5, location.y-5, 4, 4);
	}
	@Override
	public Rectangle2D getOutputBounds() 
	{
	    return new Rectangle2D.Double(location.x+5, location.y-5, 4, 4);
	}
	

	

}
