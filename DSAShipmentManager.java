import io.*;
public class DSAShipmentManager {
	private static final MassUnit defaultUnits = MassUnit.TON_LONG;
	private DSALinkedList<IShed> shedList;
	private DSAQueue<ShipmentOrder> orderQueue;
	private TerminalHelper term;
	public DSAShipmentManager() {
		shedList = new DSALinkedList<IShed>();
		orderQueue = new DSAQueue<ShipmentOrder>();
		term = new TerminalHelper(
			"Data Structures and Algorithms 120: " +
			"Gina Rinehart Edition",
			"Delan Azabani (#17065012)"
		);
	}
	public void run() {
		while (true) {
			int choice = this.uiDisplayMainMenu();
			this.uiMainMenuChoice(choice);
		}
	}
	private int uiDisplayMainMenu() {
		term.newScreen(
			"Main menu",
			"[Enter] Choose option",
			"(1) Add new shed\n" +
			"(2) Ore delivery data entry\n" +
			"(3) View shed contents\n" +
			"(4) Customer order data entry\n" +
			"(5) View pending customer orders\n" +
			"(6) Process next customer order\n" +
			"(0) Quit\n\n" +
			term.getFieldString("Enter a choice")
		);
		return ConsoleInput.readInt();
	}
	private void uiMainMenuChoice(int choice) {
		switch (choice) {
		case 0:
			term.cleanup();
			System.exit(0);
			break;
		case 1:
			uiAddShed();
			break;
		case 2:
			uiOreDeliveryEntry();
			break;
		case 3:
			uiShowShedContents();
			break;
		case 4:
			uiOrderEntry();
			break;
		case 5:
			uiShowPendingOrders();
			break;
		case 6:
			uiProcessNextOrder();
			break;
		}
	}
	private void uiAddShed() {
		String shedName;
		int oreTypeValue = 0;
		OreType oreType;
		IShed newShed = null;
		try {
			term.newScreen(
				"Add new shed",
				"[Enter] Next field",
				"Valid ore types:\n\n" +
				OreType.listValues() + "\n" +
				term.getFieldString("Enter shed name")
			);
			shedName = ConsoleInput.readLine();
			term.setPageFooter("[Enter] Complete entry");
			term.appendScreen(
				shedName + "\n" +
				term.getFieldString("Enter ore type")
			);
			oreTypeValue = ConsoleInput.readInt();
			for (IShed shed : shedList)
				if (shed.getName().equals(shedName))
					throw new IllegalArgumentException(
						"uiAddShed: name already used "
					);
			oreType = OreType.getByValue(oreTypeValue);
			switch (oreType) {
			case IRON:
				newShed = new ShedIron(shedName);
				break;
			case NICKEL:
				newShed = new ShedNickel(shedName);
				break;
			}
			shedList.insertLast(newShed);
			term.appendScreen(
				oreTypeValue + "\n\n" +
				"Success: added a new " + oreType + " shed " +
				"named '" + shedName + "'. "
			);
		} catch (Exception e) {
			term.appendScreen(
				oreTypeValue + "\n\n" +
				"Error: " + e.getMessage() + ". "
			);
		} finally {
			term.setPageFooter("[Enter] Return to main menu");
			ConsoleInput.readLine();
		}
	}
	private void uiOreDeliveryEntry() {
		String shedName;
		double oreWeight;
		double oreGrade = 0.0;
		IShed targetShed = null;
		OrePile newPile;
		try {
			term.newScreen(
				"Ore delivery data entry",
				"[Enter] Next field",
				term.getFieldString("Enter shed name")
			);
			shedName = ConsoleInput.readLine();
			term.appendScreen(
				shedName + "\n" +
				term.getFieldString(
					"Enter ore weight (" +
					defaultUnits + ")"
				)
			);
			oreWeight = ConsoleInput.readDouble();
			term.setPageFooter("[Enter] Complete entry");
			term.appendScreen(
				oreWeight + "\n" +
				term.getFieldString("Enter ore grade (%)")
			);
			oreGrade = ConsoleInput.readDouble();
			for (IShed shed : shedList)
				if (shed.getName().equals(shedName))
					targetShed = shed;
			if (targetShed == null)
				throw new Exception(
					"no shed with name '" + shedName + "'"
				);
			newPile = new OrePile(
				new Ore(
					targetShed.getOreType(),
					defaultUnits
				),
				oreWeight,
				oreGrade
			);
			targetShed.addPile(newPile);
			term.appendScreen(
				oreGrade + "\n\n" +
				"Success: recorded the delivery of " +
				oreWeight + " " + defaultUnits.toString() +
				" of " + oreGrade + "% " +
				targetShed.getOreType() + ". "
			);
		} catch (Exception e) {
			term.appendScreen(
				oreGrade + "\n\n" +
				"Error: " + e.getMessage() + ". "
			);
		} finally {
			term.setPageFooter("[Enter] Return to main menu");
			ConsoleInput.readLine();
		}
	}
	private void uiShowShedContents() {
		String tempOutput = "";
		String shedName = "";
		IShed targetShed = null;
		try {
			for (IShed shed : shedList)
				tempOutput += "* " + shed.getName() + "\n";
			term.newScreen(
				"View shed contents",
				"[Enter] Choose option",
				"List of available sheds:\n\n" +
				tempOutput + "\n" +
				term.getFieldString("Enter a choice")
			);
			shedName = ConsoleInput.readLine();
			for (IShed shed : shedList)
				if (shed.getName().equals(shedName))
					targetShed = shed;
			if (targetShed == null)
				throw new Exception(
					"no shed with name '" + shedName + "'"
				);
		} catch (Exception e) {
			term.appendScreen(
				shedName + "\n\n" +
				"Error: " + e.getMessage() + ". "
			);
			term.setPageFooter("[Enter] Return to main menu");
			ConsoleInput.readLine();
			return;
		}
		tempOutput = "";
		for (OrePile pile : targetShed)
			tempOutput +=
				"\u2502 " +
				String.format(
					"%20.12g",
					pile.getWeight()
				) + " \u2502 " +
				String.format(
					"%19.12g%%",
					pile.getGrade()
				) + " \u2502 " +
				String.format(
					"%20.12g",
					pile.getWeight() *
						pile.getGrade() / 100
				) + " \u2502\n";
		term.newScreen(
			"Contents of " + shedName,
			"[Enter] Return to main menu",
			"All weights in units: " + defaultUnits + "\n\n" +
			"\u250C" +
			term.repeatText("\u2500", 22) +
			"\u252C" +
			term.repeatText("\u2500", 22) +
			"\u252C" +
			term.repeatText("\u2500", 22) +
			"\u2510\n" +
			"\u2502 Ore weight           " +
			"\u2502 Grade percentage     " +
			"\u2502 Metal weight         \u2502\n" +
			"\u251C" +
			term.repeatText("\u2500", 22) +
			"\u253C" +
			term.repeatText("\u2500", 22) +
			"\u253C" +
			term.repeatText("\u2500", 22) +
			"\u2524\n" +
			tempOutput +
			"\u2514" +
			term.repeatText("\u2500", 22) +
			"\u2534" +
			term.repeatText("\u2500", 22) +
			"\u2534" +
			term.repeatText("\u2500", 22) +
			"\u2518\n"
		);
		ConsoleInput.readLine();
	}
	private void uiOrderEntry() {
		String customerName;
		String shippingDest;
		int oreTypeValue;
		OreType oreType;
		double unitPrice;
		double requiredMetalWeight = 0.0;
		try {
			term.newScreen(
				"Customer order data entry",
				"[Enter] Next field",
				"Valid ore types:\n\n" +
				OreType.listValues() + "\n" +
				term.getFieldString("Enter customer name")
			);
			customerName = ConsoleInput.readLine();
			term.appendScreen(
				customerName + "\n" +
				term.getFieldString(
					"Enter shipping destination"
				)
			);
			shippingDest = ConsoleInput.readLine();
			term.appendScreen(
				shippingDest + "\n" +
				term.getFieldString("Enter ore type")
			);
			oreTypeValue = ConsoleInput.readInt();
			oreType = OreType.getByValue(oreTypeValue);
			term.appendScreen(
				oreTypeValue + "\n" +
				term.getFieldString(
					"Enter price ($/" +
					defaultUnits +
					")"
				)
			);
			unitPrice = ConsoleInput.readDouble();
			term.setPageFooter("[Enter] Complete entry");
			term.appendScreen(
				unitPrice + "\n" +
				term.getFieldString(
					"Enter metal weight (" +
					defaultUnits +
					")"
				)
			);
			requiredMetalWeight = ConsoleInput.readDouble();
			ShipmentOrder order = new ShipmentOrder(
				customerName,
				shippingDest,
				new Ore(oreType, defaultUnits),
				requiredMetalWeight,
				unitPrice
			);
			orderQueue.enqueue(order);
			term.appendScreen(
				requiredMetalWeight + "\n\n" +
				"Success: order #" +
				order.getOrderID() +
				", worth $" +
				order.calcShipmentValue() +
				". "
			);
		} catch (Exception e) {
			term.appendScreen(
				requiredMetalWeight + "\n\n" +
				"Error: " + e.getMessage() + ". "
			);
		} finally {
			term.setPageFooter("[Enter] Return to main menu");
			ConsoleInput.readLine();
		}
	}
	private void uiShowPendingOrders() {
		String tempOutput = "";
		for (ShipmentOrder order : orderQueue)
			tempOutput +=
				"\u2502 " +
				String.format(
					"%3d",
					order.getOrderID()
				) + " \u2502 " +
				String.format(
					"%-15s",
					order.getCustomerName()
				) + " \u2502 " +
				String.format(
					"%-10s",
					order.getOre().getOreType()
				) + " \u2502 " +
				String.format(
					"%13.7g",
					order.getOrderedMetalWt()
				) + " \u2502 " +
				String.format(
					"%13.7g",
					order.calcShipmentValue()
				) + " \u2502\n";
		term.newScreen(
			"View pending customer orders",
			"[Enter] Return to main menu",
			"All weights in units: " + defaultUnits + "\n\n" +
			"\u250C" +
			term.repeatText("\u2500", 5) +
			"\u252C" +
			term.repeatText("\u2500", 17) +
			"\u252C" +
			term.repeatText("\u2500", 12) +
			"\u252C" +
			term.repeatText("\u2500", 15) +
			"\u252C" +
			term.repeatText("\u2500", 15) +
			"\u2510\n" +
			"\u2502 ID  " +
			"\u2502 Customer name   " +
			"\u2502 Ore type   " +
			"\u2502 Metal weight  " +
			"\u2502 Total value   \u2502\n" +
			"\u251C" +
			term.repeatText("\u2500", 5) +
			"\u253C" +
			term.repeatText("\u2500", 17) +
			"\u253C" +
			term.repeatText("\u2500", 12) +
			"\u253C" +
			term.repeatText("\u2500", 15) +
			"\u253C" +
			term.repeatText("\u2500", 15) +
			"\u2524\n" +
			tempOutput +
			"\u2514" +
			term.repeatText("\u2500", 5) +
			"\u2534" +
			term.repeatText("\u2500", 17) +
			"\u2534" +
			term.repeatText("\u2500", 12) +
			"\u2534" +
			term.repeatText("\u2500", 15) +
			"\u2534" +
			term.repeatText("\u2500", 15) +
			"\u2518\n"
		);
		ConsoleInput.readLine();
	}
	private void uiProcessNextOrder() {
		ShipmentOrder order = null;
		IShed shed = null;
		double oreToLoad;
		try {
			term.newScreen(
				"Process next customer order",
				"[Enter] Choose option",
				"Details of next order:\n"
			);
			order = orderQueue.peek();
			term.appendScreen(
				"\nID:         " + order.getOrderID() +
				"\nOre type:   " + order.getOre().getOreType() +
				"\nUnit price: " + order.getUnitPrice() +
				"\nCustomer:   " + order.getCustomerName() +
				"\nLocation:   " + order.getShippingDest() +
				"\nMetal:      " + order.getOrderedMetalWt() +
					" " + order.getOre().getUnits() +
				"\nValue:      " + order.calcShipmentValue() +
				"\n\n" +
				term.getFieldString("Continue? [y/n]")
			);
			if (!ConsoleInput.readLine().equals("y")) {
				term.appendScreen(
					"n\n\n" +
					"Order processing cancelled. "
				);
				return;
			}
			for (IShed s : shedList)
				if (
					s.getOreType() ==
					order.getOre().getOreType()
				)
					shed = s;
			term.appendScreen(
				"y\n\n" +
				"Using shed named: " + shed.getName() + "\n\n"
			);
			oreToLoad = shed.satisfyOrder(order);
			order.setShippedOreWt(oreToLoad);
			orderQueue.dequeue();
			term.appendScreen(
				"Order processed. Load " +
				oreToLoad + " " + defaultUnits +
				" onto the ship. "
			);
		} catch (Exception e) {
			term.appendScreen(
				"\n\n" +
				"Error: " + e.getMessage() + ". "
			);
			orderQueue.enqueue(orderQueue.dequeue());
		} finally {
			term.setPageFooter("[Enter] Return to main menu");
			ConsoleInput.readLine();
		}
	}
}
