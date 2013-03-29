package view;
import java.awt.*;
import javax.swing.*;


public class MainWindow extends JFrame {

	private JPanel 			_mainPanel;
	public FilePanel 		_filePanel;
	public CommitPanel 	_commitPanel;
	private ReferencesPanel _refsPanel;
	private JSplitPane 		_splitPane;
	
	public MainWindow(model.Model model)
	{
		setTitle("JGit Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_filePanel = new FilePanel(model, new controller.FileController(model,this));
		_commitPanel = new CommitPanel(model);
		_refsPanel = new ReferencesPanel(model);
		
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
