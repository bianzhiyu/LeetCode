package lc291_300;

import java.util.LinkedList;
import java.util.Queue;

import treeCodec.*;

//292. Nim Game
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Nim Game.
//Memory Usage: 34.5 MB, less than 25.56% of Java online submissions for Nim Game.
class Solution292
{
	public boolean canWinNim(int n)
	{
		n = n % 4;
		if (n == 0)
			return false;
		return true;
	}
}

//297. Serialize and Deserialize Binary Tree
//Runtime: 13 ms, faster than 64.26% of Java online submissions for Serialize and Deserialize Binary Tree.
//Memory Usage: 39.8 MB, less than 57.71% of Java online submissions for Serialize and Deserialize Binary Tree.
class Codec
{

	// Encodes a tree to a single string.
	public String serialize(TreeNode root)
	{
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty())
		{
			TreeNode p = q.peek();
			if (p == null)
			{
				sb.append("null,");
			} else
			{
				sb.append(p.val);
				sb.append(',');
				q.add(p.left);
				q.add(p.right);
			}
			q.remove();
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data)
	{
		// if (data=="") return null;
		String[] darr = data.split(",");
		if (darr.length == 0 || darr[0].compareTo("null") == 0)
			return null;
		TreeNode rt = new TreeNode(Integer.parseInt(darr[0]));
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(rt);
		int i = 1;
		while (i < darr.length)
		{
			TreeNode p = q.remove();
			if (darr[i].compareTo("null") == 0)
			{
				i++;
			} else
			{
				p.left = new TreeNode(Integer.parseInt(darr[i]));
				i++;
				q.add(p.left);
			}
			if (i < darr.length)
				if (darr[i].compareTo("null") == 0)
				{
					i++;
				} else
				{
					p.right = new TreeNode(Integer.parseInt(darr[i]));
					i++;
					q.add(p.right);
				}
		}
		return rt;
	}
}


class Codec_2 extends TreeCodec
{

}

//299. Bulls and Cows
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Bulls and Cows.
//Memory Usage: 34.8 MB, less than 98.23% of Java online submissions for Bulls and Cows.
class Solution299
{
	public String getHint(String secret, String guess)
	{
		int len=secret.length();
		boolean[] bulls=new boolean[len];
		int bullsnum=0;
		for (int i=0;i<len;i++)
			if (secret.charAt(i)==guess.charAt(i))
			{
				bullsnum++;
				bulls[i]=true;
			}
			else bulls[i]=false;
		int[] secretcount=new int[10],guesscount=new int[10];
		for (int i=0;i<len;i++)
			if (!bulls[i])
			{
				secretcount[secret.charAt(i)-'0']++;
				guesscount[guess.charAt(i)-'0']++;
			}
		int cowsnum=0;
		for (int i=0;i<10;i++)
			cowsnum+=Math.min(secretcount[i], guesscount[i]);
		return bullsnum+"A"+cowsnum+"B";
	}
}

//300. Longest Increasing Subsequence
//Runtime: 10 ms, faster than 66.93% of Java online submissions for Longest Increasing Subsequence.
//Memory Usage: 35.3 MB, less than 69.93% of Java online submissions for Longest Increasing Subsequence.
class Solution300
{
	public int lengthOfLIS(int[] nums)
	{
		int len = nums.length;
		int[] m = new int[len];
		int max = 0;
		for (int i = len - 1; i >= 0; i--)
		{
			m[i] = 1;
			for (int j = i + 1; j < len; j++)
				if (nums[j] > nums[i] && m[j] + 1 > m[i])
					m[i] = m[j] + 1;
			if (m[i] > max)
				max = m[i];
		}
		return max;
	}
}

public class LC291_300
{
	public static void main(String[] args)
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(null);
		System.out.println(q.isEmpty());

		TreeNode rt = new TreeNode(1);
		rt.left = new TreeNode(2);
		rt.left.right = new TreeNode(3);

		Codec x = new Codec();

		System.out.println(x.serialize(rt));

		TreeNode n = x.deserialize(x.serialize(rt));

		System.out.println(x.serialize(n));

	}

}
