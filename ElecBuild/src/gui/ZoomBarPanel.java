package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ZoomBarPanel extends JToolBar implements ActionListener {

	private JButton zoomButton;

	private ZoomBarListener buttonListener;

	public ZoomBarPanel() {
		setOrientation(HORIZONTAL);
		

		zoomButton = new JButton("Zoom");
		zoomButton.setToolTipText("Zoom");

		zoomButton.addActionListener(this);
		 //setLayout(new FlowLayout(FlowLayout.));
		add(zoomButton);

	}

	public void setZoomBarListener(ZoomBarListener listener) {
		this.buttonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == zoomButton) {
			  if(buttonListener != null) {
					 
				  buttonListener.stringEmitted("Zoom");
			  }
			
		}

	}
}
