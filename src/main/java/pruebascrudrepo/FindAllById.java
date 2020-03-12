package pruebascrudrepo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class FindAllById {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(5);
		ids.add(6);
		
		Iterable<Noticia> iterable = repo.findAllById(ids);
		
		iterable.forEach(noticia -> System.out.println(noticia));
		
		context.close();
	}

}
