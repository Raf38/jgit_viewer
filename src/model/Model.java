package model;

public class Model
{
	public CommitTable commit;
	public FileTree file;
	
	public Model()
	{
		commit = new CommitTable();
		file = new FileTree();
	}
}
