package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	public void ping(String os) {
		String process;
		if(os.equals("Linux")){
			process = "ping -c 10 www.google.com.br";
			this.calculatePing(process);
		} else if(os.startsWith("Windows")) {
			process = "ping -n 10 www.google.com.br";
			this.calculatePing(process);
		}
	}
	
	public void calculatePing(String process) {
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			
			double tempo; //double pq no linux usam-se valores reais
			
			int somatoria = 0;
			
			while(linha != null){
				//no caso do windows
				if(linha.contains("tempo=")){
					tempo = Double.parseDouble(linha.substring(linha.indexOf("tempo=") + 6, linha.indexOf("ms")));
					System.out.println("Tempo = " + tempo);
					somatoria += tempo;
				}
				
				//no caso do linux				
				if(linha.contains("time=")){
					tempo = Double.parseDouble(linha.substring(linha.indexOf("time=") + 5, linha.indexOf("ms")));
					System.out.println("Tempo = " + tempo);
					somatoria += tempo;
				}
				
				linha = buffer.readLine();
			}
			
			somatoria /= 10;
			
			//apresentando a media do ping
			System.out.println("Tempo medio = " + somatoria + "ms");

			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ip(String os) {
		String process;
		
		if(os.equals("Linux")){
			process = "ifconfig";
			this.getAdapterNameAndIpv4(process);
		} else if(os.startsWith("Windows")) {
			process = "ipconfig";
			this.getAdapterNameAndIpv4(process);
		}
		
	}
	
	public void callProcess(String process) {
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {
			String msgErro = e.getMessage();
			
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
	
	public String getOS() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void getAdapterNameAndIpv4(String process) {
		
		StringBuffer adaptador = new StringBuffer();
		StringBuffer ipv4 = new StringBuffer();
		
		
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo); 
			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			
			boolean adaptadorEncontrado = false;
			boolean ipv4Encontrado = false;
			
			while(linha != null){
				
				//achou adaptador ethernet
				if(linha.contains("Ethernet")){
					
					adaptadorEncontrado = true;
					adaptador.setLength(0); 				//descarta o que ja tinha
					adaptador.append(linha); 				//adiciona o conteudo de linha
					linha = buffer.readLine();
					
				}
				
				//achou ipv4
				if(linha.contains("IPv4") || linha.contains("inet")){
					
					ipv4Encontrado = true;
					ipv4.setLength(0);						//descarta o que ja tinha
					ipv4.append(linha); 					//adiciona o conteudo de linha
					linha = buffer.readLine();
				}
				
				//nao encontrou adaptador, mas achou ipv4
				if(!adaptadorEncontrado && ipv4Encontrado){
					ipv4.setLength(0);						//descarta o que ja tinha
					adaptador.setLength(0);					//descarta o que ja tinha
					ipv4Encontrado = false;
				
				//encontrou os dois
				}else if(adaptadorEncontrado && ipv4Encontrado){

					System.out.println(adaptador);
					System.out.println(ipv4);
					ipv4Encontrado = false;
					adaptadorEncontrado = false;
					
				}
				
				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readProcess(String process) {
		
		try {
			Process p = Runtime.getRuntime().exec(process);		//enquanto process estiver em execucao, a variavel 'p' vai continuar recebendo dados.
			
			InputStream fluxo = p.getInputStream(); //retorna um InputStream, fluxo de bits

			InputStreamReader leitor = new InputStreamReader(fluxo); //converte pra String

			BufferedReader buffer = new BufferedReader(leitor);
			
			String linha = buffer.readLine();
			
			
			//executando ate o buffer se esgotar
			while(linha != null){
				System.out.println(linha);
				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
