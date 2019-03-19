package dijkstra;

import java.util.List;

public class Dijkstra 
{
	/*
	 * nodenums: the number of nodes.
	 * 	These nodes should be index from 0 to nodenums-1.
	 * startnode: Path starts from it.
	 */
	public static DijkstraResult dijksta(int nodenums,
			List<List<EdgeTo>> LL,
			int startnode)
	{
		int[] dist=new int[nodenums];
		int[] pred=new int[nodenums];
		boolean[] used=new boolean[nodenums];
		int[] q=new int[nodenums];
		for (int i=0;i<nodenums;i++)
			dist[i]=Integer.MAX_VALUE;
		dist[startnode]=0;
		pred[startnode]=-1;
		used[startnode]=true;
		int f=0,r=1;
		q[0]=startnode;
		while (f<r)
		{
			int nt=q[f++];
			for (EdgeTo i:LL.get(nt))
				if (!used[i.Ind] && (long)dist[nt]+i.Weight<(long)dist[i.Ind])
				{
					used[i.Ind]=true;
					dist[i.Ind]=dist[nt]+i.Weight;
					q[r++]=i.Ind;
					pred[i.Ind]=nt;
				}
		}
		return new DijkstraResult(dist,pred);
	}

}
