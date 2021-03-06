package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sysone.app.model.Noticia;
import net.sysone.app.repository.NoticiasRepository;

public class AppKeywordBetween {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
//		List<Noticia> lista = repo.findByEstatus("Activa");
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		List<Noticia> lista = null;
		try {
//			lista = repo.findByFechaBetween(format.parse("2017-09-02"), format.parse("2017-09-02"));
			lista = repo.findByIdBetween(3, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lista.forEach(noticia -> System.out.println(noticia));
		
		context.close();

	}

}
