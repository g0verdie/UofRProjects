/*
 * File: PriorSampler-template.java
 * Creator: George Ferguson
 * Created: Wed Mar 28 13:37:27 2012
 * Time-stamp: <Wed Mar 28 15:12:45 EDT 2012 ferguson>
 *
 * WARNING: This code will not compile without changes! See README.txt.
 */

import java.util.*;

/**
 * Class implementing the Prior-Sample algorithm from AIMA Section 14.5.1.
 */
public class PriorSampler {

    /**
     * Construct and return a new PriorSampler for the given
     * BayesianNetwork. Note that changes to the variables in the
     * network will not be seen by the sampler (so don't do that).
     */
    public PriorSampler(BayesianNetwork bn) {
	this.bn = bn;
	this.variables = bn.something_about_variables();
    }

    /**
     * The BayesianNetwork being sampled by this PriorSampler.
     */
    protected BayesianNetwork bn;

    /**
     * The topologically-sorted list of RandomVariables from the
     * BayesianNetwork being sampled by this PriorSampler.
     */
    protected List<RandomVariable> variables;

    /**
     * The random number generator used by this PriorSampler.
     * Pass a seed to the constructor to make it repeatable.
     */
    protected Random random = new Random();

    /**
     * Returns an Assignment (<q>event</q>) sampled from the prior
     * specified by the given BayesianNetwork.
     * <p>
     * Note that the pseudo-code in Figure 14.13 does not mention that
     * the variables must be sorted in topological order (although it is
     * mentioned in the text and was mentioned in class). We looked
     * after that in our constructor, since it's relatively expensive to
     * compute and you want to generate samples quickly.
     */
    public Assignment getSample() {
	Assignment x = new Assignment();
	for (RandomVariable Xi : variables) {
	    x.put(Xi, randomSampleForVariable(Xi, x));
	}
	return x;
    }

    /**
     * Return a random sample from P(Xi|Parents(Xi)), where the value is
     * sampled according to the conditional distribution given the values
     * already sampled for the variable's parent (in the given Assignment).
     */
    protected Object randomSampleForVariable(RandomVariable Xi, Assignment x) {
	double sum = 0.0;
	double p = random.nextDouble();
	for (Object v : Xi.something()) {
	    x.put(Xi, v);
	    sum += bn.something_about_a_conditional_probability();
	    x.remove(Xi);
	    if (p < sum) {
		return v;
	    }
	}
	throw new RuntimeException("couldn't find value for sample!");
    }

    /**
     * Test driver for PriorSampler.
     * Reads "aima-wet-grass.xml" and then generates samples (events).
     */
    public static void main(String[] argv) throws javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException {
	XMLBIFParser parser = new XMLBIFParser();
	BayesianNetwork bn = parser.readNetworkFromFile("aima-wet-grass.xml");
	PriorSampler sampler = new PriorSampler(bn);
	for (int i=0; i < 10; i++) {
	    Assignment x = sampler.getSample();
	    System.out.println(x);
	}
    }

}
