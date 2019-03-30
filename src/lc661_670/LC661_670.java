package lc661_670;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import treeCodec.*;

//662. Maximum Width of Binary Tree
//Runtime: 3 ms, faster than 96.27% of Java online submissions for Maximum Width of Binary Tree.
//Memory Usage: 37.5 MB, less than 27.23% of Java online submissions for Maximum Width of Binary Tree.
class Solution662
{
	private HashMap<Integer, Integer> leftMost = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> rightMost = new HashMap<Integer, Integer>();

	private void trav(TreeNode rt, int pos, int layer)
	{
		if (rt == null)
			return;
		if (!leftMost.containsKey(layer))
			leftMost.put(layer, pos);
		if (leftMost.get(layer) > pos)
			leftMost.put(layer, pos);
		if (!rightMost.containsKey(layer))
			rightMost.put(layer, pos);
		if (rightMost.get(layer) < pos)
			rightMost.put(layer, pos);
		trav(rt.left, (pos << 1) + 1, layer + 1);
		trav(rt.right, (pos << 1) + 2, layer + 1);
	}

	public int widthOfBinaryTree(TreeNode root)
	{
		trav(root, 0, 0);
		int max = 0;
		for (int layer : leftMost.keySet())
			max = Math.max(max, rightMost.get(layer) - leftMost.get(layer));
		return max + 1;
	}
}

//670. Maximum Swap
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Swap.
//Memory Usage: 31.9 MB, less than 100.00% of Java online submissions for Maximum Swap.
class Solution670
{
	public int maximumSwap(int num)
	{
		char[] n = Integer.toString(num).toCharArray();
		for (int i = 0; i < n.length; i++)
		{
			int maxMostInd = -1;
			for (int j = i + 1; j < n.length; j++)
				if (n[j] > n[i] && (maxMostInd == -1 || n[j] >= n[maxMostInd]))
					maxMostInd = j;
			if (maxMostInd != -1)
			{
				num = 0;
				char c = n[i];
				n[i] = n[maxMostInd];
				n[maxMostInd] = c;

				for (int j = 0; j < n.length; j++)
					num = num * 10 + (n[j] - '0');
				return num;
			}
		}
		return num;
	}
}

public class LC661_670
{
	public static void test662()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input662.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output662.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				TreeNode rt = TreeCodec.deserialize(inLine);

				Solution662 s = new Solution662();
				int ans = s.widthOfBinaryTree(rt);
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

	public static void test670()
	{
		int n = 9973;
		Solution670 s = new Solution670();
		System.out.println(s.maximumSwap(n));
	}

	public static void main(String[] args)
	{
		test670();
	}
}
