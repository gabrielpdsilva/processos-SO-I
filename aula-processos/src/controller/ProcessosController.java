package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	
	public ProcessosController() {
		super();
	}
	
	//pega o SO
	public String getOS() {
		String os = System.getProperty("os.name"); //pega o nome do SO
		
		return os;
	}
	
	//pega a arquitetura do SO
	public String getArch() {
		String arch = System.getProperty("os.arch");
		return arch;
	}
	
	//pega a versao do SO
	public String getVersion() {
		String version = System.getProperty("os.version");
		return version;
	}
	
	//chama processo
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage(); //pega so a primeira linha da Exception, onde contem o erro
			//System.err.println(msgErro); //mostra o erro
			
			if(msgErro.contains("740")) { //vai varrer a String e ver se encontra erro 740

				//caminho necessario pra executar como administrador:
				//cmd /c caminho_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					System.err.println(e1.getMessage());
				}
				
			} else {
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	//le o processo
	public void readProcess(String process) {
		
		try {
			Process p = Runtime.getRuntime().exec(process);		//enquanto process estiver em execucao, a variavel 'p' vai continuar recebendo dados.
			
			InputStream fluxo = p.getInputStream(); //retorna um InputStream, fluxo de bits

			InputStreamReader leitor = new InputStreamReader(fluxo); //converte pra String

			BufferedReader buffer = new BufferedReader(leitor); //necessario usar buffer, caso contrario tem risco de estourar memoria.
			String linha = buffer.readLine(); //O buffer vai descartando aquilo que ja foi lido. Ou seja, no proximo readLine ja vai ser outra linha.

			//executando ate o buffer se esgotar
			while(linha != null){
			System.out.println(linha);
			linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
			
			//resumindo a sequencia de passos do metodo
			//1. pega fluxo que vem do processo
			//2. transforma em string
			//3. armazena no buffer
			//4. le o buffer

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//podemos matar um processo pelo nome e pelo PID. Nao eh recomendavel matar pelo nome
	public void killProcess(String param) {
		String cmdPid = "TASKKILL /PID"; //apenas numeros
		String cmdNome = "TASKKILL /IM"; //dificilmente sera numero
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		//Possivel erro de NumberFormatException, se o param nao for int
		try {
			
			//TASKKILL /PID parametro
			pid = Integer.parseInt(param);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		
		} catch (NumberFormatException e) {

			//TASKKILL /IM parametro
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(param);
		}
	
		this.callProcess(buffer.toString());	
	}
}