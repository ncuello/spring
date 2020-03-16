package net.sysone.app.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.realm.GenericPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {
	
	@GetMapping("/index")
	public String mostrarPrincipalAdmin(HttpSession session, Principal principal) {
		if (session.getAttribute("usuario") == null) {
			GenericPrincipal generic = (GenericPrincipal) principal;
			
			for(String s : generic.getRoles()) {
				System.out.println("Rol: " + s);
			}
			
			System.out.println("El usuario tiene el rol editor: " + generic.hasRole("editor"));
			session.setAttribute("usuario", generic);
		}
		
		return "admin";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
}
