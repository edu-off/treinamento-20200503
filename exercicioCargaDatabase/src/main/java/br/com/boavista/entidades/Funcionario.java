package br.com.boavista.entidades;

import lombok.Data;

@Data
public class Funcionario {
	
	private String nome;
	private String email;
	private String area;
	
	public Funcionario() {		
	}
}