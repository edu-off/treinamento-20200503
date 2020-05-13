package br.com.boavista.entidades;

import lombok.Data;

@Data
public class Treinamento {
	
	private Integer id;
	private String nome;
	private Funcionario funcionario;
	
	public static Treinamento fromCSV(String csv) {
		String[] dados = csv.split(";");
		Treinamento treinamento = new Treinamento();
		Funcionario funcionario = new Funcionario();
		treinamento.setId(Integer.valueOf(dados[0]));
		treinamento.setNome(dados[1]);
		funcionario.setNome(dados[2]);
		funcionario.setEmail(dados[3]);
		funcionario.setArea(dados[4]);
		treinamento.setFuncionario(funcionario);
		return treinamento;
	}
}