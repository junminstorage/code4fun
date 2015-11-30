package org.blueocean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.blueocean.NumberQ.LinkedList;
import org.blueocean.NumberQ.MatrixMinQ;

import junit.framework.Assert;
import junit.framework.TestCase;

public class NumberQTest extends TestCase {
	
	public void testpower2(){
		assertEquals(2, NumberQ.power(2, 1));
		assertEquals(4, NumberQ.power(2, 2));
	}
	
	public void testFindInInfiniteArray(){
		System.out.println(-2>>>1);
		System.out.println(-2>>1);
		
		System.out.println(1<<1);
		System.out.println(1<<27);
	}
	
	public void testqazBymergeSort(){
		int[] nums = new int[]{2, 1, 3, 5, 6, 1};
		NumberQ.qazBymergeSort(nums);
		
		System.out.println(NumberQ.maxQAZ(nums));
	}
	
	public void testmergeSort(){
		int[] nums = new int[]{2, 1, 5};
		NumberQ.mergeSort(nums);
		System.out.println(Arrays.toString(nums));
		
	}
	
	public void testFindNGE(){
		System.out.println(Arrays.toString(NumberQ.findNGE(new int[]{2, 4, 1, 5, 8})));
		System.out.println(Arrays.toString(NumberQ.findNGEBackward(new int[]{2, 4, 1, 5, 8})));
	}
	
	public void testFindLocalMin(){
		System.out.println(NumberQ.findLocalMin(new int[]{1}));
		
		System.out.println(NumberQ.findLocalMin(new int[]{1,2}));
		
		System.out.println(NumberQ.findLocalMin(new int[]{3,1,2}));
		
		System.out.println(NumberQ.findLocalMin(new int[]{1, 2, 3, 5, 4, 7}));
	}
	
	public void testfindMinUnsortedWindow(){
		System.out.println(Arrays.toString(NumberQ.findMinUnsortedWindow2(new int[]{2, 3, 4, 6, 4, 3, 2, 7, 8, 9})));
		System.out.println(Arrays.toString(NumberQ.findMinUnsortedWindow2(new int[]{7,8,9,2,3,2,3,4})));
		System.out.println(Arrays.toString(NumberQ.findMinUnsortedWindow2(new int[]{1,2,3,4,5,6,7,4,3,6,5,6,7,8,9})));
	}
	
	public void testCountingSlices(){
		System.out.println(NumberQ.countSlices(new int[]{3, 5, 7, 6, 3}, 2));
	}
	
	public void testtest(){
		
		System.out.println(-48%18);
		
		System.out.println(NumberQ.gcd(48, -28));
		System.out.println(NumberQ.gcd(-48, -18));
		System.out.println(NumberQ.gcd(18, 0));
		System.out.println(Float.valueOf("-.12"));
		
		System.out.println(Float.valueOf("+.12"));
		
		System.out.println(Float.valueOf("099"));
		
		System.out.println(NumberQ.isStringNumber("-.3"));
		System.out.println(NumberQ.isStringNumber("-0.3"));
		System.out.println(NumberQ.isStringNumber("-00.3"));
		System.out.println(NumberQ.isStringNumber("-.03"));
		System.out.println(NumberQ.isStringNumber("0.3"));
		System.out.println(NumberQ.isStringNumber("00.3"));
		
		System.out.println(NumberQ.isStringNumber("99"));
		
	}
	
	public void testReverseInt(){
		System.out.println(NumberQ.reverseInt(1147483649));
		Boolean match = false;
		
		System.out.println(match);
		
		Integer a = new Integer(10);
		Integer b = new Integer(10);
		System.out.println("a == b: " + (a == b));
		
		/*
		 * It may be worth noting that autoboxing is guaranteed to return the same object 
		 * for integral values in the range [-128, 127], 
		 * but an implementation may, at its discretion, cache values outside of that range.
		 */
		a = 127;
		b = 127;
		System.out.println("a == b: " + (a == b));
		
		String c = new String("10");
		String d = new String("10");
		System.out.println("c == d: " + (c == d));
		
		c = "10";
		d = "10";
		System.out.println("c == d: " + (c == d));
	}
	public void testDivideToString(){
		System.out.println(NumberQ.divideToString(0, 1));
		
		System.out.println(NumberQ.divideToString(15, 11));
		
		System.out.println(NumberQ.divideToString(1, 3));
		
		System.out.println(NumberQ.divideToString(1, 3333));
		
		System.out.println(NumberQ.divideToString(77777, 3333));
	}
	
	public void test112(){
		NumberQ.biggerGreater("gojh");
		//NumberQ.insertionSort("234571".toCharArray(), 2);
	}
	
	public void testSqrt(){
		System.out.println(Long.MAX_VALUE);
			long r2 = 200_000_000l;
			r2 = r2 * r2 -1;
			System.out.println(r2);
			
	        long sqrt = Math.min(r2/2, 2_000_000_000l);     
	        while(sqrt * sqrt > r2 ){
	        	 System.out.println(sqrt);
	            sqrt = ((long)(r2 / sqrt) + sqrt) >>> 1;
	        }
	        
	       System.out.println(sqrt);
	    }
	
	
	public void testbinarySearch2(){
		int[] nums = new int[]{1, 4, 6, 9, 10};
		System.out.println(NumberQ.binarySearch2(nums, 5, 0, 4));
		System.out.println(NumberQ.binarySearch2(nums, 5, -1, 4));
		
	}
	
	public void testLISNLOGN(){
		System.out.println(NumberQ.LISNLOGN(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
	}
	
	public void testallLexPerm() {
		String a = "ab";
		List<String> powerSet = new ArrayList<String>();
		for (int j = 0; j < Math.pow(2, a.length()); j++) {
			StringBuilder newa = new StringBuilder();
			for (int i = 0; i < a.length(); i++) {
				if (!Integer.toBinaryString(j & (1 << i)).equals("0")) {				
					newa.append(a.charAt(i));
				}
			}
			powerSet.add(newa.toString());
		}
		Collections.sort(powerSet);
	}

	
	public void testpermutation(){
		List<int[]> RE = NumberQ.permutation(new int[]{2, 3, 8});
		for(int[] r : RE){
			System.out.println(Arrays.toString(r));
		}
	}
	
	public void testfind1(){
		System.out.println(NumberQ.find1(new int[]{0,0,1}, 0, 2));
	}
	
	public void testNG(){
		System.out.println(Arrays.toString(NumberQ.findNextGreater(new int[]{40,50,11,32,55,68,75})));
	}
	
	public void testSqApple(){
		System.out.println (NumberQ.binarySearch(new int[]{1,  3,  5,  7, 9}, 0, 5, 1));
		
		//System.out.println(~1);
		//System.out.println(~2);
		//System.out.println(~0);
		//System.out.println(NumberQ.SqApple(11));
	}
	
	public void testminLenthSubarraySum(){
		System.out.println(NumberQ.minLenthSubarraySum(new int[]{3,  -1, 3, 5, 7}, 6));
	}
	
	public void testcombination(){
		NumberQ.combination(new int[]{1, 2, 5});
	}
	
	public void testalSequenceNumbersSumTo(){
		String s = "a\0b";
		System.out.println(s.length());
		int t = s.charAt(1);
		char c = 0;
		//System.out.println(s.charAt(1));
		System.out.println(c);
		NumberQ.alSequenceNumbersSumTo(1);
	}
	
	public void testinsertionSort(){
		int[] num = new int[]{2, 3, 5, 7, 1};
		NumberQ.insertionSort(num);
		System.out.println(Arrays.toString(num));
	}
	
	public void testnumOfCombinations(){
		System.out.println(NumberQ.numOfCombinations("111"));
	}
	public void testFactor(){
		NumberQ.factor(-192);
	}
	
	public void testShuffleSimple(){
		int[] res = new int []{2,1,1,1,3,4,4,4,5};
		NumberQ.shuffleSimple(res, 2);		
		System.out.println(Arrays.toString(res));
		
		res = new int []{4,1,1,1,4,1,1,1};
		NumberQ.shuffleSimple(res, 2);		
		System.out.println(Arrays.toString(res));
		
	}
	public void testShuffle(){
		int[] res = new int []{2,1,1,1,3,4,4,4,5};
		NumberQ.shuffle(res, 2);		
		System.out.println(Arrays.toString(res));
		
		res = new int []{4,1,1,1,4,1,1,1};
		NumberQ.shuffle(res, 2);		
		System.out.println(Arrays.toString(res));
		
		res = new int []{1, 1, 1, 1, 2};
		NumberQ.shuffle(res, 2);		
		System.out.println(Arrays.toString(res));
	}
	public void testaddRec(){
		LinkedList n1 = new LinkedList(2);
		n1.next =  new LinkedList(2);
		
		LinkedList n2 = new LinkedList(2);
		n2.next =  new LinkedList(8);
		n2.next.next =  new LinkedList(9);
		n2.next.next.next =  new LinkedList(8);
		
		int[] left = new int[1];
		LinkedList re = NumberQ.add(n1, n2);
		LinkedList c = re;
		while(c!=null){
			System.out.println(c.data);
			c = c.next;
		}
		System.out.println(left[0]);
		
		
	}
	
	public void testIsNumber(){
		System.out.println(NumberQ.isNumber(".1"));
		System.out.println(NumberQ.isNumber("0"));
		System.out.println(NumberQ.isNumber(".1."));
		System.out.println(NumberQ.isNumber(".12"));
		System.out.println(NumberQ.isNumber("11."));
		System.out.println(NumberQ.isNumber("011"));
	}
	
	public void testcanIWin(){
		System.out.println(NumberQ.canIWin(5, 12));
	}
	
	public void testmaxLengthPalindrome(){
		System.out.println(NumberQ.maxLengthPalindrome(new int[]{3, 4, 4, 5, 4}));
		System.out.println(NumberQ.maxLengthPalindrome(new int[]{4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4}));
	}
	
	public void testbuySellStock2(){
		NumberQ.buySellStock2(new int[]{100, 180, 260, 310, 40, 535, 695});
		NumberQ.buySellStock2(new int[]{100, 90, 80, 70, 40, 35, 5});
	}
	
	public void testArraytoString(){
		System.out.println(Arrays.toString(new int[]{1, 2, 3}));
		System.out.println(Arrays.toString(new char[]{1, 2, 3}));
		System.out.println(Arrays.toString(new String[]{"1", "2", "3"}));
	}
	
	public void testprintCombinations(){
		int[] numbers = new int[1];
		numbers[0] = 1;
		int[] newArray = Arrays.copyOf(numbers, numbers.length+1);
		newArray[newArray.length-1] = 2;
		
		for(int i: newArray){
			System.out.println(i);
		}
		
		int[] newArray2 = Arrays.copyOf(numbers, numbers.length+1);
		newArray2[newArray2.length-1] = 4;
		
		for(int i: newArray2){
			System.out.println(i);
		}
		
		NumberQ.printCombinations(4, 3);
	}
	public void testfind4SortedSubsequence(){
		NumberQ.find4SortedSubsequence(new int[]{12, 11, 10, 5, 6, 2, 30, 40}, 4);
	}
	
	public void testfindSortedSubSeq(){
		NumberQ.findSortedSubSeq(new int[]{12, 11, 10, 5, 6, 2, 30});
		NumberQ.findSortedSubSeq(new int[]{4, 3, 2, 1});
		NumberQ.findSortedSubSeq(new int[]{1, 2, 3, 4});
		NumberQ.findSortedSubSeq(new int[]{5,13,6,10,3,7,2});
	}
	
	public void testallPairs2(){
		NumberQ.allPairs2(new int[]{1, 1, 2, 2, 3, 3, 4, 3, 5, 6}, 6);
	}
	
	public void testallPairs(){
		NumberQ.allPairs(new int[]{1, 1, 2, 2, 3, 3, 4, 3, 5, 6}, 6);
	}
	
	public void testroman2Arabic(){
		System.out.println(NumberQ.roman2Arabic("MMXIV"));
		System.out.println(NumberQ.roman2Arabic("MMXV"));
	}
	
	public void testoutDiagonalFormat(){
		NumberQ.outDiagonalFormat(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
	}
	
	public void testfindZeroPositions(){
		List<Integer> 
			result = NumberQ.findZeroPositions(new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1 }, 2);
			
			System.out.println(result);
	}
	
	public void testgetSequencedNumbers(){
		NumberQ.getSequencedNumbers(new int[]{4, 5, 6, 7, 8, 9, 12, 15, 16, 17, 18, 20, 22, 23, 24, 27});
	}
	
	public void testAdd(){
		System.out.println(NumberQ.add(1, -21));
	}
	
	public void testfindPermutation(){
		int[] numbers = new int[]{2, 0, 3};
		
		System.out.println(NumberQ.findPermutation(numbers));
		
	}
	
	public void testshiftZeroToRight(){
		int[] numbers = new int[]{2, 0, 3, 0, 1, 4, 5, 0};
		NumberQ.shiftZeroToRight(numbers);
		System.out.println(Arrays.toString(numbers));
		numbers = new int[]{2, 0, 3, 2, 1, 9, 0, 8, 9, 0, 12};
		NumberQ.shiftZeroToRight(numbers);
		System.out.println(Arrays.toString(numbers));
		
		numbers = new int[]{2};
		NumberQ.shiftZeroToRight(numbers);
		System.out.println(Arrays.toString(numbers));
	}
	public void testmultiple(){
		System.out.println(Arrays.toString(NumberQ.multiple(new int[]{2, 3, 1, 4})));
	}
	
	public void testWap(){
		System.out.println(Arrays.toString(NumberQ.wap(new int[]{2, 3, 1, 0})));
	}
	
	public void testfindIndex(){
		System.out.println(Arrays.toString(NumberQ.findIndex(new int[]{1, 3, 11, 5, 7})));
	}
	public void testreplace0W5(){
		System.out.println(NumberQ.replace0W5(1000));
	}
	
	public void testfindkthMin(){
		System.out.println(NumberQ.findkthMin(new int[]{1, 3, 11, 5, 7}, 2));
		System.out.println(NumberQ.findkthMin(new int[]{1, 3, 5, 7, 11}, 1));
		System.out.println(NumberQ.findkthMin(new int[]{1, 3, 5, 7, 11}, 0));
		
		
	}
	
	public void testsqrt(){
		System.out.println(NumberQ.sqrt(144));
	}
	public void testprintPascalTriangle(){
		//NumberQ.printPascalTriangle(0);
		NumberQ.printPascalTriangle(3);
	}
	
	public void testprintAsList(){
		int[][] nums = {{1,3,5,7}, {2,4,6,8},{9,10,17,19}, {25,27,29,211}};
		
		MatrixMinQ q = new MatrixMinQ(nums);
		q.printAsList();
	}
	
	public void testBS2DArray1(){
		int[][] nums = {{1,3,5,7}, {2,4,6,8},{9,10,17,19}, {25,27,29,211}};
		
		System.out.println(NumberQ.BS2DArray1(nums, 4));
		
		System.out.println(NumberQ.BS2DArray1(nums, 6));
		
		System.out.println(NumberQ.BS2DArray1(nums, 8));
		
		System.out.println(NumberQ.BS2DArray1(nums, 0));
		
		System.out.println(NumberQ.BS2DArray1(nums, 212));
		
	}
	public void testBS2DArray(){
		int[][] nums = {{1,3,5,7}, {2,4,6,8},{9,10,17,19}, {25,27,29,211}};
		
		System.out.println(NumberQ.BS2DArray(nums, 4));
		
		System.out.println(NumberQ.BS2DArray(nums, 6));
		
		System.out.println(NumberQ.BS2DArray(nums, 8));
		
		System.out.println(NumberQ.BS2DArray(nums, 0));
		
//		System.out.println(NumberQ.BS2DArray(nums, 210));
		
	}

	public void testtoSLL(){
		NumberQ.NodeQ head1 = NumberQ.toDownList(new int[]{1, 3, 5});
		NumberQ.NodeQ head2 = NumberQ.toDownList(new int[]{2, 4, 6, 8});
		
		head1.next = head2;
		
		NumberQ.toSLL(head1);
		
		NumberQ.NodeQ current = head1;
		while(current!=null){
			System.out.println(current.data);
			current = current.next;
		}
		
		
	}
	public void testfloatToString(){
		NumberQ.floatToString(7.46f);
		NumberQ.floatToString(7.101f);
		NumberQ.floatToString(7.01f);
	}
	
	public void testPrintCombination(){
		float f = 8.75f%10;
		
		NumberQ.PrintCombination(3);
	}
	public void testfindCommonElements3Arrays(){
		int[] num1 = new int[]{1, 5, 10, 20, 40, 80};
		int[] num2 = new int[]{6, 7, 20, 80, 100};
		int[] num3 = new int[]{3, 4, 15, 20, 30, 70, 80, 120};
		
		NumberQ.findCommonElements3Arrays(num1, num2, num3);
		
		num1 = new int[]{1, 5, 5};
		num2 = new int[]{3, 4, 5, 5, 10};
		num3 = new int[]{5, 5, 10, 20};
		
		NumberQ.findCommonElements3Arrays(num1, num2, num3);
	}
	
	public void testfindFirstNoRepeatNumber(){
		System.out.println(NumberQ.findFirstNoRepeatNumber(new int[]{2, 1, 9, 8, 0, 0, -2, 0, 1, 6}));
		System.out.println(NumberQ.findFirstNoRepeatNumber(new int[]{-2, 1, 9, 8, 0, 0, -2, 0, 1, 6}));
		
	}
	
	public void testshiftZerosToRight(){
		//NumberQ.shiftZerosToRight(new int[]{7, -6, 5, 4, -3, 2, 1, 0, 9});
		NumberQ.shiftZerosToRight(new int[]{1, 9, 8, 0, 0, -2, 0, 1, 6});
	}
	
	public void testshufflePosNegm(){
		System.out.println(NumberQ.shufflePosNeg(new int[]{7, -6, 5, 4, -3, 2, 1, 0}));
		System.out.println(NumberQ.shufflePosNeg(new int[]{15, 2, 4, -8, -9, 5, -10, -23}));
	}
	
	public void testfindSubArrayHasSum(){
		System.out.println(NumberQ.findSubArrayHasSum(new int[]{7, 6, 5, 4, 3, 2, 1, 0}, 12));
		System.out.println(NumberQ.findSubArrayHasSum(new int[]{15, 2, 4, 8, 9, 5, 10, 23}, 23));
	}
	
	public void testfindKInDecreasedArray(){
		System.out.println(NumberQ.findKInDecreasedArray(new int[]{7, 6, 5, 4, 3, 2, 1, 0}, 7, 0, 8));
		
		System.out.println(NumberQ.findKInDecreasedArray(new int[]{7, 6, 5, 4, 3, 2, 1, 0}, 12, 0, 8));
	}
	
	public void testfindMaxPoint(){
		System.out.println(NumberQ.findMaxPoint(new int[]{1, 2, 3, 4, 5, 7, 4, 2}, 0, 8));
	}
	
	public void testcalculateSqrt(){
		System.out.println(NumberQ.calculateSqrt(100));
	}
	
	public void testFindMinimumDisTwoNumbers(){
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{3, 4, 5}, 3, 5));
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{1, 5, 3, 7, 2, 8, 3, 4, 5, 9, 9, 3, 1, 3, 2, 9}, 3, 9));
		
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{2, 5, 3, 5, 4, 4, 2, 3}, 3, 2));
		System.out.println(NumberQ.FindMinimumDisTwoNumbers(new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, 3, 6));
		
	}
	public void testSwapNoNegative(){
		System.out.println(NumberQ.swapNoNegative(new int[]{1,0, 0, 2, 0, 3}));
		System.out.println(NumberQ.swapNoNegative(new int[]{1, 0, 0, -6, 2, 0}));
		
	}
	
	public void testbase62Encoding(){
		System.out.println(NumberQ.base62Encoding(100));
		System.out.println(NumberQ.encode(100));
		
		System.out.println(NumberQ.base62Encoding(2_000_000_000));
		System.out.println(NumberQ.encode(2000000_000));
	}
	
	public void testbase62decoding(){
		System.out.println(NumberQ.base62decoding("2J"));
		//System.out.println(NumberQ.encode(100));
		
		System.out.println(NumberQ.base62decoding("43JwjC"));
		//System.out.println(NumberQ.encode(2000000_000));
	}
	
	public void testnumberToExcelCell(){
		System.out.println(NumberQ.numberToExcelCell(1));
		System.out.println(NumberQ.numberToExcelCell(26));
		System.out.println(NumberQ.numberToExcelCell(51));
		System.out.println(NumberQ.numberToExcelCell(52));
		System.out.println(NumberQ.numberToExcelCell(80));
		System.out.println(NumberQ.numberToExcelCell(676));
	}
	
	public void testFindMedian(){
		int[] num1 = new int[]{3, 6, 8, 11, 25};		
		int[] num2 = new int[]{4, 7, 8, 10, 15, 18, 40};		
		System.out.println(NumberQ.findMedian(num1, 0, 4, num2, 0, 6));
				
		num1 = new int[]{3};		
		num2 = new int[]{1};		
		System.out.println(NumberQ.findMedian(num1, 0, num1.length-1, num2, 0, num2.length-1));
		
		
	}
	
	public void testNumber2Binary(){
		
		System.out.println(NumberQ.number2Binary(-20));
	}
	
	public void testMinNumToRemoved(){
		//int[] nums = new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200};
		System.out.println(NumberQ.minToRemoved(new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{100, 8, 9, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{8, 9, 100, 10, 11, 12, 15, 200}));
		System.out.println(NumberQ.minToRemoved(new int[]{20, 4, 1, 3}));
		System.out.println(NumberQ.minToRemoved(new int[]{20, 7, 5, 6}));
		System.out.println(NumberQ.minToRemoved(new int[]{4, 7, 5, 6}));
		
	}
	public void testDivisionToString(){
		System.out.println(NumberQ.divisionToString(1, 2));
		System.out.println(NumberQ.divisionToString(1, 3));
		
		System.out.println(NumberQ.divisionToString(10, 2));
		
		System.out.println(NumberQ.divisionToString(10, 3));
		
		System.out.println(NumberQ.divisionToString(12, 5));
		
		
	}
	
	public void testAddByList(){
		
		NumberQ.Node n1 = new NumberQ.Node(1);
		NumberQ.Node n2 = new NumberQ.Node(3);
		NumberQ.Node n3 = new NumberQ.Node(9);
		n1.next = n2;
		n2.next = n3;
		
		NumberQ.Node r = NumberQ.addByLinkedList(n1);
		
	}
	
	public void testReverseList(){
		NumberQ.Node n1 = new NumberQ.Node(1);
		NumberQ.Node n2 = new NumberQ.Node(3);
		NumberQ.Node n3 = new NumberQ.Node(9);
		n1.next = n2;
		n2.next = n3;
		
		NumberQ.Node n = NumberQ.reverseList(n1);
		
	}
	
	public void testTest() throws CloneNotSupportedException{
		// int[] data = { 1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15 };

		//new NumberQ().test(data, 15);
		
		int[] nums = {-3, -1, -2,  -4, 5, 8};
		
		new NumberQ().getSubSetsForSum(nums, 0);
	}
	
	public void testPrintAllZeroSum(){
		int[] nums = {-1, -2, -3, -4, 5, 8};
		
		NumberQ.AllSubSetSum(nums, 0);
	
	}
	public void testFindMaxOnes(){
		int[][] nums = {{0,0,1,1}, {0,1,1,1},{0,0,0,1}, {1,1,1,1}};
		
		System.out.println(NumberQ.findMaxOnes(nums));
	}
	
	public void testOrderByZero(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		NumberQ.orderByZero(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] nums2 = {1, 2, 0, 3, 4, 5, 8};
		NumberQ.orderByZero(nums2);
		System.out.println(Arrays.toString(nums2));
		
		int[] nums3 = {1, 2, 0, -1, 3, 4, 5, 8};
		NumberQ.orderByZero(nums3);
		System.out.println(Arrays.toString(nums3));
		
	}
	
	public void testOrderByZero2(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		NumberQ.orderByZero2(nums);
		System.out.println(Arrays.toString(nums));
		
		int[] nums2 = {1, 2, 0, 3, 4, 5, 8};
		NumberQ.orderByZero2(nums2);
		System.out.println(Arrays.toString(nums2));
		
		int[] nums3 = {1, 2, 0, -1, 3, 4, 5, 8};
		NumberQ.orderByZero2(nums3);
		System.out.println(Arrays.toString(nums3));
		
		int[] nums4 = {1, 2, 0, -1, -2, 3, 4, -4, 5, 8};
		NumberQ.orderByZero2(nums4);
		System.out.println(Arrays.toString(nums4));
		
	}
	
	public void testIsPrimary(){
		assertTrue(NumberQ.isPrimary(3));
		assertTrue(NumberQ.isPrimary(2));
		assertTrue(NumberQ.isPrimary(5));
		
		assertFalse(NumberQ.isPrimary(4));
		assertFalse(NumberQ.isPrimary(6));
		assertTrue(NumberQ.isPrimary(23));
		assertFalse(NumberQ.isPrimary(24));
		assertFalse(NumberQ.isPrimary(25));
		
	}
	
	public void testFindKNearest(){
		int[] nums = {1, 2,  3, 4, 5, 8};
		
		System.out.println(NumberQ.findKNearest(nums, 6, 4));
		System.out.println(NumberQ.findKNearest(nums, 1, 4));
		System.out.println(NumberQ.findKNearest(nums, 100, 4));
		System.out.println(NumberQ.findKNearest(nums, 1, 40));
		System.out.println(NumberQ.findKNearest(nums, 6, 1));
			
	}
	
	public void testfindCol(){
		System.out.println(NumberQ.findCol(1));
		System.out.println(NumberQ.findCol(26));
		
		System.out.println(NumberQ.findCol(52));
		
		System.out.println(NumberQ.findCol(26*27));
	}
	
	public void testFindMaxArithmeticsSeq(){
		int[] nums = {1, 2, 5, 3, 4, 8, 5};
		
		System.out.println(NumberQ.findMaxArithmeticsSeq(nums));
		
	}
	
	public void testPrintPermutation(){
		int[] nums = {13, 4, 8};
		
		NumberQ.printPermutation(nums, 0);
	}
	
	public void testPrintPermutation2(){
		int[] nums = {13, 4, 8};
		
		ArrayList<ArrayList<Integer>>  result = NumberQ.printPermutation2(nums);
		
		for(ArrayList<Integer> item: result)
			System.out.println(item);
	}
	
	public void testPrintPermutation3(){
		Integer[] nums = {13, 4, 8, 1};
		List<Integer> numbers = new ArrayList<>(Arrays.asList(nums));
		
		List<List<Integer>> result = NumberQ.printPermutation3(numbers);
		
		for(List<Integer> item: result)
			System.out.println(item);
	}
	
	
	public void testPrintNumbersInOrder(){
		int[] nums = {10, 2, 13, 4, 8};
		
		NumberQ.printNumbersInOrder(nums);
		
		int[] nums3 = {-10, 18, 12, 4, -8};
		
		NumberQ.printNumbersInOrder(nums3);
		
	}
	
	public void testIfZeroSumExit(){
		int[] nums = {10, 2, 13, 4, 8};
		
		Assert.assertFalse(NumberQ.ifZeroSumExit(nums));
		
		int[] nums2 = {-10, -2, 12, 4, 8};
		
		Assert.assertTrue(NumberQ.ifZeroSumExit(nums2));
		
		int[] nums3 = {-10, 18, 12, 4, -8};
		
		Assert.assertTrue(NumberQ.ifZeroSumExit(nums3));
	}
	
	public void testIsPalindrome(){
		Assert.assertFalse(NumberQ.isPanlindrome(10));
		Assert.assertTrue(NumberQ.isPanlindrome(11));
		Assert.assertTrue(NumberQ.isPanlindrome(101));
		
		Assert.assertTrue(NumberQ.isPanlindrome(1));
		
	}
	
	public void testReverseBits(){
		
		System.out.println(NumberQ.reverseBits(-21));
		System.out.println(NumberQ.reverseBits2(-21));
		
		System.out.println(Integer.reverse(-21));
				
	}

}
