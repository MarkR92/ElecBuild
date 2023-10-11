package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.Switch;
import model.TempWire;

import model.XOR;
import model.AND;
//import model.And;
import model.ComponentConnections;
import model.Connections;
import model.INPUT;
import model.NOT;
import model.OR;
import model.OUTPUT;
import model.Part;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {

	public ArrayList<Object> parts = new ArrayList<>();
	
	
	public ArrayList<Part> partsList = new ArrayList<>();
	public ArrayList<TempWire> tempWireList = new ArrayList<>();
	
	ArrayList<Point> dots=new ArrayList<>();
	
	private Dimension preferredSize = (new Dimension(600, 600));
	private String currentselection="None";

	

	private double scale = 1;
	private double zoom = 1d;
	private int tranx;
	private int trany;
	
	private int snapmousepositionx, snapmousepositiony;//current snap coords
	private int currentmousepositionx,currentmousepositiony;

	private TempWire tempWire;


	private Point current;


	private Point last;


	
	   

	
	


	public void setComponent(ArrayList<Part> arrayList)
	{
		this.partsList=arrayList;
		
	}
	 public ArrayList<Part> getParts() {
	    	
	        return partsList;
	        
	 }

	@Override
	public Dimension getPreferredSize() {

		return preferredSize;

	}

	public void setPreferredSize(Dimension d) {
		preferredSize = d;

	}



	public int getGridWidth() {

		return 10;
	}

	public int getGridHeight() {

		return 10;
	}

	public void updatePreferredSize(double n, Point p) {

//		if (n == 0) // ideally getWheelRotation() should never return 0.
//			n = -1 * prevN; // but sometimes it returns 0 during changing of zoom
							// direction. so if we get 0 just reverse the direction.
		if (n < 0) {

			zoom += 0.1;
		} else {
			zoom -= 0.1;
		}

		if (zoom < 0.01) {
			zoom = 0.01;
		}
		if (zoom < 1) {
			zoom = 1;
		}
		if (zoom > 5) {
			zoom = 5;
		}
		setZoom(zoom);
//		double d = (double) n * 1.08;
//		d = (n > 0) ? 1 / d : -d;
		System.out.println(getWidth() + "," + getHeight()+"get");
		int w = (int) (692 * zoom);
		int h = (int) (704 * zoom);
			
		System.out.println(zoom);
		preferredSize.setSize(w, h);
		System.out.println(w + "," + h);
		
		System.out.println(p.x + "," + p.y);
		
		int offX = (int) (p.x / zoom) - p.x;
		int offY = (int) (p.y / zoom) - p.y;

		setTranslate((int)(p.x/zoom),(int)(p.y/zoom));
		
		getParent().setLocation(getParent().getLocation().x - offX, getParent().getLocation().y - offY);
		// in the original code, zoomPanel is being shifted. here we are shifting
		// containerPanel

		getParent().doLayout(); // do the layout for containerPanel
		getParent().getParent().doLayout(); // do the layout for jf (JFrame)

	
		


	}
	 public void createSnapGrid(int x, int y) {
	    	
    	 currentmousepositionx = x;
    	 currentmousepositiony = y;
    	 
         int remainderx = currentmousepositionx % getGridWidth(), remaindery = currentmousepositiony % getGridHeight();
         
         if (remainderx<getGridWidth()/2) setSnapX(currentmousepositionx - remainderx) ;
         else setSnapX(currentmousepositionx + (getGridWidth()-remainderx));
         
         if (remaindery<getGridHeight()/2) setSnapY(currentmousepositiony - remaindery);
         else setSnapY(currentmousepositiony + (getGridHeight()-remaindery));
        
    	
    }
    public int getSnapX(){
    	
    	return (this.snapmousepositionx);
    }
    public int getSnapY(){
    	
    	return  (this.snapmousepositiony);
    }
    
    
    public void setSnapX(int snap){
    	this.snapmousepositionx=(int) (snap);
    	
    	
    }
    public void setSnapY(int snap){
    	this.snapmousepositiony=(int) (snap);
    	
    	
    }
	

	public void drawGrid(Graphics2D g) {
		g.setColor(Color.lightGray);
		g.clearRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < getWidth() * scale; i += 20) {
			for (int j = 0; j < getHeight() * scale; j += 20) {
				g.setColor(Color.lightGray);
				
				g.drawOval(i, j, 1, 1);	
			}
			 g.setColor(Color.BLACK);
		        if ( getSnapX()>=0 &&  getSnapY()>=0 &&  getSnapX()<=getWidth() && getSnapY()<=getHeight()) {
		        	// result =true;
		        	
		        	 drawCurrentSelection(g);
		          
		        }
			
		}

	}
	public void drawCurrentSelection(Graphics g2d)
	{
		
		if(getCurrentSelection().equals("AND"))
		{
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()-10, getSnapY()+10);
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX(), getSnapY()-10);
			g2d.drawLine(getSnapX()-10, getSnapY()+10, getSnapX(), getSnapY()+10);
			g2d.drawArc(getSnapX()-10, getSnapY()-10, 20, 20, -90, 180);
		}
		else if(getCurrentSelection().equals("OR"))
		{
			g2d.drawLine(getSnapX()-13, getSnapY()-10, getSnapX()-8, getSnapY()-10);
			g2d.drawLine(getSnapX()-13, getSnapY()+10, getSnapX()-8, getSnapY()+10);	
			g2d.drawArc(getSnapX()-32, getSnapY()-12, 24, 24, -56, 110);
			g2d.drawArc(getSnapX()-28, getSnapY()-10, 40, 40, 30, 58);
			g2d.drawArc(getSnapX()-28, getSnapY()-30, 40, 40, -90, 58);
		}
		else if(getCurrentSelection().equals("Switch"))
		{
			g2d.drawOval(getSnapX()-5, getSnapY()-5, 4, 4);
		    g2d.drawLine(getSnapX()-3, getSnapY()-5, getSnapX()+7, getSnapY()-8);
		    g2d.drawOval(getSnapX()+5, getSnapY()-5, 4, 4);
		}
		else if(getCurrentSelection().equals("LED"))
		{
		
			g2d.drawLine(getSnapX()-5, getSnapY(),getSnapX()-10, getSnapY());
			g2d.drawLine(getSnapX()+10, getSnapY(),getSnapX()+5, getSnapY());
			g2d.drawOval(getSnapX()-5, getSnapY()-4, 10, 10);
			
		}
		else if(getCurrentSelection().equals("NOT"))
		{
		
			g2d.drawOval(getSnapX()+10, getSnapY()-2, 4, 4);
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()-10, getSnapY()+10);
			g2d.drawLine(getSnapX()-10, getSnapY()+10, getSnapX()+10, getSnapY());
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()+10, getSnapY());
			
		}
		else if(getCurrentSelection().equals("XOR"))
		{
		
			g2d.drawLine(getSnapX()-13, getSnapY()-10, getSnapX()-8, getSnapY()-10);
			g2d.drawLine(getSnapX()-13, getSnapY()+10, getSnapX()-8, getSnapY()+10);
			g2d.drawArc(getSnapX()-32, getSnapY()-12, 24, 24, -56, 110);
			g2d.drawArc(getSnapX()-35, getSnapY()-12, 24, 24, -56, 110);
			g2d.drawArc(getSnapX()-28, getSnapY()-10, 40, 40, 30, 58);
			g2d.drawArc(getSnapX()-28, getSnapY()-30, 40, 40, -90, 58);
			
		}else if(getCurrentSelection().contentEquals("NAND"))
		{
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()-10, getSnapY()+10);
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX(), getSnapY()-10);
			g2d.drawLine(getSnapX()-10, getSnapY()+10, getSnapX(), getSnapY()+10);
			g2d.drawArc(getSnapX()-10, getSnapY()-10, 20, 20, -90, 180);
			g2d.drawOval(getSnapX()+10, getSnapY()-2, 4, 4);
			
			
		}
		else if(getCurrentSelection().contentEquals("NOR"))
		{
			
			g2d.drawLine(getSnapX()-13, getSnapY()-10, getSnapX()-8, getSnapY()-10);
			g2d.drawLine(getSnapX()-13, getSnapY()+10, getSnapX()-8, getSnapY()+10);	
			g2d.drawArc(getSnapX()-32, getSnapY()-12, 24, 24, -56, 110);
			g2d.drawArc(getSnapX()-28, getSnapY()-10, 40, 40, 30, 58);
			g2d.drawArc(getSnapX()-28, getSnapY()-30, 40, 40, -90, 58);
			g2d.drawOval(getSnapX()+10, getSnapY()-2, 4, 4);
			
		}
		else if(getCurrentSelection().contentEquals("NOT(Active Low)"))
		{
			g2d.drawOval(getSnapX()-15, getSnapY()-2, 4, 4);
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()-10, getSnapY()+10);
			g2d.drawLine(getSnapX()-10, getSnapY()+10, getSnapX()+10, getSnapY());
			g2d.drawLine(getSnapX()-10, getSnapY()-10, getSnapX()+10, getSnapY());
			
		}
		else if(getCurrentSelection().contentEquals("Input"))
		{
			
			g2d.drawRect(getSnapX()-5,  getSnapY(), 12, 12);
			g2d.drawLine(getSnapX()+7,  getSnapY()+5, getSnapX()+12,  getSnapY()+5);
		
		}
		else if(getCurrentSelection().contentEquals("Output"))
		{
			
			g2d.drawRect(getSnapX()-5,  getSnapY()-6, 12, 12);
			g2d.drawLine(getSnapX()-10,  getSnapY(), getSnapX()-6,  getSnapY());
			
		
		}
	}
	
	protected void paintComponent(Graphics grphcs) {
		super.paintComponent(grphcs);
		Graphics2D g2d = (Graphics2D) grphcs;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		AffineTransform at = g2d.getTransform();

		at.scale(scale, scale);
		
		g2d.setTransform(at);
		
		drawGrid(g2d);
		// drawDot(g2d);
		 drawWire(g2d);
		 drawTempWire(g2d);
//		System.out.println("here draw"+tempWire);
//		if(tempWire!=null)
//		{
//			tempWire.drawTempWire(g2d);
//			System.out.println("here draw");
//			tempWire.drawDot(g2d);
//		}
//		for(TempWire wire:tempWireList)
//		{
//			wire.drawTempWire(g2d);
//		}
		
		//		
		
//		for ( Connections connection : connections) {
//			try {
//				connection.drawConnection(g2d);
//				
//			} catch (Exception e) {
//				// System.out.println("Nothing Selected");
//			}
//
//		}
		//System.out.println(partsList);
		if(partsList!=null)
		{
			for ( Part part : partsList) {
				try {
				
					( part).drawParts(g2d);
					switch (part.getType()) {
					
					 //boolean parts
					 case "AND": 	((AND) part).drawAND(g2d); break;
			         case "OR":  	((OR) part).drawOR(g2d); break;
			         case "NOT":  	((NOT) part).drawNOT(g2d); break;
			         case "XOR":  	((XOR) part).drawXOR(g2d); break;
			         case "Input":  ((INPUT) part).drawInput(g2d); break;
			         case "Output": ((OUTPUT) part).drawOutput(g2d); break;
					}
			         
					
					
				} catch (Exception e) {
					// System.out.println("Nothing Selected");
				}

			}
			
		}
		

	}

	public void setTranslate(int x, int y) {
		this.tranx = x;
		this.trany = y;

	}
	public Point getTranslate() {
		
		return new Point(tranx, trany);

	}

	public void setZoom(double zoom) {
		// zoom = Math.round(zoom * 10.0) / 10.0;

		this.scale = zoom;

	}
	public void drawDot(Graphics2D g2d)
	{
		//System.out.println(dots.size());
		for(int i=0;i<dots.size();i++)
		{
			g2d.drawOval(dots.get(i).x-5, dots.get(i).y-5, 10, 10);
		}
		
		
	}
	public void drawWire(Graphics2D g2d)
	{
		//System.out.println(dots.size());
		try {
			for(int i=0;i<dots.size();i++)
			{
				g2d.drawLine(dots.get(i).x, dots.get(i).y, dots.get(i+1).x, dots.get(i+1).y);
			}
			
		}catch( Exception e){
			
		}
		
		
		
	}
	public void drawTempWire(Graphics2D g2d)
	{
		try {
			int lenx=-last.x+current.x;
			int leny=last.y-current.y;
			double len= Math.sqrt(lenx*lenx+leny*leny);
			double angle=Math.toDegrees((Math.asin((leny)/len)));
			//tempwire.addDot(new Point(e.getPoint().x,last.y));
			//System.out.println(angle);
			if(angle>=0 & angle<45)
			{
				//tempwire.addDot(new Point(e.getPoint().x,last.y));
				
				g2d.drawLine(current.x,last.y, last.x, last.y);
				last=new Point(current.x,last.y);
				
			}else if(angle>=45 &angle<90)
			{
				g2d.drawLine(last.x,current.y, last.x, last.y);
				
				last=new Point(last.x,current.y);
				
			}
			else if(angle<0 &angle>-45)
			{
				g2d.drawLine(current.x,last.y, last.x, last.y);
				last=new Point(current.x,last.y);
				
			}
			else if(angle<-45 &angle>-90)
			{
					g2d.drawLine(last.x,current.y, last.x, last.y);
				
				last=new Point(last.x,current.y);
				
			}
			for(int i=0;i<dots.size();i++)
			{
				g2d.drawLine(dots.get(i).x, dots.get(i).y, dots.get(i+1).x, dots.get(i+1).y);
			}
				
			
				
			
			
		}catch( Exception e){
			
		}
		
		
				
		
		
		
		
	}
	
	
	public void addDots(Point dot)
	{
		this.dots.add(dot);
	}
	public void clearDots()
	{
		this.dots.clear();
	}

	public String getCurrentSelection() {
		// TODO Auto-generated method stub
		return currentselection;
	}
	public void setCurrentSelection(String currentselection) {
		this.currentselection = currentselection;
	}
	public void addConnection(ComponentConnections componentConnections) {
		// TODO Auto-generated method stub
		
	}

	public void addTempWire(Point point, Point last) {
		// TODO Auto-generated method stub
		this.current=point;
		this.last=last;
		
	}
	
	

	
	
	
}
