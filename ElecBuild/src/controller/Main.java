package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import gui.ComponentPanel;
import gui.DrawPanel;
import gui.IOPanel;
import gui.IOPopupPanel;
import gui.InfoPanel;
import gui.MenuBar;
import gui.MenuBarListener;
import gui.PartPopupPanel;
import gui.SideBarListener;
import gui.StructureFileFilter;
import gui.ToolBarPanel;
import gui.ZoomBarPanel;
import model.AND;
import model.Part;
import model.Structure;
import model.TempWire;
import model.Wire;



@SuppressWarnings("serial")
public class Main extends JFrame implements ComponentListener {
	private ComponentPanel componentpanel;
	private IOPanel iopanel;
	private DrawPanel drawpanel;
	//private ToolBarPanel toolbarpanel;
	private ZoomBarPanel zoombarpanel;
	private InfoPanel infopanel;
	private Structure structure;
	private MenuBar menubar;
	private JFileChooser fileChooser;
	private JSplitPane splitpane;
	
	private PartPopupPanel partPopup;
	private IOPopupPanel ioPopup;
	
	double zoom = 1d;
	private int clickcount=0;
	private Point current ;
	private Point last;
	private Point templast;
	private Point newcurrent;
	private int partID1=-1;
	private int partID2=-1;
	private Wire wire;
	private File currentFile;

	private TempWire tempwire;

	public Main() {
		JFrame frame = new JFrame("Untitled1");
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 800);


		tempwire= new TempWire();
		//toolbarpanel = new ToolBarPanel();
		zoombarpanel=new ZoomBarPanel();
		componentpanel = new ComponentPanel();
		iopanel= new IOPanel();
		drawpanel = new DrawPanel();
		//infopanel = new InfoPanel();
		structure= new Structure();
		menubar = new MenuBar();
		fileChooser = new JFileChooser();
		
		fileChooser.addChoosableFileFilter(new StructureFileFilter());
		
		JScrollPane scrollPane = new JScrollPane(drawpanel);
		splitpane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,iopanel);
		frame.setName("Test");
		frame.add(splitpane, BorderLayout.CENTER);
	//	frame.add(toolbarpanel, BorderLayout.PAGE_START);
		frame.add(componentpanel, BorderLayout.WEST);
		frame.add(iopanel, BorderLayout.EAST);
		
		
		iopanel.setVisible(false);
	
		frame.add(zoombarpanel, BorderLayout.NORTH);
		
	//	frame.add(infopanel, BorderLayout.SOUTH);
		frame.setJMenuBar(menubar); // add menu-bar
	
		drawpanel.setComponent(structure.getPartsList());
		drawpanel.repaint();
		
		frame.setVisible(true);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		menubar.setMenuBarListener(new MenuBarListener() {

			

			private int newcount=2;

			@Override
			public void stringEmitted(String result) {

				//System.out.println(result);
				if (result == "New") { 
					
					structure=new Structure();
					drawpanel.setComponent(structure.getPartsList());
					drawpanel.repaint();
					
					frame.setTitle("Untitled"+newcount);
					newcount++;

				}	
				if (result == "Open") { 

					if (fileChooser.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {

						try {
							loadFromFile(fileChooser.getSelectedFile());
							frame.setTitle(fileChooser.getSelectedFile().getName());
							currentFile=fileChooser.getSelectedFile();

						} catch (IOException e1) {
							JOptionPane.showMessageDialog(Main.this, "Could not Load", "Error", JOptionPane.ERROR_MESSAGE);;
						}

					}
					drawpanel.repaint();

				}
				if (result == "Save") { 

					//if (fileChooser.showSaveDialog(Main.this)== JFileChooser.APPROVE_OPTION) {
						try {
							if(currentFile!=null)
							{
								
								saveToFile(currentFile);
								//frame.setTitle(currentFile.getName());
								
							}
							else {
								result = "SaveAs";
							}
							
							
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(Main.this, "Could not Save", "Error", JOptionPane.ERROR_MESSAGE);;
						//}
					}

				}
				if (result == "SaveAs") { 
					if (fileChooser.showSaveDialog(Main.this)== JFileChooser.APPROVE_OPTION) {
						try {
							saveToFile(fileChooser.getSelectedFile());
							currentFile=fileChooser.getSelectedFile();
							frame.setTitle(currentFile.getName());
							
							//System.out.println(fileChooser.getSelectedFile());
						} catch (IOException e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(Main.this, "Could not Save", "Error", JOptionPane.ERROR_MESSAGE);;
						}
					}
				}
				if (result == "Exit") { 
					int action = JOptionPane.showConfirmDialog(Main.this, 
							"Do you really want to close the application?",
							"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

					if(action == JOptionPane.OK_OPTION) {
						System.exit(0);	
					}
				}
				if (result == "Show I/O") { 
					
					iopanel.setVisible(true);
					

				}else if (result == "Hide I/O")
				{
					iopanel.setVisible(false);
				}
				
			}
			
					

				});

		

		
		componentpanel.setSideBarListener(new SideBarListener() { // Listen for a button on the toolbar to be pressed
			@Override

			public void stringEmitted(String result) { // result from tool-bar button as string
				// System.out.println(result);
				drawpanel.setCurrentSelection(result);
				infopanel.setText(result);
				drawpanel.repaint();
			
			}
		});
		drawpanel.addMouseWheelListener(new MouseAdapter() { // add wheel listener to drawPanel
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) { // when wheel is moved
//				
				if (e.getPreciseWheelRotation() < 0) {

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

				//drawpanel.updatePreferredSize(e.getPreciseWheelRotation(), e.getPoint());

				drawpanel.repaint();

			}

		});
		
		drawpanel.addMouseListener(new MouseAdapter() {

			
			
			private String inputselected;
			private Part currentpart;
			
			
			

			public void mousePressed(MouseEvent e) {
				
//				int x = e.getPoint().x;
//				int y = e.getPoint().y;
//				//This is for handling clicks that cause parts to go off screen
//				int offX = (int) (x / zoom) - x;
//				int offY = (int) (y / zoom) - y;
//				int xn = x + offX;
//				int yn = y + offY;
				
			//	click=e.getClickCount();
				
				for(Part parts : structure.getPartsList())
				{
				
				
					if(e.getClickCount()==2 & parts.isSelected() & parts.getComponentType().equals("Boolean"))
					{
						 partPopup=new PartPopupPanel();
						 partPopup.createPopup();
						 parts.setInputConnection(partPopup.getNumInputs());
						
						
					}else if(e.getClickCount()==2 & parts.isSelected() & parts.getComponentType().equals("I/O"))
					{
						ioPopup=new IOPopupPanel(parts.getLabelName());
						
						parts.setLabelName(ioPopup.getName());
						
						
						
					}
					drawpanel.repaint();
					//When a part is selected highlight it to the user
					if(parts.getBounds()!=null)
					{
						//if we click inside the bounds of a part
						if (parts.getBounds().contains(e.getPoint()) &&!parts.isSelected()) 
						{	
							// if not already highlighted
							
								parts.setSelected(true);
							
						} 
						else if (parts.getBounds().contains(e.getPoint()) && parts.isSelected()) 
						{
							//unselect the part
							parts.setSelected(false); 

						}
					}
				}
				
				//check we haven't reached max part and that some part is selected
				if(structure.getMaxParts()<100 & !drawpanel.getCurrentSelection().equals("None"))
				{
					//if the part selected is a boolean component
					if(	drawpanel.getCurrentSelection().equals("AND")|
						drawpanel.getCurrentSelection().equals("OR")|
						drawpanel.getCurrentSelection().equals("XOR")|
						drawpanel.getCurrentSelection().equals("NOT")
						)
					{
						structure.addParts(new Point(drawpanel.getSnapX(),drawpanel.getSnapY()), drawpanel.getCurrentSelection(),"Boolean");
						//structure.addParts(new Point(drawpanel.getSnapX(),drawpanel.getSnapY()), drawpanel.getCurrentSelection());
						
					}
					
					else if(drawpanel.getCurrentSelection().equals("Wire"))
					{
						
						
						
						//tempwire= new TempWire();
						//drawing a wire requires two clicks. We don't want to create a wire part every click
					
						//a wire joins two other parts so go thru all parts and check if the inputs or outputs have been clicked
						for(Part parts : structure.getPartsList())
						{			
							//some parts don't have the below bounds so we wrap in a try catch
							try
							{
								// if the output of a part has been clicked and its the first click
								
								if(parts.getOutputBounds().contains(e.getPoint())& clickcount==0)
								{
									//Start the wire drawing process
									wire = new Wire();
									
									wire.addDots(parts.getLocationOutput());
									drawpanel.addDots(parts.getLocationOutput());
									
									
									last=parts.getLocationOutput();
									 currentpart=parts;
									
									partID1=parts.getPartId();
									
									clickcount=1;
									
								}
								else if(parts.getInput1Bounds().contains(e.getPoint())& clickcount==1)
								{
									System.out.println("here");
									current=parts.getLocationInput1();
									inputselected="Top";
									partID2=parts.getPartId();
									parts.setInput1ConnectionID(partID1);
									
									//currentpart.addWire(wire);
								}
								else if(parts.getInput2Bounds().contains(e.getPoint())& clickcount==1)
								{
									current=parts.getLocationInput2();
									inputselected="Bottom";
									//parts.currentSelectedInput("Top");
									partID2=parts.getPartId();
									parts.setInput2ConnectionID(partID1);
									//currentpart.addWire(wire);
								}
								else if(parts.getInput3Bounds().contains(e.getPoint())& clickcount==1)
								{
									current=parts.getLocationInput3();
									inputselected="Bottom";
									//parts.currentSelectedInput("Top");
									partID2=parts.getPartId();
									parts.setInput3ConnectionID(partID1);
								}
								else if(drawpanel.getBounds().contains(e.getPoint())&clickcount==1)
								{
									
									//wire.addWireEndToList(e.getPoint());
									//wire.setCurrent(e.getPoint());
								//	parts.addWire(wire);-last.x+current.x
								int lenx=-last.x+e.getPoint().x;
								int leny=last.y-e.getPoint().y;
								double len= Math.sqrt(lenx*lenx+leny*leny);
								double angle=Math.toDegrees((Math.asin((leny)/len)));
								//tempwire.addDot(new Point(e.getPoint().x,last.y));
								System.out.println(angle);
								if(angle>=0 & angle<45)
								{
									//tempwire.addDot(new Point(e.getPoint().x,last.y));
									wire.addDots(new Point(e.getPoint().x,last.y));
									drawpanel.addDots(new Point(e.getPoint().x,last.y));
									
									last=new Point(e.getPoint().x,last.y);
									
								}else if(angle>=45 &angle<90)
								{
									wire.addDots(new Point(last.x,e.getPoint().y));
									drawpanel.addDots(new Point(last.x,e.getPoint().y));
									
									last=new Point(last.x,e.getPoint().y);
									
								}
								else if(angle<0 &angle>-45)
								{
									wire.addDots(new Point(e.getPoint().x,last.y));
									drawpanel.addDots(new Point(e.getPoint().x,last.y));
									
									last=new Point(e.getPoint().x,last.y);
									
								}
								else if(angle<-45 &angle>-90)
								{
										wire.addDots(new Point(last.x,e.getPoint().y));
										drawpanel.addDots(new Point(last.x,e.getPoint().y));
									
									last=new Point(last.x,e.getPoint().y);
									
								}
									
									
									
									
								}
								
							}catch(Exception ex)
							{
								System.out.println("Error");
							}
//							if (last!=null ) {
//								drawpanel.addTempWire(new TempWire(current,last));
//							}
							
						}
						
						
						
						if(last!=null && current!=null)
						{
							Part part= structure.getPartsList().get(partID1);
							structure.setCurrentSelectedPart(partID2);
							structure.setLastSelectedPart(partID1);
							
							part.addWireStart(current);
							part.addWireEnd(last,inputselected);
							
							currentpart.addWire(wire);
							drawpanel.clearDots();
							last=null;
							current=null;
							clickcount=0;
							
							if(partID1!=-1&partID2!=-1)
							{
								structure.addEdge(partID1,partID2);
							}
							
				
						}
						
					}

					else 
					{
						//all other parts Switch , LED...etc
						structure.addParts(new Point(drawpanel.getSnapX(),drawpanel.getSnapY()-1), drawpanel.getCurrentSelection(),"I/O");
						
//					
					}
					
					
					
					
				}
				
				
				//sort each part topologically. This is done every mouse click.
			
				structure.topo();

				
				for(int i=0; i<structure.getTopoSorted().length;i++)
				{
					int[][] adj=structure.getAdj();							//get the connection adj matrix
					int currentPart=structure.getTopoSorted()[i];			//get current part num from topo sorted list of parts
					Part part=structure.getPartsList().get(currentPart);	//get current part from topo sorted list of parts
					
					if(part.getType().equals("Input") & part.isSelected()) 
					{
							structure.setIoList(1,part.partID);
							
					}
						
					else if(part.getType().equals("Input")& !part.isSelected())
					{
							structure.setIoList(0,part.partID);
							
						
					}
					

					//check everything connected to live wire
//					if(adj[0][currentPart]==1)							
//					{
//						
//						
//						//if the live wire is connected to switch we need to check if switch is closed.
//						if(part.getType().equals("Switch") & part.isSelected()) 
//						{
//							
//							if(!part.isSwitchClosed())
//							{
//								structure.setIoList(1,part.partID);
//								part.setSwitchClosed(true);
//							}
//							
//							else
//							{
//								structure.setIoList(0,part.partID);
//								part.setSwitchClosed(false);
//							
//							}
//								
//						}
//						//all other parts connected to live are live
//						else if(!part.getType().equals("Switch"))
//						{
//							
//							structure.setIoList(1,part.partID);
//							
//							if(part.getInput1ConnectionID()==0)
//							{
//								
//									part.setInput1Result(1);
//							}
//							if(part.getInput2ConnectionID()==0)
//							{
//								
//									part.setInput2Result(1);
//							}
//								
//							
//							
//						}
//					}
				

					// single input parts
					
					if( part.getType().equals("Input")|part.getType().equals("Switch")) 
					{
						//check what the part is connected to and turn connected part on
						goLive(adj,part);
					
						
					}


					//two input parts
					if( part.getType().equals("AND"))
					{
						if(part.getInputConnection()==2)
						{
							if(part.getInput1Result()==1 & part.getInput2Result()==1)
							{
								structure.setIoList(1,part.partID);
							}
							else {
								structure.setIoList(0,part.partID);
							}
						}
						if(part.getInputConnection()==3)
						{
							if(part.getInput1Result()==1 & part.getInput2Result()==1 && part.getInput3Result()==1)
							{
								structure.setIoList(1,part.partID);
							}
							else {
								structure.setIoList(0,part.partID);
							}
						}
						//check what the part is connected to and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("Output"))
					{
						
						//check if the input wires to AND are live
						if(part.getInput1Result()==1 )
						{
							structure.setIoList(1,part.partID);
						}
						else {
							structure.setIoList(0,part.partID);
						}
						
						goLive(adj,part);
						
					}
					else if( part.getType().equals("NAND"))
					{
						
						//check if the input wires to AND are live
						if(part.getInput1Result()==1 & part.getInput2Result()==1)
						{
							structure.setIoList(0,part.partID);
						}
						else {
							structure.setIoList(1,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("OR"))
					{
						//check if the input wires to OR are live
						if(part.getInput1Result()==1 | part.getInput2Result()==1)
						{
							structure.setIoList(1,part.partID);
						}
						else {
							structure.setIoList(0,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("NOR"))
					{
						//check if the input wires to OR are live
						if(part.getInput1Result()==1 | part.getInput2Result()==1)
						{
							structure.setIoList(0,part.partID);
						}
						else {
							structure.setIoList(1,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("XOR"))
					{
						//check if the input wires to XOR are live
						if(part.getInput1Result()==1 &part.getInput2Result()==1 | part.getInput1Result()==0 &part.getInput2Result()==0)
						{
							structure.setIoList(0,part.partID);
						}
						else {
							structure.setIoList(1,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("NOT"))
					{
						//check if the input wires to NOT are live
						if(part.getInput1Result()==1 )
						{
							structure.setIoList(0,part.partID);
						}
						else {
							structure.setIoList(1,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
					else if( part.getType().equals("NOT(Active Low)"))
					{
						//check if the input wires to NOT are live
						if(part.getInput1Result()==0 )
						{
							structure.setIoList(1,part.partID);
						}
						else {
							structure.setIoList(0,part.partID);
						}
						//check what the part is connected too and turn connected part on
						goLive(adj,part);
//						
						
					}
				

			}
				
				
				//when we have a cycle in a directed graph we need to save the part that cause the cysle in a new array
				//and update at the end of the topo sorted list
				try {
					
					for(int i=0; i<structure.getCycleList().size();i++)
					{
						int[][] adj=structure.getAdj();							//get the connection adj matrix
						int currentPart=structure.getCycleList().get(i);			//get current part num from topo sorted list of parts
						Part part=structure.getPartsList().get(currentPart);	//get current part from topo sorted list of parts
						
						if(part.getType().equals("Input") & part.isSelected()) 
						{
								structure.setIoList(1,part.partID);
								
						}
							
						else if(part.getType().equals("Input")& !part.isSelected())
						{
								structure.setIoList(0,part.partID);
								
							
						}
						//check everything connected to live wire
//						if(adj[0][currentPart]==1)							
//						{
//							
//							
//							//if the live wire is connected to switch we need to check if switch is closed.
//							if(part.getType().equals("Switch") & part.isSelected()) 
//							{
//								
//								if(!part.isSwitchClosed())
//								{
//									structure.setIoList(1,part.partID);
//									part.setSwitchClosed(true);
//								}
//								
//								else
//								{
//									structure.setIoList(0,part.partID);
//									part.setSwitchClosed(false);
//								
//								}
//									
//							}
//							//all other parts connected to live are live
//							else if(!part.getType().equals("Switch"))
//							{
//								
//								structure.setIoList(1,part.partID);
//								
//								if(part.getInput1ConnectionID()==0)
//								{
//									
//										part.setInput1Result(1);
//								}
//								if(part.getInput2ConnectionID()==0)
//								{
//									
//										part.setInput2Result(1);
//								}
//									
//								
//								
//							}
//						}
					

						// single input parts
						
						if( part.getType().equals("Input")|part.getType().equals("Switch")) 
						{
							//check what the part is connected too and turn connected part on
							goLive(adj,part);
						
							
						}


						//two input parts
						if( part.getType().equals("AND"))
						{
							//System.out.println(part.getInput1Result()+" "+part.getInput2Result());
							//check if the input wires to AND are live
							if(part.getInputConnection()==2)
							{
								if(part.getInput1Result()==1 & part.getInput2Result()==1)
								{
									structure.setIoList(1,part.partID);
								}
								else {
									structure.setIoList(0,part.partID);
								}
							}
							if(part.getInputConnection()==3)
							{
								if(part.getInput1Result()==1 & part.getInput2Result()==1 && part.getInput3Result()==1)
								{
									structure.setIoList(1,part.partID);
								}
								else {
									structure.setIoList(0,part.partID);
								}
							}
							//check what the part is connected too and turn connected part on
							goLive(adj,part);
//							
							
						}
						else if( part.getType().equals("OR"))
						{
							//check if the input wires to OR are live
							if(part.getInput1Result()==1 | part.getInput2Result()==1)
							{
								structure.setIoList(1,part.partID);
							}
							else {
								structure.setIoList(0,part.partID);
							}
							//check what the part is connected too and turn connected part on
							goLive(adj,part);
//							
							
						}
						else if( part.getType().equals("XOR"))
						{
							//check if the input wires to XOR are live
							if(part.getInput1Result()==1 &part.getInput2Result()==1 | part.getInput1Result()==0 &part.getInput2Result()==0)
							{
								structure.setIoList(0,part.partID);
							}
							else {
								structure.setIoList(1,part.partID);
							}
							//check what the part is connected too and turn connected part on
							goLive(adj,part);
//							
							
						}
						else if( part.getType().equals("NOT"))
						{
							//check if the input wires to NOT are live
							if(part.getInput1Result()==1 )
							{
								structure.setIoList(0,part.partID);
							}
							else {
								structure.setIoList(1,part.partID);
							}
							//check what the part is connected too and turn connected part on
							goLive(adj,part);
//							
							
						}


					}
					
					
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
					
				}
			
//				iopanel.printInputList();
				// go through all parts and copy i/o from i/o list
				
				for (Part parts :structure.getPartsList()) 
				{
					if(drawpanel.getCurrentSelection().equals("Input"))
					{
						
						//inputLabelList.add(parts.getLabelName());
					
						//opanel.addInputLabel(inputLabelList);
					}
					else if(drawpanel.getCurrentSelection().equals("Output"))
					{
					
					}
					
						if(structure.getIoList().get(parts.getPartId())==1)
						{
							parts.setOutput(1);
								
						}
						else
						{
							parts.setOutput(0);
								
						}

						
				
				}
				structure.printInputList();
				iopanel.addIOPartsList(structure.getIOPartsList());
			//	iopanel.addOutputList(structure.getOutputList());
				
				//iopanel.addInputLetters(structure.getInputLetters(),structure.getMax());
				drawpanel.repaint();
				iopanel.repaint();
				structure.displayAdj();
				System.out.println("//////////////////");
				
				structure.printIoList();
				System.out.println("//////////////////");
			//structure.printBfsPartList();
				System.out.println(" ");
				

				
				
				
				
			}

			private void goLive(int[][] adj, Part currentPart) {
	
				for(int j =0;j<adj.length;j++)
				{

					//for a specific part (row) check what it is connected to (col)
					if(adj[currentPart.partID][j]==1 |adj[currentPart.partID][j]==-1 ) // // check if connected to a part 
						{
							Part connectedPart=structure.getPartsList().get(j);	//get current part from topo sorted list of parts
							
//							
							if(connectedPart.getInput1ConnectionID()==currentPart.partID)
							{
								if(structure.getIoList().get(currentPart.partID)==1  ) // 
								{
									connectedPart.setInput1Result(1);
								}
								else
								{
									connectedPart.setInput1Result(0);
								
								}
							}
							if(connectedPart.getInput2ConnectionID()==currentPart.partID)
							{
								if(structure.getIoList().get(currentPart.partID)==1  ) // 
								{
									connectedPart.setInput2Result(1);
								}
								else
								{
									connectedPart.setInput2Result(0);
								
								}
							}
							if(connectedPart.getInput3ConnectionID()==currentPart.partID)
							{
								if(structure.getIoList().get(currentPart.partID)==1  ) // 
								{
									connectedPart.setInput3Result(1);
								}
								else
								{
									connectedPart.setInput3Result(0);
								
								}
							}
						//System.out.println(currentPart.getType()+ " is connected to "+ connectedPart.getType());
							
//						if(!connectedPart.getType().equals("Switch"))
//						{
//							if(structure.getIoList().get(currentPart.partID)==1  ) // 
//							{
//								//structure.setIoList(1,j);		//turn on current part
//							}
//							else
//							{
//								structure.setIoList(0,j);		//turn off current part
//							}
//						}
						else if(connectedPart.getType().equals("Switch"))
						{
							if( connectedPart.isSelected())
							{
								if(!connectedPart.isSwitchClosed())
								{
									
									connectedPart.setSwitchClosed(true);
								}
								
								else
								{
									
									connectedPart.setSwitchClosed(false);
								}
								
							}
							if(connectedPart.isSwitchClosed() & structure.getIoList().get(currentPart.partID)==1 )
							{
								structure.setIoList(1,j);
							}
							else if(!connectedPart.isSwitchClosed() | structure.getIoList().get(currentPart.partID)==0 )
							{
								structure.setIoList(0,j);
							}
								
							
						}
						}
				
				}
				
			}
			
			
			

		});
		drawpanel.addMouseMotionListener(new MouseAdapter() {
			// When mouse is dragged



	public void mouseDragged(MouseEvent de) {

		drawpanel.repaint();
	}

	public void mouseMoved(MouseEvent me) {
		super.mouseMoved(me);

		drawpanel.createSnapGrid(me.getPoint().x, me.getPoint().y);
		drawpanel.repaint();
		
		if (last!=null ) {
			
			
			drawpanel.addTempWire(me.getPoint(),last);
		}
			 
		
		for (Part parts :structure.getPartsList()) {

		
			try {
				
				if (parts.getBounds().contains(me.getPoint())) {// get the node bounds and check if mouse is within its
					// bounds
					if (!parts.isHighlighted()) {// if not already highlighted

						parts.setHighlighted(true);// highlight node

					}

				} else {

					parts.setHighlighted(false); // un-highlight node

				}
				if (parts.getInput1Bounds().contains(me.getPoint())) {// get the node bounds and check if mouse is within its
					// bounds
				
//					if (!parts.getShowTopInput()) {// if not already highlighted
//
//						parts.setShowTopInput(true);// highlight node
//						
//					}
					if (!parts.getShowInput1()) {// if not already highlighted

						parts.setShowInput1(true);// highlight node
						
					}

				} else {

					parts.setShowInput1(false); // un-highlight node
					//parts.setShowTopInput(false); // un-highlight node

				}
				
				if (parts.getInput2Bounds().contains(me.getPoint())) {// get the node bounds and check if mouse is within its
// bounds
//					if (!parts.getShowBottomInput()) {// if not already highlighted
//
//						parts.setShowBottomInput(true);// highlight node
//					}	
					if (!parts.getShowInput2()) {// if not already highlighted

						parts.setShowInput2(true);// highlight node
						
					}


				} else {
					parts.setShowInput2(false);
					//parts.setShowBottomInput(false); // un-highlight node

				}
				if (parts.getInput3Bounds().contains(me.getPoint())) {// get the node bounds and check if mouse is within its
					// bounds
											
										if (!parts.getShowInput3()) {// if not already highlighted

											parts.setShowInput3(true);// highlight node
											
										}


									} else {
										parts.setShowInput3(false);
										
									}
				if (parts.getOutputBounds().contains(me.getPoint())) {// get the node bounds and check if mouse is within its
// bounds
					if (!parts.getShowOutput()) {// if not already highlighted

						parts.setShowOutput(true);// highlight node
						
					}	

				} else {

					parts.setShowOutput(false); // un-highlight node

}


			}
			 catch (Exception e) {
				// System.out.println("Nothing Selected");
			}

//
//
		drawpanel.repaint();
		}
	}

	});
		
		Action escapeSelection =  new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		      
		    	drawpanel.setCurrentSelection("None");
				infopanel.setText("None");

		    
		    }
		};
	
		drawpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE),
                "escapeSelection");
		
		drawpanel.getActionMap().put("escapeSelection",
				escapeSelection);
		
		
		
		Action deleteAll =  new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		    	
				int current=structure.deletePart();
				for (Part parts :structure.getPartsList()) {
					
					if(parts.getPartId()>current)
					{
						parts.setPartId(parts.getPartId()-1);
					}
					
				}
				
				

		    
		    }
		};
		
		drawpanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
                "deleteAll");
		
		drawpanel.getActionMap().put("deleteAll",
				deleteAll);
	}
	

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();

			}
		});
	}
	
	public void saveToFile(File file ) throws IOException
	{
	   	 
	   	 FileOutputStream  fos = new FileOutputStream(file) ;
	   	 ObjectOutputStream  oos = new ObjectOutputStream(fos) ;
	   	 
	   
	   	 oos.writeObject(structure);
	   	
	   	 oos.close();
	   	 fos.close();
	 }
	public void loadFromFile(File file ) throws IOException
	{
  	 
		FileInputStream  fis = new FileInputStream(file) ;
		ObjectInputStream  ois = new ObjectInputStream(fis) ;
  	
  	 
  	
  	 try {
  		structure=(Structure) ois.readObject();
	
			drawpanel.setComponent(structure.getPartsList());
			
			 
			drawpanel.repaint();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	
  	
  	 ois.close();
  	 fis.close();
  	
   }
	@Override
	public void componentResized(ComponentEvent e) {
		// drawpanel.setPreferredSize(e.getComponent().getSize());

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
