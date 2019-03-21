package lc331_340;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//331. Verify Preorder Serialization of a Binary Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Verify Preorder Serialization of a Binary Tree.
//Memory Usage: 34.5 MB, less than 78.90% of Java online submissions for Verify Preorder Serialization of a Binary Tree.
class Solution331
{
	boolean isNum(char c)
	{
		return c >= '0' && c <= '9';
	}

	int readNum(String str, int start, int end)
	{
		while (start < end && isNum(str.charAt(start)))
			start++;
		return start;
	}

	// start: inclusive, end: exclusive
	int valid(String str, int start, int end)
	{
		if (start >= end)
			return end + 1;
		if (str.charAt(start) == '#')
		{
			return start + 1;
		}
		int tp = readNum(str, start, end);
		if (tp == start)
			return end + 1;
		tp++;// skip ','
		if (tp >= end)
			return end + 1;
		tp = valid(str, tp, end);
		tp++;// skip ','
		tp = valid(str, tp, end);
		return tp;
	}

	public boolean isValidSerialization(String preorder)
	{
		if (preorder == null || preorder.length() == 0)
		{
			return true;
		}
		return valid(preorder, 0, preorder.length()) == preorder.length();
	}
}

//336. Palindrome Pairs
//TLE
class Solution336
{
	public List<List<Integer>> palindromePairs(String[] words)
	{
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		for (int i = 0; i < words.length; i++)
			for (int j = 0; j < words.length; j++)
				if (i != j && ispal(words[i] + words[j]))
				{
					List<Integer> tmp = new ArrayList<Integer>(2);
					tmp.add(i);
					tmp.add(j);
					ans.add(tmp);
				}
		return ans;
	}

	boolean ispal(String s)
	{
		int len = s.length();
		for (int i = 0; i < len / 2; i++)
			if (s.charAt(i) != s.charAt(len - 1 - i))
				return false;
		return true;
	}
}

//Other's
//From leetcode's fast posts
class Solution336_2
{

	private static class TrieNode
	{
		TrieNode[] next;
		int index;
		List<Integer> list;

		TrieNode()
		{
			next = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}

	public List<List<Integer>> palindromePairs(String[] words)
	{
		List<List<Integer>> res = new ArrayList<>();

		TrieNode root = new TrieNode();

		for (int i = 0; i < words.length; i++)
		{
			addWord(root, words[i], i);
		}

		for (int i = 0; i < words.length; i++)
		{
			search(words, i, root, res);
		}

		return res;
	}

	private void addWord(TrieNode root, String word, int index)
	{
		for (int i = word.length() - 1; i >= 0; i--)
		{
			int j = word.charAt(i) - 'a';

			if (root.next[j] == null)
			{
				root.next[j] = new TrieNode();
			}

			if (isPalindrome(word, 0, i))
			{
				root.list.add(index);
			}

			root = root.next[j];
		}

		root.list.add(index);
		root.index = index;
	}

	private void search(String[] words, int i, TrieNode root, List<List<Integer>> res)
	{
		for (int j = 0; j < words[i].length(); j++)
		{
			if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1))
			{
				res.add(Arrays.asList(i, root.index));
			}

			root = root.next[words[i].charAt(j) - 'a'];
			if (root == null)
				return;
		}

		for (int j : root.list)
		{
			if (i == j)
				continue;
			res.add(Arrays.asList(i, j));
		}
	}

	private boolean isPalindrome(String word, int i, int j)
	{
		while (i < j)
		{
			if (word.charAt(i++) != word.charAt(j--))
				return false;
		}

		return true;
	}
}

//learn from 336_2
//Runtime: 31 ms, faster than 88.47% of Java online submissions for Palindrome Pairs.
//Memory Usage: 44.3 MB, less than 82.42% of Java online submissions for Palindrome Pairs.
class Solution336_3
{
	private static class TrieNode
	{
		TrieNode[] son = new TrieNode[26];
		int index = -1;	// -1: to here is not a word; >=0: is a word, refer to its index
		List<Integer> list = new ArrayList<Integer>();
	}

	public List<List<Integer>> palindromePairs(String[] words)
	{
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		TrieNode revTreeRt = new TrieNode();
		for (int i=0;i<words.length;i++)
		{
			TrieNode tn=revTreeRt;
			for (int j=words[i].length()-1;j>=0;j--)
			{
				if (isPld(words,i,0,j)) tn.list.add(i);
				int c=words[i].charAt(j)-'a';
				if (tn.son[c]==null) tn.son[c]=new TrieNode();
				tn=tn.son[c];	
			}
			tn.list.add(i);
			tn.index=i;
		}

		for (int i = 0; i < words.length; i++)
		{
			TrieNode tn = revTreeRt;
			boolean getEnd=true;
			for (int j = 0; j < words[i].length(); j++)
			{
				if (tn.index >= 0 && tn.index != i && isPld(words, i, j, words[i].length() - 1))
					ans.add(Arrays.asList(i, tn.index));
				int c = words[i].charAt(j) - 'a';
				if (tn.son[c] == null)
				{
					getEnd=false;
					break;
				}
				tn = tn.son[c];
			}
			if (!getEnd) continue;
			for (int j = 0; j < tn.list.size(); j++)
				if (i != tn.list.get(j))
					ans.add(Arrays.asList(i, tn.list.get(j)));
		}
		return ans;
	}

	private boolean isPld(String[] w, int sind, int stpos, int endpos)
	{
		while (stpos<endpos)
		{
			if (w[sind].charAt(stpos)!=w[sind].charAt(endpos))
				return false;
			stpos++;
			endpos--;
		}
		return true;
	}
}

//338. Counting Bits
//Runtime: 1 ms, faster than 99.97% of Java online submissions for Counting Bits.
//Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Counting Bits.
class Solution338
{
	public int[] countBits(int num)
	{
		int[] ans = new int[num + 1];
		for (int i = 1; i <= num; i++)
			ans[i] = ans[i >> 1] + (i & 1);
		return ans;
	}
}

public class LC331_340
{
	public static void testSolution331()
	{
		Solution331 s = new Solution331();
		String str;
		str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		// str="1,#";
		str = "9,#,#,1";
		System.out.println(s.isValidSerialization(str));
	}
	
	public static void test336()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input336.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output336.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			
			Solution336_3 s=new Solution336_3();
			
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] data = inLine.substring(1, inLine.length() - 1).split(",");
				String[] wds = new String[data.length];
				for (int i = 0; i < data.length; i++)
					wds[i] = data[i].substring(1,data[i].length()-1);

				List<List<Integer>> ans=s.palindromePairs(wds);

				System.out.println(ans);
				bfw.write("" +ans);
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
		test336();
	}
}
