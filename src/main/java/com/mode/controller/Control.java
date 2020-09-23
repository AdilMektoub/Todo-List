package com.mode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mode.entities.Task;
import com.mode.entities.User;
import com.mode.repo.TaskRepo;
import com.mode.repo.UserRepo;

@Controller
public class Control {
	
	@Autowired
	TaskRepo tR;
	@Autowired
	UserRepo uR;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model ,Long idUser , Long id_task) {
		
		tR.deleteById(id_task);
		User connectedUser = uR.findById(idUser).get();
		if (connectedUser != null) {
			model.addAttribute("idUser", idUser);
			List<Task> l = tR.findTasksByIdUser(idUser);
			model.addAttribute("listTasks", l);
			model.addAttribute("username", connectedUser.getUsername());
//			return connexion(model, connectedUser.getUsername(), connectedUser.getPassword());
			return "home";
		} else
			return home();
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String accueil(Model model, Long idUser) {
		
		User connectedUser = uR.findById(idUser).get();
		if (connectedUser != null)
			return connexion(model, connectedUser.getUsername(), connectedUser.getPassword());
		else
			return home();
	}
	
	@RequestMapping(value = "/connection", method = RequestMethod.POST)
	public String connexion(Model model,
			@RequestParam(name = "username", defaultValue = "") String username,
			@RequestParam(name = "password", defaultValue = "") String password) {
		
		System.out.println("in connection method");
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		List<User> users = uR.findAll();
		boolean userExist = false;
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				userExist = true;
				model.addAttribute("idUser", user.getId_user());
				model.addAttribute("listTasks", user.getTasks());
			}
		}
		if (userExist)
			return "home";
		else
			return "error";
	}
	
	@RequestMapping(value = "/addTask")
	public String frmNewCmd(Model model, Long idUser) {

		User connectedUser = uR.findById(idUser).get();
		if (connectedUser != null) {

			model.addAttribute("idUser", idUser);

			Task task = new Task();
			task.setUser(connectedUser);

			model.addAttribute("task", task);			

			return "addTask";
		} else
			return home();
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savetask(Model model, Task task, Long idUser, Long id_task) {

		System.out.println("dans le post save");

		User connectedUser = uR.findById(idUser).get();
		
		System.out.println(task.getId_task());
		
		if (connectedUser != null) {

			task.setUser(connectedUser);
			System.out.println(task.toString());
			if(id_task != null) {
				task.setId_task(id_task);
				System.out.println(task.getId_task());
			}
			tR.save(task);
			model.addAttribute("idUser", idUser);
			model.addAttribute("username", connectedUser.getUsername());
			model.addAttribute("task", task);
			return "taskAdded";
			//return login22(model, connectedClient.getNom(), connectedClient.getPassword());
		} else
			return home();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Long idUser, Long id_task) {

		User connectedUser = uR.findById(idUser).get();
		System.out.println(id_task + " dans edit");
		if (connectedUser != null) {
			Task taskToEdit = tR.findById(id_task).get();
			if (taskToEdit != null) {

				model.addAttribute("idUser", idUser);
				model.addAttribute("task", taskToEdit);
				model.addAttribute("id_task", id_task);
				return "addTask";
			} else
				return connexion(model, connectedUser.getUsername(), connectedUser.getPassword());

		} else
			return home();
	}
	
	@RequestMapping(value = "/index" , method = RequestMethod.POST)
	public String index(Model model , 	Long idUser,
	@RequestParam(name = "mc") String mc) {

		User connectedUser = uR.findById(idUser).get();
		
		List<Task> tasks = connectedUser.getTasks();
		if (mc.length() == 0 || mc.equals(null)) {
			model.addAttribute("listTasks", tasks);
		}
		else {
			List<Task> taskTrouves = tR.chercher("%"+mc+"%" , idUser);
			model.addAttribute("listTasks", taskTrouves);
		}
		model.addAttribute("mc", mc);
		model.addAttribute("idUser", idUser);
		model.addAttribute("username", connectedUser.getUsername());
		return "home";
	}

}
