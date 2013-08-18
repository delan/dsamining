Data Structures and Algorithms 120
DSAMining: preliminary submission

This submission contains not only {Ore,OrePile,ShipmentOrder}.java, but also two
extra source files that are needed by the above, {OreType,MassUnit}.java. These
classes are enumerations that have been extended so that they can be converted
to and from strings, as well as listed in the case of OreType.

Because the ore type is no longer an int, and the units are no longer strings,
trivial changes have been made to the official unit test harness classes so that
they use the correct types. The changes are explicitly marked in the source
files of the harnesses, and they are included in this submission.

For ease of testing, there is a Makefile included with "test" and "tersetest"
rules to automate the compilation and unit testing process. Barring any future
changes, this is essentially the same Makefile that will be used in the final
submission, but the "run" rule won't work because DSAShipments.java is not
included with this submission.

Delan Azabani
#17065012
