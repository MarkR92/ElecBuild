package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class IOPopupPanel extends JPanel {
	
	private JLabel iolabel;
	private JTextField label;
	private String name;

	public IOPopupPanel(String currentLabel) {
		JPanel iopanel = new JPanel();
		
		iolabel= new JLabel("I/O Name: ");
		label = new JTextField(3);
		label.setText(currentLabel);
		
		iopanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.weightx=1;
		gc.weighty=1;
		
		gc.gridx = 0;
		gc.gridy = 0;
	
		gc.fill = GridBagConstraints.NONE;
		
		gc.anchor = GridBagConstraints.LINE_END;
		iopanel.add(iolabel,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		iopanel.add(label,gc);
		
		
		
		
		int action = JOptionPane.showConfirmDialog(null,iopanel, "I/O Info",JOptionPane.OK_CANCEL_OPTION);
		
		if (action == JOptionPane.OK_OPTION)
		{
			 this.name = label.getText();
			 //this.angle=Double.parseDouble(anglefield.getText());
		}
		else
		{
			this.name = currentLabel;
			
		}
	}

	public String getName()
	{
		return name;
	}

}
