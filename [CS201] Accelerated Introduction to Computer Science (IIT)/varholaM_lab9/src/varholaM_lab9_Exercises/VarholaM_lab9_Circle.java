/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 03/25/2015
 * 	Program Name: Circle.class (Lab 9 Exercise)
 */

package varholaM_lab9_Exercises;

public class VarholaM_lab9_Circle {
	
	private double r;
	
	public VarholaM_lab9_Circle(){
		r = 0;
	}
	
	public VarholaM_lab9_Circle(double r){
		this.r = r;
	}
	
	public void setRadius(double r){
		this.r = r;
	}
	
	public double getRadius(){
		return r;
	}
	
	public boolean equals(VarholaM_lab9_Circle x){
		return (r==x.getRadius());
	}
	
	public int compareTo(VarholaM_lab9_Circle x){
		if (r>x.getRadius()){
			return 1;
		}
		else if (r<x.getRadius()){
			return -1;
		}
		else{
			return 0;
		}
	}
	
	public String toString(){
		return String.format("The circle has a radius of %2f.",r);
	}
	
	public double calcArea(){
		return Math.PI*Math.pow(r,2);
	}
	
	public double calcCircumference(){
		return Math.PI*2*r;
	}
	
}
