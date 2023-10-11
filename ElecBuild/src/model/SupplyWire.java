package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class SupplyWire extends Parts {

	String type;
	
	public SupplyWire(Point location,String type)
	{
		super(location);
		this.type=type;
	}
	@Override
	public void drawComponent(Graphics2D g2d) 
	{
		if(type.equals("Live"))
		{
			g2d.setColor(liveColor);
			//g2d.drawRect(location.x-3, location.y, location.x+1, 1000);
		
		}
		else
		{
			g2d.setColor(normalColor);
			//g2d.drawRect(location.x-3, 0, 6, 1000);
		}
		
		//g2d.drawLine(location.x, location.y, location.x, 1000);
}
		
	
}
