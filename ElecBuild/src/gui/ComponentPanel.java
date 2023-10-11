package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
public class ComponentPanel extends JPanel implements ActionListener {


	//private Dimension preferredSize = (new Dimension(200, 200));
	private JTree componentsTree;

	private SideBarListener sideBarListener;
	public ComponentPanel() {
		
		componentsTree = new JTree(createTree());
		componentsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		componentsTree.addTreeSelectionListener(new TreeSelectionListener()
		{
			
			public void valueChanged(TreeSelectionEvent e){
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)componentsTree.getLastSelectedPathComponent();
				


				try {
					Object userObject =node.getUserObject();
				sideBarListener.stringEmitted((String)userObject);
				}catch(Exception ex)
				{
					
				}
			}
			
		});
		
		setLayout(new BorderLayout());
		add(new JScrollPane(componentsTree), BorderLayout.WEST);
		
	}
	public void setSideBarListener(SideBarListener listener) {
		this.sideBarListener = listener;
	}
	
	private DefaultMutableTreeNode createTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Components");
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("Boolean");
		DefaultMutableTreeNode component1 = new DefaultMutableTreeNode("AND");
		DefaultMutableTreeNode component2 = new DefaultMutableTreeNode("OR");
		DefaultMutableTreeNode component3 = new DefaultMutableTreeNode("XOR");
		DefaultMutableTreeNode component4 = new DefaultMutableTreeNode("NOT");
		
		

		branch1.add(component1);
		branch1.add(component2);
		branch1.add(component3);
		branch1.add(component4);
		
		

		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("I/O");
		DefaultMutableTreeNode component8 = new DefaultMutableTreeNode("Input");
		DefaultMutableTreeNode component9 = new DefaultMutableTreeNode("Output");
		
		DefaultMutableTreeNode component10 = new DefaultMutableTreeNode("Switch");
		DefaultMutableTreeNode component11 = new DefaultMutableTreeNode("LED");
		DefaultMutableTreeNode component12 = new DefaultMutableTreeNode("Wire");

		branch2.add(component8);
		branch2.add(component9);
		branch2.add(component10);
		branch2.add(component11);
		branch2.add(component12);

		top.add(branch1);
		top.add(branch2);

		return top;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
