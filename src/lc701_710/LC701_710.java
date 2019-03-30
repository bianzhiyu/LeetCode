package lc701_710;

import bbst.BBST;
import bbst.MHashMap;
import bbst.MHashSet;
import treeCodec.*;

//701. Insert into a Binary Search Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Insert into a Binary Search Tree.
//Memory Usage: 40.7 MB, less than 5.38% of Java online submissions for Insert into a Binary Search Tree.
class Solution701
{
	public TreeNode insertIntoBST(TreeNode root, int val)
	{
		if (root == null)
			return new TreeNode(val);
		if (val >= root.val)
		{
			root.right = insertIntoBST(root.right, val);
			return root;
		}
		root.left = insertIntoBST(root.left, val);
		return root;
	}
}

//704. Binary Search
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Binary Search.
//Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Binary Search.
class Solution704
{
	int find(int[] a, int tar, int l, int r)
	{
		if (l > r)
			return -1;
		if (l == r)
			return a[l] == tar ? l : -1;
		if (r == l + 1)
		{
			if (a[l] == tar)
				return l;
			if (a[r] == tar)
				return r;
			return -1;
		}
		int m = (l + r) / 2;

		if (a[l] == tar)
			return l;
		if (a[m] >= tar)
			return find(a, tar, l, m);
		return find(a, tar, m + 1, r);
	}

	public int search(int[] nums, int target)
	{
		return find(nums, target, 0, nums.length - 1);
	}
}

//705. Design HashSet
//Runtime: 86 ms, faster than 30.91% of Java online submissions for Design HashSet.
//Memory Usage: 63.9 MB, less than 10.07% of Java online submissions for Design HashSet.
class MyHashSet extends MHashSet<Integer>
{

	/** Initialize your data structure here. */
	public MyHashSet()
	{

	}

	public void add(int key)
	{
		super.add(key);
	}

	public void remove(int key)
	{
		super.remove(key);
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key)
	{
		return super.contains(key);
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such: MyHashSet obj
 * = new MyHashSet(); obj.add(key); obj.remove(key); boolean param_3 =
 * obj.contains(key);
 */

//Runtime: 86 ms, faster than 30.91% of Java online submissions for Design HashSet.
//Memory Usage: 64 MB, less than 10.07% of Java online submissions for Design HashSet.
class MyHashSet_2
{
	private BBST<Integer> root;

	/** Initialize your data structure here. */
	public MyHashSet_2()
	{

	}

	public void add(int key)
	{
		if (root == null)
			root = new BBST<Integer>(key);
		else if (!root.containData(key))
			root = root.insert(key);
	}

	public void remove(int key)
	{
		if (root != null && root.containData(key))
			root = root.removeNodeByData(key);
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key)
	{
		return root != null && root.containData(key);
	}
}

//706. Design HashMap
//Runtime: 103 ms, faster than 17.30% of Java online submissions for Design HashMap.
//Memory Usage: 56.8 MB, less than 41.28% of Java online submissions for Design HashMap.
class MyHashMap extends MHashMap<Integer, Integer>
{

	/** Initialize your data structure here. */
	public MyHashMap()
	{

	}

	/** value will always be non-negative. */
	public void put(int key, int value)
	{
		super.put(key, value);
	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this map
	 * contains no mapping for the key
	 */
	public int get(int key)
	{
		Integer a = super.get(key);
		if (a == null)
			return -1;
		else
			return a;
	}

	/**
	 * Removes the mapping of the specified value key if this map contains a mapping
	 * for the key
	 */
	public void remove(int key)
	{
		super.remove(key);
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */

public class LC701_710
{
	public static void test706()
	{
		MyHashMap hm = new MyHashMap();
		hm.put(1, 1);
		hm.put(2, 2);
		System.out.println(hm.get(1));
		System.out.println(hm.get(3));
		hm.put(2, 1);
		System.out.println(hm.get(2));
		hm.remove(2);
		System.out.println(hm.get(2));
	}
	
	public static void test706_2()
	{
		MyHashMap hm = new MyHashMap();
		hm.put(1, 1);
		hm.put(2, 2);
		System.out.println(hm.get(1));
		System.out.println(hm.get(3));
		hm.put(2, 1);
		System.out.println(hm.get(2));
		hm.remove(2);
		System.out.println(hm.get(2));
	}

	public static void main(String[] args)
	{
		test706();
		test706_2();
	}

}
