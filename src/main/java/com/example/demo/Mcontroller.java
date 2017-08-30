package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins="http://localhost:4200")
@EnableScheduling
@RestController

public class Mcontroller {
	private Logger logger = LoggerFactory.getLogger(MariamApplication.class);

	 // Private fields
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private RepositoryMigration Mrepository;
	  @Autowired
	  private JavaMailSender javaMailSender;
		

public String shell (String svnUrl, String gitUrl)
{     //  shell.command("ls","C:/Users/m.wechtati/angular");
	System.out.println("hello");
	String s = null;
	String s1 = null;
	final String CHEMIN = "C:/Users/m.wechtati/Desktop/";
    String[] commande = {"cmd.exe", "/C", CHEMIN + "script3.sh "+svnUrl+" "+gitUrl};

	try {
	    
	    // run the Unix "ps -ef" command
	        // using the Runtime exec method:
	        Process p = Runtime.getRuntime().exec(commande);
	        
	        BufferedReader stdInput = new BufferedReader(new 
	             InputStreamReader(p.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new 
	             InputStreamReader(p.getErrorStream()));

	        // read the output from the command
	        System.out.println("Here is the standard output of the command:\n");
	        while ((s = stdInput.readLine()) != null) {
	            s1=s1+s;
	            System.out.println(s);
	        }
	        
	        // read any errors from the attempted command
	        System.out.println("Here is the standard error of the command (if any):\n");
	        while ((s = stdError.readLine()) != null) {
	        	 s1=s1+s;
	        	 System.out.println(s);
	        }
	     
	        String content = s1;
	        String path = "C:/Users/m.wechtati/shell";
	       // Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
	        System.out.println(content);
	        System.exit(0);
	    }
	    catch (IOException e) {
	        System.out.println("exception happened - here's what I know: ");
	        e.printStackTrace();
	        System.exit(-1);
	    }
	return s1;
	
	
}
	@RequestMapping(value = "/History")
	@ResponseBody
	public Iterable<migration> getAll() {
	
	return Mrepository.findAll();
	
}
      
	 @RequestMapping("/operation")
	  @ResponseBody 
	  List<migration> findByDate(@RequestBody String date)
	  {
		return  Mrepository.findBydate(date);
		  
	  }
	 
	  @RequestMapping("/migration")
	  @ResponseBody
	  public String Shell(@RequestBody migration migration)
	  {
		 
		
			logger.debug("Added:: " + migration);
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String date1 =dateFormat.format(date);
			migration.setDate(date1);
			Mrepository.save(migration);
		 String svnUrl =migration.getSvn();
		  String gitUrl=migration.getGit();
		  String email=migration.getEmail();
		  SimpleMailMessage mail=new SimpleMailMessage();
		  mail.setTo(email);
		  mail.setFrom("wechtetimariam3@gmail.com");
		  mail.setSubject("a new migration has been started");
		  mail.setText("this migration is about those two urls"+svnUrl+"  "+gitUrl+" " +"at"+date1);
		 try{
		  javaMailSender.send(mail);
		 }catch(MailException e)
		 {
			 logger.info("error Sending Email:"+ e.getMessage());
		 }
	        return shell(svnUrl, gitUrl);}
	  }

