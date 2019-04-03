package lc421_430;

//421. Maximum XOR of Two Numbers in an Array

//Runtime: 32 ms, faster than 83.67% of Java online submissions for Maximum XOR of Two Numbers in an Array.
//Memory Usage: 49.8 MB, less than 30.12% of Java online submissions for Maximum XOR of Two Numbers in an Array.

class Solution421
{
	private static class BT
	{
		BT[] s = new BT[2];
	}

	final static int MAXL = 31;
	int[] s = new int[MAXL];

	void insert(BT rt, int x)
	{

		for (int i = 0; i < MAXL; i++)
		{
			s[i] = x % 2;
			x /= 2;
		}
		for (int i = MAXL - 1; i >= 0; i--)
		{
			if (rt.s[s[i]] == null)
				rt.s[s[i]] = new BT();
			rt = rt.s[s[i]];
		}
	}

	public int findMaximumXOR(int[] nums)
	{
		BT rt = new BT();
		insert(rt, nums[0]);
		int maxa = 0;
		int[] pn = new int[MAXL];
		for (int i = 1; i < nums.length; i++)
		{
			int x = nums[i];
			for (int j = 0; j < MAXL; j++)
			{
				pn[j] = x % 2;
				x /= 2;
			}
			BT cur = rt;
			for (int j = MAXL - 1; j >= 0; j--)
			{
				if (cur.s[1 - pn[j]] != null)
				{
					cur = cur.s[1 - pn[j]];
					pn[j] = 1;
				} else
				{
					cur = cur.s[pn[j]];
					pn[j] = 0;
				}
			}
			int ts = 0, mul = 1;
			for (int j = 0; j < MAXL; j++)
			{
				ts += pn[j] == 1 ? mul : 0;
				mul *= 2;
			}
			if (ts > maxa)
				maxa = ts;
			insert(rt, nums[i]);
		}
		return maxa;
	}
}

//430. Flatten a Multilevel Doubly Linked List
class Node
{
	public int val;
	public Node prev;
	public Node next;
	public Node child;

	public Node()
	{
	}

	public Node(int _val, Node _prev, Node _next, Node _child)
	{
		val = _val;
		prev = _prev;
		next = _next;
		child = _child;
	}
}

//Runtime: 1 ms, faster than 100.00% of Java online submissions for Flatten a Multilevel Doubly Linked List.
//Memory Usage: 37 MB, less than 60.50% of Java online submissions for Flatten a Multilevel Doubly Linked List.
class Solution430
{
	public Node flatten(Node head)
	{
		if (head == null)
			return null;
		Node h = new Node(0, null, null, null);
		flat(h, head);
		Node p = h.next;
		while (p.next != null)
		{
			p.next.prev = p;
			p = p.next;
		}
		return h.next;

	}

	Node flat(Node tail, Node p)
	{
		if (p == null)
			return tail;
		tail.next = new Node(p.val, null, null, null);
		tail = tail.next;
		if (p.child != null)
			tail = flat(tail, p.child);
		tail = flat(tail, p.next);
		return tail;
	}
}

public class LC421_430
{
	public static void main(String[] args)
	{
		Solution421 s = new Solution421();
		int[] a = new int[]
		{ 3, 10, 5, 25, 2, 8 };
		System.out.println(s.findMaximumXOR(a));
	}
}
