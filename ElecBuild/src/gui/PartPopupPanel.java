package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PartPopupPanel extends JPanel {

	private JComboBox<String> partType;

	private JComboBox<String> numOfInputs;
	
	private JLabel invertLabel;
	private JLabel inputLabel;
	
	private boolean invert=false;
	private int numinputs=2;
	
	public void createPopup() {
		
		JPanel partpanel = new JPanel();
		partType = new JComboBox<String>();
		numOfInputs = new JComboBox<String>();
		
		invertLabel =new JLabel("Invert?: ");
		inputLabel = new JLabel("No. of Inputs: ");
		
		DefaultComboBoxModel<String> partmodel = new DefaultComboBoxModel<String>();
		partmodel.addElement("No");
		partmodel.addElement("Yes");
		
		DefaultComboBoxModel<String> nummodel = new DefaultComboBoxModel<String>();
		nummodel.addElement("2");
		nummodel.addElement("3");
		nummodel.addElement("4");
		nummodel.addElement("5");
	
		
		partType.setModel(partmodel);
		numOfInputs.setModel(nummodel);
		
		partpanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx=1;
		gc.weighty=1;
		
		gc.gridx = 0;
		gc.gridy = 0;
	
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.LINE_END;
		partpanel.add(invertLabel,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		partpanel.add(partType,gc);
//---------------------------------------------------//		
		gc.weightx=1;
		gc.weighty=1;
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.anchor = GridBagConstraints.LINE_END;
		partpanel.add(inputLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		
		gc.anchor = GridBagConstraints.LINE_START;
		partpanel.add(numOfInputs,gc);
		
		
		int action = JOptionPane.showConfirmDialog(null,partpanel, "Configure Part",JOptionPane.OK_CANCEL_OPTION);
		
		if (action == JOptionPane.OK_OPTION) {
			
			 if(partmodel.getSelectedItem().equals("Yes"))
			 {
				 invert=true;
			 }
			 else
			 {
				 invert=false;
			 }
			 numinputs=Integer.parseInt((String) nummodel.getSelectedItem());
		
			
		}
	}
	public boolean isInvert( ) {
		return invert;
	}

	public int getNumInputs( ) {
		return numinputs;
	}

}
