package org.blueocean;

import java.util.Arrays;

public class SortQ {
	
	
	public static void circleSort(int[] array){

		for(int i=0; i<array.length; i++){
			if(array[i]<0)
				continue;
			int startPos = i;
			int next_value = array[startPos];
			while( startPos != next_value ){

				int next_index = next_value;
				next_value = array[next_index];
				array[next_index] = (startPos + 1) * -1; //mark it negative 
				startPos  = next_index;
				if(next_index == i)
					break;
			}
		}

		for(int i = 0; i< array.length; i++){
			if(array[i]<0)
				array[i] = array[i]*-1 -1;
		}

	}

	
	public static int[] circularSort(int[] nums){
		int len = nums.length;
		int[] cb = new int[len];
		cb[len/2] = nums[0];
		int start = len/2, end = len/2;
		
		for(int i=1; i<len; i++){
			//if nums[i] is the smallest, append to the front
			if(nums[i]<=cb[start]){
				start = start == 0? len-1:start-1;
				cb[start] = nums[i];
			//if nums[i] is the biggest, append to the end
			}else if(nums[i]>=cb[end]){
				end = end == len-1? 0 : end+1;
				cb[end] = nums[i];
			}else{
				//if nums[i] falls into first half
				if(nums[i]<cb[len/2]){
					start = start == 0? len-1:start-1;
					int p = start;
					//shift the first half to left circularly 
					while(cb[p]<nums[i]){
						int next = p-1>=0? p-1: len-1;
						cb[next] = cb[p];
						p++;
					}
					cb[p] = nums[i];				
				//if nums[i] falls into second half	
				}else{
					end = end == len-1? 0 : end+1;
					int p = end;
					//shift the second half to right circularly
					while(cb[p]>nums[i]){
						int next = p+1<len? p+1: 0;
						cb[next] = cb[p];
						p--;
					}
					cb[p] = nums[i];
				}
			}			
		}
		return cb;
	}
	
	
	//fat partition
	public static void ThreeWayPartition(int[] nums, int target){
		//the next index for number < p
		int top=0;
		//the next index for number > p
		int bottom = nums.length-1;
		//the current pointer index
		int p=0;
		
		while(p<=bottom){
			if(nums[p]>target){
				swap(nums, p, bottom);
				bottom--;
			}else if(nums[p]<target){
				swap(nums, p, top);
				top++;
				p++;
			}else{
				p++;
			}
		}
		
		//in the end, top, bottom is the two indices dividing the array
	}
	
	public static class Pair implements Comparable<Pair>{
		int num; int index;
		
		public int compareTo(Pair p){
			return this.num - p.num;
		}
	}
	
	/*
	 * http://www.careercup.com/question?id=6515637035728896
	 */
	public static int findFirstUnique(Pair[] list){
		Arrays.sort(list);
		int min = Integer.MAX_VALUE;
		for(int i=0; i<list.length; i++){
			if(i+1<list.length && list[i+1].num!=list[i].num){
				min = Math.min(min, i);
			}else{
				int t = i;
				while(t+1<list.length && list[t+1].num==list[i].num)
					t++;
				i = t;
			}
			
		}
		return min;		
	}
	
	
	
	public static void quickSort(int[] nums){
		quickSort(nums, 0, nums.length);
		System.out.println(Arrays.toString(nums));
	}
	public static void quickSort(int[] nums, int low, int high){
        if(low<high){
            int pIndex = partition(nums, low, high);
            quickSort(nums, low, pIndex-1);
            quickSort(nums, pIndex+1, high);
        }  
    }
    
    public static void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
    public static int partition(int[] nums, int low, int high){
        int mid = high; //(low+high)>>>1
        int p = nums[mid];
        swap(nums, mid, high);
        int index = low;        
        for(int i=low; i<high; i++){
            if(nums[i]<p){
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, index, high);
        return index;
    }
	
	public static void insertionSort(int[] nums){
		int len = nums.length;
		for(int i=1; i<len; i++){
			int j = i;
			int temp = nums[j];
			while(j-1>=0 && temp<nums[j-1]){
				nums[j] = nums[j-1];//shift
				j--;
			}
			nums[j] = temp;
		}
		
		System.out.println(Arrays.toString(nums));
	}

}
