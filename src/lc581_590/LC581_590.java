package lc581_590;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution581 
{
    public int findUnsortedSubarray(int[] nums) 
    {
    	int len=nums.length;
    	
    	int x=0;
    	
    	
    	
    	return 0;
        
    }
}

//583. Delete Operation for Two Strings
//Runtime: 4 ms, faster than 100.00% of Java online submissions for Delete Operation for Two Strings.
//Memory Usage: 37.3 MB, less than 92.13% of Java online submissions for Delete Operation for Two Strings.
class Solution583
{
	public int minDistance(String word1, String word2)
	{
		int len1 = word1.length(), len2 = word2.length();
		int[][] d = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++)
			d[i][0] = i;
		for (int i = 1; i <= len2; i++)
			d[0][i] = i;
		for (int i = 1; i <= len1; i++)
			for (int j = 1; j <= len2; j++)
			{
				d[i][j] = 1 + Math.min(d[i - 1][j], d[i][j - 1]);
				if (word1.charAt(i - 1) == word2.charAt(j - 1) && d[i - 1][j - 1] < d[i][j])
					d[i][j] = d[i - 1][j - 1];
			}
		return d[len1][len2];
	}
}

//587. Erect the Fence
//std solution
//Jarvis Algorthm
//Runtime: 27 ms, faster than 37.18% of Java online submissions for Erect the Fence.
//Memory Usage: 49.8 MB, less than 75.00% of Java online submissions for Erect the Fence.
class Solution587
{
	public int orientation(Point p, Point q, Point r)
	{
		return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
	}

	public boolean inBetween(Point p, Point i, Point q)
	{
		boolean a = i.x >= p.x && i.x <= q.x || i.x <= p.x && i.x >= q.x;
		boolean b = i.y >= p.y && i.y <= q.y || i.y <= p.y && i.y >= q.y;
		return a && b;
	}

	public List<Point> outerTrees(Point[] points)
	{
		HashSet<Point> hull = new HashSet<Point>();
		if (points.length < 4)
		{
			for (Point p : points)
				hull.add(p);
			return new ArrayList<Point>(hull);
		}
		int left_most = 0;
		for (int i = 0; i < points.length; i++)
			if (points[i].x < points[left_most].x)
				left_most = i;
		int p = left_most;
		do
		{
			int q = (p + 1) % points.length;
			for (int i = 0; i < points.length; i++)
			{
				if (orientation(points[p], points[i], points[q]) < 0)
				{
					q = i;
				}
			}
			for (int i = 0; i < points.length; i++)
			{
				if (i != p && i != q && orientation(points[p], points[i], points[q]) == 0
						&& inBetween(points[p], points[i], points[q]))
				{
					hull.add(points[i]);
				}
			}
			hull.add(points[q]);
			p = q;
		} while (p != left_most);
		return new ArrayList<Point>(hull);
	}

	public int[][] outerTrees(int[][] points)
	{
		Point[] ps = new Point[points.length];
		for (int i = 0; i < points.length; i++)
			ps[i] = new Point(points[i][0], points[i][1]);
		List<Point> a1 = outerTrees(ps);
		int[][] a2 = new int[a1.size()][];
		for (int i = 0; i < a1.size(); i++)
			a2[i] = new int[]
			{ a1.get(i).x, a1.get(i).y };
		return a2;
	}
}

//mimic the standard solution
//Runtime: 33 ms, faster than 27.50% of Java online submissions for Erect the Fence.
//Memory Usage: 41 MB, less than 100.00% of Java online submissions for Erect the Fence.
class Solution587_2
{
	private int[] vec(int[] p1, int[] p2)
	{
		return new int[]
		{ p2[0] - p1[0], p2[1] - p1[1] };
	}

	private int outProd(int[] v1, int[] v2)
	{
		return v1[0] * v2[1] - v1[1] * v2[0];
	}

	private boolean isBetween(int[] p1, int[] m, int[] p2)
	{
		return (m[0] >= p1[0] && p2[0] >= m[0] || m[0] <= p1[0] && p2[0] <= m[0])
				&& (m[1] >= p1[1] && p2[1] >= m[1] || m[1] <= p1[1] && p2[1] <= m[1]);
	}

	public int[][] outerTrees(int[][] points)
	{
		if (points.length < 4)
			return points;
		int leftMost = 0;
		for (int i = 1; i < points.length; i++)
			if (points[i][0] < points[leftMost][0])
				leftMost = i;
		int ord = 0;
		int[] used = new int[points.length];
		int p = leftMost;
		do
		{
			int q = (p + 1) % points.length;
			for (int i = 0; i < points.length; i++)
				if (outProd(vec(points[p], points[q]), vec(points[p], points[i])) < 0)
					q = i;
			for (int i = 0; i < points.length; i++)
				if (i != p && i != q && outProd(vec(points[p], points[q]), vec(points[p], points[i])) == 0
						&& used[i] == 0 && isBetween(points[p], points[i], points[q]))
					used[i] = ++ord;
			if (used[q] == 0)
				used[q] = ++ord;
			p = q;
		} while (p != leftMost);
		int[][] ans = new int[ord][];
		ord = 0;
		for (int i = 0; i < points.length; i++)
			if (used[i] != 0)
				ans[ord++] = new int[]
				{ points[i][0], points[i][1] };
		return ans;
	}
}

public class LC581_590
{
	public static void test583()
	{
		Solution583 s = new Solution583();
		String s1 = "sea";
		String s2 = "eat";
		System.out.println(s.minDistance(s1, s2));
	}

	public static void test587()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input587.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output587.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g = test.Test.parse2DIntArr(inLine);

				Solution587 s = new Solution587();
				Solution587_2 s2 = new Solution587_2();

				int[][] a = s.outerTrees(g);

				String out = test.Test.int2DArrToString(a);
				System.out.println(out);

				int[][] a2 = s2.outerTrees(g);

				String out2 = test.Test.int2DArrToString(a2);

				System.out.println(out2);

				bfw.write(out);
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

	public static void main(String[] args)
	{
		test587();
	}
}
