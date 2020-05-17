package geradorducumentos.gerador;

import java.util.concurrent.ThreadLocalRandom;

/*
 * 
 * author kaio (kaio_gus@outlook.com)
 */

public class GeradorDeCPF {
	private String calcularDigitosVerificadoresCPF(String numero) {
		Integer primeiroDigito;
		Integer segundoDigito;
		int soma = 0; 
		int peso = 10;
		
		for (int i = 0; i < numero.length(); i++) {
			soma += Integer.parseInt(numero.substring(i, i + 1)) * peso--;
		}	
		
		if (soma % 11 == 0 | soma % 11 == 1) { 
			primeiroDigito = new Integer(0);  
		} else { 
			primeiroDigito = new Integer(11 - (soma % 11));  
		}
		
		soma = 0;  
		peso = 11;
			
		for (int i = 0; i < numero.length(); i++) {
			soma += Integer.parseInt(numero.substring(i, i + 1)) * peso--;  
		}  
		
		soma += primeiroDigito.intValue() * 2;  
		
		if (soma % 11 == 0 | soma % 11 == 1) {
			segundoDigito = new Integer(0);  
		} else {  
			segundoDigito = new Integer(11 - (soma % 11));  
		}
		return primeiroDigito.toString() + segundoDigito.toString();  
	} 

	public String gerarCPF() {  
		String iniciais = "";  
		int numero = 0;
		
		for (int i = 0; i < 9; i++) { 
			numero = ThreadLocalRandom.current().nextInt(0, 10);
			iniciais += numero;  
		}
		iniciais += calcularDigitosVerificadoresCPF(iniciais);
		return formatarCPF(iniciais);
	}  

	public String seEValidoCPF(String cpf) { 
		cpf = cpf.trim().replace(".", "").replace("-", "");
		
		if (cpf == null || cpf.length() != 11) {
			return "CPF deve possuir 11 digitos!";  
		}
		
		if (verificarDigitosRepetidos(cpf)) {
			return "CPF nao e valido!";
		}
		String numeroDeDigitos = cpf.substring(0, 9);		
		return calcularDigitosVerificadoresCPF(numeroDeDigitos)
			.equals(cpf.substring(9, 11)) ? "CPF e valido!" : "CPF nao e valido!";  
	}
	
	private boolean verificarDigitosRepetidos(String cpf) {
		int verificador = 0;

		for (int i = 0; i < cpf.length() - 1; i++) {
			for (int j = 0; j < cpf.length(); j++) {
				if (Integer.parseInt(String.valueOf(cpf.charAt(j))) == i) {
					verificador++;
				}
			}
			
			if (verificador == cpf.length()) {
				return true;
			}
			verificador = 0;	
		}
		return false;
	}

	private String formatarCPF(String cpf) {
		String numerosFormatado = "";
		int valor = 0;
		
		for (int i = 0; i < cpf.length(); i++) {
			if (i % 3 == 0 && i != 0) {
				if (valor < 2) {
					numerosFormatado += ".";
					valor++;
				} else {
					numerosFormatado += "-";
				}
			}
			numerosFormatado += cpf.charAt(i);
		}
		return numerosFormatado;
	}

}
