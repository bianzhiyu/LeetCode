package lc921_930;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//921. Minimum Add to Make Parentheses Valid
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Minimum Add to Make Parentheses Valid.
//Memory Usage: 37.3 MB, less than 7.66% of Java online submissions for Minimum Add to Make Parentheses 
class Solution921
{
	public int minAddToMakeValid(String S)
	{
		int p = 0, a = 0;
		for (char c : S.toCharArray())
		{
			if (c == '(')
				p++;
			else
			{
				if (p > 0)
					p--;
				else
					a++;
			}
		}
		a += p;
		return a;
	}
}

//923. 3Sum With Multiplicity
//Runtime: 788 ms, faster than 5.08% of Java online submissions for 3Sum With Multiplicity.
//Memory Usage: 72.9 MB, less than 5.88% of Java online submissions for 3Sum With Multiplicity.
class Solution923
{
	private final static int MOD = 1_000_000_007;

	public int threeSumMulti(int[] A, int target)
	{
		int len = A.length;
		List<HashMap<Integer, Integer>> a = new ArrayList<HashMap<Integer, Integer>>(len);
		a.add(new HashMap<Integer, Integer>());
		a.get(0).put(A[0], 1);
		for (int i = 1; i < len; i++)
		{
			HashMap<Integer, Integer> t = new HashMap<Integer, Integer>();
			t.putAll(a.get(i - 1));
			t.put(A[i], t.getOrDefault(A[i], 0) + 1);
			a.add(t);
		}
		int tot = 0;
		for (int i = len - 1; i >= 0; i--)
			for (int j = i - 1; j >= 1; j--)
			{
				int rem = target - A[i] - A[j];
				if (a.get(j - 1).containsKey(rem))
					tot = (tot + a.get(j - 1).get(rem)) % MOD;
			}
		return tot;
	}
}

//https://leetcode.com/problems/3sum-with-multiplicity/solution/
//Runtime: 4 ms, faster than 96.07% of Java online submissions for 3Sum With Multiplicity.
//Memory Usage: 38.4 MB, less than 83.33% of Java online submissions for 3Sum With Multiplicity.
class Solution923_2
{
	private final static int MOD = 1_000_000_007;

	public int threeSumMulti(int[] A, int target)
	{
		int len = A.length;
		Arrays.parallelSort(A);
		int[] n = new int[len];
		int[] c = new int[len];
		int nlen = 0;
		int i = 0;
		HashMap<Integer, Integer> f = new HashMap<Integer, Integer>();
		while (i < len)
		{
			int j = i + 1;
			while (j < len && A[j] == A[i])
				j++;
			n[nlen] = A[i];
			c[nlen] = j - i;
			f.put(n[nlen], c[nlen]);
			nlen++;
			i = j;
		}
		int tot = 0;
		for (i = 0; i < nlen; i++)
		{
			int T = target - n[i];
			int j = i + 1, k = nlen - 1;
			int c2 = f.getOrDefault(target - n[i] - n[i], 0);
			if (c[i] > 1 && c2 > 0 && target - n[i] - n[i] > n[i])
			{
				long tmp = ((long) c[i]) * (c[i] - 1) / 2 * c2 % MOD;
				tot = (tot + ((int) tmp)) % MOD;
			}
			if (n[i] * 3 == target && c[i] >= 3)
			{
				long tmp = ((long) c[i]) * (c[i] - 1) * (c[i] - 2) / 6 % MOD;
				tot = (tot + ((int) tmp)) % MOD;
			}
			while (j <= k)
			{
				if (j == k)
				{
					if (T == n[j] + n[j])
					{
						long tmp = ((long) c[j]) * (c[j] - 1) / 2 * c[i] % MOD;
						tot = (tot + ((int) tmp)) % MOD;
					}
					j++;
				} else
				{
					if (n[j] + n[k] < T)
						j++;
					else if (n[j] + n[k] > T)
						k--;
					else
					{
						long tmp = ((long) c[j]) * c[k] * c[i] % MOD;
						tot = (tot + ((int) tmp)) % MOD;
						j++;
					}
				}
			}
		}

		return tot;
	}
}

//924. Minimize Malware Spread
//Runtime: 11 ms, faster than 78.76% of Java online submissions for Minimize Malware Spread.
//Memory Usage: 69.5 MB, less than 9.58% of Java online submissions for Minimize Malware Spread.
class Solution924
{
	int bfs(int[][] g, int start, int[] cl, int c)
	{
		int[] q = new int[g.length];
		boolean[] u = new boolean[g.length];
		q[0] = start;
		u[start] = true;

		int f = 0, r = 1;
		while (f < r)
		{
			cl[q[f]] = c;
			for (int i = 0; i < g.length; i++)
				if (g[q[f]][i] == 1 && i != q[f] && !u[i])
				{
					q[r++] = i;
					u[i] = true;
				}
			f++;
		}
		return r;

	}

	public int minMalwareSpread(int[][] graph, int[] initial)
	{
		Arrays.sort(initial);
		int len = graph.length;
		int[] color = new int[len];
		int[] numInGrp = new int[len];
		int colNums = 0;
		Arrays.fill(color, -1);
		for (int i = 0; i < len; i++)
			if (color[i] == -1)
			{
				numInGrp[colNums] = bfs(graph, i, color, colNums);
				colNums++;
			}
		int[] malNumbersInGrp = new int[len];
		int[] firstMalIndexInGrp = new int[len];
		for (int i = 0; i < initial.length; i++)
		{
			malNumbersInGrp[color[initial[i]]]++;
			if (malNumbersInGrp[color[initial[i]]] == 1)
				firstMalIndexInGrp[color[initial[i]]] = initial[i];
		}
		int fdNodeInd = -1;
		for (int i = 0; i < colNums; i++)
			if (malNumbersInGrp[i] == 1)
			{
				if (fdNodeInd == -1)
					fdNodeInd = firstMalIndexInGrp[i];
				else
				{
					if (numInGrp[i] > numInGrp[color[fdNodeInd]])
						fdNodeInd = firstMalIndexInGrp[i];
				}
			}
		if (fdNodeInd != -1)
			return fdNodeInd;
		else
			return initial[0];
	}
}

//926. Flip String to Monotone Increasing
//Runtime: 8 ms, faster than 55.56% of Java online submissions for Flip String to Monotone Increasing.
//Memory Usage: 37.4 MB, less than 27.27% of Java online submissions for Flip String to Monotone Increasing.
class Solution926
{
	private int[] pct;

	public int minFlipsMonoIncr(String S)
	{
		int len = S.length();
		pct = new int[len];
		pct[0] = S.charAt(0) == '1' ? 1 : 0;
		for (int i = 1; i < len; i++)
			pct[i] = S.charAt(i) == '1' ? pct[i - 1] + 1 : pct[i - 1];
		int min = Math.min(pct[len - 1], len - pct[len - 1]);
		for (int i = 0; i < len - 1; i++)
		{
			int t = pct[i] + (len - i - 1 - (pct[len - 1] - pct[i]));
			if (min > t)
				min = t;
		}
		return min;
	}
}

//930. Binary Subarrays With Sum
//Runtime: 25 ms, faster than 49.47% of Java online submissions for Binary Subarrays With Sum.
//Memory Usage: 43.2 MB, less than 5.66% of Java online submissions for Binary Subarrays With Sum.
class Solution930
{
	public int numSubarraysWithSum(int[] A, int S)
	{
		if (S==0)
		{
			int tot=0,i=0,len=A.length;
			while (i<len)
			{
				if (A[i]==1)
				{
					i++;
				}
				else
				{
					int j=i+1;
					while(j<len && A[j]==0) j++;
					tot+=(1+j-i)*(j-i)/2;
					i=j;
				}
			}
			return tot;
		}
		int sum=0,len=A.length;
		HashMap<Integer,Integer>h=new HashMap<Integer,Integer>();
		for (int i=0;i<len;i++)
		{
			sum+=A[i];
			h.put(sum,h.getOrDefault(sum,0)+1);
		}
		
		HashSet<Integer> used=new HashSet<Integer>();
		int tot=h.getOrDefault(S, 0);
		sum=0;
		for (int i=0;i<len;i++)
		{
			sum+=A[i];
			if (!used.contains(sum))
			{
				tot+=h.getOrDefault(sum, 0)*h.getOrDefault(sum+S, 0);
				used.add(sum);
			}
		}
		return tot;
	}
}

public class LC921_930
{
	public static void main(String[] agrs)
	{

	}
}
