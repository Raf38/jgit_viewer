package view;
import java.awt.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;


public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JComboBox<String> combo = new JComboBox<String>();
	//JLabel label= new JLabel();
	model.FileTree _model = null;
	
	public FilePanel(model.Model model)
	{
		_model = model.file;
		//label = new JLabel("File Filter",SwingConstants.CENTER);
		//textLabel.setPreferredSize(new Dimension(300,100));
		//frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		
		combo.addItem("Hide deleted files");
		combo.addItem("Show deleted files");
		combo.addActionListener(new controller.FileFilterActionListener());
		combo.setPreferredSize(new Dimension(300,30));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(combo);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Top");
		try
		{
			top = _model.getDepotFileTree();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		JTree tree = new JTree(top);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}

}
