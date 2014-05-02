package entities;

import java.util.Calendar;

public class Challenge {

	private int cid;
	private int group_id;
	private String cname;
	private String description;
	//private Calendar startDate;
	//private Calendar endDate;
	
	public Challenge(int cid, int group_id, String cname, String description/*,
			Calendar startDate, Calendar endDate*/) {
		super();
		this.cid = cid;
		this.group_id = group_id;
		this.cname = cname;
		this.description = description;
		//this.startDate = startDate;
		//this.endDate = endDate;
	}
	
	public int getId() {
		return cid;
	}
	public void setId(int id) {
		this.cid = id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getName() {
		return cname;
	}
	public void setName(String name) {
		this.cname = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	/*public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}*/
	
	
}
