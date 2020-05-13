package br.com.boavista;

import java.util.ArrayList;
import br.com.boavista.dao.TreinamentoDao;
import br.com.boavista.entidades.Treinamento;
import br.com.boavista.service.DataReaderService;

public class CargaDados {
	
	public static void main(String[] args) {		
		
		System.out.println("Iniciando processamento...");
		TreinamentoDao treinamentoDao = null;
		DataReaderService reader = null;
		
		try {
			reader = new DataReaderService("C:/Users/essantos/Desktop/Treinamentos Realizados.csv");
			ArrayList<String> dados = reader.getData();
			
			treinamentoDao = new TreinamentoDao();

			for (String registro: dados) {
				Treinamento treinamento = Treinamento.fromCSV(registro);
				treinamentoDao.save(treinamento);
			}
		} catch(Exception e) {
			System.out.println("Erro ao processar arquivo!\n" + e.getMessage());
		} finally {
			treinamentoDao.cleanUp();
			System.out.println("Processamento concluído!");
		}			
	}
}