package controller;
import java.awt.event.*;

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
		System.out.println("event");
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
