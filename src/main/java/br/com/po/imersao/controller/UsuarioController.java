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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.po.imersao.model.Usuario;
import br.com.po.imersao.repository.UsuarioRepository;

@Controller
@RequestMapping(path="/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public @ResponseBody Iterable<Usuario> all(){
		
		return usuarioRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Usuario> findOne(@PathVariable(value="id") Integer id){
		
		return new ResponseEntity<Usuario>(usuarioRepository.findOne(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> add(@RequestParam Usuario usuario){
		if(usuarioRepository.findByEmail(usuario.getEmail()) == null) {
			usuarioRepository.save(usuario);

			return new ResponseEntity<String>("Usuário criado com sucesso", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Este email já está cadastrado em nosso sistema!", HttpStatus.CONFLICT);
	}
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(value="id") Integer id) {
		usuarioRepository.delete(id);
		return new ResponseEntity<String>("Usuário deletado com sucesso",HttpStatus.GONE);
		
		
	}

	
	@PutMapping(path="/{id}")
	public ResponseEntity<Usuario> update(@PathVariable(value="id") Integer id, @RequestBody Usuario usuario){
		
		usuario.setId(id);
		
		if(usuarioRepository.findOne(id)!=null) {
			
			usuarioRepository.save(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.GONE);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_MODIFIED);
		
	}
	
	

}
