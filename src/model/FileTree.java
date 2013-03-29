package model;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import java.io.*;
import java.util.Vector;

public class FileTree
{
	private boolean _showDeletedFiles = false;
	private boolean _showExistingFiles = true;
	private GitCommandLine _git = null;
	private DefaultTreeModel _treeModel;
	private DefaultMutableTreeNode _treeNode;
	
	public FileTree()
	{
		_git = new GitCommandLine();
		_treeNode = new DefaultMutableTreeNode("Repository");
		_treeModel = new DefaultTreeModel(_treeNode);
	}
	
	public void showDeletedFiles(boolean showDeleted)
	{
		_showDeletedFiles = showDeleted;
	}
	public boolean showDeletedFiles()
	{
		return _showDeletedFiles;
	}
	public void showExistingFiles(boolean showExisting)
	{
		_showExistingFiles = showExisting;
	}
	public boolean showExistingFiles()
	{
		return _showExistingFiles;
	}
	
	public DefaultTreeModel getTreeModel()
	{
		return _treeModel;
	}
	
	public void updateTreeModel()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Repository");
		if (_showExistingFiles)
		{
			try
			{
				Vector<String> data = _git.execCommand("ls-tree --full-tree -r --name-only -- HEAD");
				for (String line : data)
				{
					insertPathIntoTree(top, line.trim());
				}
			}
			catch (Exception e) {}
		}
		
		if (_showDeletedFiles)
		{
			try
			{
				Vector<String> data = _git.execCommand("log --diff-filter=D --summary HEAD");
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
			catch (Exception e) {}
		}
		
		_treeModel.setRoot(top);
		_treeModel.reload();
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
