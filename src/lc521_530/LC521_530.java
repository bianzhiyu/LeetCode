package lc521_530;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//521. Longest Uncommon Subsequence I
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Uncommon Subsequence I .
//Memory Usage: 35.2 MB, less than 94.94% of Java online submissions for Longest Uncommon Subsequence I .
class Solution521
{
	public int findLUSlength(String a, String b)
	{
		if (a.equals(b))
			return -1;
		else
			return a.length() > b.length() ? a.length() : b.length();
	}
}

//522. Longest Uncommon Subsequence II
//Runtime: 6 ms, faster than 53.56% of Java online submissions for Longest Uncommon Subsequence II.
//Memory Usage: 37.4 MB, less than 25.00% of Java online submissions for Longest Uncommon Subsequence II.
class Solution522
{
	private boolean isSubstr(String sub,String str,int p1,int p2)
	{
		if (p1==sub.length()) return true;
		if (p2==str.length()) return false;
		boolean a1=isSubstr(sub,str,p1,p2+1);
		if (a1) return true;
		if (sub.charAt(p1)==str.charAt(p2))
			a1=isSubstr(sub,str,p1+1,p2+1);
		return a1;
	}
	public int findLUSlength(String[] strs)
	{
		int max = -1;
		for (int i = 0; i < strs.length; i++)
			for (int j = 0; j < strs[i].length(); j++)
				for (int k = j + 1; k <= strs[i].length(); k++)
				{
					if (k - j <= max)
						continue;
					boolean exi = false;
					String sub=strs[i].substring(j,k);
					for (int l = 0; l < strs.length; l++)
						if (l != i && isSubstr(sub,strs[l],0,0))
						{
							exi = true;
							break;
						}
					if (!exi)
						max = k - j;
				}
		return max;
	}
}

//523. Continuous Subarray Sum
//Runtime: 37 ms, faster than 15.22% of Java online submissions for Continuous Subarray Sum.
//Memory Usage: 41.4 MB, less than 6.29% of Java online submissions for Continuous Subarray Sum.
class Solution523
{
	public boolean checkSubarraySum(int[] nums, int k)
	{
		if (k < 0)
			k = -k;
		HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
		int len = nums.length;
		int[] s = new int[len + 1];
		s[0] = 0;
		for (int i = 1; i <= len; i++)
		{
			if (k > 0)
				s[i] = (s[i - 1] + nums[i - 1]) % k;
			else
				s[i] = s[i - 1] + nums[i - 1];
			if (!hm.containsKey(s[i]))
				hm.put(s[i], new ArrayList<Integer>());
			hm.get(s[i]).add(i);
		}
		int tot = 0;
		for (int i = 1; i <= len; i++)
		{
			List<Integer> l = hm.get(s[i - 1]);
			if (l == null)
				continue;
			if (l.get(l.size() - 1) < i + 1)
				continue;
			int f = 0, r = l.size() - 1;
			while (f < r)
			{
				if (r == f + 1)
				{
					if (l.get(f) >= i + 1)
						r = f;
					else
						f = r;
					break;
				}
				int m = (f + r) / 2;
				if (l.get(m) >= i + 1)
					r = m;
				else
					f = m + 1;
			}
			tot += l.size() - r;
		}
		return tot > 0;
	}
}

//524. Longest Word in Dictionary through Deleting
//Runtime: 41 ms, faster than 45.67% of Java online submissions for Longest Word in Dictionary through Deleting.
//Memory Usage: 39.9 MB, less than 90.55% of Java online submissions for Longest Word in Dictionary through Deleting.
class Solution524
{
	boolean reachable(String s, String ss)
	{
		int sp = 0, ssp = 0;
		while (sp < s.length() && ssp < ss.length())
		{
			if (s.charAt(sp) == ss.charAt(ssp))
			{
				sp++;
				ssp++;
			} else
				sp++;
		}
		return ssp == ss.length();
	}

	public String findLongestWord(String s, List<String> d)
	{
		String ans = "";
		int n = d.size();
		for (int i = 0; i < n; i++)
			if (reachable(s, d.get(i)) && (ans.length() < d.get(i).length()
					|| (ans.length() == d.get(i).length() && d.get(i).compareTo(ans) < 0)))
			{
				ans = d.get(i);
			}
		return ans;
	}
}

//528. Random Pick with Weight
//Runtime: 70 ms, faster than 94.84% of Java online submissions for Random Pick with Weight.
//Memory Usage: 53.3 MB, less than 42.91% of Java online submissions for Random Pick with Weight.
class Solution528
{
	Random rd;
	int[] s;
	int m;

	public Solution528(int[] w)
	{
		rd = new Random();
		int len = w.length;
		s = new int[len];
		m = -1;
		for (int i = 0; i < len; i++)
		{
			m += w[i];
			s[i] = m;
		}
	}

	int fd(int n)
	{
		int l = 0, r = s.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (s[l] >= n)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (s[m] >= n)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}

	public int pickIndex()
	{
		int n = rd.nextInt(m + 1);
		return fd(n);
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */

public class LC521_530
{
	public static void main(String[] args)
	{
		Solution524 s = new Solution524();
		System.out.println(s.reachable("aaa", "aaa"));
		System.out.println("a".compareTo("b"));
	}

}
