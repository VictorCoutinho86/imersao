package br.com.po.imersao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping
	public @ResponseBody String add(@RequestBody Usuario usuario){
		usuarioRepository.save(usuario);

		return "Saved";
	}
	
	@DeleteMapping(path="/{id}")
	public String delete(PathVariable Integer id) {
		
	}
}
