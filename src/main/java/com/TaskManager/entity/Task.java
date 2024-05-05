package com.TaskManager.entity;


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
	    private Date dt_Criacao;
	    
	    @Column(name = "dt_conclusao")
	    private Date dt_Conclusao;
	    
	    @Column(name = "dt_limite")
	    private Date dt_limite;
	    
	    @Column(name = "prioridade")
	    private Integer prioridade;
	    
	    @Column(name = "responsavel")
	    private Integer responsavel;
	    
	    @Column(name = "status")
	    private Integer status;

}
