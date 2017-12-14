package bean.response;

public class SO_Res_ListPickZoneBean {
	private String pick_zone_id;
	private String name;
	
	public SO_Res_ListPickZoneBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ListPickZoneBean(String pick_zone_id, String name) {
		super();
		this.pick_zone_id = pick_zone_id;
		this.name = name;
	}

	public String getPick_zone_id() {
		return pick_zone_id;
	}

	public void setPick_zone_id(String pick_zone_id) {
		this.pick_zone_id = pick_zone_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
