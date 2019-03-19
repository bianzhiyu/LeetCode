package treeCodec;

public class TreeNode implements BinaryTree<Integer>
{
	public int val;
	public TreeNode left,right;
	public TreeNode(int x) {val=x;}
	@Override
	public Integer getData()
	{
		return val;
	}
	@Override
	public BinaryTree<Integer> getLeftTree()
	{
		return left;
	}
	@Override
	public BinaryTree<Integer> getRightTree()
	{
		return right;
	}

}
