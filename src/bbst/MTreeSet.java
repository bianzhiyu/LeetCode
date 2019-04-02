package bbst;

import java.util.Comparator;

//test:911,TopVotedCandidate_3
public class MTreeSet<T>
{
	private BBST2<T> root;
	private Comparator<T> cmp;

	public MTreeSet(Comparator<T> cmp)
	{
		this.cmp = cmp;
	}

	public MTreeSet()
	{
		this(null);
	}
	
	public boolean isEmpty()
	{
		return root==null;
	}

	public void insert(T x)
	{
		if (root == null)
		{
			root = new BBST2<T>(x);
			return;
		}
		if (cmp == null)
			root = root.insertComparable(x);
		else
			root = root.insertByComparator(x, cmp);
	}
	
	public T getMinData()
	{
		if (root==null) return null;
		return root.getMinData();
	}
	
	public T getMaxData()
	{
		if (root==null) return null;
		return root.getMaxData();
	}
	
	public void removeMin()
	{
		if (root==null) return;
		root=root.removeMin();
	}
	
	public void removeMax()
	{
		if (root==null) return;
		root=root.removeMax();
	}
	
	/** Remove the node whose dataLink: dataLink.comparesTo(x)==0.
	 *  If no such node exists, this method will do nothing.
	 * */
	public void removeNodeByData(T d)
	{
		if (root==null) return;
		if (cmp==null)
			root=root.removeNodeByDataComparable(d);
		else
			root=root.removeNodeByDataByComparator(d, cmp);
 	}
	
	/** Check whether this tree has some node whose dataLink:
	 * dataLink.comparesTo(d)==0.
	 */
	public boolean containData(T d)
	{
		if (root==null) return false;
		if (cmp==null)
			return root.containDataComparable(d);
		else 
			return root.containDataByComparator(d, cmp);
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
		if (root==null) return null;
		if (cmp==null)
			return root.getDataComparable(d);
		else 
			return root.getDataByComparator(d, cmp);
	}
	
	/** This method can change the node.
	 *	Be careful not to change the order.
	 *  This method replace the dataLink, dataLink.compareTo(x)==0,
	 *  contained in some node.
	 *  If no such node exists, the method will do nothing.*/
	public void replaceData(T d)
	{
		if (root==null) return;
		if (cmp==null)
			root.replaceDataComparable(d);
		else 
			root.replaceDataByComparator(d, cmp);
	}
}
