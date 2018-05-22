/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 03/25/2015
 * 	Program Name: Cylinder.class (Lab 9 Exercise)
 */

package varholaM_lab9_Activity;

public class VarholaM_lab9_Cylinder extends VarholaM_lab9_Circle{
	
	private double h;
	
	public VarholaM_lab9_Cylinder(){
		super();
		h = 0;
	}
	
	public VarholaM_lab9_Cylinder(double r){
		super(r);
		this.h = 0;
	}
	
	public VarholaM_lab9_Cylinder(double r,double h){
		super(r);
		this.h = h;
	}
	
	public void setHeight(double h){
		this.h = h;
	}
	
	public double getHeight(){
		return h;
	}
	
	public String toString(){
		return String.format("The cylinder has a radius of %.2f and a height of %.2f",getRadius(),h); 
	}
	
	public double calcVolume(){
		return calcArea()*h;
	}
	
	public boolean equals(VarholaM_lab9_Cylinder x){
		return (getRadius()==x.getRadius() && h == x.getHeight());
	}
}
