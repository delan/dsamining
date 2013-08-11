public class ShipmentOrder {
	private static int nextOrderID = 1;
	private int orderID;
	private Ore ore;
	private double unitPrice;
	private String customerName;
	private String shippingDest;
	private double orderedMetalWt;
	private double shippedOreWt;
	private boolean isPending;
	public ShipmentOrder(
		String customerName,
		String shippingDest,
		Ore ore,
		double orderedMetalWt,
		double unitPrice
	) {
		this.orderID = nextOrderID++;
		this.setOre(ore);
		this.setUnitPrice(unitPrice);
		this.setCustomerName(customerName);
		this.setShippingDest(shippingDest);
		this.setOrderedMetalWt(orderedMetalWt);
		this.shippedOreWt = 0.0;
		this.isPending = true;
	}
	public Ore getOre() {
		return this.ore;
	}
	public int getOrderID() {
		return orderID;
	}
	public double getUnitPrice() {
		return this.unitPrice;
	}
	public String getCustomerName() {
		return this.customerName;
	}
	public String getShippingDest() {
		return this.shippingDest;
	}
	public double getOrderedMetalWt() {
		return this.orderedMetalWt;
	}
	public double getShippedOreWt() {
		if (this.getIsPending())
			throw new IllegalStateException(
				"cannot get shipped ore weight until shipped"
			);
		return this.shippedOreWt;
	}
	public boolean getIsPending() {
		return this.isPending;
	}
	public void setOre(Ore ore) {
		this.ore = ore;
	}
	public void setUnitPrice(double unitPrice) {
		if (unitPrice <= 0.0)
			throw new IllegalArgumentException(
				"unit price must be positive"
			);
		this.unitPrice = unitPrice;
	}
	public void setCustomerName(String customerName) {
		if (customerName == null || customerName.equals(""))
			throw new IllegalArgumentException(
				"customer name must not be null or empty"
			);
		this.customerName = customerName;
	}
	public void setShippingDest(String shippingDest) {
		if (shippingDest == null || shippingDest.equals(""))
			throw new IllegalArgumentException(
				"shipping destination must not be null or empty"
			);
		this.shippingDest = shippingDest;
	}
	public void setOrderedMetalWt(double orderedMetalWt) {
		if (orderedMetalWt <= 0.0)
			throw new IllegalArgumentException(
				"ordered metal weight must be positive"
			);
		this.orderedMetalWt = orderedMetalWt;
	}
	public void setShippedOreWt(double shippedOreWt) {
		if (shippedOreWt <= 0.0)
			throw new IllegalArgumentException(
				"shipped ore weight must be positive"
			);
		this.shippedOreWt = shippedOreWt;
		this.isPending = false;
	}
	public double calcAverageGrade() {
		return 100.0 *
			this.getOrderedMetalWt() /
			this.getShippedOreWt();
	}
	public double calcShipmentValue() {
		return this.getOrderedMetalWt() * this.getUnitPrice();
	}
}
