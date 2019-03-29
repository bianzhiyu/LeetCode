package lc651_660;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import treeCodec.*;

//652. Find Duplicate Subtrees
//TLE
class Solution652
{
	private List<TreeNode> cand = new ArrayList<TreeNode>();
	private List<TreeNode> ans = new ArrayList<TreeNode>();

	private boolean same(TreeNode r1, TreeNode r2)
	{
		if (r1 == null && r2 == null)
			return true;
		if (r1 == null)
			return r2 == null;
		if (r2 == null)
			return false;
		if (r1.val != r2.val)
			return false;
		return same(r1.left, r2.left) && same(r1.right, r2.right);
	}

	private boolean dupIn(List<TreeNode> tocheck, TreeNode rt)
	{
		for (int i = 0; i < tocheck.size(); i++)
			if (same(tocheck.get(i), rt))
				return true;
		return false;
	}

	private void add(TreeNode rt)
	{
		if (rt == null)
			return;
		if (!dupIn(cand, rt))
			cand.add(rt);
		else if (!dupIn(ans, rt))
			ans.add(rt);
		add(rt.left);
		add(rt.right);
	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root)
	{
		if (root == null)
			return ans;
		add(root.left);
		add(root.right);
		return ans;
	}
}

//Runtime: 16 ms, faster than 87.39% of Java online submissions for Find Duplicate Subtrees.
//Memory Usage: 41.7 MB, less than 95.22% of Java online submissions for Find Duplicate Subtrees.
class Solution652_2
{
	private HashMap<TreeNode, TreeNode> represent = new HashMap<TreeNode, TreeNode>();
	private HashMap<Integer, List<TreeNode>> treeInLayer = new HashMap<Integer, List<TreeNode>>();
	private Set<TreeNode> cand = new HashSet<TreeNode>();
	private List<TreeNode> ans = new ArrayList<TreeNode>();

	private boolean same(TreeNode r1, TreeNode r2)
	{
		if (r1 == null && r2 == null)
			return true;
		if (r1 == null)
			return r2 == null;
		if (r2 == null)
			return false;
		if (r1.val != r2.val)
			return false;
		return same(r1.left, r2.left) && same(r1.right, r2.right);
	}

	private int setLayer(TreeNode rt)
	{
		if (rt == null)
			return 0;
		int leftHeight = setLayer(rt.left);
		int rightHeight = setLayer(rt.right);
		int H = 0;
		if (leftHeight > rightHeight)
			H = leftHeight;
		else
			H = rightHeight;
		H++;
		if (!treeInLayer.containsKey(H))
			treeInLayer.put(H, new ArrayList<TreeNode>());
		treeInLayer.get(H).add(rt);
		return H;
	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root)
	{
		setLayer(root);
		for (int h : treeInLayer.keySet())
		{
			List<TreeNode> eachLayer = treeInLayer.get(h);
			for (int i = 0; i < eachLayer.size(); i++)
			{
				TreeNode sameclass = null;
				for (int j = 0; j < i; j++)
					if (same(eachLayer.get(j), eachLayer.get(i)))
					{
						sameclass = eachLayer.get(j);
						break;
					}
				if (sameclass == null)
					represent.put(eachLayer.get(i), null);
				else
				{
					while (represent.get(sameclass) != null)
						sameclass = represent.get(sameclass);
					if (!cand.contains(sameclass))
						cand.add(sameclass);
					represent.put(eachLayer.get(i), sameclass);
				}
			}
		}
		ans.addAll(cand);
		return ans;
	}
}

//Runtime: 23 ms, faster than 40.87% of Java online submissions for Find Duplicate Subtrees.
//Memory Usage: 41.1 MB, less than 95.65% of Java online submissions for Find Duplicate Subtrees.
class Solution652_3
{
	private HashMap<TreeNode, TreeNode> represent = new HashMap<TreeNode, TreeNode>();
	private HashMap<Integer, List<TreeNode>> treeInLayer = new HashMap<Integer, List<TreeNode>>();
	private Set<TreeNode> cand = new HashSet<TreeNode>();
	private List<TreeNode> ans = new ArrayList<TreeNode>();

	private boolean same(TreeNode r1, TreeNode r2)
	{
		if (r1.val != r2.val)
			return false;
		if (r1.left == null)
		{
			if (r2.left != null)
				return false;
		} else
		{
			if (r2.left == null)
				return false;
			if (represent.get(r1.left) == null && represent.get(r2.left) == null)
				return r1.left == r2.left;
			if (represent.get(r1.left) != represent.get(r2.left) && represent.get(r1.left) != r2.left
					&& represent.get(r2.left) != r1.left)
				return false;
		}
		if (r1.right == null)
			return r2.right == null;
		if (r2.right == null)
			return false;
		if (represent.get(r1.right) == null && represent.get(r2.right) == null)
			return r1.right == r2.right;
		if (represent.get(r1.right) != represent.get(r2.right) && represent.get(r1.right) != r2.right
				&& represent.get(r2.right) != r1.right)
			return false;
		return true;
	}

	private int setLayer(TreeNode rt)
	{
		if (rt == null)
			return 0;
		int leftHeight = setLayer(rt.left);
		int rightHeight = setLayer(rt.right);
		int H = 0;
		if (leftHeight > rightHeight)
			H = leftHeight;
		else
			H = rightHeight;
		H++;
		if (!treeInLayer.containsKey(H))
			treeInLayer.put(H, new ArrayList<TreeNode>());
		treeInLayer.get(H).add(rt);
		return H;
	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root)
	{
		setLayer(root);
		List<Integer> allHeight = new ArrayList<Integer>();
		allHeight.addAll(treeInLayer.keySet());
		Collections.sort(allHeight);
		for (int h : allHeight)
		{
			List<TreeNode> eachLayer = treeInLayer.get(h);
			for (int i = 0; i < eachLayer.size(); i++)
			{
				TreeNode sameclass = null;
				for (int j = 0; j < i; j++)
					if (same(eachLayer.get(j), eachLayer.get(i)))
					{
						sameclass = eachLayer.get(j);
						if (eachLayer.get(i).val == -31)
						{
							;
						}
						break;
					}
				if (sameclass == null)
					represent.put(eachLayer.get(i), null);
				else
				{
					while (represent.get(sameclass) != null)
						sameclass = represent.get(sameclass);
					if (!cand.contains(sameclass))
						cand.add(sameclass);
					represent.put(eachLayer.get(i), sameclass);
				}
			}
		}
		ans.addAll(cand);
		return ans;
	}
}

//654. Maximum Binary Tree
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Maximum Binary Tree.
//Memory Usage: 38.8 MB, less than 91.48% of Java online submissions for Maximum Binary Tree.
class Solution654
{
	TreeNode Cons(int[] a, int l, int r)
	{
		if (l > r)
			return null;
		int mi = l;
		for (int i = l + 1; i <= r; i++)
			if (a[i] > a[mi])
				mi = i;
		TreeNode rt = new TreeNode(a[mi]);
		rt.left = Cons(a, l, mi - 1);
		rt.right = Cons(a, mi + 1, r);
		return rt;
	}

	public TreeNode constructMaximumBinaryTree(int[] nums)
	{
		return Cons(nums, 0, nums.length - 1);
	}
}

public class LC651_660
{
	public static void test652()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input652.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output652.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				TreeNode rt = TreeCodec.deserialize(inLine);

				Solution652_3 s = new Solution652_3();

				List<TreeNode> ans = s.findDuplicateSubtrees(rt);

				for (TreeNode t : ans)
				{
					String out = TreeCodec.serialize(t);
					bfw.write(out);
					bfw.newLine();
					System.out.println(out);
				}
				System.out.println();
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
		test652();
	}
}
