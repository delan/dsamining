// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;
import io.*;


public class UnitTestOrePile
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        OrePile orePile;
        Ore ore;
        Ore inOre;
        double dWeight;
        double dGrade;
        double dMetalWeight;

        iNumPassed = 0;
        iNumTests = 0;
        dWeight = 0.0;
        dGrade = 0.0;
        dMetalWeight = 0.0;

        // Assuming Ore works...
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        ore = new Ore(OreType.IRON, "t");
        
        // Test with normal conditions (shouldn't throw exceptions)

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing creation of OrePile: ");
            orePile = new OrePile(ore, 200, 50);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inWeight: ");
            dWeight = orePile.getWeight();
            if (dWeight != 200)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inGrade: ");
            dGrade = orePile.getGrade();
            if (dGrade != 50)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inOre: ");
            if (orePile.getOre() == null)   // Would need .equals() to test properly, but not req'd func
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            System.out.println("\n");
            System.out.println("Testing Normal Conditions - Setters and Getters");
            System.out.println("===============================================");

            iNumTests++;
            System.out.print("Testing setWeight(150): ");
            orePile.setWeight(150);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getWeight(): ");
            dWeight = orePile.getWeight();
            if (dWeight != 150)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing setGrade(65): ");
            orePile.setGrade(65);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getGrade(): ");
            dGrade = orePile.getGrade();
            if (dGrade != 65)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

        } catch(Exception e) { System.out.println("FAILED"); }


        // Tests with error conditions (SHOULD throw exceptions)
        // Testing constructor's parameters of weight and grade.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Constructor");
        System.out.println("======================================");

        //Testing WEIGHT
        try {
            iNumTests++;
            System.out.print("Testing constructor (inWeight=0): ");
            orePile = new OrePile(ore, 0, 50);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inWeight<0): ");
            orePile = new OrePile(ore, -1, 50);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        // Testing GRADE
        try {
            iNumTests++;
            System.out.print("Testing constructor (inGrade=0): ");
            orePile = new OrePile(ore, 200, 0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inGrade>100): ");
            orePile = new OrePile(ore, 200, 101);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
                
        try {
            iNumTests++;
            System.out.print("Testing constructor (inGrade<0): ");
            orePile = new OrePile(ore, 200, -1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }


        // Testing setters and getters

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Setters");
        System.out.println("==================================");

        orePile = new OrePile(ore, 200, 45);

        // Testing WEIGHT
        try {
            iNumTests++;      
            System.out.print("Testing setWeight(0) (weight=0): ");
            orePile.setWeight(0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing setWeight(-5) (weight<0): ");
            orePile.setWeight(-5);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        // Testing GRADE
        try {
            iNumTests++;
            System.out.print("Testing setGrade(0) (grade=0): ");
            orePile.setGrade(0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing setGrade(101) (grade>100): ");
            orePile.setGrade(101);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
                
        try {
            iNumTests++;
            System.out.print("Testing setGrade(-1) (grade<0): ");
            orePile.setGrade(-1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }


        // Test metal weight calculation with normal conditions 
        // (shouldn't throw exceptions)

        System.out.println("\n");
        System.out.println("Testing Other Methods");
        System.out.println("=====================");

        try {
            iNumTests++;
            System.out.print("Testing calcMetalWeight(): ");
            orePile = new OrePile(ore, 200, 50);
            dMetalWeight = orePile.calcMetalWeight();
            if (dMetalWeight == 100)
            {
                iNumPassed++;
                System.out.println("passed");
            }
            else
                throw new IllegalArgumentException("FAILED");
        } catch(Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
