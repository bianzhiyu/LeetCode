package lc871_880;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//873. Length of Longest Fibonacci Subsequence
//Runtime: 1519 ms, faster than 1.05% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
//N^3
class Solution873
{
	public int lenLongestFibSubseq(int[] A)
	{
		int lenA = A.length;
		int[][] f = new int[lenA][lenA];
		int max = 0;
		for (int i = lenA - 3; i >= 0; i--)
			for (int j = lenA - 2; j >= i + 1; j--)
			{
				for (int k = lenA - 1; k >= j + 1; k--)
				{
					if (A[i] + A[j] == A[k] && f[j][k] + 1 >= f[i][j])
						f[i][j] = f[j][k] + 1;
				}
				if (f[i][j] > max)
					max = f[i][j];
			}
		return max > 0 ? max + 2 : 0;
	}
}

//N^2 log N
//Runtime: 116 ms, faster than 22.39% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49.5 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
class Solution873_2
{
	int indexOf(int[] a, int tar, int l, int r)
	{
		if (l > r)
			return -1;
		if (l == r)
			return a[l] == tar ? l : -1;
		if (r == l + 1)
		{
			if (a[l] == tar)
				return l;
			if (a[r] == tar)
				return r;
			return -1;
		}
		int m = (l + r) / 2;
		if (a[m] == tar)
			return m;
		if (a[m] > tar)
			return indexOf(a, tar, l, m - 1);
		if (a[m] < tar)
			return indexOf(a, tar, m + 1, r);
		return -1;
	}

	public int lenLongestFibSubseq(int[] A)
	{
		int lenA = A.length;
		int[][] f = new int[lenA][lenA];
		int max = 0, k;
		for (int i = lenA - 3; i >= 0; i--)
			for (int j = lenA - 2; j >= i + 1; j--)
			{
				if ((k = indexOf(A, A[i] + A[j], j + 1, lenA - 1)) != -1)
					if (f[j][k] + 1 > f[i][j])
						f[i][j] = f[j][k] + 1;
				if (f[i][j] > max)
					max = f[i][j];
			}
		return max > 0 ? max + 2 : 0;
	}
}

//Runtime: 86 ms, faster than 49.84% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49.1 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
class Solution873_3
{
	int indexOf(int[] a, int tar, int l, int r)
	{
		if (l > r)
			return -1;
		if (l == r)
			return a[l] == tar ? l : -1;
		if (r == l + 1)
		{
			if (a[l] == tar)
				return l;
			if (a[r] == tar)
				return r;
			return -1;
		}
		int m = (l + r) / 2;
		if (a[m] == tar)
			return m;
		if (a[m] > tar)
			return indexOf(a, tar, l, m - 1);
		return indexOf(a, tar, m + 1, r);
	}

	int fd(int[] a, int tar, int l, int r)
	{
		if (l > r)
			return -1;
		if (r == l)
			return a[r] <= tar ? r : -1;
		if (r == l + 1)
		{
			if (a[r] <= tar)
				return r;
			if (a[l] <= tar)
				return l;
			else
				return -1;
		}
		int m = (l + r) / 2;
		if (a[m] <= tar)
			return fd(a, tar, m, r);
		return fd(a, tar, l, m - 1);
	}

	public int lenLongestFibSubseq(int[] A)
	{
		int lenA = A.length;
		int[][] f = new int[lenA][lenA];
		int max = 0, k;
		for (int i = lenA - 3; i >= 0; i--)
		{

			int j = fd(A, A[lenA - 1] - A[i], i + 1, lenA - 1);
			for (; j >= i + 1; j--)
			{

				if ((k = indexOf(A, A[i] + A[j], j + 1, lenA - 1)) != -1)
					if (f[j][k] + 1 > f[i][j])
						f[i][j] = f[j][k] + 1;
				if (f[i][j] > max)
					max = f[i][j];
			}
		}
		return max > 0 ? max + 2 : 0;
	}
}

//874. Walking Robot Simulation
//Runtime: 506 ms, faster than 5.04% of Java online submissions for Walking Robot Simulation.
//Memory Usage: 60.4 MB, less than 12.50% of Java online submissions for Walking Robot Simulation.
class Solution874
{
	public int robotSim(int[] commands, int[][] obstacles)
	{
		int[] pos = new int[]
		{ 0, 0 };
		int direct = 0;
		int[][] ddir = new int[][]
		{
				{ 0, 1 },
				{ 1, 0 },
				{ 0, -1 },
				{ -1, 0 } };
		int maxdist = 0, comlen = commands.length, obstlen = obstacles.length;
		int nx, ny, nd;
		for (int i = 0; i < comlen; i++)
		{
			if (commands[i] == -1)
			{
				direct = (direct + 1) % 4;
				continue;
			}
			if (commands[i] == -2)
			{
				direct = (direct + 3) % 4;
				continue;
			}
			int m = commands[i];
			for (int j = 0; j < obstlen; j++)
			{
				if (direct == 0 && pos[0] == obstacles[j][0] && pos[1] + 1 <= obstacles[j][1]
						&& obstacles[j][1] <= pos[1] + m)
				{
					m = obstacles[j][1] - pos[1] - 1;
				} else if (direct == 1 && pos[1] == obstacles[j][1] && pos[0] + 1 <= obstacles[j][0]
						&& obstacles[j][0] <= pos[0] + m)
				{
					m = obstacles[j][0] - pos[0] - 1;
				} else if (direct == 2 && pos[0] == obstacles[j][0] && pos[1] - 1 >= obstacles[j][1]
						&& obstacles[j][1] >= pos[1] - m)
				{
					m = pos[1] - 1 - obstacles[j][1];
				} else if (direct == 3 && pos[1] == obstacles[j][1] && pos[0] - 1 >= obstacles[j][0]
						&& obstacles[j][0] >= pos[0] - m)
				{
					m = pos[0] - 1 - obstacles[j][0];
				}
			}
			nx = pos[0] + m * ddir[direct][0];
			ny = pos[1] + m * ddir[direct][1];
			nd = nx * nx + ny * ny;
			if (nd > maxdist)
				maxdist = nd;
			pos[0] = nx;
			pos[1] = ny;

		}
		return maxdist;
	}
}

//875. Koko Eating Bananas
//Runtime: 11 ms, faster than 88.57% of Java online submissions for Koko Eating Bananas.
//Memory Usage: 40.6 MB, less than 82.05% of Java online submissions for Koko Eating Bananas.
class Solution875
{
	private int hrs(int[] p, int s)
	{
		int t = 0;
		for (int i = 0; i < p.length; i++)
			t += (p[i] - 1) / s + 1;
		return t;
	}

	public int minEatingSpeed(int[] piles, int H)
	{
		int r = 0, len = piles.length;
		for (int i = 0; i < len; i++)
			if (piles[i] > r)
				r = piles[i];
		int l = 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (hrs(piles, l) <= H)
					r = l;
				else
					l = r;
			} else
			{
				int m = l + (r - l) / 2;
				if (hrs(piles, m) <= H)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}
}

//877. Stone Game
//Runtime: 8 ms, faster than 40.47% of Java online submissions for Stone Game.
//Memory Usage: 39 MB, less than 13.91% of Java online submissions for Stone Game.
class Solution877
{
	private int[] s;

	private int partSum(int l, int r)
	{
		if (l == 0)
			return s[r];
		return s[r] - s[l - 1];
	}

	public boolean stoneGame(int[] piles)
	{
		int len = piles.length;
		s = new int[len];
		s[0] = piles[0];
		for (int i = 1; i < len; i++)
			s[i] = s[i - 1] + piles[i];
		int[][] d = new int[len][len];
		for (int i = 0; i < len; i++)
			d[i][i] = piles[i];
		for (int l = 2; l <= len; l++)
			for (int i = 0; i + l - 1 < len; i++)
			{
				d[i][i + l - 1] = Math.max(piles[i] + partSum(i + 1, i + l - 1) - d[i + 1][i + l - 1],
						piles[i + l - 1] + partSum(i, i + l - 2) - d[i][i + l - 2]);
			}
		return d[0][len - 1] > s[len - 1] - d[0][len - 1];
	}
}

///https://leetcode.com/problems/stone-game/solution/
//ummm....
class Solution877_2
{
	public boolean stoneGame(int[] piles)
	{
		return true;
	}
}

//880. Decoded String at Index
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Decoded String at Index.
//Memory Usage: 35.7 MB, less than 93.75% of Java online submissions for Decoded String at Index.
class Solution880
{
	private boolean isNum(char c)
	{
		return '0'<=c && c<='9';
	}
	public String decodeAtIndex(String S, int K)
	{
		char[]str=S.toCharArray();
		int len=str.length;
		long[]l=new long[len];
		l[0]=1;
		for (int i=1;i<len;i++)
			if (isNum(str[i]))
				l[i]=l[i-1]*(str[i]-'0');
			else l[i]=l[i-1]+1;
		long k=K;
		for (int i=len-1;i>=0;i--)
			if (isNum(str[i]))
				k=(k-1)%l[i-1]+1;
			else
				if (l[i]==k) return ""+str[i];
		return "";
	}
}

public class LC871_880
{
	public static void test880()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input880.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output880.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				inLine=inLine.substring(1,inLine.length()-1);

				int K=Integer.parseInt(bfr.readLine());
				
				Solution880 s=new Solution880();
				
				String ans=s.decodeAtIndex(inLine, K);
				
				System.out.println(ans);

				bfw.write(ans);
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
		test880();
	}
}
