package com.vitechinc.play;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigDecimalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = null;
		test("1");		
		test("1.0");
		test("1.12121");
		test("100.");
		
		String[] sa = gets();
		printStringArray(sa);
		List<String> al = new ArrayList<String>(Arrays.asList(sa));
		p("arraylist=" + al);
		al.add("");
		p("arraylist=" + al);
		String[] sa2 = al.toArray(new String[al.size()]);
		printStringArray(sa2);
		s = "a,b,c,d ";
		if(s.endsWith(",")){
			p("ends with a ,");
		}
		//sa[sa.length] = "";
	}
	private static void printStringArray(String[] sa) {
		for(String ss: sa){
			p("=" + ss);
		}
	}
	private static String[] gets() {
		String[] ar = {"first", "second", "third"};
		return ar;
	}
	private static void test(String s) {
		
		BigDecimal d = new BigDecimal(s);
		
		p("scale for " + s + " is=" +d.scale());
	}
	private static void p(String string) {

		System.out.println(string);
	}

}
