package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosController {
	
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
	
	public void killWindowsProcess(String param) {
		String cmdPid = "TASKKILL /PID"; //apenas numeros
		String cmdNome = "TASKKILL /IM"; //dificilmente sera numero
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
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
	
	public String getOS() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void selectCommandByOS(String os) {
		
		String process;
		
		if(os.equals("Linux")) {
			
			process = "ps -ef";
			this.readProcess(process);
			
		} else if(os.startsWith("Windows")) {
			
			process = "tasklist /fo table";
			this.readProcess(process);
		}
		
	}
	
	public void killLinuxProcess(String param) {
		
		String cmdPid = "kill -9";
		String cmdNome = "killall"; //dificilmente sera numero
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		try {
			
			//kill -9 pid
			pid = Integer.parseInt(param);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		
		} catch (NumberFormatException e) {

			//killall nome_progeama
			buffer.append(cmdNome);
			buffer.append(" ");
			buffer.append(param);
		}
	
		this.callProcess(buffer.toString());
	}
	
	public void killProcess(String os, String param) {
		
		if(os.equals("Linux")) {
			this.killLinuxProcess(param);			
		} else if(os.startsWith("Windows")) {
			this.killWindowsProcess(param);
		}
	}	

}