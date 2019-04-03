package lc951_960;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bbst.BBST;
import treeCodec.TreeNode;

//951. Flip Equivalent Binary Trees
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Flip Equivalent Binary Trees.
//Memory Usage: 36.7 MB, less than 94.23% of Java online submissions for Flip Equivalent Binary Trees.
class Solution951
{
	public boolean flipEquiv(TreeNode root1, TreeNode root2)
	{
		if (root1 == null)
			return root2 == null;
		if (root2 == null)
			return false;
		if (root1.val != root2.val)
			return false;
		if (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
			return true;
		return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
	}
}

//Runtime: 110 ms, faster than 28.38% of Java online submissions for Array of Doubled Pairs.
//Memory Usage: 53.8 MB, less than 6.58% of Java online submissions for Array of Doubled Pairs.
class Solution954
{
	public boolean canReorderDoubled(int[] A)
	{
		if (A == null || A.length == 0)
			return true;
		int len = A.length;
		BBST<Integer> root = new BBST<Integer>(A[0]);
		for (int i = 1; i < len; i++)
			root = root.insert(A[i]);
		for (int i = 0; i < len / 2; i++)
		{
			int m = root.getMinData();
			root = root.removeMin();
			if ((m < 0 && m % 2 == 0 && root.containData(m / 2)) || (m >= 0 && root.containData(2 * m)))
				root = root.removeNodeByData(m < 0 ? m / 2 : m * 2);
			else
				return false;
		}
		return true;
	}
}

//955. Delete Columns to Make Sorted II
//Runtime: 5 ms, faster than 91.97% of Java online submissions for Delete Columns to Make Sorted II.
//Memory Usage: 38.5 MB, less than 45.95% of Java online submissions for Delete Columns to Make Sorted II.
class Solution955
{
	public int minDeletionSize(String[] A)
	{
		int len = A.length, c = A[0].length(), ct = 0;
		boolean[] del = new boolean[c];
		String[] cache = new String[len];
		for (int i = 0; i < len; i++)
			cache[i] = "";
		for (int j = 0; j < c; j++)
		{
			for (int i = 0; i < len - 1; i++)
			{
				if (cache[i].compareTo(cache[i + 1]) == 0 && A[i].charAt(j) > A[i + 1].charAt(j))
				{
					del[j] = true;
					break;
				}
			}
			if (!del[j])
				for (int i = 0; i < len; i++)
					cache[i] = cache[i] + A[i].charAt(j);
		}
		for (int j = 0; j < c; j++)
			if (del[j])
				ct++;
		return ct;
	}
}

//957. Prison Cells After N Days
//Runtime: 2 ms, faster than 94.89% of Java online submissions for Prison Cells After N Days.
//Memory Usage: 37.7 MB, less than 89.04% of Java online submissions for Prison Cells After N Days.
class Solution957
{
	private int toState(int[] c)
	{
		return c[0] + (c[1] << 1) + (c[2] << 2) + (c[3] << 3) + (c[4] << 4) + (c[5] << 5) + (c[6] << 6) + (c[7] << 7);
	}

	private int[] tmp = new int[8];

	private void evolve(int[] c)
	{
		tmp[0] = 0;
		tmp[7] = 0;
		for (int i = 1; i <= 6; i++)
		{
			if (c[i - 1] == c[i + 1])
				tmp[i] = 1;
			else
				tmp[i] = 0;
		}
		for (int i = 0; i < 8; i++)
			c[i] = tmp[i];
	}

	public int[] prisonAfterNDays(int[] cells, int N)
	{
		HashMap<Integer, Integer> last = new HashMap<Integer, Integer>();
		last.put(toState(cells), 0);
		int day = 0;
		while (true)
		{
			if (day == N)
				return cells;
			day++;
			evolve(cells);
			int s = toState(cells);
			if (last.containsKey(s))
			{
				int period = day - last.get(s);
				int rem = (N - day) % period;
				for (int k = 1; k <= rem; k++)
					evolve(cells);
				return cells;
			} else
			{
				last.put(s, day);
			}
		}
	}
}

//958. Check Completeness of a Binary Tree
//Runtime: 1 ms, faster than 99.19% of Java online submissions for Check Completeness of a Binary Tree.
//Memory Usage: 37.5 MB, less than 14.86% of Java online submissions for Check Completeness of a Binary Tree.
class Solution958
{
	public boolean isCompleteTree(TreeNode root)
	{
		if (root == null || (root.left == null && root.right == null))
			return true;
		int next = 1;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Queue<Integer> ind = new LinkedList<Integer>();
		q.add(root);
		ind.add(1);
		while (!q.isEmpty())
		{
			TreeNode tn = q.remove();
			int i = ind.remove();
			if (i != next)
				return false;
			next++;
			if (tn.left != null)
			{
				q.add(tn.left);
				ind.add(i * 2);
				if (tn.right != null)
				{
					q.add(tn.right);
					ind.add(i * 2 + 1);
				}
			} else if (tn.right != null)
				return false;
		}
		return true;
	}
}

//959. Regions Cut By Slashes
//Rk: divibe each grid by 4 part:
//      up=0
// left=1     right=3
//		down=2

//Runtime: 26 ms, faster than 18.18% of Java online submissions for Regions Cut By Slashes.
//Memory Usage: 38.6 MB, less than 29.03% of Java online submissions for Regions Cut By Slashes.
class Solution959
{
	private int N,ct;
	private List<List<Integer>> graph;

	private int hashInd(int r, int c, int k)
	{
		return r * N * 4 + c * 4 + k;
	}

	private void addLink(int r1, int c1, int k1, int r2, int c2, int k2)
	{
		graph.get(hashInd(r1, c1, k1)).add(hashInd(r2, c2, k2));
		graph.get(hashInd(r2, c2, k2)).add(hashInd(r1, c1, k1));
	}

	private void establishGraph(String[] g)
	{
		N = g.length;
		graph = new ArrayList<List<Integer>>();
		for (int i = 0; i < N * N * 4; i++)
			graph.add(new ArrayList<Integer>());
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (i > 0)
					addLink(i, j, 0, i - 1, j, 2);
				if (i < N - 1)
					addLink(i, j, 2, i + 1, j, 0);
				if (j > 0)
					addLink(i, j, 1, i, j - 1, 3);
				if (j < N - 1)
					addLink(i, j, 3, i, j + 1, 1);
				if (g[i].charAt(j) == ' ')
				{
					addLink(i, j, 0, i, j, 1);
					addLink(i, j, 0, i, j, 2);
					addLink(i, j, 1, i, j, 3);
					addLink(i, j, 2, i, j, 3);
				}
				else if (g[i].charAt(j)=='/')
				{
					addLink(i, j, 0, i, j, 1);
					addLink(i, j, 2, i, j, 3);
				}
				else // '\'
				{
					addLink(i, j, 0, i, j, 3);
					addLink(i, j, 2, i, j, 1);
				}
			}
		}
	}
	
	private void color()
	{
		ct=0;
		int M=N*N*4;
		int[] clr=new int[M];
		Arrays.fill(clr, -1);
		for (int i=0;i<M;i++)
			if (clr[i]==-1)
			{
				clr[i]=ct;
				Queue<Integer> q=new LinkedList<Integer>();
				q.add(i);
				while (!q.isEmpty())
				{
					int h=q.remove();
					for (int next:graph.get(h))
						if (clr[next]==-1)
						{
							clr[next]=ct;
							q.add(next);
						}
				}
				ct++;
			}
	}
	public int regionsBySlashes(String[] grid)
	{
		establishGraph(grid);
		color();
		return ct;
	}
}

public class LC951_960
{
	public static void test955()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input955.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output955.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] strs = test.Test.parseStrArr(inLine);

				Solution955 s = new Solution955();

				int ans = s.minDeletionSize(strs);
				System.out.println(ans);

				bfw.write("" + ans);
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
		test955();
	}
}
