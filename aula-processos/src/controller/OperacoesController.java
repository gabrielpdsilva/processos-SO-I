package controller;

import java.util.Random;

public class OperacoesController {
	
	public OperacoesController() {
		super(); //pega tudo o que eh da classe pai. No caso, Object
	}
	
	//concatena 32768 caracteres, 1 a 1, em uma variavel String
	public void concatenarString() {
		String cadeia = "";
		double tempoInicial = System.nanoTime();
		
		for(int i = 0; i < 32768; i++) {
			cadeia += "a";

		}
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		
		//tempo total esta em nanosegundos. Nanosegundo -> 10^-9 segundos.
		//Pra colocar em segundos:
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("String ==> "+ tempoTotal +"s.");
	}
	
	//concatena 32768 caracteres, 1 a 1, em um buffer de Strings
	//Conclusao:
	//Se tiver operacao de leitura e escrita na String, evitar usar uma String pura. Usar buffer eh muito melhor.
			
	public void concatenarBuffer() {
		
		StringBuffer buffer = new StringBuffer();
		double tempoInicial = System.nanoTime();
		
		for(int i = 0; i < 32768; i++){
			buffer.append("a");
		}
		
		double tempoFinal = System.nanoTime();
		double tempoTotal = tempoFinal - tempoInicial;
		
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("String ==> "+ tempoTotal +"s.");
	}
	
	//Recebe uma frase, divide em palavras e imprime cada palavra.
	public void dividirFrase(String frase) {
		String[] vetorDePalavras = frase.split(" "); //vai quebrar a String sempre que encontrar um espaco.
		
		for(String palavra : vetorDePalavras) {
			System.out.println(palavra);
		}
	}
	
	/*
 	Fazer uma aplicacao em Java com Eclipse que leia
	um vetor de 100 posicoes, de 1000 posicoes e de
	10000 posicoes, com valores de 1 a 10, itere cada
	vetor somando o conteudo dos vetores. A aplicacao
	deve exibir o tempo da iteracao de cada vetor.
	Responder com os 3 tempos.
	 */
	
	public void calcularTempoVetor100() {
		
		int vetorDe100[] = new int[100];
		
		//preenchendo vetor 100
		for(int i = 0; i < 100; i++) {
			//vetorDe100[i] = rn.nextInt(10) + 1;
			vetorDe100[i] = 5;
		}
		
		int somatoria;
		double tempoInicial;
		double tempoFinal;
		double tempoTotal;
		
		/*Calculando o loop do vetor de 100 valores*/
		
		somatoria = 0;
		tempoInicial = System.nanoTime();
		
		//fazendo somatoria do vetor 100
		for(int i = 0; i < 100; i++) {
			somatoria += vetorDe100[i];
		}
		
		tempoFinal = System.nanoTime();
		tempoTotal = tempoFinal - tempoInicial;
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("Tempo vetor de 100 ==> "+ tempoTotal +"s.");
		
	}
	
	public void calcularTempoVetor1000() {
		int vetorDe1000[] = new int[1000];
		
		//preenchendo vetor 1000
		for(int i = 0; i < 1000; i++) {
			vetorDe1000[i] = 5;
		}
		
		int somatoria;
		double tempoInicial;
		double tempoFinal;
		double tempoTotal;
		
		/*Calculando o loop do vetor de 1000 valores*/
		
		somatoria = 0;
		tempoInicial = System.nanoTime();
		
		//fazendo somatoria do vetor 1000
		for(int i = 0; i < 1000; i++) {
			somatoria += vetorDe1000[i];
		}
		
		tempoFinal = System.nanoTime();
		tempoTotal = tempoFinal - tempoInicial;
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("Tempo vetor de 1000 ==> "+ tempoTotal +"s.");
		
	}

	public void calcularTempoVetor10000() {
		
		int vetorDe10000[] = new int[10000];
		
		//preenchendo vetor 10000
		for(int i = 0; i < 10000; i++) {
			vetorDe10000[i] = 5;
		}
		
		int somatoria;
		double tempoInicial;
		double tempoFinal;
		double tempoTotal;
		
		/*Calculando o loop do vetor de 10000 valores*/
		
		somatoria = 0;
		tempoInicial = System.nanoTime();
		
		//fazendo somatoria do vetor 10000
		for(int i = 0; i < 10000; i++) {
			somatoria += vetorDe10000[i];
		}
		
		tempoFinal = System.nanoTime();
		tempoTotal = tempoFinal - tempoInicial;
		tempoTotal = tempoTotal / Math.pow(10, 9);
		System.out.println("Tempo vetor de 10000 ==> "+ tempoTotal +"s.");
		
	}

}
