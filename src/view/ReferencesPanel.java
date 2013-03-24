package view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ReferencesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JComboBox<String> _combo = null;
	model.Model _model = null;
	
	public ReferencesPanel(model.Model model)
	{
		_model = model;
		
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
		//_combo.addActionListener(new controller.FileFilterActionListener());
		_combo.setPreferredSize(new Dimension(600,30));
		add(_combo, BorderLayout.CENTER);
	}	

}
