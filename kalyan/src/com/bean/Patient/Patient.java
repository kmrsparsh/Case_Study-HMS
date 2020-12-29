package com.bean.Patient;

public class Patient {
	String patientId;
	int year;
	int month;
	int dates;
	String patientssnid;
	String patientname;
	int patientage;
	String dateofadmission;
	String typeofroom;
	String patientaddress;
	String patientstate;
	String patientcity;
	String status;
	
	//getters
	public String getpatientssnid() {
		return patientssnid;
	}
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
	public String getstatus() {
		return status;
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
	public void setpatientssnid(String patientssnid) {
		this.patientssnid = patientssnid;
	}
	public void setpatientname(String patientname) {
		this.patientname = patientname;
	}
	public void setpatientage(int patientage) {
		this.patientage = patientage;
	}
	public void setdateofadmission(int year, int month, int dates) {
		this.year = year;
		this.month = month;
		this.dates = dates;
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
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public void setstring(String status) {
		this.status = status;
	}


	//constructor
	public Patient(String patientssnid, String patientname, int patientage,String dateofadmission,String typeofroom, String patientaddress, String patientstate, String patientcity,String status) {
		super();
		this.patientssnid = patientssnid;
		this.patientname = patientname;
		this.patientage = patientage;
		this.dateofadmission = dateofadmission;
		this.typeofroom = typeofroom;
		this.patientaddress = patientaddress;
		this.patientstate = patientstate;
		this.patientcity = patientcity;
		this.status = status;
	}


}
