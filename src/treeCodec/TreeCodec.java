package treeCodec;

import java.util.LinkedList;
import java.util.Queue;

//297. Serialize and Deserialize Binary Tree
//449. Serialize and Deserialize BST

public class TreeCodec
{

	// Encodes a tree to a single string.
	public static <T> String serialize(BinaryTree<T> root)
	{
		StringBuilder sb = new StringBuilder().append('[');
		Queue<BinaryTree<T>> q = new LinkedList<BinaryTree<T>>();
		q.add(root);
		while (!q.isEmpty())
		{
			BinaryTree<T> p = q.peek();
			if (p == null)
			{
				sb.append("null,");
			} else
			{
				sb.append(p.getData().toString()).append(',');
				q.add(p.getLeftTree());
				q.add(p.getRightTree());
			}
			q.remove();
		}
		return sb.append(']').toString();
	}

	/**
	 * Decodes your encoded data to tree.
	 * 
	 * @param data should be surrounded by brackets like:"[1,2,5,3,4,null,6]";
	 * @return
	 */
	public static TreeNode deserialize(String data)
	{
		data = data.trim();
		String[] darr = data.substring(1, data.length() - 1).split(",");
		for (int i=0;i<darr.length;i++)
			darr[i]=darr[i].trim();
		if (darr.length == 0 || darr[0].compareTo("null") == 0)
			return null;
		TreeNode rt = new TreeNode(Integer.parseInt(darr[0]));
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(rt);
		int i = 1;
		while (i < darr.length)
		{
			TreeNode p = q.remove();
			if (darr[i].compareTo("null") == 0)
			{
				i++;
			} else
			{
				p.left = new TreeNode(Integer.parseInt(darr[i]));
				i++;
				q.add(p.left);
			}
			if (i == darr.length)
				break;

			if (darr[i].compareTo("null") == 0)
			{
				i++;
			} else
			{
				p.right = new TreeNode(Integer.parseInt(darr[i]));
				i++;
				q.add(p.right);
			}

		}
		return rt;
	}
}