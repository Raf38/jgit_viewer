package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.GitDiffTool;

public class ChangesController implements MouseListener {

	private model.Model _model;
	private view.MainFrame _view;
	
	public ChangesController(model.Model model, view.MainFrame view)
	{
		_model = model;
		_view = view;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2)
		{
			String path = _view._changesPanel.getSelectedPath();
			String firstRef = _model.changes.getFirstReference();
			String lastRef = _model.changes.getLastReference();
			new GitDiffTool(path, firstRef, lastRef);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
