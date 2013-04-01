package view;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class ChangesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private model.Model _model;
	private JTable _table = null;
	
	public ChangesPanel(model.Model model)
	{		
		_model = model;
		setLayout(new BorderLayout());
		Vector<String> headers = new Vector<String>();
		headers.add(headers.size(),"Modification");
		headers.add(headers.size(),"Path");
		Vector<Vector<String>> data = _model.changes.getChanges();
		_table = new JTable(data,headers);
		JScrollPane scrollPane = new JScrollPane(_table);
		add(scrollPane);
	}
	
	public void refresh()
	{
		DefaultTableModel tableModel = (DefaultTableModel)_table.getModel();
		int numRows = tableModel.getRowCount();
		for (int n=0;n<numRows;n++)
		{
			tableModel.removeRow(0);
		}
		Vector<Vector<String>> data = _model.changes.getChanges();
		for (Vector<String> row : data)
		{
			tableModel.addRow(row);
		}
		tableModel.fireTableDataChanged();
	}
}
