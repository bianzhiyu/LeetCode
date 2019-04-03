package bbst;

import java.util.Comparator;

import treeCodec.BinaryTree;
import treeCodec.TreeCodec;

/**
 * A balanced binary search tree. It allows to contain duplicated elements. A
 * typical application of this class is sorting and array of type T.
 * 
 * If there are T o1,o2 and o1.compareTo(o2)==0, with BBST<T> rt,...
 * rt=rt.insert(o1).insert(o2), the tree will have two nodes.
 * 
 * @author bianz
 *
 * @param <T> test:911,TopVotedCandidate_3
 */
@SuppressWarnings("unchecked")
public class BBST2<T> implements BinaryTree<T>
{
	protected T dataLink;
	protected int height;
	protected BBST2<T> left, right;

	public BBST2(T x)
	{
		dataLink = x;
		height = 1;
		left = null;
		right = null;
	}

	protected void setLeft(BBST2<T> nl)
	{
		left = nl;
	}

	protected void setRight(BBST2<T> nr)
	{
		right = nr;
	}

	protected int getLeftHeight()
	{
		if (left != null)
			return left.height;
		else
			return 0;
	}

	protected int getRightHeight()
	{
		if (right != null)
			return right.height;
		else
			return 0;
	}

	/**
	 * return the root of the right rotated tree
	 */
	protected BBST2<T> rightRotate()

	{
		if (left == null)
			return this;
		BBST2<T> tmpleft = left;
		setLeft(tmpleft.right);
		tmpleft.setRight(this);
		this.updateHeight();
		tmpleft.updateHeight();
		return tmpleft;
	}

	/**
	 * return the root of the left rotated tree
	 */
	protected BBST2<T> leftRotate()
	{
		if (right == null)
			return this;
		BBST2<T> tmpright = right;
		setRight(tmpright.left);
		tmpright.setLeft(this);
		this.updateHeight();
		tmpright.updateHeight();
		return tmpright;
	}

	/**
	 * Return the root of the balanced tree. If abs(height of left subtree - height
	 * of right subtree)<=1, This method will do nothing. If not, it will only
	 * adjusts once.
	 */
	protected BBST2<T> balance()
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
	protected void updateHeight()
	{
		height = Math.max(left != null ? left.height : 0, right != null ? right.height : 0) + 1;
	}

	// below are the core methods for a bbst.

	//
	public T getMinData()
	{
		BBST2<T> t = this;
		while (t.left != null)
			t = t.left;
		return t.dataLink;
	}

	public T getMaxData()
	{
		BBST2<T> t = this;
		while (t.right != null)
			t = t.right;
		return t.dataLink;
	}

	public BBST2<T> removeMin()
	{
		if (left == null)
			return right;
		// Now,left sub tree will remove one node
		setLeft(left.removeMin());
		updateHeight();
		return balance();
	}

	public BBST2<T> removeMax()
	{
		if (right == null)
			return left;
		// Now,left sub tree will remove one node
		setRight(right.removeMax());
		updateHeight();
		return balance();
	}
	//

	// below we need to compare data.

	/**
	 * return the root of new BBST after x is inserted. Note that the tree can have
	 * multiple nodes which are equal.
	 */
	public BBST2<T> insertComparable(T x)
	{
		Comparable<T> y = (Comparable<T>) x;
		if (y.compareTo(dataLink) > 0)
//		if (dataLink.compareTo(x)<0)
		{
			if (right == null)
			{
				setRight(new BBST2<T>(x));
				updateHeight();
				return this;
			} else
			{
				setRight(right.insertComparable(x));
				updateHeight();
				return balance();
			}
		} else
		{
			if (left == null)
			{
				setLeft(new BBST2<T>(x));
				updateHeight();
				return this;
			} else
			{
				setLeft(left.insertComparable(x));
				updateHeight();
				return balance();
			}
		}
	}

	public BBST2<T> insertByComparator(T x, Comparator<T> cmp)
	{
		if (cmp.compare(x, dataLink) > 0)
//		if (dataLink.compareTo(x)<0)
		{
			if (right == null)
			{
				setRight(new BBST2<T>(x));
				updateHeight();
				return this;
			} else
			{
				setRight(right.insertByComparator(x, cmp));
				updateHeight();
				return balance();
			}
		} else
		{
			if (left == null)
			{
				setLeft(new BBST2<T>(x));
				updateHeight();
				return this;
			} else
			{
				setLeft(left.insertByComparator(x, cmp));
				updateHeight();
				return balance();
			}
		}
	}

	public BBST2<T> removeNodeByDataComparable(T x)
	{
		Comparable<T> y = (Comparable<T>) x;
		if (left == null && right == null)
		{
			if (y.compareTo(dataLink) == 0)
//			if (dataLink.compareTo(x) == 0)
				return null;
			else
				return this;
		}
		if (left == null && right != null)
		{
			if (y.compareTo(dataLink) < 0)
//			if (x.compareTo(dataLink) < 0)
				return this;
			else if (y.compareTo(dataLink) == 0)
//				if (dataLink.compareTo(x) == 0)
				return right;
			else
			{
				setRight(right.removeNodeByDataComparable(x));
				updateHeight();
				return balance();
			}
		}
		if (left != null && right == null)
		{
			if (y.compareTo(dataLink) > 0)
				// if (x.compareTo(dataLink) > 0)
				return this;
			else if (y.compareTo(dataLink) == 0)
//				if (x.compareTo(dataLink) == 0)
				return left;
			else
			{
				setLeft(left.removeNodeByDataComparable(x));
				updateHeight();
				return balance();
			}
		}
		// Since here, left & right subtree are not null.
		if (y.compareTo(dataLink) > 0)
//		if (x.compareTo(dataLink) > 0)
		{
			setRight(right.removeNodeByDataComparable(x));
			updateHeight();
			return balance();
		}
		if (y.compareTo(dataLink) < 0)
//		if (x.compareTo(dataLink) < 0)
		{
			setLeft(left.removeNodeByDataComparable(x));
			updateHeight();
			return balance();
		}
		// now, x==this.val, and left and right subtree are both not null
		dataLink = right.getMinData();
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}

	public BBST2<T> removeNodeByDataByComparator(T x, Comparator<T> cmp)
	{
		if (left == null && right == null)
		{
			if (cmp.compare(x, dataLink) == 0)
//			if (dataLink.compareTo(x) == 0)
				return null;
			else
				return this;
		}
		if (left == null && right != null)
		{
			if (cmp.compare(x, dataLink) < 0)
//			if (x.compareTo(dataLink) < 0)
				return this;
			else if (cmp.compare(x, dataLink) == 0)
//				if (dataLink.compareTo(x) == 0)
				return right;
			else
			{
				setRight(right.removeNodeByDataByComparator(x, cmp));
				updateHeight();
				return balance();
			}
		}
		if (left != null && right == null)
		{
			if (cmp.compare(x, dataLink) > 0)
				// if (x.compareTo(dataLink) > 0)
				return this;
			else if (cmp.compare(x, dataLink) == 0)
//				if (x.compareTo(dataLink) == 0)
				return left;
			else
			{
				setLeft(left.removeNodeByDataByComparator(x, cmp));
				updateHeight();
				return balance();
			}
		}
		// Since here, left & right subtree are not null.
		if (cmp.compare(x, dataLink) > 0)
//		if (x.compareTo(dataLink) > 0)
		{
			setRight(right.removeNodeByDataByComparator(x, cmp));
			updateHeight();
			return balance();
		}
		if (cmp.compare(x, dataLink) < 0)
//		if (x.compareTo(dataLink) < 0)
		{
			setLeft(left.removeNodeByDataByComparator(x, cmp));
			updateHeight();
			return balance();
		}
		// now, x==this.val, and left and right subtree are both not null
		dataLink = right.getMinData();
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}

	public boolean containDataByComparator(T d, Comparator<T> cmp)
	{
		if (cmp.compare(d, dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			return true;
		if (cmp.compare(d, dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return false;
			else
				return right.containDataByComparator(d, cmp);
		}
		if (left == null)
			return false;
		else
			return left.containDataByComparator(d, cmp);
	}

	public boolean containDataComparable(T d)
	{
		Comparable<T> y = (Comparable<T>) d;
		if (y.compareTo(dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			return true;
		if (y.compareTo(dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return false;
			else
				return right.containDataComparable(d);
		}
		if (left == null)
			return false;
		else
			return left.containDataComparable(d);
	}

	/**
	 * This method can change the node. Be careful not to change the order.
	 * 
	 * This method return the dataLink reference, with a=bst.getData(d), a is the
	 * reference of some instance of T, and this instance is contained in some node
	 * of the tree bst. d is used for compare: a.compareTo(d)==0.
	 * 
	 * If there is no such node whose dataLink.compareTo(d)==0, this method will
	 * return null;
	 */
	public T getDataComparable(T d)
	{
		Comparable<T> key = (Comparable<T>) d;
		if (key.compareTo(dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			return dataLink;
		if (key.compareTo(dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return null;
			else
				return right.getDataComparable(d);
		}
		if (left == null)
			return null;
		else
			return left.getDataComparable(d);
	}

	public T getDataByComparator(T d, Comparator<T> cmp)
	{
		if (cmp.compare(d, dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			return dataLink;
		if (cmp.compare(d, dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return null;
			else
				return right.getDataByComparator(d, cmp);
		}
		if (left == null)
			return null;
		else
			return left.getDataByComparator(d, cmp);
	}

	/**
	 * This method can change the node. Be careful not to change the order. This
	 * method replace the dataLink, dataLink.compareTo(x)==0, contained in some
	 * node. If no such node exists, the method will do nothing.
	 */
	public void replaceDataComparable(T d)
	{
		Comparable<T> key = (Comparable<T>) d;
		if (key.compareTo(dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			dataLink = d;
		if (key.compareTo(dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return;
			else
				right.replaceDataComparable(d);
			return;
		}
		if (left == null)
			return;
		else
			left.replaceDataComparable(d);
	}

	public void replaceDataByComparator(T d, Comparator<T> cmp)
	{
		if (cmp.compare(d, dataLink) == 0)
//		if (dataLink.compareTo(d) == 0)
			dataLink = d;
		if (cmp.compare(d, dataLink) > 0)
//		if (dataLink.compareTo(d) < 0)
		{
			if (right == null)
				return;
			else
				right.replaceDataByComparator(d, cmp);
			return;
		}
		if (left == null)
			return;
		else
			left.replaceDataByComparator(d, cmp);
	}

	// 1
	public T getNoLargerThanAndMaxComparable(T k)
	{
		Comparable<T> key = (Comparable<T>) k;
		if (key.compareTo(dataLink) < 0)
		{
			if (left == null)
				return null;
			return left.getNoLargerThanAndMaxComparable(k);
		} else
		{
			if (right == null)
				return dataLink;
			T n = right.getNoLargerThanAndMaxComparable(k);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 1
	public T getNoLargerThanAndMaxByComparator(T k, Comparator<T> cmp)
	{
		if (cmp.compare(k, dataLink) < 0)
		{
			if (left == null)
				return null;
			return left.getNoLargerThanAndMaxByComparator(k, cmp);
		} else
		{
			if (right == null)
				return dataLink;
			T n = right.getNoLargerThanAndMaxByComparator(k, cmp);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 2
	public T getLessThanAndMaxComparable(T k)
	{
		Comparable<T> key = (Comparable<T>) k;
		if (key.compareTo(dataLink) <= 0)
		{
			if (left == null)
				return null;
			return left.getLessThanAndMaxComparable(k);
		} else
		{
			if (right == null)
				return dataLink;
			T n = right.getLessThanAndMaxComparable(k);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 2
	public T getLessThanAndMaxByComparator(T k, Comparator<T> cmp)
	{
		if (cmp.compare(k, dataLink) <= 0)
		{
			if (left == null)
				return null;
			return left.getLessThanAndMaxByComparator(k, cmp);
		} else
		{
			if (right == null)
				return dataLink;
			T n = right.getLessThanAndMaxByComparator(k, cmp);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 3
	public T getNoLessThanAndMinComparable(T k)
	{
		Comparable<T> key = (Comparable<T>) k;
		if (key.compareTo(dataLink) > 0)
		{
			if (right == null)
				return null;
			return right.getNoLessThanAndMinComparable(k);
		} else
		{
			if (left == null)
				return dataLink;
			T n = left.getNoLessThanAndMinComparable(k);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 3
	public T getNoLessThanAndMinByComparator(T k, Comparator<T> cmp)
	{
		if (cmp.compare(k, dataLink) > 0)
		{
			if (right == null)
				return null;
			return right.getNoLessThanAndMinByComparator(k, cmp);
		} else
		{
			if (left == null)
				return dataLink;
			T n = left.getNoLessThanAndMinByComparator(k, cmp);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 4
	public T getLargerThanAndMinComparable(T k)
	{
		Comparable<T> key = (Comparable<T>) k;
		if (key.compareTo(dataLink) >= 0)
		{
			if (right == null)
				return null;
			return right.getLargerThanAndMinComparable(k);
		} else
		{
			if (left == null)
				return dataLink;
			T n = left.getLargerThanAndMinComparable(k);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	// 4
	public T getLargerThanAndMinByComparator(T k, Comparator<T> cmp)
	{
		if (cmp.compare(k, dataLink) >= 0)
		{
			if (right == null)
				return null;
			return right.getLargerThanAndMinByComparator(k, cmp);
		} else
		{
			if (left == null)
				return dataLink;
			T n = left.getLargerThanAndMinByComparator(k, cmp);
			if (n != null)
				return n;
			else
				return dataLink;
		}
	}

	/**
	 * For interface BinaryTree. This is for serializing this tree.
	 */
	@Override
	public T getData()
	{
		return dataLink;
	}

	/** For interface BinaryTree */
	@Override
	public BinaryTree<T> getLeftTree()
	{
		return left;
	}

	/** For interface BinaryTree */
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
