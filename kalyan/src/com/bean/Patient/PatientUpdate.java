package com.bean.Patient;

public class PatientUpdate {

	int year;
	int month;
	int dates;
	String patientname;
	int patientage;
	String dateofadmission;
	String typeofroom;
	String patientaddress;
	String patientstate;
	String patientcity;
	//getters
	public String getpatientname() {
		return patientname;
	}
	public int getpatientage() {
		return patientage;
	}
	public String getdateofadmission() {
		return dateofadmission;
	}
	public String gettypeofroom() {
		return typeofroom;
	}
	public String getpatientaddress() {
		return patientaddress;
	}
	public String getpatientstate() {
		return patientstate;
	}
	public String getpatientcity() {
		return patientcity;
	}
	//setters
	public void setpatientname(String patientname) {
		this.patientname = patientname;
	}
	public void setpatientage(int patientage) {
		this.patientage = patientage;
	}
	public void setdateofadmission(String dateofadmission) {
		this.dateofadmission = dateofadmission;
		
	}
	public void settypeofroom(String typeofroom) {
		this.typeofroom = typeofroom;
	}
	public void setpatientaddress(String patientaddress) {
		this.patientaddress = patientaddress;
	}
	public void setpatientstate(String patientstate) {
		this.patientstate = patientstate;
	}
	public void setpatientcity(String patientcity) {
		this.patientcity = patientcity;
	}
	//constructor
	
	public PatientUpdate(String patientname, int patientage,String dateofadmission,String typeofroom, String patientaddress, String patientstate, String patientcity) {
		super();
		this.patientname = patientname;
		this.patientage = patientage;
		this.dateofadmission = dateofadmission;
		this.typeofroom = typeofroom;
		this.patientaddress = patientaddress;
		this.patientstate = patientstate;
		this.patientcity = patientcity;
	}

}
