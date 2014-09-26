package com.vitechinc.play;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;

public class ReadObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		Object o = null;
		PersistentTime pt = null;
		try {
			fis = new FileInputStream("time.ser");
			ois = new ObjectInputStream(fis);
			
			o = ois.readObject();
			pt = (PersistentTime) o;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p("curr time=" + Calendar.getInstance().getTime());
		p("persistent obj read =" + pt);

	}
	private static void p(String string) {

		System.out.println(string);
	}

}
