package view;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class CommitPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private model.Model _model;
	private JTable _table = null;
	private Vector<Vector<String>> _tableData = null;
	private TableModel _tableModel = null;
	
	public CommitPanel(model.Model model, controller.CommitController controller)
	{		
		_model = model;
		setLayout(new BorderLayout());
		Vector<String> headers = new Vector<String>();
		headers.add(headers.size(),"Commit");
		headers.add(headers.size(),"Author");
		headers.add(headers.size(),"When");
		headers.add(headers.size(),"Message");
		
		_tableData = model.commit.getCommitData(_model.references.getCurrentReference());
		_table = new JTable(_tableData,headers) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		_table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		_table.getSelectionModel().addListSelectionListener(controller);
		JScrollPane scrollPane = new JScrollPane(_table);
		add(scrollPane);
	}
	
	public String getFirstSelectedCommit()
	{
		try
		{
			int[] index = _table.getSelectedRows();
			return (String) _table.getModel().getValueAt(index[index.length-1], 0);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}
	
	public String getLastSelectedCommit()
	{
		try
		{
			int[] index = _table.getSelectedRows();
			return (String) _table.getModel().getValueAt(index[0], 0);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}
		
	public void refresh()
	{
		String firstCommit = getFirstSelectedCommit();
		String lastCommit = getLastSelectedCommit();
		
		DefaultTableModel tableModel = (DefaultTableModel)_table.getModel();
		int numRows = tableModel.getRowCount();
		for (int n=0;n<numRows;n++)
		{
			tableModel.removeRow(0);
		}
		_tableData = _model.commit.getCommitData(_model.references.getCurrentReference());
		int firstSelectedRow = -1;
		int lastSelectedRow = -1;
		for (Vector<String> row : _tableData)
		{
			tableModel.addRow(row);
			if (row.get(0).equals(firstCommit))
			{
				firstSelectedRow = tableModel.getRowCount()-1;
			}
			if (row.get(0).equals(lastCommit))
			{
				lastSelectedRow = tableModel.getRowCount()-1;
			}
		}
		tableModel.fireTableDataChanged();
		if (firstSelectedRow >= 0 && lastSelectedRow >= 0)
		{
			_table.getSelectionModel().setSelectionInterval(firstSelectedRow, lastSelectedRow);
		}
	}
}
