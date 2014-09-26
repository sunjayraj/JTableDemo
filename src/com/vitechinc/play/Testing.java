package com.vitechinc.play;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {33,2,14,55,53,67};
		
		p("array=" );
		
		for(int i: array){
//			pp(i + ",");
		}
		
		String[] strArray = {"one","two", "three"};
		for(String s: strArray){
			//System.out.println("\n strArray=" + s);
		}
		for(int i=0; i<strArray.length; i++){
			//System.out.println("strArray=" + strArray[i]);
		}
		for(int j=2; j<=0; j--){
			System.out.println("strArray=" + strArray[j]);
		}
		
		//sort(array);
	}

	private static void sort(int[] array) {
		p("inside sort");
		
		for(int i=0; i<array.length; i++){
			pp("i=" + array[i] );			
			for(int j=i+1; j < array.length; j++){
				p(", j=" + array[j]);
				if(array[i] > array[j]){
					//swap()
				}
			}
			pp(array[i] + ",");			
		}
		
	}

	private static void pp(String string) {
		System.out.print(string);
	}

	private static void p(String string) {
		System.out.println(string);
	}

	
}
