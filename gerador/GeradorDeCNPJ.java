package geradorducumentos.gerador;

import java.util.concurrent.ThreadLocalRandom;

/*
 * 
 * author kaio (kaio_gus@outlook.com)
 */

public class GeradorDeCNPJ {
	private String calcularDigitosVerificadoresCNPJ(String numero) {
		Integer primeiroDigito;
		Integer segundoDigito;
		int peso = 2;
		int soma = 0; 

		for (int i = numero.length(); i > 0; i--) {
			if(i == 4) {
				peso = 2;
			}
			soma += Integer.parseInt(numero.substring(i - 1, i)) * peso++;
		}
		
		if (soma % 11 == 0 | soma % 11 == 1) { 
			primeiroDigito = new Integer(0);  
		} else { 
			primeiroDigito = new Integer(11 - (soma % 11));  
		}
		
		soma = 0;
		peso = 3;
		
		for (int i = numero.length(); i > 0; i--) {
			if(i == 5) {
				peso = 2;
			}
			soma += Integer.parseInt(numero.substring(i - 1, i)) * peso++;
		}
		
		soma += primeiroDigito.intValue() * 2;
		
		if (soma % 11 == 0 | soma % 11 == 1) { 
			segundoDigito = new Integer(0);  
		} else { 
			segundoDigito = new Integer(11 - (soma % 11));  
		}
		return primeiroDigito.toString() + segundoDigito.toString();	
	}		
		
	public String gerarCNPJ() {     
		String iniciais = "1000";     
		int numero = 0; 
		
		for (int i = 0; i < 8; i++) {
			numero = ThreadLocalRandom.current().nextInt(0, 10);     
			iniciais += numero; 
		}     
		String cnpj = inverterCNPJ(iniciais);
		cnpj = cnpj + calcularDigitosVerificadoresCNPJ(cnpj);
		return formatarCNPJ(cnpj);
	}
	
	private String inverterCNPJ(String cnpj) {
		String inverso = "";
		
		for (int i = cnpj.length(); i > 0; i--) {
			inverso += cnpj.charAt(i-1);
		}
		return inverso;
	}
	
	public String seEValidoCNPJ(String cnpj) {	
		cnpj = cnpj.trim().replace(".", "").replace("/", "").replace("-", "");
		
		if (cnpj == null || cnpj.length() != 14) { 
			return "CNPJ deve possuir 14 digitos!";  
		}
		
		if (verificarDigitosRepetidos(cnpj)) {
			return "CNPJ nao e valido! Verificador";
		}		
		String numeroDeDigitos = cnpj.substring(0, 12);		
		return calcularDigitosVerificadoresCNPJ(numeroDeDigitos)
			.equals(cnpj.substring(12, 14)) ? "CNPJ e valido!" : "CNPJ nao e valido!";  
	}
	
	private boolean verificarDigitosRepetidos(String cnpj) {
		int verificador = 0;

		for (int i = 0; i < cnpj.length() - 1; i++) {
			for (int j = 0; j < 10; j++) {
				if (Integer.parseInt(String.valueOf(cnpj.charAt(j))) == i) {
					verificador++;
				}
			}

			if (verificador == cnpj.length() - 3) {
				return true;
			}
			verificador = 0;
		}
		return false;
	}
	
	private String formatarCNPJ(String cnpj) {
		String numerosFormatado = "";
		
		for (int i = 0; i < cnpj.length(); i++) {
			if (i == 2 || i == 5) {
				numerosFormatado += ".";
			}
			
			if (i == 8) {
				numerosFormatado += "/";
			}
			
			if (i == 12) {
				numerosFormatado += "-";
			}
			numerosFormatado += cnpj.charAt(i);
		}
		return numerosFormatado;
	}
	
}
