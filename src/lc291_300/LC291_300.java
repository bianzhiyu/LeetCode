package lc291_300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import treeCodec.*;
import heap.Heap;

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

//295. Find Median from Data Stream
//Runtime: 527 ms, faster than 5.04% of Java online submissions for Find Median from Data Stream.
//Memory Usage: 70.3 MB, less than 5.01% of Java online submissions for Find Median from Data Stream.
class MedianFinder
{
	List<Integer> l = new ArrayList<Integer>();
	boolean sorted = false;

	/** initialize your data structure here. */
	public MedianFinder()
	{

	}

	public void addNum(int num)
	{
		l.add(num);
		sorted = false;
	}

	public double findMedian()
	{
		if (!sorted)
		{
			Collections.sort(l);
			sorted = true;
		}
		if (l.size() % 2 == 1)
			return l.get(l.size() / 2);
		return (0.0 + l.get(l.size() / 2 - 1) + l.get(l.size() / 2)) / 2.0;
	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
 * obj.findMedian();
 */

//Refer and rewrite.
//Runtime: 114 ms, faster than 84.93% of Java online submissions for Find Median from Data Stream.
//Memory Usage: 67.6 MB, less than 19.64% of Java online submissions for Find Median from Data Stream.
class MedianFinder_2
{
	PriorityQueue<Integer> largerPart,smallerPart;
	int size=0;

	/** initialize your data structure here. */
	public MedianFinder_2()
	{
		largerPart=new PriorityQueue<Integer>();
		smallerPart=new PriorityQueue<Integer>(
				new Comparator<Integer>()
				{
					public int compare(Integer x,Integer y)
					{
						return y-x;
					}
				});
	}

	public void addNum(int num)
	{
		if (size==0)
		{
			smallerPart.offer(num);
		}
		else if (smallerPart.size()>largerPart.size())
		{
			if (num>=smallerPart.peek())
				largerPart.offer(num);
			else
			{
				largerPart.offer(smallerPart.poll());
				smallerPart.offer(num);
			}
		}
		else 
		{
			if (num<=largerPart.peek())
				smallerPart.add(num);
			else 
			{
				smallerPart.offer(largerPart.poll());
				largerPart.offer(num);
			}
		}
		size++;
	}

	public double findMedian()
	{
		if (size%2==0) return (smallerPart.peek()+largerPart.peek())/2.0;
		return smallerPart.peek();
	}
}

//test Heap
//Runtime: 129 ms, faster than 56.31% of Java online submissions for Find Median from Data Stream.
//Memory Usage: 68.9 MB, less than 8.02% of Java online submissions for Find Median from Data Stream.
class MedianFinder_3
{
	Heap<Integer> largerPart,smallerPart;
	int size=0;

	/** initialize your data structure here. */
	public MedianFinder_3()
	{
		largerPart=new Heap<Integer>();
		smallerPart=new Heap<Integer>(
				new Comparator<Integer>()
				{
					public int compare(Integer x,Integer y)
					{
						return y-x;
					}
				});
	}

	public void addNum(int num)
	{
		if (size==0)
		{
			smallerPart.offer(num);
		}
		else if (smallerPart.size()>largerPart.size())
		{
			if (num>=smallerPart.peek())
				largerPart.offer(num);
			else
			{
				largerPart.offer(smallerPart.poll());
				smallerPart.offer(num);
			}
		}
		else 
		{
			if (num<=largerPart.peek())
				smallerPart.offer(num);
			else 
			{
				smallerPart.offer(largerPart.poll());
				largerPart.offer(num);
			}
		}
		size++;
	}

	public double findMedian()
	{
		if (size%2==0) return (smallerPart.peek()+largerPart.peek())/2.0;
		return smallerPart.peek();
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
		int len = secret.length();
		boolean[] bulls = new boolean[len];
		int bullsnum = 0;
		for (int i = 0; i < len; i++)
			if (secret.charAt(i) == guess.charAt(i))
			{
				bullsnum++;
				bulls[i] = true;
			} else
				bulls[i] = false;
		int[] secretcount = new int[10], guesscount = new int[10];
		for (int i = 0; i < len; i++)
			if (!bulls[i])
			{
				secretcount[secret.charAt(i) - '0']++;
				guesscount[guess.charAt(i) - '0']++;
			}
		int cowsnum = 0;
		for (int i = 0; i < 10; i++)
			cowsnum += Math.min(secretcount[i], guesscount[i]);
		return bullsnum + "A" + cowsnum + "B";
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
	public static void test295()
	{
		MedianFinder_2 f=new MedianFinder_2();
		f.addNum(1);
		f.addNum(2);
	}
	public static void main(String[] args)
	{
		test295();

	}

}
