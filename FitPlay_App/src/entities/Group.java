package entities;

public class Group {

	private int gid;
	private String gname;
	private int owner_id;
	
	public Group(int gid, String gname, int gowner_id) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.owner_id = gowner_id;
	}

	public Group(String string) {
		// TODO Auto-generated constructor stub
		this.gname = string;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	
	@Override
	public String toString()
	{
		return gname;
	}
	
	
	
}
