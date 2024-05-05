package com.TaskManager.enums;

public enum Status {
//    CONCLUIDA(1, "Concluída"),
//    ANDAMENTO(2, "Em Andamento"),
//    CANCELADA(3, "Cancelada");
	
	AGENDADA (1, "Agendada"),
	ANDAMENTO (2, "Em Andamento"),
	PAUSADA (3, "Pausada"),
	CANCELADA (4, "Cancelada"),
	ATRASADA (5, "Atrasada"),
	CONCLUIDA (6, "Concluída");


    private final int codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
