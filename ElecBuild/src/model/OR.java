package model;

import java.awt.Graphics2D;
import java.awt.Point;

public class OR extends Part {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2857569478145060030L;

	public OR(Point location,int partID ) {
		super(location,"OR","Boolean",partID);
	}
	
//Drawing
//----------------------------------------------//
	public void drawOR(Graphics2D g2d) 
	{
		///drawInputConnection(g2d);

		switch(inputConnection)
		{
			case 2:drawOR2(g2d);break;
			case 3:drawOR3(g2d);break;
			case 4:drawOR4(g2d);break;
			case 5:drawOR5(g2d);break;
		
			default:System.out.println("does not exist");
		
		
		}
		g2d.drawLine(location.x-13, location.y-10, location.x-8, location.y-10);
		
		g2d.drawLine(location.x-13, location.y+10, location.x-8, location.y+10);
			
		g2d.drawArc(location.x-32, location.y-12, 24, 24, -56, 110);
		g2d.drawArc(location.x-28, location.y-10, 40, 40, 30, 58);
		g2d.drawArc(location.x-28, location.y-30, 40, 40, -90, 58);
			
			drawWire(g2d);
		
			

 	}
	private void drawNOR(Graphics2D g2d) {
		//drawInput1(g2d);
		drawInput2(g2d);
		
		if(output==0)
		{
			g2d.setColor(normalColor);	
			g2d.drawLine(location.x+15, location.y, location.x+20, location.y);
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x+15, location.y, location.x+20, location.y);
			
		}
		
		g2d.setColor(normalColor);
			
		g2d.drawLine(location.x-13, location.y-10, location.x-8, location.y-10);
			
		g2d.drawLine(location.x-13, location.y+10, location.x-8, location.y+10);
			
		g2d.drawArc(location.x-32, location.y-12, 24, 24, -56, 110);
		g2d.drawArc(location.x-28, location.y-10, 40, 40, 30, 58);
		g2d.drawArc(location.x-28, location.y-30, 40, 40, -90, 58);
		
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
			g2d.drawOval(location.x+18, location.y-2, 4, 4);
		}
		g2d.drawOval(location.x+10, location.y-2, 4, 4);
		drawWire(g2d); 
		
	}
	public void drawOR2(Graphics2D g2d) 
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
	public void drawInput2(Graphics2D g2d)
	{
		if(input1Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		if(input2Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
	}
	public void drawOR3(Graphics2D g2d) 
	{
		drawInput3(g2d);
		drawOutputConnection(g2d);
	

		if(showInput1)
		{
			g2d.drawOval(location.x-17, location.y-7, 4, 4);
		}
		else if(showInput2)
		{
			g2d.drawOval(location.x-17, location.y-2, 4, 4);
		}
		else if(showInput3)
		{
			g2d.drawOval(location.x-17, location.y+3, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+13, location.y-2, 4, 4);
		}
	

		
			

 	}
	public void drawInput3(Graphics2D g2d)
	{
		if(input1Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		if(input2Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
		if(input3Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
	}
	public void drawOR4(Graphics2D g2d) 
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
	public void drawInput4(Graphics2D g2d)
	{
		if(input1Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-10, location.x-10, location.y-10);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-10, location.x-10, location.y-10);
				
		}
		if(input2Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		if(input3Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		if(input4Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+10, location.x-10, location.y+10);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+10, location.x-10, location.y+10);
				
		}
	}
	public void drawOR5(Graphics2D g2d) 
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
	public void drawInput5(Graphics2D g2d)
	{
		if(input1Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-10, location.x-10, location.y-10);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-10, location.x-10, location.y-10);
				
		}
		if(input2Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
		}
		if(input3Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
		if(input4Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
		}
		if(input5Result==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y+10, location.x-10, location.y+10);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y+10, location.x-10, location.y+10);
				
		}
	}

	

}


