package lc821_830;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bbst.BBST;

//821. Shortest Distance to a Character
//Runtime: 4 ms, faster than 95.67% of Java online submissions for Shortest Distance to a Character.
//Memory Usage: 38 MB, less than 38.51% of Java online submissions for Shortest Distance to a Character.
class Solution821
{
	public int[] shortestToChar(String S, char C)
	{
		int n = S.length();
		int[] md = new int[n];
		for (int i = 0; i < n; i++)
			md[i] = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++)
		{
			if (S.charAt(i) == C)
			{
				md[i] = 0;
				for (int j = i - 1; j >= 0; j--)
					if (S.charAt(j) == C || md[j] <= i - j)
						break;
					else
						md[j] = i - j;
				for (int j = i + 1; j < n; j++)
					if (S.charAt(j) == C)
						break;
					else
						md[j] = j - i;
			}
		}
		return md;
	}
}

//822. Card Flipping Game
//Runtime: 7 ms, faster than 92.47% of Java online submissions for Card Flipping Game.
//Memory Usage: 41.1 MB, less than 66.67% of Java online submissions for Card Flipping Game.
class Solution822
{
	private boolean canBe(int[] f, int[] b, int n)
	{
		for (int i = 0; i < f.length; i++)
			if (n == f[i] && n == b[i])
				return false;
		return true;
	}

	public int flipgame(int[] fronts, int[] backs)
	{
		int len = fronts.length;
		int succ = 0;
		for (int i = 0; i < len; i++)
		{
			if (fronts[i] == backs[i])
				continue;
			if (canBe(fronts, backs, fronts[i]) && (succ == 0 || fronts[i] < succ))
				succ = fronts[i];
			if (canBe(fronts, backs, backs[i]) && (succ == 0 || backs[i] < succ))
				succ = backs[i];
		}
		return succ;
	}
}

//823. Binary Trees With Factors
//Runtime: 20 ms, faster than 95.59% of Java online submissions for Binary Trees With Factors.
//Memory Usage: 39.7 MB, less than 63.16% of Java online submissions for Binary Trees With Factors.
class Solution823
{
	private final static int MOD = 1000000007;

	public int numFactoredBinaryTrees(int[] A)
	{
		Arrays.parallelSort(A);
		int[] d = new int[A.length];
		int len = A.length;
		d[0] = 1;
		int tot = 1;
		for (int i = 1; i < len; i++)
		{
			int p1 = 0, p2 = i - 1;
			d[i] = 1;
			long tt = A[i];
			while (p1 <= p2)
			{
				long t = A[p1];
				t *= A[p2];
				if (t < tt)
					p1++;
				else if (t > tt)
					p2--;
				else
				{
					d[i] = (int) ((d[i] + ((long) d[p1]) * d[p2]) % MOD);
					if (p1 < p2)
						d[i] = (int) ((d[i] + ((long) d[p1]) * d[p2]) % MOD);
					p1++;
					p2--;
				}
			}
			tot = (tot + d[i]) % MOD;
		}
		return tot;
	}
}

//825. Friends Of Appropriate Ages
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Friends Of Appropriate Ages.
//Memory Usage: 39.8 MB, less than 92.06% of Java online submissions for Friends Of Appropriate Ages.
class Solution825
{
	public int numFriendRequests(int[] ages)
	{
		int[] ageCt = new int[123];
		for (int i = 0; i < ages.length; i++)
			ageCt[ages[i]]++;
		int tot = 0;
		for (int i = 0; i < 123; i++)
		{
			if (ageCt[i] == 0)
				continue;
			for (int j = 0; j < 123; j++)
			{
				if (j == i || ageCt[j] == 0)
					continue;
				if ((double) j > (0.5 * i + 7.0) && (j <= i) && (j <= 100 || i >= 100))
					tot += ageCt[j] * ageCt[i];
			}
		}
		for (int i = 0; i < 123; i++)
		{
			if (ageCt[i] == 0)
				continue;
			if ((double) i > 0.5 * i + 7.0)
				tot += ageCt[i] * (ageCt[i] - 1);
		}
		return tot;
	}
}

//826. Most Profit Assigning Work
//Runtime: 64 ms, faster than 55.29% of Java online submissions for Most Profit Assigning Work.
//Memory Usage: 43.4 MB, less than 37.29% of Java online submissions for Most Profit Assigning Work.
class Solution826
{
	private static class Job implements Comparable<Job>
	{
		public int D,P;
		public Job(int d,int p)
		{
			D=d;
			P=p;
		}
		@Override
		public int compareTo(Job o)
		{
			return D-o.D;
		}
	}
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker)
	{
		int len=difficulty.length;
		BBST<Job> rt=new BBST<Job>(new Job(difficulty[0],profit[0]));
		for (int i=1;i<len;i++)
		{
			Job j=new Job(difficulty[i],profit[i]);
			if (!rt.containData(j)) 
				rt=rt.insert(j);
			else
			{
				Job k=rt.getData(j);
				if (k.P<j.P)
					k.P=j.P;
			}
		}
		List<Job> l=new ArrayList<Job>();
		while (rt!=null)
		{
			Job j=rt.getMinData();
			rt=rt.removeMin();
			if (l.size()==0) l.add(j);
			else
			{
				Job k=l.get(l.size()-1);
				if (k.P<j.P) l.add(j);
			}
			
		}
		int tot=0;
		int minD=l.get(0).D;
		for (int w:worker)
		{
			if (w<minD) continue;
			int left=0,right=l.size()-1;
			while (left<right)
			{
				if (right==left+1)
				{
					if (l.get(right).D<=w) left=right;
					else right=left;
				}
				else
				{
					int m=(left+right)/2;
					if (l.get(m).D<=w)
						left=m;
					else right=m-1;
				}
			}
			tot+=l.get(left).P;
		}
		return tot;
	}
}

class Solution826_2
{
	private static class Job implements Comparable<Job>
	{
		public int D,P;
		public Job(int d,int p)
		{
			D=d;
			P=p;
		}
		@Override
		public int compareTo(Job o)
		{
			return D-o.D;
		}
	}
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker)
	{
		int len=difficulty.length;
		BBST<Job> rt=new BBST<Job>(new Job(difficulty[0],profit[0]));
		for (int i=1;i<len;i++)
		{
			Job j=new Job(difficulty[i],profit[i]);
			if (!rt.containData(j)) 
				rt=rt.insert(j);
			else
			{
				Job k=rt.getData(j);
				if (k.P<j.P)
					k.P=j.P;
			}
		}
		List<Job> l=new ArrayList<Job>();
		while (rt!=null)
		{
			Job j=rt.getMinData();
			rt=rt.removeMin();
			if (l.size()==0) l.add(j);
			else
			{
				Job k=l.get(l.size()-1);
				if (k.P<j.P) l.add(j);
			}
			
		}
		int tot=0;
		int minD=l.get(0).D;
		for (int w:worker)
		{
			if (w<minD) continue;
			int left=0,right=l.size()-1;
			while (left<right)
			{
				if (right==left+1)
				{
					if (l.get(right).D<=w) left=right;
					else right=left;
				}
				else
				{
					int m=(left+right)/2;
					if (l.get(m).D<=w)
						left=m;
					else right=m-1;
				}
			}
			tot+=l.get(left).P;
		}
		return tot;
	}
}

public class LC821_830
{
	public static void test826()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input826.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output826.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] difficulty = test.Test.parseIntArr(inLine);
				int[] profit=test.Test.parseIntArr(bfr.readLine());
				int[] worker=test.Test.parseIntArr(bfr.readLine());
				
				Solution826 s=new Solution826();

				int ans = s.maxProfitAssignment(difficulty, profit, worker);
				System.out.println(ans);

				bfw.write(""  + ans);
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
	public static void main(String[] ags)
	{
		test826();
	}

}
