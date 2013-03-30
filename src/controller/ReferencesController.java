package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class ReferencesController implements ActionListener {

	private model.Model _model;
	private view.MainFrame _view;
	
	public ReferencesController(model.Model model, view.MainFrame view)
	{
		_view = view;
		_model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox combo = (JComboBox<String>)e.getSource();
		String newRef = (String)combo.getItemAt(combo.getSelectedIndex());
		_model.references.setCurrentReference(newRef);
		_view._filePanel.refresh();
		_view._commitPanel.refresh();
	}

}
