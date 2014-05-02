package entities;

public class Strength_Exercise {

	private int se_id;
	private String name;
	private int reps;
	private int weight;
	private String	weight_unit;
	private float cals_per_unit;
	
	
	public Strength_Exercise(int se_id, String name, int reps, int weight,
			String weight_unit, float cals_per_unit) {
		super();
		this.se_id = se_id;
		this.name = name;
		this.reps = reps;
		this.weight = weight;
		this.weight_unit = weight_unit;
		this.cals_per_unit = cals_per_unit;
	}


	public int getSe_id() {
		return se_id;
	}


	public void setSe_id(int se_id) {
		this.se_id = se_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getReps() {
		return reps;
	}


	public void setReps(int reps) {
		this.reps = reps;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getWeight_unit() {
		return weight_unit;
	}


	public void setWeight_unit(String weight_unit) {
		this.weight_unit = weight_unit;
	}


	public float getCals_per_unit() {
		return cals_per_unit;
	}


	public void setCals_per_unit(float cals_per_unit) {
		this.cals_per_unit = cals_per_unit;
	}
		
}
