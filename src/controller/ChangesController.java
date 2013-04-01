package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChangesController implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting()) { return; }
		System.out.println("first: "+e.getFirstIndex()+" last: "+e.getLastIndex());
	}

}
