package org.blueocean;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * http://sudhansu-codezone.blogspot.com/2012/02/data-structure-for-parking.html
 */
public class ParkingLot {
	
	public class Lot{
		int distance;
		int no;
		boolean occupied;
	}
	
	public class Customer{
		int id;
		int priority;
	}
	
	PriorityQueue<Lot> minHeapLot; 
	PriorityQueue<Customer> minHeapCustomer; 
	Map<Customer, Lot> parked = new HashMap<Customer, Lot>();
	
	public ParkingLot(int capacity){
		minHeapLot = new PriorityQueue(capacity, new Comparator<Lot>(){
			@Override
			public int compare(Lot o1, Lot o2) {
				return o1.distance-o2.distance;
			}
		});
	
		minHeapCustomer = new PriorityQueue(capacity, new Comparator<Customer>(){
			@Override
			public int compare(Customer o1, Customer o2) {
				return o2.priority-o1.priority;
			}
		});	
	}
	
	public void processQ(){
		while(!minHeapCustomer.isEmpty()){
			Customer c = minHeapCustomer.remove();
			Lot t = minHeapLot.remove();
			parked.put(c, t);
		}
	}
	
	public void retrieveCar(Customer c){
		Lot t = parked.get(c);
		parked.remove(c);
		minHeapLot.add(t);
	}

}
