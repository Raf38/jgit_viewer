package view;
import java.awt.*;
import javax.swing.*;


public class MainWindow {

	/**
	 * @param args
	 */
	public MainWindow()
	{
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("I'm a label",SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(300,100));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		
		//display window
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

}
