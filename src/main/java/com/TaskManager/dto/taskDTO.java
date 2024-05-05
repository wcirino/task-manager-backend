package com.TaskManager.dto;

import java.util.Date;

import com.TaskManager.enums.Prioridade;
import com.TaskManager.enums.Responsavel;
import com.TaskManager.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class taskDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Date dt_Criacao;
    private Date dt_Conclusao;
    private Date dt_limite;
    private Status status;
    private Responsavel responsavel;
    private Prioridade prioridade;
	
}
