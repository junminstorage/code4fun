package org.blueocean.romanLetter;

import java.util.EnumSet;

/**
 * a class defines rules and constraints
 * 
 * @author junminliu
 *
 */
public class Rule {
	/**
	 * digital representation of Roman letters
	 * make value classes immutable as possible
	 */
	public static enum RomanLetter {
		I(1), V(4), X(10), L(50), C(100), D(500), M(1000);
		
		private final int number;
		
		RomanLetter(int value) {this.number = value;}

		public int getValue(){return number;}
		
		public enum Substraction {
			IV(I, V), IX(I, X), XL(X,L), XC(X,C), CD(C,D), CM(C, M);
			
			final RomanLetter first;
			final RomanLetter second;			

			Substraction(RomanLetter first, RomanLetter second){
				this.first = first;
				this.second = second;
			}
			
			public int getValue(){ return this.second.getValue() - this.first.getValue();}
			
			public boolean canSubstract(RomanLetter first, RomanLetter second){
				for(Substraction s : Substraction.values()){
					if(s.first == first && s.second == second)
						return true;
				}
				return false;
			}
			
			public boolean isSubstractable(RomanLetter first){
				for(Substraction s : Substraction.values()){
					if(s.first == first)
						return true;
				}
				return false;
			}
		}
	};

	public EnumSet<RomanLetter> Repeatables = EnumSet.of(RomanLetter.I, RomanLetter.X, RomanLetter.C, RomanLetter.M);
	
	public final static int REPEAT_TIMES = 3;
	
	
}
