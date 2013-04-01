package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;

public class CommitController implements ListSelectionListener {

	private model.Model _model;
	private view.MainFrame _view;
	
	public CommitController(model.Model model, view.MainFrame view)
	{
		_model = model;
		_view = view;
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting()) { return; }
		_model.changes.setFirstReference(_view._commitPanel.getFirstSelectedCommit());
		_model.changes.setLastReference(_view._commitPanel.getLastSelectedCommit());
		_view._changesPanel.refresh();
	}

}
