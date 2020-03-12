package net.sysone.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sysone.app.model.Noticia;
import net.sysone.app.service.INoticiasService;

@Controller
@RequestMapping(value="/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService service;
	
	@GetMapping(value="/index")
	public String index(Model model) {
		List<Noticia> lista = service.buscarTodas();
		model.addAttribute("noticias", lista);
		return "noticias/listNoticias";
	}
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Noticia noticia, Model model) {
		return "noticias/formNoticia";
	}
	
//	@PostMapping(value="/save")
//	public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, @RequestParam("detalle")String detalle) {
//		
//		Noticia noticia = new Noticia();
//		noticia.setTitulo(titulo);
//		noticia.setEstatus(estatus);
//		noticia.setDetalle(detalle);
//		
//		//Pendiente: Guardar el objeto noticia en la BD
//		service.guardar(noticia);
//		
//		return "noticias/formNoticia";
//	}
	
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result, RedirectAttributes attributes) {
		System.out.println("Guardar");
		System.out.println(noticia);
		if(result.hasErrors()) {
			System.out.println("Existieron errores");
			return "noticias/formNoticias";
		}
		//Pendiente: Guardar el objeto noticia en la BD
		service.guardar(noticia);
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/noticias/index";
	}
	
	@GetMapping(value="/update/{id}")
	public String actualizar(@PathVariable("id") int idNoticia, Model model) {
		System.out.println("Actualizando: " + idNoticia);
		Noticia noticia = service.buscarPorId(idNoticia);
		model.addAttribute("noticia", noticia);
		return "noticias/formNoticia";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		System.out.println("Eliminando: " + idNoticia);
		service.eliminar(idNoticia);
		attributes.addFlashAttribute("mensaje", "La noticia fue eliminada.");
		return "redirect:/noticias/index";
	}
}
