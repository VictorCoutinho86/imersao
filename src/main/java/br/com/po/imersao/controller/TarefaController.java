package br.com.po.imersao.controller;

import java.time.LocalDate;
import java.util.List;

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
	public @ResponseBody ResponseEntity<?> findById(@PathVariable(value="id") Integer id){
		
		try {
			return new ResponseEntity<Tarefa>(tarefaRepository.findOne(id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Tarefa não encontrada.", HttpStatus.NOT_FOUND);		}	
	}
	
	
	@GetMapping(path="/criador/{id}")
	public @ResponseBody ResponseEntity<?> findByCriador(@PathVariable(value="id") Integer id){
		
			try {
				return new ResponseEntity<List<Tarefa>>(tarefaRepository.findByCriador(id), HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
			}
		}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Tarefa tarefa){
		
		try {
			// Uma tarefa sempre recebe HOJE como valor
			tarefa.setDataCriacao(LocalDate.now().toString());
			tarefaRepository.save(tarefa);
			
		}catch (Exception e) {
			return new ResponseEntity<String>("Não foi possivel cadastrar esta tarefa",
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Tarefa criado com sucesso", HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(value="id") Integer id) {
		
		try {
			tarefaRepository.delete(id);
		}catch (Exception e) {
			return new ResponseEntity<String>("Não foi possivel realizar a exclusão", HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<String>("Tarefa deletado com sucesso",HttpStatus.OK);
		
	}

	
	@PutMapping(path="/{id}")
	public ResponseEntity<Tarefa> update(@PathVariable(value="id") Integer id, @RequestBody Tarefa tarefa){
		
		tarefa.setId(id);
		
		try{
			
			if(tarefaRepository.findOne(id)!=null) {
			tarefaRepository.save(tarefa);
			}
		}catch (Exception e) {
			return new ResponseEntity<Tarefa>(tarefa, HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
	}
}
