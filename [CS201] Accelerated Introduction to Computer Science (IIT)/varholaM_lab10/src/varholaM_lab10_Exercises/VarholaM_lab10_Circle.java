/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: Circle (Lab 10 Exercise)
 */

package varholaM_lab10_Exercises;

public class VarholaM_lab10_Circle extends VarholaM_lab10_Shape implements Comparable<VarholaM_lab10_Circle>{

	private int radius;
	
	public VarholaM_lab10_Circle(){
		radius = 0;
	}
	
	public VarholaM_lab10_Circle(int radius){
		this.radius = radius;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
	
	public String toString(){
		return String.format("This Circle has a radius of %d.\n",radius); 
	}
	
	public boolean equals(VarholaM_lab10_Circle c){
		return (radius==c.getRadius());
	}
	
	public double calcArea(){
		return Math.PI*Math.pow(radius, 2);
	}
	
	public double calcPerimiter(){ //actually calcCircumference but renamed to follow abstract class implementation
		return (2 * (int) Math.PI * radius);
	}	
	
	public int compareTo(VarholaM_lab10_Circle c){
		if (radius<c.getRadius()){
			return -1;
		}
		else if (radius>c.getRadius()){
			return 1;
		}else
		{
			return 0;
		}
	}
}
