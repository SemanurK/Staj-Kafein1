package com.kafein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kafein.entity.KullaniciDao;

import com.kafein.model.KullaniciModel;




@Controller
@RequestMapping("/Kullanici")
public class KullaniciController {
	@Autowired 
	KullaniciDao dao;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Anasayfa(ModelMap model) {
		List<KullaniciModel> kullanici=dao.getKuls();
		model.addAttribute("kullanici",kullanici);
		return "/Talep/duzenle";
	}
	

	@ResponseBody
	public List<KullaniciModel>  kullist() {
		List<KullaniciModel> kullanici=dao.getKuls();
		return kullanici;
	}

}
