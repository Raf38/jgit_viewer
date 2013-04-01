package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GitCommandLine
{
	ProcessBuilder _pb;
	
	public GitCommandLine()
	{
		_pb = new ProcessBuilder("null");
	}
	
	public Vector<String> execCommand(String arguments) throws InterruptedException, IOException, Exception
	{
		String command = "/usr/local/bin/git "+arguments;
		_pb.command(command.split("\\s+"));
		_pb.redirectErrorStream(true);
		
		Process p = null;
		try
		{
			p = _pb.start();
		} catch (IOException e)
		{
			System.err.println("IOException: "+e.getMessage());
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		Vector<String> output = new Vector<String>();
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			output.add(line);
		}
		p.waitFor();
		if (p.exitValue()!=0)
		{
			for(String s : output)
			{
				System.err.println(s);
			}
			throw new Exception();
		}
		return output;
	}
}
