public class LinkedList implements LLInterface
{
	//intiate a head and tail node
	private Node head;
	//private Node tail;
	
	//node class
	public static class Node
	{	
		//constructor
		public Node(String d,Node n)
		{ data = d; next = n;}
		
		public String data;
		public Node next;
		//public Node prev;
	}

	//LL constructor
	public LinkedList()
	{head = new Node("hhh",null);}
	
	//call head
	public Node callHead()
	{return head;}
	
	//add one to the end of the list
	public void insert(String x)
	{
		if(x != "")
		{
			Node temp = new Node(x,head.next);
			head.next = temp;
		}
	}
	
	//print the whole list
	public void printList(Node node)
	{
		System.out.println(node.data);
		if(node.next != null)
			printList(node.next);
	}
	
	
	//find presence of data
	public boolean lookUp(String x, Node node)
	{
		if(!node.data.equalsIgnoreCase(x))
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
	
	public int getLength(Node node, int i)
	{
		if(node.next != null)
		{
			i++;
			return getLength(node.next,i);
		}
		else
			return i;
	}
}