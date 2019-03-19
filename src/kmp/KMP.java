package kmp;

////28. Implement strStr()
public class KMP
{
	public static int[] getNext2(String ps) {
		char[] p = ps.toCharArray();
		int[] next = new int[p.length];
		next[0] = 0;
		if (p.length>1) next[1]=0;
		for (int i=2;i<ps.length();i++)
		{
			int y=next[i-1];
			while (y>0 && p[i-1]!=p[y]) y=next[y];
			if (p[i-1]==p[y]) next[i]=y+1;			
			else next[i]=0;
		}
		return next;
	}
	public static int strStr(String S, String P) 
	{
		if (P.length()==0) return 0;
		if (S.length()==0) return -1;
		int []F=getNext2(P);
		int i=0, j=0,SL=S.length(),PL=P.length();
		while (i<=SL)
		{
			if (j==PL) return i-PL;
			if (i==SL) return -1;
			if (S.charAt(i)==P.charAt(j))
			{
				i++;j++;
			} 
			else if (j<=0)
			{
				i++;j=0;
			}
			else
			{
				j=F[j];
			}
		}
		return -1;
	}

}
