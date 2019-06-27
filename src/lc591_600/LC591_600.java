package lc591_600;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//592. Fraction Addition and Subtraction
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Fraction Addition and Subtraction.
//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Fraction Addition and Subtraction.
class Solution592
{
	private int gcd(int a, int b)
	{
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public String fractionAddition(String expression)
	{
		int p = 0, len = expression.length(), sign;
		int up = 0, down = 1;
		while (p < len)
		{
			if (p == 0)
			{
				if (expression.charAt(0) == '-')
				{
					sign = -1;
					p++;
				} else
					sign = 1;
			} else
			{
				if (expression.charAt(p) == '-')
					sign = -1;
				else
					sign = 1;
				p++;
			}
			int x = 0;
			while (expression.charAt(p) != '/')
			{
				x = x * 10 + (expression.charAt(p) - '0');
				p++;
			}
			p++;
			int y = 0;
			while (p < len && expression.charAt(p) >= '0' && expression.charAt(p) <= '9')
			{
				y = y * 10 + (expression.charAt(p) - '0');
				p++;
			}

			int e = gcd(down, y);
			up = (y / e) * up + sign * x * (down / e);
			down = down * y / e;

			if (up == 0)
			{
				down = 1;
			} else
			{
				e = gcd(Math.abs(up), down);
				down /= e;
				up /= e;
			}
		}
		if (up == 0)
			return "0/1";
		return up + "/" + down;
	}
}

//593. Valid Square
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Square.
//Memory Usage: 34.7 MB, less than 85.19% of Java online submissions for Valid Square.
class Solution593 
{
	private int norm2(int[] v)
	{
		return v[0]*v[0]+v[1]*v[1];
	}
	private boolean check(int[] v1,int[] v2,int[] v3)
	{
		if (v1[0]+v2[0]!=v3[0] || v1[1]+v2[1]!=v3[1]) return false;
		if (norm2(v1)!=norm2(v2) || 2*norm2(v1)!=norm2(v3)) return false;
		if (v1[0]*v2[0]+v1[1]*v2[1]!=0) return false;
		return true;
	}
	private int[] minus(int[] p1,int[] p2)
	{
		return new int[]
				{p1[0]-p2[0],p1[1]-p2[1]};
	}
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) 
    {
        int[] v1=minus(p2,p1),v2=minus(p3,p1),v3=minus(p4,p1);
        return norm2(v1)>0 && (check(v1,v2,v3) || check(v1,v3,v2) || check(v2,v3,v1));
    }
}

//594 Longest Harmonious Subsequence    
//Runtime: 34 ms, faster than 95.06% of Java online submissions for Longest Harmonious Subsequence.
//Memory Usage: 42.3 MB, less than 30.44% of Java online submissions for Longest Harmonious Subsequence.
class Solution594
{
	public int findLHS(int[] nums)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hm.containsKey(nums[i]))
				hm.put(nums[i], hm.get(nums[i]) + 1);
			else
				hm.put(nums[i], 1);
		int m = 0, t = 0;
		for (int i : hm.keySet())
			if (hm.containsKey(i + 1))
			{
				t = hm.get(i) + hm.get(i + 1);
				if (t > m)
					m = t;
			}
		return m;
	}
}

//599. Minimum Index Sum of Two Lists
//Runtime: 26 ms, faster than 20.42% of Java online submissions for Minimum Index Sum of Two Lists.
//Memory Usage: 52.2 MB, less than 5.52% of Java online submissions for Minimum Index Sum of Two Lists.
class Solution599
{
	private static class TrieNode
	{
		TrieNode[] son = new TrieNode[53];
		@SuppressWarnings("unused")
		boolean isWord = false;
		int ind = -1;
	}

	private int ord(char c)
	{
		if ('A' <= c && c <= 'Z')
			return c - 'A';
		if ('a' <= c && c <= 'z')
			return c - 'a' + 26;
//		(c==' ') 
		return 52;
	}

	private void add(TrieNode rt, String s, int ind)
	{
		for (int i = 0; i < s.length(); i++)
		{
			int c = ord(s.charAt(i));
			if (rt.son[c] == null)
				rt.son[c] = new TrieNode();
			rt = rt.son[c];
		}
		rt.isWord = true;
		rt.ind = ind;
	}

	private int find(TrieNode rt, String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			int c = ord(s.charAt(i));
			if (rt.son[c] == null)
				return -1;
			rt = rt.son[c];
		}
		return rt.ind;
	}

	public String[] findRestaurant(String[] list1, String[] list2)
	{
		TrieNode rt = new TrieNode();
		for (int i = 0; i < list1.length; i++)
			add(rt, list1[i], i);
		int[] stack = new int[list2.length];
		int st = 0, indexsum = 1000000;
		for (int i = 0; i < list2.length; i++)
		{
			int id = find(rt, list2[i]);
			if (id != -1)
			{
				if (st == 0)
				{
					stack[0] = i;
					indexsum = id + i;
					st = 1;
				} else if (id + i < indexsum)
				{
					st = 1;
					stack[0] = i;
					indexsum = id + i;
				} else if (id + i == indexsum)
				{
					stack[st] = i;
					st++;
				}
			}
		}
		String[] ans = new String[st];
		for (int i = 0; i < st; i++)
			ans[i] = list2[stack[i]];
		return ans;
	}
}

public class LC591_600
{
	public static void test594()
	{
		Solution594 s = new Solution594();
		System.out.println(s.findLHS(new int[]
		{ 1, 1, 1, 1 }));
	}

	public static void test592()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input592.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output592.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				Solution592 s = new Solution592();

				String ans = s.fractionAddition(inLine);
				System.out.println(ans);

				bfw.write(ans);
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

	public static void main(String[] args)
	{
		test592();
	}
}
