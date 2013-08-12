public class Ore {
	private OreType oreType;
	private MassUnit units;
	public Ore(OreType type, String units) {
		this.oreType = type;
		this.setUnits(units);
	}
	public Ore(OreType type, MassUnit units) {
		this.oreType = type;
		this.setUnits(units);
	}
	public Ore(Ore ore) {
		this.oreType = ore.getOreType();
		this.units = ore.getUnits();
	}
	public OreType getOreType() {
		return this.oreType;
	}
	public MassUnit getUnits() {
		return this.units;
	}
	public void setUnits(String units) {
		this.units = MassUnit.getBySuffix(units);
	}
	public void setUnits(MassUnit units) {
		this.units = units;
	}
}
