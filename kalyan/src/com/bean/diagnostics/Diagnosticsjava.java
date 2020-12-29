package com.bean.diagnostics;

public class Diagnosticsjava {
	String diagnostic;
	int amount;
	
	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Diagnosticsjava(String diagnostic, int amount) {
		super();
		this.diagnostic = diagnostic;
		this.amount = amount;
	}
	

}
