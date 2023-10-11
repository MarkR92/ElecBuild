package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements ActionListener{
	
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu windowMenu;
	private JMenu viewMenu;
	
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem saveasItem;
	private JMenuItem exitItem;
	private JCheckBoxMenuItem viewItem;
	
	
	
	private MenuBarListener menuItemListener;

	  public MenuBar() {
		  	  	
		    fileMenu = new JMenu("File");
		    editMenu = new JMenu("Edit");
			windowMenu = new JMenu("Window");
			
			newItem = new JMenuItem("New");
			openItem = new JMenuItem("Open File...");
			saveItem = new JMenuItem("Save");
			saveasItem = new JMenuItem("Save As...");
			exitItem = new JMenuItem("Exit");

			viewMenu= new JMenu("View");
			
			viewItem= new JCheckBoxMenuItem("I/O Table");
			
			viewMenu.add(viewItem);
			windowMenu.add(viewMenu);

			fileMenu.add(newItem);
			fileMenu.add(openItem);
			fileMenu.addSeparator();
			fileMenu.add(saveItem);
			fileMenu.add(saveasItem);
			fileMenu.addSeparator();
			fileMenu.add(exitItem);
		
			
			newItem.addActionListener(this);
			openItem.addActionListener(this);
			saveItem.addActionListener(this);
			saveasItem.addActionListener(this);
			exitItem.addActionListener(this);
			viewItem.addActionListener(this);
			
			fileMenu.setMnemonic(KeyEvent.VK_F);
			
			exitItem.setMnemonic(KeyEvent.VK_X);
			
			exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
			
	
			add(fileMenu);
			add(editMenu);
			add(windowMenu);
	    }
	  


	public void setMenuBarListener(MenuBarListener menuBarListener) {
		this.menuItemListener = menuBarListener;
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem isclicked = (JMenuItem) e.getSource();
	
		if(isclicked == newItem) {
		
			if(menuItemListener != null) {
				menuItemListener.stringEmitted("New");
		}
	}
	
		if(isclicked == openItem) {
	
			if(menuItemListener != null) {
				menuItemListener.stringEmitted("Open");
		}
	}
		if(isclicked == saveItem) {
	
			if(menuItemListener != null) {
				menuItemListener.stringEmitted("Save");
	}
}
		if(isclicked == saveasItem) {
	
			if(menuItemListener != null) {
				menuItemListener.stringEmitted("SaveAs");
	}

}
		if(isclicked == exitItem) {
	
			if(menuItemListener != null) {
				menuItemListener.stringEmitted("Exit");
	}
		}
			if(viewItem.isSelected()) {
				
				if(menuItemListener != null) {
					menuItemListener.stringEmitted("Show I/O");
				}
			}else if(!viewItem.isSelected())
			{
				if(menuItemListener != null) {
					menuItemListener.stringEmitted("Hide I/O");
				}
				
			}
		

	
}





	


}

