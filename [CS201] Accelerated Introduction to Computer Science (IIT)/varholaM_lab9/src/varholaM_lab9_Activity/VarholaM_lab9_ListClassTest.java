package varholaM_lab9_Activity;

public class VarholaM_lab9_ListClassTest {

	public static void main(String[] args) {
		VarholaM_lab9_ArrayList testList = new VarholaM_lab9_ArrayList(5);
		
		for (int i = 0; i < 5; i++){
			if (Math.random()<0.5){
				testList.add(new VarholaM_lab9_Circle((int)(Math.random() * 10 + 1)));
			}else{
				testList.add(new VarholaM_lab9_Cylinder( (int)(Math.random() * 10 + 1),(int)(Math.random() * 10 + 1)));
			}
		}
		System.out.println("Here is the current ArrayList:");
		System.out.println(testList.toString());

		testList.delete(1);
		System.out.println("After ArrayList[1] has been deleted:");
		System.out.println(testList.toString());
		
//		testList.delete(0);
//		System.out.println("After ArrayList[0] has been deleted:");
//		System.out.println(testList.toString());
//		
//		testList.delete(testList.getIndex());
//		System.out.println("After Last elementArrayList has been deleted:");
//		System.out.println(testList.toString());
				
		
		VarholaM_lab9_Cylinder testCylinder = new VarholaM_lab9_Cylinder(12,12);
		testList.insert(testCylinder, 1);
		System.out.println("After testCylinder = Cylinder(12,12) has been inserted into ArrayList[1]:");
		System.out.println(testList.toString());
		
		
		System.out.println("Is there testCylinder?");
		System.out.println(testList.isThere(testCylinder));
		System.out.println();
		
		testList.delete(testCylinder);
		System.out.println("After deleting testCylinder.");
		System.out.println(testList.toString());
		
		testList.reset();
		
		int temp = 0;
		while(testList.hasNext()){
			System.out.printf("Get object %d: %s\n",temp,testList.getNext());
			temp++;
			System.out.printf("testList hasNext?: %s\n",testList.hasNext());
		}
		
		System.out.println();
		
		System.out.printf("testList.isFull?: %s\n", testList.isFull());
		System.out.println("Adding elements untill full");
		while (!testList.isFull()){
			testList.add(new VarholaM_lab9_Circle((int)(Math.random() * 10 + 1)));
		}
		
		System.out.println();
		System.out.printf("testList.isFull?: %s\n", testList.isFull());
		System.out.printf("testList.isEmpty?: %s\n", testList.isEmpty());
		
		System.out.println(testList.toString());

		System.out.println("Clearing the aray.");
		testList.clear();
		System.out.printf("testList.isEmpty?: %s\n", testList.isEmpty());

		
		System.out.println();
		
		System.out.printf("Current array has an index of %d and a length of %d\n", testList.getIndex(),testList.getLength());
		
		System.out.println("Adding elements untill full");
		while (!testList.isFull()){
			testList.add(new VarholaM_lab9_Circle((int)(Math.random() * 10 + 1)));
		}
		System.out.printf("testList.isFull?: %s\n", testList.isFull());

		System.out.println();
		System.out.println("Deleting element [0]");
		testList.delete(0);
		System.out.printf("Current array has an index of %d and a length of %d\n", testList.getIndex(),testList.getLength());

		System.out.println();
		System.out.println("calling trim()");
		testList.trim();
		System.out.printf("Current array has an index of %d and a length of %d\n", testList.getIndex(),testList.getLength());

		System.out.println();
		System.out.println("calling moreCapacity(4)");
		testList.moreCapacity(4);
		System.out.printf("Current array has an index of %d and a length of %d\n", testList.getIndex(),testList.getLength());

		System.out.println();
		System.out.println("Clearing the aray.");
		testList.clear();
		System.out.printf("testList.isEmpty?: %s\n", testList.isEmpty());
		System.out.printf("Current array has an index of %d and a length of %d\n", testList.getIndex(),testList.getLength());

		System.out.println("\nSuccess!\n");
		
	}

}
