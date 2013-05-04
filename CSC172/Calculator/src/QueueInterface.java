public interface QueueInterface<T> 
{
	//remove the first elment
	public T dequeue();
	//add the last element
	public void enqueue(T pushed);
}
