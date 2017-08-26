package bean.response;

import java.util.List;

public class DT_Res_ListCompanyBean {
	private String company_id;
	private String company_name;
	private List<DT_Res_ListZoneBean> zone;
	
	
	public DT_Res_ListCompanyBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DT_Res_ListCompanyBean(String company_id, String company_name,
			List<DT_Res_ListZoneBean> zone) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.zone = zone;
	}


	public String getCompany_id() {
		return company_id;
	}


	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}


	public String getCompany_name() {
		return company_name;
	}


	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}


	public List<DT_Res_ListZoneBean> getZone() {
		return zone;
	}


	public void setZone(List<DT_Res_ListZoneBean> zone) {
		this.zone = zone;
	}


}
