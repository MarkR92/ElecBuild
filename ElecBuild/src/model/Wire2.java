package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Wire2 extends Parts{
	public Wire2(Point location) {
		super(location);
		// TODO Auto-generated constructor stub
	}
	private Point end;
	private Point start;
	private String type;
	private String connectedTo;
	
	
	@Override
	public Rectangle2D getBounds() 
	{
	    return new Rectangle2D.Double(end.x, start.y-2, start.x-5, 4);
	}
	@Override
	public void drawComponent(Graphics2D g2d) 
	{
		if(end.x==5)
		{
			if(inputTop==0)
			{
				g2d.setColor(normalColor);
				g2d.drawLine(location.x, location.y, end.x, location.y);
				//setOutput(0);
			}
			else
			{
				g2d.setColor(liveColor);
				g2d.drawLine(location.x, location.y, end.x, location.y);
				//setOutput(1);
				
			}
		}
		else
		{
			if(inputTop==0)
			{
				g2d.setColor(normalColor);
				g2d.drawLine(location.x, location.y, end.x, end.y);
				setOutput(0);
			}else
			{
				g2d.setColor(liveColor);
				g2d.drawLine(location.x, location.y, end.x, end.y);
				setOutput(1);
			}
			
		}
		
		
		//g2d.drawRect(end.x, location.y-2, location.x-5, 4);
	}
	@Override
	public String getType()
	{
		return type;
	}
	public String getConnectedTo()
	{
		
		return connectedTo;
	}
	public void setConnectedTo(String connectedTo)
	{
		
		this.connectedTo=connectedTo;
	}
	@Override
	public void setInputTop(int inputTop)
	{
		this.inputTop=inputTop;
	}
	@Override
	public void setOutput(int output)
	{
		this.output=output;
	}

}
