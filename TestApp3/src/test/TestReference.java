package test;


import static java.lang.System.out;
public class TestReference {

	
	
	public static void main(String[] args) {
		int a=20;
		int b=30;
		System.out.println("\nbefore function:");
		out.format("a=%d\n",a);
		out.format("b=%d\n",b);

		System.out.println("\nin function:");
		swap(a,b);
		
		System.out.println("\nafter function:");
		out.format("a=%d\n",a);
		out.format("b=%d\n",b);
	}
	
	static void swap(int fa,int fb) {
		int temp;
		temp=fa;
		fa=fb;
		fb=temp;
		
		out.format("fa=%d\n",fa);
		out.format("fb=%d\n",fb);
		
	}

}
