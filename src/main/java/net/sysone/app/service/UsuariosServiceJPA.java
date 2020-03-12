package net.sysone.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Usuario;
import net.sysone.app.repository.UsuariosRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService{
	
	@Autowired
	private UsuariosRepository repository;

	@Override
	public void guardar(Usuario usuario) {
		repository.save(usuario);
	}

}
