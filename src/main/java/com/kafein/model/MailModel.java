package com.kafein.model;

public class MailModel {
	
	public MailModel(int id,String gonderici,String alici, String konu,String mesaj)
	{
		this.id=id;
		this.alici=alici;
		this.gonderici=gonderici;
		this.konu=konu;
		this.mesaj=mesaj;
	}
	
	private int id;

	private String gonderici,alici,konu,mesaj;

	public String getKonu() {
		return konu;
	}

	public void setKonu(String konu) {
		this.konu = konu;
	}

	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

	public String getGonderici() {
		return gonderici;
	}

	public void setGonderici(String gonderici) {
		this.gonderici = gonderici;
	}

	public String getAlici() {
		return alici;
	}

	public void setAlici(String alici) {
		this.alici = alici;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
