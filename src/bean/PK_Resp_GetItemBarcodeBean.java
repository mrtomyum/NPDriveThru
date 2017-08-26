package bean;

public class PK_Resp_GetItemBarcodeBean {
	private String code;
	private int rate1;
	private String unitCode;
	private String itemName;
	private String expertCode;
	private String departmentCode;
	private String departmentName;
	private String categoryCode;
	private String categoryName;
	private String secCode;
	private String secName;
	private double averageCost;
	
	public PK_Resp_GetItemBarcodeBean() {

	}

	public PK_Resp_GetItemBarcodeBean(String code, int rate1, String unitCode,
			String itemName, String expertCode, String departmentCode,
			String departmentName, String categoryCode, String categoryName,
			String secCode, String secName, double averageCost) {
		super();
		this.code = code;
		this.rate1 = rate1;
		this.unitCode = unitCode;
		this.itemName = itemName;
		this.expertCode = expertCode;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.secCode = secCode;
		this.secName = secName;
		this.averageCost = averageCost;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getRate1() {
		return rate1;
	}

	public void setRate1(int rate1) {
		this.rate1 = rate1;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getExpertCode() {
		return expertCode;
	}

	public void setExpertCode(String expertCode) {
		this.expertCode = expertCode;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public double getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}

	
	
	
}
