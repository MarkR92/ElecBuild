package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	
	private JLabel infolabel;
	
	
	
	public InfoPanel(){
		
		infolabel= new JLabel("Current Selection: None");
		
		add(infolabel);
	}

	public void setText(String result) {
		
		infolabel.setText("Current Selection: "+result);
		
		
	}
	

}
