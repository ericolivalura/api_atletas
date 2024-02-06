package com.example.teste.principal;

import com.example.teste.model.Dados;
import com.example.teste.model.DadosAtleta;
import com.example.teste.service.ConsumidorDeApi;
import com.example.teste.service.ConversorDeDados;

import java.util.*;

public class Execucao {

    private Scanner leitura = new Scanner(System.in);
    private ConsumidorDeApi consumidor = new ConsumidorDeApi();
    private ConversorDeDados conversorDeDados = new ConversorDeDados();
    private final String ENDERECO = "https://www.thesportsdb.com/api/v1/json/3/";

    private List<DadosAtleta> atletas = new ArrayList<>();

    private String json;

    private final String MENU = """
            1- Buscar
            2- Listar buscados
            0- sair
            """;

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            json = consumidor.obterDados(ENDERECO);
            System.out.println(MENU);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1 -> getDados();
                case 2 -> listarBuscados();
                case 0 -> System.out.println("Finalizando...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

//    private Dados buscarDadosWeb() {
//
//        return conversorDeDados.obterDados(json, Dados.class);
//    }

    private void getDados() {
        System.out.println("Digite o nome para busca");
        var nome = leitura.nextLine();
        json = consumidor.obterDados(ENDERECO + "searchplayers.php?p=" + nome.replace(" ", "_"));
        Dados dados = conversorDeDados.obterDados(json, Dados.class);
        if (dados.atletas() != null) {
            Optional<DadosAtleta> atletaBuscado = dados.atletas().stream()
                    .filter(a -> a.nome().toLowerCase().contains(nome.toLowerCase()))
                    .findFirst();
            encontrarAtleta(atletaBuscado);
        }else{
            System.out.println("Atleta não encontrado");
        }
    }

    private void encontrarAtleta(Optional<DadosAtleta> atletaBuscado) {
        if (atletaBuscado.isPresent()) {
            DadosAtleta atleta = atletaBuscado.get();
            System.out.println(atleta);
            salvarAtleta(atleta);
        } else {
            System.out.println("Não encontrado");
        }
    }

    private void salvarAtleta(DadosAtleta atleta) {
        atletas.add(atleta);
    }

    private void listarBuscados() {
        atletas.stream()
                .sorted(Comparator.comparing(DadosAtleta::nome))
                .forEach(System.out::println);
    }

}
