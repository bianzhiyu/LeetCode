package dijkstra;

import java.util.List;

//743. Network Delay Time
public class Dijkstra 
{
	/*
	 * nodenums: the number of nodes.
	 * 	These nodes should be index from 0 to nodenums-1.
	 * startnode: Path starts from it.
	 */
	public static DijkstraResult dijksta(int nodeNum,
			List<List<EdgeTo>> LL,
			int startnode)
	{
		int[] dist=new int[nodeNum];
		int[] pred=new int[nodeNum];
		boolean[] used=new boolean[nodeNum];
		for (int i=0;i<nodeNum;i++)
			dist[i]=Integer.MAX_VALUE;
		dist[startnode]=0;
		pred[startnode]=-1;
		for (int i=0;i<nodeNum;i++)
		{
			int minD=Integer.MAX_VALUE;
			int minN=0;
			for (int j=0;j<nodeNum;j++)
				if (!used[j] && dist[j]<minD)
				{
					minD=dist[j];
					minN=j;
				}
			used[minN]=true;
			for (EdgeTo e:LL.get(minN))
			{
				if (minD+e.Weight<dist[e.Ind])
				{
					dist[e.Ind]=minD+e.Weight;
					pred[e.Ind]=minN;
				}
			}
		}
		return new DijkstraResult(dist,pred);
	}

}
