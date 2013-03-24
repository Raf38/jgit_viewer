package view;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class CommitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private model.Model _model;
	
	public CommitPanel(model.Model model)
	{		
		_model = model;
		Vector<String> headers = new Vector<String>();
		headers.add(headers.size(),"Commit");
		headers.add(headers.size(),"Author");
		headers.add(headers.size(),"When");
		headers.add(headers.size(),"Message");
		
		Vector<Vector<String>> data = model.commit.getCommitData();
		
		JTable tree = new JTable(data,headers);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}
}
