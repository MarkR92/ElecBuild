package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class INPUT extends Part {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4369711685426369632L;
	private static int labelNum=0;
	private String labelName="I";
	//private ArrayList<String> inputsLabel= new ArrayList<>();
	//
	public INPUT(Point location, int partID) {
		super(location, "Input","I/O", partID);
		labelName+=labelNum;
		labelNum++;
		
		//inputsLabel.add(labelName);
	}
	public void drawInput(Graphics2D g2d)
	{
		if(output==0)
		{
			g2d.setColor(normalColor);	
			g2d.drawRect(location.x-5, location.y+1, 12, 12);
			g2d.drawLine(location.x+7, location.y+6, location.x+12, location.y+6);
			g2d.drawString("0", (float)location.x-2.2f, (float)location.y+11.3f);
		}else 
		{
			g2d.setColor(liveColor);	
			g2d.drawRect(location.x-5, location.y+1, 12, 12);
			g2d.drawLine(location.x+7, location.y+6, location.x+12, location.y+6);
			g2d.drawString("1", (float)location.x-2.2f, (float)location.y+11.3f);
			
		}
		//g2d.drawRect(location.x+10, location.y, 4, 4);
		if(showOutput)
		{
			g2d.drawOval(location.x+10, location.y+4, 4, 4);
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
//	public ArrayList<String> getInputList()
//	{
//		return inputsLabel;
//	}

	

	

}
