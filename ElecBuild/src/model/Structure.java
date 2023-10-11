package model;


import java.awt.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;



public class Structure implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 700059547329289988L;
	
	private ArrayList<Part> partsList = new ArrayList<>();
	private ArrayList<Part> ioPartsList = new ArrayList<>();
	private ArrayList<INPUT> inputsList = new ArrayList<>();
	private ArrayList<String> inputsLetters = new ArrayList<>();
	private ArrayList<Part> tempPartsList = new ArrayList<>();
	private ArrayList<Integer> ioList = new ArrayList<>();
	private int adjMat[][]; // adjacency matrix
	
	private int tempAdjMat[][]; // adjacency matrix
	private int sortedParts[]; 
	
	private int nParts;
	private int partNum=0;
	
	int countA=0;
	int countB=0;
	int countI=0;

	private int currentSelectedPart;
	private ArrayList<Integer> cycleParts =new ArrayList<>();
	private int lastSelectedPart;

	

	private ArrayList<OUTPUT> outputsList;

	
	
// -------------------------------------------------------------
	public Structure() // constructor
	{
		
		adjMat = new int[5][5];
//		tempAdjMat=new int[5][5];

		intialiseAdjMat();
		
		
	} // end constructor
//--------------------------------------------------------------
	public void createInputsList()
	{
		ArrayList<Part> ioPartsList = new ArrayList<>();
		
		
		for(int i=0;i<partsList.size();i++)
		{
			if(partsList.get(i).getType().equals("Input"))
			{
				
				ioPartsList.add( partsList.get(i));
				
			}
			
		}
		for(int i=0;i<partsList.size();i++)
		{
			if(partsList.get(i).getType().equals("Output"))
			{
				
				ioPartsList.add( partsList.get(i));
				
			}
			
		}
			//System.out.println("Before "+inputsList);
		//	Collections.sort(inputsList, new InputComparator());
			//System.out.println("After "+inputsList);
			setIOPartList(ioPartsList);
			

	}
	
private void setIOPartList(ArrayList<Part> inputsList2) {
	this.ioPartsList=inputsList2;
		
	}
	// -------------------------------------------------------------
//	public void createOutputsList()
//	{
//		ArrayList<OUTPUT> outputsList = new ArrayList<>();
//		
//		
//		for(int i=0;i<partsList.size();i++)
//		{
//			if(partsList.get(i).getType().equals("Output"))
//			{
//				
//				outputsList.add((OUTPUT) partsList.get(i));
//				
//			}
//			
//		}
//			//System.out.println("Before "+inputsList);
//			Collections.sort(outputsList, new OutputComparator());
//			//System.out.println("After "+inputsList);
//			setOutputList(outputsList);
//			
//
//	}
	
// -------------------------------------------------------------
	
	
	private void setOutputList(ArrayList<OUTPUT> outputsList) {
		this.outputsList=outputsList;
	}
	
	public ArrayList<Part> getIOPartsList()
	{
		return ioPartsList;
	}
	public void printInputList()
	{	createInputsList();

	}
	
	
	
//----------------------------------------------
	public void intialiseAdjMat()
	{
		for(int j=0; j<adjMat.length; j++)
		{
			// set adjacency
			for(int k=0; k<adjMat.length; k++)
			{// matrix to 0
				adjMat[j][k] = 0;
				
			}
		}

	}
	
	public void resizeAdjMat(int num)
	{
		if(num>adjMat.length)
		{
			int temp[][]=new int[num][num];
			
			for(int i =0;i<adjMat.length;i++)
			{
				for(int j =0;j<adjMat.length;j++)
				{
				temp[i][j]=adjMat[i][j];
				}
			}
			
			adjMat=temp;
			
		}

	}
	public void removePartFromAdjMat(int num)
	{
		int temp[][]=new int[adjMat.length-1][adjMat.length-1];
		int p=0;
	
		for(int i =0;i<adjMat.length;i++)
		{
			if(i==num)
				continue;
			
			
			int q=0;
			for(int j =0;j<adjMat.length;j++)
			{	
				if(j==num)
					continue;
				
					temp[p][q]=adjMat[i][j];
				
					++q;
				
			}
			++p;
		}
		
		adjMat=temp;
		
	}
	public void removeCycleConnection(int partNo)
	{
		
			for(int k=0; k<adjMat.length; k++)
			{
				if(adjMat[partNo][k] == 1)
				{
					adjMat[partNo][k] = -1;
				}
				
			}
		
		
	}
// -------------------------------------------------------------
	public void addParts(Point location, String type, String componentType) // 
	{
		switch(type)
		{
				 case "AND": 	partsList.add(new AND( location,partNum)) ; break;
		         case "OR":  	partsList.add(new OR( location,partNum)) ; break;
		         case "NOT":  	partsList.add(new NOT( location,partNum)) ; break;
		
		         case "XOR":  	partsList.add(new XOR( location,partNum)) ; break;
		         case "Input":  partsList.add(new INPUT( location,partNum)) ; break;
		         case "Output":  partsList.add(new OUTPUT( location,partNum)) ; break;
			        
		}     

		
		resizeAdjMat(partsList.size());
		
		ioList.add(0);
//		setIoList(1,0);
		partNum++;
		
	}
//	public void addParts(Point location, String type) // 
//	{
//		if(type.equals("AND"))
//		{
//			partsList.add(new AND( location,partNum)) ;
//			
//		}else if(type.equals("OR"))
//		{
//			partsList.add(new OR( location,partNum)) ;
//			
//		}
//		else if(type.equals("NOT"))
//		{
//			partsList.add(new NOT( location,partNum)) ;
//			
//		}
//		else if(type.equals("XOR"))
//		{
//			partsList.add(new XOR( location,partNum)) ;
//			
//		}
//		
//			
//		
//		
//		
//		resizeAdjMat(partsList.size());
//		
//		ioList.add(0);
////		setIoList(1,0);
//		partNum++;
//		
//	}
	
	  public int deletePart() {
		  
		  int current=0;
	    	 for (Iterator<Part> partIterator = partsList.iterator(); partIterator.hasNext();) {
	    		Part part = partIterator.next();
	    		
	    		if (part.isSelected()) {
	    		 partIterator.remove();
	    		 current= part.getPartId();
	    		 partNum--;
	    		//fixtures.remove(node.getNumber()-1);
	    		}
	    		
	         }
	    	 removePartFromAdjMat(current);
		  return current;
	    		    	
	    }
  public int deleteAllParts() {
		  
		  int current=0;
	    	 for (Iterator<Part> partIterator = partsList.iterator(); partIterator.hasNext();) {
	    		Part part = partIterator.next();
	    		
	    		
	    		 partIterator.remove();
	    		 current= part.getPartId();
	    		 partNum--;
	    		//fixtures.remove(node.getNumber()-1);
	    		
	    		
	         }
	    	 removePartFromAdjMat(current);
		  return current;
	    		    	
	    }
	 
	  
// -------------------------------------------------------------
	public void addEdge(int start, int end)
	{
		
		adjMat[start][end] = 1;
		  
	} 
// -------------------------------------------------------------
	
	public void displayAdj()
	{
		 // Print the adjacency matrix
	    for (int i = 0; i < partsList.size(); i++) {
	      for (int j = 0; j < partsList.size(); j++) {
	        System.out.print(adjMat[i][j] + " ");
	      }
	      System.out.println();
	    }
	}
	
	
	public int[][] getAdj()
	{
		return adjMat;
		
	}
	public int getMaxParts()
	{
		return partsList.size();
	}

	public void setPartID(int newPartNum)
	{
		this.partNum=newPartNum;
	}
	
	public ArrayList<Integer> getIoList()
	{
		return ioList;
	}
	public void setIoList(int num,int location)
	{
		this.ioList.set(location, num);
	}
	public void printIoList()
	{
		System.out.println("////////////////////");
		for(int i=0;i<partsList.size();i++)
		{
			
			System.out.println(partsList.get(i).getType() +" "+ioList.get(i)+ " " + partsList.get(i).partID);
		}
	}
	
	public ArrayList<Part> getPartsList()
	{
		return partsList;
	}

	
	public void topo() // topological sort
	{
		tempAdjMat=new int[adjMat.length][adjMat.length];
		tempPartsList=(ArrayList<Part>) partsList.clone();
		
		sortedParts = new int[partsList.size()];
		
		//tempAdjMat =adjMat;
		for(int j=0; j<adjMat.length; j++)
		{
			// set adjacency
			for(int k=0; k<adjMat.length; k++)
			{// matrix to 0
				tempAdjMat[j][k] =adjMat[j][k];
			}
		}

				
		
		nParts=partsList.size();
		
		
		int orig_nParts = partsList.size(); // remember how many parts
		
		while(nParts > 0) // while vertices remain,
		{
			// get a vertex with no successors, or -1
			int currentVertex = noSuccessors();
			
			if(currentVertex == -1) // must be a cycle
			{
				System.out.println("ERROR: Graph has cycles");
				//System.out.println(currentSelectedPart);
				removeCycleConnection(currentSelectedPart);
				
				cycleParts.add(lastSelectedPart);
				
				return;
			}

			// insert vertex label in sorted array (start at end)
			sortedParts[nParts-1] = tempPartsList.get(currentVertex).partID;
		
			
			deleteVertex(currentVertex); // delete vertex
		}
		// end while
		// vertices all gone; display sortedArray
		System.out.print("Topologically sorted order: ");
		
		for(int j=0; j<orig_nParts; j++)
		{
			System.out.print( sortedParts[j]+"-" );
		}
		System.out.println("");
		} // end topo
	
	public int noSuccessors() // returns vert with no successors
	{ 							// (or -1 if no such verts)
	
		boolean isEdge; // edge from row to column in adjMat
		for(int row=0; row<nParts; row++) // for each vertex,
		{
			isEdge = false; // check edges
			for(int col=0; col<nParts; col++)
			{
				if( tempAdjMat[row][col] > 0 ) // if edge to
				{ // another,
					isEdge = true;
					break; // this vertex
				} // has a successor
			} // try another
			
			if( !isEdge ) // if no edges,
			{
				return row; // has no successors
			}
		}
		
		return -1; // no such vertex
		} 
	
	public void deleteVertex(int delVert)
	{
		if(delVert != nParts-1) // if not last vertex,

		{ 
			// delete from vertexList
			for(int j=delVert; j<nParts-1; j++)
			{
				tempPartsList.set(j, tempPartsList.get(j+1));
			}
			// delete row from adjMat
			for(int row=delVert; row<nParts-1; row++)
			{
				moveRowUp(row, nParts);
			}
			// delete col from adjMat
			for(int col=delVert; col<nParts-1; col++)
			{
				moveColLeft(col, nParts-1);
			}
		}
		nParts--; // one less vertex
		} // end deleteVertex
	// -------------------------------------------------------------
	private void moveRowUp(int row, int length)
	{
		for(int col=0; col<length; col++)
		{
			tempAdjMat[row][col] = tempAdjMat[row+1][col];
		}
	}
	private void moveColLeft(int col, int length)
	{
		for(int row=0; row<length; row++)
		{
			tempAdjMat[row][col] = tempAdjMat[row][col+1];
		}
	}
	
	public int[] getTopoSorted()
	{
		return sortedParts;
	}
	public ArrayList<Integer> getCycleList()
	{
		return cycleParts;
	}
	
//	public void dfs() // depth-first search
//	{ // begin at vertex 0
//		partsList.get(0).wasVisited = true; // mark it
//		//displayVertex(0); // display it
//		theStack.push(0); // push it
//		ArrayList <Integer> dfsPartsList = new ArrayList<>();
//		dfsPartsList.add(0);
//		while( !theStack.isEmpty() ) // until stack empty,
//		{
//			// get an unvisited vertex adjacent to stack top
//			int v = getAdjUnvisitedVertex( theStack.peek() );
//			if(v == -1) // if no such vertex,
//				theStack.pop();
//			else // if it exists,
//			{
//				partsList.get(v).wasVisited = true; // mark it
//				//displayVertex(v); // display it
//				dfsPartsList.add(v);	// display it
//				theStack.push(v); // push it
//			}
//		} // end while
//		this.dfsPartsList=dfsPartsList;
//	// stack is empty, so we’re done
//		for(int j=0; j<nParts; j++) // reset flags
//			partsList.get(j).wasVisited = false;
//		} // end dfs
	// ------------------------------------------------------------
	// returns an unvisited vertex adj to v
	
//	public int getAdjUnvisitedVertex(int v)
//	{
//		for(int j=0; j<partsList.size(); j++)
//			if(adjMat[v][j]==1 && partsList.get(j).wasVisited==false)
//				return j;
//		return -1;
//	} // end getAdjUnvisitedVertex()
	
//	public void printdfsPartList()
//	{
//		for(int i=0;i<dfsPartsList.size();i++)
//		{
//			System.out.print(dfsPartsList.get(i)+"-");
//		}
//		System.out.println();
//	}
//	public void bfs() 							// breadth-first search
//	{ 											// begin at vertex 0
//		partsList.get(0).wasVisited = true; 	// mark it
//		displayVertex(0); 						// display it
//		theQueue.insert(0); 					// insert at tail
//		int v2;
//		ArrayList <Integer> bfsPartsList = new ArrayList<>();
//		bfsPartsList.add(0);
//		while( !theQueue.isEmpty() ) 			// until queue empty,
//		{
//			int v1 = theQueue.remove(); 		// remove vertex at head
//												// until it has no unvisited neighbors
//			while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
//			{ 	 						// get one,
//				partsList.get(v2).wasVisited = true; 	// mark it
//				displayVertex(v2); 	
//				bfsPartsList.add(v2);	// display it
//				theQueue.insert(v2); 					// insert it
//			} 											// end while(unvisited neighbors)
//		}											 	// end while(queue not empty)
//				this.bfsPartsList=bfsPartsList;
//														// queue is empty, so we’re done
//		for(int j=0; j<partsList.size(); j++) 
//		{
//			
//			partsList.get(j).wasVisited = false;	// reset flags
//		}
//			
//	}
//	
//	public void printBfsPartList()
//	{
//		for(int i=0;i<bfsPartsList.size();i++)
//		{
//			System.out.print(bfsPartsList.get(i));
//		}
//	}
//	public ArrayList<Integer> getBfsPartList()
//	{
//	
//			return bfsPartsList;
//		
//	}
//	// returns an unvisited vertex adj to v
//	public int getAdjUnvisitedVertex(int v)
//	{
//		//System.out.println("here");
//		for(int j=0; j<partsList.size(); j++)
//		{
//			//System.out.println(adjMat[v][j]);
//			if(adjMat[v][j]==1 && partsList.get(j).wasVisited==false)
//			{
//				return j;
//			}
//		}	
//		return -1;
//	} // end getAdjUnvisitedVertex()
	public int getCurrentSelectedPart()
	{
		return currentSelectedPart;
	}
	public void setCurrentSelectedPart(int currentSelectedPart )
	{
		this.currentSelectedPart=currentSelectedPart;
	}
	public void setPartsList(Part part) {
		partsList.add(part);
		ioList.add(0);
		
	}

	public void setLastSelectedPart(int lastSelectedPart) {
		this.lastSelectedPart=lastSelectedPart;
		
	}
//	
	
	

} //
