package com.kafein.controller;

import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kafein.entity.TalepDao;
import com.kafein.model.MailModel;
import com.kafein.model.TalepTablo;

@Controller
@RequestMapping("/Mail")
public class MailController {

	static List<MailModel> list = new ArrayList<MailModel>();
	static List<TalepTablo> list2 = new ArrayList<TalepTablo>();
	static String result = "";
	@Autowired
	TalepDao dao;

	public static void check(String host, String storeType, String user, String password) {
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore("imaps");

			store.connect(host, user, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			SearchTerm sender = new FromTerm(new InternetAddress("sema.174k@gmail.com"));
			Message[] messages = emailFolder.search(sender);
			System.out.println("messages.length---" + messages.length);
			int n = messages.length;
			for (int i = 0; i < n; i++) {

				Message message = messages[i];
				writePart(message);
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String main(ModelMap model,final RedirectAttributes redirectAttributes) {

		list.clear();
		String host = "pop.gmail.com";// change accordingly
		String mailStoreType = "imap";
		String username = "mail";// change accordingly
		String password = "sifre";// change accordingly
		check(host, mailStoreType, username, password);
		
			int yeni=dao.listkontrol_sprint(list2);
			model.addAttribute("taleplist", dao.getTaleps());
			redirectAttributes.addFlashAttribute("yenitalepsayisi", yeni);
		
		
		
		return "redirect:/Talep/index";
		

	}

	public static void writePart(Part p) throws Exception {
		// result="";

		if (p.isMimeType("text/plain")) {
			if (result != null) {
				result = "";
				result = result + (String) p.getContent();
			} else
				result = result + (String) p.getContent();

		}
		// check if the content has attachment
		else if (p.isMimeType("multipart/*")) {

			Multipart mp = (Multipart) p.getContent();

			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				writePart(mp.getBodyPart(i));
		}

		else if (p.isMimeType("message/rfc822")) {

			writePart((Part) p.getContent());
		}

		else if (p.getContentType().contains("image/")) {

			File f = new File("image" + new Date().getTime() + ".jpg");
			DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) p.getContent();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = test.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		} else {
			Object o = p.getContent();
			if (o instanceof String) {
				table((String) o);

			}
		}

	}

	public static void table(String html ) {
		Document doc2 = Jsoup.parse(html);
		Elements tableElements = doc2.select("table");
		//String deneme=tableElements.toString();
		if (!tableElements.toString().equals("")) {
			Elements tableRowElements = tableElements.select(":not(thead) tr");
			// Sprint no alma
			String str = result;

			String sprint_sentence = null;
			Pattern p = Pattern.compile("[A-Z](?i)[^.?!]*?\\b(Sprint|Planlama|Raporu)\\b[^.?!]*[.?!]");
			Matcher m = p.matcher(str);
			
				while (m.find()) {
					if (m.group().contains("Planlama")) {
						if (m.group().contains("Raporu")) {
							sprint_sentence = m.group();
							 break;
						}
					}
				}
				
				System.out.println(sprint_sentence.toString());
				String [] sprint_reserve=sprint_sentence.split(" ");
				int sprint_no=Integer.parseInt(sprint_reserve[1]);					

			

			for (int i = 1; i < tableRowElements.size() - 1; i++) {
				Element row = tableRowElements.get(i);
				// System.out.println("row");
				Elements rowItems = row.select("td");
				String rowItems2 = rowItems.select("table").toString();
				if (rowItems2.equals("")) {

					 list2.add(new TalepTablo(rowItems.get(0).text(),
					 rowItems.get(2).text().replace('\'', ' '),
					 rowItems.get(5).text(),sprint_no));
				} else
					break;
				
			}
			 System.out.println();

		}
	}

}
