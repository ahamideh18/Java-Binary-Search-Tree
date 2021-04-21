package lab3;

public class BSTSet {
	TNode root;
	
	//---------------------------------CONSTRUCTORS--------------------------------
	public BSTSet() {
        root = null; //Constructing an empty set; root==null.
    }

	public BSTSet(int[] input){
      /*for(int i=0;i<input.length;i++){
            add(input[i]);
       }*/
		input=sortArray(input);  //Sorting the input array in order to make minimum height tree
		input=removeRep(input);  //Removing any repetitions from the array.
		root=new TNode(input[input.length/2], null, null); //The root of the tree is the element in the middle of the array since it is sorted
        
        for(int i=(input.length/2)-1;i<input.length;i++){  //For loop adding elements greater then the root 
            this.add(input[i]);  //Calling add() to add the node to the tree
        }
        
        for(int i=(input.length/2)-1;i>=0;i--){   //For loop adding elements less then the root 
            this.add(input[i]);
        }
	}
	
	//---------------------------------isIn()--------------------------------
	public boolean isIn(int v) { //Theta(logn)
		TNode currNode=this.root;  //This code will traverse the tree starting from the root
		
		if(currNode==null)  //If the tree is empty false is returned 
        { 
            return false; 
        }
		
		for(;currNode!=null;) {  //Traversing the tree using post-order traversal (LRC)
			if(v<currNode.element){  //If the integer is less then the element of the node, go to the left
				currNode=currNode.left;
            }
			else if(v>currNode.element){  //If the integer is greater then the element of the node, go to the right
				currNode=currNode.right;
            }
			else if(v==currNode.element) {  //If the integer is equal to the element of the node return true
				return true;
			}
		}
		return false;
	}
	
	//---------------------------------add()----------------------------------
	public void add(int v){   //Theta(n) (worst) Theta(logn) (average)
        if(this.isIn(v))  //If the integer is already in the tree, it is not modified
        {
            return;
        }

        else if(this.root==null){  //If the tree is empty, the integer is set as the root of the tree with no children
            this.root=new TNode(v,null,null);
            return;
        }
        
        else{ //If the tree is not empty, the new node will always be added as a leaf
            int flag=1;  //A flag is set to indicate when to stop traversing after the element is added
            TNode currNode=root;  //Current node is the root of the tree; where traversal starts
            
            while(flag==1) {  //As long as flag is 1 keep traversing
            	if(v>currNode.element) { //If the integer is greater then the current node, go to the right of the node
                    if(currNode.right==null) { //When an empty spot is found, add the node
                    	currNode.right=new TNode(v,null,null);
                    	flag=0; //Change flag value to 0
                    }
                    currNode=currNode.right; 
                }
            	
            	else if(v<currNode.element) {  //If the integer is less than current element, go to the left
                    if(currNode.left == null) {
                    	currNode.left = new TNode(v,null,null); 
                        flag=0; 
                    }
                    currNode=currNode.left;
                } 
            }
        }
	}
	
	//---------------------------------remove()--------------------------------
	public boolean remove(int v) { //Remove takes Theta(logn) to search and Theta(n) to remove the element
		if(isIn(v)) { 
			this.root=remove(v,root);  //If the integer is in the tree, the function calls remove and returns false
            return true;
        }
		else {  //If the integer is not in the tree false is returned
            return false; 
        }
	}
	
	private TNode getMin(TNode node) {
        TNode min=node;
        while (min.left!=null){  //This function returns the minimum value in the tree
            min=min.left;
        }
        return min;
    }
	
	public TNode remove(int v,TNode currNode) {  
		if(currNode==null) {
			return currNode; 
		}
		else if(v<currNode.element){ //If the element is less than the value of the node
            currNode.left=remove(v,currNode.left); //Traverse to the left of the tree
        }
		else if(currNode.element<v){  //If the element is greater than the value of the node
            currNode.right=remove(v,currNode.right); //Traverse to the right of the tree
        }
		else{  //When the element of the save value as the integer is found
			if(currNode.left==null){  //If there's only one child delete the node and replace the deleted node with its only child
                return currNode.right;
            }
			else if(currNode.right== null){  //If there's only one child to the left, delete the node and replace it with its only child
                return currNode.left;
			}
			else{ 
                currNode.element=getMin(currNode.right).element;  //If a node has two children, get the minimum node in the right subtree of the node
                currNode.right=remove(currNode.element,currNode.right);  //Replace the minimum node in the right subtree with the node being deleted
            }
		}
		return currNode;
	}
	
	//---------------------------------union()--------------------------------
	public BSTSet union(BSTSet s){  //Union uses a driver function that simply modifies the set instead of returning a new set
		if(s.root==null) { //If s is empty, then the union is this
			return this;
		}
		else if(this.root==null) { //If this is empty, then the union is s
			return s;
		}
		BSTSet uniSet=new BSTSet(s.toArray()); 
        union(this.root, uniSet);
        return uniSet;
    }
    private void union(TNode currNode, BSTSet s){ 
        if (currNode==null){   //Union traverses through the set s and calls add() to add each element that is not repeated
            return; 
        }
        union(currNode.left, s);  //Uses recursion to traverse and add the elements
        s.add(currNode.element);
        union(currNode.right, s);
    }
    
    //---------------------------------intersection()--------------------------------
    public BSTSet intersection(BSTSet s) {  //Elements that are in both s and this
    	BSTSet interSet=new BSTSet();  //A new BSTSet interSet to store the values of the intersection
    	int[] thisArr=this.toArray();  //Both s and this are converted to arrays to make traversing them easier
    	int[] sArr=s.toArray();
    	
    	for(int i=0;i<thisArr.length;i++) {  //Looping through each list
    		for(int j=0;j<sArr.length;j++) {
    			if(thisArr[i]==sArr[j]) { //If ith element in this array (outer loop)==jth element in s array (inner loop)
    				interSet.add(thisArr[i]);
    			}
    		}
    	}
    	return interSet;
    }
    
    //---------------------------------difference()--------------------------------
    public BSTSet difference(BSTSet s) { //Find elements in this that are not in s
    	BSTSet diffSet=new BSTSet();  //Creates a new BSTSet so that the original sets are not modified
    	int[] thisArr=this.toArray();  //this is converted to an array to make traversing it easier
    	
    	for(int i=0;i<thisArr.length;i++) {
    		if(s.isIn(thisArr[i])==false) { //Every element in thisArray is checked if it is in s, if not it is added to the new diffSet
    			diffSet.add(thisArr[i]);
    		}
    	}
    	return diffSet;
    }

	//---------------------------------size()--------------------------------
	public int size(){
        return size(this.root);
    }
    private int size(TNode currNode){ 
        if(currNode==null){ //If the set is empty, the size==0
            return 0;
        }
        return 1+size(currNode.left)+size(currNode.right); //We traverse the tree and add 1 for each node + 1 for the root
    }
	
  //---------------------------------height()--------------------------------
    public int height(){
        return height(this.root); 
    }
    public int height(TNode currNode){
		if(currNode==null) { //If the tree is empty -1 is returned as the height
			return -1;
		}         
		else {
			int lt=height(currNode.left); int rt=height(currNode.right); //the tree is traversed from the left and right 
			return 1+Math.max(lt,rt);  //The height is the maximum depth in the tree and 1 is added to account for the root
		}
	}
	
  //---------------------------------printBSTSet()--------------------------------
	public void printBSTSet(){ //THIS CODE IS PROVIDED ON THE LAB MANUAL
        if(root==null)
            System.out.println("The set is empty");
        else{
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }
    
    private void printBSTSet(TNode t){
        if(t!=null){
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }
  //---------------------------------printNonRec()--------------------------------
    public void printNonRec() {
    	Stack BSTStack=new Stack();  //Creates new stack of custom data type Stack
        TNode currNode=this.root;    //Current Node is the root of the tree
        
        while(BSTStack.isEmpty()==false||currNode!=null) {  //Traversal is done from left to right
        	if(currNode!=null) {
                BSTStack.push(currNode);  //Elements are pushed in stack 
                currNode=currNode.left;
            }
        	else {
        		currNode=BSTStack.pop();  //To print the elements, the items are popped from the stack
                if(currNode!=null) {
                    System.out.print(currNode.element+", ");
                    currNode=currNode.right;
                }    
            }
        }
        System.out.print("\n");
    }
    
  //---------------------------------EXTRA METHODS--------------------------------
    public int[] toArray(){
        int[] setArray=new int[this.size()];
        if(this.size()==0) {
        	return setArray;
        }
        else {
        	toArray(this.root,setArray,0);
        	return setArray;
        }
    }
    public int toArray(TNode currNode, int[] setArray, int i){
        if(currNode==null) { 
        	return i; 
        }
        i=toArray(currNode.left,setArray,i); 
        setArray[i++]=currNode.element;
        i=toArray(currNode.right,setArray,i); 
        return i;
    }
    
    private int[] sortArray(int[] arr) {
    	int temp=0;
        for (int i=0;i<arr.length;i++) {     
            for (int j=i+1;j<arr.length;j++) {     
               if(arr[i]>arr[j]) {    
                   temp=arr[i];    
                   arr[i]=arr[j];    
                   arr[j]=temp;    
               }     
            }     
        }  
        return arr;
    }
    
    private int[] removeRep(int[] arr) {
    	for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                for(int j=i+1; j<arr.length-1;j++){
                    arr[j]=arr[j+1];
                }
            }
        }
    	return arr;
    }
}
