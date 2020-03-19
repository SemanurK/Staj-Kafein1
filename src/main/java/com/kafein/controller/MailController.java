package com.kafein.controller;

import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import java.io.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kafein.model.MailModel;

@Controller
@RequestMapping("/Mail")
public class MailController {

	static List<MailModel> list = new ArrayList<MailModel>();
	static String result = "";

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

			// Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);
			int n = messages.length;

			for (int i = 0; i < n; i++) {

				Message message = messages[i];

				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));

				System.out.println("Subject: " + message.getSubject());

				System.out.println("From: " + message.getFrom()[0]);

				writePart(message);
				list.add(new MailModel(i + 1, message.getFrom()[0].toString(), user, message.getSubject(),result));

				// String line = reader.readLine();
				// if ("YES".equals(line)) {
				// message.writeTo(System.out);
				// } else if ("QUIT".equals(line)) {
				// break;
				// }
				// if (message.isMimeType("text/plain")) {
				// System.out.println("Text: " +
				// message.getContent().toString());
				//
				// } else if (message.isMimeType("multipart/*")) {
				// String result = "";
				// MimeMultipart mimeMultipart = (MimeMultipart)
				// message.getContent();
				//
				// int count = mimeMultipart.getCount();
				// for (int j = 0; j < count; j++) {
				// BodyPart bodyPart = mimeMultipart.getBodyPart(j);
				// if (bodyPart.isMimeType("text/plain")) {
				// result = result + "\n" + bodyPart.getContent();
				// break; // without break same text appears twice in
				// // my tests
				// } else if (bodyPart.isMimeType("text/html")) {
				// String html = (String) bodyPart.getContent();
				// result = result + "\n" + Jsoup.parse(html).text();
				//
				// }
				// else if(bodyPart.isMimeType("multipart/*")){
				//
				// for(int k=0; k<count; k++)
				// {
				// Multipart mp = (Multipart) bodyPart.getContent();
				// if (bodyPart.isMimeType("text/plain")) {
				// result = result + "\n" + bodyPart.getContent();
				// break; // without break same text appears twice in
				// // my tests
				// } else if (bodyPart.isMimeType("text/html")) {
				// String html = (String) bodyPart.getContent();
				// result = result + "\n" + Jsoup.parse(html).text();
				//
				// }
				// }
				// }
				// }
				// System.out.println("Text: " + result);
				//
				// }
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
	public String main(ModelMap model) {

		list.clear();
		String host = "pop.gmail.com";// change accordingly
		String mailStoreType = "imap";
		String username = "kafeinxx8@gmail.com";// change accordingly
		String password = "123456789k.";// change accordingly
		check(host, mailStoreType, username, password);
		model.addAttribute("mailliste", list);
		return "/Mail/index";

	}

	public static void writePart(Part p) throws Exception {
		if (p instanceof Message)
			// Call methos writeEnvelope
			writeEnvelope((Message) p);
	
		System.out.println("----------------------------");
		System.out.println("CONTENT-TYPE: " + p.getContentType());

		// check if the content is plain text
		if (p.isMimeType("text/plain")) {
			System.out.println("This is plain text");
			System.out.println("---------------------------");
			System.out.println((String) p.getContent());
			result = result + "\n" + (String) p.getContent();
			System.out.println("Message : "+result);

		}
		// check if the content has attachment
		else if (p.isMimeType("multipart/*")) {
			System.out.println("This is a Multipart");
			System.out.println("---------------------------");
			Multipart mp = (Multipart) p.getContent();

			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				writePart(mp.getBodyPart(i));
		}
		// check if the content is a nested message
		else if (p.isMimeType("message/rfc822")) {
			System.out.println("This is a Nested Message");
			System.out.println("---------------------------");
			writePart((Part) p.getContent());
		}
		// check if the content is an inline image
		else if (p.getContentType().contains("image/")) {
			System.out.println("content type" + p.getContentType());
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
				System.out.println("This is a string");
				System.out.println("---------------------------");
				System.out.println((String) o);
			} else if (o instanceof InputStream) {
				System.out.println("This is just an input stream");
				System.out.println("---------------------------");
				InputStream is = (InputStream) o;
				is = (InputStream) o;
				int c;
				while ((c = is.read()) != -1)
					System.out.write(c);
			} else {
				System.out.println("This is an unknown type");
				System.out.println("---------------------------");
				System.out.println(o.toString());
			}
		}

	}

	public static void writeEnvelope(Message m) throws Exception {
		System.out.println("This is the message envelope");
		System.out.println("---------------------------");
		Address[] a;

		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("TO: " + a[j].toString());
		}

		// SUBJECT
		if (m.getSubject() != null)
			System.out.println("SUBJECT: " + m.getSubject());

	}

}
