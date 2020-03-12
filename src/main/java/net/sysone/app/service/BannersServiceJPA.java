package net.sysone.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Banner;
import net.sysone.app.repository.BannersRepository;

@Service
@Primary
public class BannersServiceJPA implements IBannersService {
	
	@Autowired
	private BannersRepository repository;
	
	@Override
	public void insertar(Banner banner) {
		repository.save(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		Optional<Banner> optional = repository.findById(idBanner);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public void eliminar(int idBanner) {
		repository.deleteById(idBanner);
	}

	@Override
	public List<Banner> buscarActivas() {
		return repository.findByEstatus("Activo");
	}

}
