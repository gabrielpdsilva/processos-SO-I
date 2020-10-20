package view;

import controller.ProcessosController;

public class Principal {
	
	public static void main(String[] args) {
		
		ProcessosController pc = new ProcessosController();
		
		/*Mostrando informacoes sobre o S.O.*/
		
//		System.out.println("S.O. -> " + pc.getOS());
//		System.out.println("Arquitetura -> " + pc.getArch());
//		System.out.println("Versao -> " + pc.getVersion());

		//==========================================
		
		/*Chamando processo notepad*/
		
//		String process = "C:\\Windows\\notepad.exe";
//		pc.callProcess(process);
		
		//==========================================
		
		/*Chamando processo notepad*/
		
//		String process = "C:\\Windows\\notepaaaad.exe";
//		pc.callProcess(process);
		
		//vai dar erro 2 -> sistema nao conseguiu encontrar o arquivo especificado
		
		
		//==========================================
		
		/*Tentando chamar um processo que requer permissao de administrador, sem tratar erro*/
		
//		String process = "C:\\Users\\Windows 10\\Downloads\\Executáveis\\SteamSetup";
//		pc.callProcess(process);
		
		//vai dar erro 740 -> precisa ser administrador
		
		//==========================================
		
		/*Tentando chamar um processo que requer permissao de administrador, apos tratar o catch no metodo*/
		
//		String process = "C:\\Users\\Windows 10\\Downloads\\Executáveis\\SteamSetup";
//		pc.callProcess(process);
		
		//==========================================
		
		/*Listando todos os processos do windows. Equivalente a digitar TASKLIST /FO TABLE no cmd. */
		
//		String process = "TASKLIST /FO TABLE";
//		pc.readProcess(process);
		
		//==========================================
		
		/*Ping em um site*/
		
//		String process = "ping www.google.com.br";
//		pc.readProcess(process);
		
		//==========================================
		
		/*Descobrindo o caminho que os pacotes fazem ate chegar no google*/
		
//		String process = "TRACERT www.google.com.br";
//		pc.readProcess(process);
		
		//==========================================
		
		/*mostrando ipconfig igual no cmd*/
		
//		String process = "ipconfig";
//		pc.readProcess(process);
		
		//==========================================
		
		/*matando um processo pelo nome*/
		
//		pc.killProcess("notepad.exe");
		
		/*matando um processo pelo pid*/
		
//		pc.killProcess("6464");

		
	}

}