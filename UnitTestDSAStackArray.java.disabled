// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;
import io.*;


public class UnitTestDSAStackArray
{	
	public static void main(String args[])
	{
        int ii;
        int iNumPassed;
        int iNumTests;
        DSAStack s;
        Ore nickelOre;
        OrePile orePile;

        // Assume Ore works...
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        nickelOre = new Ore(OreType.NICKEL, "t");
        s = new DSAStack();
        iNumTests = 0;
        iNumPassed = 0;

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing (count=0): ");
            if (s.getCount() != 0)
                throw new IllegalArgumentException("Count must equal zero.");
            System.out.println("passed");
            iNumPassed++;
        } catch(Exception e) { System.out.println("FAILED"); }

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
        } catch (Exception e) { System.out.println("FAILED"); }
        
        try {
            iNumTests++;
            System.out.print("Testing top(): ");
            orePile = (OrePile)s.top();
            if (orePile.getWeight() != 10)
                throw new IllegalStateException("Not looking at top ore pile.");
            iNumPassed++;
            System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }
        
        try {
            iNumTests++;
            System.out.println("Testing pop(): ");

            for (ii = 10; ii >= 1; ii--)
            {
                orePile = (OrePile)s.pop();

                // Verify that stack was OK.
                if ((int)orePile.getWeight() != ii)
                    throw new IllegalStateException("Stack testing failed at index " + ii);

                System.out.println("Top of stack: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing pop(): passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            if (s.isEmpty() == false)
                throw new IllegalStateException("Stack is empty.");
            System.out.println("passed");
            iNumPassed++;
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing isFull(): ");
            for (ii = 1; ii <= 100; ii++)
                s.push(new OrePile(nickelOre, ii, 50));
            if (s.isFull() == false)
                throw new IllegalStateException("Stack is full.");
            iNumPassed++;
            System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Methods");
        System.out.println("==================================");

        try {
            iNumTests++;
            System.out.print("Testing push() for full stack: ");
            // Push another item onto full stack (see previous test) - should fail.
            s.push(new OrePile(nickelOre, 101, 50));             
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing pop() for empty stack: ");
            // First pop all items from stack.
            for (ii = 1; ii <= 100; ii++)
                orePile = (OrePile)s.pop();
            // Now try to pop a non-existing item.
            if (s.pop() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing top() for empty stack: ");
            orePile = (OrePile)s.top();
            System.out.println("FAILED");
        } catch (Exception e) { iNumPassed++; System.out.println("passed"); }

        // Ensure that stack coded to use Object rather than OrePile.
        try {
            iNumTests++;
            System.out.print("Testing Object stack: ");
            s = new DSAStack();
            for (ii = 1; ii <= 10; ii++)
                s.push("string test");
            iNumPassed++;
			System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
