package pruebasjparepo;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class AppPaging {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
//		Obtenes todas las entidades por asignación
//		Page<Noticia> page = repo.findAll(PageRequest.of(0, 5));
//		page.forEach(noticia -> System.out.println(noticia));
//		System.out.println(page.getTotalPages());
		
		
		Page<Noticia> page = repo.findAll(PageRequest.of(0, 5, Sort.by("titulo")));
		page.forEach(noticia -> System.out.println(noticia));
		
		context.close();

	}

}
