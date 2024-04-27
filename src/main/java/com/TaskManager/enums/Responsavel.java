package com.TaskManager.enums;

public enum Responsavel {
    WILLYAN(1, "Willyan"),
    NATALIA(2, "Natalia"),
    KARINA(3, "Karina"),
    MICHELLE(4, "Michelle");

    private final int codigo;
    private final String nome;

    Responsavel(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}