package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class TempWire {

	 Point current ;
	 Point last;
	 
	 
	 public TempWire() {
			
		}
	public TempWire(Point current, Point last) {
		this.current=current;
		this.last=last;
	}
	public void drawTempWire(Graphics2D g2d)
	{
		//System.out.println(getAngle());
		g2d.setColor( Color.GRAY);
		if(getAngle()>=0 & getAngle()<45)
		{
			g2d.drawLine(current.x,last.y, last.x, last.y);
			
		}else if(getAngle()>=45 |getAngle()<0)
		{
			g2d.drawLine(last.x,current.y, last.x, last.y);
			
		}
//	
		
	}


	public double getDeltaX() {
		
		return (double)((-last.x+current.x)/10)/2; // dividing by 10 then 2 converts from pixels to meters
	}

	public double getDeltaY() {
		
		return (double)((last.y-current.y)/10)/2; // dividing by 10 then 2 converts from pixels to meters
	}

	public double getLength() {
		
		double x = getDeltaX()*getDeltaX();
		double y = getDeltaY()*getDeltaY();
		double l = (Math.sqrt(x+y));

		return Math.round(l *100.0 )/ 100.0;

	}
	public double getAngle() {
		
		double angle=Math.toDegrees((Math.asin((getDeltaY())/getLength())));
		return Math.round(angle*10.0)/10.0;

	}

	 public Point getCurrent() {
			return current;
		}
		public void setCurrent(Point current) {
			this.current = current;
		}
		public Point getLast() {
			return last;
		}
		public void setLast(Point last) {
			this.last = last;
		}
}
