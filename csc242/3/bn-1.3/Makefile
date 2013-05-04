#
# File: Makefile
# Creator: George Ferguson
# Created: Mon Mar 26 10:31:25 2012
# Time-stamp: <Fri Mar 30 15:57:44 EDT 2012 ferguson>
#

VERSION = 1.1

SRCS = \
	ArraySet.java \
	Assignment.java \
	BayesianNetwork.java \
	BIFLexer.java \
	BIFParser.java \
	CPT.java \
	CPTFormatException.java \
	Distribution.java \
	Domain.java \
	EnumerationInferencer.java \
	Printable.java \
	PriorSampler.java \
	RandomVariable.java \
	RejectionSamplingInferencer.java \
	XMLBIFParser.java


CLASSES = $(SRCS:.java=.class)

DISTFILES = README.txt Makefile HISTORY.txt doc $(SRCS) BIFParser.y \
	aima-alarm.xml aima-wet-grass.xml dog-problem.xml \
	alarm.bif insurance.bif diabetes.bif.gz

#
# Rules for Java
#
JAVA = java
JAVAC = javac
JAVADOC = javadoc
JFLAGS = -Xlint

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $<

#
# Rules for Bison
#
#BISON = bison
#BISON = bison --report=state
BISON = /usr/local/bin/bison
.y.java:
	$(BISON) --language=java --warnings=error $<

#
# Building
#
all: $(CLASSES)

BIFLexer.class: BIFParser.class

clean:
	rm -f $(CLASSES)

doc javadoc: $(SRCS) overview.html
	$(JAVADOC) -stylesheetfile stylesheet.css -d doc -overview overview.html $(SRCS)

#
# Testing
#
parser:
	$(JAVA) XMLBIFParser $(ARGV)

dog:
	$(JAVA) XMLBIFParser dog-problem.xml

alarm:
	$(JAVA) XMLBIFParser aima-alarm.xml

bn:
	$(JAVA) BayesianNetwork

cpt:
	$(JAVA) CPT

alarm-bif:
	$(JAVA) BIFParser alarm.bif

insurance-bif:
	$(JAVA) BIFParser insurance.bif

diabetes-bif:
	gzcat diabetes.bif.gz | $(JAVA) BIFParser

#
# Archiving
#
ARCHIVE = bn-$(VERSION)
.DUMMY: $(ARCHIVE)
zip:
	rm -rf $(ARCHIVE) $(ARCHIVE).zip
	mkdir $(ARCHIVE)
	cp -pr $(DISTFILES) $(ARCHIVE)
	zip -r $(ARCHIVE).zip $(ARCHIVE)
	rm -rf $(ARCHIVE)

distclean: clean
	rm -rf doc $(ARCHIVE)
