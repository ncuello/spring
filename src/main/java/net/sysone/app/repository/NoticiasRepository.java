package net.sysone.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.sysone.app.model.Noticia;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {
	
	// Select * from Noticias
	List<Noticia> findBy();
	
	// Select * from Noticias where Estatus = ?
	List<Noticia> findByEstatus(String estatus);
	
	// Select * from Noticias where Fecha = ?
	List<Noticia> findByFecha(Date fecha);
	
	// Select * from Noticias where Estatus = ? and Fecha = ?
	List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);
	
	// Select * from Noticias where Estatus = ? and Fecha = ?
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
	
	// Select * from Noticias where Fecha Between ? and ?
	List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);
	
	// Select * from Noticias where Fecha Between ? and ?
	List<Noticia> findByIdBetween(int id1, int id2);
	
	List<Noticia> findTop3ByEstatusOrderByFechaDesc(String estatus);
}
