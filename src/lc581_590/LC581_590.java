package lc581_590;

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

public class LC581_590
{
	public static void test583()
	{
		Solution583 s = new Solution583();
		String s1 = "sea";
		String s2 = "eat";
		System.out.println(s.minDistance(s1, s2));
	}

	public static void main(String[] args)
	{
		test583();
	}
}
