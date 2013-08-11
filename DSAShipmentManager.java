import io.*;
public class DSAShipmentManager {
	private DSALinkedList<IShed> shedList;
	private DSAQueue<ShipmentOrder> orderQueue;
	private TerminalHelper term;
	public DSAShipmentManager() {
		shedList = new DSALinkedList<IShed>();
		orderQueue = new DSAQueue<ShipmentOrder>();
		term = new TerminalHelper(
			"Data Structures and Algorithms 120: " +
			"Mining Magnate Edition",
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
			"(1) Add New Shed\n" +
			"(2) Ore Delivery Data Entry\n" +
			"(3) View Shed Contents\n" +
			"(4) Customer Order Data Entry\n" +
			"(5) View Pending Customer Orders\n" +
			"(6) Process Next Customer Order\n" +
			"(0) Quit\n" +
			"Enter a choice: "
		);
		return ConsoleInput.readInt();
	}
	private void uiMainMenuChoice(int choice) {
		if (choice == 0) {
			term.cleanup();
			System.exit(0);
		}
	}
}
