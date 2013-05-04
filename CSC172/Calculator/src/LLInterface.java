public interface LLInterface<T>
{
	public void insert(T x);
	public void delete(T x, LinkedList.Node<T> node);
	public boolean lookUp(T x, LinkedList.Node<T> node);
	public void printList(LinkedList.Node<T> node);
}
