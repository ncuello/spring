package net.sysone.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sysone.app.model.Horario;
import net.sysone.app.model.Pelicula;
import net.sysone.app.service.IHorariosService;
import net.sysone.app.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {
	
	@Autowired
	private IPeliculasService peliculasService;
	
	@Autowired
	private IHorariosService service;
	
	@GetMapping(value="/index")
	public String index(Model model) {
		List<Horario> lista = service.buscarTodas();
		model.addAttribute("horarios", lista);
		return "horarios/listHorarios";
	}
	
	@GetMapping(value="/indexPaginate")
	public String indexPaginado(Model model, Pageable page) {
		Page<Horario> lista = service.buscarTodas(page);
		System.out.println(page.getPageSize());
		System.out.println(lista.getTotalPages());
		model.addAttribute("hasPrevious", lista.hasPrevious());
		model.addAttribute("hasNext", lista.hasNext());
		model.addAttribute("horarios", lista);
		return "horarios/listHorarios";
	}
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Horario horario) {
		return "horarios/formHorario";
	}

	/**
	 * Método para guardar el registro del horario
	 * @return
	 */
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, RedirectAttributes attributes) {
		/**
		 * Verificar si hay errores en el Data Binding
		 * En caso de no haber errores, imprimir en consola el objeto de model Horario
		 * que ya deberá de tener los datos del formulario
		 * 
		 * De momento hacemos un redirect al mismo formulario
		 */
		System.out.println(horario);
		if(result.hasErrors()) {
			System.out.println("Existieron errores");
			return "horarios/formHorario";
		}
		
		service.insertar(horario);
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/horarios/indexPaginate?page=0";
	}
	
	@GetMapping(value="/update/{id}")
	public String actualizar(@PathVariable("id")int idHorario, Model model) {
		System.out.println("Actualizando: " + idHorario);
		Horario horario = service.buscarPorId(idHorario);
		model.addAttribute("horario", horario);
		return "horarios/formHorario";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
		System.out.println("Eliminando: " + idHorario);
		service.eliminar(idHorario);
		attributes.addFlashAttribute("mensaje", "El horario fue eliminado.");
		return "redirect:/horarios/index";
	}
	
	/**
	 * Agregamos al modelo, el listado de peliculas para que este disponible
	 * para todos los metodos de este controlador
	 * @return
	 */
	@ModelAttribute("peliculas")
	public List<Pelicula> getPeliculas(){
		return peliculasService.buscarActivas();
	}
	
	//Personalizamos el Data Binding para todas las propiedades de tipo Date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, Boolean.FALSE));
	}
}
