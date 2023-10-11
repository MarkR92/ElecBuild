package model;

import java.awt.Graphics2D;
import java.awt.Point;

public class AND extends Part {

	private static final long serialVersionUID = -5197962030481972850L;

	
	public AND(Point location,int partID ) {
		super(location,"AND","Boolean",partID);
	}
	
//Drawing
//----------------------------------------------//
	public void drawAND(Graphics2D g2d) 
	{
		

		switch(inputConnection)
		{
			case 2:drawAND2(g2d);break;
			case 3:drawAND3(g2d);break;
			case 4:drawAND4(g2d);break;
			case 5:drawAND5(g2d);break;
		
			default:System.out.println("does not exist");
		
		
		}
			g2d.drawLine(location.x-10, location.y-10, location.x-10, location.y+10);
			g2d.drawLine(location.x-10, location.y-10, location.x, location.y-10);
			g2d.drawLine(location.x-10, location.y+10, location.x, location.y+10);
			g2d.drawArc(location.x-10, location.y-10, 20, 20, -90, 180);
			
			drawWire(g2d);
		
			

 	}
//	private void drawNAND(Graphics2D g2d) {
//		drawInput1(g2d);
//		drawInput2(g2d);
//		
//		if(output==0)
//		{
//			g2d.setColor(normalColor);
//			g2d.drawLine(location.x+15, location.y, location.x+20, location.y);
//				
//		}
//		else
//		{	
//			g2d.setColor(liveColor);
//			g2d.drawLine(location.x+15, location.y, location.x+20, location.y);
//		}
//			g2d.setColor(normalColor);
//			
//		if(showInput1)
//		{
//			g2d.drawOval(location.x-17, location.y-7, 4, 4);
//		}
//		else if(showInput2)
//		{
//			g2d.drawOval(location.x-17, location.y+3, 4, 4);
//		}
//		else if(showOutput)
//		{
//			g2d.drawOval(location.x+18, location.y-2, 4, 4);
//		}
//		
//		
//		g2d.drawLine(location.x-10, location.y-10, location.x-10, location.y+10);
//		g2d.drawLine(location.x-10, location.y-10, location.x, location.y-10);
//		g2d.drawLine(location.x-10, location.y+10, location.x, location.y+10);
//		g2d.drawArc(location.x-10, location.y-10, 20, 20, -90, 180);
//		
//			g2d.drawOval(location.x+10, location.y-2, 4, 4);
//			
//			drawWire(g2d);
//		
//		
//	}
	public void drawAND2(Graphics2D g2d) 
	{
		drawInput2(g2d);
		drawOutputConnection(g2d);
		
			if(showInput1)
			{
				g2d.drawOval(location.x-17, location.y-7, 4, 4);
			}
			else if(showInput2)
			{
				g2d.drawOval(location.x-17, location.y+3, 4, 4);
			}
			else if(showOutput)
			{
				g2d.drawOval(location.x+13, location.y-2, 4, 4);
			}
	
 	}
	
	public void drawAND3(Graphics2D g2d) 
	{
		drawInput3(g2d);
		drawOutputConnection(g2d);
	

		if(showInput1)
		{
			g2d.drawOval(location.x-17, location.y-7, 4, 4);
		}
		else if(showInput2)
		{
			g2d.drawOval(location.x-17, location.y+3, 4, 4);
		}
		else if(showInput3)
		{
			g2d.drawOval(location.x-17, location.y-2, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+13, location.y-2, 4, 4);
		}
	

		
			

 	}
	
	public void drawAND4(Graphics2D g2d) 
	{
		drawInput4(g2d);
		drawOutputConnection(g2d);
	
		
		
			
		if(showInput1)
		{
			g2d.drawOval(location.x-17, location.y-12, 4, 4);
		}
		else if(showInput2)
		{
			g2d.drawOval(location.x-17, location.y-7, 4, 4);
		}
		else if(showInput3)
		{
			g2d.drawOval(location.x-17, location.y+3, 4, 4);
		}
		else if(showInput4)
		{
			g2d.drawOval(location.x-17, location.y+8, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+13, location.y-2, 4, 4);
		}
		
		
			
		
		
		
			//g2d.drawLine(location.x-10, location.y-10, location.x-10, location.y+10);
			
		//	drawWire(g2d);
		
			

 	}
	
	public void drawAND5(Graphics2D g2d) 
	{
		drawInput5(g2d);
		drawOutputConnection(g2d);
		
		
		if(showInput1)
		{
			g2d.drawOval(location.x-17, location.y-12, 4, 4);
		}
		else if(showInput2)
		{
			g2d.drawOval(location.x-17, location.y-7, 4, 4);
		}
		else if(showInput3)
		{
			g2d.drawOval(location.x-17, location.y-2, 4, 4);
		}
		else if(showInput4)
		{
			g2d.drawOval(location.x-17, location.y+3, 4, 4);
		}
		else if(showInput4)
		{
			g2d.drawOval(location.x-17, location.y+10, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+13, location.y-2, 4, 4);
		}
			
		
		
			
		
		
		

			
		//	drawWire(g2d);
		
			

 	}
	



}
