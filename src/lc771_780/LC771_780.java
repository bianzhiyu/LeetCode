package lc771_780;


//773. Sliding Puzzle
//Runtime: 32 ms, faster than 15.94% of Java online submissions for Sliding Puzzle.
//Memory Usage: 49.7 MB, less than 5.43% of Java online submissions for Sliding Puzzle.
class Solution773 {
	void add(int[][]q,int p,int a1,int a2,int a3,int a4,int a5,int a6)
	{
		q[p][0]=a1;q[p][1]=a2;q[p][2]=a3;
		q[p][3]=a4;q[p][4]=a5;q[p][5]=a6;
	}
	int toInt(int[] x)
	{
		return x[5]+x[4]*6+x[3]*36+
				x[2]*216+x[1]*1296+x[0]*7776;
	}
	boolean success(int[] x)
	{
		return x[5]==0 && x[4]==5 && x[3]==4 &&
				x[2]==3 && x[1]==2 && x[0]==1;	
	}
	void swap(int[]x,int a,int b)
	{
		int t=x[a];x[a]=x[b];x[b]=t;
	}
	void copy(int[][]q,int h,int t)
	{
		q[t][0]=q[h][0];  q[t][1]=q[h][1];  q[t][2]=q[h][2];
		q[t][3]=q[h][3];  q[t][4]=q[h][4];  q[t][5]=q[h][5];
	}
	public int slidingPuzzle(int[][] board) {
		int[][] q=new int[50000][6];
		boolean[] used=new boolean[50000];
		int[] step=new int[50000];
		add(q,0,board[0][0],board[0][1],board[0][2],
				board[1][0],board[1][1],board[1][2]);
		used[toInt(new int[] {board[0][0],board[0][1],board[0][2],
				board[1][0],board[1][1],board[1][2]})]=false;
		step[0]=0;
		int head=0,tail=1;
		while (head<tail)
		{
			if (success(q[head])) return step[head];
			if (q[head][0]==0)
			{
				swap(q[head],0,1);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],0,1);

				swap(q[head],0,3);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],0,3);
			}
			else if (q[head][1]==0)
			{
				swap(q[head],1,0);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],1,0);

				swap(q[head],1,2);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],1,2);

				swap(q[head],1,4);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],1,4);
			}
			else if (q[head][2]==0)
			{
				swap(q[head],2,1);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],2,1);

				swap(q[head],2,5);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],2,5);
			}
			else if (q[head][3]==0)
			{
				swap(q[head],3,0);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],3,0);

				swap(q[head],3,4);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],3,4);
			}
			else if (q[head][4]==0)
			{
				swap(q[head],4,1);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],4,1);

				swap(q[head],4,3);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],4,3);

				swap(q[head],4,5);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],4,5);
			}
			else if (q[head][5]==0)
			{
				swap(q[head],5,2);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],5,2);

				swap(q[head],5,4);
				if (!used[toInt(q[head])])
				{
					copy(q,head,tail);
					step[tail]=step[head]+1;
					used[toInt(q[head])]=true;
					tail++;
				}
				swap(q[head],5,4);
			}
			head++;
		}
		return -1;
	}
}

//778. Swim in Rising Water
//Runtime: 28 ms, faster than 76.08% of Java online submissions for Swim in Rising Water.
//Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Swim in Rising Water.
//Using BFS and relaxing thoughts.
class Solution778 {
	int m,n,xs,ys;
	int[][]f,g,q;
	boolean frozen;
	boolean[][] used;
	final static int[][]di= {{1,0},{0,1},{-1,0},{0,-1}};
	void init(int[][]grid)
	{
		g=grid;
		m=g.length;
		n=g[0].length;
		f= new int[m][n];
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				f[i][j]=Integer.MAX_VALUE-1;
		f[0][0]=grid[0][0];
		q=new int[m*n][2];
		used=new boolean[m][n];
	}
	void search()
	{
		frozen=true;
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				for (int k=0;k<4;k++)
				{
					xs=i+di[k][0];
					ys=j+di[k][1];
					if (xs>=0 && xs<m && ys>=0 && ys<n &&
							f[xs][ys]>Math.max(f[i][j], g[xs][ys]))
					{
						f[xs][ys]=Math.max(f[i][j], g[xs][ys]);
						frozen=false;
						return;
					}
				}
	}
	void bfs()
	{
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				used[i][j]=false;
		int h=0,r=1;
		q[0][0]=xs;q[0][1]=ys;
		used[xs][ys]=true;
		int tx,ty;
		while (h<r)
		{
			tx=q[h][0];
			ty=q[h][1];
			for (int k=0;k<4;k++)
			{
				int nx=tx+di[k][0];
				int ny=ty+di[k][1];
				if (nx>=0 && nx<m && ny>=0 && ny<n && !used[nx][ny] &&
						f[nx][ny]>Math.max(f[tx][ty], g[nx][ny]))
				{
					used[nx][ny]=true;
					q[r][0]=nx;
					q[r][1]=ny;
					r++;
					f[nx][ny]=Math.max(f[tx][ty], g[nx][ny]);
				}
			}
			h++;
		}

	}
	public int swimInWater(int[][] grid) {
		if (grid.length==0 || grid[0].length==0) return 0;
		init(grid); 
		search();
		while (!frozen)
		{
			bfs();
			search();
		}
		return f[m-1][n-1];
	}
}

//Runtime: 17 ms, faster than 83.74% of Java online submissions for Swim in Rising Water.
//Memory Usage: 38 MB, less than 100.00% of Java online submissions for Swim in Rising Water.
//Diff:
//Now the bfs allows a node to re-enter the queue after
//it is removed from the queue.
class Solution778_2 {
	int m,n,xs,ys;
	int[][]f,g,q;
	boolean frozen;
	boolean[][] used;
	final static int[][]di= {{1,0},{0,1},{-1,0},{0,-1}};
	void init(int[][]grid)
	{
		g=grid;
		m=g.length;
		n=g[0].length;
		f= new int[m][n];
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				f[i][j]=Integer.MAX_VALUE-1;
		f[0][0]=grid[0][0];
		q=new int[m*n+20][2];
		used=new boolean[m][n];
	}
	void search()
	{
		frozen=true;
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				for (int k=0;k<4;k++)
				{
					xs=i+di[k][0];
					ys=j+di[k][1];
					if (xs>=0 && xs<m && ys>=0 && ys<n &&
							f[xs][ys]>Math.max(f[i][j], g[xs][ys]))
					{
						f[xs][ys]=Math.max(f[i][j], g[xs][ys]);
						frozen=false;
						return;
					}
				}
	}
	void bfs()
	{
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				used[i][j]=false;
		int h=0,r=1;
		q[0][0]=xs;q[0][1]=ys;
		used[xs][ys]=true;
		int tx,ty;
		while (h<r)
		{
			tx=q[h][0];
			ty=q[h][1];
			h=(h+1)%q.length;
			used[tx][ty]=false;
			for (int k=0;k<4;k++)
			{
				int nx=tx+di[k][0];
				int ny=ty+di[k][1];
				if (nx>=0 && nx<m && ny>=0 && ny<n && !used[nx][ny] &&
						f[nx][ny]>Math.max(f[tx][ty], g[nx][ny]))
				{
					used[nx][ny]=true;
					q[r][0]=nx;
					q[r][1]=ny;
					r=(r+1)%q.length;
					f[nx][ny]=Math.max(f[tx][ty], g[nx][ny]);
				}
			}
		}

	}
	public int swimInWater(int[][] grid) {
		if (grid.length==0 || grid[0].length==0) return 0;
		init(grid); 
		search();
		while (!frozen)
		{
			bfs();
			search();
		}
		return f[m-1][n-1];
	}
}

//780. Reaching Points
//java.lang.StackOverflowError, with case
//new Solution780().reachingPoints(35, 13, 455955547, 420098884)
class Solution780 
{
	public boolean reachingPoints(int sx, int sy, int tx, int ty) 
	{
		if (sx>tx || sy>ty) return false;
		if (sx==tx && sy==ty) return true;
		if (reachingPoints(sx+sy,sy,tx,ty)) return true;
		if (reachingPoints(sx,sy+sx,tx,ty)) return true;
		return false;
	}
}

//Runtime: 6 ms, faster than 79.79% of Java online submissions for Reaching Points.
//Memory Usage: 36.9 MB, less than 87.04% of Java online submissions for Reaching Points.
class Solution780_2
{
	public boolean reachingPoints(int sx, int sy, int tx, int ty) 
	{
		if (sx>tx || sy>ty) return false;
		if (sx==tx && sy==ty) return true;
		//Without the following 2 statement,
		//java.lang.StackOverflowError, with case
		//new Solution780().reachingPoints(1, 1, 1000000000, 0)
		if (sx==tx) return (ty-sy)%sx==0;
		if (sy==ty) return (tx-sx)%sy==0;
		if (tx>ty)
		{
			if (reachingPoints(sx,sy,tx-ty,ty)) return true;
			return false;
		}
		if (ty>tx)
		{
			if (reachingPoints(sx,sy,tx,ty-tx)) return true;
			return false;
		}
		//tx==ty
		if (sx==0 && sy==ty) return true;
		if (sx==tx && sy==0) return true;
		return false;
	}
}
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Reaching Points.
//Memory Usage: 37.2 MB, less than 5.55% of Java online submissions for Reaching Points.
class Solution780_3
{
	public boolean reachingPoints(int sx, int sy, int tx, int ty) 
	{
		while (true)
		{
			if (sx>tx || sy>ty) return false;
			if (sx==tx && sy==ty) return true;
			//Without the following 2 statement,
			//java.lang.StackOverflowError, with case
			//new Solution780().reachingPoints(1, 1, 1000000000, 0)
			if (sx==tx)	return (ty-sy)%sx==0;
			if (sy==ty) return (tx-sx)%sy==0;
			if (tx>ty)
			{
				tx-=ty;
				continue;
			}
			if (ty>tx)
			{
				ty-=tx;
				continue;
			}
			//tx==ty
			if (sx==0 && sy==ty) return true;
			if (sx==tx && sy==0) return true;
			return false;
		}

	}
}
public class LC771_780 {
	public static void main(String[] args)
	{
		System.out.println(new Solution780_2()
				.reachingPoints(3,3,12,9));
	}

}
