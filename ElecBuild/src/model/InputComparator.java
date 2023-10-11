package model;

import java.util.Comparator;

public class InputComparator implements Comparator<INPUT> {

	

	@Override
	public int compare(INPUT o1, INPUT o2) {
		return o1.getLabelName().compareTo(o2.getLabelName());
		
	}



}
