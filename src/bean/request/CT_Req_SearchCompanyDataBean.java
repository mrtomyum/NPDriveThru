package bean.request;

public class CT_Req_SearchCompanyDataBean {
	private String company_id;
	private String zone_id;
	
	public CT_Req_SearchCompanyDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Req_SearchCompanyDataBean(String company_id, String zone_id) {
		super();
		this.company_id = company_id;
		this.zone_id = zone_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}
	
	
}
