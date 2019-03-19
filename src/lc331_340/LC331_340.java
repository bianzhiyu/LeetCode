package lc331_340;

//331. Verify Preorder Serialization of a Binary Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Verify Preorder Serialization of a Binary Tree.
//Memory Usage: 34.5 MB, less than 78.90% of Java online submissions for Verify Preorder Serialization of a Binary Tree.
class Solution331
{
	boolean isNum(char c)
	{
		return c>='0' && c<='9';
	}
	int readNum(String str,int start,int end)
	{
		while (start<end && isNum(str.charAt(start)))
			start++;
		return start;
	}
	//start: inclusive,  end: exclusive
	int valid(String str,int start,int end)
	{
		if (start>=end) return end+1;
		if (str.charAt(start)=='#')
		{
			return start+1;
		}
		int tp=readNum(str,start,end);
		if (tp==start) return end+1;
		tp++;//skip ','
		if (tp>=end) return end+1;
		tp=valid(str,tp,end);
		tp++;//skip ','
		tp=valid(str,tp,end);
		return tp;
	}
	public boolean isValidSerialization(String preorder)
	{
		if (preorder==null || preorder.length()==0)
		{
			return true;
		}
		return valid(preorder,0,preorder.length())==preorder.length();
	}
}

//338. Counting Bits
//Runtime: 1 ms, faster than 99.97% of Java online submissions for Counting Bits.
//Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Counting Bits.
class Solution338
{
	public int[] countBits(int num)
	{
		int[] ans = new int[num + 1];
		for (int i = 1; i <= num; i++)
			ans[i] = ans[i >> 1] + (i & 1);
		return ans;
	}
}

public class LC331_340
{
	public static void testSolution331()
	{
		Solution331 s=new Solution331();
		String str;
		str="9,3,4,#,#,1,#,#,2,#,6,#,#";
//		str="1,#";
		str="9,#,#,1";
		System.out.println(s.isValidSerialization(str));
	}
	public static void main(String[] args)
	{
		testSolution331();
	}
}
