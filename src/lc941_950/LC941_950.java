package lc941_950;

import java.util.Arrays;

//942. DI String Match
//Runtime: 5 ms, faster than 99.66% of Java online submissions for DI String Match.
//Memory Usage: 38.6 MB, less than 46.74% of Java online submissions for DI String Match.
class Solution942
{
    public int[] diStringMatch(String S) 
    {
        int upperbound=S.length(),lowerbound=0;
        int[] a=new int[upperbound+1];
        for (int i=0;i<a.length-1;i++)
        {
        	if (S.charAt(i)=='I') 
        	{
        		a[i]=lowerbound;
        		lowerbound++;
        	}
        	else 
        	{
        		a[i]=upperbound;
        		upperbound--;
        	}
        }
        a[a.length-1]=lowerbound;
        return a;
    }
}
//944. Delete Columns to Make Sorted
//Runtime: 11 ms, faster than 94.76% of Java online submissions for Delete Columns to Make Sorted.
//Memory Usage: 39.9 MB, less than 54.82% of Java online submissions for Delete Columns to Make Sorted.
class Solution944 
{
    public int minDeletionSize(String[] A) 
    {
        
        int n=A.length,m=A[0].length();
        int d=0;
        for (int i=0;i<m;i++)
        {
            for (int j=0;j<n-1;j++)
            {
                if (A[j].charAt(i)>A[j+1].charAt(i))
                {
                    d++;
                    break;
                }
            }
        }
        return d;
    }
}
//945. Minimum Increment to Make Array Unique
//Runtime: 20 ms, faster than 81.53% of Java online submissions for Minimum Increment to Make Array Unique.
//Memory Usage: 45.6 MB, less than 81.25% of Java online submissions for Minimum Increment to Make Array Unique.
class Solution945 
{
    public int minIncrementForUnique(int[] A) 
    {
    	if (A.length==0) return 0;
    	Arrays.parallelSort(A);
    	int min=A[0],m=0;
    	for (int i=0;i<A.length;i++)
    	{
    		if (A[i]<=min)
    		{
    			m+=min-A[i];
    			min++;
    		}
    		else
    		{
    			min=A[i]+1;
    		}
    	}
    	return m;
    }
}
public class LC941_950 {
	public static void main(String[] args)
	{
		System.out.println(new Solution945().minIncrementForUnique(
				new int[] {0,2,2}
				));
	}

}
