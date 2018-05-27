package ru.primvol.diplom.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.primvol.diplom.model.User;
import ru.primvol.diplom.repo.UserRepository;

@Controller
public class WebController {
	
	@Autowired
	UserRepository repository;
	
	private User activeUser = new User("test", "firstName", "secondName", "pass");
	
	
	@RequestMapping("/")
	public String index(Model model) {
		User autoUser = new User("", "");
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("user", autoUser);
		return "index";
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String saveStudent(@ModelAttribute User user, Model model) {
		 	String email = user.getEmail();
		 	String pass = user.getPass();
		 	if (repository.findByEmail(email).size() == 1) {
		 		User test = repository.findByEmail(email).get(0);
		 		if (test.getPass().equals(pass)) {
		 			this.activeUser = repository.findByEmail(email).get(0);
		 			model.addAttribute("activeUser", this.activeUser);
		 			model.addAttribute("user", this.activeUser);
		 			return "index";
		 		}
		 	}
		 	User autoUser = new User("", "");
		 	model.addAttribute("activeUser", this.activeUser);
			model.addAttribute("user", autoUser); 	
	        return "index";
	    }

	
	@RequestMapping("/save")
	public String process() {
		
		
		return "Done!";
	}
	
	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		
		for(User vol : repository.findAll()){
			result += vol.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findById(id).toString();
		return result;
	}
	
	@RequestMapping("/findbysecondname")
	public String fetchDataByLastName(Model model){
		String result = "";
		
		result = repository.findByEmail("barankotar@gmail.com").get(0).getPass();
		model.addAttribute("myObject", result);
		return "403";
	}

}
