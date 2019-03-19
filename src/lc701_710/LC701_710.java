package lc701_710;

//704. Binary Search
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Binary Search.
//Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Binary Search.
class Solution704 {
	int find(int[] a,int tar,int l,int r)
	{
		if (l>r) return -1;
		if (l==r) return a[l]==tar?l:-1;
		if (r==l+1)
		{
			if (a[l]==tar) return l;
			if (a[r]==tar) return r;
			return -1;
		}
		int m=(l+r)/2;
		
		if (a[l]==tar) return l;
		if (a[m]>=tar) return find(a,tar,l,m);
		return find(a,tar,m+1,r);
	}
    public int search(int[] nums, int target) {
        return find(nums,target,0,nums.length-1);
    }
}

public class LC701_710 {
	public static void main(String[] args)
	{
		
	}

}
