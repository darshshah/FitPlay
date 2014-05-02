package entities;

public class Cardio_Exercise {
	
		private int ce_id;
		private String name;
		private String units;
		private float cals_per_unit;
		
		public Cardio_Exercise(int ce_id, String name, String units,
				float cals_per_unit) {
			super();
			this.ce_id = ce_id;
			this.name = name;
			this.units = units;
			this.cals_per_unit = cals_per_unit;
		}

		public int getCe_id() {
			return ce_id;
		}

		public void setCe_id(int ce_id) {
			this.ce_id = ce_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUnits() {
			return units;
		}

		public void setUnits(String units) {
			this.units = units;
		}

		public float getCals_per_unit() {
			return cals_per_unit;
		}

		public void setCals_per_unit(float cals_per_unit) {
			this.cals_per_unit = cals_per_unit;
		}
		
		
}
