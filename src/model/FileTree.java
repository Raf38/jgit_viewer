package model;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import java.io.*;
import java.util.Vector;

public class FileTree
{
	private boolean _showDeletedFiles = false;
	private GitCommandLine _git = null;
	
	public FileTree()
	{
		_git = new GitCommandLine();
	}
	
	public void showDeletedFiles(boolean showDeleted)
	{
		_showDeletedFiles = showDeleted;
	}
	
	public DefaultMutableTreeNode getDepotFileTree() throws Exception
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Repository");
		Vector<String> data = _git.execCommand("ls-tree --full-tree -r --name-only -- HEAD");
		for (String line : data)
		{
			insertPathIntoTree(top, line.trim());
		}
		
		if (_showDeletedFiles)
		{
			data = _git.execCommand("log --diff-filter=D --summary HEAD");
			for (String line : data)
			{
				String tokens[] = line.split(" ");
				
				System.out.println(line);
				try
				{
					if (tokens.length == 5 && tokens[1].equals("delete") && tokens[2].equals("mode"))
					{
						insertPathIntoTree(top, tokens[4].trim());
					}
				} catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
		}
		
		return top;
	}
	
	private void insertDeletedFiles(DefaultMutableTreeNode top)
	{
		
	}
	
	private void insertPathIntoTree(DefaultMutableTreeNode tree, String path)
	{
		DefaultMutableTreeNode current = tree;
		String line = path.trim();
		String dirs[] = line.split("/");
		for (String s : dirs)
		{
			DefaultMutableTreeNode child = null;
			try
			{
				child = (DefaultMutableTreeNode)current.getFirstChild();
			} catch (Exception e) {}
			while(child != null)
			{
				if (s.equals(child.getUserObject()))
				{
					current = child;
					break;
				} else
				{
					child = child.getNextSibling();
				}
			}
			if (child == null)
			{
				DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(s);
				current.add(newChild);
				current = newChild;
			}
		}
	}
}
