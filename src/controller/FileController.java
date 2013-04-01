package controller;
import java.awt.event.*;

import javax.swing.JCheckBox;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.FileTreeNode;

public class FileController implements ActionListener,TreeSelectionListener {

	private model.Model _model;
	private view.MainFrame _view;
	
	public FileController(model.Model model, view.MainFrame view)
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
		FileTreeNode node = (FileTreeNode) e.getPath().getLastPathComponent();
		String path = node.getPathString();
		_model.commit.setPathFilter(path);
		_model.changes.setPathFilter(path);
		_view._commitPanel.refresh();
		_view._changesPanel.refresh();
	}

}
