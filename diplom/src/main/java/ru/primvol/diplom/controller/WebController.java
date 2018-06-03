package ru.primvol.diplom.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.primvol.diplom.collection.DatesCollection;
import ru.primvol.diplom.collection.EventsCollection;
import ru.primvol.diplom.collection.ListVolCollection;
import ru.primvol.diplom.model.Dates;
import ru.primvol.diplom.model.Event;
import ru.primvol.diplom.model.ListVol;
import ru.primvol.diplom.model.User;
import ru.primvol.diplom.repo.EventRepository;
import ru.primvol.diplom.repo.ListVolRepository;
import ru.primvol.diplom.repo.UserRepository;
import ru.primvol.diplom.repo.DatesRepository;

@Controller
public class WebController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	DatesRepository datesRepository;
	
	@Autowired
	ListVolRepository listVolRepository;
	
	private User activeUser = new User("", "", "", "");
	
	// Контроллер для обработки главной страницы
	
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
		 	if (userRepository.findByEmail(email).size() == 1) {
		 		User test = userRepository.findByEmail(email).get(0);
		 		if (test.getPass().equals(pass)) {
		 			this.activeUser = userRepository.findByEmail(email).get(0);
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
	 
	 @RequestMapping(value="/exit")
	 public String exitFromIndex(Model model) {
		 this.activeUser = new User("", "", "", "");
		 User autoUser = new User("", "");
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("user", autoUser);
		return "index";
	 }
	 
	 //Конец контроллера главной страницы
	 
	 //Контроллер "мероприятий"
	
	 @RequestMapping("/events")
		public String events(Model model) {
		 	List<Event> list = new ArrayList<Event>();
		 	for (Event e : eventRepository.findAll()) {
		 		list.add(e);
		 	}
		 
		 	model.addAttribute("activeUser", this.activeUser);
		 	model.addAttribute("events", list);
			return "events";
		}
	 
	 @RequestMapping("/createEvent")
		public String createEvent(Model model) {
		 	Event event = new Event("", 0, 10, "", "");
		 	event.setIdCoord(this.activeUser.getId());
		 	model.addAttribute("event", event);
		 	model.addAttribute("activeUser", this.activeUser);
			return "createEvent";
		}
	 
	@RequestMapping(value = "/success", method = RequestMethod.POST)
		public String createEvent(@ModelAttribute Event event, Model model) {
			eventRepository.save(event);
		 	List<Event> list = new ArrayList<Event>();
		 	for (Event e : eventRepository.findAll()) {
		 		list.add(e);
		 	}
		 	model.addAttribute("activeUser", this.activeUser);
		 	model.addAttribute("events", list);
			return "events";
	}
	
	//Конец контроллера "мероприятий"
	//Изменение "мероприятий"
	
	@RequestMapping("/editEvent")
	public String editEvent(Model model, @RequestParam("id") long id) {
		Event event = eventRepository.findById(id).get();
	 	event.setIdCoord(this.activeUser.getId());
	 	model.addAttribute("event", event);
	 	model.addAttribute("activeUser", this.activeUser);
		return "editEvent";
	}
	
	@RequestMapping("/addDates")
	public String addDates(Model model, @RequestParam("id") long id) {
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(id)) {
	 		list.add(e);
	 	}
	 	Event event = eventRepository.findById(id).get();
	 	Dates newDate = new Dates(id, new Date(), "", "");
	 	list.add(newDate);
	 	DatesCollection collection = new DatesCollection();
	 	collection.setDates(list);
	 	model.addAttribute("event", event);
	 	model.addAttribute("Dates", collection);
		return "addDates";
	}
	
	@RequestMapping("/addDate")
	public String addDate(@ModelAttribute DatesCollection Dates, Model model, @RequestParam("id") long id) {
		List<Dates> list = Dates.getDates();
		Dates newDate = new Dates(id, new Date(), "", "");
		list.add(newDate);
	 	Event event = eventRepository.findById(id).get();
	 	DatesCollection collection = new DatesCollection();
	 	collection.setDates(list);
	 	model.addAttribute("event", event);
	 	model.addAttribute("Dates", collection);
		return "addDates";
	}
	
	@RequestMapping("/removeDate")
	public String removeDate(Model model, @RequestParam("idDate") long idDate, @RequestParam("idEvent") long idEvent) {
		List<Dates> list = new ArrayList<Dates>();
		datesRepository.deleteById(idDate);
	 	for (Dates e : datesRepository.findByIdEvent(idEvent)) {
	 		list.add(e);
	 	}
	 	Event event = eventRepository.findById(idEvent).get();
	 	DatesCollection collection = new DatesCollection();
	 	collection.setDates(list);
	 	model.addAttribute("event", event);
	 	model.addAttribute("Dates", collection);
		return "addDates";
	}
	
	@RequestMapping(value = "/newDates", method = RequestMethod.POST)
	public String newDates(@ModelAttribute DatesCollection Dates, Model model) {
		for (int i = 0; i<Dates.getDates().size(); i++) {
			System.out.println("yes"+i);
		}
	 	List<Event> list = new ArrayList<Event>();
	 	for (Event e : eventRepository.findAll()) {
	 		list.add(e);
	 	}
	 	model.addAttribute("activeUser", this.activeUser);
	 	model.addAttribute("events", list);
		return "events";
	}
	
	//конец изменения мероприятий
	
	//регистрация и авторизация
	
	@RequestMapping("/registration") 
	public String registrationForm(Model model) {
		User user = new User("","","","");
		model.addAttribute("user", user);
		return "registrationForm";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String regUser(@ModelAttribute User user, Model model) {
		userRepository.save(user);
	 	this.activeUser = user;
	 	model.addAttribute("activeUser", this.activeUser);
		return "index";
	}
	
	@RequestMapping("/loginForm") 
	public String loginForm(Model model) {
		User user = new User("","","","");
		model.addAttribute("user", user);
		return "loginForm";
	}
	
	//конец авторизации и регистрации
	
	//контроллер заявок и ответов
	
	@RequestMapping("/show") 
	public String showEvent(Model model, @RequestParam("id") long id) {
		Event event = eventRepository.findById(id).get();
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(id)) {
	 		list.add(e);
	 	}
	 	String message = new String("");
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("dates", list);
		model.addAttribute("event", event);
		model.addAttribute("message", message);
		return "show";
	}
	
	@RequestMapping("/takeMe")
	public String takeUserToEvent(Model model, @RequestParam("idEvent") long idEvent, @RequestParam("idUser") long idUser) {
		ListVol listVol = new ListVol(idUser, idEvent);
	 	listVolRepository.save(listVol);
	 	Event event = eventRepository.findById(idEvent).get();
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(idEvent)) {
	 		list.add(e);
	 	}
	 	String message = new String("Ваша заявка принята! Ждите ответа!");
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("dates", list);
		model.addAttribute("event", event);
		model.addAttribute("message", message);
		return "show";
	}
	
	@RequestMapping("/answers") 
	public String answers(Model model, @RequestParam("id") long id) {
		Event event = eventRepository.findById(id).get();
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(id)) {
	 		list.add(e);
	 	}
	 	List<User> vols = new ArrayList<User>();
	 	List<ListVol> listVol = new ArrayList<ListVol>();
	 	for (ListVol e : listVolRepository.findByIdEvent(id)) {
	 		long idVol = e.getIdVol();
	 		listVol.add(e);
	 		User v = userRepository.findById(idVol).get();
	 		vols.add(v);
	 	}
	 	ListVolCollection listVolCol = new ListVolCollection();
	 	listVolCol.setListVol(listVol);
	 	model.addAttribute("listVol", listVolCol);
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("dates", list);
		model.addAttribute("event", event);
		model.addAttribute("vols", vols);
		return "answers";
	}
	
	@RequestMapping(value = "/saveList", method = RequestMethod.POST) 
	public String saveList(@ModelAttribute ListVolCollection listVol, @RequestParam("id") long id, Model model) {
		for (int i = 0; i<listVol.getListVol().size(); i++) {
			listVolRepository.save(listVol.getListVol().get(i));
		}
		Event event = eventRepository.findById(id).get();
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(id)) {
	 		list.add(e);
	 	}
	 	List<User> vols = new ArrayList<User>();
	 	List<ListVol> listVolNew = new ArrayList<ListVol>();
	 	for (ListVol e : listVolRepository.findByIdEvent(id)) {
	 		long idVol = e.getIdVol();
	 		listVolNew.add(e);
	 		User v = userRepository.findById(idVol).get();
	 		vols.add(v);
	 	}
	 	ListVolCollection listVolCol = new ListVolCollection();
	 	listVolCol.setListVol(listVolNew);
	 	model.addAttribute("listVol", listVolCol);
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("dates", list);
		model.addAttribute("event", event);
		model.addAttribute("vols", vols);
		return "answers";
	}
	
	//контроллер отчётов
	
	@RequestMapping("/report") 
	public String report(@RequestParam("id") long id, Model model) {
		
		return "report";
	}
	
	
	@RequestMapping("/save")
	public String process() {
		
		
		return "Done!";
	}
	
	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		
		for(User vol : userRepository.findAll()){
			result += vol.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = userRepository.findById(id).toString();
		return result;
	}
	
	@RequestMapping("/findbysecondname")
	public String fetchDataByLastName(Model model){
		String result = "";
		
		result = userRepository.findByEmail("barankotar@gmail.com").get(0).getPass();
		model.addAttribute("myObject", result);
		return "403";
	}

}
