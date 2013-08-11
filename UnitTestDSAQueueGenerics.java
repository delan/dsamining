// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;


public class UnitTestDSAQueueGenerics
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        DSAQueue<OrePile> q;
        int ii;
        boolean bIsEmpty;
        Ore ironOre;
        OrePile orePile;

        iNumPassed = 0;
        iNumTests = 0;
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        ironOre = new Ore(OreType.IRON, "t");
        q = new DSAQueue<OrePile>();

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing dequeue(): ");
            if (q.dequeue() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Methods");
        System.out.println("===================================");

        try {
            iNumTests++;
            System.out.println("Testing enqueue(): ");
            for (ii = 1; ii <= 10; ii++)
            {
                // Create queue of iron ore piles.
                q.enqueue(new OrePile(ironOre, ii, 50));
                System.out.println("Front of queue: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing enqueue(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing peek(): ");
            orePile = (OrePile)q.peek();
            if (orePile.getWeight() != 1)
                throw new IllegalStateException("Not looking at first ore pile.");
            iNumPassed++;
            System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.println("Testing dequeue():");
            for (ii = 1; ii <= 10; ii++)
            {
                orePile = q.dequeue();

                if ((int)orePile.getWeight() != ii)
                    throw new IllegalStateException("Queue testing failed at index " + ii);

                System.out.println("Front of queue: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing dequeue(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Queue isEmpty(): ");
            bIsEmpty = q.isEmpty();
            if (bIsEmpty == false)
                throw new IllegalStateException("Queue is empty.");
            iNumPassed++;
            System.out.println("passed");
            } catch(Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Methods");
        System.out.println("==================================");

        try {
            iNumTests++;
            System.out.print("Testing dequeue() for empty queue: ");
            if (q.dequeue() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing peek() for empty stack: ");
            orePile = q.peek();
            System.out.println("FAILED");
        } catch (Exception e) { iNumPassed++; System.out.println("passed"); }
        
        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
