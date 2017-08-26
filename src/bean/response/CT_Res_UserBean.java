package bean.response;

public class CT_Res_UserBean {
	private String pic_profile;
	private String sale_code;
	private String user_name;
	private String company_id;
	private String zone_id;
	
	public CT_Res_UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Res_UserBean(String pic_profile, String sale_code,
			String user_name, String company_id, String zone_id) {
		super();
		this.pic_profile = pic_profile;
		this.sale_code = sale_code;
		this.user_name = user_name;
		this.company_id = company_id;
		this.zone_id = zone_id;
	}

	public String getPic_profile() {
		return pic_profile;
	}

	public void setPic_profile(String pic_profile) {
		this.pic_profile = pic_profile;
	}

	public String getSale_code() {
		return sale_code;
	}

	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
