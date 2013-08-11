JC = javac
JFLAGS = -g -Werror
.SUFFIXES: .java .class

CLASSES = $(wildcard *.java)
TESTCLASSES = $(wildcard UnitTest*.java)
RUNCLASSES = DSAShipments.java

.java.class:
	$(JC) $(JFLAGS) $*.java

.java:
	# The following two commands are for cygwin.
	dos2unix java.sh
	chmod +x java.sh
	./java.sh $*

all: $(CLASSES:.java=.class)

test: all $(TESTCLASSES:.java=)

tersetest: all
	$(MAKE) test | egrep 'PASSED|#'

run: all $(RUNCLASSES:.java=)

clean:
	rm -fv *.class
