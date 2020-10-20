package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {
	
	public static void mostrarMenu() {
		System.out.println("*********************************");
		System.out.println("*VISUALIZADOR DE IPCONFIG E PING*");
		System.out.println("*********************************\n");
		System.out.println("Escolha uma opcao, segundo:");
		System.out.println("1- IPCONFIG (ou IFCONFIG no linux)");
		System.out.println("2- PING (www.google.com.br)");
		System.out.println("9- Encerrar programa.");
	}
	
	public static void main(String[] args) {
		
		mostrarMenu();
		
		RedesController rc = new RedesController();
		int opcao;

		String os = rc.getOS();

		do {
			
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opcao desejada."));
			
			switch(opcao) {
				case 1:
					rc.ip(os);
				break;
					
				case 2:
					rc.ping(os);
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