package net.sysone.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sysone.app.model.Pelicula;

public interface IPeliculasService {
	void insertar(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	List<Pelicula> buscarActivas();
	List<Pelicula> buscarPorFecha(Date fecha);
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();
	void eliminar(int idPelicula);
	Page<Pelicula> buscarTodas(Pageable page);
}
