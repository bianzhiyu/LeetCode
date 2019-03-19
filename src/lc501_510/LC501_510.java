package lc501_510;

//507. Perfect Number
//Runtime: 6 ms, faster than 97.28% of Java online submissions for Perfect Number.
//Memory Usage: 36.9 MB, less than 59.38% of Java online submissions for Perfect Number.
class Solution507 
{
	public static int getsqrt(int n)
	{
		int t=(int) Math.sqrt(n);
		if ((t+1)*(t+1)==n) return t+1;
		return t;
	}
    public boolean checkPerfectNumber(int n) 
    {
    	
    	if (n<=2) return false;
    	int sum=1,tmp=getsqrt(n);
    	if (tmp*tmp==n)sum+=tmp;
    	else tmp++;
    	for (int i=2;i<tmp;i++)
    		if (n%i==0) sum+=i+n/i;
    	return sum==n;
    }
}

//509. Fibonacci Number
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
//Memory Usage: 35.3 MB, less than 100.00% of Java online submissions for Fibonacci Number.
class Solution509 {
    public int fib(int n) {
        int[] f=new int[n+3];
        f[0]=0;        f[1]=1;
        for (int i=2;i<=n;i++)
            f[i]=f[i-1]+f[i-2];
        return f[n];
    }
}

public class LC501_510 {
	public static void main(String[] args)
	{
		
	}

}
