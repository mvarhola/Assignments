/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/21/2015
 * 	Program Name: GenericList (Lab 12 Activity)
 */


package varholaM_lab12;

public class VarholaM_lab12_GenericList<T> {

	final int MAX_SIZE =  300;		//max size of the array

	private T [] objArray;
	private int index;		//next empty slot
	private int iterator;
	
	//default constructor
	@SuppressWarnings("unchecked")
	public VarholaM_lab12_GenericList(){
		objArray = (T[]) new Object[MAX_SIZE];
		iterator=0;
	}
	
	//nondefault constructor
	@SuppressWarnings("unchecked")
	public VarholaM_lab12_GenericList(int size){
		//instantiates the array with size
		objArray = (T[]) new Object[size];
		iterator = 0;
	}
	
	//get length of an array
	public int getLength(){
		//returns the length of the array
		return objArray.length;

	}
			
	public T[] getArray(){
		//return a copy of the array
		T[] tempArray = objArray;
		return tempArray;
	}

	public void setArray(T [] anArray){
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
	
	public T getElement(int pos){
	//returns the element of the array at pos
		return objArray[pos];
	}
	
	public void setElement(T obj, int pos){
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
	
	public boolean equals(VarholaM_lab12_GenericList<T> anArray){
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
	public void add(T obj){
		// stores obj in the  next empty slot in the array & increments the next empty slot variable
		if (index < objArray.length){
			objArray[index++] = obj;
		}
	}
	
	//inserts an element in the array
	public void insert(T obj, int pos){
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
	public int isThere(T obj){
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
	public void delete(T obj){
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
	
	@SuppressWarnings("unchecked")
	public void clear(){
		objArray = (T[]) new Object[objArray.length];
		reset();
		index = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void trim(){
		T[] tempArray =  (T[]) new Object[index];
		for (int i = 0; i < index; i++){
			tempArray[i] = objArray[i];
		}
		objArray = tempArray;
	}
	
	public void moreCapacity(int multiple){
		@SuppressWarnings("unchecked")
		T[] tempArray = (T[]) new Object[objArray.length*multiple];
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
	
	public T getNext(){
		return hasNext() ? objArray[iterator++]:null;
	}
	
	
}
