package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Wire {
	
	private Point current ;
	private Point last;
	private Point adjustedcurrent;
	private ArrayList<Point> startList=new ArrayList<>();
	private ArrayList<Point> endList=new ArrayList<>();
	
	ArrayList<Point> dots=new ArrayList<>();
	
	public Wire() {
		
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
		public void drawWire(Graphics2D g2d)
		{
			try {
				for(int i=0;i<dots.size();i++)
				{
					g2d.drawLine(dots.get(i).x, dots.get(i).y, dots.get(i+1).x, dots.get(i+1).y);
				}
				
			}catch( Exception e){
				
			}
			
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
		public Point getAdjustedCurrent() {
			return adjustedcurrent;
		}
		private void setAdjustedCurrent(Point point) {
			this.adjustedcurrent=point;
			
		}
		public void addWireStartToList(Point start)
		{
			startList.add(start);
		}
		public void addWireEndToList(Point end)
		{
			endList.add(end);
		}
		public void addDots(Point dot)
		{
			this.dots.add(dot);
		}

	 

}
