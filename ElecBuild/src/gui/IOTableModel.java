package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.INPUT;
import model.OUTPUT;
import model.Part;

@SuppressWarnings("serial")

public class IOTableModel extends AbstractTableModel {

	private ArrayList<Part> inputsList= new ArrayList<>();
	
	
	public IOTableModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return  inputsList.get(column).getLabelName();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return inputsList.size();
	}

	public ArrayList<Part> getIOPartsList() {
		return inputsList;
	}

	public void setInputsList(ArrayList<Part> inputsList) {
		this.inputsList = inputsList;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
			switch(rowIndex)
			{
			
			case 0:return inputsList.get(columnIndex).getOutput();
			
			}
			return null;
		
		
	}

}
