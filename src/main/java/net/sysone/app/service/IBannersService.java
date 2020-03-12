package net.sysone.app.service;

import java.util.List;

import net.sysone.app.model.Banner;

public interface IBannersService {

	void insertar(Banner banner);
	List<Banner> buscarTodos();
	List<Banner> buscarActivas();
	Banner buscarPorId(int idBanner);
	void eliminar(int idBanner);
}
