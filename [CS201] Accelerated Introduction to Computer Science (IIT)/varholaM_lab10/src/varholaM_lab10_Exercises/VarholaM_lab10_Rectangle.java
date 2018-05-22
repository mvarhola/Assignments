/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: Rectangle (Lab 10 Exercise)
 */

package varholaM_lab10_Exercises;

public class VarholaM_lab10_Rectangle extends VarholaM_lab10_Shape implements Comparable<VarholaM_lab10_Rectangle>{

	private int len;
	private int width;
	
	public VarholaM_lab10_Rectangle(){
		len = 0;
		width = 0;
	}
	
	public VarholaM_lab10_Rectangle(int len, int width){
		this.len = len;
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getLength(){
		return len;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setLength(int len){
		this.len = len;
	}
	
	public String toString(){
		return String.format("This Rectangle as a width of %d and a length of %d",len,width); 
	}
	
	public boolean equals(VarholaM_lab10_Rectangle r){
		return (len==r.getLength() && width==r.getWidth());
	}
	
	public double calcArea(){
		return len*width;
	}
	
	
	public double calcPerimiter(){
		return (len*2) + (width*2);
	}
	
	public int compareTo(VarholaM_lab10_Rectangle r){
		if (len<r.getLength()){
			return -1;
		}
		else if (len>r.getLength()){
			return 1;
		}else
		{
			return 0;
		}
	}
	
}
