/*
 * File: Domain.java
 * Creator: George Ferguson
 * Created: Sun Mar 25 15:07:31 2012
 * Time-stamp: <Fri Mar 30 14:18:09 EDT 2012 ferguson>
 */

import java.util.Collection;

/**
 * A Domain represents an ordered set of possible Values for a Variable.
 * Technically of course a set needn't be ordered, but it might make
 * life easier to use a List here...
 * <p>
 * We use a ArraySet here since iteration is the main use for Domains
 * in Bayes nets algorithms.
 */
public class Domain extends ArraySet<Object> {

    public Domain() {
	super();
    }

    public Domain(int size) {
	super(size);
    }

    public Domain(Object... elements) {
	this();
	for (Object o : elements) {
	    add(o);
	}
    }

    public Domain(Collection<Object> collection) {
	this();
	for (Object o : collection) {
	    add(o);
	}
    }
}
