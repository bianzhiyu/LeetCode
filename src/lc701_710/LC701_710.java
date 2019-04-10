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

//707. Design Linked List
//Runtime: 52 ms,  98.95%
//Memory Usage: 46.2 MB
class MyLinkedList
{
	private static class LN
	{
		int val;
		LN next, prev;

		private LN(int x)
		{
			val = x;
			next = null;
			prev = null;
		}
	}

	private LN head, tail;

	/** Initialize your data structure here. */
	public MyLinkedList()
	{
		head = null;
		tail = null;
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index)
	{
		if (index < 0)
		{
			return -1;
		}
		if (head == null)
			return -1;
		LN p = head;
		for (int i = 1; i <= index; i++)
		{
			if (p.next == null)
				return -1;
			p = p.next;
		}
		return p.val;
	}

	/**
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list.
	 */
	public void addAtHead(int val)
	{
		if (head == null)
		{
			head = new LN(val);
			tail = head;
		} else
		{
			LN p = new LN(val);
			head.prev = p;
			p.next = head;
			head = p;
		}
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val)
	{
		if (head == null)
		{
			head = new LN(val);
			tail = head;
		} else
		{
			LN p = new LN(val);
			p.prev = tail;
			tail.next = p;
			tail = p;
		}
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If index
	 * equals to the length of linked list, the node will be appended to the end of
	 * linked list. If index is greater than the length, the node will not be
	 * inserted.
	 */
	public void addAtIndex(int index, int val)
	{
		if (index < 0)
		{
			if (head == null)
			{
				head = tail = new LN(val);
			} else
			{

			}
			return;
		}
		if (head == null)
		{
			if (index != 0)
				return;
			head = new LN(val);
			tail = head;
			return;
		}

		if (index == 0)
		{
			LN p = new LN(val);
			head.prev = p;
			p.next = head;
			head = p;
		} else
		{
			LN q = head;
			for (int i = 0; i < index; i++)
			{
				if (q.next == null && i == index - 1)
				{
					tail.next = new LN(val);
					tail.next.prev = tail;
					tail = tail.next;
					return;
				}
				if (q.next == null)
					return;
				q = q.next;
			}
			if (q != head)
			{
				LN t = q.prev;
				LN t2 = new LN(val);
				t2.next = q;
				t2.prev = t;
				q.prev = t2;
				t.next = t2;
			} else
			{
				head.prev = new LN(val);
				head.prev.next = head;
				head = head.prev;
			}
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index)
	{
		if (index < 0)
			return;
		if (head == null)
			return;
		LN p = head;
		for (int i = 1; i <= index; i++)
		{
			if (p.next == null)
				return;
			p = p.next;
		}
		if (p == tail)
		{
			if (p == head)
			{
				head = tail = null;
			} else
			{
				tail = tail.prev;
				tail.next = null;
			}
		} else if (p == head)
		{
			if (p == tail)
				head = tail = null;
			else
			{
				head = head.next;
				head.prev = null;
			}
		} else
		{
			LN b = p.prev, n = p.next;
			b.next = n;
			n.prev = b;
		}
	}

	public void disp()
	{
		if (head == null)
			System.out.println("null");
		else
		{
			System.out.print("head.prev=" + head.prev + " ");
			LN p = head;
			while (p != null)
			{
				System.out.print(p.val + " ");
				p = p.next;
			}
			System.out.println();
		}
	}
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList(); int param_1 = obj.get(index);
 * obj.addAtHead(val); obj.addAtTail(val); obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
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

	public static void test707()
	{
		MyLinkedList m = new MyLinkedList();
		m.addAtHead(1);
		m.disp();
		m.addAtTail(3);
		m.disp();
		m.addAtIndex(1, 2);
		m.disp();
		m.get(1);
		m.deleteAtIndex(1);
		m.disp();
	}

	public static void main(String[] args)
	{
		test707();
	}

}
