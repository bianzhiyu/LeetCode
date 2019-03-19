package lc781_790;

public class BST_786
{
	private int height;
	private int x,y;
	private BST_786 left,right;
	public BST_786(int x1,int y1) {x=x1;y=y1;height=1;}
	private void setLeft(BST_786 nl){left=nl;}
	private void setRight(BST_786 nr){right=nr;}
	public int getX() {return x;}
	public int getY() {return y;}
	private boolean bigger(int x1,int y1)
	{
		return x*y1-x1*y>0;
	}
	private boolean smaller(int x1,int y1)
	{
		return x*y1-x1*y<0;
	}
	private boolean sameVal(int x1,int y1)
	{
		return x*y1-x1*y==0;
	}
	private int getLeftHeight()
	{
		if (left!=null) return left.height;
		else return 0;
	}
	private int getRightHeight()
	{
		if (right!=null) return right.height;
		else return 0;
	}
	/** return the root of the right rotated tree
	 */
	private BST_786 rightRotate()
	
	{
		if (left==null) return this;
		BST_786 tmpleft=left;
		setLeft(tmpleft.right);
		tmpleft.setRight(this);
		this.updateHeight();
		tmpleft.updateHeight();
		return tmpleft;
	}
	/** return the root of the left rotated tree
	 */
	private BST_786 leftRotate()
	{
		if (right==null) return this;
		BST_786 tmpright=right;
		setRight(tmpright.left);
		tmpright.setLeft(this);
		this.updateHeight();
		tmpright.updateHeight();
		return tmpright;
	}
	 /** Return the root of the balanced tree.
	  * If |height of left subtree - height of right subtree|<=1,
	  * This method will do nothing.
	  * If not, it will only adjusts once.
	 */
	private BST_786 balance()
	{
		if (getLeftHeight()>=getRightHeight()+2)
		{
			if (left.getRightHeight()>left.getLeftHeight())
			{
				setLeft(left.leftRotate());
				updateHeight();
			}
			return rightRotate();
		}
		if (getLeftHeight()<=getRightHeight()-2)
		{
			if (right.getLeftHeight()>right.getRightHeight())
			{
				setRight(right.leftRotate());
				updateHeight();
			}
			return leftRotate();
		}
		return this;
	}
	/** update height by its two branches.
	 */
	private void updateHeight()
	{
		height=Math.max(left!=null?left.height:0, 
				right!=null?right.height:0)+1;
	}
	/**return the root of new BST_786 after x is inserted.
	 */
	public BST_786 insert(int x1,int y1)
	{
		if (smaller(x1,y1))
		{
			if (right==null)
			{
				setRight(new BST_786(x1,y1));
				updateHeight();
				return this;
			}
			else
			{
				setRight(right.insert(x1,y1));
				updateHeight();
				return balance();
			}
		}
		else
		{
			if (left==null)
			{
				setLeft(new BST_786(x1,y1));
				updateHeight();
				return this;
			}
			else
			{
				setLeft(left.insert(x1,y1));
				updateHeight();
				return balance();
			}
		}
	}
	public BST_786 getMinNode()
	{
		BST_786 t=this;
		while (t.left!=null) t=t.left;
		return t;
	}
	public BST_786 getMaxNode()
	{
		BST_786 t=this;
		while (t.right!=null) t=t.right;
		return t;
	}
	public BST_786 removeMin()
	{
		if (left==null) return right;
		//Now,left sub tree will remove one node
		setLeft(left.removeMin());
		updateHeight();
		return balance();
	}
	public BST_786 removeMax()
	{
		if (right==null) return left;
		//Now,left sub tree will remove one node
		setRight(right.removeMax());
		updateHeight();
		return balance();
	}
	public BST_786 removeNodeByVal(int x1,int y1)
	{
		if (left==null && right==null)
		{
			if (sameVal(x1,y1)) return null;
			else return this;
		}
		if (left==null && right!=null)
		{
			if (bigger(x1,y1)) return this;
			else if (sameVal(x1,y1)) return right;
			else 
			{
				setRight(right.removeNodeByVal(x1,y1));
				updateHeight();
				return balance();
			}
		}
		if (left!=null && right==null)
		{
			if (smaller(x1,y1)) return this;
			else if (sameVal(x1,y1)) return left;
			else
			{
				setLeft(left.removeNodeByVal(x1,y1));
				updateHeight();
				return balance();
			}
		}
		//Since here, left & right subtree are not null.
		if (smaller(x1,y1))
		{
			setRight(right.removeNodeByVal(x1,y1));
			updateHeight();
			return balance();
		}
		if (bigger(x1,y1))
		{
			setLeft(left.removeNodeByVal(x1,y1));
			updateHeight();
			return balance();
		}
		//now, x==this.val, and left and right subtree are both not null
		BST_786 r=right.getMinNode();
		x=r.x;y=r.y;
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}
}
