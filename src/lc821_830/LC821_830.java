package lc821_830;


//821. Shortest Distance to a Character
//Runtime: 4 ms, faster than 95.67% of Java online submissions for Shortest Distance to a Character.
//Memory Usage: 38 MB, less than 38.51% of Java online submissions for Shortest Distance to a Character.
class Solution821
{
    public int[] shortestToChar(String S, char C) 
    {
        int n=S.length();
        int[] md=new int[n];
        for (int i=0;i<n;i++) md[i]=Integer.MAX_VALUE;
        for(int i=0;i<n;i++)
        {
        	if (S.charAt(i)==C)
        	{
        		md[i]=0;
        		for (int j=i-1;j>=0;j--)
        			if (S.charAt(j)==C || md[j]<=i-j)
        				break;
        			else md[j]=i-j;
        		for (int j=i+1;j<n;j++)
        			if (S.charAt(j)==C) break;
        			else md[j]=j-i;
        	}
        }
        return md;
    }
}

public class LC821_830 {
	public static void main(String[] ags)
	{
		int[] a=new Solution821().
				shortestToChar("loveleetcode", 'e');
		for (int i:a)
			System.out.print(i+" ");
	}

}
