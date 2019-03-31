package lc801_810;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//801. Minimum Swaps To Make Sequences Increasing
//Runtime: 2 ms, faster than 97.24% of Java online submissions for Minimum Swaps To Make Sequences Increasing.
//Memory Usage: 39.1 MB, less than 94.55% of Java online submissions for Minimum Swaps To Make Sequences Increasing.
class Solution801
{
	public int minSwap(int[] A, int[] B)
	{
		int min1 = 0, min2 = 1;
		for (int i = 1; i < A.length; i++)
		{
			int nmin1 = Integer.MAX_VALUE, nmin2 = Integer.MAX_VALUE;
			if (A[i] > A[i - 1] && B[i] > B[i - 1])
			{
				nmin1 = Math.min(min1, nmin1);
				nmin2 = Math.min(min2 + 1, nmin2);
			}
			if (A[i] > B[i - 1] && B[i] > A[i - 1])
			{
				nmin1 = Math.min(min2, nmin1);
				nmin2 = Math.min(min1 + 1, nmin2);
			}
			min1 = nmin1;
			min2 = nmin2;
		}
		return Math.min(min1, min2);
	}
}

//802. Find Eventual Safe States
//Runtime: 32 ms, faster than 41.45% of Java online submissions for Find Eventual Safe States.
//Memory Usage: 66.7 MB, less than 36.79% of Java online submissions for Find Eventual Safe States.
class Solution802
{
	public List<Integer> eventualSafeNodes(int[][] graph)
	{
		int len = graph.length;
		List<List<Integer>> father = new ArrayList<List<Integer>>();
		for (int i = 0; i < len; i++)
			father.add(new ArrayList<Integer>());
		for (int i = 0; i < len; i++)
			for (int j = 0; j < graph[i].length; j++)
				father.get(graph[i][j]).add(i);
		List<Integer> q = new ArrayList<Integer>();
		boolean[] used = new boolean[len];
		int[] outD = new int[len];
		for (int i = 0; i < len; i++)
			if ((outD[i] = graph[i].length) == 0)
			{
				used[i] = true;
				q.add(i);
			}
		int f = 0;
		while (f < q.size())
		{
			int nt = q.get(f++);
			for (int pre : father.get(nt))
			{
				if (!used[pre])
				{
					outD[pre]--;
					if (outD[pre] == 0)
					{
						q.add(pre);
						used[pre] = true;
					}
				}
			}
		}
		Collections.sort(q);
		return q;
	}
}

//807. Max Increase to Keep City Skyline
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Max Increase to Keep City Skyline.
//Memory Usage: 42.8 MB, less than 26.74% of Java online submissions for Max Increase to Keep City Skyline.
class Solution807
{
	public int maxIncreaseKeepingSkyline(int[][] grid)
	{
		int len = grid.length;
		int[] tdh = new int[len];
		int[] lrh = new int[len];
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
			{
				if (grid[i][j] > tdh[j])
					tdh[j] = grid[i][j];
				if (grid[i][j] > lrh[i])
					lrh[i] = grid[i][j];
			}
		int tot = 0;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				tot += Math.min(tdh[j], lrh[i]) - grid[i][j];
		return tot;
	}
}

public class LC801_810
{
	public static void test802()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input802.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output802.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g = test.Test.parse2DIntArr(inLine);

				Solution802 s = new Solution802();

				List<Integer> ans = s.eventualSafeNodes(g);

				System.out.println(ans);
				bfw.write(ans.toString());
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
		test802();
	}
}
