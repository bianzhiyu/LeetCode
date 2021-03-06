package lc1011_1020;

import java.util.ArrayList;
import java.util.List;

//1011. Capacity To Ship Packages Within D Days
//Runtime: 7 ms, faster than 90.21% of Java online submissions for Capacity To Ship Packages Within D Days.
//Memory Usage: 44.3 MB, less than 100.00% of Java online submissions for Capacity To Ship Packages Within D Days.
class Solution1011
{
	private boolean judge(int[] w, int Lt, int D)
	{
		int used = 0, rem = 0;
		for (int i = 0; i < w.length; i++)
		{
			if (rem >= w[i])
				rem -= w[i];
			else
			{
				used++;
				rem = Lt - w[i];
			}
			if (used > D)
				return false;
		}
		return true;
	}

	public int shipWithinDays(int[] weights, int D)
	{
		int L = weights[0], R = 0;
		for (int i = 0; i < weights.length; i++)
		{
			if (weights[i] > L)
				L = weights[i];
			R += weights[i];
		}
		while (L < R)
		{
			if (R == L + 1)
			{
				if (judge(weights, L, D))
					return L;
				else
					return R;
			}
			int m = (L + R) / 2;
			if (judge(weights, m, D))
				R = m;
			else
				L = m + 1;
		}
		return L;
	}
}

//1014. Best Sightseeing Pair
// Runtime: 2 ms, faster than 100.00% of Java online submissions for Best Sightseeing Pair.
// Memory Usage: 54.2 MB, less than 100.00% of Java online submissions for Best Sightseeing Pair.
class Solution1014 
{
	public int maxScoreSightseeingPair(int[] A) 
	{
        int len=A.length,cur=A[len-1]-len+1,max=0;
        for (int i=len-2;i>=0;i--)
        {
            if (A[i]+i+cur>max)
                max=A[i]+i+cur;
            if (A[i]-i>cur) cur=A[i]-i;
        }
        return max;
    }
}

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

//1018. Binary Prefix Divisible By 5
//Runtime: 3 ms, faster than 97.47% of Java online submissions for Binary Prefix Divisible By 5.
//Memory Usage: 40.9 MB, less than 29.06% of Java online submissions for Binary Prefix Divisible By 5.
class Solution1018 
{
    public List<Boolean> prefixesDivBy5(int[] A) 
    {
        ArrayList<Boolean> ans=new ArrayList<Boolean>();
        int x=0;
        for (int i=0;i<A.length;i++)
        {
        	x=(x%5)*2+A[i];
        	ans.add(x%5==0);
        }
        return ans;
    }
}

public class LC1011_1020
{
	public static void test1011()
	{
		int[] w = new int[]
		{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int D = 5;
		Solution1011 s = new Solution1011();
		System.out.println(s.shipWithinDays(w, D));
	}

	public static void test1017()
	{
		for (int i = -100; i < 100; i++)
		{
			String str = Solution1017.expNegBase(3, i);
			String nstr=""+Solution1017.toNum(-3, str);
			System.out.println(i + ": " + 
				str + " " + 
				Solution1017.toNum(-3, str)
				+ "  cmp="+nstr.compareTo(""+i));
		}
	}

	public static void main(String[] args)
	{
		test1017();
	}
}
