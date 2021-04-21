package lab3;


public class falseTest {
	public static void main(String[] args) {
		
		int[] set1 = {5,6,8,1,2,5,8};
		int[] set2 = {-1,5,6,8,-65,1,2,5,8};
		int[] set3 = {50,30,20,40,-65,70,60,80,1,2};
		int[] set4 = {2,32,24,37,58,7,48,60,70,55,42,40};
		int[] set5 = {0,0,0,0,0,0,0,0};
		
		BSTSet s1 = new BSTSet(set1);
		BSTSet s2 = new BSTSet(set2);
		BSTSet s3 = new BSTSet(set3);
		BSTSet s4 = new BSTSet(set4);
		BSTSet s5 = new BSTSet(set5);
		
		System.out.print("Set1: "); s1.printBSTSet();
		System.out.print("Set2: "); s2.printBSTSet();
		System.out.print("Set3: "); s3.printBSTSet();
		System.out.print("Set4: "); s4.printBSTSet();
		System.out.print("Set5: "); s5.printBSTSet();
		System.out.println("\n");
		
		int v1 = 5;
		int v2 = 10;
		int v3 = 6; 
		int v4 = 0;

		System.out.println("constructor1" );
	    BSTSet emptySet = new BSTSet();
		emptySet.printBSTSet();
		System.out.println("\n");
				 
		System.out.println("constructor2---no repetitions" );
		s3 = new BSTSet(set3);
		s3.printBSTSet();
		System.out.println("\n");
		
		System.out.println("constructor2---with repetitions" );
		s2 = new BSTSet(set2);
		s2.printBSTSet();
		System.out.println("\n");
		
		System.out.println("isIn(v1)---true" );
		System.out.println("5 is in set set1");
		s1 = new BSTSet(set1);
		System.out.println(s1.isIn(v1));
		System.out.println("\n");
		
		System.out.println("isIn(v2)---false" );
		System.out.println("10 is in set set3");
		System.out.println(s3.isIn(v2));
		System.out.println("\n");
		
		System.out.println("add(v1)---5 in set" );
		s1 = new BSTSet(set1);
		s1.add(v1);
		s1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("add(v2)---10 not in set" );
		s4 = new BSTSet(set4);
		s4.add(v2);
		s4.printBSTSet();
		System.out.println("\n");			
		
		System.out.println("remove(v2)--10 not in set5" );
		s5 = new BSTSet(set5);
		System.out.println(s5.remove(v2)); //false
		s5.printBSTSet();
		System.out.println("\n");
		
		System.out.println("remove(v1)--0 in set5" );
		s5 = new BSTSet(set5);
		System.out.println(s5.remove(v4)); //true
		s5.printBSTSet();
		System.out.println("\n");
		
		System.out.println("remove(v3)--v3 in set" );
		s1 = new BSTSet(set1);
		System.out.println(s1.remove(v3)); //true
		s1.printBSTSet();
		System.out.println("\n");
		
		System.out.println("union()---set1 and set3" );
		s1 = new BSTSet(set1);
		s3 = new BSTSet(set3);
		BSTSet u1 = s1.union(s3); 
		u1.printBSTSet();
		System.out.println("\n");
		
		
		System.out.println("union()---set2 and set4" );
		s2 = new BSTSet(set2);
		s4 = new BSTSet(set4);
		BSTSet u2 = s4.union(s2); 
		u2.printBSTSet();
		System.out.println("\n");
		
		System.out.println("union()---emptySet and set4" );
		emptySet = new BSTSet();
		s4 = new BSTSet(set4);
		BSTSet u3 = s4.union(emptySet); 
		u3.printBSTSet();
		System.out.println("\n");
		
		System.out.println("intersection()---set3 and set4" );
		s3 = new BSTSet(set3);
		s4 = new BSTSet(set4);
		BSTSet i1 = s3.intersection(s4); 
		i1.printBSTSet();
		System.out.println("\n");

		System.out.println("intersection()---set5 and set1" );
		s5 = new BSTSet(set5);
		s1 = new BSTSet(set1);
		BSTSet i2 = s1.intersection(s5); 
		i2.printBSTSet();
		System.out.println("\n");
		
		
		System.out.println("intersection()---empty set and set5" );
		emptySet = new BSTSet(); //empty set
		s5 = new BSTSet(set5);
		BSTSet i3 = emptySet.intersection(s5); 
		i3.printBSTSet();
		System.out.println("\n");
		
		System.out.println("size() and height() testing" );
		s5 = new BSTSet(set5);
		System.out.println("size set5: " + s5.size());
		System.out.println("height set5: " + s5.height()); 
		s4 = new BSTSet(set4);
		System.out.println("size set4: " + s4.size());
		System.out.println("height set4: " + s4.height()); 
		System.out.println("\n");

		
		System.out.println("size() and height()---empty set" );
		emptySet = new BSTSet(); 
		System.out.println("size empty: " + emptySet.size()); 
		System.out.println("height empty " + emptySet.height());
		System.out.println("\n");
		

		System.out.println("printNonRec()" );
		s2 = new BSTSet(set2);
		s2.printNonRec(); 
		s3 = new BSTSet(set3);
		s3.printNonRec(); 
	}
}
