package model;

import java.util.*;

public class ChangesModel
{
	private GitCommandLine _git = null;
	private String _pathFilter = "";
	private String _startReference = "";
	private String _endReference = "";
	
	public ChangesModel()
	{
		_git = new GitCommandLine();
	}
	
	public Vector<Vector<String>> getChanges()
	{
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		try
		{
			if (_startReference.length() > 0 && _endReference.length() > 0)
			{
				Vector<String> gitOutput = _git.execCommand("diff-tree --no-commit-id -r --name-status "+_startReference+"^1.."+_endReference+" -- "+_pathFilter);
				for (String line : gitOutput)
				{
					data.add(data.size(),new Vector<String>(Arrays.asList(line.trim().split("\t"))));
				}
			}
		}
		catch (Exception e) {}
		return data;
	}
	
	public void setFirstReference(String ref)
	{
		_startReference = ref;
	}
	
	public void setLastReference(String ref)
	{
		_endReference = ref;
	}
	
	public void setOnlyReference(String ref)
	{
		_startReference = ref;
		_endReference = ref;
	}
	
	public void setPathFilter(String path)
	{
		if (path == null)
		{
			_pathFilter = "";
		} else
		{
			_pathFilter = path;
		}
	}
}
