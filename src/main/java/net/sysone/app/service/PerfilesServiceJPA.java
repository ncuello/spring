package net.sysone.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import net.sysone.app.model.Perfil;
import net.sysone.app.repository.PerfilesRepository;

public class PerfilesServiceJPA implements IPerfilesService {
	
	@Autowired
	private PerfilesRepository repository;

	@Override
	public void guardar(Perfil perfil) {
		repository.save(perfil);
	}

}
