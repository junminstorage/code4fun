package org.blueocean;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class NumberQ {
	
	
	/*
	 * Given an array of numbers print the values in diagonal format. 

Example (1) for 8 dataset 
Input Array : [1, 2, 3,4,5,6,7,8] 
Output 
01 02 04 07 
03 05 08 
06 
http://www.careercup.com/question?id=5673265078992896
	 */
	
	public static void outDiagonalFormat(int[] numbers){
		int start = 0;
		int step = 0;
		int counter = 0;
		while(start<numbers.length){
			int i = start;
			while(i<numbers.length){
				System.out.print(numbers[i] + "\t");
				step++;
				i = i + step;
			}
			System.out.println("");
			counter++;
			start = start + 1 + counter;
			step = counter;
		}
	}
	
	
	/*
	 * You are given with an array of 1s and 0s. And you are given with an integer m, which signifies number of flips allowed. 

find the position of zeros which when flipped will produce maximum continuous series of 1s. 

e.g. 
input: 
arr={1 1 0 1 1 0 0 1 1 1 } m=1 
output={1 1 1 1 1 0 0 1 1 1} position=2 

arr={1 1 0 1 1 0 0 1 1 1 } m=2 
output={1 1 0 1 1 1 1 1 1 1} position=5,6
http://www.careercup.com/question?id=5106425965576192
	 */
	public static List<Integer> findZeroPositions(int[] numbers, int k){
		int p1=0;
		int p2 = p1;
		
		int max = 0;
		int maxIndex = p1;
		int counter = 0;
		
		while(p1<numbers.length-max){
						
			while(p2<numbers.length && (numbers[p2]==1 || counter<k)){				
				if(numbers[p2]==0)
					counter++;
				p2++;
			}
			
			if(p2 - p1>max){
				max = p2 - p1;
				maxIndex = p1;
			}
			
			if(numbers[p1]==0)
				counter--;
			p1++;		
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=maxIndex; i<maxIndex+max; i++){
			if(numbers[i]==0)
				list.add(i);
		}
		
		return list;
	}
	
	
	/*
	 * Given a sorted array with some sequenced numbers and some non-sequenced numbers. Write an algorithm that takes this array as an input and returns a list of {start, end} of all consecutive numbers. Consecutive numbers have difference of 1 only. 
E.g. of array: 
[4, 5, 6, 7, 8, 9, 12, 15, 16, 17, 18, 20, 22, 23, 24, 27]
http://www.careercup.com/question?id=5701363426131968
	 */
	public static void getSequencedNumbers(int[] numbers){
		if(numbers==null || numbers.length==0)
			return;
		int p1=0;
		int p2=1;
		int pre = numbers[p1];
		while(p1<numbers.length && p2<numbers.length){
			if(numbers[p2]!=pre+1){
				System.out.println(p1 + " - " + (p2-1));
				p1=p2;
			}
			pre=numbers[p2];
			p2++;
		}
		
		System.out.println(p1 + " - " + (p2-1));
		
	}
	
	static class Element{
		int v;
		char c;
	}
	
	/*
	 * http://www.glassdoor.com/Interview/Sort-a-million-32-bit-integers-using-only-2MB-of-RAM-QTN_120936.htm
	 * 1 million integers = 4MB which is > 2MB RAM.
solution: external sort - divide and conquer

1. read half the list into 2MB ram and sort using quicksort (quicksort uses logn space - however 0.5m integers is less than 2MB (2000kb v 2048kb) so this should be okay).
2. write sorted data to disk
3. repeat for next chunk
4. merging results: we need an output buffer. lets say this is 1MB. then we read 512KB from each of your chunks into input buffers. we then perform a 2-way merge of the data. when an input buffer becomes empty we can pull in the remainder of the chunk.
5. when the output buffer is full we write this to disk.
6. when the process completes we are left with 2x 1MB files sorted in the correct order.

notes:
- when choosing an input buffer size, the smaller we make it the more I/O operations we will need to perform which would significantly slow down our sorting. a smaller output buffer would also do the same. we would rather a smaller output buffer as multi-threading would allow sorting to continue while writing to disk.
- we could use an additional thread to load next chunk from disk when input buffer drops below half size.
- HDD mirror raid would increase sequential read and write speed to disk as well as HDD speed
- compression of input would also reduce I/O
- we choose quicksort over mergesort as mergesort requires O(n) space. quicksort therefore reduces the number of merge operations.
	 */
	//http://stackoverflow.com/questions/7203159/to-sort-1-billion-of-integer-numbers-with-small-physical-memory?lq=1
	
	/*
	 * Write code to sum 2 integer 
	 * but u cant use a+b method, you have to use either ++ or --. How you will handle negative numbers.
	 */
	public static int sum(int number, int number2){
		for(int i=0; i<Math.abs(number2); i++){
			if(number2>=0)
				number++;
			else
				number--;
		}
		number++;
		return number;
	}
	
	/*
	 * Sum of two bits can be obtained by 
	 * performing XOR (^) of the two bits. Carry bit can be obtained by performing AND (&) of two bits. 
	 */
	public static int add(int x, int y)
	{
	    while (y != 0)
	    {
	        int carry = x & y;  
	 
	         x = x ^ y; 
	 
	         y = carry << 1;
	         System.out.println(x + "-" + y);
	    }
	    return x;
	}
	
	/*
	 * Given a string array ex: [1, 2, 3], find the permutation in best time.
	 * http://www.careercup.com/question?id=4734553532923904
	 * 
	 */
	public static List<ArrayList<Integer>> findPermutation(int[] numbers){
		ArrayList<String[]> ret = new ArrayList<String[]>();
		List<ArrayList<Integer>> permutation = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> init = new ArrayList<Integer>();
		for(int i: numbers)
			init.add(i);
		permutation.add(init);
		
		for(int i=0; i<numbers.length; i++){
			List<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> current : permutation){
				for(int j=i; j<numbers.length; j++){					
					temp.add(swap(new ArrayList<Integer>(current), i, j));
				}
			}
			permutation = temp;
		}
		
		return permutation;
	}
	
	private static ArrayList<Integer> swap(ArrayList<Integer> current, int i, int j) {
		int temp = current.get(i);
		current.set(i, current.get(j));
		current.set(j, temp);
		return current;
	}

	/*
	 * http://www.careercup.com/question?id=5650339814440960
	 * Given a list of n sorted lists of numbers, 
	 * write a method that returns one giant list of all the numbers in order. 
	 */
	public static class MinQSort{
		Node[] nodes;
		int capacity;
		int size;
		MinQSort(int n){
			nodes = new Node[n];
			capacity = n;
			size = 0;
		}
		
		public void push(Node node){
			if(size<capacity){
				nodes[size++] = node;
				//bubble up
			}
		}
		
		public Node pop(){
			if(size==0)
				return null;
			else{
				Node min = nodes[0];
				if(min.next!=null){
					nodes[0] = min.next;
				}else
					nodes[0] = nodes[--size];
				//bubble down
				return min;
			}
		}
		
		public boolean isEmpty(){
			return size == 0;
		}
	}
	
	public Node sortNList(Node[] lists){
		MinQSort mq = new MinQSort(lists.length);
		for(Node node: lists){
			mq.push(node);
		}
		
		Node head = null;
		Node tail = null;
		
		while(!mq.isEmpty()){
			Node min = mq.pop();
			if(head==null){
				head = min;
				tail = min;
			}else{
				tail.next = min;
				tail = min;
			}
		}
		
		return head;
		
	}
	
	
	/*
	 * http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/
	 */
	public static class PriorityQ{
		int[] data;
		boolean isMin;
		int size;
		PriorityQ(boolean isMin){
			this.isMin = isMin;
		}
		//add element
		public void push(int i){			
		}
		//return top element
		public int pop(){
			return -1;
		}
		
		public int size(){
			return this.size;
		}
		//peek the top element
		public int top(){
			return -1;
		}
		
	}
	public static int findMedianInIntegerStream(int[] numbers){
		PriorityQ maxQ = new PriorityQ(true);
		PriorityQ minQ = new PriorityQ(false);
		
		for(int n : numbers){
			if(n<=maxQ.top()){
				if(maxQ.size()==minQ.size())
					maxQ.push(n);
				else{
					minQ.push(maxQ.pop());
					maxQ.push(n);
				}
			}else{
				if(maxQ.size()==minQ.size()){
					maxQ.push(minQ.pop());
					minQ.push(n);
				}else
					minQ.push(n);
			}
			
		}
		
		if(numbers.length%2==0){
			return (maxQ.top() + minQ.top())/2;
		}else{
			return maxQ.top();
		}
		
	}
	
	/* 
	Write an algorithm that brings all nonzero elements to the left of the array, and returns the number of nonzero elements. 

	Example input: [ 1, 0, 2, 0, 0, 3, 4 ] 
	Example output: 4 

	[1, 4, 2, 3, 0, 0, 0] 

	* The algorithm should operate in place, i.e. shouldn't create a new array. 
	* The order of nonzero elements does not matter 
	*/
	
	public static void shiftZeroToRight(int[] numbers){
		int pos = numbers.length-1;
		while(pos>=0){
			if(numbers[pos]==0){
				pos--;
			}else{
				int swap = pos;
				while(swap>=0 && numbers[swap]!=0)
					swap--;

				if(swap<0)//all nonzero are in place
					return;

				swap(numbers, swap, pos);
			}

		}
		
	}
	
	
	/*
	 * input [2,3,1,4] 
	 * output [12,8,24,6] Multiply all fields except it's own position. 
	 * Restrictions: 1. no use of division 2. complexity in O(n)
	 */
	public static int[] multiple(int[] numbers){
		int[] backward =  new int[numbers.length];
		backward[numbers.length-1] = 1;
		
		for(int i=numbers.length-2; i>=0; i--){
			backward[i] = backward[i+1] * numbers[i+1];
		}
		
		int p = 1;
		for(int i=0; i<numbers.length; i++){
			backward[i] = p * backward[i];
			p = p * numbers[i];
			
		}				
		
		return backward;
	}
	
	/*
	 * WAP to modify the array such that arr[I] = arr[arr[I]]. 
		Do this in place i.e. with out using additional memory. 

		example : if a = {2,3,1,0} 
		o/p = a = {1,0,3,2} 
	 */
	public static int[] wap(int[] numbers){
		int startIndex = 0;
		int temp = numbers[startIndex];
		
		int target = startIndex;
		int replace = numbers[target];
		
		while(replace!=startIndex){
			numbers[target] = numbers[replace];
			target = replace;
			replace = numbers[target];
		}
		
		numbers[target] = temp;
		return numbers;
	}
	
	
	/*
	 * Given an array of integers. We have to find the max element of the array, which is at multiple places in the array and return any one of the indices randomly.
	 */
	public static int findMaxRand(int[] numbers){
		List<Integer> positions = new ArrayList<Integer>();
		int random = (int) (Math.random() * positions.size());	
		return positions.get(random);
		
	}
	
	/*
	 * You're given an array of integers(eg [3,4,7,1,2,9,8]) 
	 * Find the index of values that satisfy A+B = C + D, where A,B,C & D are integers values in the array. 
	 *Eg: Given [3,4,7,1,2,9,8] array 
	 * The following 
	 * 3+7 = 1+ 9 satisfies A+B=C+D 
	 * so print (0,2,3,5)
	 */
	
	public static int[] findIndex(int[] numbers){
		if(numbers==null || numbers.length<4)
			return null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0;i<numbers.length; i++)
			map.put(numbers[i], i);
		Arrays.sort(numbers);		
		for(int p1=0; p1<numbers.length-3; p1++){
			for(int p2=numbers.length-1; p2>p1+2; p2--){
				int p3=p1+1;	
				int p4=p2-1;
				int sum = numbers[p1]+numbers[p2];
				while(p3<p4){
					if(sum==numbers[p3]+numbers[p4])
						return new int[]{numbers[p1], numbers[p2], numbers[p3], numbers[p4]};
					else if(sum<numbers[p3]+numbers[p4]){
						p4--;
					}else{
						p3++;
					}
				}					
			}
		}
		return null;
	}
	
	
	/*
	 *  Take a integer as a input and replace all the ‘0’ with ‘5’.
	 */
	public static long replace0W5(int number){
		long result = number%10==0?5:number%10;
		number = number/10;
		int c = 10;
		while(number>0){
			int last = number%10 ;
			result = result + (last==0?5:last) * c;
			number = number/10;
			c = c*10;
		}
		return result;
	}
	
	/*
	 * Finding median in array
	 */
	public static int findMedian(int[] numbers){
		return findkthMin(numbers, numbers.length/2);
	}
	
	/*
	 * http://en.wikipedia.org/wiki/Quickselect
	 */
	public static int findkthMin(int[] numbers, int k){
		if(numbers.length==1)
			return 0;
		int left = 0;
		int right = numbers.length-1;
		
		while(true){
			int pivotal = partition(numbers, left, right);
			if(k==pivotal)
				return numbers[pivotal];
			else if(k<pivotal){
				right = pivotal-1;
			}else{
				left = pivotal + 1;
			}
		}		
	}
	
	public static int partition(int[] numbers, int left, int right){
		int pivotal = (left+right)/2;
		swap(numbers, right, pivotal);
		int index = left;
		for(int i=left; i<=right-1;i++){
			if(numbers[i] < numbers[right]){
				swap(numbers, i, index);
				index++;
			}
		}
		swap(numbers, right, index);		
		return index;
	}
	
	/*
	 * Find the square root of 
	 * any number (square root can be a real number) without using any library function
	 */
	public static float sqrt(int number){
		float result = number/2.0f;
		
		int c = 0;
		while(result * result - number >0.001){
			c++;
			result = (result + number/result)/2;
		}
		System.out.println(c);
		return result;
	}
	
	/*
	 * 2. Print pascal triangle 
	 * and your output should be same as pascal triangular form (have to consider the space separation) .
	 * I told him two approaches and wrote the code.
	 */
	public static void printPascalTriangle(int level){//level: level index starting from 0
		int[][] nums = new int[level+1][2*level+1];
		nums[0][level] = 1;
		
		for(int i=0; i<=level; i++){
			for(int j=0; j<nums[i].length; j++){
				if(nums[i][j]==0)
					System.out.print(" ");
				else
					System.out.print(nums[i][j]);
				
				if(i+1<level+1){
					int left = j-1>=0?nums[i][j-1]:0;
					int right = j+1<nums[i].length?nums[i][j+1]:0;
					nums[i+1][j] = left+right;
				}
			}
			System.out.println();
		}
		
		
	}
	
	
	/*
	 * 3. Given an array , each element is one more or one less than its preceding element .
	 * find an element in it.(better than O(n) approach)
	 */
	public boolean findElement(int[] nums, int target){
		for(int i=0; i<nums.length; i++){
			if(nums[i]==target)
				return true;
			
			i = i + Math.abs(target-nums[i]);
		}
		
		return false;
	}
	
	
	/*
	 * 2. In the same (M X N) matrix I have to print the matrix in increasing order 
	 * of elements .
	 */
	
	static class  ElementQ {
		int data;
		int row;
		int col;
		
		public ElementQ(int d, int r, int c){
			data = d;
			row = r;
			col = c;
		}
	}
	
	static class  MatrixMinQ {
		ElementQ[] minQ;
		int size;
		int rowSize;
		int[][] arrayP;
		
		public void printAsList(){
			ElementQ min = this.pop();
			while(min!=null){
				System.out.println(min.data);
				min = this.pop();
			}
		}
		
		//constructor using 2D array
		public MatrixMinQ(int[][] array){
			arrayP = array;
			rowSize = array[0].length;
			size = array.length;
			minQ = new ElementQ[size];
			int counter = 0;
			while(counter<size){
				minQ[counter] = new ElementQ(array[counter][0], counter, 0);
				counter++;
			}
		}
		
		public ElementQ pop(){
			if(size==0)
				return null;
			
			ElementQ min = minQ[0];		
			
			if(min.col+1<rowSize)
				minQ[0] = new ElementQ(arrayP[min.row][min.col+1], min.row, min.col+1);
			else {
				minQ[0] = minQ[size-1];
				minQ[size-1] = null;
				size--;
			}
			bubbleDown(0);			
			return min;			
		}
		
		
		public void bubbleDown(int i){
			int left = i*2+1;
			int right = i*2+2;
			
			int next = i;
			if(left<size && minQ[next].data > minQ[left].data)
				next = left;
			
			if(right<size && minQ[next].data > minQ[right].data)
				next = right;
			
			if(next != i){
				ElementQ temp = minQ[next];
				minQ[next] = minQ[i];
				minQ[i] = temp;
				bubbleDown(next);
			}
			
		}
		
		public ElementQ peek(){
			return minQ[0];
		}
		
		public boolean push(ElementQ node){
			if(minQ[minQ.length-1]!=null)
				return false;
			minQ[minQ.length-1] = node;
			//bubbleUp();
			//to-do
			return true;
		}
	}
	
	
	/*
	 * 1. Given a 2d matrix in which rows are sorted in ascending order
	 *  and columns are also sorted in ascending order .I need to find an element in optimal time complexity
	 */
	
	//solution 1
	public static int BS2DArray1(int[][] array, int target){
		
		int row = 0;
		int col = array[0].length-1;
		
		while(row<array.length && col>=0){
			if(array[row][col]==target)
				return target;
			else if(array[row][col]>target)
				col--;
			else
				row++;
		}
		
		return -1;
		
	}
	
	//solution 2, it is wrong
	public static int BS2DArray(int[][] array, int target){
		if(array==null || array.length==0)
			return -1;
				
		int startRow = 0;
		int startCol = 0;
		int endRow = array.length-1;
		int endCol = array[0].length-1;
		
		int found = 0;
		
		while(startRow<array.length && startCol<array[0].length){
			found = BS1DArrayUtil(array[startRow], target, startCol, endCol);

			if(found==-1){
				if(array[startRow][startCol]>target)
					return -1;
				
				if(array[startRow][endCol]<target){
					found = endCol;
				}
			}

			if(array[startRow][found] == target)
				return target;
			else{
				startCol = found;
				int[] temp = new int[array.length-startRow];
				for(int i=startRow; i<temp.length; i++){
					temp[i] = array[i][startCol];
				}
				found = BS1DArrayUtil(temp, target, startRow, endRow);
				if(found == -1){
					if(array[startRow][startCol]>target)
						return -1;
					if(array[endRow][startCol]<target)
						found = endRow;					
				}
				
				if(array[found][startCol] == target)
					return target;
				else{
					startRow = found;
				}			
			}
		}
		
		return -1;
		
	}
	
	public static int BS1DArrayUtil(int[] array, int target, int start, int end){
		if(start>end)
			return -1;
		
		if(end-start==1){
			if(target == array[end])
				return end;
			if(array[start]<=target && target < array[end])
				return start;
			else
				return -1;
		}
		
		int middle = (start+end)/2;		
				
		if(target==array[middle] || (target>array[middle] && middle+1<array.length && target<array[middle+1]))
			return middle;
		else if(target>array[middle])
			return BS1DArrayUtil(array, target, middle, end);
		else
			return BS1DArrayUtil(array, target, start, middle-1);		
		
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-51-campus-sdet/
	 */
	public static int betting(String input, int sum, int bet){
		
		for(int i=0; i<input.length(); i++){
			if(sum<=0)
				return sum;
			if(input.charAt(i) == 'W'){
				sum = sum + bet;
				bet = 1;
			}else if(input.charAt(i) == 'L'){
				sum  = sum - bet;
				bet = 2* bet;
			}
		}
		
		return sum;
	}
	
	/*
	 * Q2. Given a link list with right 
	 * pointers and each element of the list has a down link contains another 
	 * link list with down pointers as:
	 */
	static class  NodeQ {
		int data;
		NodeQ next;
		NodeQ down;
	}
	
	static class  MinQ {
		NodeQ[] minQ;
		int size;
		public void init(NodeQ head){
			int counter = 0;
			NodeQ p = head;
			while(p!=null){
				counter++;
				p = p.next;
			}
			size = counter;
			minQ = new NodeQ[counter];
			
			p = head;
			counter = 0;
			while(p!=null){
				minQ[counter] = p;
				p = p.next;
				counter++;
			}
		}
		
		public NodeQ pop(){
			if(size==0)
				return null;
			
			NodeQ min = minQ[0];			
			if(min.down!=null)
				minQ[0] = min.down;
			else {
				minQ[0] = minQ[size-1];
				minQ[size-1] = null;
				size--;
			}
			bubbleDown(0);			
			return min;			
		}
		
		
		public void bubbleDown(int i){
			int left = i*2+1;
			int right = i*2+2;
			
			int next = i;
			if(left<size && minQ[next].data > minQ[left].data)
				next = left;
			
			if(right<size && minQ[next].data > minQ[right].data)
				next = right;
			
			if(next != i){
				NodeQ temp = minQ[next];
				minQ[next] = minQ[i];
				minQ[i] = temp;
				bubbleDown(next);
			}
			
		}
		
		public NodeQ peek(){
			return minQ[0];
		}
		
		public boolean push(NodeQ node){
			if(minQ[minQ.length-1]!=null)
				return false;
			minQ[minQ.length-1] = node;
			//bubbleUp();
			return true;
		}
	}
	
	public static NodeQ toDownList(int[] num1){
		NumberQ.NodeQ  head=null;
		NumberQ.NodeQ  pre=null;
		
		for(int i : num1){
			NumberQ.NodeQ newN  = new NumberQ.NodeQ();
			newN.data = i;
			
			if(head==null){
				head = newN;
				pre = head;
			}else{
				pre.down = newN;
				pre = newN;
			}
		}
		
		return head;
	}
	
	public static NodeQ toSLL(NodeQ head){
		MinQ minq = new MinQ();
		minq.init(head);
		
		NodeQ resultHead = minq.pop();
		NodeQ p = resultHead;
		while(p!=null){
			NodeQ next = minq.pop();
			p.next = next;
			p = p.next;
		}
		
		return resultHead;
	}
	
	
	/*
	 * Given a float number 7.64, convert it into the string WITHOUT using any inbuilt function/library
	 */
	public static void floatToString(float number){
		int pre = (int) number;
		int counter = 0;
		while(number*10 > (int)number*10){
			number = number * 10;
			counter++;
		}
		System.out.println(counter);
		//float after = number - pre;		
		int number2 = (int) number;
		StringBuilder sb = new StringBuilder();
		
		while(number2>1){
			counter--;
			sb.append(number2%10);
			if(counter==0)
				sb.append(".");
			number2 = number2/10;
		}
	
		sb.reverse();
		
		System.out.println(sb.toString());
		
	}
	
	
	
	/*
	 * Given a number print all the combination in which no. can be printed.
	 */
	
	public static void PrintCombination(int i){
		List<ArrayList<Integer>> result = getCombination(i);
		for(ArrayList<Integer> item : result){
			System.out.println(item);
		}
	}
	
	public static List<ArrayList<Integer>> getCombination(int i){
		List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if(i==1){
			ArrayList<Integer> r = new ArrayList<Integer>();
			r.add(i);
			list.add(r);
			return list;
		}
		
		for(int index=1; index<i; index++){
			List<ArrayList<Integer>> re = getCombination(i-index);
			if(i-index>1){
				ArrayList<Integer> r = new ArrayList<Integer>();
				r.add(i-index);
				re.add(r);
			}
			for(ArrayList<Integer> item : re){
				item.add(index);
				list.add(item);
			}
		
		}
		
		return list;
	}
	
	////////////////////////
	private static int testV =1;
	private int testV3 =1;
	
	class Inner{
		//A local class can have static members provided that they are constant variables. 
		static final String farewell = "Bye bye";
		static final int i = 1;
		
		public void test(){
			testV = 123;//An inner class has access to the members of its enclosing class. 
			testV3 = 12;//An inner class has access to the members of its enclosing class. 
		}
	}
	
	public void testLocalClass(){
		final int testV2 = 1;
		
		class Local{
			//A local class can have static members provided that they are constant variables. 
			static final String farewell = "Bye bye";
			static final int i = 1;
			
			public void test(){
				testV = 123;//A local class has access to the members of its enclosing class. 
				testV3 = 12;//A local class has access to the members of its enclosing class. 
				testV = testV2;//starting in Java SE 8, a local class can access local variables and parameters of the enclosing block that are final or effectively final
			}
		}
	}
	/////////////////////////////
	/*
	 * http://sudhansu-codezone.blogspot.com/2012/02/data-structure-for-parking.html
	 */
	/*
	 * http://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
	 */
	public static void findCommonElements3Arrays(int[] num1, int[] num2, int[] num3){
		int p1 = 0;
		int p2 = 0;
		int p3 = 0;
		
		while(p1<num1.length && p2 <num2.length && p3<num3.length){			
			if(num1[p1]>num2[p2])
				p2++;
			else if(num1[p1]<num2[p2])
				p1++;
			else{
				
				if(num3[p3]>num1[p1]){
					p1++;
					p2++;
				}else if(num3[p3]<num1[p1])
					p3++;
				else{
					System.out.println(num1[p1]);
					p1++;
					p2++;
					p3++;
				}			
			}				
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/microsoft-interview-set-37-sde-1/
	 * Given numbers a1…an find the minimum index, whose element occurs 
	 * twice in the array. Do it in one pass of the array ( or less that O(n) if possible?)
	 */
	public static int findFirstNoRepeatNumber(int[] numbers){
		Map<Integer, Node> store = new HashMap<Integer, Node>();
		Node head = null;
		Node tail = null;
		for(int i : numbers){
			if(store.containsKey(i)){
				Node found = store.get(i);
				found.times = found.times + 1;
				if(found.times==2){
					//add it to DLL when repeat twice
					if(head==null){
						head = found;
						tail = found;
					}else{
						found.pre = tail;
						tail.next = found;
						tail = found;
					}
				}
				
				if(found.times>2){
					//remove found from DLL
						if(found==head)
							head = found.next;
						
						if(found==tail)
							tail = found.pre;
						
						if(found.pre!=null && found.next!=null){
							found.pre.next = found.next;
							found.next.pre = found.pre;
						}
						
						found.pre = null;
						found.next = null;
					
				}
			}else{
				//create node and add to map for first time
				Node newNode = new Node();
				newNode.index = i;
				newNode.times = 1;
				store.put(i, newNode);
			}
		}
		
		return head.index;
	}
	
	public static void printZeroSum(Element[] elements){
		Map<Integer, Character> table = new HashMap<Integer, Character>();
		for(Element e : elements){
			table.put(e.v, e.c);
		}
		
		for(Element e : elements){
			if(table.containsKey(0-e.v)){
				System.out.println(e.c + " == " + table.get(0-e.v));
				table.remove(0-e.v);
			}
		}
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-125-on-campus-for-internship/
	 * Q1- Given an array of positive and negative numbers, arrange them in an alternate fashion such that every positive number is followed by negative and vice versa maintaining the 
	 * order of appearance.If the count of negative numbers is more keep the extra at last in array
	constraint : Space complexity should be O(1).
	 */
	public static int[] shufflePosNeg(int[] numbers){
		if(numbers.length<=2)
			return numbers;
		
		int pre = numbers[0];
		for(int i=1;  i<numbers.length; i++){
			int current = numbers[i];
			if(current*pre<0){
				pre = current;
			}else{
				int found = i+1;
				while(found<numbers.length&& numbers[found]*current>0)
					found++;
				if(found<numbers.length){
					for(int index=found; index>i; index--){
						int temp = numbers[index];
						numbers[index] = numbers[index-1];
						numbers[index-1] = temp;
					}
				}
				pre = numbers[i];
			}	
		}
		
		return numbers;
	}
	
	/*
	 * http://www.geeksforgeeks.org/amazon-interview-set-125-on-campus-for-internship/
	 * Q2- Given an array of random numbers, Push all the zero’s of a given array to the right end of the array in minimum possible swaps. 
	 * Order of appearance doesn’t matter. Print the total nonzero numbers and minimum swaps needed to do so.
	 */
	public static void shiftZerosToRight(int numbers[]){
		int numberOf0 = 0;
		int numberOfSwap = 0;
		for(int i=0; i<numbers.length; i++){
			if(numbers[i]==0)
				numberOf0++;
		}
		
		int pointer = numbers.length-1;
		while(pointer>=0 && pointer>=numbers.length-numberOf0){
			while(pointer>=0 && numbers[pointer]==0)
				pointer--;
		
			if(pointer>=numbers.length-numberOf0){
				int start = numbers.length - numberOf0 - 1;
				while(start>=0 && numbers[start]!=0)
					start--;
				numberOfSwap++;
				int temp = numbers[pointer];
				numbers[pointer] = numbers[start];
				numbers[start] = temp;
			}
		}
		
		System.out.println(numberOf0);
		System.out.println(numberOfSwap);
		for(int i=0; i<numbers.length; i++)
			System.out.println(numbers[i]);
	}
	
	/*
	 * Q3 – given two arrays in sorted form. The first array has some empty space equal to the size of second array at its end. You have to 
	 * merge both array in the smartest possible way in the first array. With constraint that auxiliary space O(1).
	 */
	public static void merge(int[] numbers1, int[] numbers2){
		int p1 = 0;
		int p2 = 0;
		while(p1<=numbers1.length-numbers2.length-1){
		
			while(p1<numbers1.length && numbers1[p1]!=0)
				p1++;
			p2 = p1;
			while(p2<numbers1.length && numbers1[p2]==0)
				p2++;
			if(p2<numbers1.length)
				numbers1[p1] = numbers1[p2];
		}
		
		int end1 = p1;
		int end2 = numbers2.length-1;
		
		int posTofill= numbers1.length-1;
		
		while(end2>=0){
			int numberTofill = numbers1[end1]>=numbers2[end2]?numbers1[end1]:numbers2[end2];
			numbers1[posTofill] = numberTofill;
			posTofill--;
			if(numbers1[end1]>=numbers2[end2])
				end1--;
			else
				end2--;			
			
		}
		
	}
	
	
	//Given an integer array and a positive integer k, 
	//count all distinct pairs with difference equal to k.
	public static void caleng(){
		//"fdsf".l
	}
	
	public static class Node{
		int d;
		int index;
		int times;
		Node next;
		Node pre;
		public Node(int v){
			d = v;
		}
		
		public Node(){
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
	
	/*
	 * Given an unsorted array of nonnegative integers, 
	 * find a continous subarray which adds to a given number.
	 */
	public static int findSubArrayHasSum(int[] numbers, int sum){
		int start = 0;
		int end = 1;
		
		if(numbers[0] == sum)
			return start;
		
		int currentSum = numbers[start];
		while(end < numbers.length){		
			if(currentSum == sum)
				return start;
			else if(currentSum>sum){
				currentSum = currentSum - numbers[start];
				start++;
			}else{
				currentSum = numbers[end] + currentSum;
				end++;
			}
		}
		
		return -1;
	}
	
	/*
	 * http://www.geeksforgeeks.org/facebook-interview-set-2-campus-interview-internship/
	 * 1. You have an array of n elements, and a sum. Check if any 2 elements in the array sum to the given sum. ( Expected time complexity O(n). Use hashing)
	 */
	public static boolean hasSum(int[] numbers, int sum){
		Map<Integer, Boolean> mapping = new HashMap<Integer, Boolean>();
		for(int n : numbers)
			mapping.put(n, true);
		
		for(int k: numbers){
			if(mapping.containsKey(sum-k))
				return true;
		}
		
		return false;
	}
	
	/*
	 * 1. You are given the start time and finish time of n intervals. You have to write a a function that returns boolean value indicating if there was any overlapping
	 *  interval in the set of existing intervals. (Sort and check, time complexity O(nlogn))
	 */
	class Interval {
		int start;
		int end;
	}
	
	public static boolean hasOverlap(List<Interval> input){
		Collections.sort(input, new Comparator<Interval>(){
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start-o2.start;
			}			
		});
		
		for(int i=0; i<input.size()-1; i++){
			if(input.get(i).end > input.get(i+1).start)
				return true;
		}
		return false;
	}
	
	/*
	 * 2. You have 2 sparse vectors (large number of 0’s). 
	 * First tell me a way to represent and store them, and then find the dot product.
	 */
	public int product(int[] index1, int[] values1, int[] index2, int[] values2, int n){
		int sum = 0;
		for(int i=0; i<n; i++){
			//binary search find i in index1 and index2
			//return its pos in index1 and index2
			int pos1=0;
			int pos2=0;
			sum = sum + values1[pos1]*values2[pos2];
		}
		return sum;
	}
	
	/**
	 * R1Q1
	 * http://www.geeksforgeeks.org/facebook-interview-set-2-campus-interview-internship/
	 * m seconds, n trees, assume m<n
	 * 
	 * @return
	 */
	public static int findMaxWindow(int[] values, int m, int n){		
		int max = 0;
		//initialize max with window starting from index 0
		for(int i=0; i<m; i++){
			max = values[i] + max;
		}
		
		int sum = max;
		for(int start = 1; start < n; start++){
			sum = sum - values[start-1];
			if(start+m-1<=n-1)
				sum = sum + values[start+m-1];
			else
				sum = sum + values[m-n+start-1];
			if(sum>max)
				max = sum;
		}
		
		return max;
		
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
	
	/*
	 * Given an array which initially increases and then decreases, 
	 * search for an element in the array.
	 */
	public int findKInInfiniteArray(int[] numbers, int k){
		int start = 0;
		if(numbers[start]==k)
			return start;		
		else if(numbers[start]>k){
			//need to find the point where number < k
			start = 1;
			while(numbers[start]>k){
				start = start * 2;
			}
			//find k in a decreased array 
			return findKInDecreasedArray(numbers, k, start/2, start);		
		}
		else{
			//need to find the point where number > k, also stop if the numbers start to decreased
			start = 1;
			while(numbers[start]<k && numbers[start]>numbers[start/2]){
				start = start*2;
			}
			int max = findMaxPoint(numbers, start/4, start);
			return findKInDecreasedArray(numbers, k, max, start);	
		}
	}

	protected static int findMaxPoint(int[] numbers, int start, int end){
		if(end-start==1){
			return numbers[start]>numbers[end]?start:end;
		}
		int middle = (start+end)/2;
		if(numbers[middle]>numbers[middle-1]){
			return findMaxPoint(numbers, middle, end);
		}else{
			return findMaxPoint(numbers, start, middle-1);
		}
	}

	protected static int findKInDecreasedArray(int[] numbers, int k, int start, int end) {
		if(start==end){
			return numbers[start]==k?start:-1;
		}
		
		if(end-start==1){
			return numbers[start]==k?start:(numbers[end]==k?end:-1);
		}
		
		int middle = (start+end)/2;
		if(numbers[middle]==k)
			return middle;
		else if(numbers[middle]>k)
			return findKInDecreasedArray(numbers, k, middle+1, end);
		else
			return findKInDecreasedArray(numbers, k, start, middle-1);
	}
		
}
