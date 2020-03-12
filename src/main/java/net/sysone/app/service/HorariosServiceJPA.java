package net.sysone.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Horario;
import net.sysone.app.repository.HorariosRepository;

@Service
public class HorariosServiceJPA implements IHorariosService {
	
	@Autowired
	private HorariosRepository repository;

	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return repository.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}

	@Override
	public void insertar(Horario horario) {
		repository.save(horario);
	}

	@Override
	public List<Horario> buscarTodas() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Horario buscarPorId(int idHorario) {
		Optional<Horario> optional = repository.findById(idHorario);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public void eliminar(int idHorario) {
		repository.deleteById(idHorario);
	}

	@Override
	public Page<Horario> buscarTodas(Pageable page) {
		return repository.findAll(page);
	}
}
