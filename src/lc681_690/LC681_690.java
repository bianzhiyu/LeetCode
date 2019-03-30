package lc681_690;

import java.util.HashMap;

//684. Redundant Connection
//Runtime: 4 ms, faster than 36.63% of Java online submissions for Redundant Connection.
//Memory Usage: 38.5 MB, less than 75.09% of Java online submissions for Redundant Connection.
class Solution684
{
	private HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();

	private int getTop(int x)
	{
		int p = x;
		while (father.get(p) != p)
			p = father.get(p);
		while (father.get(x) != p)
		{
			int q = father.get(x);
			father.put(x, p);
			x = q;
		}
		return p;
	}

	private void merge(int u, int v)
	{
		int av = getTop(v);
		int au = getTop(u);
		father.put(av, au);
	}

	public int[] findRedundantConnection(int[][] edges)
	{
		for (int i = 0; i < edges.length; i++)
		{
			int u = edges[i][0];
			int v = edges[i][1];
			if (!father.containsKey(u))
				father.put(u, u);
			if (!father.containsKey(v))
				father.put(v, v);
			int au = getTop(u);
			int av = getTop(v);
			if (au != av)
				merge(u, v);
			else
				return edges[i];
		}
		return null;
	}
}

//688. Knight Probability in Chessboard
//Runtime: 20 ms, faster than 11.76% of Java online submissions for Knight Probability in Chessboard.
//Memory Usage: 38.2 MB, less than 7.38% of Java online submissions for Knight Probability in Chessboard.
class Solution688
{
	private static final int[][] di=new int[][] {{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,-1},{-2,1}};
	private HashMap<Integer,Double>record=new HashMap<Integer,Double>();
	
	private double dfs(int N,int remStep,int r,int c,double p)
	{
		if (remStep==0) return 0;
		if (p==0) return 0;
		int state=r*26*101+c*101+remStep;
		if (record.containsKey(state)) return p*record.get(state);
		double accu=0;
		for (int i=0;i<8;i++)
		{
			int nx=r+di[i][0];
			int ny=c+di[i][1];
			if (nx>=0 && ny>=0 && nx<N && ny<N)
			{
				accu+=dfs(N,remStep-1,nx,ny,p/8);
			}
			else
			{
				accu+=p/8;
			}
		}
		record.put(state,accu/p);
		return accu;
	}
	public double knightProbability(int N, int K, int r, int c)
	{
		double outP=dfs(N,K,r,c,1);
		return 1-outP;
	}
}

public class LC681_690
{
	public static void test688()
	{
		Solution688 s=new Solution688();
		double ans;
		ans=s.knightProbability(3, 2, 0, 0);
		ans=s.knightProbability(8, 30, 6, 4);
		System.out.println(ans);
	}
	public static void main(String[] args)
	{
		test688();
	}
}
