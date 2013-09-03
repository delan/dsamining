Data Structures and Algorithms 120
DSAMining: final submission

This submission contains not only the required classes, but also two extra
source files that are needed by the above, {OreType,MassUnit}.java. These
classes are enumerations that have been extended so that they can be converted
to and from strings, as well as listed in the case of OreType.

Because the ore type is no longer an int, and the units are no longer strings,
trivial changes have been made to the official unit test harness classes so that
they use the correct types. The changes are explicitly marked in the source
files of the harnesses, and they are included in this submission.

For ease of testing, there is a Makefile included with "test" and "tersetest"
rules to automate the compilation and unit testing process.

This submission includes the following required classes:

* {Ore,OrePile,ShipmentOrder}.java
* {DSAQueueArray,DSAStackArray}.java
* {DSAQueue,DSAStack}.java
* DSALinkedList.java (includes DSAListNode and DSAListIterator private classes)
* DSAShipmentManager.java
* DSAShipments.java

In addition, the following supporting classes are also included:

* {OreType,MassUnit}.java: enumeration types augmented with useful methods
* TerminalHelper.java: helper class for drawing a pretty terminal-based UI

Finally, the following pre-written classes are supplied because minor changes
to these were required due to the use of the OreType and MassUnit classes:

* IShed.java
* Shed{Iron,Nickel}.java
* UnitTestDSALinkedList{,Generics}.java
* UnitTestDSAQueue{Array,Generics}.java
* UnitTestDSAStack{Array,Generics}.java
* UnitTestOre{,Pile}.java
* UnitTestShipmentOrder.java

Also included are the remaining non-class files and directories:

* Makefile: POSIX make recipes for building, running and testing the program
* java.sh: a helper for the Makefile that logs CLI arguments and runs the JVM
* README.final.txt: this README file
* README.submission1.txt: the README file used in the practicals 1-4 submission
* .git*: metadata for the Git DVCS (you can view my development progress!)

The Makefile mentioned above has the following rules:

* compile: the default rule, which builds all of the classes
* run: compiles and runs the program
* clean: deletes all generated files
* test: runs all unit tests, including all output
* tersetest: runs all unit tests, only printing each harness' total score
* submission1: archives a subset of files for the preliminary submission
* submission2: archives all files for the final submission

Delan Azabani
#17065012
