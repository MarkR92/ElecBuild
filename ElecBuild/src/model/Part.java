package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Part implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7752049517572448098L;
	public boolean wasVisited;
	public int partID;		
	private String type;
	private String componentType;
	protected Point location;
	
	private static int lastPartID=0;
	
	private int input1ConnectionID=-1;
	private int input2ConnectionID=-1;
	private int input3ConnectionID=-1;
	private int input4ConnectionID=-1;
	private int input5ConnectionID=-1;
	
	protected int input1Result=0;
	protected int input2Result=0;
	protected int input3Result=0;
	protected int input4Result=0;
	protected int input5Result=0;
	
	protected int output=0;
	
	
	
	protected Color normalColor=Color.BLACK;
	protected Color liveColor=Color.RED;
	private Color normalWireColor=Color.BLACK;
	private Color liveWireColor=Color.RED;
	
	private boolean showInputTop=false;
	private boolean showInputBottom=false;
	protected boolean showInput1=false;
	protected boolean showInput2=false;
	protected boolean showInput3=false;
	protected boolean showInput4=false;
	protected boolean showInput5=false;
	protected boolean showOutput=false;
	
	private boolean highlighted=false;
	private int selectedcount;
	private boolean selected=false;
	private Boolean isswitchclosed=false;
	private Color onColor=Color.GREEN;
	private Color offColor=Color.GRAY;

	private ArrayList<Point> wireStart= new ArrayList<>();
	private ArrayList<Point> wireEnd= new ArrayList<>();
	private ArrayList<String> currentSelectedInput= new ArrayList<>();
	
	
	protected int inputConnection=2;
	private ArrayList<Wire> wireList= new ArrayList<>();;
	
// -------------------------------------------------------------
	// constructors start
	public Part(Point location, String type,String componentType ,int partID) 
	{
		this.location=location;
		this.type = type;
		this.componentType=componentType;
		this.partID=partID;
		wasVisited = false;
		
	}
	public Part(Point location,String type,int partID) 
	{
		this.location=location;
		this.type = type;
		this.partID=partID;
		wasVisited = false;
	}

//	
//	
//	public Part()
//	{
//		
//		this.type="Wire";
//		this.partID=lastPartID;
//		lastPartID++;
//		wasVisited = false;
//		
//	}
	// constructors end
// -------------------------------------------------------------
	
	

	
	
// -------------------------------------------------------------
	//drawing parts start
	public void drawParts(Graphics2D g2d) 
	{
		 switch (type) {
		
		 //boolean parts
		// case "AND": 	drawAND(g2d); break;
         //case "OR":  	drawOR(g2d); break;
//         case "NOT":  	drawNOT(g2d); break;
//         case "XOR":  	drawXOR(g2d); break;
       //  case "NOT(Active Low)":  drawNOTLOW(g2d); break;
       //  case "NAND":  	drawNAND(g2d); break;
      //   case "NOR":  	drawNOR(g2d); break;
       
         //i/o parts
        // case "Input": 			drawInput(g2d); break;
        // case "Output":  		drawOutput(g2d); break;
        
         case "Switch": 		drawSwitch(g2d); break;
         case "LED":  			drawLED(g2d); break;
       //  case "SupplyLive": 	drawLiveWire(g2d); break;
     
         
         //default:System.out.println(type + " is not a recognised part");break;
		 }

	}
	public void setInputConnection(int num)
	{
		this.inputConnection=num;
	}
	public int getInputConnection()
	{
		return inputConnection;
	}
	
	
	



//	private void drawNOTLOW(Graphics2D g2d) {
//		drawTopNotLOW(g2d);
//		
//		if(output==0)
//		{
//			g2d.setColor(normalColor);
//			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
//					
//		}
//		else
//		{	
//			g2d.setColor(liveColor);
//			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
//		}
//		
//		
//		
//		g2d.setColor(normalColor);
//		g2d.drawOval(location.x-15, location.y-2, 4, 4);
//		g2d.drawLine(location.x-10, location.y-10, location.x-10, location.y+10);
//		g2d.drawLine(location.x-10, location.y+10, location.x+10, location.y);
//		g2d.drawLine(location.x-10, location.y-10, location.x+10, location.y);
//		
//		if(showInputTop)
//		{
//			g2d.drawOval(location.x-22, location.y-2, 4, 4);
//		}
//		
//		else if(showOutput)
//		{
//			g2d.drawOval(location.x+13, location.y-2, 4, 4);
//		}
//		drawWire(g2d);
//		
//	}


	

	
	public void drawWire(Graphics2D g2d)
	{
	

		if(output==0)
		{
		g2d.setColor(normalWireColor);
		}
		else
		{
			g2d.setColor(liveWireColor);
		}
		for(Wire wire:wireList)
		{
			
//			//System.out.println(wireList.size());
			wire.drawWire(g2d);
		}
		
		if(wireEnd.size()!=0)
		{
			for(int i=0;i<wireEnd.size();i++)
			{
				if(output==0)
				{
					g2d.setColor(normalWireColor);
				}
				else
				{
					g2d.setColor(liveWireColor);
				}
				
				
				if(wireStart.get(i).x <wireEnd.get(i).x & wireStart.get(i).y <wireEnd.get(i).y & currentSelectedInput.get(i).equals("Top"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y-10, wireEnd.get(i).x, wireStart.get(i).y-10);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y-10, wireEnd.get(i).x, wireEnd.get(i).y);
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y-10,wireStart.get(i).x, wireStart.get(i).y);
			
					
				}
				else if(wireStart.get(i).x <wireEnd.get(i).x & wireStart.get(i).y >wireEnd.get(i).y& currentSelectedInput.get(i).equals("Top"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y-10, wireEnd.get(i).x, wireStart.get(i).y-10);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y-10, wireEnd.get(i).x, wireEnd.get(i).y);
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y-10,wireStart.get(i).x, wireStart.get(i).y);
			
					
				}
				else if(wireStart.get(i).x !=wireEnd.get(i).x& currentSelectedInput.get(i).equals("Top"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y, wireEnd.get(i).x, wireStart.get(i).y);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y, wireEnd.get(i).x, wireStart.get(i).y-(wireStart.get(i).y-wireEnd.get(i).y));
				}
				
				else if(wireStart.get(i).x <wireEnd.get(i).x & wireStart.get(i).y <wireEnd.get(i).y & currentSelectedInput.get(i).equals("Bottom"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y+10, wireEnd.get(i).x, wireStart.get(i).y+10);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y+10, wireEnd.get(i).x, wireEnd.get(i).y);
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y+10,wireStart.get(i).x, wireStart.get(i).y);
//			
					
				}
				else if(wireStart.get(i).x <wireEnd.get(i).x & wireStart.get(i).y >wireEnd.get(i).y& currentSelectedInput.get(i).equals("Bottom"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y+10, wireEnd.get(i).x, wireStart.get(i).y+10);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y+10, wireEnd.get(i).x, wireEnd.get(i).y);
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y+10,wireStart.get(i).x, wireStart.get(i).y);
//			
					
				}
				else if(wireStart.get(i).x !=wireEnd.get(i).x& currentSelectedInput.get(i).equals("Bottom"))
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y, wireEnd.get(i).x, wireStart.get(i).y);
					g2d.drawLine(wireEnd.get(i).x, wireStart.get(i).y, wireEnd.get(i).x, wireStart.get(i).y-(wireStart.get(i).y-wireEnd.get(i).y));
				}
				else
				{
					g2d.drawLine(wireStart.get(i).x, wireStart.get(i).y, wireEnd.get(i).x, wireEnd.get(i).y);
				}
				
			}
		
		}
		
		
	}
//-------------------------------------------------------------------------------//
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
//-------------------------------------------------------------------------------//
	public void drawOutputConnection(Graphics2D g2d)
	{
		if(output==0)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x+10, location.y, location.x+15, location.y);
		}
			g2d.setColor(normalColor);
	}
	//-------------------------------------------------------------------------------//

	public void drawTopNot(Graphics2D g2d)
	{
		if(output==1)
		{
			g2d.setColor(normalColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
		else
		{	
			g2d.setColor(liveColor);
			g2d.drawLine(location.x-15, location.y, location.x-10, location.y);
				
		}
	}
//	public void drawTopNotLOW(Graphics2D g2d)
//	{
//		if(output==1)
//		{
//			g2d.setColor(normalColor);
//			g2d.drawLine(location.x-20, location.y, location.x-15, location.y);
//				
//		}
//		else
//		{	
//			g2d.setColor(liveColor);
//			g2d.drawLine(location.x-20, location.y, location.x-15, location.y);
//				
//		}
//	}
	
	
	
	
	

	public void drawLED(Graphics2D g2d)
	{
		g2d.setColor(normalColor);
		g2d.drawLine(location.x-5, location.y+2,location.x-10, location.y+2);
		g2d.drawLine(location.x+10, location.y+2,location.x+5, location.y+2);
		
		if(output==0)
		{
			g2d.setColor(offColor);
		}else
		{
			g2d.setColor(onColor);
			g2d.fillOval(location.x-5, location.y-3, 10, 10);
		}
		if(input1Result==0)
		{	g2d.setColor(offColor);
			g2d.drawOval(location.x-5, location.y-3, 10, 10);
			
		}
		else
			
		{	
			g2d.setColor(onColor);
			g2d.fillOval(location.x-5, location.y-3, 10, 10);
		}
		if(showInputTop)
		{
			g2d.drawOval(location.x-12, location.y, 4, 4);
		}
		else if(showOutput)
		{
			g2d.drawOval(location.x+8, location.y-2, 4, 4);
		}
		
		drawWire(g2d);
	}
	public void drawSwitch(Graphics2D g2d)
	{
		g2d.setColor(normalColor);
		
		if(selected)
		{
			selectedcount++;
			selected=false;
		}
		if(selectedcount==0 |selectedcount==2)
		{
				
				g2d.drawOval(location.x-5, location.y-5, 4, 4);
			    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-8);
			    g2d.drawOval(location.x+5, location.y-5, 4, 4);
			    
		}
		else
		{
			if(output==1)
			{
			
			g2d.setColor(liveColor);
			
			}
				g2d.drawOval(location.x-5, location.y-5, 4, 4);
			    g2d.drawLine(location.x-3, location.y-5, location.x+7, location.y-5);
			    g2d.drawOval(location.x+5, location.y-5, 4, 4);
		}
		if(selectedcount==2)
		{
			selectedcount=0;
			
		}
		
		if(showInputTop)
		{
			g2d.fillOval(location.x-5, location.y-5, 4, 4);
		}
		else if(showOutput)
		{
			g2d.fillOval(location.x+5, location.y-5, 4, 4);
		}
		drawWire(g2d);
	}
	//drawing parts end
// -------------------------------------------------------------
	public int getSelectedCount()
	{
		return selectedcount;
	}
	public Rectangle2D getBounds() 
	{
//		if(type.equals("AND")|type.equals("OR")|type.equals("XOR")|type.equals("NOT") |type.equals("NOR")|type.equals("ANDN")|type.equals("NOT(Active Low)"))
//		{
//			return new Rectangle2D.Double(location.x-10, location.y-10, 20, 20);
//		}
		if(componentType.equals("Boolean"))
		{
			return new Rectangle2D.Double(location.x-10, location.y-10, 20, 20);
		}
		else if(type.equals("Input")|type.equals("Output"))
		{
			return new Rectangle2D.Double(location.x-5, location.y+1, 12, 12);
		}
		else if(type.equals("Switch"))
		{
			return new Rectangle2D.Double(location.x, location.y-10, 4, 10);
		}
		else if(type.equals("LED"))
		{
			return new Rectangle2D.Double(location.x-5, location.y-5, 10, 10);
		}
		 
		else if (type.equals("SupplyLive"))
		{
			
			return new Rectangle2D.Double(location.x-3, location.y, location.x+1, 1000);
		}
		else if (type.equals("SupplyGround"))
		{
			return new Rectangle2D.Double(location.x-3, 0, 6, 1000);
		}
		else
		{
			return null;
		}
	    
	}
	public Boolean isSwitchClosed()
	{
		return isswitchclosed;
	}
	public Point getLocationInput1()
	{
		if(type.equals("AND")|type.equals("OR")|type.equals("XOR")|type.equals("NOR")|type.equals("ANDN"))
		{
			Point p = new Point(location.x-15, location.y-5);
			
			return p;
		}
		else if(type.equals("NOT"))
		{
			Point p = new Point(location.x-15, location.y);
			
			return p;
		}
		else if(type.equals("NOT(Active Low)"))
		{
			Point p = new Point(location.x-20, location.y);
			
			return p;
		}
		else if(type.equals("Switch"))
		{
			Point p = new Point(location.x-3, location.y-3);
			
			return p;
		}
		else if(type.equals("Output"))
		{
			Point p = new Point(location.x-10, location.y+1);
			
			return p;
		}
		else if(type.equals("Input"))
		{
			Point p = new Point(location.x-8, location.y+2);
			
			return p;
		}
		else
		{

			return null;
		}
		
	}
	public Point getLocationInput2()
	{
		Point p = new Point(location.x-15, location.y+5);
		
		return p;
	}
	public Point getLocationInput3() {
		
		Point p = new Point(location.x-15, location.y);
		
		return p;
	}
	
	public Point getLocationOutput()
	{
		
		if(type.equals("AND")|type.equals("OR")|type.equals("XOR")|type.equals("ANDN"))
		{
			Point p = new Point(location.x+16, location.y);
			
			return p;
		}
		else if(type.equals("Input"))
		{
			Point p = new Point(location.x+12, location.y+6);
			
			return p;
		}
		else if(type.equals("NOR")|type.equals("NAND"))
		{
			Point p = new Point(location.x+20, location.y);
			
			return p;
			
		}
		
		else if(type.equals("Switch"))
		{
			Point p = new Point(location.x+7, location.y-3);
			
			return p;
		}
		else if(type.equals("NOT"))
		{
			Point p = new Point(location.x+20, location.y);
			
			return p;
		}
		else if(type.equals("NOT(Active Low)"))
		{
			Point p = new Point(location.x+15, location.y);
			
			return p;
		}
		else if(type.equals("Output"))
		{
			Point p = new Point(location.x+10, location.y);
			
			return p;
		}
		else
		{

			return null;
		}
	}
	

// -------------------------------------------------------------
	//input and output getters and setters
	public int getInput1Result() {
		
		return input1Result;
	}
	public void setInput1Result(int input1Result) {
		
		this.input1Result=input1Result;
	}

	public int getInput2Result() {
	
		return input2Result;
	}
	public void setInput2Result(int input2Result) {
		
		this.input2Result=input2Result;
	}
	public int getInput3Result() {
		
		return input3Result;
	}
	public void setInput3Result(int input3Result) {
		
		this.input3Result=input3Result;
	}
//-----------------------------------------------------------------//
	public void setInput1ConnectionID(int input1ConnectionID)
	{
		this.input1ConnectionID=input1ConnectionID;
	}
	
	public int getInput1ConnectionID()
	{
		return input1ConnectionID;
	}
	
	public void setInput2ConnectionID(int input2ConnectionID)
	{
		this.input2ConnectionID=input2ConnectionID;
	}
	
	public int getInput2ConnectionID()
	{
		return input2ConnectionID;
	}
	public int getInput3ConnectionID()
	{
		return input3ConnectionID;
	}
	public void setInput3ConnectionID(int input3ConnectionID) {
		this.input3ConnectionID=input3ConnectionID;
		
	}
	public void setOutput(int output)
	{
		this.output=output;
	}
	
	public int getOutput()
	{
		return output;
	}
// -------------------------------------------------------------
	public String getType()
	{
		return type;
	}
//--------------------------------------------------------------//
	public Rectangle2D getInput1Bounds() 
	{
		if(componentType.equals("Boolean"))
		{
			 return new Rectangle2D.Double(location.x-17, location.y-7, 4, 4);
		}
		else if(type.equals("Output")|type.equals("Input"))
		{	
			 return new Rectangle2D.Double(location.x-12, location.y-2, 4, 4);
		}
		else if(type.equals("NOT"))
		{
			return new Rectangle2D.Double(location.x-17, location.y-2, 4, 4);
		}
		else if(type.equals("NOT(Active Low)"))
		{
			return new Rectangle2D.Double(location.x-22, location.y-2, 4, 4);
		}
		else if(type.equals("Switch"))
		{
			
			return new Rectangle2D.Double(location.x-5, location.y-5, 4, 4);
		}
		else if (type.equals("LED"))
		{
			 return new Rectangle2D.Double(location.x-12, location.y-2, 4, 4);
				}
		
		else if (type.equals("Wire"))
		{
			return new Rectangle2D.Double(location.x-3, 0, 6, 1000);
		}
		else
		{
			return null;
		}
	   
	}
	
	public Rectangle2D getInput2Bounds()
	{
		if(componentType.equals("Boolean"))
		{
			return new Rectangle2D.Double(location.x-17, location.y+3, 4, 4);
		}
		else if(type.equals("Input"))
		{
			
			return new Rectangle2D.Double(location.x-10, location.y-5, 4, 4);
		}
		else if(type.equals("NOT")|type.equals("NOT(Active Low)"))
		{
			
			return new Rectangle2D.Double(location.x-10, location.y-5, 4, 4);
		}

		else
		{
			return null;
		}
	}
	public Rectangle2D getInput3Bounds()
	{
		if(componentType.equals("Boolean"))
		{
			return new Rectangle2D.Double(location.x-17, location.y, 4, 4);
		}
		else if(type.equals("Input"))
		{
			
			return new Rectangle2D.Double(location.x-10, location.y-5, 4, 4);
		}
		else if(type.equals("NOT")|type.equals("NOT(Active Low)"))
		{
			
			return new Rectangle2D.Double(location.x-10, location.y-5, 4, 4);
		}

		else
		{
			return null;
		}
	}
	public Rectangle2D getOutputBounds() 
	{
		
		if(componentType.equals("Boolean"))
		{
			
			return new Rectangle2D.Double(location.x+13, location.y-2, 4, 4);
			
		}
		else if(type.equals("Input"))
		{
			
			 return new Rectangle2D.Double(location.x+10, location.y+4, 4, 4);
		}
		if(type.equals("NOR")|type.equals("NAND"))
		{
			
			return new Rectangle2D.Double(location.x+18, location.y-2, 4, 4);
			
		}
		else if(type.equals("NOT"))
		{
			
			return new Rectangle2D.Double(location.x+17, location.y-2, 4, 4);
		}
		else if(type.equals("NOT(Active Low)"))
		{
			
			return new Rectangle2D.Double(location.x+13, location.y-2, 4, 4);
		}
		
		else if(type.equals("Switch"))
		{
			return new Rectangle2D.Double(location.x+5, location.y-5, 4, 4);
		}
		else if(type.equals("Output"))
		{
			 return new Rectangle2D.Double(location.x+8, location.y-2, 4, 4);
		}
		
		else if (type.equals("Wire"))
		{
			return new Rectangle2D.Double(633, 0, 6, 1000);
		}
		else
		{
			return null;
		}
	    
	    
	}
	

	
	public boolean getShowInput1()
	{
	    return showInput1;
	}
	
	public void setShowInput1(boolean showInput1) 
	{
	    this.showInput1=showInput1;
	}
	public void setShowInput2(boolean showInput) 
	{
	    this.showInput2=showInput;
	}
	public boolean getShowInput2()
	{
	    return showInput2;
	}
	public void setShowInput3(boolean showInput) 
	{
	    this.showInput3=showInput;
	}
	public boolean getShowInput3()
	{
	    return showInput3;
	}
	public void setShowInput4(boolean showInput) 
	{
	    this.showInput4=showInput;
	}
	public boolean getShowInput4()
	{
	    return showInput4;
	}
	public void setShowInput5(boolean showInput) 
	{
	    this.showInput5=showInput;
	}
	public boolean getShowInput5()
	{
	    return showInput5;
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
	public void setSelected(boolean selected)
	{
	    this.selected = selected;
	 
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
	public void setSwitchClosed(boolean closed) {
	this.isswitchclosed=closed;
		
	}

	public void addWireStart(Point current) {
		
			wireStart.add(current);

		
	}
	public void addWireEnd(Point last, String current) {
		wireEnd.add(last);
		currentSelectedInput.add(current);
		
		
	}

	public void setLocation(int x, int y) {
		this.location.x=x;
		this.location.y=y;
		
	}
//---------------------------------------------------------------	
	public int getPartId()
	{
		return this.partID;
		
	}
	public void setPartId(int partID)
	{
		this.partID=partID;
		
	}
	public String getComponentType() {
		// TODO Auto-generated method stub
		return componentType;
	}
	public String getLabelName() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setLabelName(String name) {
		
	}
	public void addWire(Wire wire) {
		wireList.add(wire);
		
	}
	
	
	
} 