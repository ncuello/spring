package net.sysone.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sysone.app.model.Banner;

public interface BannersRepository extends JpaRepository<Banner, Integer> {

	List<Banner> findByEstatus(String estatus);
}
