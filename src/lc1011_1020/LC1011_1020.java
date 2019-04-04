package lc1011_1020;

import java.util.ArrayList;
import java.util.List;

//1017. Convert to Base -2
//Runtime: 1 ms, faster than 97.42% of Java online submissions for Convert to Base -2.
//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Convert to Base -2.
class Solution1017
{
	public int a = 2;// base

	public String baseNeg2(int N)
	{
		if (N == 0)
			return "0";
		List<Integer> ans = new ArrayList<Integer>();
		boolean normalD = true;
		while (N != 0)
		{
			if (normalD)
			{
				ans.add(N % a);
				N = N / a;
			} else
			{
				if (N % a == 0)
				{
					ans.add(0);
					N = N / a;
				} else
				{
					int d = a - N % a;
					ans.add(d);
					N = (N + d) / a;
				}
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
		Solution1017 s = new Solution1017();
		s.baseNeg2(4);
	}

	public static void main(String[] args)
	{

	}
}
