package bean;

public class PK_Resp_SaleCodeDetails {
	private int isExist;
	private String saleCode;
	private String saleName;
	
	
	public PK_Resp_SaleCodeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PK_Resp_SaleCodeDetails(int isExist, String saleCode, String saleName) {
		super();
		this.isExist = isExist;
		this.saleCode = saleCode;
		this.saleName = saleName;
	}
	
	public int getIsExist(){
		return isExist;
	}
	
	public void setIsExist(int isExist){
		this.isExist = isExist;
	}
	
	public String getSaleCode(){
		return saleCode;
	}
	
	public void setSaleCode(String saleCode){
		this.saleCode = saleCode;
	}
	
	public String getSaleName(){
		return saleName;
	}
	
	public void setSaleName(String saleName){
		this.saleName = saleName;
	}
}
