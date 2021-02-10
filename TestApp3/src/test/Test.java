package test;

import java.util.Arrays;

import javax.xml.transform.stream.StreamSource;

import practice.S;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] ints=new int[0];
		ints=new int[] {1,2,3};
		System.out.println(ints.length);
		Arrays.asList(ints).stream().map(x->x) .forEach(S.o::space);
		S.o.l("xxx");
		Arrays.stream(ints).forEach(S.o::space);
	}

}
