package net.sysone.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sysone.app.model.Horario;

public interface IHorariosService {
	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	void insertar(Horario horario);
	List<Horario> buscarTodas();
	Page<Horario> buscarTodas(Pageable page);
	Horario buscarPorId(int idHorario);
	void eliminar(int idHorario);
}
