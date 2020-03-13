package net.sysone.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Perfil;
import net.sysone.app.repository.PerfilesRepository;

@Service
public class PerfilesServiceJPA implements IPerfilesService {
	
	@Autowired
	private PerfilesRepository repository;

	@Override
	public void guardar(Perfil perfil) {
<<<<<<< HEAD
		// pruebo gitlab v3
=======
		// pruebo gitlab v2
>>>>>>> b9e378816d5bbe4dff800297f7e4f4ed3f383b29
		repository.save(perfil);
	}

}
