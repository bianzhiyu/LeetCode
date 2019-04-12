package lc761_770;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//763. Partition Labels
//Runtime: 8 ms, faster than 67.46% of Java online submissions for Partition Labels.
//Memory Usage: 38.4 MB, less than 5.00% of Java online submissions for Partition Labels.
class Solution763
{
	public static class Col763
	{
		int[] ct;

		Col763()
		{
			ct = new int[26];
		}

		Col763(Col763 na)
		{
			ct = new int[26];
			for (int i = 0; i < 26; i++)
				ct[i] = na.ct[i];
		}

		Col763 minus(Col763 na)
		{
			Col763 ans = new Col763(this);
			for (int i = 0; i < 26; i++)
				ans.ct[i] -= na.ct[i];
			return ans;
		}

		static boolean disjoint(Col763 c1, Col763 c2)
		{
			for (int i = 0; i < 26; i++)
				if (c1.ct[i] > 0 && c2.ct[i] > 0)
					return false;
			return true;
		}

		void setAllZero()
		{
			for (int i = 0; i < 26; i++)
				ct[i] = 0;
		}
		// Col763 minus
	}

	public List<Integer> partitionLabels(String S)
	{
		List<Integer> ans = new ArrayList<Integer>();
		int n = S.length();
		if (n == 0)
			return ans;
		Col763[] ana = new Col763[n + 1];
		ana[0] = new Col763();
		for (int i = 0; i < n; i++)
		{
			ana[i + 1] = new Col763(ana[i]);
			ana[i + 1].ct[S.charAt(i) - 'a']++;
		}
		int left = 0;
		Col763 rem = new Col763(ana[n]), sinceleft = new Col763();
		for (int i = 1; i <= n; i++)
		{
			rem.ct[S.charAt(i - 1) - 'a']--;
			sinceleft.ct[S.charAt(i - 1) - 'a']++;
			if (Col763.disjoint(sinceleft, rem))
			{
				ans.add(i - left);
				left = i;
				sinceleft.setAllZero();
			}
		}
		return ans;
	}
}

//764. Largest Plus Sign
//Runtime: 265 ms, faster than 32.84% of Java online submissions for Largest Plus Sign.
//Memory Usage: 56.4 MB, less than 54.76% of Java online submissions for Largest Plus Sign.
class Solution764
{
	public int orderOfLargestPlusSign(int N, int[][] mines)
	{
		int[][] map = new int[N][N];
		for (int i = 0; i < mines.length; i++)
			map[mines[i][0]][mines[i][1]] = 1;
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				if (map[i][j] == 1)
					continue;
				int k = 1;
				while (j - k >= 0 && j + k < N && i - k >= 0 && i + k < N && map[i][j - k] == 0 && map[i][j + k] == 0
						&& map[i - k][j] == 0 && map[i + k][j] == 0)
				{
					k++;
				}
				if (k > max)
					max = k;
			}
		return max;
	}
}

//https://leetcode.com/problems/largest-plus-sign/solution/
//Runtime: 208 ms, faster than 49.47% of Java online submissions for Largest Plus Sign.
//Memory Usage: 59.1 MB, less than 42.86% of Java online submissions for Largest Plus Sign.
class Solution764_2
{
	public int orderOfLargestPlusSign(int N, int[][] mines)
	{
		Set<Integer> banned = new HashSet<Integer>();
		int[][] dp = new int[N][N];

		for (int[] mine : mines)
			banned.add(mine[0] * N + mine[1]);
		int ans = 0, count;

		for (int r = 0; r < N; ++r)
		{
			count = 0;
			for (int c = 0; c < N; ++c)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				dp[r][c] = count;
			}

			count = 0;
			for (int c = N - 1; c >= 0; --c)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				dp[r][c] = Math.min(dp[r][c], count);
			}
		}

		for (int c = 0; c < N; ++c)
		{
			count = 0;
			for (int r = 0; r < N; ++r)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				dp[r][c] = Math.min(dp[r][c], count);
			}

			count = 0;
			for (int r = N - 1; r >= 0; --r)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				dp[r][c] = Math.min(dp[r][c], count);
				ans = Math.max(ans, dp[r][c]);
			}
		}

		return ans;
	}
}

//Runtime: 227 ms, faster than 44.42% of Java online submissions for Largest Plus Sign.
//Memory Usage: 58.3 MB, less than 47.62% of Java online submissions for Largest Plus Sign.
class Solution764_3
{
	public int orderOfLargestPlusSign(int N, int[][] mines)
	{
		Set<Integer> banned = new HashSet<Integer>();
		int[][] toLeft = new int[N][N], toRight = new int[N][N], toUp = new int[N][N], toDown = new int[N][N];

		for (int[] mine : mines)
			banned.add(mine[0] * N + mine[1]);
		int ans = 0, count;

		for (int r = 0; r < N; ++r)
		{
			count = 0;
			for (int c = 0; c < N; ++c)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				toLeft[r][c] = count;
			}

			count = 0;
			for (int c = N - 1; c >= 0; --c)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				toRight[r][c] = count;
			}
		}

		for (int c = 0; c < N; ++c)
		{
			count = 0;
			for (int r = 0; r < N; ++r)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				toUp[r][c] = count;
			}

			count = 0;
			for (int r = N - 1; r >= 0; --r)
			{
				count = banned.contains(r * N + c) ? 0 : count + 1;
				toDown[r][c] = count;
			}
		}
		ans = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				int max = toLeft[i][j];
				if (toRight[i][j] < max)
					max = toRight[i][j];
				if (toUp[i][j] < max)
					max = toUp[i][j];
				if (toDown[i][j] < max)
					max = toDown[i][j];
				if (max > ans)
					ans = max;
			}
		return ans;
	}
}

//766. Toeplitz Matrix
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Toeplitz Matrix.
//Memory Usage: 44.3 MB, less than 22.91% of Java online submissions for Toeplitz Matrix.
class Solution766
{
	public boolean isToeplitzMatrix(int[][] matrix)
	{
		int R = matrix.length, C = matrix[0].length;
		for (int i = 0; i < R; i++)
		{
			// start from (i,0)
			int j = i + 1;
			while (j < R && j - i < C)
			{
				if (matrix[j][j - i] != matrix[i][0])
					return false;
				j++;
			}
		}
		for (int i = 1; i < C; i++)
		{
			// start from (0,i)
			int j = i + 1;
			while (j < C && j - i < R)
			{
				if (matrix[j - i][j] != matrix[0][i])
					return false;
				j++;
			}
		}
		return true;
	}
}

//767. Reorganize String
//Runtime: 2 ms, faster than 98.76% of Java online submissions for Reorganize String.
//Memory Usage: 36.6 MB, less than 95.95% of Java online submissions for Reorganize String.
class Solution767
{
	private class Mp implements Comparable<Mp>
	{
		public char c;
		public int ct;

		public Mp(char _c, int _ct)
		{
			c = _c;
			ct = _ct;
		}

		public int compareTo(Mp o)
		{
			return o.ct - ct;
		}
	}

	public String reorganizeString(String S)
	{
		int[] ct = new int[26];
		for (int i = 0; i < S.length(); i++)
			ct[S.charAt(i) - 'a']++;
		int max = 0;
		for (int i = 0; i < 26; i++)
			if (ct[i] > max)
				max = ct[i];
		if (S.length() % 2 == 0 && max > S.length() / 2)
			return "";
		if (S.length() % 2 == 1 && max > S.length() / 2 + 1)
			return "";
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Mp> l = new PriorityQueue<Mp>();
		for (int i = 0; i < 26; i++)
			if (ct[i] > 0)
				l.add(new Mp((char) (i + 'a'), ct[i]));
		while (!l.isEmpty())
		{
			Mp p = l.poll();
			sb.append(p.c);
			p.ct--;
			if (!l.isEmpty())
			{
				Mp q = l.poll();
				sb.append(q.c);
				q.ct--;
				if (p.ct > 0)
					l.offer(p);
				if (q.ct > 0)
					l.offer(q);
			}
		}
		return sb.toString();
	}
}

//769. Max Chunks To Make Sorted
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Max Chunks To Make Sorted.
//Memory Usage: 35.3 MB, less than 94.38% of Java online submissions for Max Chunks To Make Sorted.
class Solution769
{
	public int maxChunksToSorted(int[] arr)
	{
		int len = arr.length;
		int[] maxUntil = new int[len];
		maxUntil[0] = arr[0];
		for (int i = 1; i < len; i++)
			maxUntil[i] = Math.max(maxUntil[i - 1], arr[i]);
		int[] minSince = new int[len];
		minSince[len - 1] = arr[len - 1];
		for (int i = len - 2; i >= 0; i--)
			minSince[i] = Math.min(minSince[i + 1], arr[i]);
		int ct = 0;
		for (int i = 0; i < len - 1; i++)
			if (maxUntil[i] <= minSince[i + 1])
				ct++;
		return ct + 1;
	}
}

public class LC761_770
{
	public static void main(String[] args)
	{

	}

}
