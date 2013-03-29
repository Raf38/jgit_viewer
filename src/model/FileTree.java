package model;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class FileTree
{
	private boolean _showDeletedFiles = false;
	private boolean _showExistingFiles = true;
	private GitCommandLine _git = null;
	private DefaultTreeModel _treeModel;
	private FileTreeNode _treeNode;
	
	public FileTree()
	{
		_git = new GitCommandLine();
		_treeNode = new FileTreeNode("Repository");
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
	
	private FileTreeNode getFileTree()
	{
		FileTreeNode top = new FileTreeNode("Repository");
		if (_showExistingFiles)
		{
			try
			{
				Vector<String> data = _git.execCommand("ls-tree --full-tree -r --name-only -- HEAD");
				for (String line : data)
				{
					insertPathIntoTree(top, line.trim(),false);
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
					
					try
					{
						if (tokens.length == 5 && tokens[1].equals("delete") && tokens[2].equals("mode"))
						{
							insertPathIntoTree(top, tokens[4].trim(), true);
						}	
					} catch (Exception e)
					{
						System.err.println(e.getMessage());
					}
				}
			}
			catch (Exception e) {}
		}
		
		return top;
	}
	
	private void updateTreeNode(FileTreeNode dest, FileTreeNode src)
	{
		Vector<FileTreeNode> srcNodesToDelete = new Vector<FileTreeNode>();
		//delete old and update existing 
		for(int n=0;n<dest.getChildCount();n++)
		{
			FileTreeNode destNode = (FileTreeNode) dest.getChildAt(n);
			boolean matched = false;
			for(int m=0;m<src.getChildCount();m++)
			{
				FileTreeNode srcNode = (FileTreeNode) src.getChildAt(m);
				if (destNode.getName().equals(srcNode.getName()))
				{
					destNode.setDeleted(srcNode.isDeleted());
					updateTreeNode(destNode,srcNode);
					srcNodesToDelete.add(srcNode);
					matched = true;
				}
			}
			if (!matched)
			{
				dest.remove(destNode);
			}
		}
		
		for(FileTreeNode n : srcNodesToDelete)
		{
			src.remove(n);
		}
		
		for(int m=0;m<src.getChildCount();m++)
		{
			FileTreeNode srcNode = (FileTreeNode) src.getChildAt(m);
			dest.add(srcNode);
		}
	}
	
	public void updateTreeModel()
	{
		FileTreeNode newTree = getFileTree();
		FileTreeNode tree = (FileTreeNode)_treeModel.getRoot();
		
		//updateTreeNode(tree, newTree);
		_treeModel.setRoot(newTree);
		_treeModel.reload();
	}
	
	private void insertPathIntoTree(FileTreeNode tree, String path, boolean deleted)
	{
		FileTreeNode current = tree;
		String line = path.trim();
		String dirs[] = line.split("/");
		for (String s : dirs)
		{
			FileTreeNode child = null;
			try
			{
				child = (FileTreeNode)current.getFirstChild();
			} catch (Exception e) {}
			while(child != null)
			{
				if (s.equals(child.getName()))
				{
					current = child;
					break;
				} else
				{
					child = (FileTreeNode) child.getNextSibling();
				}
			}
			if (child == null)
			{
				FileTreeNode newChild = new FileTreeNode(s, deleted);
				current.add(newChild);
				current = newChild;
			}
		}
	}
}
