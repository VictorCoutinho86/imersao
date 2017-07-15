package br.com.po.imersao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.po.imersao.model.Tarefa;
import br.com.po.imersao.repository.TarefaRepository;

@Controller
@RequestMapping("/todo")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	
	@GetMapping
	public @ResponseBody Iterable<Tarefa> all(){
		
		return tarefaRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Tarefa> findById(@RequestBody Integer id){
		
		return new ResponseEntity<Tarefa>(tarefaRepository.findOne(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Tarefa usuario){
		
		try {
			tarefaRepository.save(usuario);

			return new ResponseEntity<String>("Usuário criado com sucesso", HttpStatus.CREATED);
			
		}catch (Exception e) {
			return new ResponseEntity<String>("Este email já está cadastrado em nosso sistema!",
					HttpStatus.CONFLICT);		}
		
	}
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(value="id") Integer id) {
		tarefaRepository.delete(id);
		return new ResponseEntity<String>("Usuário deletado com sucesso",HttpStatus.OK);
		
		
	}

	
	@PutMapping(path="/{id}")
	public ResponseEntity<Tarefa> update(@PathVariable(value="id") Integer id, @RequestBody Tarefa tarefa){
		
		tarefa.setId(id);
		
		if(tarefaRepository.findOne(id)!=null) {
			
			tarefaRepository.save(tarefa);
			return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
		}
		return new ResponseEntity<Tarefa>(tarefa, HttpStatus.NOT_MODIFIED);
		
	}

}
