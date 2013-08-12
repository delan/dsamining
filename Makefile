JC = javac
JFLAGS = -g -Werror
.SUFFIXES: .java .class

TESTCLASSES = $(wildcard UnitTest*.java)
RUNCLASSES = DSAShipments.java

.java:
	# The following two commands are for cygwin.
	dos2unix java.sh
	chmod +x java.sh
	./java.sh $*

all:
	$(JC) $(JFLAGS) *.java

test: all $(TESTCLASSES:.java=)

tersetest: all
	$(MAKE) test | egrep 'PASSED|#'

run: all $(RUNCLASSES:.java=)

clean:
	rm -fv *.class
