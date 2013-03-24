package controller;

public class Controller {

	public FileController file = null;
	
	public Controller(model.Model model, view.MainWindow view)
	{
		file = new FileController(model, view);
	}
}
