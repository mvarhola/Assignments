package finalPrep;

public class TestArrayClass {

	private int MAXSIZE = 20;
	private Object[] testArray;
	
	public TestArrayClass() {
		testArray = new Object[MAXSIZE];
	}

	public TestArrayClass(int size) {
		testArray = new Object[size];
	}

	public Object[] getTestArray() {
		return testArray;
	}

	public void setTestArray(Object[] testArray) {
		this.testArray = testArray;
	}
	
	public Object getObject(int x){
		return testArray[x];
	}
	
	public void setObject(int x, Object y){
		testArray[x]=y;
	}
	
	public String toString(){
		String temp = "";
		for (Object i:testArray){
			temp+=i.toString();
			temp+='\n';
		}	
		return temp;
	}
	
	public boolean equals(TestArrayClass x){
		boolean isEqual = false;
		if (testArray.length==x.getTestArray().length){
			isEqual = false;
			for (int i = 0; i < testArray.length;i++){
				isEqual = (testArray[i]==x.getObject(i));
			}
		}
		return isEqual;
		
	}
	
	public int isThere(Object x){
		int pos = -1;
		for (int i = 0; i < testArray.length;i++){
			if (testArray[i].equals(x)){
				pos = i;
				break;	// only get the first object occurence,
			}
		}
		return pos;
	}
	
	public void add(Object x){
		for (int i = 0; i < testArray.length; i++){
			if (testArray[i].equals(null)){	//finds the first empty space (should be after last element in this case
				testArray[i]=x; 
				break;	//stop from adding the same element extra amount
			}
		}
	}
	
	public void delete(Object x){
		if (isThere(x) >= 0){
			for (int i = isThere(x); i < testArray.length-1;i++){
				testArray[i]=testArray[i+1];
			}
			testArray[testArray.length]=null;
		}
	}
	
	public void insert(Object x, int pos){
		if (pos < testArray.length){
			for (int i = testArray.length-2;i>=pos;i--){
				testArray[i+1] = testArray[i];
			}
			testArray[pos] = x;
		}
	}
	
	public void swapObjects(int x, int y){
		Object temp = testArray[y];
		testArray[y] = testArray[x];
		testArray[x]=temp;
	}

}
