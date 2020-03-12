package pruebasrelaciones;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Pelicula;
import net.sysone.app.repository.PeliculasRepository;

public class AppFindAll {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		PeliculasRepository repo = context.getBean("peliculasRepository", PeliculasRepository.class);
		
		List<Pelicula> lista = repo.findAll();
		lista.forEach(pelicula -> System.out.println(pelicula));
		
		context.close();

	}

}
