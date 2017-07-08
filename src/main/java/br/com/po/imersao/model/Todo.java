package br.com.po.imersao.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Todo implements Serializable {

	private static final long serialVersionUID = -6246916121644380680L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String descricao;
	
	@DateTimeFormat
	private LocalDate dataCriacao;
	
	
	

}
