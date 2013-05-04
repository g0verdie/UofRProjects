/*
 * File: EnumerationInferencer-template.java
 * Creator: George Ferguson
 * Created: Mon Mar 26 13:08:05 2012
 * Time-stamp: <Fri Apr  6 11:41:59 EDT 2012 ferguson>
 *
 * WARNING: This code will not compile without changes! See README.txt.
 */

import java.util.*;

/**
 * Implements the depth-first <q>inference by enumeration</q> algorithm for
 * exact inference in Bayesian networks described in AIMA Figure 14.9.
 */
public class EnumerationInferencer {

    public EnumerationInferencer() {
    }

    /**
     * Return the posterior Distribution P(X|e) for RandomVariable X
     * given Assignment e (evidence variables and their values) using
     * the joint probability distribution encoded in the given
     * BayesianNetwork.
     */
    public Distribution enumerateAsk(RandomVariable X, Assignment e, BayesianNetwork bn) {
	// ``Q(X) <- a distribution over X, initially empty
	Distribution Q = new Distribution(X);
	List<RandomVariable> vars = getVariablesInOrder(X, e, bn);
	// ``for each value x_i of X do''
	for (Object xi : X.getDomain()) {
	    // ``e_x_i is e extended with X=x_i''
	    e.put(X, xi);
	    // ``Q(x_i) <- ENUMERATE-ALL(bn.vars, e_x_i)''
	    Q.put(xi, enumerateAll(vars, e, bn));
	    e.remove(X);
	}
	// ``return NORMALIZE(Q(X))''
	...;
	return Q;
    }

    /**
     * Returns the RandomVariables from the given BayesianNetwork in the
     * following order: first the query variable X, then the hidden variables
     * Y, then the evidence variables E. This order is crucial to the
     * enumerateAll method, but not really mentioned in the textbook.
     */
    public List<RandomVariable> getVariablesInOrder(RandomVariable X, Assignment e, BayesianNetwork bn) {
	List<RandomVariable> result = new ArrayList<RandomVariable>(bn.size());
	Set<RandomVariable> evars = e.variableSet();
	// First query var X
	result.add(X);
	// Then hidden vars Y (i.e., vars other than X not in e)
	for (RandomVariable Y : bn.getVariableList()) {
	    if (Y != X && !evars.contains(Y)) {
		result.add(Y);
	    }
	}
	// Then evidence vars E (i.e., keys of e)
	result.addAll(evars);
	return result;
    }

    /**
     * Recursive (depth-first) step in the enumeration algorithm for
     * answering queries on BayesianNetworks.
     */
    protected double enumerateAll(List<RandomVariable> vars, Assignment e, BayesianNetwork bn) {
	// ``if vars is empty, return 1.0''
	...;
	// ``Y <- FIRST(vars)''
	RandomVariable Y = vars.get(0);
	// ``REST(vars)'' (note subList view incantation)
	List<RandomVariable> rest = vars.subList(1, vars.size());
	// ``if Y has value y in e''
	Object y = ...;
	if (y != null) {
	    return bn.something_about_conditional_probability * something_recursive;
	} else {
	    double sum = 0.0;
	    for (Object yy : Y.getDomain()) {
		// ``e_y is e extended with Y=y''
		e.put(Y, yy);
		sum += bn.something_about_conditional_probability * something_recursive;
		e.remove(Y);
	    }
	    return sum;
	}
    }
    
    // Testing

    protected void trace(String msg) {
	System.err.println(msg);
    }

    /**
     * Test driver for EnumerationInferencer: Loads aima-alarm.xml,
     * asks for the distribution P(B|j,m), and prints the result together
     * with the expected result given in the textbook.
     */
    public static void main(String[] argv) throws javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException {
	XMLBIFParser parser = new XMLBIFParser();
	BayesianNetwork bn = parser.readNetworkFromFile("aima-alarm.xml");
	RandomVariable B = bn.getVariableByName("B");
	RandomVariable J = bn.getVariableByName("J");
	RandomVariable M = bn.getVariableByName("M");
	Assignment e = new Assignment();
	e.put(J, "true");
	e.put(M, "true");
	EnumerationInferencer engine = new EnumerationInferencer();
	Distribution dist = engine.ask(B, e, bn);
	System.out.println("B=true  should be 0.284: " + dist.get("true"));
	System.out.println("B=false should be 0.716: " + dist.get("false"));
    }

}
