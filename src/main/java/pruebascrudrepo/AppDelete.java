package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.repository.NoticiasRepository;

public class AppDelete {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		int idNoticia = 2;
//		repo.deleteById(idNoticia);
		
		if(repo.existsById(idNoticia)) {
			repo.deleteById(idNoticia);
		}
		
		context.close();

	}

}
