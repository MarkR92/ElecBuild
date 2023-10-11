package model;

import java.awt.Graphics2D;
import java.awt.Point;

public class OUTPUT extends Part {


	private static final long serialVersionUID = -7022021180852529918L;
	private static int labelNum=0;
	private String labelName="Q";
	
	public OUTPUT(Point location, int partID) {
		super(location, "Output", "I/O", partID);
		labelName+=labelNum;
		labelNum++;
		
		//outputsLabel.add(labelName);
	}
	public void drawOutput(Graphics2D g2d)
	{
		if(input1Result==0)
		{ 
			g2d.setColor(normalColor);	
			g2d.drawRect(location.x-5, location.y-5, 12, 12);
			g2d.drawLine(location.x-10, location.y+1, location.x-6, location.y+1);
			g2d.drawString("0", (float)location.x-2.2f, (float)location.y+5.3f);
		}
	else
	{
		g2d.setColor(liveColor);	
		g2d.drawRect(location.x-5, location.y-5, 12, 12);
		g2d.drawLine(location.x-10, location.y+1, location.x-6, location.y+1);
		g2d.drawString("1", (float)location.x-2.2f, (float)location.y+5.3f);
		
	}
		
		if(showInput1)
		{
			g2d.drawOval(location.x-12, location.y-1, 4, 4);
		}
		
		drawWire(g2d);
	}
	@Override
	public String getLabelName() {
		return labelName;
	}
	@Override
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	

}
