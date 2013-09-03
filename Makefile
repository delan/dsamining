JC = javac
JFLAGS = -g -Werror
.SUFFIXES: .java .class

TESTCLASSES = $(wildcard UnitTest*.java)
RUNCLASSES = DSAShipments.java
SUBMISSION1FILES = \
	Ore.java \
	OrePile.java \
	ShipmentOrder.java \
	OreType.java \
	MassUnit.java \
	UnitTestOre.java \
	UnitTestOrePile.java \
	UnitTestShipmentOrder.java \
	Makefile \
	java.sh \
	README.submission1.txt

compile:
	$(JC) $(JFLAGS) *.java

# The first two commands in the following recipe are for cygwin.

.java:
	chmod +x java.sh
	dos2unix java.sh > /dev/null 2>&1
	./java.sh $*

test: compile $(TESTCLASSES:.java=)

tersetest: compile
	$(MAKE) test | grep -E 'PASSED|#'

run: compile $(RUNCLASSES:.java=)

clean:
	rm -rfv *.class submission1 submission2

submission1: clean
	mkdir -pv submission1
	cp -v $(SUBMISSION1FILES) submission1
	tar cvzf submission1/submission1.tar.gz submission1/*

submission2: clean
	mkdir -pv submission2
	tar cvzf submission2/submission2.tar.gz * .git* --exclude='submission*'
