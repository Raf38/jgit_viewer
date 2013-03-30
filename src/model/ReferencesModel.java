package model;

import java.io.BufferedReader;
import java.util.*;

public class ReferencesModel
{
	private GitCommandLine _git = null;
	private String _currentReference = "HEAD";
	
	public ReferencesModel()
	{
		_git = new GitCommandLine();
		
		try
		{
			Vector<String> gitData = _git.execCommand("branch --all");
			for (String line : gitData)
			{
				if (line.substring(0, 2).equals("* "))
				{
					_currentReference = line.substring(2,line.length()).trim();
					break;
				}
			}
		}
		catch (Exception e) {}
	}
	
	public Vector<String> getReferences()
	{
		Vector<String> data = new Vector<String>();
		try
		{
			Vector<String> gitData = _git.execCommand("branch --all");
			for (String line : gitData)
			{
				data.add(data.size(),line.substring(2,line.length()).trim());
			}
		}
		catch (Exception e) {}
		return data;
	}
	
	public String getCurrentReference()
	{
		return _currentReference;
	}

	public void setCurrentReference(String newRef) {
		// TODO Auto-generated method stub
		_currentReference = newRef;
	}
}
