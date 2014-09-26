package com.vitechinc.sort;

import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = { 5,3, 7, 2, 4, 9, 6,1, 8, 100,11,19,18,17 };
		print(arr);
		//quickSort(0, arr.length -1, arr);
		//myQuickSort(0, arr.length, arr);
		//insertionSortRecursive(arr, arr.length);
		shellSort(arr);
		//theRealShellSort(arr);
		print(arr);
		
		Random r = new Random();
//		log("random:" + r.nextInt(100));
//		log("random:" + r.nextInt(100));
	}

	static void shellSort(int[] arr){
		int step = getMaxStep(arr);
		//log("step=" + step);
		int comps = 0;
		while(step>0){
			log("Step=" + step);
			for( int i=step; i<arr.length; i++){
				log("..i==" + i);
				int ai = arr[i], j;
				
				for(j=i; j>0 && arr[j-1] > ai ; j--){
					arr[j] = arr[j-1];print(arr);
					comps++;
				}
				comps++;
				arr[j] = ai;print(arr);				
			}
			step = step / 3;
		}
		log("comps=" + comps);
	}
	static void theRealShellSort(int[] arr){
		int step = getMaxStep(arr);
		
		int comps = 0;
		while(step>0){
			log("Step=" + step);
			for(int k=0; k <= step; k++){
				if(step==1 && k==1) continue;
				log("..k=" + k);
				for(int l=0; l<arr.length/step;l++){
					log("....i=" + (k+l*step));
					int i = k+l*step;
					int ai=arr[i], j;
					for(j=i; (j-k)>0 && arr[j-step]>ai; j=j-step){
						log("......arr[" + (j-step) + "] is > " + ai );
						arr[j] = arr[j-step]; print(arr);
						comps++;
					}
					comps++;
//					for(j=i; (j-k)>0 && arr[j-l*step]>ai; j=j-l*step){
//						log("......arr[" + (j-l*step) + "] is > " + ai );
//						arr[j] = arr[j-l*step]; print(arr);
//					}
					arr[j] = ai;print(arr);
				}
			}
			step = step / 3;
		}
		log("comps=" + comps);
	}
	private static int getMaxStep(int[] arr) {
		int step = 1;
		while((3*step+1) < arr.length){
			step = 3*step + 1;
		}
		return step;
	}
	static void insertionSort(int[] arr){
		for(int i=1; i < arr.length; i++){
			int ai = arr[i], j;
			
			for(j=i; j>0 && arr[j-1] > ai ; j--){
				arr[j] = arr[j-1];
			}
			arr[j] = ai;
		}
	}
	static void insertionSortRecursive(int[] arr, int n){
		if(n<2) return;
		
		insertionSortRecursive(arr, n-1);
		
		int ai = arr[n-1], j;
		
		for(j=n-1; j>0 && arr[j-1] > ai ; j--){
			arr[j] = arr[j-1];
		}
		arr[j] = ai;
		
	}
	
	
	private static void log(String s) {
		System.out.println(s);
	}

	private static void print(int[] arr) {
		for(int i: arr){
			System.out.print(i + " ");
		}
		System.out.print("\n");
	}

	static void quickSort(int l, int u, int[] arr){
		int m = l;
		
		if( l>=u) return; //BASIS
		
		//swap(l, partition(l,u,arr), arr);
		//swap(l, new Random().nextInt(u - l), arr);
		int pivot = arr[l];
		
		for(int i=l+1; i<=u; i++){
			if(arr[i] < pivot){
				swap(i, ++m, arr);
			}
		}
		swap(l, m, arr);
		
		quickSort(l, m-1, arr);
		quickSort(m+1, u, arr);
	}
	
	static void myQuickSort(int l, int u, int[] arr){
		if(u-l < 2) return;
		
		int pivot = partition(l,u, arr);
		print( arr);
		myQuickSort(l, pivot, arr);
		myQuickSort(pivot+1, u, arr);
	}

	private static int partition(int l, int u, int[] arr) {
		int pivot = middleElementAsPivot(l,u, arr), i=l, j=u;
		
		while(i<j){
			while(i<j && arr[--j]>=pivot);
			if(i<j)
				arr[i] = arr[j];
						
			while(i<j && arr[++i] <= pivot);
			if(i<j)
				arr[j] = arr[i];
			
		}
		arr[j] = pivot;
		return j;
	}
	private static int firstElementAsPivot(int l, int[] arr) {
		return arr[l];
	}
	private static int middleElementAsPivot(int l, int u, int[] arr) {
		int i = (l+u)/2;
		//if
		return arr[i];
	}

	private static void swap(int l, int nextInt, int[] arr) {
		int tmp = arr[l];
		arr[l] = arr[nextInt];
		arr[nextInt] = tmp;
	}
}
