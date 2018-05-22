/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 03/25/2015
 * 	Program Name: TestApp(Lab 9 Exercise)
 */

package varholaM_lab9_Exercises;

public class VarholaM_lab9_TestApp {

	public static void main(String[] args) {
		VarholaM_lab9_Circle x = new VarholaM_lab9_Circle(42);
		VarholaM_lab9_Cylinder y = new VarholaM_lab9_Cylinder (2,3);
		
		System.out.println("The radius of the cylinder is "+y.getRadius());
		
		y.setRadius(20);
		System.out.println("The radius of the cylinder is now "+y.getRadius());
		
		VarholaM_lab9_Circle circ1 = new VarholaM_lab9_Cylinder(5,6);
		
		//System.out.println(circ1.calcVolume()); //cannot do this
		
		System.out.println(circ1.toString());	
		
		System.out.println(circ1.calcArea());
		
	}
}
