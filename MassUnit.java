public enum MassUnit {
	GRAM       (1.0,        "g"),
	KILOGRAM   (1.0e3,      "kg"),
	TONNE      (1.0e6,      "t"),
	TON_SHORT  (907184.74,  "short tons"),
	TON_LONG   (1.016e6,    "long tons"),
	;
	private final double mass;    // normalised as grams
	private final String suffix;  // human-readable identifier
	private MassUnit(double mass, String suffix) {
		this.mass = mass;
		this.suffix = suffix;
	}
	public String toString() {
		return this.suffix;
	}
	public static MassUnit getBySuffix(String suffix) {
		MassUnit result = null;
		for (MassUnit unit : MassUnit.values())
			if (unit.suffix.equals(suffix))
				result = unit;
		if (result == null)
			throw new IllegalArgumentException(
				"invalid suffix: " +
				suffix
			);
		return result;
	}
}
