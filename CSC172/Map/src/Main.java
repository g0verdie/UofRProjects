import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		
		//open file
		System.out.println(args[0]);
		File file = new File(args[0]);
		//read the file
		try 
		{
			Scanner scanner = new Scanner(file);
			Graph graph = new Graph();
			
			//so long as we have input to write
			while(scanner.hasNextLine())
			{
				String next = scanner.nextLine();
				//split it by \t
				String[] splitt = next.split("\t");
				//add each array into the graph
				graph.into(splitt);
					
			}
		
			//draw the minimum tree
			//graph.minTree(args[1]);
			//shortest distance
			graph.shortestDist(args[1], args[2]);
			//draw the map
			GUI gui = new GUI(graph.maxx,graph.maxy,graph.intersections, graph.roads);
		}
		//if not found throw an exception
		catch (FileNotFoundException e)
		{e.printStackTrace();}
	}
}
