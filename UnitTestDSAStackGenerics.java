// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;


public class UnitTestDSAStackGenerics
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        DSAStack<OrePile> s;
        int ii;
        boolean bIsEmpty;
        Ore nickelOre;
        OrePile orePile;

        iNumPassed = 0;
        iNumTests = 0;
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        nickelOre = new Ore(OreType.NICKEL, "t");
        s = new DSAStack<OrePile>();

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing pop(): ");
            if (s.pop() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Methods");
        System.out.println("===================================");

        try {
            iNumTests++;
            System.out.println("Testing push(): ");
            for (ii = 1; ii <= 10; ii++)
            {
                // Create stack of nickel ore piles.
                s.push(new OrePile(nickelOre, ii, 50));
                System.out.println("Top of stack: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing push(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing top(): ");
            orePile = s.top();
            if ( (orePile.getWeight() != 10)  && (orePile.getGrade() != 50) )
                throw new IllegalStateException("Not looking at top ore pile.");
            iNumPassed++;
            System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.println("Testing pop(): ");
            for (ii = 10; ii >= 1; ii--)
            {
                orePile = s.pop();

                // Verify that stack was OK.
                if ((int)orePile.getWeight() != ii)
                    throw new IllegalStateException("Stack testing failed at index " + ii);

                System.out.println("Top of stack: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing pop(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            if (s.isEmpty() == false)
                throw new IllegalStateException("Stack is empty.");
            System.out.println("passed");
            iNumPassed++;
        } catch (Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Methods");
        System.out.println("==================================");

        try {
            iNumTests++;
            System.out.print("Testing pop() for empty stack: ");
            if (s.pop() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing top() for empty stack: ");
            orePile = s.top();
            System.out.println("FAILED");
        } catch (Exception e) { iNumPassed++; System.out.println("passed"); }
        
        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
