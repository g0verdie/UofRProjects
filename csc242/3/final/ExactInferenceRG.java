import java.util.*;
public class ExactInferenceRG {
	
	BNode query;
	ArrayList<String[]> evidence;
	ArrayList<BNode> evidenceVars;
	BayesianNetworkRG bnet;
	
	public ExactInferenceRG(BNode query,ArrayList<String[]> evidence,BayesianNetworkRG bnet) {
		this.query = query;
		this.evidence = evidence;
		this.bnet = bnet;
		
		setUpEvidence();
	}
	
	private void setUpEvidence() {
		evidenceVars = new ArrayList<BNode>();
		for(String[] clause : evidence) {
			BNode current = bnet.getNodeByName(clause[0]);
			current.setVariable(clause[1]);
			evidenceVars.add(current);
		}
	}
	
	public double getProbability() {
		return sumChain();
	}
	
	public double sumChain() {
		//collect all unused variablse
		ArrayList<BNode> unused = BNode.copyArray(bnet.getNodes());
		unused.removeAll(evidenceVars);
		unused.remove(query);
		System.out.println("unused size- "+unused.size());
		ArrayList<BNode> used = BNode.copyArray(bnet.getNodes());
		used.removeAll(unused);
		System.out.println("used size- "+used.size());
		
		//set up the tree
		JointDistTree sumTree = new JointDistTree(unused,used);
		double answer = sumTree.sumProbs(bnet);
		return answer;
	}

}
