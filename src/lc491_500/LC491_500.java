package lc491_500;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//491. Increasing Subsequences
//Runtime: 933 ms, faster than 6.31% of Java online submissions for Increasing Subsequences.
//Memory Usage: 53.8 MB, less than 61.82% of Java online submissions for Increasing Subsequences.
class Solution491
{
	private int[] stack, n;
	private List<List<Integer>> ans;

	public List<List<Integer>> findSubsequences(int[] nums)
	{
		n = nums;
		stack = new int[nums.length];
		ans = new ArrayList<List<Integer>>();
		dfs(0, 0, -200);
		return ans;
	}

	private void dfs(int sp, int startInd, int min)
	{
		if (sp >= 2)
		{
			boolean found = false;
			for (int i = 0; i < ans.size(); i++)
			{
				List<Integer> exi = ans.get(i);
				if (exi.size() != sp)
					continue;
				found = true;
				for (int j = 0; j < sp; j++)
					if (exi.get(j) != n[stack[j]])
					{
						found = false;
						break;
					}
				if (found)
					break;
			}
			if (!found)
			{
				List<Integer> tmp = new ArrayList<Integer>();
				for (int i = 0; i < sp; i++)
					tmp.add(n[stack[i]]);
				ans.add(tmp);
			}
		}
		for (int i = startInd; i < n.length; i++)
		{
			if (n[i] < min)
				continue;
			stack[sp] = i;
			dfs(sp + 1, i + 1, n[i]);
		}
	}
}

//https://leetcode.com/problems/increasing-subsequences/discuss/238086/Java-7ms-solution-which-beats-99.8

class Solution491_2
{
	public List<List<Integer>> findSubsequences(int[] nums)
	{
		List<List<Integer>> res = new LinkedList<>();
		helper(new LinkedList<Integer>(), 0, nums, res);
		return res;
	}

	private void helper(LinkedList<Integer> list, int index, int[] nums, List<List<Integer>> res)
	{
		if (list.size() > 1)
			res.add(new LinkedList<Integer>(list));
		Set<Integer> used = new HashSet<>();
		for (int i = index; i < nums.length; i++)
		{
			if (used.contains(nums[i]))
				continue;
			if (list.size() == 0 || nums[i] >= list.peekLast())
			{
				used.add(nums[i]);
				list.add(nums[i]);
				helper(list, i + 1, nums, res);
				list.remove(list.size() - 1);
			}
		}
	}
}

//495. Teemo Attacking
//Runtime: 4 ms, faster than 97.51% of Java online submissions for Teemo Attacking.
//Memory Usage: 40 MB, less than 91.67% of Java online submissions for Teemo Attacking.
class Solution495
{
	public int findPoisonedDuration(int[] timeSeries, int duration)
	{
		int tottimespan = 0, lastpoisonedtime, laststarttime;
		if (timeSeries.length == 0)
			return 0;
		lastpoisonedtime = timeSeries[0] + duration;
		laststarttime = timeSeries[0];
		for (int i = 1; i < timeSeries.length; i++)
		{
			if (timeSeries[i] <= lastpoisonedtime)
			{
				lastpoisonedtime = Math.max(lastpoisonedtime, timeSeries[i] + duration);
			} else
			{
				tottimespan += lastpoisonedtime - laststarttime;
				laststarttime = timeSeries[i];
				lastpoisonedtime = timeSeries[i] + duration;
			}
		}
		tottimespan += lastpoisonedtime - laststarttime;
		return tottimespan;
	}
}

//498. Diagonal Traverse
//Runtime: 4 ms, faster than 68.72% of Java online submissions for Diagonal Traverse.
//Memory Usage: 46.6 MB, less than 59.59% of Java online submissions for Diagonal Traverse.
class Solution498
{
	public int[] findDiagonalOrder(int[][] matrix)
	{
		if (matrix.length == 0 || matrix[0].length == 0)
			return new int[0];
		int m = matrix.length, n = matrix[0].length;
		int[] ans = new int[m * n];

		int x = 0, y = 0;
		int dx = -1, dy = 1;
		int sp = 0;
		for (sp = 0; sp < m * n; sp++)
		{
			ans[sp] = matrix[x][y];
			if (x == 0 && dx == -1)
			{
				if (y < n - 1)
				{
					y++;
					dx = -dx;
					dy = -dy;
				} else
				{
					x++;
					dx = -dx;
					dy = -dy;
				}
			} else if (dx == -1 && y == n - 1)
			{
				x++;
				dx = -dx;
				dy = -dy;
			} else if (dy == -1 && y == 0)
			{
				if (x < m - 1)
				{
					x++;
					dx = -dx;
					dy = -dy;
				} else
				{
					y++;
					dx = -dx;
					dy = -dy;
				}
			} else if (dy == -1 && x == m - 1)
			{
				y++;
				dx = -dx;
				dy = -dy;
			} else
			{
				x += dx;
				y += dy;
			}
		}

		return ans;
	}
}

public class LC491_500
{
	public static void test498()
	{
		int[][] a = new int[][]
		{
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 } };

		Solution498 s = new Solution498();
		test.Test.dispArr(s.findDiagonalOrder(a));
	}

	public static void test491()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input491.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output491.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] data = inLine.substring(1, inLine.length() - 1).split(",");
				int[] nums = new int[data.length];
				for (int i = 0; i < data.length; i++)
					nums[i] = Integer.parseInt(data[i].trim());

				Solution491 s = new Solution491();
				List<List<Integer>> ans = s.findSubsequences(nums);

				bfw.write(ans.toString());
				bfw.newLine();
				bfw.newLine();

				System.out.println(ans.toString());
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void main(String[] agrs)
	{
		test491();
	}

}
