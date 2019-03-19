package lc1_10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ListNode
{
	public int val;
	public ListNode next;

	public ListNode(int x)
	{
		val = x;
	}
}

//t1.Two Sum    
//t=22ms, m=28MB
//t: 0.4022, o: 0.1093
class Solution1
{
	public int[] twoSum(int[] nums, int target)
	{
		for (int i = 0; i < nums.length; i++)
			for (int j = i + 1; j < nums.length; j++)
				if (nums[i] + nums[j] == target)
					return (new int[]
					{ i, j });
		throw new IllegalArgumentException("No two sum solution");
	}
}

//t1_2.
//t=3ms, m=23MB
//t:0.997, o:0.9266
class Solution1_2
{
	public int[] twoSum(int[] nums, int target)
	{
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++)
		{
			int complement = target - nums[i];
			if (map.containsKey(complement))
				return new int[]
				{ map.get(complement), i };
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}
}

//t2.Add Two Numbers   
//t=23ms, o=37MB
//t:0.6923, o=0.1324
class Solution2
{
	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		ListNode head, before;
		head = new ListNode(0);
		before = head;
		int acc = 0;
		while ((l1 != null) || (l2 != null))
		{
			int s = acc;
			if (l1 != null)
			{
				s += l1.val;
				l1 = l1.next;
			}
			if (l2 != null)
			{
				s += l2.val;
				l2 = l2.next;
			}
			before.next = new ListNode(s % 10);
			acc = s / 10;
			before = before.next;
		}
		if (acc != 0)
		{
			before.next = new ListNode(acc);
		}
		return head.next;
	}
}

//t3.Longest Substring Without Repeating Characters    
//t=25ms, o=29MB
//t:0.8184, o:0.1718
class Solution3
{
	// t3
	// suppose letters are bytes.
	public int lengthOfLongestSubstring(String s)
	{
		int len = s.length();
		int max = 0;
		boolean used[] = new boolean[256];
		for (int i = 0; i < len; i++)
		{
			for (int j = 0; j < 256; j++)
			{
				used[j] = false;
			}
			int j = 0;
			for (j = 0; i + j < len; j++)
			{
				if (used[(int) s.charAt(i + j)])
				{
					break;
				} else
				{
					used[(int) s.charAt(i + j)] = true;
				}
			}
			if (j > max)
			{
				max = j;
			}
		}
		return max;
	}
}

//t3_2
//t=29ms, o=25MB
//t:0.6157, o:7687
class Solution3_2
{
	public int lengthOfLongestSubstring(String s)
	{
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n)
		{
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j)))
			{
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else
				set.remove(s.charAt(i++));
		}
		return ans;
	}
}

//t4.Median of Two Sorted Arrays  
//t=31ms, o=38MB
//t:0.5878, o:0.3750
class Solution4
{
	public double findMedianSortedArrays(int[] nums1, int[] nums2)
	{
		int len1 = nums1.length, len2 = nums2.length, len = len1 + len2;
		float s = 0;
		int p1 = 0, p2 = 0, p = 0;
		boolean even = (len % 2 == 0);
		while (true)
		{
			float temp = 0;
			if (p1 == len1)
			{
				temp = nums2[p2++];
			} else if (p2 == len2)
			{
				temp = nums1[p1++];
			} else
			{
				if (nums1[p1] < nums2[p2])
				{
					temp = nums1[p1++];
				} else
				{
					temp = nums2[p2++];
				}
			}
			if ((!even) && (p == len / 2))
			{
				return temp;
			}
			if (even && (p == len / 2 - 1))
			{
				s = temp;
			}
			if (even && (p == len / 2))
			{
				return (s + temp) / 2;
			}
			p++;
		}
	}
}

//t5.Longest Palindromic Substring    
//t=28ms, o=27MB
//t:0.5682, o:5953
class Solution5
{
	// t5
	public String longestPalindrome(String s)
	{
		if (s.length() == 0)
		{
			return s;
		}
		int maxlen = 1, l = 0, r = 1, slen = s.length();
		// type 1 "bab"
		for (int mid = 1; mid < slen; mid++)
		{
			for (int j = 1; (mid - j >= 0) && (mid + j < slen); j++)
			{
				if (s.charAt(mid - j) != s.charAt(mid + j))
				{
					break;
				}
				if (2 * j + 1 > maxlen)
				{
					maxlen = 2 * j + 1;
					l = mid - j;
					r = mid + j + 1;
				}
			}
		}
		// type 2 "baab"
		for (int mid = 0; mid < slen; mid++)
		{
			for (int j = 1; (mid - j + 1 >= 0) && (mid + j < slen); j++)
			{
				if (s.charAt(mid - j + 1) != s.charAt(mid + j))
				{
					break;
				}
				if (2 * j > maxlen)
				{
					maxlen = 2 * j;
					l = mid - j + 1;
					r = mid + j + 1;
				}
			}
		}
		return s.substring(l, r);
	}
}

//t6.ZigZag Conversion    
//t=19ms, o=29MB
//t:0.9889, o:3424
class Solution6
{
	public String convert(String s, int numRows)
	{
		int len = s.length();
		char[] c = new char[len];
		int p = 0;
		if (numRows == 1)
			return s;
		if (numRows == 2)
		{
			for (int i = 0; i < len; i += 2)
			{
				c[p++] = s.charAt(i);
			}
			for (int i = 1; i < len; i += 2)
			{
				c[p++] = s.charAt(i);
			}
			return new String(c);
		}
		for (int i = 0; i < len; i += 2 * numRows - 2)
		{
			c[p++] = s.charAt(i);
		}
		for (int j = 2; j < numRows; j++)
		{
			for (int i = j - 1; i < len; i += 2 * numRows - 2)
			{
				c[p++] = s.charAt(i);
				if (i + 2 * (numRows - j) < len)
				{
					c[p++] = s.charAt(i + 2 * (numRows - j));
				}
			}
		}
		for (int i = numRows - 1; i < len; i += 2 * numRows - 2)
		{
			c[p++] = s.charAt(i);
		}
		return new String(c);
	}
}

//t7.Reverse Integer    
//t=17ms, o=27MB
//t:0.7739, o:0.2024
class Solution7
{
	public int reverse(int x)
	{
		int result = 0;

		while (x != 0)
		{
			int tail = x % 10;
			int newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result)
			{
				return 0;
			}
			result = newResult;
			x = x / 10;
		}

		return result;
	}
}

//t8.String to Integer (atoi)    
//t=20ms, o=23MB
//t:0.6343, o:8114
class Solution8
{
	// t8
	public int myAtoi(String str)
	{
		int a = 0;
		boolean isPosi = true;
		// 排除字符串首空格
		for (int i = 0; i < str.length();)
		{
			if (str.charAt(i) == ' ')
			{
				str = str.substring(0, i) + str.substring(i + 1, str.length());
			} else
			{
				break;
			}
		}
		// 空串返0
		if (str.length() == 0)
		{
			return 0;
		}
		// 处理符号
		if (str.charAt(0) == '-')
		{
			isPosi = false;
			str = str.substring(1, str.length());
		} else if (str.charAt(0) == '+')
		{
			isPosi = true;
			str = str.substring(1, str.length());
		} else
		{
			isPosi = true;
		}
		// 处理空串，或者去掉符号位之后首位字符非数字
		if (str.length() == 0)
		{
			return 0;
		} else if ((str.charAt(0) < '0') || (str.charAt(0) > '9'))
		{
			return 0;
		}

		for (int i = 0; i < str.length(); i++)
		{
			// 首位之后，遇到非数字，则退出循环，输出当前已经处理的数字
			if ((str.charAt(i) < '0') || (str.charAt(i) > '9'))
			{
				break;
			}
			int tail = ((int) str.charAt(i) - (int) '0');
			int newa = a * 10 + (isPosi ? tail : -tail);
			// 处理溢出
			if (newa / 10 != a)
			{
				if (isPosi)
				{
					return Integer.MAX_VALUE;
				} else
				{
					return Integer.MIN_VALUE;
				}
			} else
			{
				a = newa;
			}
		}
		return a;
	}
}

//t9.Palindrome Number    
//t=76ms, o=29MB
//t:0.9517, o:0.3801
class Solution9
{
	public boolean isPalindrome(int x)
	{
		if (x < 0)
		{
			return false;
		}
		if (x == 0)
		{
			return true;
		}
		int a[] = new int[11], l = 0;
		while (x > 0)
		{
			a[l++] = x % 10;
			x = x / 10;
		}
		for (int i = 0; i < l - 1 - i; i++)
		{
			if (a[i] != a[l - 1 - i])
			{
				return false;
			}
		}
		return true;
	}

}

//10. Regular Expression Matching
//The rule does not explain the the test cases.
//How can ("",".*")  be true?
//I decide to copy a solution in the "Discuss".
//https://leetcode.com/problems/regular-expression-matching/discuss/6034/Share-a-scarce-DP-solution-in-Java-(time%3A-O(mn)-spaceO(n))
//Runtime: 11 ms, faster than 98.56% of Java online submissions for Regular Expression Matching.
//Memory Usage: 38.9 MB, less than 36.68% of Java online submissions for Regular Expression Matching.
class Solution10
{
	public boolean isMatch(String s, String p)
	{
		int lens = s.length();
		int lenp = p.length();
		if (lens == 0 && lenp == 0)
			return true;

		boolean[] last = new boolean[lenp + 1]; // last[j] means if p[1~j] matches s[1~i-1]
		boolean[] cur = new boolean[lenp + 1]; // last[j] means if p[1~j] matches s[1~i]
		last[0] = cur[0] = true;
		// for string like "a*b*c*", make last/cur[indexOf('*')]=true.
		for (int j = 1; j <= lenp; j++)
		{
			if (j >= 2 && p.charAt(j - 1) == '*' && last[j - 2])
			{
				last[j] = cur[j] = true;
			}
		}

		for (int i = 1; i <= lens; i++)
		{
			// determine if p[1~j] matches s[1~i].
			cur[0] = false;
			for (int j = 1; j <= lenp; j++)
			{
				// three cases:
				// 1) p[j]==s[i] (include p[j]=='.') and p[1~j-1] matches s[1~i-1];
				// 2) p[j-1~j]="x*" and s[i]='x' and p[1~j] matches s[1~i-1];
				// 3) p[j-2~j]="xy*" and p[1~j-2] matches s[1~i].
				cur[j] = (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') && last[j - 1]
						|| p.charAt(j - 1) == '*' && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
								&& last[j]
						|| j >= 2 && p.charAt(j - 1) == '*' && cur[j - 2];
			}
			for (int j = 0; j <= lenp; j++)
			{
				last[j] = cur[j];
			}
		}

		return cur[lenp];
	}
}

public class LC1_10
{
	public static void main(String[] args)
	{

	}

}
