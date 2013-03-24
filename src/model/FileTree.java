package model;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import java.io.*;

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
		BufferedReader reader = _git.execCommand("ls-tree --full-tree -r --name-only -- HEAD");
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			DefaultMutableTreeNode current = top;
			line = line.trim();
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
			System.out.print(line);
		}
		return top;
	}
}
