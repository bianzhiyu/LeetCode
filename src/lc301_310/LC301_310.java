package lc301_310;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import dijkstra.*;
import treeCodec.*;

///303. Range Sum Query - Immutable
//You may assume that the array does not change.
/**
 * Your NumArray object will be instantiated and called as such: NumArray obj =
 * new NumArray(nums); int param_1 = obj.sumRange(i,j);
 */

class NumArray
{
	int[] sum;

	public NumArray(int[] nums)
	{
		if (nums.length != 0)
		{
			sum = new int[nums.length];
			sum[0] = nums[0];
			for (int i = 1; i < nums.length; i++)
			{
				sum[i] = sum[i - 1] + nums[i];
			}
		}
	}

	public int sumRange(int i, int j)
	{
		return (i == 0 ? sum[j] : sum[j] - sum[i - 1]);
	}
}

//304. Range Sum Query 2D - Immutable
//Runtime: 58 ms, faster than 100.00% of Java online submissions for Range Sum Query 2D - Immutable.
//Memory Usage: 46.9 MB, less than 32.26% of Java online submissions for Range Sum Query 2D - Immutable.
class NumMatrix
{
	int m, n;
	int[][] ps;
	boolean isNull;

	public NumMatrix(int[][] matrix)
	{
		if (matrix.length == 0 || matrix[0].length == 0)
		{
			isNull = true;
			return;
		} else
			isNull = false;
		m = matrix.length;
		n = matrix[0].length;
		ps = new int[m][n];
		ps[0][0] = matrix[0][0];
		for (int i = 1; i < n; i++)
			ps[0][i] = ps[0][i - 1] + matrix[0][i];
		for (int i = 1; i < m; i++)
		{
			ps[i][0] = ps[i - 1][0] + matrix[i][0];
			for (int j = 1; j < n; j++)
				ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i][j];
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2)
	{
		if (isNull)
			return 0;
		if (row1 == 0)
		{
			if (col1 == 0)
				return ps[row2][col2];
			else
				return ps[row2][col2] - ps[row2][col1 - 1];
		}
		if (col1 == 0)
			return ps[row2][col2] - ps[row1 - 1][col2];
		else
			return ps[row2][col2] + ps[row1 - 1][col1 - 1] - ps[row2][col1 - 1] - ps[row1 - 1][col2];
	}
}

/**
 * Your NumMatrix object will be instantiated and called as such: NumMatrix obj
 * = new NumMatrix(matrix); int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */


//306. Additive Number
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Additive Number.
//Memory Usage: 34.2 MB, less than 73.33% of Java online submissions for Additive Number.
class Solution306
{
	boolean check(String num,int len1,int len2)
	{
		if (len1>1 && num.charAt(0)=='0') return false;
		if (len2>1 && num.charAt(len1)=='0') return false;
		int len=num.length();
		int[] n1=new int[len+2],n2=new int[len+2];
		for (int i=0;i<len1;i++)
			n1[len1-i-1]=num.charAt(i)-'0';
		for (int i=0;i<len2;i++)
			n2[len2-i-1]=num.charAt(len1+i)-'0';
		int readpos=len1+len2;
		int digitlen1=len1,digitlen2=len2,digitlen3=0;
		int []n3=new int[len+2];
		while (readpos<len)
		{
			digitlen3=Math.max(digitlen1, digitlen2);
			for (int i=0;i<digitlen3;i++)
			{
				n3[i]=0;
				if (i<digitlen1) n3[i]+=n1[i];
				if (i<digitlen2) n3[i]+=n2[i]; 
			}
			for (int i=0;i<digitlen3-1;i++)
			{
				n3[i+1]+=n3[i]/10;
				n3[i]%=10;
			}
			while (n3[digitlen3-1]>9)
			{
				n3[digitlen3]=n3[digitlen3-1]/10;
				n3[digitlen3-1]%=10;
				digitlen3++;
			}
			if (readpos+digitlen3>num.length()) return false;
			for (int i=0;i<digitlen3;i++)
				if (num.charAt(readpos+i)-'0'!=n3[digitlen3-1-i]) return false;
			readpos+=digitlen3;
			for (int i=0;i<digitlen2;i++)
				n1[i]=n2[i];
			digitlen1=digitlen2;
			for (int i=0;i<digitlen3;i++)
				n2[i]=n3[i];
			digitlen2=digitlen3;
			
		}
		return true;
	}
    public boolean isAdditiveNumber(String num) 
    {
    	int len=num.length();
    	for (int i=1;i<=len-2;i++)
    		for (int j=1;j<=len-i-1;j++)
    			if (check(num,i,j)) return true;
    	return false;
    }
}



//307. Range Sum Query - Mutable
//Runtime: 235 ms, faster than 15.81% of Java online submissions for Range Sum Query - Mutable.
//Memory Usage: 61.9 MB, less than 23.70% of Java online submissions for Range Sum Query - Mutable.
class NumArray307
{
	int[] ns;
	int[] ps;
	public NumArray307(int[] nums)
	{
		ns=nums;
		ps=new int[nums.length+1];
		ps[0]=0;
		for (int i=1;i<=nums.length;i++)
			ps[i]=ps[i-1]+nums[i-1];
	}

	public void update(int i, int val)
	{
		ns[i]=val;
		for (int j=i+1;j<=ns.length;j++)
			ps[j]=ps[j-1]+ns[j-1];
	}

	public int sumRange(int i, int j)
	{
		return ps[j+1]-ps[i];
	}
}

//307. Range Sum Query - Mutable
//Use so called segment tree.
//Runtime: 58 ms, faster than 95.20% of Java online submissions for Range Sum Query - Mutable.
//Memory Usage: 48.6 MB, less than 66.35% of Java online submissions for Range Sum Query - Mutable.
class NumArray307_2
{
	protected TreeNode rt;
	protected int d;
	
	protected void genTree(TreeNode rt,int sp)
	{
		if (sp==d) return;
		rt.left=new TreeNode(0);
		rt.right=new TreeNode(0);
		genTree(rt.left,sp+1);
		genTree(rt.right,sp+1);
	}
	protected void initVal(TreeNode tn,int[]nums,int sp,int ind)
	{
		if (d==sp)
		{
			if (ind<nums.length) tn.val=nums[ind];
			return;
		}
		initVal(tn.left,nums,sp+1,ind*2);
		initVal(tn.right,nums,sp+1,ind*2+1);
	}
	protected void initSum(TreeNode tn,int sp)
	{
		if (sp==d) return;
		initSum(tn.left,sp+1);
		initSum(tn.right,sp+1);
		tn.val=tn.left.val+tn.right.val;
	}
	public NumArray307_2(int[] nums)
	{
		d=0;
		int t=1;
		while (t<nums.length)
		{
			d++;
			t<<=1;
		}
		rt=new TreeNode(0);
		genTree(rt,0);
		initVal(rt,nums,0,0);
		initSum(rt,0);
	}

	protected void updateData(int[] path,int val,int sp,TreeNode tn)
	{
		if (sp==d)
		{
			tn.val=val;
			return;
		}
		if (path[d-sp-1]==0) updateData(path,val,sp+1,tn.left);
		else updateData(path,val,sp+1,tn.right);
		tn.val=tn.left.val+tn.right.val;
	}
	protected int[] toPath(int x)
	{
		int[] path=new int[d];
		for (int i=0;i<d;i++)
		{
			path[i]=x%2;
			x/=2;
		}
		return path;
	}
	public void update(int i, int val)
	{
		updateData(toPath(i),val,0,rt);
	}
	protected int getSum(int[] path,int sp,TreeNode tn)
	{
		if (sp==d)
		{
			return tn.val;
		}
		if (path[d-sp-1]==1) return tn.left.val+getSum(path,sp+1,tn.right);
		else return getSum(path,sp+1,tn.left);
	}
	public int sumRange(int i, int j)
	{
		return getSum(toPath(j),0,rt)-
				(i==0?0:getSum(toPath(i-1),0,rt));
	}
}



/**
 * Your NumArray object will be instantiated and called as such: NumArray obj =
 * new NumArray(nums); obj.update(i,val); int param_2 = obj.sumRange(i,j);
 */
//310. Minimum Height Trees
//Time Limit Exceeded
//O(N^3)
class Solution310
{
	public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				d[i][j] = 1000000000;
		for (int i = 0; i < edges.length; i++)
		{
			d[edges[i][0]][edges[i][1]] = 1;
			d[edges[i][1]][edges[i][0]] = 1;
		}
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (d[i][k] + d[k][j] < d[i][j])
						d[i][j] = d[i][k] + d[k][j];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				System.out.print(d[i][j]);
			System.out.println();
		}
		int[] maxd = new int[n];
		int Min = 1000000000;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				if (d[i][j] > maxd[i] && j != i)
					maxd[i] = d[i][j];
			if (maxd[i] < Min)
				Min = maxd[i];
		}
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			if (maxd[i] == Min)
				ans.add(i);
		return ans;
	}
}

//O(N^2)
//Time Limit Exceeded
class Solution310_2
{
	final static boolean __debug = false;
	List<Integer> ans;
	List<List<EdgeTo>> LL;
	int n;
	int[] dist0, father;
	List<HashMap<Integer, Integer>> d;

	void init(int n, int[][] edges)
	{
		ans = new ArrayList<Integer>();
		LL = new ArrayList<List<EdgeTo>>(n);
		this.n = n;
		for (int i = 0; i < n; i++)
			LL.add(new LinkedList<EdgeTo>());
		for (int i = 0; i < edges.length; i++)
		{
			LL.get(edges[i][0]).add(new EdgeTo(edges[i][1], 1));
			LL.get(edges[i][1]).add(new EdgeTo(edges[i][0], 1));
		}
	}

	void SetSons(int rt)
	{
		for (EdgeTo i : LL.get(rt))
			if (i.Ind != father[rt])
			{
				father[i.Ind] = rt;
				SetSons(i.Ind);
			}

	}

	void SetTree()
	{
		father = new int[n];
		SetSons(0);
	}

	void setDistFromNodeToTree(int nd, int rt, int distFromNdToRt)
	{
		d.get(nd).put(rt, distFromNdToRt);
		d.get(rt).put(nd, distFromNdToRt);
		for (EdgeTo i : LL.get(rt))
			if (i.Ind != father[rt])
				setDistFromNodeToTree(nd, i.Ind, distFromNdToRt + i.Weight);
	}

	void setDistBetweenTrees(int rt1, int rt2, int rtdist)
	{
		setDistFromNodeToTree(rt1, rt2, rtdist);
		for (EdgeTo i : LL.get(rt1))
			if (i.Ind != father[rt1])
				setDistBetweenTrees(i.Ind, rt2, rtdist + i.Weight);
	}

	void SetDistInBetweenSubtrees(int rt)
	{
		for (int i = 0; i < LL.get(rt).size(); i++)
			for (int j = 0; j < LL.get(rt).size(); j++)
				if (i != j && LL.get(rt).get(i).Ind != father[rt] && LL.get(rt).get(j).Ind != father[rt])
					setDistBetweenTrees(LL.get(rt).get(i).Ind, LL.get(rt).get(j).Ind,
							LL.get(rt).get(i).Weight + LL.get(rt).get(j).Weight);
		for (EdgeTo i : LL.get(rt))
			if (i.Ind != father[rt])
				SetDistInBetweenSubtrees(i.Ind);
	}

	void SetAllDistFromRootToSubNode(int rt)
	{
		setDistFromNodeToTree(rt, rt, 0);
		for (EdgeTo i : LL.get(rt))
			if (i.Ind != father[rt])
				SetAllDistFromRootToSubNode(i.Ind);
	}

	void SetDist()
	{
		d = new ArrayList<HashMap<Integer, Integer>>(n);
		for (int i = 0; i < n; i++)
			d.add(new HashMap<Integer, Integer>());

		SetAllDistFromRootToSubNode(0);
		SetDistInBetweenSubtrees(0);
	}

	void GetAns()
	{
		int[] maxd = new int[n];
		int Min = 1000000000;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				if (d.get(i).get(j) > maxd[i] && j != i)
					maxd[i] = d.get(i).get(j);
			if (maxd[i] < Min)
				Min = maxd[i];
		}
		if (__debug)
		{
			for (int i = 0; i < n; i++)
				System.out.print(maxd[i] + " ");
			System.out.println();
		}
		ans = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
			if (maxd[i] == Min)
				ans.add(i);
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		init(n, edges);
		SetTree();
		SetDist();
		if (__debug)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					System.out.print(d.get(i).get(j) + " ");
				System.out.println();
			}
		}
		GetAns();
		return ans;
	}
}

//https://leetcode.com/problems/minimum-height-trees/discuss/76052/Two-O(n)-solutions
//His observation is profound.
//Runtime: 17 ms, faster than 86.44% of Java online submissions for Minimum Height Trees.
//Memory Usage: 51.5 MB, less than 42.17% of Java online submissions for Minimum Height Trees.
class Solution310_3
{
	List<List<EdgeTo>> LL;
	int n;

	void init(int n, int[][] edges)
	{
		LL = new ArrayList<List<EdgeTo>>(n);
		this.n = n;
		for (int i = 0; i < n; i++)
			LL.add(new LinkedList<EdgeTo>());
		for (int i = 0; i < edges.length; i++)
		{
			LL.get(edges[i][0]).add(new EdgeTo(edges[i][1], 1));
			LL.get(edges[i][1]).add(new EdgeTo(edges[i][0], 1));
		}
	}

	void getPath(List<Integer> path, int sp, int start, int end, DijkstraResult r)
	{
		path.add(end);
		if (end == start)
			return;
		getPath(path, sp + 1, start, r.pred[end], r);
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges)
	{
		init(n, edges);
		DijkstraResult r = Dijkstra.dijksta(n, LL, 0);
		int max = 0, maxi = 0;
		for (int i = 0; i < n; i++)
			if (r.dist[i] > max)
			{
				max = r.dist[i];
				maxi = i;
			}
		r = Dijkstra.dijksta(n, LL, maxi);
		int nmaxi = 0;
		for (int i = 0; i < n; i++)
			if (r.dist[i] > max)
			{
				max = r.dist[i];
				nmaxi = i;
			}
		List<Integer> path = new ArrayList<Integer>();
		getPath(path, 0, maxi, nmaxi, r);
		int s = path.size();
		List<Integer> ans = new ArrayList<Integer>();
		if (s % 2 == 1)
			ans.add(path.get(s / 2));
		else
		{
			ans.add(path.get(s / 2));
			ans.add(path.get(s / 2 - 1));
		}
		return ans;
	}
}

public class LC301_310
{
	public static void main(String[] args)
	{
		Solution306 s=new Solution306();
		System.out.println(s.isAdditiveNumber("199001200"));
	}

}
