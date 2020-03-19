package com.kafein.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kafein.model.TalepModel;




public class TalepDao {
	JdbcTemplate temp;

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
	public List<TalepModel> talepFiltre(String deger,String filtre,String durum)
	{
		deger=deger+"%";
		String sorgu=null;
		if(durum.equals("hepsi")){
			sorgu="SELECT * FROM kafein.talep  WHERE "+filtre+" LIKE '"+deger+"' ORDER BY talep_id ASC";
		}else{
			int durum_k=Boolean.parseBoolean(durum)?1:0;
			sorgu="SELECT * FROM kafein.talep WHERE "+filtre+" LIKE '"+deger+"' and durum="+durum+" ORDER BY talep_id ASC";
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

		String sorgu="update kafein.talep set adi='"+t.getAdi()+"', talep_id='" + t.getTalep_id() + "', change_id='" + t.getChange_id() + "', spring_no='" + t.getSpring_no() + "', durum='"+durum+"', talep_sahip='"+t.getTalep_sahip()+"', efor='"+t.getEfor()+"'";
		if(t.getTarih()!=null){
			sorgu=sorgu+",tarih='"+t.getTarih()+"'";
		}
		if(t.getBaslangic_tarihi()!=null){
			sorgu=sorgu+",baslangic_tarihi='"+t.getBaslangic_tarihi()+"'";
		}
		if(t.getBitis_tarihi()!=null){
			sorgu=sorgu+",bitis_tarihi='"+t.getBitis_tarihi()+"'";
		}
		sorgu=sorgu+"where id="	+ t.getId() + "";
		return temp.update(sorgu);
	}
	public int kaydet(TalepModel t) {
		
		if(kontrol(t.getTalep_id())>0){
			return 0;
		}
		else
		{			
		int durum = t.isDurum() ? 1 : 0;
		String govde="insert into kafein.talep(talep_id,adi,change_id,spring_no,talep_sahip,durum,efor";
		String values="values('" + t.getTalep_id() + "','"
				+ t.getAdi() + "','"+t.getChange_id()+"','"+t.getSpring_no()+"','"+t.getTalep_sahip()+"','"+durum+"','"+t.getEfor()+"'";
		String sorgu=null;
			if(t.getTarih()!=null){
				govde+=",tarih";
				values=values+",'"+t.getTarih()+"'";
			}
			if(t.getBaslangic_tarihi()!=null){
				govde+=",baslangic_tarihi";
				values=values+",'"+t.getBaslangic_tarihi()+"'";
			}
			if(t.getBitis_tarihi()!=null){
				govde=",bitis_tarihi";
				values=values+",'"+t.getBitis_tarihi()+"'";
			}
			
			sorgu=govde+")"+values+")";
			
			return temp.update(sorgu);
		}
		
		
		
	}
	public int kontrol(int talep_id)
	{
		String sorgu="select count(id) from kafein.talep where talep_id = "+talep_id+"";
		int result = temp.queryForObject(
			    sorgu, Integer.class);		
		return result;
	}

}
