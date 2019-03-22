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
		System.out.println(new notPublicClass().x);
		System.out.println(new MutualC2().c2int);
	}
}
