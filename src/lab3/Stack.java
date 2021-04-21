package lab3;

public class Stack { //Array implementation of stack containing TNodes
	int top;
	TNode[] TNodeStack;
	int maxSize=1000;  //Max size of the stack is set arbitrarily to 1000
	
	public Stack(){
		TNodeStack=new TNode[maxSize];  //Empty stach constructor
	}
	
	public Stack(int n){
		TNodeStack=new TNode[n];  //TNodeStack constructor with size n elements
	}
     
	public void push(TNode node) {
		if(top==TNodeStack.length-1){
			TNode[] lgStack=new TNode[2*TNodeStack.length]; //A new array of 2 times the original length is initialized
            for(int i=0;i<lgStack.length;i++){
                lgStack[i]=TNodeStack[i]; //Each of the original elements in the stack are added to the new stack
            }
            TNodeStack=lgStack;
		}
		TNodeStack[++top]=node; //The size is first pre-incremented and then the node is added so there is space in the array
	}
	
	public TNode pop() {
		if(isEmpty()) {
			throw new IllegalArgumentException ("Stack underflow");  //if the stack is empty. nothing can be popped
		}
		TNode tempE=TNodeStack[top]; //Stack follows LIFO, therefore the topmost element is returned
		TNodeStack[top--]=null; //The popped index is overwritten as null
		return tempE;
	}
	
	public boolean isEmpty() {
		return top<0?true:false; //If the index of top is less than 0, the stack is empty.
	}
	
	public int size() {
		return TNodeStack.length; //The size of the stack is simply the length of the array
	}
}
