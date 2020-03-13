package net.sysone.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sysone.app.model.Perfil;
import net.sysone.app.model.Usuario;
import net.sysone.app.service.IPerfilesService;
import net.sysone.app.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private static final int activo = 1;
	
	private static final int inactivo = 0;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private IUsuariosService service;
	
	@Autowired
	private IPerfilesService servicePerfiles;
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Usuario usuario) {
		return "usuarios/formUsuario";
	}
	
	@GetMapping("/index")
	public String index() {
		return "usuarios/listUsuarios";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Usuario usuario, @RequestParam("perfil") String perfil) {
		System.out.println("Usuario: " + usuario);
		System.out.println("Perfil: " + perfil);
		
		String passwordTemporal = usuario.getPwd();
		String passwordEncriptada = encoder.encode(passwordTemporal);
		usuario.setPwd(passwordEncriptada);
		usuario.setActivo(activo);
		service.guardar(usuario);
		
		Perfil perfilTemporal = new Perfil(usuario.getCuenta(), perfil);
		
		servicePerfiles.guardar(perfilTemporal);
		
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/demo-bcrypt")
	public String pruebaBcrypt() {
		String password = "mari123";
		String encriptado = encoder.encode(password);
		System.out.println("Password encriptado: " + encriptado);
		return "usuarios/demo";
	}
	

}
