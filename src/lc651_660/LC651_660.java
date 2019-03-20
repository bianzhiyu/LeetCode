package lc651_660;

class TreeNode 
{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class SolutionHeap
{
	static void up(int[] a,int l,int p)
	{
		int f,t;
		while (p>0)
		{
			f=(p-1)/2;
			if (a[f]<=a[p])
			{
				t=a[f];a[f]=a[p];a[p]=t;
			}
			else break;
			p=f;
		}
	}
	static void down(int[] a,int l,int p)
	{
		int s,t;
		while (p*2+1<=l)
		{
			s=2*p+1;
			if (2*p+2<=l && a[s]<a[2*p+2])
				s=s*p+2;
			if (a[p]>=a[s]) break;
			else 
			{
				t=a[p];a[p]=a[s];a[s]=t;
			}
			p=s;
		}
	}
	TreeNode Cons(int p,int l,int[] a)
	{
		if (p>l) return null;
		TreeNode r=new TreeNode(a[p]);
		r.left=Cons(2*p+1,l,a);
		r.right=Cons(2*p+2,l,a);
		return r;
	}
    public TreeNode constructMaximumBinaryTree(int[] nums) 
    {
    	int len=nums.length;
        for (int i=0;i<len;i++)
        {
        	up(nums,i,i);
        	down(nums,i,0);
        }
        return Cons(0,len-1,nums);
    }
}

//654. Maximum Binary Tree
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Maximum Binary Tree.
//Memory Usage: 40.5 MB, less than 19.24% of Java online submissions for Maximum Binary Tree.
class Solution654 
{
	TreeNode Cons(int[] a,int l, int r)
	{
		if (l>r) return null;
		int mi=l;
		for (int i=l+1;i<=r;i++)
			if (a[i]>a[mi])
				mi=i;
		TreeNode rt=new TreeNode(a[mi]);
		rt.left=Cons(a,l,mi-1);
		rt.right=Cons(a,mi+1,r);
		return rt;
	}
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return Cons(nums,0,nums.length-1);
    }
}



class MPair implements Comparable<MPair>
{
	int key,ct;
	MPair(int _key,int _ct)
	{
		key=_key;
		ct=_ct;
	}
	@Override
	public int compareTo(MPair o)
	{
		return key-o.key;
	}

}
//class Solution659 
//{
//    public boolean isPossible(int[] nums) 
//    {
//    	BBST<MPair> rt=new BBST<MPair>(new MPair(nums[0],1));
//    	for (int i=1;i<nums.length;i++)
//    	{
//    		MPair p=new MPair(nums[i],1);
//    		if (rt.containData(p))
//    		{
//    			p=rt.getData(p);
//    			p.ct++;
//    		}
//    		else rt=rt.insert(p);
//    	}
//    	while (rt!=null)
//    	{
//    		MPair p1=rt.getMinData();
//    		MPair p2=new MPair(p1.key+1,1);
//    		if (!rt.containData(p2)) return false;
//    		MPair p3=new MPair(p1.key+2,1);
//    		if (!rt.containData(p3)) return false;
//    		p2=rt.getData(p2);
//    		p3=rt.getData(p3);
//    		p1.ct--;
//    		if (p1.ct==0) rt=rt.removeNodeByData(p1);
//    		p2.ct--;
//    		if (p2.ct==0) rt=rt.removeNodeByData(p2);
//    		p3.ct--;
//    		if (p3.ct==0) rt=rt.removeNodeByData(p3);
//    	}
//    	return true;
//    }
//}

public class LC651_660 {
	public static void main(String [] args)
	{
		
	}
}
