package ru.primvol.diplom.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.docx4j.jaxb.Context;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
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
	 	if (list.size() == 0) {
	 		Dates newDate = new Dates(id, new Date(), "", "");
	 		datesRepository.save(newDate);
		 	list.add(newDate);
	 	}
	 	Event event = eventRepository.findById(id).get();
	 	DatesCollection collection = new DatesCollection();
	 	collection.setDates(list);
	 	model.addAttribute("event", event);
	 	model.addAttribute("Dates", collection);
		return "addDates";
	}
	
	@RequestMapping("/addDate")
	public String addDate(Model model, @RequestParam("id") long id) {
		Dates newDate = new Dates(id, new Date(), "", "");
 		datesRepository.save(newDate);
		List<Dates> list = new ArrayList<Dates>();
	 	for (Dates e : datesRepository.findByIdEvent(id)) {
	 		list.add(e);
	 	}
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
			datesRepository.save(Dates.getDates().get(i));
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
		List<ListVol> listVol = new ArrayList<ListVol>();
		List<User> users = new ArrayList<User>();
		Event event = eventRepository.findById(id).get();
		for (ListVol e : listVolRepository.findByIdEventAndStatus(id, 2)) {
			listVol.add(e);
			users.add(userRepository.findById(e.getIdVol()).get());
		}
		ListVolCollection listVolCol = new ListVolCollection();
		listVolCol.setListVol(listVol);
		model.addAttribute("listVol", listVolCol);
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("event", event);
		model.addAttribute("vols", users);	
		return "report";
		
	}
	@RequestMapping(value = "/createReport", method = RequestMethod.POST) 
	public String createReport(@RequestParam("id") long id, Model model, @ModelAttribute ListVolCollection listVol) throws Docx4JException {
		//добавление часов
		for (int i = 0; i<listVol.getListVol().size(); i++) {
			listVolRepository.save(listVol.getListVol().get(i));
		}
		//создание модели
		List<ListVol> listVolNew = new ArrayList<ListVol>();
		List<User> users = new ArrayList<User>();
		Event event = eventRepository.findById(id).get();
		for (ListVol e : listVolRepository.findByIdEventAndStatus(id, 2)) {
			listVolNew.add(e);
			users.add(userRepository.findById(e.getIdVol()).get());
		}
		ListVolCollection listVolCol = new ListVolCollection();
		listVolCol.setListVol(listVolNew);
		model.addAttribute("listVol", listVolCol);
		model.addAttribute("activeUser", this.activeUser);
		model.addAttribute("event", event);
		model.addAttribute("vols", users);	
		
		// создание документа
		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
		MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
		mainDocumentPart.addStyledParagraphOfText("Title", event.getNameOfEvent());
		
		ObjectFactory factory = Context.getWmlObjectFactory();
		
		P p = factory.createP(); //абзац
		
		//строка даты жирная
		R dateOf = factory.createR();
		Text dateText = factory.createText();
		dateText.setValue("Дата(ы) работы: ");
		dateText.setSpace("preserve");
		dateOf.getContent().add(dateText);
		p.getContent().add(dateOf);
		RPr rpr = factory.createRPr();       
		BooleanDefaultTrue b = new BooleanDefaultTrue();
		rpr.setB(b);
		dateOf.setRPr(rpr);
		// строка даты продолжение
		R dateOfNext = factory.createR();
		Text dateTextNext = factory.createText();
		
		String stringDates = " ";
		for (Dates e : datesRepository.findByIdEvent(id)) {
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			stringDates +=  df.format(e.getDateOf()) + ", ";
		}
		
		dateTextNext.setValue(stringDates);
		dateOfNext.getContent().add(dateTextNext);
		p.getContent().add(dateOfNext);
		mainDocumentPart.getContent().add(p);
		//строка координатора жирная
		P p2 = factory.createP();
		R coord = factory.createR();
		Text coordText = factory.createText();
		coordText.setValue("Координатор: ");
		coordText.setSpace("preserve");
		coord.getContent().add(coordText);
		p2.getContent().add(coord);
		rpr.setB(b);
		coord.setRPr(rpr);
		//строка координатора продолжение
		R coordNext = factory.createR();
		Text coordTextNext = factory.createText();
		String stringCoord = " ";
		User coordUser = new User("", "");
		coordUser = userRepository.findById(event.getIdCoord()).get();
		stringCoord += coordUser.getSecondName() + " " + coordUser.getFirstName() + " " + coordUser.getThirdName();
		coordTextNext.setValue(stringCoord);
		coordNext.getContent().add(coordTextNext);
		p2.getContent().add(coordNext);
		mainDocumentPart.getContent().add(p2);
		
		//строка количества жирная
		P p3 = factory.createP();
		R number = factory.createR();
		Text numberText = factory.createText();
		numberText.setValue("Количество волонтёров: ");
		numberText.setSpace("preserve");
		number.getContent().add(numberText);
		p3.getContent().add(number);
		rpr.setB(b);
		number.setRPr(rpr);
		// строка количества продолжение
		R numberNext = factory.createR();
		Text numberTextNext = factory.createText();
		String stringNumber = Integer.toString(event.getNumberOfVol());
		numberTextNext.setValue(stringNumber);
		numberNext.getContent().add(numberTextNext);
		p3.getContent().add(numberNext);
		mainDocumentPart.getContent().add(p3);
		
		
		int writableWidthTwips = wordPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
		int columnNumber = 5;
		Tbl tbl = TblFactory.createTable(listVolNew.size()+1, 5, writableWidthTwips/columnNumber);     
		List<Object> rows = tbl.getContent();
		int k = 0; //строки
		for (Object row : rows) {
			int i = 0; //колонки
		    Tr tr = (Tr) row;
		    List<Object> cells = tr.getContent();
			for(Object cell : cells) {
				Tc td = (Tc) cell;
				String string = "";
				switch(i) {
				case 0: if (k==0) {
					string = "";
				}
				else {
					string = Integer.toString(k);
				}
				break;
				case 1: if (k==0) {
					string = "ФИО";
				}
				else {
					string = users.get(k-1).getSecondName() + " " + users.get(k-1).getFirstName() + " " + users.get(k-1).getThirdName();
				}
				break;
				case 2: if (k==0) { 
					string = "Email";
				}
				else {
					string = users.get(k-1).getEmail();
				}
				break;
				case 3: if (k==0) {
					string = "Телефон";
				}
				else {
					string = users.get(k-1).getPhone();
				}
				break;
				case 4: if (k==0) {
					string = "Часы";
				}
				else {
					string = Integer.toString(listVolNew.get(k-1).getHours());
				}
				break;
				}
				Text field = factory.createText();
				field.setValue(string);
				P parag = factory.createP();
				R run = factory.createR();
				run.getContent().add(field);
				parag.getContent().add(run);
				td.getContent().add(parag);
				i++;
			}
			k++;
		}
		mainDocumentPart.addObject(tbl);
		
		File exportFile = new File("welcome.docx");
		wordPackage.save(exportFile);
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
