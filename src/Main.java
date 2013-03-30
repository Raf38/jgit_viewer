import javax.swing.UIManager;

import model.*;

import view.MainFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame w = new MainFrame(new model.Model());
	}

}
