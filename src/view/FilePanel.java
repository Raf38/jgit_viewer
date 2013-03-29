package view;
import java.awt.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JCheckBox _existingCheckbox = null;
	JCheckBox _deletedCheckbox = null;
	JTree _tree = null;
	model.FileTree _model = null;
	controller.FileController _controller = null;
	
	public FilePanel(model.Model model, controller.FileController controller)
	{
		_model = model.file;
		_controller = controller;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		_existingCheckbox = new JCheckBox();
		_existingCheckbox.setText("Show Existing Files");
		_existingCheckbox.setActionCommand("ExistingToggle");
		_existingCheckbox.addActionListener(_controller);
		
		
		_deletedCheckbox = new JCheckBox();
		_deletedCheckbox.setText("Show Deleted Files");
		_deletedCheckbox.setActionCommand("DeletedToggle");
		_deletedCheckbox.addActionListener(_controller);
		
		_tree = new JTree(_model.getTreeModel());
		_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		_tree.addTreeSelectionListener(_controller);
		JScrollPane scrollPane = new JScrollPane(_tree);
		
		refresh();
		
		add(_existingCheckbox);
		add(_deletedCheckbox);
		add(scrollPane);
	}
	
	public void refresh()
	{
		_existingCheckbox.setSelected(_model.showExistingFiles());
		_deletedCheckbox.setSelected(_model.showDeletedFiles());
		_model.updateTreeModel();
	}
}
