package lc101_110;

class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x)
	{
		val = x;
	}
}

public class BBST extends TreeNode // Balanced Binary Search Tree
{
	private int height;
	private BBST left, right;

	public BBST(int x)
	{
		super(x);
		height = 1;
	}

	private void setLeft(BBST nl)
	{
		super.left = left = nl;
	}

	private void setRight(BBST nr)
	{
		super.right = right = nr;
	}

	private int getLeftHeight()
	{
		if (left != null)
			return left.height;
		else
			return 0;
	}

	private int getRightHeight()
	{
		if (right != null)
			return right.height;
		else
			return 0;
	}

	/**
	 * return the root of the right rotated tree
	 */
	private BBST rightRotate()

	{
		if (left == null)
			return this;
		BBST tmpleft = left;
		setLeft(tmpleft.right);
		tmpleft.setRight(this);
		this.updateHeight();
		tmpleft.updateHeight();
		return tmpleft;
	}

	/**
	 * return the root of the left rotated tree
	 */
	private BBST leftRotate()
	{
		if (right == null)
			return this;
		BBST tmpright = right;
		setRight(tmpright.left);
		tmpright.setLeft(this);
		this.updateHeight();
		tmpright.updateHeight();
		return tmpright;
	}

	/**
	 * Return the root of the balanced tree. If |height of left subtree - height of
	 * right subtree|<=1, This method will do nothing. If not, it will only adjusts
	 * once.
	 */
	private BBST balance()
	{
		if (getLeftHeight() >= getRightHeight() + 2)
		{
			if (left.getRightHeight() > left.getLeftHeight())
			{
				setLeft(left.leftRotate());
				updateHeight();
			}
			return rightRotate();
		}
		if (getLeftHeight() <= getRightHeight() - 2)
		{
			if (right.getLeftHeight() > right.getRightHeight())
			{
				setRight(right.rightRotate());
				updateHeight();
			}
			return leftRotate();
		}
		return this;
	}

	/**
	 * update height by its two branches.
	 */
	private void updateHeight()
	{
		height = Math.max(left != null ? left.height : 0, right != null ? right.height : 0) + 1;
	}

	/**
	 * return the root of new BBST after x is inserted.
	 */
	public BBST insert(int x)
	{
		if (val < x)
		{
			if (right == null)
			{
				setRight(new BBST(x));
				updateHeight();
				return this;
			} else
			{
				setRight(right.insert(x));
				updateHeight();
				return balance();
			}
		} else
		{
			if (left == null)
			{
				setLeft(new BBST(x));
				updateHeight();
				return this;
			} else
			{
				setLeft(left.insert(x));
				updateHeight();
				return balance();
			}
		}
	}

	public int getMinVal()
	{
		BBST t = this;
		while (t.left != null)
			t = t.left;
		return t.val;
	}

	public int getMaxVal()
	{
		BBST t = this;
		while (t.right != null)
			t = t.right;
		return t.val;
	}

	public BBST removeMin()
	{
		if (left == null)
			return right;
		// Now,left sub tree will remove one node
		setLeft(left.removeMin());
		updateHeight();
		return balance();
	}

	public BBST removeMax()
	{
		if (right == null)
			return left;
		// Now,left sub tree will remove one node
		setRight(right.removeMax());
		updateHeight();
		return balance();
	}

	public BBST removeNodeByVal(int x)
	{
		if (left == null && right == null)
		{
			if (val == x)
				return null;
			else
				return this;
		}
		if (left == null && right != null)
		{
			if (x < val)
				return this;
			else if (val == x)
				return right;
			else
			{
				setRight(right.removeNodeByVal(x));
				updateHeight();
				return balance();
			}
		}
		if (left != null && right == null)
		{
			if (x > val)
				return this;
			else if (val == x)
				return left;
			else
			{
				setLeft(left.removeNodeByVal(x));
				updateHeight();
				return balance();
			}
		}
		// Since here, left & right subtree are not null.
		if (x > val)
		{
			setRight(right.removeNodeByVal(x));
			updateHeight();
			return balance();
		}
		if (x < val)
		{
			setLeft(left.removeNodeByVal(x));
			updateHeight();
			return balance();
		}
		// now, x==this.val, and left and right subtree are both not null
		val = right.getMinVal();
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}
}
