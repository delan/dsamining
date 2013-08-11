import java.util.*;
import java.io.*;


public class UnitTestDSALinkedListGenerics
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        DSALinkedList<String> ll;
        Iterator<String> iter;
        String sTestString;

        iNumPassed = 0;
        iNumTests = 0;
        ll = new DSALinkedList<String>();
        Object nodeValue;
                
        // Testing constructor
        try {
            iNumTests++;
            System.out.print("Testing constructor (is empty?): ");
            if (ll.isEmpty() == false)
                throw new IllegalArgumentException("Head must be null.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // Testing linked list with strings.
        System.out.println("");
        System.out.println("Test inserting first and removing first (ie stack behaviour)");
        System.out.println("============================================================");

        try {
            iNumTests++;
            System.out.print("Linked List - insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - peekLast(): ");
            sTestString = ll.peekLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - removeFirst(): ");
            sTestString = ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - peekLast() with empty list: ");
            sTestString = ll.peekLast();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Linked List - isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("");
        System.out.println("Test inserting last and removing first (ie queue behaviour)");
        System.out.println("===========================================================");

        try {
            iNumTests++;
            System.out.print("Linked List - insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - peekFirst(): ");
            sTestString = (String)ll.peekFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Linked List - peekFirst() with empty list: ");
            sTestString = ll.peekFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Linked List - isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Test inserting first");
        System.out.println("====================");

        try {
            iNumTests++;
            System.out.print("Linked List - insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // Testing iterator
        System.out.println("\n");
        System.out.println("Test linked list iterator");
        System.out.println("=========================");

        try {
            iNumTests++;
            System.out.print("Linked List - hasNext(): ");
            iter = ll.iterator();
            // Iterate thru 3 elements - should only have 3!
            iter.next();
            iter.next();
            iter.next();
            if (iter.hasNext() == true)
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}