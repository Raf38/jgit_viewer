package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GitDiffTool implements Runnable
{
	private String _path;
	private String _firstRef;
	private String _lastRef;
	
	public GitDiffTool(String path, String firstRef, String lastRef)
	{
		_path = path;
		_firstRef = firstRef;
		_lastRef = lastRef;
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		GitCommandLine gc = new GitCommandLine();
		try
		{
			gc.execCommand("difftool "+_firstRef+"^1.."+_lastRef+" -- "+_path);
		}
		catch (Exception e) {}
	}
}
