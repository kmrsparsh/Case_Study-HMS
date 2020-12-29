package com.bean.service;

import com.bean.PatientRegistrationDao.PatientRegistrationDao;
import com.bean.Patient.Patient;
import java.sql.SQLException;

public class PatientRegistrationService {

public static int addPatient(Patient newPatient, String token) throws SQLException {
	
		
		return PatientRegistrationDao.addPatient(newPatient,token);
	}
	
}
