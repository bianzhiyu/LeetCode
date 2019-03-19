package lc441_450;

import treeCodec.*;

//449. Serialize and Deserialize BST
//Runtime: 10 ms, faster than 53.07% of Java online submissions for Serialize and Deserialize BST.
//Memory Usage: 39 MB, less than 85.33% of Java online submissions for Serialize and Deserialize BST.

class Codec extends TreeCodec
{
   
}

//450. Delete Node in a BST
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Delete Node in a BST.
//Memory Usage: 40.5 MB, less than 67.61% of Java online submissions for Delete Node in a BST.
class Solution450 {
	int getMin(TreeNode root)
	{
		if (root.left!=null) return getMin(root.left);
		return root.val;
	}
	int getMax(TreeNode root)
	{
		if (root.right!=null) return getMin(root.right);
		return root.val;
	}
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root==null) return root;
		if (key>root.val)
		{
			root.right=deleteNode(root.right,key);
			return root;
		}
		if (key<root.val)
		{
			root.left=deleteNode(root.left,key);
			return root;
		}
		if (root.right!=null)
		{
			root.val=getMin(root.right);
			root.right=deleteNode(root.right,root.val);
			return root;
		}
		if (root.left!=null)
		{
			root.val=getMax(root.left);
			root.left=deleteNode(root.left,root.val);
			return root;
		}
		return null;
	}
}
public class LC441_450 {
	public static void main(String[] ags)
	{
		
	}

}
