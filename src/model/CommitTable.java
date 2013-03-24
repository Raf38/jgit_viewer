package model;

import java.io.BufferedReader;
import java.util.*;

public class CommitTable
{
	private GitCommandLine _git = null;
	private String _pathFilter = "";
	
	public CommitTable()
	{
		_git = new GitCommandLine();
	}
	
	public Vector<Vector<String>> getCommitData()
	{
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		try
		{
			BufferedReader reader = _git.execCommand("log --pretty=format:\"%h,%an,%ar,%s\" -- "+_pathFilter);
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				data.add(data.size(),new Vector<String>(Arrays.asList(line.trim().split(","))));
			}
		}
		catch (Exception e) {}
		return data;
	}
	
	public void setPathFilter(String path)
	{
		_pathFilter = path;
	}
}
