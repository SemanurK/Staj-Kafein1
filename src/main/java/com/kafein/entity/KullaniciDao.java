package com.kafein.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kafein.model.KullaniciModel;

public class KullaniciDao {

	JdbcTemplate temp;

	public void setTemplate(JdbcTemplate template) {
		this.temp = template;
	}

	public List<KullaniciModel> getKuls() {
		String sorgu = "select*from kafein.kullanici";
		return temp.query(/* "select * from kafein.talep" */sorgu, new RowMapper<KullaniciModel>() {
			public KullaniciModel mapRow(ResultSet rs, int row) throws SQLException {
				KullaniciModel k = new KullaniciModel();
				k.setKul_id(rs.getInt(1));
				k.setKul_ad_soyad(rs.getString(2));
				k.setKul_gorev(rs.getString(3));

				return k;
			}
		});

	}
}
