package br.com.po.imersao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.po.imersao.model.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Integer> {

	public Tarefa findById(Integer id);
	
	public List<Tarefa> findByCriador(Integer criador);

}
