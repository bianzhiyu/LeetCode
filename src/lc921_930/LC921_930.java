package lc921_930;

import java.util.Arrays;

//921. Minimum Add to Make Parentheses Valid
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Minimum Add to Make Parentheses Valid.
//Memory Usage: 37.3 MB, less than 7.66% of Java online submissions for Minimum Add to Make Parentheses 
class Solution921
{
	public int minAddToMakeValid(String S)
	{
		int p=0,a=0;
		for (char c:S.toCharArray())
		{
			if (c=='(') p++;
			else 
			{
				if (p>0) p--;
				else a++;
			}
		}
		a+=p;
		return a;
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

public class LC921_930
{
	public static void main(String[] agrs)
	{

	}
}
