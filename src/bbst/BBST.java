package bbst;

import treeCodec.BinaryTree;
import treeCodec.TreeCodec;

/**
 * A balanced binary search tree.
 * It allows to contain duplicated elements.
 * A typical application of this class is sorting and array of type T.
 * 
 * If there are T o1,o2 and o1.compareTo(o2)==0,
 * with BBST<T> rt,...
 * rt=rt.insert(o1).insert(o2),
 * the tree will have two nodes.
 * 
 * If one wants to avoid duplication,
 * he needs to check containData(x) before insert.
 * The caller himself needs to guarantee no-duplication.
 * 
 * With no duplication, this class can be used to implement things
 * like hash set, hash map, et al.
 * @author bianzhiyu
 *
 * @param <T>
 */
public class BBST<T extends Comparable<T>>  
implements BinaryTree<T>
{
	protected T dataLink;
	protected int height;
	protected BBST<T> left,right;
	
	public BBST(T x)
	{
		dataLink=x;
		height=1;
		left=null;
		right=null;
	}
	
	protected void setLeft(BBST<T> nl){left=nl;}
	protected void setRight(BBST<T> nr){right=nr;}
	protected int getLeftHeight()
	{
		if (left!=null) return left.height;
		else return 0;
	}
	protected int getRightHeight()
	{
		if (right!=null) return right.height;
		else return 0;
	}
	
	/** return the root of the right rotated tree
	 */
	protected BBST<T> rightRotate()
	
	{
		if (left==null) return this;
		BBST<T> tmpleft=left;
		setLeft(tmpleft.right);
		tmpleft.setRight(this);
		this.updateHeight();
		tmpleft.updateHeight();
		return tmpleft;
	}
	
	/** return the root of the left rotated tree
	 */
	protected BBST<T> leftRotate()
	{
		if (right==null) return this;
		BBST<T> tmpright=right;
		setRight(tmpright.left);
		tmpright.setLeft(this);
		this.updateHeight();
		tmpright.updateHeight();
		return tmpright;
	}
	
	 /** Return the root of the balanced tree.
	  * If abs(height of left subtree - height of right subtree)<=1,
	  * This method will do nothing.
	  * If not, it will only adjusts once.
	 */
	protected BBST<T> balance()
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
				setRight(right.rightRotate());
				updateHeight();
			}
			return leftRotate();
		}
		return this;
	}
	/** update height by its two branches.
	 */
	protected void updateHeight()
	{
		height=Math.max(left!=null?left.height:0, 
				right!=null?right.height:0)+1;
	}
	/**return the root of new BBST after x is inserted.
	 * Note that the tree can have multiple nodes which are equal.
	 */
	public BBST<T> insert(T x)
	{
		if (dataLink.compareTo(x)<0)
		{
			if (right==null)
			{
				setRight(new BBST<T>(x));
				updateHeight();
				return this;
			}
			else
			{
				setRight(right.insert(x));
				updateHeight();
				return balance();
			}
		}
		else
		{
			if (left==null)
			{
				setLeft(new BBST<T>(x));
				updateHeight();
				return this;
			}
			else
			{
				setLeft(left.insert(x));
				updateHeight();
				return balance();
			}
		}
	}
	
	public T getMinData()
	{
		BBST<T> t=this;
		while (t.left!=null) t=t.left;
		return t.dataLink;
	}
	public T getMaxData()
	{
		BBST<T> t=this;
		while (t.right!=null) t=t.right;
		return t.dataLink;
	}
	public BBST<T> removeMin()
	{
		if (left==null) return right;
		//Now,left sub tree will remove one node
		setLeft(left.removeMin());
		updateHeight();
		return balance();
	}
	public BBST<T> removeMax()
	{
		if (right==null) return left;
		//Now,left sub tree will remove one node
		setRight(right.removeMax());
		updateHeight();
		return balance();
	}
	
	/** Remove the node whose dataLink: dataLink.comparesTo(x)==0.
	 *  If no such node exists, this method will do nothing.
	 * */
	public BBST<T> removeNodeByData(T x)
	{
		if (left==null && right==null)
		{
			if (dataLink.compareTo(x)==0) return null;
			else return this;
		}
		if (left==null && right!=null)
		{
			if (x.compareTo(dataLink)<0) return this;
			else if (dataLink.compareTo(x)==0) return right;
			else 
			{
				setRight(right.removeNodeByData(x));
				updateHeight();
				return balance();
			}
		}
		if (left!=null && right==null)
		{
			if (x.compareTo(dataLink)>0) return this;
			else if (x.compareTo(dataLink)==0) return left;
			else
			{
				setLeft(left.removeNodeByData(x));
				updateHeight();
				return balance();
			}
		}
		//Since here, left & right subtree are not null.
		if (x.compareTo(dataLink)>0)
		{
			setRight(right.removeNodeByData(x));
			updateHeight();
			return balance();
		}
		if (x.compareTo(dataLink)<0)
		{
			setLeft(left.removeNodeByData(x));
			updateHeight();
			return balance();
		}
		//now, x==this.val, and left and right subtree are both not null
		dataLink=right.getMinData();
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}
	
	/** Check whether this tree has some node whose dataLink:
	 * dataLink.comparesTo(d)==0.
	 */
	public boolean containData(T d)
	{
		if (dataLink.compareTo(d)==0) return true;
		if (dataLink.compareTo(d)<0)
		{
			if (right==null) return false;
			else return right.containData(d);
		}
		if (left==null) return false;
		else return left.containData(d);
	}
	
	
	/**This method can change the node.
	 * Be careful not to change the order.
	 * 
	 * This method return the dataLink reference,
	 * with a=bst.getData(d),
	 * a is the reference of some instance of T,
	 * and this instance is contained in some node of the tree bst.
	 * d is used for compare:
	 * a.compareTo(d)==0.
	 * 
	 * If there is no such node whose dataLink.compareTo(d)==0,
	 * this method will return null;
	 * */
	public T getData(T d)
	{
		if (dataLink.compareTo(d)==0) return dataLink;
		if (dataLink.compareTo(d)<0)
		{
			if (right==null) return null;
			else return right.getData(d);
		}
		if (left==null) return null;
		else return left.getData(d);
	}
	
	/** This method can change the node.
	 *	Be careful not to change the order.
	 *  This method replace the dataLink, dataLink.compareTo(x)==0,
	 *  contained in some node.
	 *  If no such node exists, the method will do nothing.*/
	public void replaceData(T d)
	{
		if (dataLink.compareTo(d)==0) dataLink=d;
		if (dataLink.compareTo(d)<0)
		{
			if (right==null) return;
			else right.replaceData(d);
			return;
		}
		if (left==null) return;
		else left.replaceData(d);
	}

	/**For interface BinaryTree. 
	 * This is for serializing this tree.*/
	@Override
	public T getData()
	{
		return dataLink;
	}

	/**For interface BinaryTree */
	@Override
	public BinaryTree<T> getLeftTree()
	{
		return left;
	}

	/**For interface BinaryTree */
	@Override
	public BinaryTree<T> getRightTree()
	{
		return right;
	}
	
	public String toString()
	{
		return TreeCodec.serialize(this);
	}
}
