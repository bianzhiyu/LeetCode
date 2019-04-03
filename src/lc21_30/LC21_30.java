package lc21_30;

import java.util.ArrayList;
import java.util.List;
//import java.util.Date;
//import java.text.SimpleDateFormat;

class ListNode
{
	public int val;
	public ListNode next;

	public ListNode(int x)
	{
		val = x;
	}
}

//t21.Merge Two Sorted Lists    
//t=7ms, o=27MB
//t:0.7278, o:0.4016
class Solution21
{
	public ListNode mergeTwoLists(ListNode p1, ListNode p2)
	{
		ListNode head = new ListNode(0), before = head;
		while ((p1 != null) || (p2 != null))
		{
			if (p1 == null)
			{
				while (p2 != null)
				{
					before.next = new ListNode(p2.val);
					before = before.next;
					p2 = p2.next;
				}
			} else if (p2 == null)
			{
				while (p1 != null)
				{
					before.next = new ListNode(p1.val);
					before = before.next;
					p1 = p1.next;
				}
			} else
			{
				if (p1.val < p2.val)
				{
					before.next = new ListNode(p1.val);
					before = before.next;
					p1 = p1.next;
				} else
				{
					before.next = new ListNode(p2.val);
					before = before.next;
					p2 = p2.next;
				}
			}
		}
		return head.next;
	}
}

//t22.Generate Parentheses    
//t=1ms, m=27MB
//t:1, m:0.3943
class Solution22
{
	public static void dfs(int n, char[] st, int sp, int leftused, int sum, List<String> ans)
	{
		if (sp == 2 * n)
		{
			ans.add(new String(st));
			return;
		}
		if (leftused < n)
		{
			st[sp] = '(';
			dfs(n, st, sp + 1, leftused + 1, sum + 1, ans);
		}
		if (sum > 0)
		{
			st[sp] = ')';
			dfs(n, st, sp + 1, leftused, sum - 1, ans);
		}
	}

	public List<String> generateParenthesis(int n)
	{
		List<String> ans = new ArrayList<String>();
		char[] stack = new char[2 * n];
		dfs(n, stack, 0, 0, 0, ans);
		return ans;
	}
}

class test
{
	int count = 0;

	public void out(int[] stack)
	{
		System.out.println(count++);
	}

	public boolean legal(int[] stack, int sp)
	{
		return true;
	}

	public int CalcMsn(int[] stack, int sp)
	{
		return 3;
	}

	public void loopforrecursion(int MaxDepth)
	{
		int[] Stack = new int[MaxDepth];
		int[] Msn = new int[MaxDepth];
		Msn[0] = CalcMsn(Stack, 0);
		int sp = 0;
		Stack[0] = -1;
		while (sp > -1)
		{
			if (sp == MaxDepth)
			{
				out(Stack);
				sp--;
				continue;
			}

			Stack[sp]++;

			if (Stack[sp] == Msn[sp])
			{
				sp--;
				continue;
			}

			if (!legal(Stack, sp))
				continue;
			sp++;
			if (sp < MaxDepth)
			{
				Stack[sp] = -1;
				Msn[sp] = CalcMsn(Stack, sp);
			}

		}
	}
}

//t22 loop instead of recursion
//t:5ms, o:27MB
//t:0.1262, o:0.1312
class Solution22_2
{
	public List<String> generateParenthesis(int n)
	{
		List<String> ans = new ArrayList<String>();
		int MaxDepth = 2 * n;
		int[] Stack = new int[MaxDepth];
		int sp = 0, lp = 0, rp = 0;
		Stack[0] = -1;
		while (sp > -1)
		{
			if (sp == MaxDepth)
			{
				String s = "";
				for (int j = 0; j < MaxDepth; j++)
				{
					if (Stack[j] == 0)
					{
						s = s + "(";
					} else
					{
						s = s + ")";
					}
				}
				ans.add(s);
				sp--;
				continue;
			}

			Stack[sp]++;
			if (Stack[sp] == 0)
			{
				lp++;
			} else if (Stack[sp] == 1)
			{
				lp--;
				rp++;
			} else
			{
				rp--;
			}

			if (Stack[sp] == 2)
			{
				sp--;
				continue;
			}

			if (!((lp >= rp) && (lp <= n) && (rp <= n)))
				continue;
			sp++;
			if (sp < MaxDepth)
			{
				Stack[sp] = -1;
			}
		}
		return ans;
	}
}

//t23.Merge k Sorted Lists    
//t=7ms, o=29MB
//t:0.9106, o:0.6130
class Solution23
{
	public ListNode merge(int l, int r, ListNode[] lists)
	{
		if (l == r)
		{
			return lists[l];
		}
		ListNode p1 = merge(l, (l + r) / 2, lists), p2 = merge((l + r) / 2 + 1, r, lists);
		ListNode head = new ListNode(0), before = head;
		while ((p1 != null) || (p2 != null))
		{
			if (p1 == null)
			{
				while (p2 != null)
				{
					before.next = new ListNode(p2.val);
					before = before.next;
					p2 = p2.next;
				}
			} else if (p2 == null)
			{
				while (p1 != null)
				{
					before.next = new ListNode(p1.val);
					before = before.next;
					p1 = p1.next;
				}
			} else
			{
				if (p1.val < p2.val)
				{
					before.next = new ListNode(p1.val);
					before = before.next;
					p1 = p1.next;
				} else
				{
					before.next = new ListNode(p2.val);
					before = before.next;
					p2 = p2.next;
				}
			}
		}
		return head.next;
	}

	public ListNode mergeKLists(ListNode[] lists)
	{
		if (lists.length == 0)
		{
			return null;
		}
		return merge(0, lists.length - 1, lists);
	}
}

//t24.Swap Nodes in Pairs    
//t=2ms, o=26MB
//t:0.9998, o:0.1249
class Solution24
{
	public ListNode swapPairs(ListNode head)
	{
		ListNode b = head;
		while (head != null)
		{
			if (head.next != null)
			{
				int a = head.val;
				head.val = head.next.val;
				head.next.val = a;
				head = head.next.next;
			} else
			{
				break;
			}
		}
		return b;
	}
}

//t25.Reverse Nodes in k-Group    
//t=4ms, o=27MB
//t:0.7280, o:0.3718
class Solution25
{
	public ListNode reverseKGroup(ListNode head, int k)
	{
		if (k <= 1)
			return head;
		if (head == null)
			return head;

		// deal with first reverse
		ListNode a = head;
		int flag = 0;
		for (int i = 0; i < k - 1; i++)
		{
			if (a.next == null)
			{
				flag = 1;
			} else
			{
				a = a.next;
			}
		}
		if (flag == 1)
		{
			return head;
		}
		ListNode b = a.next;
		a = head;
		ListNode c = a.next;
		for (int i = 0; i < k - 1; i++)
		{
			ListNode d = c.next;
			c.next = a;
			a = c;
			c = d;
		}
		ListNode tailBefore = head;
		head.next = b;

		head = a;

		// deal with all other reverses
		while (true)
		{
			flag = 0;
			a = tailBefore;
			for (int i = 0; i < k; i++)
			{
				if (a.next != null)
				{
					a = a.next;
				} else
				{
					flag = 1;
					break;
				}
			}
			if (flag == 1)
			{
				break;
			}
			ListNode e = tailBefore.next;
			b = e;
			c = b.next;
			for (int i = 0; i < k - 1; i++)
			{
				ListNode d = c.next;
				c.next = b;
				b = c;
				c = d;
			}

			tailBefore.next.next = c;
			tailBefore.next = b;

			tailBefore = e;

		}
		return head;
	}
}

//t26.Remove Duplicates from Sorted Array    
//t=31ms, o=31MB
//t:0.0644, o=0.0540
class Solution26
{
	public int removeDuplicates(int[] nums)
	{
		int l = nums.length;
		int i = l - 1;
		while (i > 0)
		{
			int j = i;
			while ((j > 0) && (nums[j] == nums[i]))
			{
				j--;
			}
			if (nums[j] != nums[i])
			{
				int d = i - j - 1;
				for (int k = j + 1; k < l - d; k++)
				{
					nums[k] = nums[k + d];
				}
				i = j;
				l = l - d;
			} else
			{
				for (int k = 0; k < l - i; k++)
				{
					nums[k] = nums[k + i];
				}
				l = l - i;
				i = 0;
			}
		}

		return l;
	}
}

//t26_2
//t=6ms, o=30MB
//t:0.9748, o:0.3457
class Solution26_2
{
	public int removeDuplicates(int[] nums)
	{
		int i = 0;
		for (int j = 1; j < nums.length; j++)
		{
			if (nums[j] > nums[i])
			{
				nums[++i] = nums[j];
			}
		}
		return i + 1;
	}
}

//t27. Remove Elemen
//t=4ms, m=37.7MB
//t:0.9892, m:1
class Solution27
{
	public int removeElement(int[] nums, int val)
	{
		int wp = 0, rp = 0;
		while (rp < nums.length)
			if (nums[rp] == val)
				rp++;
			else
				nums[wp++] = nums[rp++];
		return wp;
	}
}

//28. Implement strStr()
//KMP
//Runtime: 5 ms, faster than 63.02% of Java online submissions for Implement strStr().
//Memory Usage: 38.7 MB, less than 17.50% of Java online submissions for Implement strStr().
class Solution28
{
	public static int[] getNext2(String ps)
	{
		char[] p = ps.toCharArray();
		int[] next = new int[p.length];
		next[0] = 0;
		if (p.length > 1)
			next[1] = 0;
		for (int i = 2; i < ps.length(); i++)
		{
			int y = next[i - 1];
			while (y > 0 && p[i - 1] != p[y])
				y = next[y];
			if (p[i - 1] == p[y])
				next[i] = y + 1;
			else
				next[i] = 0;
		}
		return next;
	}

	public int strStr(String S, String P)
	{
		if (P.length() == 0)
			return 0;
		if (S.length() == 0)
			return -1;
		int[] F = getNext2(P);
		int i = 0, j = 0, SL = S.length(), PL = P.length();
		while (i <= SL)
		{
			if (j == PL)
				return i - PL;
			if (i == SL)
				return -1;

			if (S.charAt(i) == P.charAt(j))
			{
				i++;
				j++;
			} else if (j <= 0)
			{
				i++;
				j = 0;
			} else
			{
				j = F[j];
			}
		}
		return -1;
	}
}

//t29.Divide Two Integers    
//t=17ms, o=27MB
//t:0.6950, o:0.4665
class Solution29
{
	public int divide(int dividend, int divisor)
	{
		boolean neg = false;
		long la = dividend;
		long lb = divisor;
		if (dividend < 0)
		{
			neg = !neg;
			la = -la;
		}
		if (divisor < 0)
		{
			neg = !neg;
			lb = -lb;
		}
		int l = 0;
		long[] b = new long[33];
		long[] n = new long[33];
		b[0] = lb;
		n[0] = 1;
		while (b[l] <= la)
		{
			l = l + 1;
			b[l] = b[l - 1] + b[l - 1];
			n[l] = n[l - 1] + n[l - 1];
		}
		long ans = 0;
		for (int i = l - 1; (i >= 0) && (la > 0); i--)
		{
			if (la >= b[i])
			{
				la = (la - b[i]);
				ans = (ans + n[i]);
			}
		}
		if (neg)
		{
			ans = -ans;
		}
		if (!neg)
		{
			return ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) ans;
		} else
		{
			return ans < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) ans;
		}
	}

}

//t30
//severe time limit exceeded
class Solution30
{
	String[] WL;
	String S;
	int sl, wn, wl;
	boolean[] used;
	boolean[][] cp;

	boolean dfs(int i, int n)
	{
		if (n == wn)
			return true;
		for (int j = 0; j < wn; j++)
			if (!used[j] && cp[i][j])
			{
				used[j] = true;
				if (dfs(i + wl, n + 1))
					return true;
				used[j] = false;
			}
		return false;
	}

	public List<Integer> findSubstring(String s, String[] words)
	{
		if (s.length() == 0 || words.length == 0 || words[0].length() == 0)
			return new ArrayList<Integer>();
		sl = s.length();
		wn = words.length;
		wl = words[0].length();
		WL = words;
		S = s;
		used = new boolean[wn];
		List<Integer> ans = new ArrayList<Integer>();
		cp = new boolean[sl - wl + 1][];

//    	long startTime=System.currentTimeMillis(); 
//    	
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		for (int i = 0; i <= sl - wl; i++)
		{
			cp[i] = new boolean[wn];
			String tmp = S.substring(i, i + wl);
			for (int j = 0; j < wn; j++)
				cp[i][j] = tmp.compareTo(WL[j]) == 0;
		}
		// test out
//    	for (int i=0;i<=sl-wl;i++)
//    	{
//    		System.out.println(i+":  ");
//    		for (int j=0;j<wn;j++)
//    			System.out.print(cp[i][j]?1:0);
//    		System.out.println();
//    	}

//    	df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//    	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//    	
//    	System.out.println((System.currentTimeMillis()-startTime)+"ms");
//    	startTime=System.currentTimeMillis(); 

		for (int i = 0; i <= sl - wn * wl; i++)
		{
			for (int j = 0; j < wn; j++)
				used[j] = false;
			if (dfs(i, 0))
				ans.add(i);
//        	System.out.println((System.currentTimeMillis()-startTime)+"ms");
//        	startTime=System.currentTimeMillis();
		}

//        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        System.out.println((System.currentTimeMillis()-startTime)+"ms");

		return ans;
	}
}

//t30_2
//t:43ms, m=39.3MB
//t:0.7369, m:1
class Solution30_2
{
	List<String> Type;// type
	List<Integer> Cap; // capacity
	List<Integer> MT;// Match Type
	int WL, SL, WN;// WL:word length, SL: s length, WN:the number of words

	public List<Integer> merge(int l, int r, List<List<Integer>> lists)
	{
		if (l == r)
			return lists.get(l);
		List<Integer> p1 = merge(l, (l + r) / 2, lists), p2 = merge((l + r) / 2 + 1, r, lists);
		List<Integer> ans = new ArrayList<Integer>();
		int i1 = 0, i2 = 0, l1 = p1.size(), l2 = p2.size();
		while (i1 < l1 || i2 < l2)
		{
			if (i1 == l1)
			{
				while (i2 < l2)
					ans.add(p2.get(i2++));
			} else if (i2 == l2)
			{
				while (i1 < l1)
					ans.add(p1.get(i1++));
			} else
			{
				if (p1.get(i1) < p2.get(i2))
					ans.add(p1.get(i1++));
				else
					ans.add(p2.get(i2++));
			}
		}
		return ans;
	}

	public List<Integer> mergeKLists(List<List<Integer>> lists)
	{
		if (lists.size() == 0)
			return new ArrayList<Integer>();
		return merge(0, lists.size() - 1, lists);
	}

	public void TypeMerge(String[] words)
	{
		Type = new ArrayList<String>();
		Cap = new ArrayList<Integer>();
		for (int i = 0; i < words.length; i++)
		{
			int j = Type.indexOf(words[i]);
			if (j < 0)
			{
				Type.add(words[i]);
				Cap.add(1);
			} else
				Cap.set(j, Cap.get(j) + 1);
		}
	}

	public void Analyze(String s)
	{
		MT = new ArrayList<Integer>();
		for (int i = 0; i < SL - WL + 1; i++)
			MT.add(Type.indexOf(s.substring(i, i + WL)));
	}

	public List<Integer> Select(List<Integer> L, int start, int d)
	{
		List<Integer> A = new ArrayList<Integer>();
		for (int i = start; i < L.size(); i += d)
			A.add(L.get(i));
		return A;
	}

	public List<Integer> FindMatch(List<Integer> S, int shift)
	{
		List<Integer> A = new ArrayList<Integer>();
		int slen = S.size();
		int[] ct = new int[Type.size()];
		for (int i = 0; i < slen - WN + 1; i++)
		{
			for (int j = 0; j < Type.size(); j++)
				ct[j] = 0;
			int j = i;
			for (j = i; j < i + WN; j++)
			{
				if (S.get(j) < 0)
					break;
				if (ct[S.get(j)] == Cap.get(S.get(j)))
					break;
				ct[S.get(j)]++;
			}
			if (j == i + WN)
				A.add(i * WL + shift);
		}
		return A;
	}

	public List<Integer> findSubstring(String s, String[] words)
	{
		if (s.length() == 0 || words.length == 0 || words[0].length() == 0)
			return new ArrayList<Integer>();
		WL = words[0].length();
		SL = s.length();
		WN = words.length;
		TypeMerge(words);
		Analyze(s);
		List<List<Integer>> AnsL = new ArrayList<List<Integer>>();
		for (int i = 0; i < WL; i++)
			AnsL.add(FindMatch(Select(MT, i, WL), i));
		return mergeKLists(AnsL);
	}
}

public class LC21_30
{
	public static void main(String[] args)
	{
		System.out.println();
	}
}
