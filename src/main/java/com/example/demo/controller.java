package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:4200")
@EnableScheduling
@Controller
public class controller {

	// Private fields

	// Wire the UserDao used inside this controller.
	@Autowired
	private Repositoryuser userrepository;
	private Logger logger = LoggerFactory.getLogger(MariamApplication.class);

	
	@RequestMapping(value = "/allUsers")
	@ResponseBody
	public Iterable<user> getAll() {
		
		return userrepository.findAll();
		
	}
	
	// -------------------Create a
	// migration--------------------------------------------------------

	@RequestMapping(value = "/create")
	@ResponseBody
	public void CreateUser(@RequestBody user user) {
		System.out.println("Creating user " + user.getUsername());
		userrepository.save(user);
		logger.debug("Added:: " + user);
	}
	
	

	
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Integer id) {
		user mi = userrepository.findOne(id);
		userrepository.delete(mi);
		logger.info("user was deleted successfully ");

	}

}
