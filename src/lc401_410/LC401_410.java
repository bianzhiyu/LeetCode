package lc401_410;


//402. Remove K Digits
//Runtime: 3 ms, faster than 98.22% of Java online submissions for Remove K Digits.
//Memory Usage: 37.5 MB, less than 82.45% of Java online submissions for Remove K Digits.

//https://leetcode.com/problems/remove-k-digits/discuss/88708/Straightforward-Java-Solution-Using-Stack
//He gives the intuition.
//I do come calculation to make sure that is right.
class Solution402
{
	public String removeKdigits(String num, int k)
	{
		char[] stack=new char[10003];
		int sp=0,rp=0,slen=num.length();
		while (rp<slen)
		{
			if (sp==0)
			{
				stack[sp++]=num.charAt(rp++);
			}
			else
			{
				while (k>0 && sp>0 && num.charAt(rp)<stack[sp-1])
				{
					k--;
					sp--;
				}
				stack[sp++]=num.charAt(rp++);
			}
		}
		StringBuilder s=new StringBuilder();
		sp-=k;
		int i=0;
		while (i<sp && stack[i]=='0') i++;
		if (i==sp) return "0";
		while (i<sp) s.append(stack[i++]);
		return s.toString();
	}
}

//403. Frog Jump
//Runtime: 184 ms, faster than 9.32% of Java online submissions for Frog Jump.
//Memory Usage: 50.2 MB, less than 100.00% of Java online submissions for Frog Jump.
class Solution403
{
	public boolean canCross(int[] stones)
	{
		// Arrays.sort(stones);
		int L = stones.length;
		if (L <= 1 || stones[1] - stones[0] > 1)
			return false;
		int[][] D = new int[L + 1][L + 1];
		int[] lenD = new int[L + 1];
		lenD[0] = 0;
		lenD[1] = 1;
		D[1][0] = 0;
		for (int i = 2; i < L; i++)
			for (int j = 1; j < i; j++)
			{
				for (int k = 0; k < lenD[j]; k++)
				{
					int jumplen = stones[j] - D[j][k];
					if (jumplen - 1 <= stones[i] - stones[j] && jumplen + 1 >= stones[i] - stones[j])
					{
						lenD[i]++;
						D[i][lenD[i] - 1] = stones[j];
						break;
					}
				}
			}
		return lenD[L - 1] > 0;
	}
}

//406. Queue Reconstruction by Height
//Runtime: 15 ms, faster than 62.08% of Java online submissions for Queue Reconstruction by Height.
//Memory Usage: 45.4 MB, less than 66.80% of Java online submissions for Queue Reconstruction by Height.
class Solution406
{
	public static void swap(int[][] a, int p1, int p2)
	{
		int t = a[p1][0];
		a[p1][0] = a[p2][0];
		a[p2][0] = t;
		t = a[p1][1];
		a[p1][1] = a[p2][1];
		a[p2][1] = t;
	}

	public static int compareTo(int[] a, int[] b)
	{
		if (a[0] != b[0])
			return a[0] - b[0];
		return a[1] - b[1];
	}

	public static void qs(int[][] a, int l, int r)
	{
		if (l >= r)
			return;
		int pivotInd = l;
		int[] pivot = new int[]
				{ a[pivotInd][0], a[pivotInd][1] };
		swap(a, pivotInd, r - 1);
		int storeInd = l;
		for (int i = l; i < r - 1; i++)
			if (compareTo(a[i], pivot) < 0)
			{
				swap(a, i, storeInd);
				storeInd++;
			}
		swap(a, r - 1, storeInd);
		qs(a, l, storeInd);
		qs(a, storeInd + 1, r);
	}

	public int[][] reconstructQueue(int[][] people)
	{
		int len = people.length;
		qs(people, 0, len);
		int[][] ans = new int[len][2];
		boolean[] used = new boolean[len];
		int i = 0, h = 0, ion = 0, ct = 0, p = 0;
		while (i < len)
		{
			p = 0;
			ct = 0;
			ion = people[i][1];
			h = people[i][0];
			while (true)
			{
				if (used[p])
				{
					if (ans[p][0] >= h)
						ct++;
					p++;
				} else
				{
					if (ct == ion)
						break;
					ct++;
					p++;
				}
			}
			ans[p][0] = h;
			ans[p][1] = ct;
			used[p] = true;
			i++;
		}
		return ans;
	}
}

//Runtime: 12 ms, faster than 68.98% of Java online submissions for Queue Reconstruction by Height.
//Memory Usage: 39.9 MB, less than 98.74% of Java online submissions for Queue Reconstruction by Height.
class Solution406_2
{
	public static void swap(int[][] a, int p1, int p2)
	{
		int t = a[p1][0];
		a[p1][0] = a[p2][0];
		a[p2][0] = t;
		t = a[p1][1];
		a[p1][1] = a[p2][1];
		a[p2][1] = t;
	}

	public static int compareTo(int[] a, int[] b)
	{
		if (a[0] != b[0])
			return a[0] - b[0];
		return a[1] - b[1];
	}

	public static void qs(int[][] a, int l, int r)
	{
		if (l >= r)
			return;
		int pivotInd = l;
		int[] pivot = new int[]
				{ a[pivotInd][0], a[pivotInd][1] };
		swap(a, pivotInd, r - 1);
		int storeInd = l;
		for (int i = l; i < r - 1; i++)
			if (compareTo(a[i], pivot) < 0)
			{
				swap(a, i, storeInd);
				storeInd++;
			}
		swap(a, r - 1, storeInd);
		qs(a, l, storeInd);
		qs(a, storeInd + 1, r);
	}

	public int[][] reconstructQueue(int[][] people)
	{
		int len = people.length;
		qs(people, 0, len);
		int[][] ans = new int[len][2];
		boolean[] used = new boolean[len];
		int i = 0, h = 0, ion = 0, ct = 0, p = 0;
		while (i < len)
		{
			ion = people[i][1];
			h = people[i][0];
			if (i > 0 && h == people[i - 1][0])
			{
				p++;
				ct++;
			} else
			{
				p = 0;
				ct = 0;
			}
			while (true)
			{
				if (used[p])
				{
					if (ans[p][0] >= h)
						ct++;
					p++;
				} else
				{
					if (ct == ion)
						break;
					ct++;
					p++;
				}
			}
			ans[p][0] = h;
			ans[p][1] = ct;
			used[p] = true;
			i++;
		}
		return ans;
	}
}

public class LC401_410
{
	public static void main(String[] args)
	{

	}

}
