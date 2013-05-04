import java.util.*;
/*
 * represents a bayesian network. Nodes are BNodes
 */
public class BayesianNetworkRG {
	private ArrayList<BNode> BNodes;
	//private ArrayList<Integer> connectors;
	
	public BayesianNetworkRG() 
	{BNodes = new ArrayList<BNode>();}
	
	public void addBNode(BNode BNode) 
	{BNodes.add(BNode);}
	
	public ArrayList<BNode> getNodesSorted() {
		// ``L <- Empty list that will contain the sorted nodes''
		ArrayList<BNode> L = new ArrayList<BNode>(BNodes.size());
		// ``S <- Set of all nodes with no outgoing edges''
		ArrayList<BNode> S = new ArrayList<BNode>(BNodes.size());
		for (BNode node : BNodes) {
			if (getChildren(node).isEmpty()) {
				S.add(node);
			}
		}
		// Can't mark nodes visited; instead keep as a set
		ArrayList<BNode> visited = new ArrayList<BNode>(BNodes.size());
		// ``for each node n in S do''
		for (BNode n : S) {
			// ``visit(n)''
			visit(n, L, visited);
		}
		return L;
	}

	protected void visit(BNode n, ArrayList<BNode> L, ArrayList<BNode> visited) {
		// ``if n has not been visited yet''
		if (!visited.contains(n)) {
			// ``mark n as visited''
			visited.add(n);
			// ``for each node m with an edge from m to n do''
			for (BNode m : BNodes) {
				if (getChildren(m).contains(n)) {
					// ``visit(m)''
					visit(m, L, visited);
				}
			}
			// ``add n to L''
			L.add(n);
		}
	}

	public BNode getNodeByName(String name) {
		for(BNode c : BNodes) {
			if(c.getName().equals(name))
				return c;
		}
		return null;
	}
	public ArrayList<BNode> getNodes() {
		return BNodes;
	}
	
	public ArrayList<BNode> getChildren(BNode parent) {
		ArrayList<BNode> nodes = this.getNodes();
		ArrayList<BNode> children = new ArrayList<BNode>();
		for(int i = 0; i < nodes.size(); i++) {
			if(!nodes.get(i).getName().equals(parent.getName()) && nodes.get(i).getParents().contains(parent)) {
				children.add(nodes.get(i));
				}
		}
		return children;
	}
	
	public void print() {
		for(BNode c : BNodes) {
			c.print();
		}
	}
}
