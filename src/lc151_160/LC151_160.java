package lc151_160;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//151. Reverse Words in a String
//Runtime: 19 ms, faster than 23.61% of Java online submissions for Reverse Words in a String.
//Memory Usage: 40.4 MB, less than 9.94% of Java online submissions for Reverse Words in a String.
class Solution151
{
	public String reverseWords(String s)
	{
		int p = 0, slen = s.length();
		Stack<String> stack = new Stack<String>();
		while (p < slen)
		{
			while (p < slen && s.charAt(p) == ' ')
				p++;
			if (p == slen)
				break;
			int q = p + 1;
			while (q < slen && s.charAt(q) != ' ')
				q++;
			stack.push(s.substring(p, q));
			p = q;
		}
		if (stack.isEmpty())
			return "";
		String ans = stack.pop();
		while (!stack.isEmpty())
			ans = ans + " " + stack.pop();
		return ans;
	}
}


//152. Maximum Product Subarray
//Runtime: 84 ms, faster than 9.49% of Java online submissions for Maximum Product Subarray.
//Memory Usage: 46.8 MB, less than 7.02% of Java online submissions for Maximum Product Subarray.
class Solution152
{
	public int maxProduct(int[] nums)
	{
		int max=nums[0];
		for (int i=0;i<nums.length;i++)
		{
			int t=1;
			for (int j=i;j<nums.length;j++)
			{
				t*=nums[j];
				if (t>max) max=t;
			}
		}
		return max;
	}
}

//https://leetcode.com/problems/maximum-product-subarray/discuss/48252/Sharing-my-solution%3A-O(1)-space-O(n)-running-time
//Runtime: 1 ms, faster than 98.67% of Java online submissions for Maximum Product Subarray.
//Memory Usage: 35 MB, less than 77.23% of Java online submissions for Maximum Product Subarray.
class Solution152_2
{
	public int maxProduct(int[] nums)
	{
		int len=nums.length;
		int[] max=new int[len],min=new int[len];
		max[0]=nums[0];
		min[0]=nums[0];
		for (int i=1;i<len;i++)
		{
			max[i]=nums[i];
			min[i]=nums[i];
			if (max[i-1]*nums[i]>max[i]) max[i]=max[i-1]*nums[i];
			if (min[i-1]*nums[i]>max[i]) max[i]=min[i-1]*nums[i];
			if (max[i-1]*nums[i]<min[i]) min[i]=max[i-1]*nums[i];
			if (min[i-1]*nums[i]<min[i]) min[i]=min[i-1]*nums[i];
		}
		int a=nums[0];
		for (int i=0;i<len;i++)
			if (max[i]>a) a=max[i];
		return a;
	}
}

//153. Find Minimum in Rotated Sorted Array
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
//Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
class Solution153
{
	public int findMin(int[] nums)
	{
		int min = nums[0];
		for (int i = 1; i < nums.length; i++)
			if (nums[i] < min)
				min = nums[i];
		return min;

	}
}



//t154. Find Minimum in Rotated Sorted Array II
//t=0ms, m=36mb
//t:1, m:1
//According to https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/48849/Stop-wasting-your-time.-It-most-likely-has-to-be-O(n).
//"O(T) most likely has to be O(n)."
class Solution154
{
	public int findMin(int[] nums)
	{
		int min = nums[0];
		for (int i = 1; i < nums.length; i++)
			if (nums[i] < min)
				min = nums[i];
		return min;
	}
}

//155. Min Stack
//Runtime: 56 ms, faster than 99.60% of Java online submissions for Min Stack.
//Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Min Stack.
class DDLinkNode
{
	int val;
	DDLinkNode pred, succ;

	DDLinkNode()
	{
	}

	DDLinkNode(int v, DDLinkNode p, DDLinkNode s)
	{
		val = v;
		pred = p;
		succ = s;
	}
}

class MinStack
{
	DDLinkNode head, tail;
	int minv;

	/** initialize your data structure here. */
	public MinStack()
	{
		head = new DDLinkNode(0, null, null);
		tail = null;
		minv = 0;
	}

	public void disp()
	{
		System.out.println("Disp:");
		DDLinkNode a = head.succ;
		while (a != null)
		{
			System.out.print(a.val + " ");
			a = a.succ;
		}
		System.out.println();
	}

	public void push(int x)
	{
		DDLinkNode nd = new DDLinkNode(x, null, null);

		if (head.succ == null)
		{
			head.succ = nd;
			nd.pred = head;
			tail = nd;
			minv = x;
		} else
		{
			tail.succ = nd;
			nd.pred = tail;
			tail = nd;
			if (minv > x)
				minv = x;
		}

	}

	public void pop()
	{
		if (head.succ == null)
			return;

		tail = tail.pred;
		tail.succ = null;

		minv = getMinVal();

	}

	public int top()
	{
		if (head.succ == null)
			return 0;
		return tail.val;
	}

	private int getMinVal()
	{
		int min = Integer.MAX_VALUE;
		DDLinkNode a = head.succ;
		while (a != null)
		{
			if (a.val < min)
				min = a.val;
			a = a.succ;
		}
		return min;
	}

	DDLinkNode getFirstNodeByVal(int v)
	{
		DDLinkNode a = head.succ;
		while (a != null)
		{
			if (a.val == v)
				return a;
			a = a.succ;
		}
		return a;
	}

	public int getMin()
	{
		if (head.succ == null)
			return 0;
		return minv;
	}
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */

class Solution160
{
	public ListNode getIntersectionNode(ListNode headA, ListNode headB)
	{
		Set<ListNode> s = new HashSet<ListNode>();
		ListNode p = headA;
		while (p != null)
		{
			s.add(p);
			p = p.next;
		}
		p = headB;
		while (p != null)
		{
			if (s.contains(p))
				return p;
			p = p.next;
		}
		return null;

	}
}

//160. Intersection of Two Linked Lists
//Runtime: 9 ms, faster than 15.14% of Java online submissions for Intersection of Two Linked Lists.
//Memory Usage: 36.8 MB, less than 51.72% of Java online submissions for Intersection of Two Linked Lists.
public class LC151_160
{
	public static void main(String args[])
	{
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		System.out.print(s.pop());
		Solution151 sol = new Solution151();
		System.out.println(sol.reverseWords("abc ds"));

	}

}
