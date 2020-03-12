package net.sysone.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

@Service
@Primary
public class NoticiasServiceJPA implements INoticiasService {
	
	@Autowired
	private NoticiasRepository repository;

	@Override
	public void guardar(Noticia noticia) {
		repository.save(noticia);
	}

	@Override
	public List<Noticia> buscarTodas() {
		return repository.findAll();
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> optional = repository.findById(idNoticia);
		if (optional.isPresent())
			return optional.get();
		return null;
	}

	@Override
	public void eliminar(int idNoticia) {
		repository.deleteById(idNoticia);
	}

	@Override
	public List<Noticia> buscarUltimas() {
		return repository.findTop3ByEstatusOrderByFechaDesc("Activa");
	}
}
