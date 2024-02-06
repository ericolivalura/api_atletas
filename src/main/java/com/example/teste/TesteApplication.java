package com.example.teste;

import com.example.teste.principal.Execucao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TesteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Execucao execucao = new Execucao();
		execucao.exibeMenu();
	}
}
