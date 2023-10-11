package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Parts3 {
	
	private Color color;
	private Color color2;
	private String type;
	private Point location;
	private Boolean highlighted=false;
	private Boolean selected=false;
	//private Boolean toAdd=true;
	private Point wireStart;
	private int wireEnd;
	private Boolean showTop=false;
	private Boolean showBottom=false;
	private Boolean topInputSelected=false;
	private Boolean bottomInputSelected=false;
	private Boolean outputSelected=false;
	private Boolean isconnectedtop=false;
	private Boolean isconnectedbottom=false;
	private int partID;
	private int input1=0;
	private int input2=0;
	private int output=0;
	
	private static int lastPartID=100;
	private int [] connections= {0,0,0};
	private int x;
	private int y;

	
	
	
	public Parts3(Point location,String type)
	{
		this.type=type;
		this.location=location;
		this.x=20;
		this.y=20;
		
		this.color = Color.BLACK;
		this.color2 = Color.RED;
		
		
		connections[0]=0;
		connections[2]=0;
		this.partID=lastPartID;
		lastPartID++;
	}
	public Parts3(String type, int output)
	{
		this.type=type;
		this.location=new Point(10,0);
		this.x=10;
		this.y=600;
		this.color = Color.BLACK;
		this.color2 = Color.RED;
		connections[0]=0;
		connections[2]=0;
		this.partID=lastPartID;
		this.output=output;
		
		lastPartID++;
		
	}
	public Parts3(Point start,Point end,int outputConnection,int inputConnection)
	{
		this.type="Wire";
		this.location=start;
		this.x=end.x;
		this.y=end.y;
		this.color = Color.BLACK;
		this.color2 = Color.RED;
		connections[0]=inputConnection;
		connections[2]=outputConnection;
		this.partID=lastPartID;
		lastPartID++;
		
	}
	public int getPartId()
	{
		return this.partID;
		
	}
	
	public int[] getConnection()
	{
		//System.out.println("----"+partID+"----");
		for( int i=0;i<3;i++)
		{
			//System.out.println(connections[i]);
		}
		
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
	public void setInputConnection(int connection)
	{
		connections[0]=connection;
	}
	public void setOutputConnection(int connection)
	{
		connections[2]=connection;
	}


	public Rectangle2D getBoundsLive()
	{
		return new Rectangle2D.Double(5, 0, 5, 600);
		
		
	}
	
	public void setWireStart(Point start)
	{
		this.wireStart=start;
		
	}
	public void setWireEnd(int endx)
	{
		this.wireEnd=endx;
	}
	
	public void drawComponent(Graphics2D g2d) {
		//drawSupplyLine(g2d);
		
		g2d.setColor(color);
		if(highlighted)
		{
			//g2d.drawRect(location.x-10, location.y-10, x, y);
			
		}
		if(type.equals("Live")) {
			g2d.setColor(Color.RED);
			g2d.drawLine(5, 0, 5, y);
			//g2d.drawRect(3, 0, 1, 600);
		}
		if(type.equals("Ground")) {
			g2d.setColor(Color.BLACK);
			g2d.drawLine(600-6, 0, 600-6, 600);
		
		}
		
		if (type.equals("AND")) {
			if(input1==0)
			{
				g2d.setColor(color);
				g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
			}
			else
			{	
				g2d.setColor(color2);
				g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
				
			}
			if(input2==0)
			{
				g2d.setColor(color);
				g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
			}
			else
			{	
				g2d.setColor(color2);
				g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
				
			}
			if(input1==0 || input2==0)
			{
				g2d.setColor(color);
				g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
				
			}
			else
			{	
				g2d.setColor(color2);
				g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
				setOutput(1);
			}
			g2d.setColor(color);
			
			//g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
			if(showTop)
			{
				g2d.drawOval(location.x-17, location.y-7, 4, 4);
			}
			else if(showBottom)
			{
				g2d.drawOval(location.x-17, location.y+3, 4, 4);
			}
			
			g2d.drawRect(location.x-10, location.y-10, 20, 20);
			
			g2d.drawString("A", location.x-4, location.y+5);
			//drawFixtureFixed(g2d);
		} else if (type.equals("OR")) {
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
			g2d.drawRect(location.x-10, location.y-10, 20, 20);
			g2d.drawString("R", location.x-4, location.y+5);
			//drawFixturePinned(g2d);
		} else if (type.equals("XOR")) {
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
			g2d.drawRect(location.x-10, location.y-10, 20, 20);
			g2d.drawString("XR", location.x-8, location.y+5);
			//drawFixtureSliding(g2d);
		} 
		else if (type.equals("NOT")) {
			//drawFixtureSliding(g2d);
			g2d.drawLine(location.x-15, location.y-5, location.x-10, location.y-5);
			g2d.drawLine(location.x-15, location.y+5, location.x-10, location.y+5);
			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
			g2d.drawRect(location.x-10, location.y-10, 20, 20);
			g2d.drawString("N", location.x-4, location.y+5);
		}
		else if(type.equals("LED")){
			g2d.drawOval(location.x-5, location.y-5, 10, 10);
			//g2d.setColor(color.gray);
		   g2d.fillOval(location.x-5, location.y-5, 10, 10);
		   //g2d.drawOval(location.x-5, location.y-5, 10, 10);
		}
		else if(type.equals("Wire")){
			if(connections[0]==100 || getInput1()==1)
			{
				g2d.setColor(Color.RED);
				
			}else
			{
				g2d.setColor(Color.BLACK);
				
			}
			g2d.drawLine(location.x, location.y, x, location.y);
		}
		else if(type.equals("Push Button")){
			
			if(!selected)
			{
					g2d.drawOval(location.x-5, location.y-5, 4, 4);
				    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-8);
				    g2d.drawOval(location.x+5, location.y-5, 4, 4);
			}
			else
			{
					g2d.drawOval(location.x-5, location.y-5, 4, 4);
				    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-5);
				    g2d.drawOval(location.x+5, location.y-5, 4, 4);
			}
			
		   
		}

	}

public void setSelected(boolean selected) {
    this.selected = selected;
    if (selected) {
    	
    	//System.out.println(fixturetype);
       // color = Color.BLACK;
    } else {
        //color = Color.RED;
    }  
 
}
public boolean isSelected() {
    return selected;
}



public void setLocation(int x, int y)
{
	this.location.x=x;
	this.location.y=y;
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
public Point getLocationLive()
{
	Point p = new Point(5, location.y-5);
	
	return p;
}
public boolean isHighlighted() {
    return highlighted;
}
public void setHighlighted(boolean highlighted) {
    this.highlighted = highlighted;
   
    
    if (highlighted) {
        color = Color.BLUE;
    }
    else
    {
    	color = Color.BLACK;	
    } 
}
public boolean getShowTopInput() {
    return showTop;
}
public void setShowTopInput(boolean showTop) {
    this.showTop=showTop;
}
public boolean getShowBottomInput() {
    return showBottom;
}
public void setShowBottomInput(boolean showBottom) {
    this.showBottom=showBottom;
}

	public Rectangle2D getBounds() {
	    return new Rectangle2D.Double(location.x-10, location.y-10, x, y);
	}
	public Rectangle2D getTopInputBounds() {
		
	    return new Rectangle2D.Double(location.x-17, location.y-7, 4, 4);
	}
	public Rectangle2D getBottomInputBounds() {
		
	
	    return new Rectangle2D.Double(location.x-17, location.y+3, 4, 4);
	}
	public Rectangle2D getOutputBounds() {
	
	    return new Rectangle2D.Double(location.x-17, location.y+3, 4, 4);
	}

	
	public boolean isConnectedTop()
	{
		return isconnectedtop;
	}
	public void setInput1(int input1)
	{
		this.input1=input1;
	}
	public int getInput1()
	{
		return input1;
	}
	public void setInput2(int input2)
	{
		this.input2=input2;
	}
	public int getInput2()
	{
		return input2;
	}
	public void setOutput(int output)
	{
		this.output=output;
	}
	public int getOutput()
	{
		return output;
	}
	public void setConnectedTop(boolean c)
	{
		this.isconnectedtop=c;
	}
	public boolean isConnectedBottom()
	{
		return isconnectedbottom;
	}
	public String getType() {
		
		return type;
	}
	
}


