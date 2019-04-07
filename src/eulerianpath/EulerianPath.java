package eulerianpath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EulerianPath
{
	private static void dfsDirected(LinkedList<Integer> path,
			int N,List<List<Integer>> graph,
			List<Integer> remEdgeNums,
			int nodeInd
			)
	{
		int i;
		while ((i=remEdgeNums.get(nodeInd))>0)
		{
			int next=graph.get(nodeInd).get(i-1);
			remEdgeNums.set(nodeInd, i-1);
			dfsDirected(path,N,graph,remEdgeNums,next);
			path.addFirst(next);
		}
	}
	
	/**
	 * @param N
	 * Total node numbers, which are index from 0 to N-1.
	 * @param graph
	 * Graph is a directed graph.
	 * An edge like i-> j can appear more than once.
	 * Link table:
	 * For List<Integer> l=graph.get(i),
	 * each integer x in l means there is an edge i->x.
	 * @return
	 * A path, travel all edge once,
	 * indicated as travel between nodes in order.
	 * The graph inputed should guarantee this path exits.
	 * Otherwise what this function returns can not be expected.
	 */
	public static LinkedList<Integer> getPathDirectedGraph(int N,List<List<Integer>> graph)
	{
		List<Integer> remEdgeNums=new ArrayList<Integer>(N);
		for (int i=0;i<N;i++)
			remEdgeNums.add(graph.get(i).size());
		LinkedList<Integer> path=new LinkedList<Integer>();
		dfsDirected(path,N,graph,remEdgeNums,0);
		path.addFirst(0);
		return path;
	}
	
	
	private static void removeEdge(List<List<Integer>> graph,
			List<Integer> remEdgeNums,
			int start,int end)
	{
		List<Integer> ls=graph.get(start);
		int edgnum=remEdgeNums.get(start),ind=-1;
		for (int i=0;i<edgnum;i++)
			if (ls.get(i)==end)
			{
				ind=i;break;
			}
		if (ind!=edgnum-1) ls.set(ind, ls.get(edgnum-1));
		remEdgeNums.set(start, edgnum-1);
	}
	private static void dfsUndirected(LinkedList<Integer> path,
			int N,List<List<Integer>> graph,
			List<Integer> remEdgeNums,
			int nodeInd
			)
	{
		int i;
		while ((i=remEdgeNums.get(nodeInd))>0)
		{
			int next=graph.get(nodeInd).get(i-1);
			remEdgeNums.set(nodeInd, i-1);
			removeEdge(graph,remEdgeNums,next,nodeInd);
			
			dfsUndirected(path,N,graph,remEdgeNums,next);
			path.addFirst(next);
		}
	}
	
	/**
	 * @param N
	 * Total node numbers, which are index from 0 to N-1.
	 * @param edges
	 * The graph is a undirected graph.
	 * All its edges are contained in edges
	 * Edge number= edges.length,
	 * edges[i] is of type int[2].
	 * An edge like i - j can appear more than once.
	 * One can go from i to j through edge i - j, 
	 * or from j to i through the same edge.
	 * But both way will consume this edge.
	 * And one edge can be only used once.
	 * @return
	 * A path, travel all edge once,
	 * indicated as travel between nodes in order.
	 * The graph inputed should guarantee this path exits.
	 * Otherwise what this function returns can not be expected.
	 * The graph should only contain 0 or 2 odd degree nodes.
	 */
	public static LinkedList<Integer> getPathUndirectedGraph(int N,int[][] edges)
	{
		List<List<Integer>> lls=new ArrayList<List<Integer>>(N);
		int[] deg=new int[N];
		for (int i=0;i<N;i++)
			lls.add(new ArrayList<Integer>());
		for (int[] b:edges)
		{
			lls.get(b[0]).add(b[1]);
			lls.get(b[1]).add(b[0]);
			deg[b[0]]++;
			deg[b[1]]++;
		}
		
		int oddNodeNum=0,oddNodeInd=0;
		for (int i=0;i<N;i++)
		{
			if (deg[i]%2!=0)
			{
				oddNodeNum++;
				oddNodeInd=i;
			}
		}
		
		if (oddNodeNum!=2 && oddNodeNum!=0) return null;
		
		List<Integer> remEdgeNums=new ArrayList<Integer>(N);
		for (int i=0;i<N;i++)
			remEdgeNums.add(lls.get(i).size());
		LinkedList<Integer> path=new LinkedList<Integer>();
		dfsUndirected(path,N,lls,remEdgeNums,oddNodeInd);
		path.addFirst(oddNodeInd);
		return path;
	}
}
