// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;
import io.*;


public class UnitTestDSAQueueArray
{	
	public static void main(String args[])
	{
        int ii;
        int iNumPassed;
        int iNumTests;
        // MODIFICATION: DSAQueue -> DSAQueueArray
        DSAQueueArray q;
        Ore ironOre;
        OrePile orePile;

        // Assume Ore works...
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        ironOre = new Ore(OreType.IRON, "t");
        // MODIFICATION: DSAQueue -> DSAQueueArray
        q = new DSAQueueArray();
        iNumTests = 0;
        iNumPassed = 0;

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing (count=0): ");
            if (q.getCount() != 0)
                throw new IllegalArgumentException("Count must equal zero.");
            System.out.println("passed");
            iNumPassed++;
        } catch(Exception e) { System.out.println("FAILED"); }

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
        } catch (Exception e) { System.out.println("FAILED"); }

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
            System.out.println("Testing dequeue(): ");

            for (ii = 1; ii <= 10; ii++)
            {
                orePile = (OrePile)q.dequeue();

                // Verify that queue was OK.
                if ((int)orePile.getWeight() != ii)
                    throw new IllegalStateException("Queue testing failed at index " + ii);

                System.out.println("Front of queue: " + ii);
            }
            iNumPassed++;
            System.out.println("Testing dequeue(): passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            if (q.isEmpty() == false)
                throw new IllegalStateException("Queue is empty.");
            iNumPassed++;
            System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing isFull(): ");
            for (ii = 1; ii <= 100; ii++)
                q.enqueue(new OrePile(ironOre, ii, 50));
            if (q.isFull() == false)
                throw new IllegalStateException("Queue is full.");
            System.out.println("passed");
            iNumPassed++;
        } catch (Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Methods");
        System.out.println("==================================");

        try {
            iNumTests++;
            System.out.print("Testing enqueue() for full queue: ");
            // Enqueue another item onto full queue (see previous test) - should fail.
            q.enqueue(new OrePile(ironOre, 101, 50));             
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing dequeue() for empty queue: ");
            // First dequeue all items from queue.
            for (ii = 1; ii <= 100; ii++)
                orePile = (OrePile)q.dequeue();
            // Now try to dequeue a non-existing item.
            if (q.dequeue() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing peek() for empty queue: ");
            orePile = (OrePile)q.peek();
            System.out.println("FAILED");
        } catch (Exception e) { iNumPassed++; System.out.println("passed"); }

        // Ensure that queue coded to use Object rather than OrePile.
        try {
            iNumTests++;
            System.out.print("Testing Object queue: ");
            // MODIFICATION: DSAQueue -> DSAQueueArray
            q = new DSAQueueArray();
            for (ii = 1; ii <= 10; ii++)
                q.enqueue("string test");
            System.out.println("passed");
            iNumPassed++;
        } catch (Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
