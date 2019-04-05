package lc1001_1010;

import java.util.ArrayList;
import java.util.List;

import treeCodec.TreeNode;

//1003. Check If Word Is Valid After Substitutions
//Runtime: 15 ms, faster than 77.25% of Java online submissions for Check If Word Is Valid After Substitutions.
//Memory Usage: 39.4 MB, less than 100.00% of Java online submissions for Check If Word Is Valid After Substitutions.
class Solution1003
{
	private static class Ln
	{
		char c;
		Ln next;

		public Ln(char _c)
		{
			c = _c;
			next = null;
		}
	}

	public boolean isValid(String S)
	{
		Ln h = new Ln('0'), p = h;
		for (int i = 0; i < S.length(); i++)
		{
			p.next = new Ln(S.charAt(i));
			p = p.next;
		}
		while (h.next != null)
		{
			p = h;
			Ln p2, p3;
			boolean del = false;
			while (p.next != null && (p2 = p.next).next != null && (p3 = p2.next).next != null)
			{
				if (p.next.c == 'a' && p2.next.c == 'b' && p3.next.c == 'c')

				{
					del = true;
					p.next = p3.next.next;
				} else
					p = p.next;

			}
			if (!del)
				return false;
		}
		return true;
	}
}

//1004. Max Consecutive Ones III
//Runtime: 4 ms, faster than 97.85% of Java online submissions for Max Consecutive Ones III.
//Memory Usage: 42.8 MB, less than 100.00% of Java online submissions for Max Consecutive Ones III.
class Solution1004
{
	public int longestOnes(int[] A, int K)
	{
		int used = 0, left = 0, max = 0;

		for (int i = 0; i < A.length; i++)
		{
			if (A[i] == 0)
			{
				used++;
				if (used > K)
				{
					while (A[left] == 1 && left < i)
						left++;
					left++;
				}
				if (i - left + 1 > max)
					max = i - left + 1;
			} else
			{
				if (i - left + 1 > max)
					max = i - left + 1;
			}
		}
		return max;
	}
}

//https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247534/Java-Sliding-Window
class Solution1004_2
{
	public int longestOnes(int[] A, int K)
	{
		int res = 0, count = 0, start = 0;
		for (int i = 0; i < A.length; i++)
		{
			if (A[i] == 0)
				count++;
			if (count > K)
			{
				res = Math.max(res, i - start);
				while (A[start] == 1)
					start++;
				start++;
				count--;
				System.out.println(res + " " + start + " " + i);
			}
		}
		res = Math.max(res, A.length - start);
		return res;
	}
}

//1006. Clumsy Factorial
//from 150, 224
//Runtime: 90 ms, faster than 5.05% of Java online submissions for Clumsy Factorial.
//Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Clumsy Factorial.
class Solution1006
{
	private boolean larger(String s1, String s2)
	{
		if (s2.compareTo("(") == 0)
			return true;
		if ((s2.compareTo("+") == 0 || s2.compareTo("-") == 0))
			return s1.compareTo("*") == 0 || s1.compareTo("/") == 0;
		return false;
	}

	private List<String> toRPN(String s)
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

	private int calculate(String s)
	{
		return evalRPN(toRPN(s));
	}

	// From solution150
	private int evalRPN(List<String> tokens)
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

	public int clumsy(int N)
	{
		StringBuilder sb = new StringBuilder();
		int K = 0;
		for (int i = N; i >= 1; i--)
		{
			sb.append(i);
			if (i > 1)
			{
				if (K % 4 == 0)
					sb.append('*');
				else if (K % 4 == 1)
					sb.append('/');
				else if (K % 4 == 2)
					sb.append('+');
				else
					sb.append('-');
			}
			K = (K + 1) % 4;
		}
		return calculate(sb.toString());
	}
}

//1008. Construct Binary Search Tree from Preorder Traversal
//Runtime: 1 ms, faster than 81.95% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Construct Binary Search Tree from Preorder Traversal.
class Solution1008
{
	private TreeNode con(int[] po, int start, int end)
	{
		if (start > end)
			return null;
		TreeNode rt = new TreeNode(po[start]);
		int li = -1;
		for (int i = start + 1; i <= end; i++)
			if (po[i] > rt.val)
			{
				li = i;
				break;
			}
		if (li != -1)
		{
			rt.left = con(po, start + 1, li - 1);
			rt.right = con(po, li, end);
		} else
			rt.left = con(po, start + 1, end - 1);
		return rt;
	}

	public TreeNode bstFromPreorder(int[] preorder)
	{
		return con(preorder, 0, preorder.length);
	}
}

//1009. Complement of Base 10 Integer
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Complement of Base 10 Integer.
// Memory Usage: 31.8 MB, less than 100.00% of Java online submissions for Complement of Base 10 Integer.
class Solution1009 
{
	public int bitwiseComplement(int N) 
	{
        int x=1;
        while (x<N) x=1+(x<<1);
        return x-N;    
    }
}

public class LC1001_1010
{
	public static void test1004()
	{
		int[] A = new int[]
		{ 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		int K = 2;
		Solution1004_2 s = new Solution1004_2();
		System.out.println(s.longestOnes(A, K));
	}

	public static void test1006()
	{
		Solution1006 s = new Solution1006();
		System.out.println(s.clumsy(4));
	}

	public static void main(String[] args)
	{
		test1006();
	}
}
