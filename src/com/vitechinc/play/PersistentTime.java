package com.vitechinc.play;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PersistentTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersistentTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	Date date;
	//transient 
	String s;
	
	public static void main(String[] args) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		PersistentTime pt = new PersistentTime(Calendar.getInstance().getTime());
		
		try {
			fos = new FileOutputStream("time.ser");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(pt);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public String toString() {
		return "PersistentTime [date=" + date + ", s=" + s +"]";
	}

	public PersistentTime(Date date) {
		super();
		this.date = date;
	}

}
