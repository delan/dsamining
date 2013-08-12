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
			"Enter a choice: "
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
		//
	}
	private void uiOrderEntry() {
		//
	}
	private void uiShowPendingOrders() {
		//
	}
	private void uiProcessNextOrder() {
		//
	}
	private void findShedByName() {
		//
	}
}
