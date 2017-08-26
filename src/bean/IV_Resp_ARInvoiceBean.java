package bean;

public class IV_Resp_ARInvoiceBean {
	 private String docNo; 
	 private String docDate; 
	 private String taxNo; 
	 private int taxType; 
	 private String arCode;  
	 private String arName; 
	 private String arAddress; 
	 private String cashierCode;  
	 private String machineNo; 
	 private String machineCode; 
	 private int posStatus; 
	 private String billTime; 
     private String creditType; 
     private String creditNo; 
     private String confirmNo; 
     private String chargeWord; 
     private double creditBaseAmount; 
     private double chargeAmount; 
     private double grandTotal; 
     private double coupongAmount;  
     private double changeAmount;
     private String departCode; 
     private int creditDay; 
     private String dueDate;
     private String saleCode; 
     private float taxRate; 
     private int isConfirm; 
     private String myDescription; 
     private int billType; 
     private String billGroup; 
     private String refDocNo; 
     private double sumOfItemAmount; 
     private String discountWord; 
     private double discountAmount; 
     private double afterDiscount; 
     private double beforeTaxAmount; 
     private double taxAmount; 
     private double totalAmount;  
     private double zeroTaxAmount; 
     private double exceptTaxAmount; 
     private double sumCashAmount; 
     private double sumChqAmount; 
     private double sumCreditAmount; 
     private double sumBankAmount; 
     private double depositIncTax; 
     private double sumOfDeposit1; 
     private double sumOfDeposit2; 
     private double sumOfWTax; 
     private double netDebtAmount; 
     private double homeAmount; 
     private double otherIncome; 
     private double otherExpense; 
     private double excessAmount1; 
     private double excessAmount2; 
     private double billBalance;
     private float exchangeRate;
     private int isCancel; 
     private int isCompleteSave; 
     private int isPostGL;  
     private int payBillStatus; 
     private String allocateCode; 
     private String projectCode;   
     private String creatorCode;
     private int isConditionSend;
     private double payBillAmount;
     private String soRefNo;
     private String shiftCode;
     private String recurName;
     
     
	public IV_Resp_ARInvoiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_ARInvoiceBean(String docNo, String docDate, String taxNo,
			int taxType, String arCode, String arName, String arAddress,
			String cashierCode, String machineNo, String machineCode,
			int posStatus, String billTime, String creditType, String creditNo,
			String confirmNo, String chargeWord, double creditBaseAmount,
			double chargeAmount, double grandTotal, double coupongAmount,
			double changeAmount, String departCode, int creditDay,
			String dueDate, String saleCode, float taxRate, int isConfirm,
			String myDescription, int billType, String billGroup,
			String refDocNo, double sumOfItemAmount, String discountWord,
			double discountAmount, double afterDiscount,
			double beforeTaxAmount, double taxAmount, double totalAmount,
			double zeroTaxAmount, double exceptTaxAmount, double sumCashAmount,
			double sumChqAmount, double sumCreditAmount, double sumBankAmount,
			double depositIncTax, double sumOfDeposit1, double sumOfDeposit2,
			double sumOfWTax, double netDebtAmount, double homeAmount,
			double otherIncome, double otherExpense, double excessAmount1,
			double excessAmount2, double billBalance, float exchangeRate,
			int isCancel, int isCompleteSave, int isPostGL, int payBillStatus,
			String allocateCode, String projectCode, String creatorCode,
			int isConditionSend, double payBillAmount, String soRefNo,
			String shiftCode, String recurName) {
		super();
		this.docNo = docNo;
		this.docDate = docDate;
		this.taxNo = taxNo;
		this.taxType = taxType;
		this.arCode = arCode;
		this.arName = arName;
		this.arAddress = arAddress;
		this.cashierCode = cashierCode;
		this.machineNo = machineNo;
		this.machineCode = machineCode;
		this.posStatus = posStatus;
		this.billTime = billTime;
		this.creditType = creditType;
		this.creditNo = creditNo;
		this.confirmNo = confirmNo;
		this.chargeWord = chargeWord;
		this.creditBaseAmount = creditBaseAmount;
		this.chargeAmount = chargeAmount;
		this.grandTotal = grandTotal;
		this.coupongAmount = coupongAmount;
		this.changeAmount = changeAmount;
		this.departCode = departCode;
		this.creditDay = creditDay;
		this.dueDate = dueDate;
		this.saleCode = saleCode;
		this.taxRate = taxRate;
		this.isConfirm = isConfirm;
		this.myDescription = myDescription;
		this.billType = billType;
		this.billGroup = billGroup;
		this.refDocNo = refDocNo;
		this.sumOfItemAmount = sumOfItemAmount;
		this.discountWord = discountWord;
		this.discountAmount = discountAmount;
		this.afterDiscount = afterDiscount;
		this.beforeTaxAmount = beforeTaxAmount;
		this.taxAmount = taxAmount;
		this.totalAmount = totalAmount;
		this.zeroTaxAmount = zeroTaxAmount;
		this.exceptTaxAmount = exceptTaxAmount;
		this.sumCashAmount = sumCashAmount;
		this.sumChqAmount = sumChqAmount;
		this.sumCreditAmount = sumCreditAmount;
		this.sumBankAmount = sumBankAmount;
		this.depositIncTax = depositIncTax;
		this.sumOfDeposit1 = sumOfDeposit1;
		this.sumOfDeposit2 = sumOfDeposit2;
		this.sumOfWTax = sumOfWTax;
		this.netDebtAmount = netDebtAmount;
		this.homeAmount = homeAmount;
		this.otherIncome = otherIncome;
		this.otherExpense = otherExpense;
		this.excessAmount1 = excessAmount1;
		this.excessAmount2 = excessAmount2;
		this.billBalance = billBalance;
		this.exchangeRate = exchangeRate;
		this.isCancel = isCancel;
		this.isCompleteSave = isCompleteSave;
		this.isPostGL = isPostGL;
		this.payBillStatus = payBillStatus;
		this.allocateCode = allocateCode;
		this.projectCode = projectCode;
		this.creatorCode = creatorCode;
		this.isConditionSend = isConditionSend;
		this.payBillAmount = payBillAmount;
		this.soRefNo = soRefNo;
		this.shiftCode = shiftCode;
		this.recurName = recurName;
	}


	public String getDocNo() {
		return docNo;
	}


	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}


	public String getDocDate() {
		return docDate;
	}


	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}


	public String getTaxNo() {
		return taxNo;
	}


	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}


	public int getTaxType() {
		return taxType;
	}


	public void setTaxType(int taxType) {
		this.taxType = taxType;
	}


	public String getArCode() {
		return arCode;
	}


	public void setArCode(String arCode) {
		this.arCode = arCode;
	}


	public String getArName() {
		return arName;
	}


	public void setArName(String arName) {
		this.arName = arName;
	}


	public String getArAddress() {
		return arAddress;
	}


	public void setArAddress(String arAddress) {
		this.arAddress = arAddress;
	}


	public String getCashierCode() {
		return cashierCode;
	}


	public void setCashierCode(String cashierCode) {
		this.cashierCode = cashierCode;
	}


	public String getMachineNo() {
		return machineNo;
	}


	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}


	public String getMachineCode() {
		return machineCode;
	}


	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


	public int getPosStatus() {
		return posStatus;
	}


	public void setPosStatus(int posStatus) {
		this.posStatus = posStatus;
	}


	public String getBillTime() {
		return billTime;
	}


	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}


	public String getCreditType() {
		return creditType;
	}


	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}


	public String getCreditNo() {
		return creditNo;
	}


	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}


	public String getConfirmNo() {
		return confirmNo;
	}


	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}


	public String getChargeWord() {
		return chargeWord;
	}


	public void setChargeWord(String chargeWord) {
		this.chargeWord = chargeWord;
	}


	public double getCreditBaseAmount() {
		return creditBaseAmount;
	}


	public void setCreditBaseAmount(double creditBaseAmount) {
		this.creditBaseAmount = creditBaseAmount;
	}


	public double getChargeAmount() {
		return chargeAmount;
	}


	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}


	public double getGrandTotal() {
		return grandTotal;
	}


	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}


	public double getCoupongAmount() {
		return coupongAmount;
	}


	public void setCoupongAmount(double coupongAmount) {
		this.coupongAmount = coupongAmount;
	}


	public double getChangeAmount() {
		return changeAmount;
	}


	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}


	public String getDepartCode() {
		return departCode;
	}


	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}


	public int getCreditDay() {
		return creditDay;
	}


	public void setCreditDay(int creditDay) {
		this.creditDay = creditDay;
	}


	public String getDueDate() {
		return dueDate;
	}


	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}


	public String getSaleCode() {
		return saleCode;
	}


	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}


	public float getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}


	public int getIsConfirm() {
		return isConfirm;
	}


	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}


	public String getMyDescription() {
		return myDescription;
	}


	public void setMyDescription(String myDescription) {
		this.myDescription = myDescription;
	}


	public int getBillType() {
		return billType;
	}


	public void setBillType(int billType) {
		this.billType = billType;
	}


	public String getBillGroup() {
		return billGroup;
	}


	public void setBillGroup(String billGroup) {
		this.billGroup = billGroup;
	}


	public String getRefDocNo() {
		return refDocNo;
	}


	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}


	public double getSumOfItemAmount() {
		return sumOfItemAmount;
	}


	public void setSumOfItemAmount(double sumOfItemAmount) {
		this.sumOfItemAmount = sumOfItemAmount;
	}


	public String getDiscountWord() {
		return discountWord;
	}


	public void setDiscountWord(String discountWord) {
		this.discountWord = discountWord;
	}


	public double getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}


	public double getAfterDiscount() {
		return afterDiscount;
	}


	public void setAfterDiscount(double afterDiscount) {
		this.afterDiscount = afterDiscount;
	}


	public double getBeforeTaxAmount() {
		return beforeTaxAmount;
	}


	public void setBeforeTaxAmount(double beforeTaxAmount) {
		this.beforeTaxAmount = beforeTaxAmount;
	}


	public double getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public double getZeroTaxAmount() {
		return zeroTaxAmount;
	}


	public void setZeroTaxAmount(double zeroTaxAmount) {
		this.zeroTaxAmount = zeroTaxAmount;
	}


	public double getExceptTaxAmount() {
		return exceptTaxAmount;
	}


	public void setExceptTaxAmount(double exceptTaxAmount) {
		this.exceptTaxAmount = exceptTaxAmount;
	}


	public double getSumCashAmount() {
		return sumCashAmount;
	}


	public void setSumCashAmount(double sumCashAmount) {
		this.sumCashAmount = sumCashAmount;
	}


	public double getSumChqAmount() {
		return sumChqAmount;
	}


	public void setSumChqAmount(double sumChqAmount) {
		this.sumChqAmount = sumChqAmount;
	}


	public double getSumCreditAmount() {
		return sumCreditAmount;
	}


	public void setSumCreditAmount(double sumCreditAmount) {
		this.sumCreditAmount = sumCreditAmount;
	}


	public double getSumBankAmount() {
		return sumBankAmount;
	}


	public void setSumBankAmount(double sumBankAmount) {
		this.sumBankAmount = sumBankAmount;
	}


	public double getDepositIncTax() {
		return depositIncTax;
	}


	public void setDepositIncTax(double depositIncTax) {
		this.depositIncTax = depositIncTax;
	}


	public double getSumOfDeposit1() {
		return sumOfDeposit1;
	}


	public void setSumOfDeposit1(double sumOfDeposit1) {
		this.sumOfDeposit1 = sumOfDeposit1;
	}


	public double getSumOfDeposit2() {
		return sumOfDeposit2;
	}


	public void setSumOfDeposit2(double sumOfDeposit2) {
		this.sumOfDeposit2 = sumOfDeposit2;
	}


	public double getSumOfWTax() {
		return sumOfWTax;
	}


	public void setSumOfWTax(double sumOfWTax) {
		this.sumOfWTax = sumOfWTax;
	}


	public double getNetDebtAmount() {
		return netDebtAmount;
	}


	public void setNetDebtAmount(double netDebtAmount) {
		this.netDebtAmount = netDebtAmount;
	}


	public double getHomeAmount() {
		return homeAmount;
	}


	public void setHomeAmount(double homeAmount) {
		this.homeAmount = homeAmount;
	}


	public double getOtherIncome() {
		return otherIncome;
	}


	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}


	public double getOtherExpense() {
		return otherExpense;
	}


	public void setOtherExpense(double otherExpense) {
		this.otherExpense = otherExpense;
	}


	public double getExcessAmount1() {
		return excessAmount1;
	}


	public void setExcessAmount1(double excessAmount1) {
		this.excessAmount1 = excessAmount1;
	}


	public double getExcessAmount2() {
		return excessAmount2;
	}


	public void setExcessAmount2(double excessAmount2) {
		this.excessAmount2 = excessAmount2;
	}


	public double getBillBalance() {
		return billBalance;
	}


	public void setBillBalance(double billBalance) {
		this.billBalance = billBalance;
	}


	public float getExchangeRate() {
		return exchangeRate;
	}


	public void setExchangeRate(float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}


	public int getIsCancel() {
		return isCancel;
	}


	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}


	public int getIsCompleteSave() {
		return isCompleteSave;
	}


	public void setIsCompleteSave(int isCompleteSave) {
		this.isCompleteSave = isCompleteSave;
	}


	public int getIsPostGL() {
		return isPostGL;
	}


	public void setIsPostGL(int isPostGL) {
		this.isPostGL = isPostGL;
	}


	public int getPayBillStatus() {
		return payBillStatus;
	}


	public void setPayBillStatus(int payBillStatus) {
		this.payBillStatus = payBillStatus;
	}


	public String getAllocateCode() {
		return allocateCode;
	}


	public void setAllocateCode(String allocateCode) {
		this.allocateCode = allocateCode;
	}


	public String getProjectCode() {
		return projectCode;
	}


	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	public String getCreatorCode() {
		return creatorCode;
	}


	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}


	public int getIsConditionSend() {
		return isConditionSend;
	}


	public void setIsConditionSend(int isConditionSend) {
		this.isConditionSend = isConditionSend;
	}


	public double getPayBillAmount() {
		return payBillAmount;
	}


	public void setPayBillAmount(double payBillAmount) {
		this.payBillAmount = payBillAmount;
	}


	public String getSoRefNo() {
		return soRefNo;
	}


	public void setSoRefNo(String soRefNo) {
		this.soRefNo = soRefNo;
	}


	public String getShiftCode() {
		return shiftCode;
	}


	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}


	public String getRecurName() {
		return recurName;
	}


	public void setRecurName(String recurName) {
		this.recurName = recurName;
	}


}
