package br.com.po.imersao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping(path="/user")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public @ResponseBody Iterable<Usuario> all(){
		
		return usuarioRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<?> findOne(@PathVariable(value="id") Integer id){
		Usuario usuario = usuarioRepository.findOne(id);
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Usuário não encontrado!", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Usuario usuario){
		
			if(usuarioRepository.findByEmail(usuario.getEmail())==null) {
				usuarioRepository.save(usuario);
				return new ResponseEntity<String>("Usuário criado com sucesso!", HttpStatus.OK);
			}else {

			return new ResponseEntity<String>("Este email já está cadastrado em nosso sistema!", HttpStatus.CONFLICT);
			}
		
	}
		
	
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(value="id") Integer id) {
		
		try {
			usuarioRepository.delete(id);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Não foi possivel realizar a exclusão!", HttpStatus.NOT_MODIFIED);
		}
		
		return new ResponseEntity<String>("Usuário deletado com sucesso",HttpStatus.OK);
		
	}

	
	@PutMapping(path="/{id}")
	public ResponseEntity<Usuario> update(@PathVariable(value="id") Integer id, @RequestBody Usuario usuario){
		
		usuario.setId(id);
		
		try {
			usuarioRepository.findOne(id);
			
			usuarioRepository.save(usuario);
		}catch (Exception e) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.NOT_MODIFIED);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.GONE);
	}
	
	

}
