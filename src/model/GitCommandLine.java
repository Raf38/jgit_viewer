package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class GitCommandLine
{
	ProcessBuilder _pb;
	
	public GitCommandLine()
	{
		_pb = new ProcessBuilder("null");
	}
	
	public BufferedInputStream execCommand(String arguments) throws InterruptedException, IOException, Exception
	{
		String command = "/usr/local/bin/git "+arguments;
		_pb.command(command.split("\\s+"));
		
		Process p = null;
		try
		{
			p = _pb.start();
		} catch (IOException e)
		{
			System.err.println("IOException: "+e.getMessage());
		}
		p.waitFor();
		if (p.exitValue()!=0)
		{
			InputStream istream = p.getErrorStream();
			//BufferedInputStream bistream = new BufferedInputStream(istream);
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while((bytesRead = istream.read(buffer))>0)
			{
				System.err.write(buffer, 0, bytesRead);
			}
			istream = p.getInputStream();
			while((bytesRead = istream.read(buffer))>0)
			{
				System.err.write(buffer, 0, bytesRead);
			}
			throw new Exception();
		}
		return new BufferedInputStream(p.getInputStream());
	}
}
