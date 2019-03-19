package lc761_770;

import java.util.ArrayList;
import java.util.List;

class Col763
{
	int[] ct;
	Col763(){ct=new int[26];}
	Col763(Col763 na)
	{
		ct=new int[26];
		for (int i=0;i<26;i++)
			ct[i]=na.ct[i];
	}
	Col763 minus(Col763 na)
	{
		Col763 ans=new Col763(this);
		for (int i=0;i<26;i++)
			ans.ct[i]-=na.ct[i];
		return ans;
	}
	static boolean disjoint(Col763 c1,Col763 c2)
	{
		for (int i=0;i<26;i++)
			if (c1.ct[i]>0 && c2.ct[i]>0) return false;
		return true;
	}
	void setAllZero()
	{
		for (int i=0;i<26;i++)
			ct[i]=0;
	}
	//	Col763 minus
}
//763. Partition Labels
//Runtime: 8 ms, faster than 67.46% of Java online submissions for Partition Labels.
//Memory Usage: 38.4 MB, less than 5.00% of Java online submissions for Partition Labels.
class Solution763 
{
	public List<Integer> partitionLabels(String S) 
	{
		List<Integer> ans=new ArrayList<Integer>();
		int n=S.length();
		if (n==0) return ans;
		Col763[] ana=new Col763[n+1];
		ana[0]=new Col763();
		for (int i=0;i<n;i++)
		{
			ana[i+1]=new Col763(ana[i]);
			ana[i+1].ct[S.charAt(i)-'a']++;
		}
		int left=0;
		Col763 rem=new Col763(ana[n]),sinceleft=new Col763();
		for (int i=1;i<=n;i++)
		{
			rem.ct[S.charAt(i-1)-'a']--;
			sinceleft.ct[S.charAt(i-1)-'a']++;
			if (Col763.disjoint(sinceleft, rem))
			{
				ans.add(i-left);
				left=i;
				sinceleft.setAllZero();
			}
		}
		return ans;
	}
}


public class LC761_770 {
	public static void main(String[] args)
	{

	}

}
