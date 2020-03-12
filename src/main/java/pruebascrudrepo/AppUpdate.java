package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class AppUpdate {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		System.out.println("Application name: " + context.getApplicationName());
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		Optional<Noticia> optional = repo.findById(1);
		
		if (optional.isPresent()) {
			Noticia noticia = optional.get();
			System.out.println(noticia);
			noticia.setEstatus("Inactiva");
			repo.save(noticia);
		}
		
		context.close();

	}

}
