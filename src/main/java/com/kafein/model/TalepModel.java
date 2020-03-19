package com.kafein.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

public class TalepModel {
	private int id;
	private String adi;
	
	private String spring_no;

	private Date tarih;

	private String ad_soyad;
	private String sahip_gorev;
	private int talep_id;

	@Nullable
	private String change_id;

	private boolean durum;
	private int talep_sahip;

	// @DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date baslangic_tarihi;

	// @DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date bitis_tarihi;

	private String efor;

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpring_no() {
		return spring_no;
	}

	public void setSpring_no(String spring_no) {
		this.spring_no = spring_no;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	/*
	 * public String getTalepsahip_ad() { return talepsahip_ad; } public void
	 * setTalepsahip_ad(String talepsahip_ad) { this.talepsahip_ad =
	 * talepsahip_ad; } public int getTalepsahip_id() { return talepsahip_id; }
	 * public void setTalepsahip_id(int talepsahip_id) { this.talepsahip_id =
	 * talepsahip_id; }
	 */
	public boolean isDurum() {
		return durum;
	}

	public void setDurum(boolean durum) {
		this.durum = durum;
	}

	public String getChange_id() {
		return change_id;
	}

	public void setChange_id(String change_id) {
		this.change_id = change_id;
	}

	public int getTalep_id() {
		return talep_id;
	}

	public void setTalep_id(int talep_id) {
		this.talep_id = talep_id;
	}

	public String getSahip_gorev() {
		return sahip_gorev;
	}

	public void setSahip_gorev(String sahip_gorev) {
		this.sahip_gorev = sahip_gorev;
	}

	public String getAd_soyad() {
		return ad_soyad;
	}

	public void setAd_soyad(String ad_soyad) {
		this.ad_soyad = ad_soyad;
	}

	public int getTalep_sahip() {
		return talep_sahip;
	}

	public void setTalep_sahip(int talep_sahip) {
		this.talep_sahip = talep_sahip;
	}

	public Date getBaslangic_tarihi() {
		return baslangic_tarihi;
	}

	public void setBaslangic_tarihi(Date baslangic_tarihi) {
		this.baslangic_tarihi = baslangic_tarihi;
	}

	public Date getBitis_tarihi() {
		return bitis_tarihi;
	}

	public void setBitis_tarihi(Date bitis_tarihi) {
		this.bitis_tarihi = bitis_tarihi;
	}

	public String getEfor() {
		return efor;
	}

	public void setEfor(String efor) {
		this.efor = efor;
	}

}
