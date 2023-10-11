package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import model.AND;
import model.INPUT;
import model.NOT;
import model.OR;
import model.OUTPUT;
import model.Part;
import model.XOR;



@SuppressWarnings("serial")
public class IOPanel extends JToolBar{

	private JLabel title;
	private JLabel title2;

	private ArrayList<INPUT> inputsList= new ArrayList<>();
	private ArrayList<OUTPUT> outputsList= new ArrayList<>();
	
	private JTable table;
	private IOTableModel tablemodel;


	private int outstart;
	
	public IOPanel() {
		setOrientation(VERTICAL);
		
		tablemodel= new IOTableModel();
		table= new JTable(tablemodel);
		
		title = new JLabel("                                   ");
		title2 = new JLabel("                                   ");
	
		
        setLayout(new BorderLayout());
		
        add(new JScrollPane(table),BorderLayout.CENTER);
        
//		GridBagConstraints gc = new GridBagConstraints();
//		
//		
//		gc.weightx=1;
//		gc.weighty=1;
//		
//		
//		gc.gridx = 0;
//		gc.gridy = 0;
//		
//	
//		gc.fill = GridBagConstraints.NONE;
//		
//		gc.anchor = GridBagConstraints.LINE_START;
//		add(title,gc);
//		
//		gc.gridx = 0;
//		gc.gridy = 2;
//		gc.weighty=100;
//		gc.anchor = GridBagConstraints.LINE_START;
//		//add(title2,gc);
//		
//		gc.gridx = 0;
//		gc.gridy = 2;
//		gc.weighty=0.1;
//		gc.anchor = GridBagConstraints.LINE_START;
//		//add(inputs,gc);
//	
//		gc.gridx = 0;
//		gc.gridy = 3;
//		gc.weighty=0.1;
//		gc.anchor = GridBagConstraints.LINE_START;
//	//	add(outputsTitle,gc);
//		
//		
//		gc.weighty=100;
//		gc.gridx = 0;
//		gc.gridy = 4;
//	
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
	//	add(outputs,gc);
		
		
		//setSize(200,400);
	}
	protected void paintComponent(Graphics grphcs) {
		super.paintComponent(grphcs);
		Graphics2D g2d = (Graphics2D) grphcs;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	
		//drawTable(g2d);
			
		
		

	}
	public void drawTable(Graphics2D g2d)
	{
	
		
		//g2d.drawString("Inputs/Outputs", 0, 30);
		//g2d.drawString("Inputs/Outputs", 0, 90);
		
		
		
		for(int i =0;i<inputsList.size();i++)
		{
			g2d.drawString(inputsList.get(i).getLabelName(), 4+i*20, 65);
			g2d.drawString(""+inputsList.get(i).getOutput(), 4+i*20, 80);
			
			outstart=4+i*20;
			
		}
		
		//g2d.drawString("Outputs", outstart, 30);
		
		
		
			
			
		for(int i =0;i<outputsList.size();i++)
		{
			//System.out.println("heee");
			g2d.drawString(outputsList.get(i).getLabelName(), outstart+20+i*20, 65);
			g2d.drawString(""+outputsList.get(i).getOutput(), outstart+20+i*20, 80);
			
		}
		
	
		
		
		}
	
	public void addIOPartsList(ArrayList<Part> arrayList) {
		this.tablemodel.setInputsList(arrayList);
		tablemodel.fireTableStructureChanged();
		
		
	}
	
	

	
	

}
