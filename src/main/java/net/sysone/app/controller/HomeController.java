package net.sysone.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.sysone.app.model.Banner;
import net.sysone.app.model.Horario;
import net.sysone.app.model.Noticia;
import net.sysone.app.model.Pelicula;
import net.sysone.app.service.IBannersService;
import net.sysone.app.service.IHorariosService;
import net.sysone.app.service.INoticiasService;
import net.sysone.app.service.IPeliculasService;
import net.sysone.app.util.Utileria;

@Controller
public class HomeController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private IBannersService serviceBanners;
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private INoticiasService serviceNoticias;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@GetMapping(value="/about")
	public String acerca() {
		return "acerca";
	}
	
//	Está mapeado en security.xml
	@GetMapping(value="/formLogin")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha") Date fecha, Model model) {
		
		try {
			List<String> listaFechas = Utileria.getNextDays(4);
			System.out.println(listaFechas);
			
			Date fechaSinHora = dateFormat.parse(dateFormat.format(fecha));
			List<Pelicula> peliculas = servicePeliculas.buscarPorFecha(fechaSinHora);
			
			model.addAttribute("peliculas", peliculas);
			model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
			model.addAttribute("fechas", listaFechas);
			System.out.println("Buscando todas las películas en exhibición para la fecha: " + fecha);
		} catch (ParseException e) {
			System.out.println("Error: HomeController.buscar" + e.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		try {
			List<String> listaFechas = Utileria.getNextDays(4);
			System.out.println(listaFechas);
			Date fechaSinHora = dateFormat.parse(dateFormat.format(new Date()));
			List<Pelicula> peliculas = servicePeliculas.buscarPorFecha(fechaSinHora);
			
			model.addAttribute("peliculas", peliculas);
			model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
			model.addAttribute("fechas", listaFechas);
		} catch (ParseException e) {
			System.out.println("Error: HomeController.mostrarPrincipal" + e.getMessage());
		}
		return "home";
	}
	
	@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
		System.out.println("mostrarDetalle");
		
		System.out.println("Buscando horarios para la película: " + idPelicula);
		System.out.println("Para la fecha: " + fecha);
		
		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		return "detalle";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String verDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		System.out.println("verDetalle");
		System.out.println("Buscando horarios para la película: " + idPelicula);
		System.out.println("Para la fecha: " + fecha);
		
		return "detalle";
	}
	
	@ModelAttribute("noticias")
	public List<Noticia> getNoticias() {
		return serviceNoticias.buscarUltimas();
	}
	
	@ModelAttribute("banners")
	public List<Banner> getBanners() {
		return serviceBanners.buscarActivas();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, Boolean.FALSE));
	}
}
