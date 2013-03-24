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
			BufferedReader reader = _git.execCommand("branch --all");
			String line = null;
			while ((line = reader.readLine()) != null)
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
			BufferedReader reader = _git.execCommand("branch --all");
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				String sub = line.substring(0, 2);
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
