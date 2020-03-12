package pruebasjparepo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.repository.NoticiasRepository;

public class AppDeleteAllInBatch {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		repo.deleteAllInBatch();

		context.close();

	}

}
