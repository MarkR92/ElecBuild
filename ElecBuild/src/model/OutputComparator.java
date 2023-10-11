package model;

import java.util.Comparator;

public class OutputComparator implements Comparator<OUTPUT> {
	@Override
	public int compare(OUTPUT o1, OUTPUT o2) {
		return o1.getLabelName().compareTo(o2.getLabelName());
		
	}
}
