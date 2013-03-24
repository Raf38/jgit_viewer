package model;

public class Model
{
	public CommitTable commit;
	public FileTree file;
	public References references;
	
	public Model()
	{
		commit = new CommitTable();
		file = new FileTree();
		references = new References();
	}
}
