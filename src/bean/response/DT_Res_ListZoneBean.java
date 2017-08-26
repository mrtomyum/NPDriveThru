package bean.response;

import java.util.List;

public class DT_Res_ListZoneBean {
	private String zone_id;
	private String zone_name;
	
	
	public DT_Res_ListZoneBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DT_Res_ListZoneBean(String zone_id, String zone_name) {
		super();
		this.zone_id = zone_id;
		this.zone_name = zone_name;
	}


	public String getZone_id() {
		return zone_id;
	}


	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}


	public String getZone_name() {
		return zone_name;
	}


	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}
	
	
}
