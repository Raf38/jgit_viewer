package view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ReferencesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JComboBox<String> _combo = null;
	model.Model _model = null;
	
	public ReferencesPanel(model.Model model, controller.ReferencesController controller)
	{
		_model = model;
		setLayout(new BorderLayout());
		_combo = new JComboBox<String>();
		String currentReference = _model.references.getCurrentReference();
		Vector<String> references = _model.references.getReferences();
		for (String s : references)
		{
			_combo.addItem(s);
			if (s.equals(currentReference))
			{
				_combo.setSelectedIndex(_combo.getItemCount()-1);
			}
		}
		_combo.setName("combo name");
		_combo.addActionListener(controller);
		add(_combo);
	}	

}
