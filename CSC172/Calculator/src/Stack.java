public class Stack<T> extends LinkedList<T> implements StackInterface<T> 
{	
	//constructor  same as linked list
	public Stack()
	{
		super();
	}
	
	//remove the last element
	public T pop()
	{
		T popped = callTail().prev.data;
		callTail().prev.prev.next = callTail();
		callTail().prev = callTail().prev.prev;
		return popped;
	}
	
	//add last element
	public void push(T pushed)
	{insert(pushed);}
	
	//read the last element
	public T read()
	{return callTail().prev.data;}
}
