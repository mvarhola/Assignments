/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 03/25/2015
 * 	Program Name: ArrayList (Lab 9 Activity)
 */


package varholaM_lab10_BookInventory;

public class VarholaM_lab10_ArrayList {

	final int MAX_SIZE =  300;		//max size of the array

	private Object [] objArray;
	private int index;		//next empty slot
	private int iterator;
	
	//default constructor
	public VarholaM_lab10_ArrayList(){
		objArray = new Object[MAX_SIZE];
		iterator=0;
	}
	
	//nondefault constructor
	public VarholaM_lab10_ArrayList(int size){
		//instantiates the array with size
		objArray = new Object[size];
		iterator = 0;
	}
	
	//get length of an array
	public int getLength(){
		//returns the length of the array
		return objArray.length;

	}
			
	public Object[] getArray(){
		//return a copy of the array
		Object[] tempArray = objArray;
		return tempArray;
	}

	public void setArray(Object [] anArray){
	//assign anArray to this array Note: this assumes that anArray is already a copy and will be called with setIndex() so that the index agrees with the data
		objArray = anArray;
	}
	
	public int getIndex(){
	//returns the index
		return index;
	}
	
	public void setIndex(int anIndex){
	//assigns a new value to the index
		index = anIndex;
	}
	
	public Object getElement(int pos){
	//returns the element of the array at pos
		return objArray[pos];
	}
	
	public void setElement(Object obj, int pos){
	//assigns obj to array at pos
		objArray[pos] = obj;
	}
	
	public String toString(){
	//iterates through the array and concatenates the elements into a string that is returned	
		String temp = "";
		for (int i = 0; i < index; i++){
			if (objArray[i]!=null){
				temp+=i+"). "+objArray[i]+"\n";
			}else{
				i--;
			}
		}
		return temp;
	}
	
	public boolean equals(VarholaM_lab10_ArrayList anArray){
	//returns true if anArray has same elements as this array, same index and is the same length as this array
		boolean isEqual = false;
		
		if ((index == anArray.getIndex())&&(objArray.length==anArray.getLength())){
			isEqual = true;
			for (int i = 0; i < objArray.length; i++){
				if(!(objArray[i].equals(anArray.getElement(i)))){
					isEqual = false;
				}
			}
		}
		
		return isEqual;
		
	}
	
	//store an element in the array
	public void add(Object obj){
		// stores obj in the  next empty slot in the array & increments the next empty slot variable
		if (index < objArray.length){
			objArray[index++] = obj;
		}
	}
	
	//inserts an element in the array
	public void insert(Object obj, int pos){
		// moves all elements from pos towards end of array, inserts obj & increments the next empty slot variable
		if (index < objArray.length){
			for (int i = objArray.length-2;i>=pos;i--){
				objArray[i+1] = objArray[i];
			}
			objArray[pos] = obj;
			index++;
		}
	}
	
	//finds an element in the array
	public int isThere(Object obj){
		// searches the array for obj and returns pos it is found or -1 if not found
		int isThere = -1;
		for (int i = 0; i < index; i++){
			if (objArray[i].equals(obj)){
				isThere = i;
				break;
			}
		}
		return isThere;
	}
	
	//deletes an element in the array
	public void delete(Object obj){
		// finds obj in the array then and moves all elements from that pos to array end  towards 0, decrements  the next empty slot variable
		if (isThere(obj)>=0){
			for (int i = isThere(obj); i<objArray.length; i++){
				objArray[i-1] = objArray[i];
			}
		index--;
		//delete(obj);//recursively call to remove remaining strings
		}

	}
	
	//deletes an element in the array
	public void delete(int pos){
	// moves all elements from pos to array end  towards 0, decrements  the next empty slot variable
		if (pos <= objArray.length){
			for (int i = pos+1; i<objArray.length; i++){
				objArray[i-1] = objArray[i];
			}
		index--;
		}
	}
	
	public boolean isFull(){
		return (index==objArray.length);
	}
	
	public boolean isEmpty(){
		return (index==0);
	}
	
	public void clear(){
		objArray = new Object[objArray.length];
		reset();
		index = 0;
	}
	
	public void trim(){
		Object[] tempArray = new Object[index];
		for (int i = 0; i < index; i++){
			tempArray[i] = objArray[i];
		}
		objArray = tempArray;
	}
	
	public void moreCapacity(int multiple){
		Object[] tempArray = new Object[objArray.length*multiple];
		for (int i = 0; i < objArray.length; i++){
			tempArray[i] = objArray[i];
		}
		objArray = tempArray;
	}
	
	public void reset(){
		iterator = 0;
	}
	
	public boolean hasNext(){
		return (iterator<index && objArray[iterator]!=null);
	}
	
	public Object getNext(){
		return hasNext() ? objArray[iterator++]:null;
	}
	
	
}
