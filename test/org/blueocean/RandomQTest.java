package org.blueocean;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class RandomQTest extends TestCase {
	
	public void testremoveDuplicates(){
		String[] result = (String[])RandomQ.removeDuplicates(new String[]{"dog",  "dog","cat", "tiger", "dog", "cat", "cat" });
		System.out.println(Arrays.toString(result));
	}
	public void testZoo(){
		
		Integer[] numbers = new Integer[4];
		List re = Arrays.asList(numbers);
		
		System.out.println(re.size());
		
		re.add(new Integer(1));
		
		/*RandomQ q = new RandomQ();
		q.y = 100;
		
		int counter = 0;
		for(int i = 0; i<q.y; i++){
		   if(q.zoo(3))
			   counter++;
		}
		*/
		//System.out.println(counter);
		
	}
	
	public void testprintAllParentheses(){
		RandomQ.printAllParentheses(1);
		RandomQ.printAllParentheses(2);
		RandomQ.printAllParentheses(3);
	}
	
	public void testallWordsFromPhonePad(){
		List<String> result = RandomQ.allWordsFromPhonePad(234);
		
		for(String s: result)
			System.out.println(s);
	}
	
	public void testAallParenthesis(){
		RandomQ.allParenthesis(2);
		RandomQ.allParenthesis(3);  
		RandomQ.allParenthesis(4);
		RandomQ.allParenthesis(5);
		RandomQ.allParenthesis(6);
	}
	
	public void testkthMin(){
		int[] x = {2, 4, 5, 7, 8};
		int[] y = {3, 6, 9, 10, 11};
		
		System.out.println(RandomQ.kthMin(x, y, 1)+"-");
		System.out.println(RandomQ.kthMin(x, y, 2)+"-");
		System.out.println(RandomQ.kthMin(x, y, 3)+"-");
		System.out.println(RandomQ.kthMin(x, y, 4)+"-");
		System.out.println(RandomQ.kthMin(x, y, 5)+"-");
		System.out.println(RandomQ.kthMin(x, y, 6)+"-");
		System.out.println(RandomQ.kthMin(x, y, 7)+"-");
		System.out.println(RandomQ.kthMin(x, y, 8)+"-");
	}
	
	public void testkthMin2(){
		int[] x = {2, 4, 5, 7, 8};
		int[] y = {3, 6, 9, 10, 11};
		
		System.out.println(RandomQ.kthMin2(x, y, 1)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 2)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 3)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 4)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 5)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 6)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 7)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 8)+"-");
		System.out.println(RandomQ.kthMin2(x, y, 9)+"-");
	}
}
