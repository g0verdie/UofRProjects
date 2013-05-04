import java.util.*;
public class Node {
	private RandomVariableRG variable;
	private ArrayList<Node> parents = null;
	private 
	
	
	public Node(RandomVariableRG variable, ArrayList<Node> parents) {
		this.variable = variable;
		
		if(parents != null)
			this.parents.addAll(parents);
		else
			this.parents = null;
	}
	
	public void addParent(Node parent) {
		this.parents.add(parent);
	}
	
	public String getName() {
		return variable.getName();
	}
	public RandomVariableRG getVariable() {
		return variable;
	}
	
	public void print() {
		variable.print();
		if(parents == null)
			System.out.println("no parents");
		else {
			System.out.println("parents: ");
			for(Node p : parents) {
				System.out.print(p.getName()+", ");
			}
		}
	}
}
