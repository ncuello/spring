package net.sysone.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sysone.app.model.Banner;
import net.sysone.app.service.IBannersService;
import net.sysone.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannerController {
	
	@Autowired
	private IBannersService service;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Banner> listaBanners = service.buscarTodos();
		
		model.addAttribute("banners", listaBanners);
//		comentario
		
		return "banners/listBanners";
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Banner banner, Model model) {
		return "banners/formBanner";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			System.out.println("Existieron errores");
			return "banners/formBanner";
		}
		
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			System.out.println("Nombre de la imagen del banner " + nombreImagen);
			banner.setArchivo(nombreImagen);
		}
		
		System.out.println("Recibiendo objeto " + banner);
		
		System.out.println("Elementos en la lista antes de la inserción: " + service.buscarTodos().size());
		service.insertar(banner);
		System.out.println("Elementos en la lista después de la inserción: " + service.buscarTodos().size());
		
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/banners/index";
	}
	
	@GetMapping(value="/update/{id}")
	public String editar(@PathVariable("id") int idBanner, Model model) {
		System.out.println("Editando banner: " + idBanner);
		Banner banner = service.buscarPorId(idBanner);
		model.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		System.out.println("Eliminando banner: " + idBanner);
		service.eliminar(idBanner);
		attributes.addFlashAttribute("mensaje", "El banner fue eliminado.");
		return "redirect:/banners/index";
	}
}
