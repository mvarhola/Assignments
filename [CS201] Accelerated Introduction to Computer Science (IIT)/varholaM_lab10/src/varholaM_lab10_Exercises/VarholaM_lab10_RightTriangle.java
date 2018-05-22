/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: RightTriangle (Lab 10 Exercise)
 */

package varholaM_lab10_Exercises;

public class VarholaM_lab10_RightTriangle extends VarholaM_lab10_Shape implements Comparable<VarholaM_lab10_RightTriangle>{
	
	private int height;
	private int width;
	
	public VarholaM_lab10_RightTriangle(){
		height = 0;
		width = 0;
	}
	
	public VarholaM_lab10_RightTriangle(int height, int width){
		this.height = height;
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public String toString(){
		return String.format("This Right Triangle has a width of %d and a height of %d",width,height); 
	}
	
	public boolean equals(VarholaM_lab10_RightTriangle t){
		return (height==t.getHeight() && width==t.getWidth());
	}
	
	public double calcArea(){
		return height*width*0.5;
	}
	
	public double calcPerimiter(){
		return (height+width+(Math.sqrt(Math.pow(height, 2)*Math.pow(width,2))));
	}
	
	public int compareTo(VarholaM_lab10_RightTriangle t){
		if (height<t.getHeight()){
			return -1;
		}
		else if (height>t.getHeight()){
			return 1;
		}else{
			return 0;
		}
	}
}
