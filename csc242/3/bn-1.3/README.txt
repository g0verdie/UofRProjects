
File: README.txt
Creator: George Ferguson
Created: Tue Mar 27 21:03:13 2012
Time-stamp: <Wed Mar 20 15:55:57 EDT 2013 ferguson>

Code for CSC242 Intro to AI, Project 3: Uncertainty

You have the following options with respect to this code for your
project:

1. You can completely ignore it. In which case, you can also stop
   reading. ;-) There is nothing here that isn't in the project
   decscription itself.

2. Perhaps you're thinking about your software design, and you'd like
   to compare your thinking to mine without actually using my code. No
   problem. Read the javadoc by starting at "doc/index.html". I
   include a class overview ("overview.html") to get you started. You
   can look at the classes and their APIs, which might be all you
   want.

3. Perhaps programming isn't your greatest strength, and you don't
   think you can get anything working in two weeks. (You did start
   reading this stuff when I gave it out, right?) So you want to use
   my code for the basics, then add your own parts to actually address
   the requirements of the project. No problem. Dig into the
   sources. There are lots of comments (most of which are also
   javadoc, but sometimes they're clearer in the source code
   context). You would want to acknowledge use of my code in your
   writeup. And obviously this counts as somewhat less work for you.

4. Suppose you've read and thought about and tried to code up the
   necessary algorithms, but you're having trouble translating the
   pseudo-code from the textbook into actual code. Well, that can be
   tricky. But if you're ready to give up, check out the
   "*-template.java" files that I've provided. They give you some of
   the structure for some of the core algorithms. You would still have
   to understand things well enough to fill in the blanks. These files
   won't compile as is. Copy them to a new ".java" file (like maybe
   strip out the "-template" part), fill in the blanks, and off you
   go. Again, you would want to acknowledge this in your writeup, and
   again it would count as somewhat less work that you had to do.

If you are still stuck even after looking at all my code, or if you're
stuck and don't want to look at my code, get in touch with the TAs
*right away*.


Highlights

See javadoc class overview for more details.

- Run: java CPT
  - to see an example of a conditional probability table printed to
    stdout.

- Run: java BayesianNetwork
  - to print a simple Bayesian network built by hand (in code) to
    stdout.

- Run: java XMLBIFParser filename
  - to parse the given filename and print the resulting BayesianNetwork
    to stdout. Try "aima-alarm.xml" or "dog-problem.xml" as inputs.

- Run: java BIFParser filename
  - Same but for .bif files (the pre-XML "standard").


Example networks

- aima-alarm.xml: AIMA Figure 14.2

- aima-wet-grass.xml: AIMA Figure 14.12

- dog-problem.xml: Charniak's dog problem, from the XMLBIF spec

- Three networks from the Nir Friedman Lab respository:
  http://www.cs.huji.ac.il/site//labs/compbio/Repository/networks.html
  - alarm.bif (37 variables, 2-4 values each)
  - insurance.bif (27 variables, 2-5 values each)
  - diabetes.bif.gz (413 variables, up to 21 values each)
    - edited to make identifiers start with a letter, per BIF spec
    - this one's big!

