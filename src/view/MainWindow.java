package view;
import java.awt.*;
import javax.swing.*;


public class MainWindow extends JFrame {

	private JPanel 			_mainPanel;
	private FilePanel 		_filePanel;
	private CommitPanel 	_commitPanel;
	private ReferencesPanel _refsPanel;
	private JSplitPane 		_splitPane;
	
	public MainWindow(model.Model model)
	{
		setTitle("JGit Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_filePanel = new FilePanel(model);
		_commitPanel = new CommitPanel(model);
		_refsPanel = new ReferencesPanel();
		
		_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,_filePanel,_commitPanel);
		_mainPanel = (JPanel)getContentPane();
		_mainPanel.setLayout(new BoxLayout(_mainPanel,BoxLayout.Y_AXIS));
		_mainPanel.add(_refsPanel);
		_mainPanel.add(_splitPane);
		
		
		//getContentPane().add(_mainPanel);
		
		//display window
		setLocationRelativeTo(null);
		setPreferredSize(new Dimension(800,600));
		pack();
		setVisible(true);
	}

}
