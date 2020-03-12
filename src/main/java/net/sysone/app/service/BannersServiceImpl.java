package net.sysone.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.sysone.app.model.Banner;

@Service
public class BannersServiceImpl implements IBannersService {
	
	private List<Banner> lista = null;
	
	public BannersServiceImpl() {
		System.out.println("Creando instancia de la clase " + BannersServiceImpl.class.getName());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			lista = new LinkedList<>();
			
			Banner banner1 = new Banner();
			banner1.setArchivo("slide1.jpg");
			banner1.setEstatus("Inactivo");
			banner1.setFecha(formatter.parse("02-05-2017"));
			banner1.setId(1);
			banner1.setTitulo("Banner1");
			
			Banner banner2 = new Banner();
			banner2.setArchivo("slide2.jpg");
			banner2.setId(2);
			banner2.setTitulo("Banner2");
			
			Banner banner3 = new Banner();
			banner3.setArchivo("slide3.jpg");
			banner3.setFecha(formatter.parse("09-08-2019"));
			banner3.setId(3);
			banner3.setTitulo("Banner3");
			
			lista.add(banner1);
			lista.add(banner2);
			lista.add(banner3);
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void insertar(Banner banner) {
		lista.add(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		return lista;
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int idBanner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Banner> buscarActivas() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
