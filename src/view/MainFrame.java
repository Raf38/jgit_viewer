package view;
import java.awt.*;

import javax.swing.*;


public class MainFrame extends JFrame {

	private JPanel 			_mainPanel;
	public FilePanel 		_filePanel;
	public CommitPanel 	_commitPanel;
	private ReferencesPanel _refsPanel;
	private JSplitPane 		_splitPane;
	
	public MainFrame(model.Model model)
	{
		setTitle("JGit Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		_filePanel = new FilePanel(model, new controller.FileController(model,this));
		_commitPanel = new CommitPanel(model);
		_refsPanel = new ReferencesPanel(model, new controller.ReferencesController(model, this));
		
		_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		_splitPane.setLeftComponent(_filePanel);
		_splitPane.setRightComponent(_commitPanel);
		_splitPane.setContinuousLayout(true);
		_mainPanel = (JPanel)getContentPane();
//		_mainPanel.setLayout(new BorderLayout());
		_mainPanel.add(_refsPanel,BorderLayout.PAGE_START);
		_mainPanel.add(_splitPane);
		
		
		//getContentPane().add(_mainPanel);
		
		//display window
		setLocationRelativeTo(null);
		setPreferredSize(new Dimension(800,600));
		pack();
		setVisible(true);
	}

}
