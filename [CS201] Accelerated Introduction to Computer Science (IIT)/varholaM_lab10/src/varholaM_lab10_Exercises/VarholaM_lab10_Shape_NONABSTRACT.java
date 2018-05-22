/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: Shape_NONABSTRACT (Lab 10 Exercise)
 */

package varholaM_lab10_Exercises;

import java.awt.Shape;

public class VarholaM_lab10_Shape_NONABSTRACT {
	
	private int numSides;
	
	public VarholaM_lab10_Shape_NONABSTRACT(){
		numSides = 0;
	}
	
	public VarholaM_lab10_Shape_NONABSTRACT(int numSides){
		this.numSides = numSides;
	}
	
	public boolean equals(Shape s){
		return s.equals(s);
	}
	public double calcArea(){
		return 0;
		//Different formulas for calculating different areas
	}
	
	public double calcCircumference(){
		return 0;
		//Different formulas for calculating different circumferences
	}
	
}
