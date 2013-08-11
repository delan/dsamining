// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;
import io.*;


public class UnitTestOre
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        Ore ore;
        // MODIFICATION: data type of iOreType from int to OreType
        OreType iOreType;
        String sUnits;

        iNumPassed = 0;
        iNumTests = 0;

        // Test with normal conditions (shouldn't throw exceptions)

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {   
            iNumTests++;
            System.out.print("Testing creation of Ore: ");
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            ore = new Ore(OreType.IRON, "t");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inOreType: ");
            iOreType = ore.getOreType();
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            if (iOreType != OreType.IRON)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inUnits: ");
            // MODIFICATION: append .toString() to call chain
            sUnits = ore.getUnits().toString();
            if (sUnits.equals("t") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            System.out.println("\n");
            System.out.println("Testing Normal Conditions - Setters and Getters");
            System.out.println("===============================================");

            iNumTests++;
            System.out.print("Testing setUnits('g'): ");
            ore.setUnits("g");	
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getUnits(): ");
            // MODIFICATION: append .toString() to call chain
            sUnits = ore.getUnits().toString();
            if (sUnits.equals("g") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

        } catch(Exception e) { System.out.println("FAILED"); }

        // Tests with error conditions (SHOULD throw exceptions)
        // Testing constructor's parameters of ore type and units.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Constructor");
        System.out.println("======================================");

        // Testing ORE TYPE
        // This test would also be redundant if they choose to use enums instead of constants.

        // MODIFICATION: removed tests because they are irrelevant with enum.
        // try {
        //     iNumTests++;
        //     System.out.print("Testing constructor (inOreType=-1): ");
        //     ore = new Ore(-1, "t");
        //     System.out.println("FAILED");
        // } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // try {
        //     iNumTests++;
        //     System.out.print("Testing constructor (inOreType=33): ");
        //     ore = new Ore(33, "t");
        //     System.out.println("FAILED");
        // } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing UNITS
        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnits='a'): ");
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            ore = new Ore(OreType.NICKEL, "a");	
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnits=''): ");
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            ore = new Ore(OreType.NICKEL, "");	
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing setters and getters - note we don't have an Ore Type setter
        // so we can't test whether the getter is accurate.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Setters");
        System.out.println("==================================");

        // Testing UNITS
        try {
            iNumTests++;
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            ore = new Ore(OreType.NICKEL, "kg");
            System.out.print("Testing setUnits('') (units=''): ");
            ore.setUnits("");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
            ore = new Ore(OreType.NICKEL, "kg");
            System.out.print("Testing setUnits('abc') (units not equal to 't', 'kg', 'g'): ");
            ore.setUnits("abc");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
