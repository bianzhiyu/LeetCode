package lc631_640;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//632. Smallest Range
//Runtime: 53 ms, faster than 77.19% of Java online submissions for Smallest Range.
//Memory Usage: 49 MB, less than 81.67% of Java online submissions for Smallest Range.
class Solution632
{
	int findMinIndexNoLessThan(List<Integer> arr, int tar)
	{
		int l = 0, r = arr.size() - 1;
		if (arr.get(r) < tar)
			return r + 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (arr.get(l) >= tar)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (arr.get(m) >= tar)
					r = m;
				else
					l = m;
			}
		}
		return l;
	}

	int findMaxIndexNoLargerThan(List<Integer> arr, int tar)
	{
		int l = 0, r = arr.size() - 1;
		if (arr.get(0) > tar)
			return -1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (arr.get(r) <= tar)
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (arr.get(m) <= tar)
					l = m;
				else
					r = m;
			}
		}
		return l;
	}

	boolean joint(List<Integer> arr, int l, int r)
	{
		if (findMinIndexNoLessThan(arr, l) <= findMaxIndexNoLargerThan(arr, r))
			return true;
		else
			return false;
	}

	public int[] smallestRange(List<List<Integer>> nums)
	{
		Set<Integer> ns = new HashSet<Integer>();
		for (List<Integer> i : nums)
			for (int j : i)
				ns.add(j);
		int[] allns = new int[ns.size()];
		int j = 0;
		for (int i : ns)
			allns[j++] = i;
		Arrays.parallelSort(allns);
		int[] ans = new int[2];
		int minlen = Integer.MAX_VALUE;
		for (int i = 0; i < allns.length; i++)
			for (j = i; j < allns.length; j++)
			{
				if (allns[j] - allns[i] > minlen)
					break;
				boolean succ = true;
				for (List<Integer> k : nums)
				{
					if (!joint(k, allns[i], allns[j]))
					{
						succ = false;
						break;
					}
				}
				if (succ)
				{
					if (allns[j] - allns[i] < minlen)
					{
						minlen = allns[j] - allns[i];
						ans[0] = allns[i];
						ans[1] = allns[j];
					} else if (allns[j] - allns[i] == minlen && allns[i] < ans[0])
					{
						ans[0] = allns[i];
						ans[1] = allns[j];
					}
				}
			}
		return ans;
	}
}

//636. Exclusive Time of Functions
//misunderstand the question.
class Solution636
{
	private int indexOf(int[] a, int len, int v)
	{
		for (int i = 0; i < len; i++)
			if (a[i] == v)
				return i;
		return -1;
	}

	private void removeAt(int[] a, int len, int ind)
	{
		for (int i = ind; i < len - 1; i++)
			a[i] = a[i + 1];
	}

	public int[] exclusiveTime(int n, List<String> logs)
	{
		int curFunId = -1, startTime = -1;

		int[] stackFunId = new int[n + 5];
		int stackLen = 0;

		int[] time = new int[n];

		for (String info : logs)
		{
			String[] data = info.split(":");
			int funId = Integer.parseInt(data[0]);
			int timeStp = Integer.parseInt(data[2]);
			if (data[1].compareTo("start") == 0)
			{
				if (curFunId == -1)
				{
					curFunId = funId;
					startTime = timeStp;
				} else if (curFunId == funId)
				{
					;// do nothing
				} else
				{
					time[curFunId] += timeStp - startTime;
					stackFunId[stackLen] = curFunId;
					stackLen++;

					curFunId = funId;
					startTime = timeStp;
					int findWaitInd = indexOf(stackFunId, stackLen, funId);
					if (findWaitInd >= 0)
					{
						removeAt(stackFunId, stackLen, findWaitInd);
						stackLen--;
					}
				}
			} else // "end"
			{
				if (funId == curFunId)
				{
					time[funId] += timeStp - startTime + 1;
					if (stackLen == 0)
					{
						startTime = -1;
						curFunId = -1;
					} else
					{
						stackLen--;
						curFunId = stackFunId[stackLen];
						startTime = timeStp + 1;
					}
				} else
				{
					int findWaitInd = indexOf(stackFunId, stackLen, funId);
					if (findWaitInd >= 0)
					{
						removeAt(stackFunId, stackLen, findWaitInd);
						stackLen--;
					}
				}
			}
		}
		return time;
	}
}

//Runtime: 12 ms, faster than 99.68% of Java online submissions for Exclusive Time of Functions.
//Memory Usage: 39.8 MB, less than 58.29% of Java online submissions for Exclusive Time of Functions.
class Solution636_2
{
	public int[] exclusiveTime(int n, List<String> logs)
	{
		int curFunId = -1, startTime = -1;

		int[] stackFunId = new int[logs.size() + 1];
		int stackLen = 0;

		int[] time = new int[n];

		for (String info : logs)
		{
			String[] data = info.split(":");
			int funId = Integer.parseInt(data[0]);
			int timeStp = Integer.parseInt(data[2]);
			if (data[1].compareTo("start") == 0)
			{
				if (curFunId == -1)
				{
					stackFunId[stackLen] = funId;
					stackLen++;
					curFunId = funId;
					startTime = timeStp;
				} else
				{
					time[curFunId] += timeStp - startTime;
					stackFunId[stackLen] = curFunId;
					stackLen++;
					curFunId = funId;
					startTime = timeStp;
				}
			} else // "end"
			{
				time[curFunId] += timeStp - startTime + 1;
				stackLen--;
				curFunId = stackFunId[stackLen];
				startTime = timeStp + 1;
			}
		}
		return time;
	}
}

//638. Shopping Offers
//Runtime: 300 ms, faster than 5.12% of Java online submissions for Shopping Offers.
//Memory Usage: 58.2 MB, less than 7.69% of Java online submissions for Shopping Offers.
class Solution638
{
	private int[] record;

	private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs)
	{
		int state = toState(needs);
		if (state == 0)
			return 0;
		if (record[state] > -1)
			return record[state];

		int tmp, tmpPrice;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < price.size(); i++)
		{
			if ((tmp = needs.get(i)) >= 1)
			{
				needs.set(i, tmp - 1);
				tmpPrice = dfs(price, special, needs);
				if (tmpPrice != Integer.MAX_VALUE && (tmpPrice = tmpPrice + price.get(i)) < min)
					min = tmpPrice;
				needs.set(i, tmp);
			}
		}
		int priceInd = needs.size();
		for (List<Integer> of : special)
		{
			boolean canBuy = true;
			for (int j = 0; j < needs.size(); j++)
				if (needs.get(j) < of.get(j))
				{
					canBuy = false;
					break;
				}
			if (canBuy)
			{
				for (int j = 0; j < needs.size(); j++)
					needs.set(j, needs.get(j) - of.get(j));
				tmpPrice = dfs(price, special, needs);
				if (tmpPrice != Integer.MAX_VALUE && (tmpPrice = tmpPrice + of.get(priceInd)) < min)
					min = tmpPrice;
				for (int j = 0; j < needs.size(); j++)
					needs.set(j, needs.get(j) + of.get(j));
			}
		}

		record[state] = min;
		return min;
	}

	private int toState(List<Integer> needs)
	{
		int s = 0;
		for (int i = 0; i < needs.size(); i++)
			s = s * 7 + needs.get(i);
		return s;
	}

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs)
	{
		record = new int[toState(needs) + 10];
		Arrays.fill(record, -1);
		return dfs(price, special, needs);
	}
}

//640. Solve the Equation
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Solve the Equation.
//Memory Usage: 35.6 MB, less than 95.35% of Java online submissions for Solve the Equation.
class Solution640
{
	private boolean isNum(char c)
	{
		return '0' <= c && c <= '9';
	}

	private int[] parse(String exp)
	{
		int[] res = new int[2];
		int p = 0, sign = 1, num;
		while (p < exp.length())
		{
			if (p == 0)
			{
				if (exp.charAt(0) == '-')
				{
					sign = -1;
					p++;
				} else if (exp.charAt(0) == '+')
				{
					sign = 1;
					p++;
				} else
					sign = 1;
			} else
			{
				if (exp.charAt(p) == '-')
					sign = -1;
				else if (exp.charAt(p) == '+')
					sign = 1;
				p++;
			}
			if (exp.charAt(p) == 'x')
			{
				res[0] += sign;
				p++;
			} else
			{
				num = 0;
				while (p < exp.length() && isNum(exp.charAt(p)))
					num = num * 10 + (exp.charAt(p++) - '0');
				if (p < exp.length() && exp.charAt(p) == 'x')
				{
					res[0] += sign * num;
					p++;
				} else
					res[1] += sign * num;
			}
		}
		return res;
	}

	public String solveEquation(String equation)
	{
		int equPos = equation.indexOf('=');
		int[] p1 = parse(equation.substring(0, equPos));
		int[] p2 = parse(equation.substring(equPos + 1));
		p1[0] -= p2[0];
		p1[1] -= p2[1];
		if (p1[0] != 0)
			return "x=" + Integer.toString(-p1[1] / p1[0]);
		if (p1[1] == 0)
			return "Infinite solutions";
		return "No solution";
	}
}

public class LC631_640
{
	public static void test636()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input636.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output636.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int n = Integer.parseInt(inLine);
				inLine = bfr.readLine();
				inLine = inLine.substring(1, inLine.length() - 1);
				List<String> logs = new ArrayList<String>();
				for (String s : inLine.split(","))
					logs.add(s.substring(1, s.length() - 1));

				Solution636_2 solver = new Solution636_2();

				int[] ans = solver.exclusiveTime(n, logs);

				String ansstr = test.Test.intArrToString(ans);

				System.out.println(ansstr);

				bfw.write(ansstr);
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

	public static void test640()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input640.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output640.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{

				Solution640 solver = new Solution640();

				String ans = solver.solveEquation(inLine.substring(1, inLine.length() - 1));

				System.out.println(ans);

				bfw.write(ans);
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
		test640();
	}

}
