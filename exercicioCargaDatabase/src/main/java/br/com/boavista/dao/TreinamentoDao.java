package br.com.boavista.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.boavista.entidades.Treinamento;

public class TreinamentoDao {
	private final String SQL_INSERT = "INSERT INTO treinamento ( " +
			" id, nome_treinamento, nome_funcionario, email, area) " +
			" VALUES (?, ?, ?, ?, ?)"; 
	
	private Connection connection;
	private Statement sqlDDL;
	private PreparedStatement sqlInsert;
	
	public TreinamentoDao() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:treinamento.db");
			this.sqlDDL = this.connection.createStatement();
			
			sqlDDL.executeUpdate("drop table if exists treinamento");
			sqlDDL.executeUpdate("create table treinamento (id integer, nome_treinamento string, nome_funcionario string, email string, area string)");

			this.sqlInsert = this.connection.prepareStatement(SQL_INSERT);

		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar driver de conexão do banco de dados!\n" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados!\n" + e.getMessage());
		}
	}
	
	public void save(Treinamento treinamento) {
		try {
			this.sqlInsert.setInt(1, treinamento.getId());
			this.sqlInsert.setString(2, treinamento.getNome());
			this.sqlInsert.setString(3, treinamento.getFuncionario().getNome());
			this.sqlInsert.setString(4, treinamento.getFuncionario().getEmail());
			this.sqlInsert.setString(5, treinamento.getFuncionario().getArea());
			System.out.println("SqlInsert " + sqlInsert.toString());
			this.sqlInsert.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar dados na base de dados!\n" + e.getMessage());
		}
	}
	
	public void cleanUp() {
		try {
			if (this.connection != null) {
				this.connection.close();	
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco de dados!\n" + e.getMessage());
		}
	}	
}