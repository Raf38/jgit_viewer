package model;

import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	private String _name;
	private boolean _deleted;
	
	public FileTreeNode(String name, boolean deleted)
	{
		_name = name;
		_deleted = deleted;
	}
	
	public FileTreeNode(String name)
	{
		_name = name;
		_deleted = false;
	}
	
	public String getPathString()
	{
		FileTreeNode parent = ((FileTreeNode)this.parent);
		
		if (parent == null)
		{
			return null;
		}

		String path = ((FileTreeNode)this.parent).getPathString();
		if (path == null)
		{
			return _name;
		}
		return path+"/"+_name;
	}
	
	public FileTreeNode getNodeFromPath(String path) throws Exception
	{
		if (path.length() == 0)
		{
			return this;
		}
		
		int dirIndex = path.indexOf('/');
		if (dirIndex < 0)
		{
			dirIndex = path.length();
		}
		String firstElement = path.substring(0,dirIndex);
		String remainingElements = "";
		if (dirIndex < path.length())
		{
			remainingElements = path.substring(dirIndex+1);
		}
		for(int n=0;n<getChildCount();n++)
		{
			FileTreeNode child = (FileTreeNode)getChildAt(n);
			if (child.getName().equals(firstElement))
			{
				return child.getNodeFromPath(remainingElements);
			}
		}
		throw new Exception("path not found");
	}
	
	public void setDeleted(boolean deleted)
	{
		_deleted = deleted;
	}
	
	public boolean isDeleted()
	{
		return _deleted;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public String toString()
	{
		if (_deleted)
		{
			return "<html><font color='Red'>"+_name+"</font></html>";
		}
		return _name;
	}
	
}
