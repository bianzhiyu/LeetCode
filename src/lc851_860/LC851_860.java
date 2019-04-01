package lc851_860;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

//852. Peak Index in a Mountain Array
//Runtime: 2 ms, faster than 75.06% of Java online submissions for Peak Index in a Mountain Array.
//Memory Usage: 40.3 MB, less than 87.32% of Java online submissions for Peak Index in a Mountain Array.
class Solution852
{
	public int peakIndexInMountainArray(int[] A)
	{
		for (int i = 1; i <= A.length - 2; i++)
			if (A[i] > A[i - 1] && A[i] > A[i + 1])
				return i;
		return 0;
	}
}

//853. Car Fleet
//Wrong answer, because times should be considered as float numbers.
class Solution853
{
	private static class Car implements Comparable<Car>
	{
		int pos, spd;

		public Car(int p, int s)
		{
			pos = p;
			spd = s;
		}

		public int compareTo(Car o)
		{
			return o.pos - pos;
		}

		public String toString()
		{
			return "[" + pos + ", " + spd + "]";
		}
	}

	public int carFleet(int target, int[] position, int[] speed)
	{
		PriorityQueue<Car> h = new PriorityQueue<Car>();
		for (int i = 0; i < position.length; i++)
			h.add(new Car(position[i], speed[i]));
		int fleetNum = 0;
		while (!h.isEmpty())
		{
			PriorityQueue<Car> h2 = new PriorityQueue<Car>();
			while (!h.isEmpty())
			{
				Car c = h.poll(), d;
				c.pos += c.spd;
				while (!h.isEmpty())
				{
					d = h.peek();
					if (d.pos + d.spd < c.pos)
						break;
					h.poll();
				}
				if (c.pos >= target)
					fleetNum++;
				else
					h2.offer(c);
			}
			h = h2;
		}
		return fleetNum;
	}
}

//Runtime: 54 ms, faster than 32.31% of Java online submissions for Car Fleet.
//Memory Usage: 41.2 MB, less than 77.45% of Java online submissions for Car Fleet.
class Solution853_2
{
	public static class Car
	{
		int position;
		double time;

		Car(int p, double t)
		{
			position = p;
			time = t;
		}
	}

	public int carFleet(int target, int[] position, int[] speed)
	{
		int N = position.length;
		Car[] cars = new Car[N];
		for (int i = 0; i < N; ++i)
			cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
		Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));

		int ans = 0, t = N;
		while (--t > 0)
		{
			if (cars[t].time < cars[t - 1].time)
				ans++; // if cars[t] arrives sooner, it can't be caught
			else
				cars[t - 1] = cars[t]; // else, cars[t-1] arrives at same time as cars[t]
		}

		return ans + (t == 0 ? 1 : 0); // lone car is fleet (if it exists)
	}
}

//Runtime: 21 ms, faster than 80.87% of Java online submissions for Car Fleet.
//Memory Usage: 41 MB, less than 80.39% of Java online submissions for Car Fleet.
class Solution853_3
{
	private static class Car implements Comparable<Car>
	{
		int pos;
		double time;

		public Car(int p, double t)
		{
			pos = p;
			time = t;
		}

		public int compareTo(Car o)
		{
			return o.pos - pos;
		}

		public String toString()
		{
			return "[" + pos + ", " + time + "]";
		}
	}

	public int carFleet(int target, int[] position, int[] speed)
	{
		int len = position.length;
		Car[] lt = new Car[len];
		for (int i = 0; i < len; i++)
			lt[i] = new Car(position[i], ((double) target - position[i]) / speed[i]);
		Arrays.parallelSort(lt);
		int fleetNum = 0, i = 0;
		while (i < len)
		{
			int j = i + 1;
			while (j < len && lt[j].time <= lt[i].time)
			{
				j++;
			}
			fleetNum++;
			i = j;
		}
		return fleetNum;
	}
}

//855. Exam Room
//Runtime: 68 ms, faster than 75.13% of Java online submissions for Exam Room.
//Memory Usage: 37.6 MB, less than 94.19% of Java online submissions for Exam Room.
class ExamRoom
{
	public int[] stu;
	public int num, N;

	public ExamRoom(int N)
	{
		stu = new int[10000];
		num = 0;
		this.N = N;
	}

	public int seat()
	{
		if (num == 0)
		{
			stu[0] = 0;
			num++;
			return 0;
		}
		int maxd = -1, insp = 0;
		if (stu[0] - 0 - 1 > maxd)
		{
			insp = 0;
			maxd = stu[0] - 0 - 1;
		}
		for (int i = 0; i < num - 1; i++)
		{
			int d = (stu[i + 1] - stu[i]) / 2 - 1;
			if (d > maxd)
			{
				maxd = d;
				insp = stu[i] + d + 1;
			}
		}

		if (N - 1 - stu[num - 1] - 1 > maxd)
		{
			insp = N - 1;
			maxd = N - 1 - stu[num - 1] - 1;
		}

		stu[num++] = insp;
		Arrays.parallelSort(stu, 0, num);
		return insp;
	}

	public void leave(int p)
	{
		int l = 0, r = num - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (stu[r] == p)
					l = r;
				else
					r = l;
			}
			int m = l + (r - l) / 2;
			if (stu[m] > p)
				r = m - 1;
			else
				l = m;
		}
		if (l == num - 1)
			num--;
		else
		{
			stu[l] = stu[num - 1];
			num--;
			Arrays.parallelSort(stu, 0, num);
		}
	}
}

/**
 * Your ExamRoom object will be instantiated and called as such: ExamRoom obj =
 * new ExamRoom(N); int param_1 = obj.seat(); obj.leave(p);
 */

//855 std solution
//Runtime: 76 ms, faster than 55.64% of Java online submissions for Exam Room.
//Memory Usage: 42.3 MB, less than 44.19% of Java online submissions for Exam Room.
class ExamRoom_2
{
	int N;
	TreeSet<Integer> students;

	public ExamRoom_2(int N)
	{
		this.N = N;
		students = new TreeSet<Integer>();
	}

	public int seat()
	{
		// Let's determine student, the position of the next
		// student to sit down.
		int student = 0;
		if (students.size() > 0)
		{
			// Tenatively, dist is the distance to the closest student,
			// which is achieved by sitting in the position 'student'.
			// We start by considering the left-most seat.
			int dist = students.first();
			Integer prev = null;
			for (Integer s : students)
			{
				if (prev != null)
				{
					// For each pair of adjacent students in positions (prev, s),
					// d is the distance to the closest student;
					// achieved at position prev + d.
					int d = (s - prev) / 2;
					if (d > dist)
					{
						dist = d;
						student = prev + d;
					}
				}
				prev = s;
			}

			// Considering the right-most seat.
			if (N - 1 - students.last() > dist)
				student = N - 1;
		}

		// Add the student to our sorted TreeSet of positions.
		students.add(student);
		return student;
	}

	public void leave(int p)
	{
		students.remove(p);
	}
}

//856. Score of Parentheses
//Runtime: 1 ms, faster than 96.94% of Java online submissions for Score of Parentheses.
//Memory Usage: 35.4 MB, less than 19.23% of Java online submissions for Score of Parentheses.
class Solution856
{
	private class Token
	{
		private int type; // 0: num; 1:(; 
		private int num;

		public Token(int t, int n)
		{
			type = t;
			num = n;
		}
	}

	public int scoreOfParentheses(String S)
	{
		Token[] stack = new Token[100];
		int p = 0, len = S.length(), st = 0;
		while (p < len)
		{
			if (S.charAt(p) == '(')
			{
				stack[st++] = new Token(1, 0);
				p++;
			} else if (S.charAt(p) == ')')
			{
				if (stack[st - 1].type == 1)
				{
					stack[st - 1].type = 0;
					stack[st - 1].num = 1;
				} else
				{
					int s = 0;
					while (st > 0 && stack[st - 1].type == 0)
					{
						s += stack[--st].num;
					}
					stack[st - 1].type = 0;
					stack[st - 1].num = s * 2;
				}
				p++;
			}
		}
		int s = 0;
		while (st > 0)
			s += stack[--st].num;
		return s;
	}
}

public class LC851_860
{
	public static void test853()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input853.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output853.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int target = Integer.parseInt(inLine);
				int[] pos = test.Test.parseIntArr(bfr.readLine());
				int[] spd = test.Test.parseIntArr(bfr.readLine());
				Solution853_3 s = new Solution853_3();
				int ans = s.carFleet(target, pos, spd);

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

	public static void test855()
	{
		ExamRoom r = new ExamRoom(10);
		int a;
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
//		r.stu=new int[] {1,2,3,5,6,7,8,9,0,0};
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		r.leave(0);
		System.out.println(r.num + ":");
		test.Test.dispArr(r.stu);
		r.leave(4);
		System.out.println(r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
		r.leave(0);
		System.out.println(r.num + ":");
		test.Test.dispArr(r.stu);
		r.leave(4);
		System.out.println(r.num + ":");
		test.Test.dispArr(r.stu);

		a = r.seat();
		System.out.println(a + " :" + r.num + ":");
		test.Test.dispArr(r.stu);
	}

	public static void main(String[] args)
	{

	}
}
