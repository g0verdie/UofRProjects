public class LinkedList<T> implements LLInterface<T>
{
	//intiate a head and tail node
	private Node<T> head;
	private Node<T> tail;
	
	//node class
	public static class Node<T>
	{	
		//constructor
		public Node(T d,Node<T> n, Node<T> p)
		{ data = d; next = n; prev = p;}
		
		public T data;
		public Node<T> next;
		public Node<T> prev;
	}

	//LL constructor
	public LinkedList()
	{
		//initialize the head and tail nodes
		head = new Node<T>(null,tail,null);
		tail= new Node<T>(null,null,head);
		head.next = tail;
	}
	

	//call head
	public Node<T> callHead()
	{return head;}
	
	//call tail
	public Node<T> callTail()
	{return tail;}
	
	//add one to the end of the list
	public void insert(T x)
	{
		if(x != "")
		{
			Node<T> temp = new Node<T>(x,tail,tail.prev);
			tail.prev.next = temp;
			tail.prev = temp;
		}
	}
	
	//print the whole list
	public void printList(Node<T> node)
	{
		System.out.println(node.data);
		if(node.next != null)
			printList(node.next);
	}
	
	
	//find presence of data
	public boolean lookUp(T x, Node<T> node)
	{
		if(node.data != x)
		{
			if(node.next != null)
			{
				return lookUp(x, node.next);
			}
			else
				return false;		
		}
		else
		{
			return true;
		}
		
	}
	
	//delete an item
	public void delete(T x, Node<T> node)
	{
		if(node.data != x)
		{
			if(node.next != null)
			{
				delete(x, node.next);
			}
			else
				Main.print("not found");	
		}
		else
		{
			node.next.prev = node.prev;
			node.prev.next = node.next;
			if(node.next != null);
		}
	}
	
	public int getLength(Node<T> node, int i)
	{
		if(node.next != null)
		{
			i++;
			return getLength(node.next,i);
		}
		else
			return i;
	}
	public String[] toString(Node<T>node, int i,int length,String[] holder)
	{
		if(node.next != null)
		{
			holder[i] = node.data.toString();
			i++;
			return toString(node.next,i,length,holder);
		}
		else
		{
			return holder;
		}
	}
}