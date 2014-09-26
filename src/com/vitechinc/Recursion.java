package com.vitechinc;

public class Recursion {

	
	public static void main(String[] args) {
		int result = factorial(4);
		p(""+ result);
		result = factorialR(4);
		p(""+ result);
	}

	private static int factorialR(int n) {
		if(n<=1)
			return 1;
		return n*factorialR(n-1);
	}
	private static int factorial(int n) {
		int result = 1;
		for(int i=n; i>1; i--){
			result = i * result;
		}
		return result;
	}

	private static void p(String string) {
		System.out.println(string);
	}

}
