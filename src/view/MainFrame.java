package view;
import java.awt.*;

import javax.swing.*;


public class MainFrame extends JFrame {

	private JPanel 			_mainPanel;
	public FilePanel 		_filePanel;
	public CommitPanel 	_commitPanel;
	private ReferencesPanel _refsPanel;
	public ChangesPanel	_changesPanel;
	private JSplitPane 		_splitPane;
	private JSplitPane 		_splitPane2;
	
	public MainFrame(model.Model model)
	{
		setTitle("JGit Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.changes.getChanges();
		
		_refsPanel = new ReferencesPanel(model, new controller.ReferencesController(model, this));
		_filePanel = new FilePanel(model, new controller.FileController(model,this));
		_commitPanel = new CommitPanel(model, new controller.CommitController(model,this));
		_changesPanel = new ChangesPanel(model, new controller.ChangesController(model, this));
		
		_splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		_splitPane2.setTopComponent(_commitPanel);
		_splitPane2.setBottomComponent(_changesPanel);
		_splitPane2.setContinuousLayout(true);
		_splitPane2.setDividerLocation(0.5f);
		_splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		_splitPane.setLeftComponent(_filePanel);
		_splitPane.setRightComponent(_splitPane2);
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
