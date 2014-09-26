package com.vitechinc.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class SystemClipboard implements ClipboardOwner {

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemClipboard sc = new SystemClipboard();
		
		p("curr clipboard contents="+ sc.getClipboardContents());
		sc.setClipboardContents("bla bla bla");

		p("curr clipboard contents="+ sc.getClipboardContents());
	}

	private void setClipboardContents(String string) {
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(string);
		c.setContents(stringSelection, this);
	}

	private String getClipboardContents() {
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = c.getContents(null);
		String result = "";
		
		if(contents!=null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)){
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private static void p(String string) {		System.out.println(string); }
}
