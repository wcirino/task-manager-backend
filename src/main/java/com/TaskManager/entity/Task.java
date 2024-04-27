package com.TaskManager.entity;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	    
	    @Column(name = "titulo")
	    private String titulo;
	    
	    @Column(name = "descricao")
	    private String descricao;
	    
	    @Column(name = "dt_criacao")
	    private Date dtCriacao;
	    
	    @Column(name = "dt_conclusao")
	    private Date dtConclusao;
	    
	    @Column(name = "dt_limite")
	    private Date dt_limite;
	    
	    @Column(name = "prioridade")
	    private int prioridade;
	    
	    @Column(name = "responsavel")
	    private int responsavel;
	    
	    @Column(name = "status")
	    private int status;

}
