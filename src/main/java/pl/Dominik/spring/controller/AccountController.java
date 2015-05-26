package pl.Dominik.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.Dominik.spring.exception.UserAlreadyExistsException;
import pl.Dominik.spring.model.User;
import pl.Dominik.spring.service.UserService;

@Controller
public class AccountController {
	
	private UserService userService;
	private static int pageNumber;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String viewRegistration(Model model){
		model.addAttribute("userForm",new User());
		return "register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String processRegistration(@Valid @ModelAttribute("userForm") User user, BindingResult result, Model model){
		if (result.hasErrors()){
			return "register";
		}
		
		try{
			this.userService.addUser(user);
		}catch(UserAlreadyExistsException e){
			model.addAttribute("notification","Taki u¿ytkownik ju¿ istnieje");
			return "register";
		}
		
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());

		return "registrationSuccess";//przenosi na stronê z potwierdzeniem rejestracji
	}
	
	@RequestMapping(value="/logoutPage",method=RequestMethod.GET)
	public String viewLogout(){
		return "logoutPage";
	}
	
	@RequestMapping(value="/loginPage",method=RequestMethod.GET)
	public String viewLoginForm(Model model){
		model.addAttribute("userForm", new User());
		return "loginPage";
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String error(){
		return "error";
	}
	
	@RequestMapping(value = "/adminuserspage={nr}", method = RequestMethod.GET)
	public String listUsers(@PathVariable("nr") int nr, Model model){
		User user=new User();
		pageNumber=nr;
		boolean hasNextPage=false;
		if(this.userService.getPage(nr+1).size()>0)
			hasNextPage=true;
		
		model.addAttribute("user",user);
		model.addAttribute("pageNumber", nr);
		model.addAttribute("listUsers", this.userService.getPage(nr));
		model.addAttribute("hasNextPage", hasNextPage);
		return "users";
	}
	
	@RequestMapping("/adminRemoveUser/{id}")
	public String removeUser(@PathVariable("id") int id){
			this.userService.removeUser(id);
			if(this.userService.getPage(pageNumber).size()==0)
				pageNumber--;
			return "redirect:/adminuserspage="+pageNumber;

	}
	
	@RequestMapping("/adminEditUser{id}page{nr}" )
	public String editUser(@PathVariable("id")int id,@PathVariable("nr")int nr, Model model){
		model.addAttribute("user",this.userService.getUserById(id));
		model.addAttribute("pageNumber", nr);
		model.addAttribute("listUsers", this.userService.getPage(nr));
		return "users";
	}
	
	
	@RequestMapping(value = "/adminUpdateUser", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model){
		if(!result.hasErrors())
			this.userService.updateUser(user);
		return "redirect:/adminuserspage="+pageNumber;
	}

}
