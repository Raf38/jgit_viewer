package view;

import java.awt.*;
import javax.swing.*;

public class ReferencesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ReferencesPanel()
	{
		JLabel label = new JLabel("References Panel",SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(300,30));
		add(label, BorderLayout.CENTER);
	}	

}
