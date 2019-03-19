package lc891_900;

import java.util.Arrays;

//891. Sum of Subsequence Widths
//Runtime: 5098 ms, faster than 5.35% of Java online submissions for Sum of Subsequence Widths.
//Memory Usage: 43 MB, less than 66.67% of Java online submissions for Sum of Subsequence Widths.
class Solution891 {
    public int sumSubseqWidths(int[] A) {
    	Arrays.parallelSort(A);
    	long sum=0;
    	long modulo=1000000007;
    	for(int i=0;i<A.length;i++)
    	{
    		long base=1;
    		for (int j=i+1;j<A.length;j++)
    		{
    			sum=(sum+(A[j]-A[i])*base)%modulo;
    			base=(base*2)%modulo;
    		}
    	}
    	return (int)sum;
    }
}
// Suppose we have a_0,... a_{n-1}
//Ans=\sum_{i=0,...,n-2} \sum_{j=i+1,...,n-1} (a_j-a_i) * 2^{j-i-1}
//Ans=\sum_{j=1,...,n-1} (2^j-1)*(a_j-a_{n-1-j})

//Runtime: 25 ms, faster than 90.37% of Java online submissions for Sum of Subsequence Widths.
//Memory Usage: 43 MB, less than 66.67% of Java online submissions for Sum of Subsequence Widths.
class Solution891_2 {
    public int sumSubseqWidths(int[] A) {
    	Arrays.parallelSort(A);
    	int n=A.length;
    	long sum=0;
    	long modulo=1000000007;
    	long mul=1;
    	for(int j=1;j<n;j++)
    	{
    		sum=(sum+mul*(A[j]-A[n-1-j]))%modulo;
    		mul=(2*mul+1)%modulo;
    	}
    	return (int)sum;
    }
}

public class LC891_900 {
	public static void main(String[] args)
	{
		
	}

}
