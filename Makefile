JC = javac
JFLAGS = -g -Werror
.SUFFIXES: .java .class

TESTCLASSES = $(wildcard UnitTest*.java)
RUNCLASSES = DSAShipments.java

# The first command in the following recipe is for cygwin.

.java:
	dos2unix java.sh > /dev/null 2>&1
	./java.sh $*

all:
	$(JC) $(JFLAGS) *.java

test: all $(TESTCLASSES:.java=)

tersetest: all
	$(MAKE) test | egrep 'PASSED|#'

run: all $(RUNCLASSES:.java=)

clean:
	rm -fv *.class
