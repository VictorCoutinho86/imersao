package br.com.po.imersao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.po.imersao.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
	
	public Tarefa findById(Integer id);
	
	public List<Tarefa> findByUsuarioId(Integer id);
	
	public List<Tarefa> findByTituloContains(String titulo);

}
