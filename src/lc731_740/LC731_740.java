package lc731_740;

//739. Daily Temperatures
//Runtime: 5 ms, faster than 99.98% of Java online submissions for Daily Temperatures.
//Memory Usage: 44.1 MB, less than 44.53% of Java online submissions for Daily Temperatures.
class Solution739
{
	public int[] dailyTemperatures(int[] T)
	{
		int len=T.length;
		int[] next=new int[len];
		for (int i=len-2;i>=0;i--)
		{
			int j=i+1;
			while (j!=0 && T[j]<=T[i])
				j=next[j];
			next[i]=j;
		}
		for (int i=len-1;i>=0;i--)
			if (next[i]>0) next[i]=next[i]-i;
		return next;
	}
}

public class LC731_740
{
	public static void main(String[] agrs)
	{

	}
}
