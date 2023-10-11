package model;

import java.awt.Graphics2D;
import java.awt.Point;

public class NOT extends Part {


	private static final long serialVersionUID = -4165873233904286336L;

	public NOT(Point location,int partID )  {
		super(location,"NOT","Boolean",partID);
	}
	public void drawNOT(Graphics2D g2d)
	{
		drawTopNot(g2d);
		
		if(output==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x+14, location.y, location.x+19, location.y);
					
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x+14, location.y, location.x+19, location.y);
		}
		
		
		
		g2d.setColor(normalColor);
		g2d.drawOval(location.x+10, location.y-2, 4, 4);
		g2d.drawLine(location.x-10, location.y-10, location.x-10, location.y+10);
		g2d.drawLine(location.x-10, location.y+10, location.x+10, location.y);
		g2d.drawLine(location.x-10, location.y-10, location.x+10, location.y);
		
		if(showInput1)
		{
			g2d.drawOval(location.x-17, location.y-2, 4, 4);
		}
		
		else if(showOutput)
		{
			g2d.drawOval(location.x+17, location.y-2, 4, 4);
		}
		drawWire(g2d);
	}

}
