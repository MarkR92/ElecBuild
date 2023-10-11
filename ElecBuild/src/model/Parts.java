package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Parts{
	
	private String type="";
	protected Point location;					//location (x,y) to be drawn on screen
	protected Boolean highlighted=false;  		//when mouse hovers over part highlight it
	protected Boolean selected=false;				//when mouse clicks over part select it

	protected Boolean showInputTop=false;    	//shows connection point of top input
	protected Boolean showInputBottom=false;	//shows connection point of bottom input
	protected Boolean showOutput=false;			//shows connection point of output
	
	private int partID;						//Unique part ID
	protected int inputTop=0;					//if top input is live
	protected int inputBottom=0;				//if bottom input is live
	protected int output=0;					//output of a part
	
	private static int lastPartID=100;
	protected int [] connections= {0,0,0};
	protected Color normalColor;
	protected Color liveColor;
//	private int x;
//	private int y;

	public Parts(Point location)
	{
		this.location=location;
		this.type = type;
		this.partID=lastPartID;
		lastPartID++;
		this.normalColor=Color.BLACK;
		this.liveColor=Color.RED;
		
	}
	public Parts(Point start,Point end)
	{
		this.location=start;
		this.partID=lastPartID;
		lastPartID++;
		this.normalColor=Color.BLACK;
		this.liveColor=Color.RED;
		
	}

	public String getType()
	{
		return type;
	}
	
	public Rectangle2D getTopInputBounds() 
	{
		
	    return new Rectangle2D.Double(location.x-17, location.y-7, 4, 4);
	}
	
	public Rectangle2D getBottomInputBounds()
	{
	    return new Rectangle2D.Double(location.x-17, location.y+3, 4, 4);
	}
	
	public Rectangle2D getOutputBounds() 
	{
	    return new Rectangle2D.Double(location.x+13, location.y-2, 4, 4);
	}
	
	public boolean getShowTopInput()
	{
	    return showInputTop;
	}
	
	public void setShowTopInput(boolean showTop) 
	{
	    this.showInputTop=showTop;
	}
	
	public boolean getShowBottomInput() 
	{
	    return showInputBottom;
	}
	public void setShowBottomInput(boolean showBottom) 
	{
	    this.showInputBottom=showBottom;
	}
	public boolean getShowOutput() 
	{
	    return showOutput;
	}
	public void setShowOutput(boolean showOutput) 
	{
	    this.showOutput=showOutput;
	}
	
	public int getPartId()
	{
		return this.partID;
		
	}
	public int[] getConnection()
	{
	
		return connections;
	}
	
	public void printConnection()
	{
		System.out.println("----"+partID+"----");
		for( int i=0;i<3;i++)
		{
			System.out.println(connections[i]);
		}
	
	}
	
	public void setInputTopConnection(int connection)
	{
		connections[0]=connection;
	}
	
	public void setInputBottomConnection(int connection)
	{
		connections[1]=connection;
	}
	
	
	public void setOutputConnection(int connection)
	{
		connections[2]=connection;
	}
	
	public void setSelected(boolean selected)
	{
	    this.selected = selected;
	    if (selected) {
	    	
	    	//System.out.println(fixturetype);
	       // color = Color.BLACK;
	    } else {
	        //color = Color.RED;
	    }  
	 
	}
	
	public boolean isSelected()
	{
	    return selected;
	}
	
	public void setHighlighted(boolean highlighted)
	{
	    this.highlighted = highlighted;
	    if (highlighted)
	    {
	    	normalColor = Color.BLUE;
	    	liveColor = Color.BLUE;
	    }
	    else
	    {
	    	normalColor = Color.BLACK;	
	    	liveColor = Color.RED;
	    } 
	}
	
	public boolean isHighlighted()
	{
	    return highlighted;
	}
	
	public void setInputTop(int inputTop)
	{
		this.inputTop=inputTop;
	}
	
	public int getInputTop()
	{
		return inputTop;
	}
	
	public void setInputBottom(int inputBottom)
	{
		this.inputBottom=inputBottom;
	}
	
	public int getInputBottom()
	{
		return inputBottom;
	}
	public Rectangle2D getBounds() 
	{
	    return null;
	}
	public void setOutput(int output)
	{
		this.output=output;
	}
	
	public int getOutput()
	{
		return output;
	}
	
	public Point getLocationTop()
	{
		Point p = new Point(location.x-15, location.y-5);
		
		return p;
	}
	public Point getLocationBottom()
	{
		Point p = new Point(location.x-15, location.y+5);
		
		return p;
	}
	public Point getLocationOutput()
	{
		Point p = new Point(location.x+16, location.y);
		
		return p;
	}
	public void drawComponent(Graphics2D g2d) 
	{
		
		
		//	g2d.drawRect(location.x-10, location.y-10, 20, 20);
			
		
		

 	}
	public Rectangle2D getBoundsLive() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
