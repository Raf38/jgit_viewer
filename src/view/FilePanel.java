package view;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import model.FileTreeNode;


public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JCheckBox _existingCheckbox = null;
	JCheckBox _deletedCheckbox = null;
	JTree _tree = null;
	model.Model _model = null;
	controller.FileController _controller = null;
	
	public FilePanel(model.Model model, controller.FileController controller)
	{
		_model = model;
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
		
		_tree = new JTree(_model.file.getTreeModel());
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
		_existingCheckbox.setSelected(_model.file.showExistingFiles());
		_deletedCheckbox.setSelected(_model.file.showDeletedFiles());
		
		FileTreeNode rootNode = (FileTreeNode)_tree.getModel().getRoot();
		Enumeration e = _tree.getExpandedDescendants(new TreePath(rootNode));
		Vector<String> expandedPaths = new Vector<String>();
		if (e != null)
		{
			while (e.hasMoreElements())
			{
				TreePath tp = (TreePath)e.nextElement();
				FileTreeNode tn = (FileTreeNode)tp.getLastPathComponent();
				String s = tn.getPathString();
				expandedPaths.add(tn.getPathString());
			}
		}	
		String selectedPath = null;
		try
		{
			FileTreeNode tn = (FileTreeNode)_tree.getSelectionPath().getLastPathComponent();
			selectedPath = tn.getPathString();
		}
		catch (Exception ex) {}
		
		_model.file.updateTreeModel(_model.references.getCurrentReference());
		
		rootNode = (FileTreeNode)_tree.getModel().getRoot();
		for(String s : expandedPaths)
		{
			try
			{
				TreePath tp = new TreePath(rootNode.getNodeFromPath(s).getPath());
				_tree.expandPath(tp);
			} catch (Exception ex) { }
		}
		try
		{
			TreePath tp = new TreePath(rootNode.getNodeFromPath(selectedPath).getPath());
			_tree.setSelectionPath(tp);
		}
		catch (Exception ex) {}
	}
}
