package geradorducumentos.menu;

import javax.swing.JOptionPane;
import geradorducumentos.gerador.GeradorDeCNPJ;
import geradorducumentos.gerador.GeradorDeCPF;

/*
 * 
 * author kaio (kaio_gus@outlook.com)
 */

public class GeradorCpfCnpj {
	public static void main(String[] args) {
		GeradorDeCPF gerandoCPF = null;
		GeradorDeCNPJ gerandoCNPJ = null;
		int opcao;
		
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("ESCOLHA UMA OPCAO:"
					+ "\n1 - Gerar CPF"
                    + "\n2 - Gerar CNPJ"
                    + "\n3 - Verificar CPF"
                    + "\n4 - Verificar CNPJ"
                    + "\n0 - Sair"));
					
			switch(opcao) {
				case 1:
					gerandoCPF = new GeradorDeCPF();
					String cpf = gerandoCPF.gerarCPF();
					int subopcaoCPF = Integer.parseInt(JOptionPane.showInputDialog("ESCOLHA UMA OPCAO:"
							+ "\n1 - Gerar CPF formatado"
							+ "\n2 - Gerar CPF nao formatado"));
					
					switch(subopcaoCPF) {
						case 1:
							JOptionPane.showMessageDialog(null, "CPF Gerado: " + cpf);
							break;
						case 2:
							cpf = cpf.replace(".", "").replace("-", "");
							JOptionPane.showMessageDialog(null, "CPF Gerado: " + cpf);
							break;
						default:
							JOptionPane.showMessageDialog(null, "Erro de opcao, tente novamente!");
					}
					break;
				case 2:
					gerandoCNPJ = new GeradorDeCNPJ();
					String cnpj = gerandoCNPJ.gerarCNPJ();
					int subopcaoCNPJ = Integer.parseInt(JOptionPane.showInputDialog("ESCOLHA UMA OPCAO:"
							+ "\n1 - Gerar CNPJ formatado"
							+ "\n2 - Gerar CNPJ nao formatado"));
					
					switch(subopcaoCNPJ) {
						case 1:
							JOptionPane.showMessageDialog(null, "CNPJ Gerado: " + cnpj);
							break;
						case 2:
							cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");
							JOptionPane.showMessageDialog(null, "CNPJ Gerado: " + cnpj);
							break;
						default:
							JOptionPane.showMessageDialog(null, "Erro de opcao, tente novamente!");
					}
					break;
				case 3:
					gerandoCPF = new GeradorDeCPF();
					String cpfDigitado = JOptionPane.showInputDialog("Digite um cpf?");
					String respostaCPF = gerandoCPF.seEValidoCPF(cpfDigitado);
					JOptionPane.showMessageDialog(null, respostaCPF);
					break;
				case 4:
					gerandoCNPJ = new GeradorDeCNPJ();
					String cnpjDigitado = JOptionPane.showInputDialog("Digite um cnpj?");
					String respostaCNPJ = gerandoCNPJ.seEValidoCNPJ(cnpjDigitado);
					JOptionPane.showMessageDialog(null, respostaCNPJ);
					break;
				case 0:
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opcao menu Invalida! \nDigite opcao valida ou 0 para sair.");
			}
			
		} while (opcao != 0);
	}
	
}
