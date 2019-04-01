package lc881_890;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import treeCodec.*;

//881. Boats to Save People
//Runtime: 21 ms, faster than 92.22% of Java online submissions for Boats to Save People.
//Memory Usage: 53 MB, less than 16.48% of Java online submissions for Boats to Save People.
class Solution881
{
	public int numRescueBoats(int[] people, int limit)
	{
		Arrays.parallelSort(people);
		int s = 0, l = 0, r = people.length - 1;
		while (l <= r)
		{
			if (l == r)
			{
				s++;
				l++;
			} else
			{
				if (people[l] + people[r] <= limit)
				{
					l++;
					r--;
					s++;
				} else
				{
					r--;
					s++;
				}
			}
		}
		return s;
	}
}

//885. Spiral Matrix III
//Runtime: 3 ms, faster than 99.48% of Java online submissions for Spiral Matrix III.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Spiral Matrix III.
class Solution885
{
	private final static int[][] di = new int[][]
	{
			{ 0, 1 },
			{ 1, 0 },
			{ 0, -1 },
			{ -1, 0 } };

	public int[][] spiralMatrixIII(int R, int C, int r0, int c0)
	{
		int totlen = R * C;
		int[][] ans = new int[totlen][2];
		ans[0][0] = r0;
		ans[0][1] = c0;
		int wp = 1, len = 1, dirt = 0, ct = 0;
		while (wp < totlen)
		{
			r0 += di[dirt][0];
			c0 += di[dirt][1];
			ct++;
			if (r0 >= 0 && c0 >= 0 && r0 < R && c0 < C)
			{
				ans[wp][0] = r0;
				ans[wp][1] = c0;
				wp++;
			}
			if (ct == len)
			{
				dirt = (dirt + 1) % 4;
				if (dirt == 2 || dirt == 0)
					len++;
				ct = 0;
			}
		}
		return ans;
	}
}

//886. Possible Bipartition
//Runtime: 19 ms, faster than 88.86% of Java online submissions for Possible Bipartition.
//Memory Usage: 58.7 MB, less than 71.74% of Java online submissions for Possible Bipartition.
class Solution886
{
	private int[] pos;
	private List<List<Integer>> dl;

	private boolean bfs(int st)
	{
		pos[st] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(st);
		while (!q.isEmpty())
		{
			int h = q.remove();
			for (int d : dl.get(h))
			{
				if (pos[d] == -1)
				{
					q.add(d);
					pos[d] = 1 - pos[h];
				} else if (pos[d] == pos[h])
					return false;
			}
		}
		return true;
	}

	public boolean possibleBipartition(int N, int[][] dislikes)
	{
		pos = new int[N];
		Arrays.fill(pos, -1);
		dl = new ArrayList<List<Integer>>(N);
		for (int i = 0; i < N; i++)
			dl.add(new ArrayList<Integer>());
		for (int[] r : dislikes)
		{
			dl.get(r[0] - 1).add(r[1] - 1);
			dl.get(r[1] - 1).add(r[0] - 1);
		}
		for (int i = 0; i < N; i++)
			if (pos[i] == -1 && !bfs(i))
				return false;
		return true;
	}
}

//889. Construct Binary Tree from Preorder and Postorder Traversal
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
//Memory Usage: 38.9 MB, less than 96.58% of Java online submissions for Construct Binary Tree from Preorder and Postorder Traversal.
class Solution889
{
	private int indexOf(int[] a, int s, int e, int t)
	{
		for (int i = s; i <= e; i++)
			if (a[i] == t)
				return i;
		return -1;
	}

	private TreeNode con(int[] pre, int s1, int e1, int[] post, int s2, int e2)
	{
		TreeNode r = new TreeNode(pre[s1]);
		if (s1 == e1)
			return r;
		int k = pre[s1 + 1];
		if (k == post[e2 - 1])
		{
			r.left = con(pre, s1 + 1, e1, post, s2, e2 - 1);
			return r;
		}
		int ind = indexOf(post, s2, e2 - 1, k);
		int leftlen = ind - s2 + 1;
		r.left = con(pre, s1 + 1, s1 + leftlen, post, s2, s2 + leftlen - 1);
		r.right = con(pre, s1 + leftlen + 1, e1, post, s2 + leftlen, e2 - 1);
		return r;
	}

	public TreeNode constructFromPrePost(int[] pre, int[] post)
	{
		return con(pre, 0, pre.length - 1, post, 0, post.length - 1);
	}
}

//890. Find and Replace Pattern
//Runtime: 2 ms, faster than 98.88% of Java online submissions for Find and Replace Pattern.
//Memory Usage: 37.4 MB, less than 62.09% of Java online submissions for Find and Replace Pattern.
class Solution890
{
	public List<String> findAndReplacePattern(String[] words, String pattern)
	{
		List<String> ans = new ArrayList<String>();
		int len = pattern.length();
		for (String w : words)
		{
			HashMap<Character, Character> rule = new HashMap<Character, Character>();
			HashSet<Character> used = new HashSet<Character>();
			// Array can replace the hash map.
			boolean match = true;
			for (int i = 0; i < len; i++)
			{
				if (!rule.containsKey(pattern.charAt(i)))
				{
					if (used.contains(w.charAt(i)))
					{
						match = false;
						break;
					}
					rule.put(pattern.charAt(i), w.charAt(i));
					used.add(w.charAt(i));
				} else
				{
					if (rule.get(pattern.charAt(i)) != w.charAt(i))
					{
						match = false;
						break;
					}
				}
			}
			if (match)
				ans.add(w);
		}
		return ans;
	}
}

public class LC881_890
{
	public static void test889()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input889.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output889.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] pre = test.Test.parseIntArr(inLine);
				int[] post = test.Test.parseIntArr(bfr.readLine());

				Solution889 s = new Solution889();

				TreeNode rt = s.constructFromPrePost(pre, post);

				String sstr = TreeCodec.serialize(rt);

				System.out.println(sstr);

				bfw.write(sstr);
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
		test889();
	}
}
