package entities;




public class Log{
	private int lid;
	private int amount;
	private int user_id;
	private int activity_id;
	private String activity_type;
	
	
	public Log(int id, int amount, int user_id, int activity_id,String activity_type) {
		super();
		this.lid = id;
		this.amount = amount;
		this.user_id = user_id;
		this.activity_id = activity_id;
		this.activity_type = activity_type;
	}
	
	public int getId() {
		return lid;
	}
	public void setId(int id) {
		this.lid = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAcctivity_id() {
		return activity_id;
	}
	public void setAcctivity_id(int acctivity_id) {
		this.activity_id = acctivity_id;
	}
	public String getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}
	
	

}
