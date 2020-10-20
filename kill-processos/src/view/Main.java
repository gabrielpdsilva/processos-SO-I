package view;

import javax.swing.JOptionPane;

import controller.ProcessosController;

public class Main {
	
	public static void mostrarMenu() {
		System.out.println("**************************");
		System.out.println("*GERENCIADOR DE PROCESSOS*");
		System.out.println("**************************\n");
		System.out.println("Escolha uma opcao, segundo:");
		System.out.println("1- Listar processos ativos");
		System.out.println("2- Matar processo");
		System.out.println("9- Encerrar programa.");
	}
	
	public static void main(String[] args) {
		
		mostrarMenu();
		
		ProcessosController pc = new ProcessosController();
		int opcao;

		String os = pc.getOS();

		do {
			
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao desejada."));
			
			switch(opcao) {
				case 1:
					pc.selectCommandByOS(os);
				break;
					
				case 2:
					String param = JOptionPane.showInputDialog(null, "Digite o nome ou PID do processo a ser finalizado.");
					//pc.killWindowsProcess(param);
					pc.killProcess(os, param);
				break;
					
				case 9:
					JOptionPane.showMessageDialog(null, "Aplicacao finalizada.");
				break;
					
				default:
					System.out.println("Opcao invalida.");
				break;
			}
			
		} while(opcao != 9);
	}
	
}
