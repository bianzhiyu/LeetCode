package lc871_880;

//873. Length of Longest Fibonacci Subsequence
//Runtime: 1519 ms, faster than 1.05% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
//N^3
class Solution873 {
    public int lenLongestFibSubseq(int[] A) {
        int lenA=A.length;
        int [][] f=new int[lenA][lenA];
        int max=0;
        for (int i=lenA-3;i>=0;i--)
        	for (int j=lenA-2;j>=i+1;j--)
        	{
        		for (int k=lenA-1;k>=j+1;k--)
        		{
        			if (A[i]+A[j]==A[k] && f[j][k]+1>=f[i][j])
        				f[i][j]=f[j][k]+1;
        		}
        		if (f[i][j]>max) max=f[i][j];
        	}
        return max>0?max+2:0;
    }
}

//N^2 log N
//Runtime: 116 ms, faster than 22.39% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49.5 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
class Solution873_2 {
	int indexOf(int[] a,int tar,int l,int r)
	{
		if (l>r) return -1;
		if (l==r) return a[l]==tar?l:-1;
		if (r==l+1) {
			if (a[l]==tar) return l;
			if (a[r]==tar) return r;
			return -1;
		}
		int m=(l+r)/2;
		if (a[m]==tar) return m;
		if (a[m]>tar) return indexOf(a,tar,l,m-1);
		if (a[m]<tar) return indexOf(a,tar,m+1,r);
		return -1;
	}
    public int lenLongestFibSubseq(int[] A) {
        int lenA=A.length;
        int [][] f=new int[lenA][lenA];
        int max=0,k;
        for (int i=lenA-3;i>=0;i--)
        	for (int j=lenA-2;j>=i+1;j--)
        	{
        		if ((k=indexOf(A,A[i]+A[j],j+1,lenA-1))!=-1)
        			if (f[j][k]+1>f[i][j])
        				f[i][j]=f[j][k]+1;
        		if (f[i][j]>max) max=f[i][j];
        	}
        return max>0?max+2:0;
    }
}

//Runtime: 86 ms, faster than 49.84% of Java online submissions for Length of Longest Fibonacci Subsequence.
//Memory Usage: 49.1 MB, less than 100.00% of Java online submissions for Length of Longest Fibonacci Subsequence.
class Solution873_3 {
	int indexOf(int[] a,int tar,int l,int r)
	{
		if (l>r) return -1;
		if (l==r) return a[l]==tar?l:-1;
		if (r==l+1) {
			if (a[l]==tar) return l;
			if (a[r]==tar) return r;
			return -1;
		}
		int m=(l+r)/2;
		if (a[m]==tar) return m;
		if (a[m]>tar) return indexOf(a,tar,l,m-1);
		return indexOf(a,tar,m+1,r);
	}
	int fd(int[]a,int tar,int l,int r)
	{
		if (l>r) return -1;
		if (r==l) return a[r]<=tar?r:-1;
		if (r==l+1) 
		{
			if (a[r]<=tar) return r;
			if (a[l]<=tar) return l;
			else return -1;
		}
		int m=(l+r)/2;
		if (a[m]<=tar) return fd(a,tar,m,r);
		return fd(a,tar,l,m-1);
	}
    public int lenLongestFibSubseq(int[] A) {
        int lenA=A.length;
        int [][] f=new int[lenA][lenA];
        int max=0,k;
        for (int i=lenA-3;i>=0;i--)
        {
        	
        	int j=fd(A,A[lenA-1]-A[i],i+1,lenA-1);
        	for (;j>=i+1;j--)
        	{
        		
        		if ((k=indexOf(A,A[i]+A[j],j+1,lenA-1))!=-1)
        			if (f[j][k]+1>f[i][j])
        				f[i][j]=f[j][k]+1;
        		if (f[i][j]>max) max=f[i][j];
        	}
        }
        return max>0?max+2:0;
    }
}
//874. Walking Robot Simulation
//Runtime: 506 ms, faster than 5.04% of Java online submissions for Walking Robot Simulation.
//Memory Usage: 60.4 MB, less than 12.50% of Java online submissions for Walking Robot Simulation.
class Solution874 
{
    public int robotSim(int[] commands, int[][] obstacles) 
    {
    	int[] pos=new int[]{0,0};
    	int direct=0;
    	int[][] ddir=new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
    	int maxdist=0,comlen=commands.length,obstlen=obstacles.length;
    	int nx,ny,nd;
    	for (int i=0;i<comlen;i++)
    	{
    		if (commands[i]==-1)
    		{
    			direct=(direct+1)%4;
    			continue;
    		}
    		if (commands[i]==-2)
    		{
    			direct=(direct+3)%4;
    			continue;
    		}
    		int m=commands[i];
    		for (int j=0;j<obstlen;j++)
    		{
    			if (direct==0 && 
    				pos[0]==obstacles[j][0] &&
    				pos[1]+1<=obstacles[j][1] &&
    				obstacles[j][1]<=pos[1]+m
    					)
    			{
    				m=obstacles[j][1]-pos[1]-1;
    			}
    			else if (direct==1 && 
        				pos[1]==obstacles[j][1] &&
        				pos[0]+1<=obstacles[j][0] &&
        				obstacles[j][0]<=pos[0]+m
        					)
        			{
        				m=obstacles[j][0]-pos[0]-1;
        			}
    			else if (direct==2 && 
        				pos[0]==obstacles[j][0] &&
        				pos[1]-1>=obstacles[j][1] &&
        				obstacles[j][1]>=pos[1]-m
        					)
        			{
        				m=pos[1]-1-obstacles[j][1];
        			}
    			else if (direct==3 && 
        				pos[1]==obstacles[j][1] &&
        				pos[0]-1>=obstacles[j][0] &&
        				obstacles[j][0]>=pos[0]-m
        					)
    			{
    				m=pos[0]-1-obstacles[j][0];
    			}
    		}
    		nx=pos[0]+m*ddir[direct][0];
    		ny=pos[1]+m*ddir[direct][1];
    		nd=nx*nx+ny*ny;
    		if (nd>maxdist) maxdist=nd;
    		pos[0]=nx;
    		pos[1]=ny;
    		
    	}
        return maxdist;
    }
}
public class LC871_880 {
	public static void main(String[]args)
	{

	}
}
