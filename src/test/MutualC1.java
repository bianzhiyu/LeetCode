package test;

public class MutualC1
{
	MutualC2 data;
	void foo()
	{
		System.out.println(data.c2int);
		System.out.println(new notPublicClass().x);
	}
	public static void main(String[]args)
	{
		MutualC1 c=new MutualC1();
		System.out.println(c.data.c2int);
	}
}
