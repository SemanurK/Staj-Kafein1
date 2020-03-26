package com.kafein.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kafein.model.TalepModel;
import com.kafein.model.TalepTablo;
import com.mysql.jdbc.PreparedStatement;

public class TalepDao {
	JdbcTemplate temp;
	List<TalepTablo> listdeneme = new ArrayList<TalepTablo>();

	public void setTemplate(JdbcTemplate template) {
		this.temp = template;
	}

	public List<TalepModel> getTaleps() {
		String sorgu = "select*from kafein.talep as t inner join kafein.kullanici as k on t.talep_sahip=k.kul_id ORDER BY talep_id ASC";
		return temp.query(/* "select * from kafein.talep" */sorgu, new RowMapper<TalepModel>() {
			public TalepModel mapRow(ResultSet rs, int row) throws SQLException {
				TalepModel t = new TalepModel();
				t.setId(rs.getInt(1));
				t.setAdi(rs.getString(2));
				t.setSpring_no(rs.getString(3));
				t.setTarih(rs.getDate(4));
				t.setTalep_sahip(rs.getInt(5));
				t.setDurum(rs.getBoolean(6));
				t.setTalep_id(rs.getInt(7));
				t.setChange_id(rs.getString(8));
				t.setBaslangic_tarihi(rs.getDate(9));
				t.setBitis_tarihi(rs.getDate(10));
				t.setEfor(rs.getString(11));
				t.setAd_soyad(rs.getString(13));
				t.setSahip_gorev(rs.getString(14));
				return t;
			}
		});

	}

	public List<TalepModel> talepFiltre(String deger, String filtre, String durum) {
		deger = deger + "%";
		String sorgu = null;
		if (durum.equals("hepsi")) {
			sorgu = "SELECT * FROM kafein.talep  WHERE " + filtre + " LIKE '" + deger + "' ORDER BY talep_id ASC";
		} else {
			int durum_k = Boolean.parseBoolean(durum) ? 1 : 0;
			sorgu = "SELECT * FROM kafein.talep WHERE " + filtre + " LIKE '" + deger + "' and durum=" + durum_k
					+ " ORDER BY talep_id ASC";
		}

		return temp.query(/* "select * from kafein.talep" */sorgu, new RowMapper<TalepModel>() {
			public TalepModel mapRow(ResultSet rs, int row) throws SQLException {
				TalepModel t = new TalepModel();
				t.setId(rs.getInt(1));
				t.setAdi(rs.getString(2));
				t.setSpring_no(rs.getString(3));
				t.setTarih(rs.getDate(4));
				t.setTalep_sahip(rs.getInt(5));
				t.setDurum(rs.getBoolean(6));
				t.setTalep_id(rs.getInt(7));
				t.setChange_id(rs.getString(8));
				t.setBaslangic_tarihi(rs.getDate(9));
				t.setBitis_tarihi(rs.getDate(10));
				t.setEfor(rs.getString(11));

				return t;
			}
		});
	}

	public TalepModel getId(int id) {
		String sorgu = "select*from kafein.talep as t inner join kafein.kullanici as k on t.talep_sahip=k.kul_id  where t.id=?";
		return temp.queryForObject(sorgu, new Object[] { id }, new BeanPropertyRowMapper<TalepModel>(TalepModel.class));

	}

	public int guncelle(TalepModel t) {
		int durum = t.isDurum() ? 1 : 0;

		String sorgu = "update kafein.talep set adi='" + t.getAdi() + "', talep_id='" + t.getTalep_id()
				+ "', change_id='" + t.getChange_id() + "', spring_no='" + t.getSpring_no() + "', durum='" + durum
				+ "', talep_sahip='" + t.getTalep_sahip() + "', efor='" + t.getEfor() + "'";
		if (t.getTarih() != null) {
			sorgu = sorgu + ",tarih='" + t.getTarih() + "'";
		}

		if (t.getBaslangic_tarihi() != null) {
			sorgu = sorgu + ",baslangic_tarihi='" + t.getBaslangic_tarihi() + "'";
		}
		if (t.getBitis_tarihi() != null) {
			sorgu = sorgu + ",bitis_tarihi='" + t.getBitis_tarihi() + "'";
		}
		sorgu = sorgu + "where id=" + t.getId() + "";
		return temp.update(sorgu);
	}

	public int listkontrol_sprint(List<TalepTablo> list) {
		// List<TalepTablo> temp =list;
		/* Mailden okunan taleplerin sprint numalarýný veritabanýnda kontrol edip ayný sprint numarasýna ait taleplerin listeden silmeye yaranyan kod*/
		List<TalepTablo> temp = new ArrayList<TalepTablo>();

		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}

		int sayac = 0;
		for (int j = 0; j < list.size(); j++) {
			int sprint_no = list.get(j).getSprint();
			int uzunluk = temp.size();
			if (temp.stream().filter(o -> o.getSprint() == sprint_no).findFirst().isPresent()) {
				int sonuc = kontrol_sprint(sprint_no);
				if (sonuc > 0) {
					for (int k = 0; k < uzunluk; k++) {
						if (list.get(sayac).getSprint() == sprint_no) {
							if (sayac == list.size())
								break;
							else {								
								if (temp.size() == 0) {
									break;
								} else {
									TalepTablo x=list.get(sayac);
									temp.remove(x);
									sayac++;
									// temp.remove(k);
								}
							}
						}
					}
				}
			}

		}
//		for (int x = 0; x < temp.size(); x++) {
//			System.out.println(temp.get(x).getSprint());
//		}
		
		if(temp.size()!=0)
		{
			batchInsert(temp);
		}
		return temp.size();
	}

	public int[] batchInsert(final List<TalepTablo> list) {
			

		return this.temp.batchUpdate(
				"insert into kafein.talep (talep_id, adi, efor,durum,talep_sahip,spring_no) values(?,?,?,?,?,?)",
				new BatchPreparedStatementSetter() {

					public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {

						// TODO Auto-generated method stub
						ps.setString(1, list.get(i).getTalepNo());
						ps.setString(2, list.get(i).getTalepAdi());
						ps.setString(3, list.get(i).getEfor());
						ps.setInt(4, 0);
						ps.setInt(5, 1);
						ps.setInt(6, list.get(i).getSprint());

					}

					public int getBatchSize() {
						return list.size();
					}

				});
		
		
	}

	public int kaydet(TalepModel t) {

		if (kontrol(t.getTalep_id()) > 0) {
			return 0;
		} else {
			int durum = t.isDurum() ? 1 : 0;
			String govde = "insert into kafein.talep(talep_id,adi,change_id,spring_no,talep_sahip,durum,efor";
			String values = "values('" + t.getTalep_id() + "','" + t.getAdi() + "','" + t.getChange_id() + "','"
					+ t.getSpring_no() + "','" + t.getTalep_sahip() + "','" + durum + "','" + t.getEfor() + "'";
			String sorgu = null;
			if (t.getTarih() != null) {
				govde += ",tarih";
				values = values + ",'" + t.getTarih() + "'";
			}
			if (t.getBaslangic_tarihi() != null) {
				govde += ",baslangic_tarihi";
				values = values + ",'" + t.getBaslangic_tarihi() + "'";
			}
			if (t.getBitis_tarihi() != null) {
				govde = ",bitis_tarihi";
				values = values + ",'" + t.getBitis_tarihi() + "'";
			}

			sorgu = govde + ")" + values + ")";

			return temp.update(sorgu);
		}

	}

	public int kontrol(int talep_id) {
		String sorgu = "select count(id) from kafein.talep where talep_id = " + talep_id + "";
		int result = temp.queryForObject(sorgu, Integer.class);
		return result;
	}

	public int kontrol_sprint(int sprint_no) {
		String sorgu = "select count(id) from kafein.talep where spring_no = " + sprint_no + "";
		int result = temp.queryForObject(sorgu, Integer.class);
		return result;
	}

}
