package br.com.po.imersao.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.po.imersao.model.Tarefa;
import br.com.po.imersao.repository.TarefaRepository;

//@CrossOrigin(origins = "http://localhost:4200") // Angular
@CrossOrigin(origins = "http://localhost:8100") // Ionic
@RestController
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
	
	@GetMapping(path="/titulo/{titulo}")
	public @ResponseBody ResponseEntity<?> findByTitulo(@PathVariable(value="titulo") String titulo) {
		System.out.println(titulo);
		try {
			return new ResponseEntity<List<Tarefa>>(tarefaRepository.findByTitulo(titulo), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(produces="application/json")
	public /*ResponseEntity<String>*/HashMap<String, String> add(@RequestBody Tarefa tarefa){
		HashMap<String, String> map = new HashMap<>();
		
		try {
			// Uma tarefa sempre recebe HOJE como valor
			tarefa.setDataCriacao(LocalDate.now().toString());
			tarefaRepository.save(tarefa);
			
			map.put("mensagem", "Tarefa criada com sucesso.");
			
		}catch (Exception e) {
//			return new ResponseEntity<String>("Não foi possivel cadastrar esta tarefa",
//					HttpStatus.CONFLICT);
			map.put("mensagem", "Falha ao criar tarefa.");
		}
//		return new ResponseEntity<String>("Tarefa criado com sucesso", HttpStatus.OK);
		return map;
	}
	
	@DeleteMapping(path="/{id}")
	public HashMap<String, String>/*@ResponseBody ResponseEntity<String>*/ delete(@PathVariable(value="id") Integer id) {
		HashMap<String, String> map = new HashMap<>();
		
		try {
			tarefaRepository.delete(id);
			
			map.put("mensagem", "Tarefa deletada com sucesso.");
		}catch (Exception e) {
//			return new ResponseEntity<String>("Não foi possivel realizar a exclusão", HttpStatus.NOT_MODIFIED);
			map.put("mensagem", "Falha ao deletar tarefa.");
		}
//		return new ResponseEntity<String>("Tarefa deletado com sucesso",HttpStatus.OK);
		return map;
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
