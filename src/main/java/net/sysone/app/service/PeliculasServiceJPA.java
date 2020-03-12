package net.sysone.app.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Horario;
import net.sysone.app.model.Pelicula;
import net.sysone.app.repository.HorariosRepository;
import net.sysone.app.repository.PeliculasRepository;

@Service
@Primary // Sirve para que en Home Controller cuando se instancie IPeliculasService, al tener 2 clases que implementan esa Interface, la que tiene prioridad es ésta. Se soluciona con esta etiqueta o sacando el @Service de la otra clase que implementa la Interface.
public class PeliculasServiceJPA implements IPeliculasService {
	
	@Autowired
	private PeliculasRepository repository;
	
	@Autowired 
	private HorariosRepository horariosRepository;

	@Override
	public void insertar(Pelicula pelicula) {
		repository.save(pelicula);
		
	}

	@Override
	public List<Pelicula> buscarTodas() {
		return repository.findAll();
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		Optional<Pelicula> optional = repository.findById(idPelicula);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	/**
	 * Método que regresa una lista de los géneros de películas
	 */
	@Override
	public List<String> buscarGeneros() {
		// Nota: Esta lista pordía ser obtenida de una BD
		List<String> generos = new LinkedList<>();
		generos.add("Acción");
		generos.add("Aventura");
		generos.add("Clásicas");
		generos.add("Comedia Romántica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Acción y Aventura");
		generos.add("Romántica");
		generos.add("Ciencia Ficción");
		return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		repository.deleteById(idPelicula);
	}

	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public List<Pelicula> buscarActivas() {
		return repository.findByEstatus("Activa");
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		List<Pelicula> peliculas = null;
		// Buscamos en la tabla de horarios, [agrupando por idPelicula]
		List<Horario> horarios = horariosRepository.findByFecha(fecha);
		peliculas = new LinkedList<>();

		// Formamos la lista final de Peliculas que regresaremos.
		for (Horario h : horarios) {
			// Solo nos interesa de cada registro de horario, el registro de pelicula.
			peliculas.add(h.getPelicula());
		}		
		return peliculas;
	}

}
