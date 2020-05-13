package br.com.boavista.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReaderService {
	private FileReader reader;
	private BufferedReader buffer;
	
	public DataReaderService(String source) {
		try {
			reader = new FileReader(source);
			buffer = new BufferedReader(reader);
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao acessar arquivo de dados!\n" + e.getMessage());
		} 
	}
	
	public ArrayList<String> getData() {
		ArrayList<String> dados = new ArrayList<>();
		try {
			String registro = this.buffer.readLine();
			while (registro != null) {
				dados.add(registro);
				registro = this.buffer.readLine();
			}
			return dados;
		} catch (IOException e) {
			System.out.println("Erro ao processar arquivo de dados!\n" + e.getMessage());
			return null;
		} finally {
			if (this.buffer != null) {
				try {
					this.buffer.close();	
				} catch (Exception e) {
					System.out.println("Erro ao fechar arquivo de dados!\n" + e.getMessage());
				}
			}
		}
	}	
}