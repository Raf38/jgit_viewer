package model;

public class Model
{
	public CommitModel commit;
	public FileModel file;
	public ReferencesModel references;
	
	public Model()
	{
		commit = new CommitModel();
		file = new FileModel();
		references = new ReferencesModel();
	}
}
