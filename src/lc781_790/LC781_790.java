package lc781_790;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//781. Rabbits in Forest
//Runtime: 3 ms, faster than 93.87% of Java online submissions for Rabbits in Forest.
//Memory Usage: 37.6 MB, less than 17.65% of Java online submissions for Rabbits in Forest.
class Solution781
{
	public int numRabbits(int[] answers)
	{
		HashMap<Integer, Integer> numCt = new HashMap<Integer, Integer>();
		for (int i = 0; i < answers.length; i++)
			numCt.put(answers[i], numCt.getOrDefault(answers[i], 0) + 1);
		int tot = 0;
		for (int i : numCt.keySet())
		{
			int ct = numCt.get(i);
			tot += ((ct - 1) / (i + 1) + 1) * (i + 1);
		}
		return tot;
	}
}

//785. Is Graph Bipartite?
//Runtime: 4 ms, faster than 90.02% of Java online submissions for Is Graph Bipartite?.
//Memory Usage: 40.6 MB, less than 15.51% of Java online submissions for Is Graph Bipartite?.
class Solution785
{
	public boolean isBipartite(int[][] graph)
	{
		int n = graph.length;
		int[] state = new int[n];
		int[] q = new int[n];
		int h = 0, r = 0, nt = 0;
		for (int i = 0; i < n; i++)
		{
			if (state[i] == 0)
			{
				q[0] = i;
				h = 0;
				r = 1;
				state[i] = 1;
				while (h < r)
				{
					nt = q[h];
					h++;
					for (int k = 0; k < graph[nt].length; k++)
					{
						if (state[graph[nt][k]] == 0)
						{
							state[graph[nt][k]] = 3 - state[nt];
							q[r] = graph[nt][k];
							r++;
						} else if (state[graph[nt][k]] + state[nt] != 3)
							return false;
					}
				}
			}
		}

		return true;

	}
}

// 786. K-th Smallest Prime Fraction
class BST_786
{
	private int height;
	private int x, y;
	private BST_786 left, right;

	public BST_786(int x1, int y1)
	{
		x = x1;
		y = y1;
		height = 1;
	}

	private void setLeft(BST_786 nl)
	{
		left = nl;
	}

	private void setRight(BST_786 nr)
	{
		right = nr;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	private boolean bigger(int x1, int y1)
	{
		return x * y1 - x1 * y > 0;
	}

	private boolean smaller(int x1, int y1)
	{
		return x * y1 - x1 * y < 0;
	}

	private boolean sameVal(int x1, int y1)
	{
		return x * y1 - x1 * y == 0;
	}

	private int getLeftHeight()
	{
		if (left != null)
			return left.height;
		else
			return 0;
	}

	private int getRightHeight()
	{
		if (right != null)
			return right.height;
		else
			return 0;
	}

	/**
	 * return the root of the right rotated tree
	 */
	private BST_786 rightRotate()

	{
		if (left == null)
			return this;
		BST_786 tmpleft = left;
		setLeft(tmpleft.right);
		tmpleft.setRight(this);
		this.updateHeight();
		tmpleft.updateHeight();
		return tmpleft;
	}

	/**
	 * return the root of the left rotated tree
	 */
	private BST_786 leftRotate()
	{
		if (right == null)
			return this;
		BST_786 tmpright = right;
		setRight(tmpright.left);
		tmpright.setLeft(this);
		this.updateHeight();
		tmpright.updateHeight();
		return tmpright;
	}

	/**
	 * Return the root of the balanced tree. If |height of left subtree - height of
	 * right subtree|<=1, This method will do nothing. If not, it will only adjusts
	 * once.
	 */
	private BST_786 balance()
	{
		if (getLeftHeight() >= getRightHeight() + 2)
		{
			if (left.getRightHeight() > left.getLeftHeight())
			{
				setLeft(left.leftRotate());
				updateHeight();
			}
			return rightRotate();
		}
		if (getLeftHeight() <= getRightHeight() - 2)
		{
			if (right.getLeftHeight() > right.getRightHeight())
			{
				setRight(right.leftRotate());
				updateHeight();
			}
			return leftRotate();
		}
		return this;
	}

	/**
	 * update height by its two branches.
	 */
	private void updateHeight()
	{
		height = Math.max(left != null ? left.height : 0, right != null ? right.height : 0) + 1;
	}

	/**
	 * return the root of new BST_786 after x is inserted.
	 */
	public BST_786 insert(int x1, int y1)
	{
		if (smaller(x1, y1))
		{
			if (right == null)
			{
				setRight(new BST_786(x1, y1));
				updateHeight();
				return this;
			} else
			{
				setRight(right.insert(x1, y1));
				updateHeight();
				return balance();
			}
		} else
		{
			if (left == null)
			{
				setLeft(new BST_786(x1, y1));
				updateHeight();
				return this;
			} else
			{
				setLeft(left.insert(x1, y1));
				updateHeight();
				return balance();
			}
		}
	}

	public BST_786 getMinNode()
	{
		BST_786 t = this;
		while (t.left != null)
			t = t.left;
		return t;
	}

	public BST_786 getMaxNode()
	{
		BST_786 t = this;
		while (t.right != null)
			t = t.right;
		return t;
	}

	public BST_786 removeMin()
	{
		if (left == null)
			return right;
		// Now,left sub tree will remove one node
		setLeft(left.removeMin());
		updateHeight();
		return balance();
	}

	public BST_786 removeMax()
	{
		if (right == null)
			return left;
		// Now,left sub tree will remove one node
		setRight(right.removeMax());
		updateHeight();
		return balance();
	}

	public BST_786 removeNodeByVal(int x1, int y1)
	{
		if (left == null && right == null)
		{
			if (sameVal(x1, y1))
				return null;
			else
				return this;
		}
		if (left == null && right != null)
		{
			if (bigger(x1, y1))
				return this;
			else if (sameVal(x1, y1))
				return right;
			else
			{
				setRight(right.removeNodeByVal(x1, y1));
				updateHeight();
				return balance();
			}
		}
		if (left != null && right == null)
		{
			if (smaller(x1, y1))
				return this;
			else if (sameVal(x1, y1))
				return left;
			else
			{
				setLeft(left.removeNodeByVal(x1, y1));
				updateHeight();
				return balance();
			}
		}
		// Since here, left & right subtree are not null.
		if (smaller(x1, y1))
		{
			setRight(right.removeNodeByVal(x1, y1));
			updateHeight();
			return balance();
		}
		if (bigger(x1, y1))
		{
			setLeft(left.removeNodeByVal(x1, y1));
			updateHeight();
			return balance();
		}
		// now, x==this.val, and left and right subtree are both not null
		BST_786 r = right.getMinNode();
		x = r.x;
		y = r.y;
		setRight(right.removeMin());
		updateHeight();
		return balance();
	}
}

// TLE
class Solution786
{

	public int[] kthSmallestPrimeFraction(int[] A, int K)
	{
		BST_786 tr = new BST_786(A[0], A[1]);
		for (int i = 0; i < A.length; i++)
			for (int j = i + 1; j < A.length; j++)
				if (!(i == 0 && j == 1))
					tr = tr.insert(A[i], A[j]);
		for (int i = 1; i <= K - 1; i++)
			tr = tr.removeMin();
		tr = tr.getMinNode();
		return new int[]
		{ tr.getX(), tr.getY() };
	}
}

// Ref:
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/228346/Java-Simple-code
// Runtime: 8 ms, faster than 91.10% of Java online submissions for K-th
// Smallest Prime Fraction.
// Memory Usage: 40.1 MB, less than 48.00% of Java online submissions for K-th
// Smallest Prime Fraction.
class Solution786_2
{
	private int[] countPairs(int[] A, double x)
	{
		int count = 0, n = A.length, p = 0, q = 0;
		double max = 0.0;
		for (int i = 0, j = 0; i < n; i++)
		{
			while (j < i && A[j] < A[i] * x)
				j++;
			if (j > 0)
			{
				double fraction = (double) A[j - 1] / (double) A[i];
				if (max < fraction)
				{
					max = fraction;
					p = A[j - 1];
					q = A[i];
				}
			}
			count += j;
		}
		return new int[]
		{ count, p, q };
	}

	public int[] kthSmallestPrimeFraction(int[] A, int K)
	{
		int n = A.length, min = A[0], max = A[n - 1], p = 0, q = 0;
		double lo = (double) min / (double) max, hi = 1.0;
		while (lo < hi)
		{
			double mid = (lo + hi) / 2.0;
			int[] count = countPairs(A, mid);
			if (count[0] == K)
			{
				p = count[1];
				q = count[2];
				break;
			}
			if (count[0] < K)
				lo = mid;
			else
				hi = mid;
		}
		return new int[]
		{ p, q };
	}
}

//787. Cheapest Flights Within K Stops
//Runtime: 10 ms, faster than 78.49% of Java online submissions for Cheapest Flights Within K Stops.
//Memory Usage: 38.8 MB, less than 80.69% of Java online submissions for Cheapest Flights Within K Stops.
class Solution787
{
	public static class Edge
	{
		public int from, wgt;

		public Edge(int i, int w)
		{
			from = i;
			wgt = w;
		}
	}

	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
	{
		List<List<Edge>> LL = new ArrayList<List<Edge>>(n);
		for (int i = 0; i < n; i++)
			LL.add(new ArrayList<Edge>());
		for (int i = 0; i < flights.length; i++)
			LL.get(flights[i][1]).add(new Edge(flights[i][0], flights[i][2]));
		K++;
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		int[] nd = new int[n];
		for (int time = 0; time < K; time++)
		{
			for (int i = 0; i < n; i++)
			{
				nd[i] = dist[i];
				for (Edge e : LL.get(i))
				{
					if (dist[e.from] < Integer.MAX_VALUE && dist[e.from] + e.wgt < nd[i])
						nd[i] = dist[e.from] + e.wgt;
				}
			}
			int[] t = dist;
			dist = nd;
			nd = t;
		}
		if (dist[dst] == Integer.MAX_VALUE)
			return -1;
		return dist[dst];
	}
}

//789. Escape The Ghosts
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Escape The Ghosts.
//Memory Usage: 41.1 MB, less than 6.90% of Java online submissions for Escape The Ghosts.
class Solution789
{
	public boolean escapeGhosts(int[][] ghosts, int[] target)
	{
		int d = Math.abs(target[0]) + Math.abs(target[1]);
		for (int i = 0; i < ghosts.length; i++)
			if (Math.abs(ghosts[i][0] - target[0]) + Math.abs(ghosts[i][1] - target[1]) <= d)
				return false;
		return true;
	}
}

//790. Domino and Tromino Tiling
//Runtime: 1 ms, faster than 94.14% of Java online submissions for Domino and Tromino Tiling.
//Memory Usage: 32.1 MB, less than 100.00% of Java online submissions for Domino and Tromino Tiling.
class Solution790
{
	private final static int MOD=1000000007;
	public int numTilings(int N)
	{
		int[][] d=new int[N+4][5];
		d[0][0]=0; //up - down =2
		d[0][1]=0; //up - down =1
		d[0][2]=1; //up - down =0
		d[0][3]=0; //up - down =-1
		d[0][4]=1; //up - down =-2
		d[1][0]=0;
		d[1][1]=0;
		d[1][2]=1;
		d[1][3]=1;
		d[1][4]=1;
		for (int i=2;i<=N;i++)
		{
			d[i][0]=d[i-2][2];
			d[i][1]=(d[i-2][2]+d[i-2][3])%MOD;
			d[i][2]=((d[i-1][2]+d[i-2][3])%MOD+(d[i-1][1]+d[i-2][2])%MOD)%MOD;
			d[i][3]=(d[i-1][2]+d[i][1])%MOD;
			d[i][4]=d[i][2];
		}
		return d[N][2];
	}
}

public class LC781_790
{
	public static void test781()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input781.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output781.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution781 s = new Solution781();

				int ans = s.numRabbits(nums);
				System.out.println(ans);

				bfw.write("" + ans);
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void test786()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input786.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			String instr;
			while ((instr = bfr.readLine()) != null && instr.length() > 0)
			{
				String[] inarr = instr.substring(1, instr.length() - 1).split(",");

				int[] a = new int[inarr.length];
				for (int i = 0; i < inarr.length; i++)
					a[i] = Integer.parseInt(inarr[i].trim());

				int K = Integer.parseInt(bfr.readLine());

				Solution786 s = new Solution786();

				test.Test.dispArr(s.kthSmallestPrimeFraction(a, K));
			}
			bfr.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void test787()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input787.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output787.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int n = Integer.parseInt(inLine);

				inLine = bfr.readLine();

				String[] tmp = inLine.split("(\\],\\[)|(\\]\\])");
				tmp[0] = tmp[0].substring(2);
				int[][] f = new int[tmp.length][];
				for (int i = 0; i < tmp.length; i++)
				{
					String[] s = tmp[i].split(",");
					f[i] = new int[s.length];
					for (int j = 0; j < s.length; j++)
						f[i][j] = Integer.parseInt(s[j]);
				}
				int src = Integer.parseInt(bfr.readLine());
				int dst = Integer.parseInt(bfr.readLine());
				int K = Integer.parseInt(bfr.readLine());

				Solution787 s = new Solution787();

				int ans = s.findCheapestPrice(n, f, src, dst, K);

				System.out.println(ans);

				bfw.write("" + ans);
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void main(String[] agrs)
	{
		test787();

	}
}
