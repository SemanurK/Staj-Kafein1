package com.kafein.model;

public class TalepTablo {
	private String talepNo;
	private String talepAdi;
	private String efor;
	private int sprint;
	public TalepTablo(String talepNo,String talepAdi, String efor,int sprint)
	{
		this.efor=efor;
		this.talepAdi=talepAdi;
		this.talepNo=talepNo;
		this.setSprint(sprint);
	}
	public String getTalepNo() {
		return talepNo;
	}
	public void setTalepNo(String talepNo) {
		this.talepNo = talepNo;
	}
	public String getEfor() {
		return efor;
	}
	public void setEfor(String efor) {
		this.efor = efor;
	}
	public String getTalepAdi() {
		return talepAdi;
	}
	public void setTalepAdi(String talepAdi) {
		this.talepAdi = talepAdi;
	}
	public int getSprint() {
		return sprint;
	}
	public void setSprint(int sprint) {
		this.sprint = sprint;
	}

}
