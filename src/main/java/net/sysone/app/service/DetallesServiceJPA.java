package net.sysone.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Detalle;
import net.sysone.app.repository.DetallesRepository;

@Service
public class DetallesServiceJPA implements IDetallesService {
	
	@Autowired
	private DetallesRepository repository;

	@Override
	public void insertar(Detalle detalle) {
		repository.save(detalle);
	}

	@Override
	public void eliminar(int idPelicula) {
		repository.deleteById(idPelicula);
	}

}
