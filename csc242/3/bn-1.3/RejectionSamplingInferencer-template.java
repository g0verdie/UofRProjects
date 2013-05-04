/*
 * File: RejectionSamplingInferencer-template.java
 * Creator: George Ferguson
 * Created: Wed Mar 28 14:34:36 2012
 * Time-stamp: <Wed Mar 28 15:14:37 EDT 2012 ferguson>
 *
 * WARNING: This code will not compile without changes! See README.txt.
 */

/**
 * Implements the rejection sampling algorithm for approximate
 * inference in Bayesian networks described in AIMA Figure 14.14.
 */
public class RejectionSamplingInferencer {

    public RejectionSamplingInferencer() {
    }

    /**
     * Return an estimate of the Distribution P(X|e) from the given
     * BayesianNetwork, where X is the query RandomVariable,
     * e is an Assignment representing the evidence variables and their values,
     * and n is the total number of samples to be generated.
     * <p>
     * The big decision here is how to store the counts. If we use a
     * Distribution, then we will be constantly creating Doubles to store
     * in it (since it's a LinkedHashMap). Also the floating point math could
     * in principle be less exact. If we use an array of ints,
     * then we need to convert values of X to indexes, and eventually
     * convert to a Distribution anyway. So I'll just use a Distribution
     * for now.
     */
    public Distribution ask(RandomVariable X, Assignment e, BayesianNetwork bn, int n) {
	PriorSampler sampler = new PriorSampler(bn);
	// ``N, a vector of counts for each value of X, initially zero''
	Distribution N = new Distribution(X);
	// ``for j=1 to n do''
	for (int j=0; j < n; j++) {
	    // ``x <- PRIOR-SAMPLE(bn)''
	    Assignment xvec = sampler.something();
	    // ``if x is consistent with e then''
	    if (something_about_consistency(xvec, e)) {
		// ``N[x] <- N[x]+1 where x is the value of X in x''
		Object x = xvec.get(X);
		Double d = N.get(x);
		if (d == null) {
		    N.put(x, 1);
		} else {
		    N.put(x, d.intValue() + 1);		    
		}
	    }
	}
	// ``return NORMALIZE(N)''
	...;
	return N;
    }

    /**
     * Returns true if Assignment x is consistent with Assignment e, in
     * the sense that every variable in e has the same value in x.
     */
    protected boolean isConsistent(Assignment x, Assignment e) {
	boolean consistent = true;
	for (RandomVariable var : e.variableSet()) {
	    if (!x.get(var).equals(e.get(var))) {
		consistent = false;
		break;
	    }
	}
	return consistent;
    }

    /**
     * Test driver for RejectionSamplingInferencer: Loads aima-wet-grass.xml,
     * asks for the distribution P(Rain|Sprinkler=true), and prints the result
     * together with the expected result given in the textbook. Number of
     * samples given on cmd-line, or default=100.
     */
    public static void main(String[] argv) throws javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException {
	int N = 100;
	if (argv.length > 0) {
	    try {
		N = Integer.parseInt(argv[0]);
	    } catch (NumberFormatException ex) {
		System.err.println(ex);
		System.exit(-1);
	    }
	}
	XMLBIFParser parser = new XMLBIFParser();
	BayesianNetwork bn = parser.readNetworkFromFile("aima-wet-grass.xml");
	RandomVariable R = bn.getVariableByName("R");
	RandomVariable S = bn.getVariableByName("S");
	Assignment e = new Assignment();
	e.put(S, "true");
	RejectionSamplingInferencer engine = new RejectionSamplingInferencer();
	Distribution dist = engine.ask(R, e, bn, N);
	System.out.println("R=true  should be 0.3: " + dist.get("true"));
	System.out.println("R=false should be 0.7: " + dist.get("false"));
    }

}
