package com.vitechinc;

import java.util.Arrays;

public class Practise {

	public static void main(String[] args) {
		int[] arr = {53,23,9,64,50,48};
		int count = 0;
		
		printArray(arr);
		for(int j=0; j<arr.length -1; j++){
			
			for(int i=1;i< arr.length - j;i++){
				if(arr[i-1] > arr[i]){
					swap(arr, i-1, i);
				}
				printArray(arr);				
				count++;
			}
		}
		log("Number or iterations=" + count);
	}

	private static void log(String string) {
		System.out.println(string);
		
	}

	private static void swap(int[] arr, int i, int j) {
		int temp;
		temp = arr[i];
		arr[i]=arr[j];
		arr[j] = temp;
	}
	
	static void printArray(int[] arr){
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i] + ", ");
		}
		System.out.println(" ");
	}
}
