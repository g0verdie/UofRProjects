public class Queue<T> extends LinkedList<T> implements QueueInterface<T> 
{	
	//constructor same as LinkedList
	public Queue()
	{
		super();
	}
	
	//remove the first item
	public T dequeue()
	{
		T end = callHead().next.data;
		delete(end,callHead());
		return end;
	}
	
	//add the last item
	public void enqueue(T pushed)
	{insert(pushed);}
}
