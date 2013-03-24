import javax.swing.UIManager;

import model.*;

import view.MainWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow w = new MainWindow(new model.Model());
	}

}
