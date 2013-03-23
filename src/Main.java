import javax.swing.UIManager;

import model.GitCommandLine;

import view.MainWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		model.GitCommandLine git = new GitCommandLine();
		try
		{
			git.execCommand("ls-tree");
		} catch (Exception e) {}
		MainWindow w = new MainWindow();
	}

}
