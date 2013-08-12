public enum OreType {
	IRON            (1),
	NICKEL          (2),
	;
	private int value;
	private OreType(int value) {
		this.value = value;
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
