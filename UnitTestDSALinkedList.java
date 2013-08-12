import java.io.*;


public class UnitTestDSALinkedList
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        DSALinkedList ll;
        String sTestString;

        iNumPassed = 0;
        iNumTests = 0;
        ll = new DSALinkedList();
        Object nodeValue;
                
        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
            iNumTests++;
            System.out.print("Testing creation of DSALinkedList (isEmpty()): ");
            if (ll.isEmpty() == false)
                throw new IllegalArgumentException("Head must be null.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // Testing linked list with strings.
        System.out.println("");
        System.out.println("Test inserting first and removing first (stack behaviour)");
        System.out.println("==========================================================");

        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing peekLast(): ");
            sTestString = (String)ll.peekLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
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
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("");
        System.out.println("Test inserting last and removing first (queue behaviour)");
        System.out.println("========================================================");

        try {
            iNumTests++;
            System.out.print("Testing insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing peekFirst(): ");
            sTestString = (String)ll.peekFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
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
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing insertFirst()");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(double)iNumPassed/iNumTests*100 + "%)");
    }
}