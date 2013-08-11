import io.*;
public class DSAShipmentManager {
	private DSALinkedList<IShed> shedList;
	private DSAQueue<ShipmentOrder> orderQueue;
	public DSAShipmentManager() {
		shedList = new DSALinkedList<IShed>();
		orderQueue = new DSAQueue<ShipmentOrder>();
	}
	public void run() {
		while (true) {
			int choice = uiDisplayMainMenu();
			this.uiMainMenuChoice(choice);
		}
	}
	private int uiDisplayMainMenu() {
		TerminalHelper.newScreen(
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
			TerminalHelper.cleanup();
			System.exit(0);
		}
	}
}
