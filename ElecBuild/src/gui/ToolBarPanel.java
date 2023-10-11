package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarPanel extends JToolBar implements ActionListener {
	private JButton saveButton;
	private JButton drawButton;

	private ToolBarListener buttonListener;

	public ToolBarPanel() {
		//setBorder(BorderFactory.createEtchedBorder());
		
		saveButton = new JButton("Save");
		saveButton.setToolTipText("Save");
		drawButton = new JButton("Draw");
		drawButton.setToolTipText("Draw");

		saveButton.addActionListener(this);
		drawButton.addActionListener(this);
		
		add(saveButton);
		add(drawButton);

	}

	public void setToolBarListener(ToolBarListener listener) {
		this.buttonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == saveButton) {
			  if(buttonListener != null) {
					 
				  buttonListener.stringEmitted("Save");
			  }
			
		}
		else if (clicked == drawButton) {
			  if(buttonListener != null) {
					 
				  buttonListener.stringEmitted("Draw");
			  }
			
		}

	}
}
