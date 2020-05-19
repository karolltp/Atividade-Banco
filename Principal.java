package view;

/* Um banco deve controlar Saques e Dep�sitos.
O sistema pode permitir um Saque e um Dep�sito Simult�neos, mas nunca 2 Saques ou 2 Dep�sitos Simult�neos.
Para calcular a transa��o (Saque ou Dep�sito), o m�todo deve receber o c�digo da conta, o saldo da conta e o valor
a ser transacionado.
Deve-se montar um sistema que deve considerar que 20 transa��es simult�neas ser�o enviadas ao sistema (aleatoriamente 
essas transa��es podem ser qualquer uma das op��es) e tratar todas as transa��es, de acordo com as regras acima.*/

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Principal {

	public static void main(String[] args) {
		
		int permissoes = 2;
		Semaphore semaforo = new Semaphore(permissoes);
				
		for (int idTransacao = 0; idTransacao < 20; idTransacao++) {
			Thread tTransacao = new ThreadBanco(idTransacao, semaforo);
			tTransacao.start();
		}
	}

}