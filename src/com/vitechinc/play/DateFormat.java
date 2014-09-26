package com.vitechinc.play;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyBB");
		
		String s = "12-22-198x";
		
		String test = "";
		Date d = null;
		try {
			d = sdf.parse(s);
			test = sdf.format(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p("date is:" + d);
		if(!test.equals(s)){
			p("this is an invalid date");
		}
		
	}

	private static void p(String string) {

		System.out.println(string);
	}

}
