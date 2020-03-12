package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class AppExists {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		int idNoticia = 1;
		System.out.println(repo.existsById(idNoticia));
		
		System.out.println(repo.existsById(2));
		
		context.close();

	}

}
