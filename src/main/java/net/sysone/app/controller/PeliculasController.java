package net.sysone.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sysone.app.model.Detalle;
import net.sysone.app.model.Pelicula;
import net.sysone.app.service.IDetallesService;
import net.sysone.app.service.IPeliculasService;
import net.sysone.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService service;
	
	@Autowired
	private IDetallesService serviceDetalles;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = service.buscarTodas();
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> lista = service.buscarTodas(page);
		System.out.println(page.getPageSize());
		System.out.println(lista.getTotalPages());
		model.addAttribute("hasPrevious", lista.hasPrevious());
		model.addAttribute("hasNext", lista.hasNext());
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		return "peliculas/formPelicula";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes, 
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		if(result.hasErrors()) {
			System.out.println("Existieron errores");
			return "peliculas/formPelicula";
		}
		
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			System.out.println("Nombre de imagen: " + nombreImagen);
			pelicula.setImagen(nombreImagen);
		}
		
		System.out.println("Recibiendo objeto película: " + pelicula);
		
		// TODO Ahora guardo antes de la película el Detalle, Spring lo debería hacer solo. INVESTIGAR!!!
		Detalle detalle = pelicula.getDetalle();
		System.out.println("Antes: " + detalle);
		serviceDetalles.insertar(detalle);
		System.out.println("Después: " + detalle);
		
//		System.out.println("Elementos en la lista antes de la inserción: " + service.buscarTodas().size());
		service.insertar(pelicula);
//		System.out.println("Elementos en la lista después de la inserción: " + service.buscarTodas().size());
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
//		return "peliculas/formPelicula";
//		return "peliculas/listPeliculas";
//		return "redirect:/peliculas/index"; 
		return "redirect:/peliculas/indexPaginate?page=0"; 
		/**
		 * al hacer un POST se debe realizar un nuevo ciclo request-response hacia el servidor. 
		 * Por eso no se renderiza la vista.
		 * Conceptos FORWARD - REDIRECT.
		 * Se hace el Redirect para luego renderizar la vista con la información actualizada.
		 */
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model) {
		Pelicula pelicula = service.buscarPorId(idPelicula);
		model.addAttribute("pelicula", pelicula);
		return "peliculas/formPelicula";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
		// Busco película para obtener id de Detalle 
		Pelicula pelicula = service.buscarPorId(idPelicula);
		// Elimino película
		service.eliminar(idPelicula);
		// Elimino detalle
		serviceDetalles.eliminar(pelicula.getDetalle().getId());
		attributes.addFlashAttribute("mensaje", "La película fue eliminada.");
		return "redirect:/peliculas/index";
	}
	
	/**
	 * Se va a ejecutar para todos los métodos del controlador.
	 * @return
	 */
	@ModelAttribute("generos")
	public List<String> getGeneros() {
		return service.buscarGeneros();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, Boolean.FALSE));
	}

}
