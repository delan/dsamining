// The following test harness has been trivially modified to suit the class and
// method design of the production code. Changes marked with "MODIFICATION".

import java.io.*;
import io.*;

public class UnitTestShipmentOrder
{
	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        ShipmentOrder shipOrder;
        Ore ore;
        OrePile orePile;
        int iOrderID;
        boolean bIsPending;
        String sCustName;
        String sShipDest;
        double dOrderedMetalWt;
        double dUnitPrice;
        double dAveGrade;
        double dShipValue;

        iNumPassed = 0;
        iNumTests = 0;
        iOrderID = 0;
        bIsPending = false;
        sCustName = "";
        sShipDest = "";
        dOrderedMetalWt = 0.0;
        dUnitPrice = 0.0;
        dAveGrade = 0.0;
        dShipValue = 0.0;

        // Assuming Ore and OrePile work...
        // MODIFICATION: constant from Ore.ORETYPE_(...) to OreType.(...).
        ore = new Ore(OreType.IRON, "t");
        orePile = new OrePile(ore, 500, 50);
        
        // Test with normal conditions (shouldn't throw exceptions)

        System.out.println("\n");
        System.out.println("Testing Normal Conditions");
        System.out.println("=========================");

        try {
            iNumTests++;
            System.out.print("Testing creation of ShipmentOrder: ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 400, 250);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing OrderID: ");
            iOrderID = shipOrder.getOrderID();
            if (iOrderID != 1)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing isPending: ");
            bIsPending = shipOrder.getIsPending();
            if (bIsPending == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inCustName: ");
            sCustName = shipOrder.getCustomerName();
            if (sCustName.equals("John Smith") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inShipDest: ");
            sShipDest = shipOrder.getShippingDest();
            if (sShipDest.equals("Exmouth") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inOre: ");
            if (shipOrder.getOre() == null)   // Would need .equals() to test properly, but not req'd func
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inOrderedMetalWt: ");
            dOrderedMetalWt = shipOrder.getOrderedMetalWt();
            if (dOrderedMetalWt != 400)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inUnitPrice: ");
            dUnitPrice = shipOrder.getUnitPrice();
            if (dUnitPrice != 250)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            System.out.println("\n");
            System.out.println("Testing Normal Conditions - Setters and Getters");
            System.out.println("===============================================");

            iNumTests++;
            System.out.print("Testing setShipDest(\"Broome\"): ");
            shipOrder.setShippingDest("Broome");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getShipDest(): ");
            sShipDest = shipOrder.getShippingDest();
            if (sShipDest.equals("Broome") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing setOrderedMetalWt(350): ");
            shipOrder.setOrderedMetalWt(350);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getOrderedMetalWt(): ");
            dOrderedMetalWt = shipOrder.getOrderedMetalWt();
            if (dOrderedMetalWt != 350)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing setUnitPrice(125): ");
            shipOrder.setUnitPrice(125);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getUnitPrice(): ");
            dUnitPrice = shipOrder.getUnitPrice();
            if (dUnitPrice != 125)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

        } catch(Exception e) { System.out.println("FAILED"); }

        // Testing getShippedOreWt() - should throw an exception at this point, so catch it = passed
        try {
            iNumTests++;
            System.out.print("Testing getShippedOreWt(): ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 400, 250);
            shipOrder.getShippedOreWt();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }


        // Tests with error conditions (SHOULD throw exceptions)
        // Testing constructor's parameters of customer name, 
        // shipping destination, ordered metal weight, unit price.
        // Assume ore is OK.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Constructor");
        System.out.println("======================================");

        //Testing CUSTOMER NAME
        try {
            iNumTests++;
            System.out.print("Testing constructor (inCustomerName=\"\"): ");
            shipOrder = new ShipmentOrder("", "Exmouth", ore, 200, 250);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        //Testing SHIPPING DESTINATION
        try {
            iNumTests++;
            System.out.print("Testing constructor (inShippingDest=\"\"): ");
            shipOrder = new ShipmentOrder("John Smith", "", ore, 200, 250);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        // Testing ORDERED METAL WEIGHT
        try {
            iNumTests++;
            System.out.print("Testing constructor (inOrderedMetalWt=0): ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 0, 250);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inOrderedMetalWt<0): ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, -1, 250);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing UNIT PRICE
        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnitPrice=0): ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 200, 0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnitPrice<0): ");
            shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 200, -1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing setters and getters

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Setters");
        System.out.println("==================================");

        shipOrder = new ShipmentOrder("John Smith", "Exmouth", ore, 200, 250);

        // Testing CUSTOMER NAME
        try {
            iNumTests++;
            System.out.print("Testing setCustomerName(\"\"): ");
            shipOrder.setCustomerName("");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing SHIPPING DESTINATION
        try {
            iNumTests++;
            System.out.print("Testing setShippingDest(\"\"): ");
            shipOrder.setShippingDest("");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        // Testing ORDERED METAL WEIGHT
        try {
            iNumTests++;
            System.out.print("Testing setOrderedMetalWt(0): ");
            shipOrder.setOrderedMetalWt(0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing setOrderedMetalWt(-1): ");
            shipOrder.setOrderedMetalWt(-1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing UNIT PRICE
        try {
            iNumTests++;
            System.out.print("Testing setUnitPrice(0): ");
            shipOrder.setUnitPrice(0);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
                
        try {
            iNumTests++;
            System.out.print("Testing setUnitPrice(-1): ");
            shipOrder.setUnitPrice(-1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing SHIPPED ORE WEIGHT
        try {
            iNumTests++;
            System.out.print("Testing setShippedOreWt(-1): ");
            shipOrder.setShippedOreWt(-1);
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing other methods.

        // Have valid setShippedOreWt(), test getShippedOreWt() to work without exceptions
        // Does getIsPending then start returning false. If not, then FAILED
        try {
            iNumTests++;
            System.out.print("Testing setShippedOreWt(300): ");
            shipOrder.setShippedOreWt(300);
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getShippedOreWt(): ");
            if (shipOrder.getShippedOreWt() != 300)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getIsPending(): ");
            if (shipOrder.getIsPending() == true)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // Test average grade and shipment value calculations with normal conditions 
        // (shouldn't throw exceptions).
        try {
            iNumTests++;
            System.out.print("Testing average grade calculation: ");
            shipOrder.setShippedOreWt(400);
            dAveGrade = shipOrder.calcAverageGrade();
            if (dAveGrade == 50)
            {
                iNumPassed++;
                System.out.println("passed");
            }
            else
                throw new IllegalArgumentException("FAILED");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
            iNumTests++;
            System.out.print("Testing shipment value calculation: ");
            dShipValue = shipOrder.calcShipmentValue();
            if (dShipValue == 50000)
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
