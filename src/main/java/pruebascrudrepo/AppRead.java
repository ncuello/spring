package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class AppRead {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		System.out.println("Application name: " + context.getApplicationName());
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		Optional<Noticia> noticia = repo.findById(1);
		System.out.println("Noticia1" + noticia.get());
		
		Optional<Noticia> noticia2 = repo.findById(2);
		System.out.println("Noticia2" + noticia2.get());
		
		
		context.close();

	}

}
