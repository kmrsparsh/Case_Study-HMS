package com.bean.Patient;

public class Medicine {
	
	String Medicine_name;
	int Total_quantity;
	int Usedquantity;
	int RatePerMed;
	int Totalcost;
	
	
	//getters
	
	public String getMedicine_name() {
		return Medicine_name;
	}
	public int getTotal_quantity() {
		return Total_quantity;
	}
	public int getUsedquantity() {
		return Usedquantity;
	}
	public int getRatePerMed() {
		return RatePerMed;
	}
	public int getTotalcost() {
		return Totalcost;
	}
	
	//setters
	
	public void setMedicine_name(String Medicine_name) {
		this.Medicine_name = Medicine_name;
	}
	public void setTotal_quantity(int Total_quantity) {
		this.Total_quantity = Total_quantity;
	}
	public void setUsedquantity(int Usedquantity) {
		this.Usedquantity = Usedquantity;
	}

	public void setRatePerMed(int RatePerMed) {
		this.RatePerMed = RatePerMed;
	}

	public void setTotalcost(int Totalcost) {
		this.Totalcost = Totalcost;
		
		
	}
	public Medicine(String medicine_name, int usedquantity, int ratePerMed, int totalcost) {
		super();
		Medicine_name = medicine_name;
		Usedquantity = usedquantity;
		RatePerMed = ratePerMed;
		Totalcost = totalcost;
	}
	public Medicine(String medicine_name, int total_quantity, int ratePerMed) {
		super();
		Medicine_name = medicine_name;
		Total_quantity = total_quantity;
		RatePerMed = ratePerMed;
	}
	
	

}
