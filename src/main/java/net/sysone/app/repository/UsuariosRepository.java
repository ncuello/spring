package net.sysone.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sysone.app.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
