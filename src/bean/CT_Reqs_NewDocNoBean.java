package bean;

public class CT_Reqs_NewDocNoBean {
	private String access_token;
	private String carNumber;
	private String carBrand;
	
	
	public CT_Reqs_NewDocNoBean() {

	}


	public CT_Reqs_NewDocNoBean(String access_token, String carNumber, String carBrand) {
		this.access_token = access_token;
		this.carNumber = carNumber;
		this.carBrand = carBrand;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getCarNumber() {
		return carNumber;
	}


	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}


	public String getCarBrand() {
		return carBrand;
	}


	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	

}
