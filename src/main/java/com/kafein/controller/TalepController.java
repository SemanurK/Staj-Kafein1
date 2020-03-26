package com.kafein.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kafein.entity.KullaniciDao;
import com.kafein.entity.TalepDao;
import com.kafein.model.KullaniciModel;
import com.kafein.model.TalepModel;

@Controller
@RequestMapping("/Talep")
public class TalepController {

	@Autowired
	TalepDao dao;
	@Autowired
	KullaniciDao kdao;
	List<TalepModel> taleplist;
	List<TalepModel> filtrele;
	List<KullaniciModel> km;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Anasayfa(ModelMap model) {
		taleplist = dao.getTaleps();
		model.addAttribute("taleplist", taleplist);
		return "/Talep/index";
	}

	@RequestMapping(value = "/duzenle")
	public String Talep_Duzenle(@ModelAttribute("id") int id, ModelMap model) {
		TalepModel tm = dao.getId(id);
		model.addAttribute("talepbilgi", tm);
		km = kdao.getKuls();
		model.addAttribute("id", id);
		model.addAttribute("kullaniciBilgi", km);
		return "/Talep/duzenle";
	}

	@RequestMapping(value = "/guncelle", method = RequestMethod.POST)
	public String duzenle(@ModelAttribute("talep") TalepModel talep, ModelMap model, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("/docreatetask in here" + result.getFieldError().getDefaultMessage());
		}

		dao.guncelle(talep);
		model.addAttribute("taleplist", dao.getTaleps());
		return "redirect:/Talep/index";
	}

	@RequestMapping("/yenitalep")
	public String yenitalep(ModelMap model) {
		km = kdao.getKuls();
		model.addAttribute("kullaniciBilgi", km);
		return "/Talep/duzenle";
	}

	@RequestMapping(value = "/talepekle", method = RequestMethod.POST)
	public String talepekle(@ModelAttribute("talep") TalepModel talep, ModelMap model,
			final RedirectAttributes redirectAttributes) {
		int sonuc = dao.kaydet(talep);
		if (sonuc == 0) {
			model.addAttribute("sonuc", sonuc);

			model.addAttribute("kullaniciBilgi", kdao.getKuls());

			return "/Talep/duzenle";
		} else {
			redirectAttributes.addFlashAttribute("sonuc", sonuc);
			return "redirect:/Talep/index";
		}

	}

	@RequestMapping(value = "/filtre", method = RequestMethod.GET)
	// @ResponseBody
	public String duzenle(@RequestParam(value = "deger") String deger, @RequestParam(value = "filtre") String filtre,
			@RequestParam(value = "durum") String durum, ModelMap model) {

		filtrele = dao.talepFiltre(deger, filtre, durum);
		model.addAttribute("deger", deger);
		model.addAttribute("taleplist", filtrele);
		return "/Talep/table";
	}

//	@RequestMapping(value = "/mailkontrol", method = RequestMethod.GET)
//	public String mailkontol(ModelMap model)
//	{
//		MailTalep mt=new MailTalep();
//		mt.main();
//		model.addAttribute("taleplist", dao.getTaleps());
//		return "redirect:/Talep/index";
//	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

}
