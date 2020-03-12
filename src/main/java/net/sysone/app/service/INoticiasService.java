package net.sysone.app.service;

import java.util.List;

import net.sysone.app.model.Noticia;

public interface INoticiasService {
	
	void guardar (Noticia noticia);
	List<Noticia> buscarTodas(); 
	List<Noticia> buscarUltimas();
	Noticia buscarPorId(int idNoticia);
	void eliminar(int idNoticia);
}
