package model;

import java.io.BufferedReader;
import java.util.*;

public class References
{
	private GitCommandLine _git = null;
	
	public References()
	{
		_git = new GitCommandLine();
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
		try
		{
			Vector<String> gitData = _git.execCommand("branch --all");
			for (String line : gitData)
			{
				if (line.substring(0, 2).equals("* "))
				{
					return line.substring(2,line.length()).trim();
				}
			}
		}
		catch (Exception e) {}
		return "";
	}
}
