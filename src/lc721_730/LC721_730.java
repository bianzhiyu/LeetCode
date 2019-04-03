package lc721_730;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import bbst.BBST;

//721. Accounts Merge
//Runtime: 30 ms, faster than 96.57% of Java online submissions for Accounts Merge.
//Memory Usage: 46.6 MB, less than 61.39% of Java online submissions for Accounts Merge.
class Solution721
{
	private HashMap<String, String> mailToName = new HashMap<String, String>();
	private HashMap<String, String> father = new HashMap<String, String>();

	private String getTop(String str)
	{
		String p = str;
		while (p.compareTo(father.get(p)) != 0)
			p = father.get(p);
		while (father.get(str).compareTo(p) != 0)
		{
			String q = father.get(str);
			father.put(str, p);
			str = q;
		}
		return p;
	}

	private void merge(String s1, String s2)
	{
		String a1 = getTop(s1);
		String a2 = getTop(s2);
		father.put(a2, a1);
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts)
	{
		for (List<String> act : accounts)
		{
			String ancMail = "";
			boolean fd = false;
			for (int i = 1; i < act.size(); i++)
			{
				if (father.containsKey(act.get(i)))
				{
					fd = true;
					ancMail = getTop(act.get(i));
					break;
				}
			}
			if (fd)
			{
				for (int i = 1; i < act.size(); i++)
					if (!father.containsKey(act.get(i)))
						father.put(act.get(i), ancMail);
					else
						merge(ancMail, act.get(i));
			} else
			{
				for (int i = 1; i < act.size(); i++)
					father.put(act.get(i), act.get(1));
				mailToName.put(act.get(1), act.get(0));
			}
		}

		HashMap<String, PriorityQueue<String>> ancToQueue = new HashMap<String, PriorityQueue<String>>();
		for (String mail : father.keySet())
		{
			String anc = getTop(mail);
			if (!ancToQueue.containsKey(anc))
			{
				PriorityQueue<String> pq = new PriorityQueue<String>();
				pq.offer(mail);
				ancToQueue.put(anc, pq);
			} else
				ancToQueue.get(anc).offer(mail);
		}
		List<List<String>> ans = new ArrayList<List<String>>();
		for (String anc : ancToQueue.keySet())
		{
			List<String> tmp = new ArrayList<String>();
			tmp.add(mailToName.get(anc));
			PriorityQueue<String> pq = ancToQueue.get(anc);
			while (!pq.isEmpty())
				tmp.add(pq.poll());
			ans.add(tmp);
		}
		return ans;
	}
}

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
class Solution726
{
	private static class Token
	{
		HashMap<String, Integer> ct = new HashMap<String, Integer>();
		int type = 0;

		// 0:'('
		// 1:container
		Token(int t)
		{
			type = t;
		}

		void merge(Token o)
		{
			for (String key : o.ct.keySet())
				if (ct.containsKey(key))
				{
					ct.put(key, ct.get(key) + o.ct.get(key));
				} else
				{
					ct.put(key, o.ct.get(key));
				}
		}

		void mul(int m)
		{
			for (String key : ct.keySet())
				ct.put(key, ct.get(key) * m);
		}

	}

	private static class MP implements Comparable<MP>
	{
		String str;
		int ct;

		MP(String _s, int _ct)
		{
			str = _s;
			ct = _ct;
		}

		@Override
		public int compareTo(MP o)
		{
			return str.compareTo(o.str);
		}
	}

	boolean isLowerCase(char c)
	{
		return c >= 'a' && c <= 'z';
	}

	boolean isNum(char c)
	{
		return '0' <= c && c <= '9';
	}

	public String countOfAtoms(String formula)
	{
		Stack<Token> stack = new Stack<Token>();
		int len = formula.length(), rp = 0;
		while (rp < len)
		{
			if (formula.charAt(rp) == '(')
			{
				rp++;
				stack.push(new Token(0));
			} else if (formula.charAt(rp) == ')')
			{
				rp++;
				int mul = 0;
				while (rp < len && isNum(formula.charAt(rp)))
				{
					mul = mul * 10 + (formula.charAt(rp) - '0');
					rp++;
				}
				if (mul == 0)
					mul = 1;
				Token tmp = new Token(1);
				while (!stack.isEmpty() && stack.peek().type == 1)
				{
					tmp.merge(stack.pop());
				}
				tmp.mul(mul);
				stack.pop();
				stack.push(tmp);
			} else
			{
				String name = formula.charAt(rp++) + "";
				while (rp < len && isLowerCase(formula.charAt(rp)))
				{
					name = name + formula.charAt(rp++);
				}
				int mul = 0;
				while (rp < len && isNum(formula.charAt(rp)))
				{
					mul = mul * 10 + (formula.charAt(rp) - '0');
					rp++;
				}
				if (mul == 0)
					mul = 1;
				Token tmp = new Token(1);
				tmp.ct.put(name, mul);
				stack.push(tmp);
			}
		}
		Token coll = new Token(1);
		while (!stack.isEmpty())
		{
			coll.merge(stack.pop());
		}
		List<MP> a = new ArrayList<MP>();
		for (String s : coll.ct.keySet())
			a.add(new MP(s, coll.ct.get(s)));
		Collections.sort(a);
		String ans = "";
		for (int i = 0; i < a.size(); i++)
			ans += a.get(i).ct > 1 ? a.get(i).str + a.get(i).ct : a.get(i).str;
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

//729. My Calendar I
//Runtime: 72 ms, faster than 91.12% of Java online submissions for My Calendar I.
//Memory Usage: 53.7 MB, less than 34.50% of Java online submissions for My Calendar I.
class MyCalendar
{
	private static class Mp implements Comparable<Mp>
	{
		int st, ed;

		public Mp(int a, int b)
		{
			st = a;
			ed = b;
		}

		@Override
		public int compareTo(Mp o)
		{
			if (ed < o.st)
				return -1;
			if (st > o.ed)
				return 1;
			return 0;
		}
	}

	private BBST<Mp> root;

	public MyCalendar()
	{

	}

	public boolean book(int start, int end)
	{
		if (root == null)
		{
			root = new BBST<Mp>(new Mp(start, end - 1));
			return true;
		}
		Mp p = new Mp(start, end - 1);
		if (root.containData(p))
			return false;
		else
		{
			root = root.insert(p);
			return true;
		}
	}
}

/**
 * Your MyCalendar object will be instantiated and called as such: MyCalendar
 * obj = new MyCalendar(); boolean param_1 = obj.book(start,end);
 */

public class LC721_730
{
	public static void test721()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input721.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output721.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int rows = Integer.parseInt(inLine);
				List<List<String>> in = new ArrayList<List<String>>();
				for (int i = 0; i < rows; i++)
				{
					inLine = bfr.readLine();
					inLine = inLine.substring(1, inLine.length() - 1);
					String[] eachC = inLine.split(",");

					List<String> tmp = new ArrayList<String>();
					for (String s : eachC)
						tmp.add(s.substring(1, s.length() - 1));
					in.add(tmp);
				}

				Solution721 s = new Solution721();

				List<List<String>> out = s.accountsMerge(in);

				for (List<String> eachrow : out)
				{
					System.out.println(eachrow);
					bfw.write(eachrow.toString());
					bfw.newLine();
				}

				System.out.println();
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void test726()
	{
		String s = "";
		System.out.println(new Solution726().countOfAtoms(s));
	}

	public static void main(String[] args)
	{
		test721();
	}
}
