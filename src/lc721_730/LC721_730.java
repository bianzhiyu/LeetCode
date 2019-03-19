package lc721_730;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}
//724. Find Pivot Index
//Runtime: 17 ms, faster than 63.35% of Java online submissions for Find Pivot Index.
//Memory Usage: 41.8 MB, less than 79.05% of Java online submissions for Find Pivot Index.

class Solution724
{
	public int pivotIndex(int[] nums)
	{
		int l = nums.length;
		int[] ls = new int[l];
		int[] rs = new int[l];
		int t = 0;
		for (int i = 0; i < l; i++)
		{
			t += nums[i];
			ls[i] = t;
		}
		t = 0;
		for (int i = l - 1; i >= 0; i--)
		{
			t += nums[i];
			rs[i] = t;
		}
		if (0 == rs[1])
			return 0;
		for (int i = 1; i < l - 1; i++)
			if (ls[i - 1] == rs[i + 1])
				return i;
		if (ls[l - 2] == 0)
			return l - 1;
		return -1;
	}
}

//725. Split Linked List in Parts
//Runtime: 2 ms, faster than 99.91% of Java online submissions for Split Linked List in Parts.
//Memory Usage: 37.3 MB, less than 50.00% of Java online submissions for Split Linked List in Parts.
class Solution725
{
	public ListNode[] splitListToParts(ListNode root, int k)
	{
		int n = 0;
		ListNode p = root;
		while (p != null)
		{
			n++;
			p = p.next;
		}
		int s1 = n / k;
		int s2 = n % k;
		ListNode[] ans = new ListNode[k];
		p = root;
		for (int i = 0; i < s2; i++)
		{
			ans[i] = new ListNode(p.val);
			p = p.next;
			ListNode q = ans[i];
			for (int j = 0; j < s1; j++)
			{
				q.next = new ListNode(p.val);
				p = p.next;
				q = q.next;
			}
		}
		for (int i = s2; i < k; i++)
		{
			if (s1 > 0)
			{
				ans[i] = new ListNode(p.val);
				p = p.next;
				ListNode q = ans[i];
				for (int j = 1; j < s1; j++)
				{
					q.next = new ListNode(p.val);
					p = p.next;
					q = q.next;
				}
			}
		}
		return ans;
	}
}

/////726. Number of Atoms
//Runtime: 6 ms, faster than 66.95% of Java online submissions for Number of Atoms.
//Memory Usage: 37.1 MB, less than 15.00% of Java online submissions for Number of Atoms.
class Token
{
	HashMap<String,Integer>ct=new HashMap<String,Integer>();
	int type=0;
	//0:'('
	//1:container
	Token(){}
	Token(int t){type=t;}
	void merge(Token o)
	{
		for (String key:o.ct.keySet())
			if (ct.containsKey(key))
			{
				ct.put(key,ct.get(key)+o.ct.get(key));
			}
			else
			{
				ct.put(key,o.ct.get(key));
			}
	}
	void mul(int m)
	{
		for (String key:ct.keySet())
			ct.put(key,ct.get(key)*m);
	}
	
}
class MP implements Comparable<MP>
{
	String str;
	int ct;
	MP(String _s,int _ct)
	{
		str=_s;
		ct=_ct;
	}
	@Override
	public int compareTo(MP o)
	{
		return str.compareTo(o.str);
	}
}
class Solution726
{
	boolean isLowerCase(char c)
	{
		return c>='a' && c<='z';
	}
	boolean isNum(char c)
	{
		return '0'<=c && c<='9';
	}
	public String countOfAtoms(String formula)
	{
		Stack<Token> stack=new Stack<Token>();
		int len=formula.length(),rp=0;
		while (rp<len)
		{
			if (formula.charAt(rp)=='(')
			{
				rp++;
				stack.push(new Token(0));
			}
			else if (formula.charAt(rp)==')')
			{
				rp++;
				int mul=0;
				while (rp<len && isNum(formula.charAt(rp)))
				{
					mul=mul*10+(formula.charAt(rp)-'0');
					rp++;
				}
				if (mul==0) mul=1;
				Token tmp=new Token(1);
				while (!stack.isEmpty() && stack.peek().type==1)
				{
					tmp.merge(stack.pop());
				}
				tmp.mul(mul);
				stack.pop();
				stack.push(tmp);
			}
			else
			{
				String name=formula.charAt(rp++)+"";
				while (rp<len && isLowerCase(formula.charAt(rp)))
				{
					name=name+formula.charAt(rp++);
				}
				int mul=0;
				while (rp<len && isNum(formula.charAt(rp)))
				{
					mul=mul*10+(formula.charAt(rp)-'0');
					rp++;
				}
				if (mul==0) mul=1;
				Token tmp=new Token(1);
				tmp.ct.put(name,mul);
				stack.push(tmp);
			}
		}
		Token coll=new Token(1);
		while (!stack.isEmpty())
		{
			coll.merge(stack.pop());
		}
		List<MP> a=new ArrayList<MP>();
		for (String s:coll.ct.keySet())
			a.add(new MP(s,coll.ct.get(s)));
		Collections.sort(a);
		String ans="";
		for (int i=0;i<a.size();i++)
			ans+=a.get(i).ct>1?a.get(i).str+a.get(i).ct:a.get(i).str;
		return ans;
	}
}

//728. Self Dividing Numbers
//Runtime: 3 ms, faster than 99.86% of Java online submissions for Self Dividing Numbers.
//Memory Usage: 36.8 MB, less than 53.33% of Java online submissions for Self Dividing Numbers.
class Solution728
{
	public static boolean jud(int i)
	{
		if (i == 0)
			return false;
		int ii = i;
		while (ii > 0)
		{
			int x = ii % 10;
			if (x == 0 || i % x != 0)
				return false;
			ii = ii / 10;
		}
		return true;
	}

	public List<Integer> selfDividingNumbers(int left, int right)
	{
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = left; i <= right; i++)
			if (jud(i))
				ans.add(i);
		return ans;
	}
}

public class LC721_730
{
	public static void main(String[] args)
	{
		String s="";
		System.out.println(new Solution726().countOfAtoms(s));
	}
}
