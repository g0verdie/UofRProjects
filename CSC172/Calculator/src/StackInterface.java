public interface StackInterface<T> 
{
	//remove the last element
	public T pop();
	//add a last element
	public void push(T pushed);
	//read the last element
	public T read();
}
