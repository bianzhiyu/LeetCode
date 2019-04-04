package lc1011_1020;

import java.util.ArrayList;
import java.util.List;

//1017. Convert to Base -2
//Runtime: 1 ms, faster than 97.42% of Java online submissions for Convert to Base -2.
//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Convert to Base -2.
class Solution1017
{
	public static String expNegBase(int a, int N)
	{
		if (N == 0)
			return "0";
		List<Integer> ans = new ArrayList<Integer>();
		boolean normalD = true;
		while (N != 0)
		{
			if (normalD)
			{
				int nd = N % a;
				if (nd < 0)
					nd += a;
				ans.add(nd);
				N = (N - nd) / a;
			} else
			{
				int nd = (a - N % a) % a;
				if (nd < 0)
					nd += a;
				ans.add(nd);
				N = (N + nd) / a;
			}
			normalD = !normalD;
		}
		StringBuilder sb = new StringBuilder();
		int len = ans.size();
		for (int i = 0; i < ans.size(); i++)
			sb.append(ans.get(len - 1 - i));
		return sb.toString();
	}

	public static String baseNeg2(int N)
	{
		return expNegBase(2, N);
	}

	public static int toNum(int a, String str)
	{
		int x = 1, s = 0;
		for (int i = str.length() - 1; i >= 0; i--)
		{
			s = s + x * (str.charAt(i) - '0');
			x *= a;
		}
		return s;
	}
}

//original
class Solution1017_2
{
	public String baseNeg2(int N)
	{
		if (N == 0)
			return "0";
		int a = 2;
		List<Integer> ans = new ArrayList<Integer>();
		boolean normalD = true;
		while (N != 0)
		{
			if (normalD)
			{
				int nd = N % a;
				if (nd < 0)
					nd += a;
				ans.add(nd);
				N = (N - nd) / a;
			} else
			{
				int nd = (a - N % a) % a;
				if (nd < 0)
					nd += a;
				ans.add(nd);
				N = (N + nd) / a;
			}
			normalD = !normalD;
		}
		StringBuilder sb = new StringBuilder();
		int len = ans.size();
		for (int i = 0; i < ans.size(); i++)
			sb.append(ans.get(len - 1 - i));
		return sb.toString();
	}
}

public class LC1011_1020
{
	public static void test1017()
	{
		for (int i=-100;i<100;i++)
		{
			String str = Solution1017.expNegBase(3,i);
			System.out.println(i+": "+str+" "+Solution1017.toNum(-3, str));
		}
	}

	public static void main(String[] args)
	{
		test1017();
	}
}
