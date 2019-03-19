package lc791_800;


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

//793. Preimage Size of Factorial Zeroes Function
//Runtime: 61 ms, faster than 50.12% of Java online submissions for Binary Search Tree Iterator.
//Memory Usage: 45 MB, less than 97.29% of Java online submissions for Binary Search Tree Iterator.
class LN
{
	int val;
	LN next;
	public LN(int x) {val=x;}
	public LN() {}
}
class BSTIterator {
	LN head;
	
	private LN Trav(TreeNode root,LN p)
	{
		if (root==null) return p;
		p=Trav(root.left,p);
		p.next=new LN(root.val);
		p=p.next;
		p=Trav(root.right,p);
		return p;
	}

    public BSTIterator(TreeNode root) {
        head=new LN();
        Trav(root,head);
    }
    
    /** @return the next smallest number */
    public int next() {
        head=head.next;
        if (head !=null) return head.val;
        return 0;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return head.next!=null;
    }
}



//799. Champagne Tower
//Runtime: 14 ms, faster than 62.66% of Java online submissions for Champagne Tower.
//Memory Usage: 38.1 MB, less than 11.87% of Java online submissions for Champagne Tower.
class Solution799 
{
    public double champagneTower(int poured, int query_row, int query_glass) 
    {
        double p=((double)poured);
        double[][] rm=new double [query_row+2][2];
        rm[0][0]=p;
        int pre=0;
        for (int i=0;i<=query_row;i++)
        {
        	
        	boolean nothingrm=true;
        	for (int j=0;j<=i;j++)
        	{
        		if (rm[j][pre]>1)
        		{
        			nothingrm=false;
        			rm[j][1-pre]+=(rm[j][pre]-1)/2;
        			rm[j+1][1-pre]+=(rm[j][pre]-1)/2;
        			rm[j][pre]=1;
        		}
        		if (i==query_row && j==query_glass)
        			return rm[j][pre];
        	}
        	if (nothingrm) return 0;
        	pre=1-pre;
        	for (int j=0;j<=i+1;j++)
        		rm[j][1-pre]=0;
        }
        return 1;
    }
}

public class LC791_800 {
	public static void main(String[] args)
	{
//		System.out.println(new Solution793().preimageSizeFZF(5));
	}

}
