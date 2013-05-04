import java.util.*;

public class Graph 
{
	Hashtable<String,Edge> roads;
	Hashtable<String,Vertex> intersections;

	int maxx;
	int maxy;

	//initial graph- an empty array of roads and intersections with a max size set to 0
	public Graph()
	{
		roads = new Hashtable<String,Edge>();
		intersections = new Hashtable<String,Vertex>();
		maxx = 0;
		maxy = 0;
	}

	//vertex class
	public class Vertex 
	{
		public String id;
		public int x, y;
		public ArrayList<Edge> edges;
		public boolean known;
		public boolean known2;
		public double dist;
		public Edge path;

		public Vertex(String id1, int x1, int y1) 
		{
			id = id1;
			x = x1;
			y = y1;
			edges = new ArrayList<Edge>();
			known = false;
			known2 = false;
			dist = 1000000;
		}
	}

	//edge class
	public class Edge 
	{
		public String id;
		public Vertex v1;
		public Vertex v2;
		public Double length;
		public int flagged;
		public Vertex path;

		public Edge(String id1, Vertex v11, Vertex v21) 
		{
			id = id1;
			v1 = v11;
			v2 = v21;
			length = Math.pow(
					Math.pow((double) v2.x - v1.x, 2)
							+ Math.pow((double) v2.y - v1.y, 2), .5);
			flagged = 0;
		}
		
		//get the other vertex of an edge
		public Vertex otherVert(Vertex v)
		{
			if(v.id.equals(v1.id))
				return v2;
			else
				return v1;
		}
	}

	//add a string or edge into the data
	public void into(String[] splitt) 
	{
		//if it's an intersection
		if (splitt[0].equals("i")) 
		{
			Vertex temp = new Vertex(splitt[1],
					(int) Double.parseDouble(splitt[2]),
					(int) Double.parseDouble(splitt[3]));
			//if the x or y is bigger than any other, we need to remember this so the graph will be big enough
			if ((int) Double.parseDouble(splitt[2]) > maxx)
				maxx = (int) Double.parseDouble(splitt[2]);

			if ((int) Double.parseDouble(splitt[3]) > maxy)
				maxy = (int) Double.parseDouble(splitt[3]);
			intersections.put(temp.id,temp);
		}

		// if its a road
		else if (splitt[0].equals("r")) 
		{
			// make a new road
			Edge temp;
			// two new vertecies to be inserted
			Vertex v1 = new Vertex("", 0, 0);
			Vertex v2 = new Vertex("", 0, 0);
			
			v1 = intersections.get(splitt[2]);
			v2 = intersections.get(splitt[3]);
			
			temp = new Edge(splitt[1], v1, v2);
			v1.edges.add(temp);
			v2.edges.add(temp);
			roads.put(temp.id,temp);
		}
	}

	//method for minimum spanning tree
	public void minTree(String start) 
	{
		//make a heap for sorting the possible roads to go down
		PriorityQueue<Edge> choices = new PriorityQueue<Edge>(11,
				new Comparator<Edge>() 
				{
					public int compare(Edge a, Edge b) 
					{
						return (int) (a.length - b.length);
					}
				});

		// start with the beginning
		Vertex pointer = intersections.get(start);
		// until you run out of streets
		while (true)
		{
			// we've discovered the point that we're at now
			pointer.known = true;

			// for all the connectors at the current point
			for (int i = 0; i < pointer.edges.size(); i++) 
			{
				// if the intersection at the other end is unknown, queue up the
				// edge
				if (!pointer.edges.get(i).otherVert(pointer).known)
					choices.add(pointer.edges.get(i));
			}

			// go down the shortest road
			Edge plucked = choices.poll();
			
			//that spot is now flagged
			plucked.flagged = 1;
			//the unknown vertex of the edge is the one we want to go down
			if (!plucked.v1.known) 
			{
				pointer = plucked.v1;
			}
			else 
			{
				pointer = plucked.v2;
			}
			if (choices.isEmpty())
				break;
		}

	}
	
	//find the shortest distance between 2 points
	public void shortestDist(String from, String to1) 
	{
		//make a heap of edges to pick shortest possible one
		PriorityQueue<Edge> choices = new PriorityQueue<Edge>(11,
				new Comparator<Edge>() 
				{
					public int compare(Edge a, Edge b) 
					{
						return (int) (a.length - b.length);
					}
				});

		// start with the beginning
		//Vertex pointer = intersections.get(intersections.get(from));
		Vertex pointer = intersections.get(from);
		Vertex fromHold = intersections.get(from);
		Vertex to = intersections.get(to1);
		pointer.dist = 0.0;
		
		// until you run out of streets
		while (true) 
		{
			// we've discovered the point that we're at now
			pointer.known2 = true;
			// System.out.println(pointer.edges.size());

			// for all the connectors at the current point
			for (int i = 0; i < pointer.edges.size(); i++) 
			{

				// if the intersection at the other end is unknown
				Vertex other = pointer.edges.get(i).otherVert(pointer);
				if(!other.known2)
				{
					//distance of the edge we'd cover
					double cvw = pointer.edges.get(i).length;
					
					//if the total distance of this move is less than total distance now
					if(pointer.dist + cvw < other.dist)
					{
						//set the others distance to what it should be (as opposed to inf)
						other.dist = pointer.dist + cvw;
						//the current edge now connects to the pointer
						pointer.edges.get(i).path = pointer;
						//the other vert now connects to the edge
						other.path = pointer.edges.get(i);
					}
				
					choices.add(pointer.edges.get(i));
				}
			}

			Edge plucked = choices.poll();
			
			//the adjascent vertex is now the pointer
			if (!plucked.v1.known2) 
			{
				pointer = plucked.v1;
			}
			else 
			{
				pointer = plucked.v2;
			}
			if (choices.isEmpty())
			{
				//System.out.println("not looping when finding path");
				break;
			}
		}
		//paint the path you took back
		paintShort(to,to.path,fromHold);
	}
	
	//paints from the last point to the beginning point
	public void paintShort(Vertex last, Edge next, Vertex first)
	{
		next.flagged = 2;
		if(!next.path.id.equals(first.id))
		{
			paintShort(next.path,next.path.path,first);
			System.out.println(next.path.id);
		}
	}
}
