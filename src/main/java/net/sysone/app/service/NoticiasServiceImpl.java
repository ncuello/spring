package net.sysone.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.sysone.app.model.Noticia;

@Service
public class NoticiasServiceImpl implements INoticiasService {
	
	// Constructor vacio. Unicamente para imprimir un mensaje al crearse una instancia
	public NoticiasServiceImpl() {
		System.out.println("Creando una instancia de la clase NoticiasServiceImpl");
	}
	
	
	@Override
	public void guardar(Noticia noticia) {
		System.out.println("Guadando el objeto " + noticia + " en la base de datos.");
	}


	@Override
	public List<Noticia> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Noticia buscarPorId(int idNoticia) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void eliminar(int idNoticia) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Noticia> buscarUltimas() {
		// TODO Auto-generated method stub
		return null;
	}

}
