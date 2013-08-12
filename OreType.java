public enum OreType {
	IRON            (1,     "iron"),
	NICKEL          (2,     "nickel"),
	;
	private int value;
	private String friendlyName;
	private OreType(int value, String friendlyName) {
		this.value = value;
		this.friendlyName = friendlyName;
	}
	public String toString() {
		return this.friendlyName;
	}
	public static OreType getByValue(int value) {
		OreType result = null;
		for (OreType oreType : OreType.values())
			if (oreType.value == value)
				result = oreType;
		if (result == null)
			throw new IllegalArgumentException(
				"OreType: invalid value: " +
				value
			);
		return result;
	}
	public static String listValues() {
		String result = "";
		for (OreType oreType : OreType.values())
			result +=
				oreType.value + ". " +
				oreType.toString() + "\n";
		return result;
	}
}
