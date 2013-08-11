JC = javac
JVM = ./java.sh
JFLAGS = -g -Werror
.SUFFIXES: .java .class

CLASSES = $(wildcard *.java)
TESTCLASSES = $(wildcard UnitTest*.java)
RUNCLASSES = DSAShipmentManager.java

.java.class:
	$(JC) $(JFLAGS) $*.java

.java:
	$(JVM) $*

all: $(CLASSES:.java=.class)

test: all $(TESTCLASSES:.java=)

tersetest: all
	$(MAKE) test | egrep 'PASSED|#'

run: all $(RUNCLASSES:.java=)

clean:
	rm -fv *.class
