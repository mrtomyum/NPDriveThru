package bean.response;

import java.util.List;

public class DT_Res_ZoneBean {
	private CT_Resp_ResponseBean resp;
	List<DT_Res_ListZoneBean> zone;
	
	
	public DT_Res_ZoneBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DT_Res_ZoneBean(CT_Resp_ResponseBean resp,
			List<DT_Res_ListZoneBean> zone) {
		super();
		this.resp = resp;
		this.zone = zone;
	}


	public CT_Resp_ResponseBean getResp() {
		return resp;
	}


	public void setResp(CT_Resp_ResponseBean resp) {
		this.resp = resp;
	}


	public List<DT_Res_ListZoneBean> getZone() {
		return zone;
	}


	public void setZone(List<DT_Res_ListZoneBean> zone) {
		this.zone = zone;
	}
	
	
}
