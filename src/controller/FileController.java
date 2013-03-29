package controller;
import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class FileController implements ActionListener,TreeSelectionListener {

	private model.Model _model;
	private view.MainWindow _view;
	
	public FileController(model.Model model, view.MainWindow view)
	{
		_view = view;
		_model = model;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(e.getActionCommand().equals("ExistingToggle"))
			{
				_model.file.showExistingFiles(!_model.file.showExistingFiles());
			}
			_view._filePanel.refresh();
		}
		catch (Exception ex) {}
		
		try
		{
			if(e.getActionCommand().equals("DeletedToggle"))
			{
				_model.file.showDeletedFiles(!_model.file.showDeletedFiles());
			}
			_view._filePanel.refresh();
		}
		catch (Exception ex) {}
		System.out.println("event "+e.getActionCommand());
	}
	
	public void valueChanged(TreeSelectionEvent e)
	{
		TreePath tp = e.getPath();
		Object[] treeNodes = tp.getPath();
		String path = "";
		for (int n=1;n<treeNodes.length;n++)
		{
			if (n>1) { path += "/"; }
			path += (String)((DefaultMutableTreeNode)treeNodes[n]).getUserObject();
		}
		_model.commit.setPathFilter(path);
		_view._commitPanel.refresh();
	}

}
