package lc221_230;

import java.util.ArrayList;
import bbst.BBST;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x)
	{
		val = x;
	}
}

//221. Maximal Square
//Runtime: 7 ms, faster than 97.80% of Java online submissions for Maximal Square.
//Memory Usage: 40.9 MB, less than 49.84% of Java online submissions for Maximal Square.
class Solution221
{
	int f(int[][] s, int i, int j, int l)
	{
		if (i == 0)
		{
			if (j == 0)
				return s[l][l];
			return s[l][j + l] - s[l][j - 1];
		}
		if (j == 0)
			return s[i + l][l] - s[i - 1][l];
		return s[i + l][j + l] + s[i - 1][j - 1] - s[i + l][j - 1] - s[i - 1][j + l];
	}

	public int maximalSquare(char[][] matrix)
	{
		if (matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][] s = new int[m][n];
		if (matrix[0][0] == '1')
			s[0][0] = 1;
		else
			s[0][0] = 0;
		for (int i = 1; i < n; i++)
			s[0][i] = s[0][i - 1] + (matrix[0][i] == '1' ? 1 : 0);
		for (int i = 1; i < m; i++)
		{
			s[i][0] = s[i - 1][0] + (matrix[i][0] == '1' ? 1 : 0);
			for (int j = 1; j < n; j++)
				s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + (matrix[i][j] == '1' ? 1 : 0);
		}
		int max = 0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				for (int l = (int) Math.round(Math.sqrt(max)); i + l < m && j + l < n; l++)
				{
					int t = f(s, i, j, l);
					if (t != (l + 1) * (l + 1))
						break;
					max = (l + 1) * (l + 1);
				}
		return max;
	}
}

//222. Count Complete Tree Nodes
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Count Complete Tree Nodes.
//Memory Usage: 38.9 MB, less than 92.80% of Java online submissions for Count Complete Tree Nodes.
class Solution222
{
	public int countNodes(TreeNode root)
	{
		if (root == null)
			return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);

	}
}

//224. Basic Calculator
//Only parenthesis, +,-,*,/, positive numbers are allowed
//No direct negative numbers:
//no such thing like (-1)*2.
//Runtime: 45 ms, faster than 22.60% of Java online submissions for Basic Calculator.
//Memory Usage: 53.9 MB, less than 12.54% of Java online submissions for Basic Calculator.
class Solution224
{
	boolean larger(String s1, String s2)
	{
		if (s2.compareTo("(") == 0)
			return true;
		if ((s2.compareTo("+") == 0 || s2.compareTo("-") == 0))
			return s1.compareTo("*") == 0 || s1.compareTo("/") == 0;
		return false;
	}

	List<String> toRPN(String s)
	{
		List<String> ans = new ArrayList<String>();
		String[] Stack = new String[5000];
		int slen = s.length(), i = 0, sp = 0;
		while (i < slen)
		{
			if (s.charAt(i) == ' ')
				i++;
			else if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
			{
				int j = i + 1;
				while (j < slen && s.charAt(j) >= '0' && s.charAt(j) <= '9')
					j++;
				ans.add(s.substring(i, j));
				i = j;
			} else if (s.charAt(i) == '(')
			{
				Stack[sp++] = "(";
				i++;
			} else if (s.charAt(i) == ')')
			{
				while (Stack[sp - 1].compareTo("(") != 0)
					ans.add(Stack[--sp]);
				sp--;
				i++;
			} else
			{
				while (sp > 0 && !larger("" + s.charAt(i), Stack[sp - 1]))
					ans.add(Stack[--sp]);
				Stack[sp++] = s.charAt(i) + "";
				i++;
			}
		}
		while (sp > 0)
			ans.add(Stack[--sp]);
		return ans;
	}

	public int calculate(String s)
	{
		return evalRPN(toRPN(s));
	}

	// From solution150
	int evalRPN(List<String> tokens)
	{
		int[] stack = new int[500];
		int sp = 0;
		for (int i = 0; i < tokens.size(); i++)
		{
			if (tokens.get(i).compareTo("+") == 0)
			{
				stack[sp - 2] = stack[sp - 2] + stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("-") == 0)
			{
				stack[sp - 2] = stack[sp - 2] - stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("*") == 0)
			{
				stack[sp - 2] = stack[sp - 2] * stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("/") == 0)
			{
				stack[sp - 2] = stack[sp - 2] / stack[sp - 1];
				sp--;
			} else
				stack[sp++] = Integer.parseInt(tokens.get(i));
		}
		return stack[0];
	}
}

//225. Implement Stack using Queues
//Runtime: 48 ms, faster than 100.00% of Java online submissions for Implement Stack using Queues.
//Memory Usage: 36.7 MB, less than 20.14% of Java online submissions for Implement Stack using Queues.
class MyStack
{

	Stack<Integer> s = new Stack<Integer>();

	/** Initialize your data structure here. */
	public MyStack()
	{

	}

	/** Push element x onto stack. */
	public void push(int x)
	{
		s.push(x);

	}

	/** Removes the element on top of the stack and returns that element. */
	public int pop()
	{
		return s.pop();

	}

	/** Get the top element. */
	public int top()
	{
		return s.peek();

	}

	/** Returns whether the stack is empty. */
	public boolean empty()
	{
		return s.isEmpty();
	}
}

/**
 * Your MyStack object will be instantiated and called as such: MyStack obj =
 * new MyStack(); obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

//226. Invert Binary Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
//Memory Usage: 35.9 MB, less than 5.20% of Java online submissions for Invert Binary Tree.
class Solution226
{
	public TreeNode invertTree(TreeNode root)
	{
		if (root == null)
			return null;
		TreeNode nr = new TreeNode(root.val);
		nr.left = invertTree(root.right);
		nr.right = invertTree(root.left);
		return nr;
	}
}

//227. Basic Calculator II
//Just The Same Code of 224
//Runtime: 40 ms, faster than 22.39% of Java online submissions for Basic Calculator II.
//Memory Usage: 49 MB, less than 5.21% of Java online submissions for Basic Calculator II.
class Solution227
{
	boolean larger(String s1, String s2)
	{
		if (s2.compareTo("(") == 0)
			return true;
		if ((s2.compareTo("+") == 0 || s2.compareTo("-") == 0))
			return s1.compareTo("*") == 0 || s1.compareTo("/") == 0;
		return false;
	}

	List<String> toRPN(String s)
	{
		List<String> ans = new ArrayList<String>();
		String[] Stack = new String[5000];
		int slen = s.length(), i = 0, sp = 0;
		while (i < slen)
		{
			if (s.charAt(i) == ' ')
				i++;
			else if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
			{
				int j = i + 1;
				while (j < slen && s.charAt(j) >= '0' && s.charAt(j) <= '9')
					j++;
				ans.add(s.substring(i, j));
				i = j;
			} else if (s.charAt(i) == '(')
			{
				Stack[sp++] = "(";
				i++;
			} else if (s.charAt(i) == ')')
			{
				while (Stack[sp - 1].compareTo("(") != 0)
					ans.add(Stack[--sp]);
				sp--;
				i++;
			} else
			{
				while (sp > 0 && !larger("" + s.charAt(i), Stack[sp - 1]))
					ans.add(Stack[--sp]);
				Stack[sp++] = s.charAt(i) + "";
				i++;
			}
		}
		while (sp > 0)
			ans.add(Stack[--sp]);
		return ans;
	}

	public int calculate(String s)
	{
		return evalRPN(toRPN(s));
	}

	// From solution150
	int evalRPN(List<String> tokens)
	{
		int[] stack = new int[500];
		int sp = 0;
		for (int i = 0; i < tokens.size(); i++)
		{
			if (tokens.get(i).compareTo("+") == 0)
			{
				stack[sp - 2] = stack[sp - 2] + stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("-") == 0)
			{
				stack[sp - 2] = stack[sp - 2] - stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("*") == 0)
			{
				stack[sp - 2] = stack[sp - 2] * stack[sp - 1];
				sp--;
			} else if (tokens.get(i).compareTo("/") == 0)
			{
				stack[sp - 2] = stack[sp - 2] / stack[sp - 1];
				sp--;
			} else
				stack[sp++] = Integer.parseInt(tokens.get(i));
		}
		return stack[0];
	}
}

//228. Summary Ranges
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Summary Ranges.
//Memory Usage: 37 MB, less than 98.74% of Java online submissions for Summary Ranges.
class Solution228
{
	public List<String> summaryRanges(int[] nums)
	{
		int len = nums.length;
		int i = 0;
		List<String> ans = new ArrayList<String>();

		while (i < len)
		{
			int j = i + 1;
			while (j < len && nums[j - 1] + 1 == nums[j])
				j++;
			if (j > i + 1)
				ans.add(nums[i] + "->" + nums[j - 1]);
			else
				ans.add("" + nums[i]);
			i = j;
		}

		return ans;
	}
}

//229. Majority Element II
//Runtime: 2 ms, faster than 97.44% of Java online submissions for Majority Element II.
//Memory Usage: 41.4 MB, less than 23.75% of Java online submissions for Majority Element II.
class Solution229
{
	public List<Integer> majorityElement(int[] nums)
	{
		List<Integer> ans = new ArrayList<Integer>();
		Arrays.sort(nums);
		int i = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] == nums[i])
				j++;
			if (j - i > nums.length / 3)
				ans.add(nums[i]);
			i = j;
		}
		return ans;
	}
}

//Runtime: 13 ms, faster than 14.62% of Java online submissions for Majority Element II.
//Memory Usage: 40.5 MB, less than 25.12% of Java online submissions for Majority Element II.
class Solution229_2
{
	public List<Integer> majorityElement(int[] A)
	{
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();

		for (int n : A)
		{
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		for (int n : A)
		{
			if (map.get(n) > A.length / 3)
				set.add(n);
		}
		list.addAll(set);
		return list;
	}
}

//230. Kth Smallest Element in a BST
//Runtime: 2 ms, faster than 18.73% of Java online submissions for Kth Smallest Element in a BST.
//Memory Usage: 40.5 MB, less than 5.12% of Java online submissions for Kth Smallest Element in a BST.
class Solution230
{
	BBST<Integer> Trav(TreeNode r, BBST<Integer> b)
	{
		if (r == null)
			return b;
		b = Trav(r.left, b);
		b = b.insert(r.val);
		return Trav(r.right, b);
	}

	public int kthSmallest(TreeNode root, int k)
	{
		BBST<Integer> bst = new BBST<Integer>(root.val);
		bst = Trav(root.left, bst);
		bst = Trav(root.right, bst);
		for (int i = 1; i < k; i++)
			bst = bst.removeMin();
		return bst.getMinData();
	}
}

public class LC221_230
{
	public static void main(String[] ag)
	{
		Solution224 s = new Solution224();
		System.out.println(s.calculate("1 + 1"));
	}

}
