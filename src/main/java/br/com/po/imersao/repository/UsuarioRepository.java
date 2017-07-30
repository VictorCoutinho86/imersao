package br.com.po.imersao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.po.imersao.model.Tarefa;
import br.com.po.imersao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	
	
	public Usuario findByNomeIsLike(String nome);
	
	
	public Usuario findByEmail(String email);	
	
	public Usuario findByTarefasId(Integer id);
	
	public List<Tarefa> findById(Integer id);
	
}
