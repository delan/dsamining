public class OrePile {
	private Ore ore;
	private double weight;
	private double grade;
	public OrePile(Ore ore, double weight, double grade) {
		this.ore = ore;
		this.setWeight(weight);
		this.setGrade(grade);
	}
	public OrePile(OrePile orePile) {
		this.ore = orePile.getOre();
		this.setWeight(orePile.getWeight());
		this.setGrade(orePile.getGrade());
	}
	public Ore getOre() {
		return this.ore;
	}
	public double getWeight() {
		return this.weight;
	}
	public double getGrade() {
		return this.grade;
	}
	public void setWeight(double weight) {
		if (weight <= 0.0)
			throw new IllegalArgumentException(
				"weight must be positive"
			);
		this.weight = weight;
	}
	public void setGrade(double grade) {
		if (grade <= 0.0 || grade > 100.0)
			throw new IllegalArgumentException(
				"grade must be a member of (0.0, 100.0]"
			);
		this.grade = grade;
	}
	public double calcMetalWeight() {
		return this.weight * this.grade / 100.0;
	}
}
