package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class NumberQ {
	
	
	public static class Node{
		int d;
		Node next;
		public Node(int v){
			d = v;
		}
	}
	
	static int left = 0;
	public static Node add(Node a1, Node a2){
		if(a1==null && a2==null)
			return null;
		
		Node n;
		if(a1==null)
			n = add(null, a2.next==null?null:a2.next);
		else if(a2==null)
			n = add(a1.next==null?null:a1.next, null);
		else
			n = add(a1.next==null?null:a1.next, a2.next==null?null:a2.next);
		
		int sum = left;
		if(a1!=null)
			sum = sum + a1.d;
		if(a2!=null)
			sum = sum + a2.d;
		
		Node n2 = new Node(sum);
		n2.next = n;
		
		return n2;
		
	}
	
	protected static String alphabetString = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
	
	public static String base62Encoding(long number){
		StringBuffer sb = new StringBuffer();
		int base = alphabetString.length();
		while(number>0){
			int pos = (int) (number%base);
			char c = alphabetString.charAt(pos);
			sb.append(c);
			number = number/base;
		}
		
		return sb.reverse().toString();
		
	}
	
	public static long base62decoding(String code){
		Long result = 0l;
		int base = alphabetString.length();
		for(int i=0;i<code.length();i++){
			char c = code.charAt(i);
			int pos = alphabetString.indexOf(c);
			result = result*base + pos;
		}
		return result;
	}
	
	protected static char[] alphabet = alphabetString.toCharArray();
	protected static int base_count = alphabet.length;
	
	public static String encode(long num){
		String result = "";
		long div;
		int mod = 0;
		
		while (num >= base_count) {
			div = num/base_count;
			mod = (int)(num-(base_count*(long)div));
			result = alphabet[mod] + result;
			num = (long)div;
		}
		if (num>0){
			result = alphabet[(int)num] + result;
		}
		return result;
	}
	
	
	public static int FindMinimumDisTwoNumbers(int[] numbers, int i, int j){
		int iP = -1;
		int jP = -1;
		
		int min = Integer.MAX_VALUE;
		
		for(int k=0; k<numbers.length; k++){
			if(numbers[k] == i)
				iP = k;
			
			if(numbers[k] == j)
				jP = k;
			
			if(iP!=-1 && jP!=-1 && Math.abs(iP - jP) <= min )
				min = Math.abs(iP - jP);
			
		}
		
		return min;
			
	}
	
	
	public static int secondLargest(int[] numbers){
		
		if(numbers.length==0)
			return Integer.MIN_VALUE;
		
		if(numbers.length==1)
			return numbers[0];
		
		int result = numbers[0]>numbers[1]?numbers[1]:numbers[0];
		int max = numbers[0]>numbers[1]?numbers[0]:numbers[1];
		
		for(int i = 2; i<numbers.length-1; i++){
			if(numbers[i]>=max){
				result = max;
				max = numbers[i];
			}
			
			if (numbers[i]<max && numbers[i]>=result)
				result = numbers[i];		
		}
		
		return result;
	}
	
	public static int FindNextGreaterNumberWithSameSetOfDigits(int[] digits){
		if(digits.length==1)
			return digits[0];
		
		int min = digits[digits.length-1];
		
		for(int i=digits.length-2; i>=0; i--){
			if(digits[i]<min){
				//swap(i, 0);		
				digits[0] = digits[i];
				digits[i] = min;				
				for(int j=digits.length-1; j<i; j--){
					for(int k=j; k<i;k--){
						if(digits[k]>digits[j]){
							int temp = digits[k];
							digits[k] = digits[j];
							digits[j] = temp;
						}
					}					
				}
				
				return i;
			}
		}
		
		return -1;
		
	}
	
	public static int swapNoNegative(int[] nums){
		int swap = 0;	
		int zero = 0;	
		
		while(zero<nums.length){
			while(zero<nums.length && nums[zero]!=0)
				zero++;		
			int nonZero = zero;
			while(nonZero<nums.length && nums[nonZero]==0)
				nonZero++;	
			if(nonZero == nums.length)
				return swap;
			else{
				swap++;
				int t = nums[zero];
				nums[zero] = nums[nonZero];
				nums[nonZero] = t;
			}
		}
		
		return swap;
	}
	
	public static String numberToExcelCell(int num){
		StringBuilder sb = new StringBuilder();
		
		while(num>0){
			int pos = (num-1)%26;
			char c = (char)(pos + 'A');
			sb.append(c);
			num = (num-1)/26;
		}
		
		return sb.reverse().toString();
	}
	public static int MaximumSumPathinTwoArrays(int[] num1, int[] num2){
		HashMap<Integer, Integer> num2Sum = new HashMap<Integer, Integer>();
		
		int sum = 0;
		for(int i=0; i<num1.length; i++){
			sum = num1[i] + sum;
			num2Sum.put(num1[i], sum);
		}
		
		sum = 0;
		
		for(int i=0; i<num2.length; i++){
			sum = num2[i] + sum;
			if(num2Sum.get(num2[i])!=null){ 
				if(num2Sum.get(num2[i])>sum)
					sum = num2Sum.get(num2[i]);
				else	
					num2Sum.put(num2[i], sum);
			}
		}
		
		return sum;
	}
	
	
	public static int[] buySellStock(int[] nums){
		int[] times = new int[2];		
		times[0] = 0;
		times[1] = times[0];
		
		int minIndex = 0;
		int min = nums[0];
		int nextMax = min;
		int maxDiff = nextMax - min;
		
		for(int i=1; i<nums.length; i++){
			
			if(nums[i]>nextMax)
				nextMax = nums[i];
			
			if(nums[i]<min){
				minIndex = i;
				min = nums[i];
				nextMax = min;
			}
			
			if(nextMax - min > maxDiff){
				times[0] = minIndex;
				times[1] = i;
			}
			
		}
		
		
		return times;
	}
	
	public static int[] findNextBigNum(int[] nums){
		int[] res = new int[nums.length];
		res[nums.length-1] = -1;
		for(int i = nums.length-2; i>=0; i--){
			if(nums[i] < nums[i+1])
				res[i] = nums[i+1];
			else if(nums[i]<res[i+1])
				res[i] = res[i+1];
			else
				res[i] = -1;
		}
		return res;
	}
	
	/*
	 * num2 > num3
	 */
	public static int findMedian(int num1, int num2, int num3){		
		return Math.min(Math.max(num1, num2),  num3);		
	}
	
	/*
	 * len1 > len2
	 */
	public static int findMedian(int[] num1, int start1, int end1, int[] num2, int start2, int end2){
		System.out.println(start1 + " - " + end1 + " - " + start2 + " - " + end2);
		
		int len1 = end1 - start1 + 1;
		int len2 = end2 - start2 + 1;

		if(len1 == 1 && len2 == 1)
			return (num1[start1] + num2[start1])/2;

		if(len1 == 1 && len2 > 1){
			if(len2%2==0)
				return findMedian(num1[start1], num2[(start2+end2)/2],  num2[(start2+end2)/2+1]);
			if(len2%2==1){
				int m2 = num2[(start2+end2)/2];
				int m1 = findMedian(num1[start1], num2[(start2+end2)/2-1], num2[(start2+end2)/2+1]);
				return (m1+m2)/2;
			}				
		}

		if(len1 == 2 && len2 == 2){
			return (Math.min(num1[end1], num2[end2]) + Math.max(num1[start1], num2[start2]))/2;						
		}

		if(len1 == 2 && len2 > 2){
			if(len2%2==0){
				return (Math.max(num1[start1], num2[(start2+end2)/2]) + Math.min(num1[end1], num2[(start2+end2)/2+1]))/2;
			}
			if(len2%2==1){
				int m2 = num2[(start2+end2)/2];
				int m1 = Math.max(num1[start1], num2[(start2+end2)/2-1]);
				int m3 = Math.min(num1[end1], num2[(start2+end2)/2+1]);				
				return findMedian(m2, m1, m3);
			}			 
		}

		int n1 = (start1+end1)/2;
		int n2 = (start2+end2)/2;

		if(num1[n1]>=num2[n2]){
			return NumberQ.findMedian(num1, start1, n1, num2, start2 + (end1-n1+1), end2);						
		}else{
			return NumberQ.findMedian(num1, n1, end1, num2, start2, end2- (n1-start1+1));			
		}	

	}
	
	public static int[] findLIS(int[] nums){
		int maxLIS = 0;
		
		int[] lens = new int[nums.length];
		int[] index = new int[nums.length];
		
		for(int i=0; i<nums.length; i++){
			lens[i] = 1;
			index[i] = i;
		}
		
		for(int i = 1; i<nums.length; i++){			
			for(int j=0;j<i;j++){
				if(nums[j] < nums[i] && lens[i] < lens[j] + 1){
					lens[i] = lens[j] + 1;
					index[i] = j;
				}					
			}
		}
		
		int winner = 0;
		for(int i=1; i<nums.length; i++){
			if(lens[i] > lens[winner])
				winner = i;
		}
		
		int[] lis = new int[lens[winner]];
		
		int pos = winner;
		while(lens[pos]>1){
			lis[--lens[pos]] = nums[pos];
			pos = index[pos];
		}
		
		lis[--lens[pos]] = nums[pos];
		
		return lis;
	}
	
	public static String number2Binary(int num){
		int BYTES = 32;
		boolean positive = num>0?true:false;
		
		num = Math.abs(num);
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while(num!=0){
			sb.append(num%2);
			num = num/2;
			counter++;
		}
		
		while(counter<BYTES-1){
			counter++;
			sb.append("0");
		}
		
		sb.append(positive?"0":"1");
		
		return sb.toString();
	}
	
	public static int[] MaxWithinKWindow(int[] nums, int k){
		int[] result = new int[nums.length - k + 1];
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int i=0; i<k; i++){
			while(!dq.isEmpty() && nums[dq.getLast()] < nums[i])
				dq.removeLast();			
			dq.addLast(i);
		}
		
		
		for(int i=k; i<nums.length; i++){
			result[i-k] = nums[dq.getFirst()];
			
			while(!dq.isEmpty() && dq.getFirst() <= i-k )
				dq.removeFirst();
			
			while(!dq.isEmpty() && nums[dq.getLast()] < nums[i])
				dq.removeLast();			
			dq.addLast(i);
			
		}
		
		result[nums.length - k] = nums[dq.getFirst()];
		return result;
	}
	
	public static int minToRemoved(int[] nums){
		int distant = 0;
		
		for(int i=0; i<nums.length; i++){
			int min = nums[i];
			int max = nums[i];
			int end_index = i;
			for(int j=i; j<nums.length; j++){				
				if(nums[j] < min)
					min = nums[j];				
				if(nums[j] > max)
					max = nums[j];
				if(max<=2*min){
					end_index = j;
				}				
			}			
			if(distant < end_index - i)
				distant = end_index - i;			
		}
		
		return nums.length - distant - 1;
		
	}
	
	public static Node addByLinkedList(Node head){
		Node rev = NumberQ.reverseList(head);
		
		Node head2 = rev;
		
		int left = 1;
		Node pre = null;
		while(rev!=null){
			pre = rev;
			int result = rev.d + left;	
			rev.d = result%10;			
			left = result/10;
			rev = rev.next;
		}
		
		if(left==1){
			Node newN = new Node(left);
			pre.next = newN;
		}
		
		return NumberQ.reverseList(head2);
	}
	
	public static Node reverseList(Node head){
		if(head==null)
			return null;
		if(head.next==null)
			return head;
		
		Node curr = head;
		Node pre = null;
		
		while(curr!=null){
			Node t = curr.next;
			curr.next = pre;
			pre = curr;
			curr = t;
		}
		
		return pre;
	}
	
	
	public static List<Integer> findKNearest(int[] nums, int found, int k){
		List<Integer> result = new ArrayList<Integer>();
		
		int index = NumberQ.findNearestIndex(nums, found);
		
		int i = index;
		int j = index + 1;
		while(result.size()<k){
			
			if(i<0 && j>nums.length-1)
				break;
			else if(j>nums.length-1){
				result.add(nums[i]);
				i--;
			}else if(i<0){
				result.add(nums[j]);
				j++;
			}else if( found - nums[i] > nums[j] - found ){
				result.add(nums[j]);
				j++;
			}else{
				result.add(nums[i]);
				i--;
			}	
			
		}
		
		return result;
		
	}
	
	
	public static int findNearestIndexBinary(int[] nums, int found){
		if(nums[0]>=found)
			return 0;
		
		if(nums[nums.length-1]<=found)
			return nums.length-1;
		
		int start = 0;
		int end = nums.length -1;
		
		return 	findNearestIndexBinary2(nums, found, start, end);

	}
	
	public static int findNearestIndexBinary2(int[] nums, int found, int start, int end){
		int mid = (start + end)/2;
		
		if(nums[mid] <= found && nums[mid+1]>found)
			return mid;
		else if(nums[mid]>found){
			return findNearestIndexBinary2(nums, found, start, mid-1);
		}else
			return findNearestIndexBinary2(nums, found, mid+1, end);
	}
	
	public static int findNearestIndex(int[] nums, int found){
		if(nums[0]>=found)
			return 0;		
		for(int i=0; i<nums.length-1; i++){
			if(nums[i]<=found && nums[i+1]>found){
				return i;
			}				
		}		
		return nums.length-1;
	}
	
	
	public static String findCol(int n){
		StringBuffer sb = new StringBuffer();
		
		int d = 0;
		int a = 'A';
		
		while(n>0){
			d = (n-1)%26;
			sb.append((char)(d+a));
			n = (n - d)/26;
		}
		
		return sb.reverse().toString();
	}
	
	public static List<Integer> findMaxArithmeticsSeq(int[] num){
		int maxL = 0;
		List<Integer> result = new ArrayList<Integer>();
		//the difference between two numbers in the list defines the maxL of the sequence
		//store the mapping will help to reduce the number of the iterations		
		Map<Integer, Integer> diff2Length = new HashMap<Integer, Integer>();
		
		for(int i=0; i<num.length-1; i++){			
			for(int j=i+1; j<num.length; j++){
				int diff = num[j]-num[i];
				//we don't need to continue since max Length for this difference is already found
				if(diff2Length.containsKey(diff))
					continue;
				List<Integer> found = new ArrayList<Integer>();
				found.add(num[i]);
				int pre = num[i];
				for(int k=j; k<num.length; k++){
					if(num[k] - pre == diff){
						found.add(num[k]);
						pre = num[k];
					}
				}				
				diff2Length.put(diff, found.size());
				if(found.size() > maxL){
					maxL = found.size();
					result = found;
				}
			}						
		}		
		return result;
	}
	
	public static void printPermutation(int[] nums, int start){
		if(start == nums.length-1){
			System.out.println(Arrays.toString(nums));
			return;
		}
		
		for(int k=start; k<nums.length; k++){				
			swap(nums, start, k);								
			printPermutation(nums, start+1);
			swap(nums, start, k);
		}													
	}
	
	public static ArrayList<ArrayList<Integer>> printPermutation2(int[] nums){
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<Integer>());		
		for(int i=0; i<nums.length; i++){
			ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
			for(ArrayList<Integer> each : result){				
				for(int index = 0; index<=each.size(); index++){
					ArrayList<Integer> dest = new ArrayList<>(each);
					dest.add(index, nums[i]);
					temp.add(dest);
				}				
			}			
			result = temp;			
		}		
		return result;
	}
	
	
	public static List<List<Integer>> printPermutation3(List<Integer> nums){
		List<List<Integer>> result = new ArrayList<>();
		if(nums.size()==0){
			result.add(new ArrayList<Integer>());
			return result;
		}
		Integer n = nums.remove(0);
		List<List<Integer>> result2 = printPermutation3(nums);
		for(List<Integer> each : result2){				
			for(int index = 0; index<=each.size(); index++){
				ArrayList<Integer> dest = new ArrayList<>(each);
				dest.add(index, n);
				result.add(dest);
			}				
		}			
		return result;
	}
	
	
	private static void swap(int[] nums, int i, int j){
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public static void printNumbersInOrder(int[] nums){
		Set<Integer> s = new TreeSet<Integer>();
		
		for(int i=0; i< nums.length; i++)
			s.add(nums[i]);
		
		//for(Integer i : s)
			//System.out.println(i+"");
		
		Integer[] result = s.toArray(new Integer[s.size()]);
		for(int k=0; k<result.length; k++)
			System.out.println(result[k]);
		
	}
	
	public static boolean ifZeroSumExit(int[] nums){
		Set<Integer> s = new TreeSet<Integer>();		
		for(int i=0; i< nums.length; i++)
			s.add(nums[i]);	
		
		Integer[] result = s.toArray(new Integer[1]);
		
		for(int k=0; k<result.length; k++){
			int l = k+1;
			int m = result.length-1;
			
			while(l<m){
				int sum = result[k] + result[l] + result[m] ;
				if(sum == 0)
					return true;
				
				if(sum > 0) 
					m--; 
				else	
					l++;
			}			
		}				
		return false;
	}
	
	
	/*
	 * printAllZeroSum2(sorted, new Stack[sorted.length], 0, 0, sum)		
	 */
	public static void printAllZeroSum2(int[] sorted, int[] stack, int stackLen, int startIndex, int target){
		if(target==0){
			if(startIndex!=0){	
				System.out.println(Arrays.toString(Arrays.copyOf(stack, stackLen)));
				return;
			}
		}
		
		if(startIndex>sorted.length-1 || sorted[startIndex]>target)
			return;
		
		while(startIndex<sorted.length && sorted[startIndex]<=target){
			stack[stackLen] = sorted[startIndex];
			printAllZeroSum2(sorted, stack, stackLen+1, startIndex+1, target-sorted[startIndex]);
			startIndex++;
		}				
	}
	
	public static void AllSubSetSum(int[] nums, int target){
		Arrays.sort(nums);		
		printAllZeroSum2(nums, new int[nums.length], 0, 0, target);
	}
	
	public void populateSubset(final int[] data, int fromIndex, 
			final int[] stack, final int stacklen,
			final int target) {
		System.out.println(" - " + fromIndex + " - " + target + " - "+ stacklen + " - " + Arrays.toString(stack));
		if (target == 0) {
			// exact match of our target. Success!
			if(fromIndex!=0){
				print(Arrays.copyOf(stack, stacklen));
				return;
			}
		}

		while (fromIndex < data.length && data[fromIndex] > target) {
			// take advantage of sorted data.
			// we can skip all values that are too large.
			fromIndex++;
			//return;
		}

		while (fromIndex < data.length && data[fromIndex] <= target) {
			// stop looping when we run out of data, or when we overflow our target.
			stack[stacklen] = data[fromIndex];
			populateSubset(data, fromIndex + 1, stack, stacklen + 1, target - data[fromIndex]);
			fromIndex++;
		}
	}

	private void print(int[] js) {    
        System.out.println(Arrays.toString(js));
    }
	
	
	public void getSubSetsForSum(int[] data, int target){		
		Arrays.sort(data); 
		System.out.println(Arrays.toString(data));
		populateSubset(data, 0, new int[data.length], 0, target);
	}
	public static int reverseNum(int num){
		assert(num<=0);		
		int result = 0;
		while(num!=0){
			result = num%10 + result*10;
			num = num/10;
		}		
		return result;
	}
	/**
	 * method use no extra space
	 * @param num
	 * @return
	 */
	public static boolean isPanlindrome(int num){
		assert(num<=0);
		
		if(num<10)
			return true;
		
		while(num>=10){
			int first =num;
			int counter=0;
			while(first>=10){
				first = first/10;
				counter++;
			}			
			int last = num%10;			
			
			if(first!=last)
				return false;
			else{
				num = (num - first*10*counter)/10;				
			}				
		}	
		
		return true;
	}
	
	
	public static int getFirstDigit(int num){
		int result=0;
		while(num!=0){
			result = num%10;
			num = num/10;
		}
		return result;
	}
	
	public static String divisionToString(int num1, int num2){
		int beforeD = num1/num2;
		int afterD = num1%num2;
		
		if(afterD==0)
			return String.valueOf(beforeD);
		else{
			StringBuilder sb = new StringBuilder();
			sb.append(beforeD);
			sb.append('.');
			int counter = -1;
			while(afterD<num2){
				afterD = afterD * 10;
				counter++;
				if(counter>0)
					sb.append("0");
			}
			
			int pre = afterD;
			while(afterD!=0){
				int temp = afterD/num2;
				afterD = (afterD%num2)*10;
				if(pre == afterD){
					sb.append('(');
					sb.append(temp);
					sb.append(")");
					break;
				}else{
					pre = afterD;
					sb.append(temp);
				}				
			}				
			return sb.toString();	
		}	
	}
	
	
	

	public static int reverseBits(int num, int i, int j){
		int biti = (num>>i)&1;
		int bitj = (num>>j)&1;
		
		if((biti^bitj)==1){
			num = (1<<j)^num;
			num = (1<<i)^num;
		}
		return num; 		
	}
	
	public static int reverseBits(int num){
		int size =  8 * 4 ;
		for(int i=0; i<size/2; i++){
			num = reverseBits(num, i, size-i-1);
		}		
		return num;
	}
	
	public static int reverseBits2(int num){
		int b=0;	
		int counter = 31;
		while(counter!=0){
			b|=(num&1);		
			num >>=1;
			b = b<<1;
			counter--;
		}
		return b;
		
	}
	
	/**
	 *  Given an array that has positive numbers and negative numbers and zero in it. 
	 *  You need to seperate the negative numbers and positive numbers in such a way that 
	 *  negative numbers lies to left of zero 
	 *  and positive numbers to the right and the original order of elements should be maintained
	 * @param num
	 */
	public static void orderByZero(int[] num){
		
		for(int i = 1 ; i< num.length; i++){
			int j = i ;			
			while(j>0){
				if((num[j]<0 && num[j-1]>=0) || (num[j]==0 && num[j-1]>0)){
					int t = num[j];
					num[j] = num[j-1];
					num[j-1] = t;
					j--;
				}else{				
					break;
				}
			}			
		}
	}
	
public static void orderByZero2(int[] num){
	System.out.println(Arrays.toString(num));
	
	int neg = -1;
	int pos = num.length;
	
	int i = 0;
			//assume there is one zero
			while(neg<pos-1 && i < num.length){
				
				if((num[i]<=0)){
					neg++;
					int t = num[neg];
					num[neg] = num[i];
					num[i] = t;
					
				}
				
				//if((num[i]>0)){	
				//	pos--;
				//	int t = num[pos];
				//	num[pos] = num[i];
				//	num[i] = t;
					
				//}
				
				i = neg + 1;
				//System.out.println(Arrays.toString(num));				
			}					
	}
		
	
	public static void printPrimary(int num){
		boolean[] isPrimary = new boolean[num+1];		
		isPrimary[0] = false;		
		if(num>=1)
			isPrimary[1] = false;
		
		for(int i=2; i<=num; i++)
			isPrimary[i] = true;
		
		for(int i=2; i<=Math.sqrt(num); i++){
			if(isPrimary[i]){
				
				for(int j = i*i; j<=num; j = j + i){
					isPrimary[j] = false;
				}				
			}
		}
		
	}

	public static boolean isPrimary(int num){
		if(num == 0 || num ==1 )
			return false;
		if(num == 2 || num == 3)
			return true;		
		
		for(int i = 2; i< (int)Math.sqrt(num)+1; i++){
			if(num%i==0)
				return false;
		}
		return true;		
	}
	
	/**
	 * num (num-1)!
	 * @param k
	 * @param num
	 * @return
	 */
	public static boolean isDivisible(int k, int num){
		if(num ==1 )
			return true;
		if(k>=num)
			return true;		
		
		if((num==2 || num==3 || num==4))
			if(k<num)
				return false;
		
		for(int i = 2; i< (int)Math.sqrt(num)+1; i++){
			if(num%i==0)
				return true;
		}
		return false;		
	}
	
	public static int findMaxOnes(int[][] nums){
		int max = nums[0].length-1;
		int index = 0;
		
		for(int i = 0; i<nums.length; i++){
			for(int j = max; j>=0; j--){
				if(nums[i][j]==0)
					break;
				max = j;
				index = i;
			}			
		}
		
		return index;
	}
	
	/*
	 * return the index of first occurrence 
	 */
	public static int findFirstNumber(int[] sorted, int k){
		return findFirstNumberHelper(sorted, k, 0, sorted.length);
	}
	
	public static int findFirstNumberHelper(int[] sorted, int k, int start, int end){
		if(start>end)
			return -1;
		
		int mid = (start+end)/2;
		
		if(mid==0 && sorted[mid]==k)
			return mid;
		else if(mid>0 && sorted[mid]==k && sorted[mid-1]<k)
			return mid;
		else if(sorted[mid]<k)
			findFirstNumberHelper(sorted, k, mid+1, end);
		else
			findFirstNumberHelper(sorted, k, start, mid-1);
		
		return -1;
	}

	
	public static float calculateSqrt(int number){
		float precision = 0.0001f;
		float x = number;
		float y = 1f;
		while(x-y>precision){
			x = (x+y)/2;
			y = number/x;
		}
		return y;
	}
	
	
	public int findFirstNumberInInfiniteArray(int[] sorted, int k){
		int start = 0;
		if(sorted[start] == k)
			return start;
		
		start = 1;
		while(sorted[start]<k){
			start = start*2;
		}
		
		return findFirstNumberHelper(sorted, k, start/2, start);
			
	}
	
}
