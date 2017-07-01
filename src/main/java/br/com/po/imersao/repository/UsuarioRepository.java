package br.com.po.imersao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.po.imersao.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
