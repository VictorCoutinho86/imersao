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
	public @ResponseBody Usuario findOne(@RequestBody Integer id){
		
		return usuarioRepository.findOne(id);
	}

	@PostMapping
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
		
		usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody String delete(@PathVariable(value="id") Integer id) {
		usuarioRepository.delete(id);
		
		return "Usuário deletado com sucesso";
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Usuario> update(@PathVariable(value="id") Integer id, @RequestBody Usuario usuario){
		Usuario user = usuarioRepository.findOne(id);
		user.setEmail(usuario.getEmail());
		user.setNome(usuario.getNome());
		usuarioRepository.save(user);
		
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.ACCEPTED);
		
	}
	
	

}
