package view;

/* Um banco deve controlar Saques e Depósitos.
O sistema pode permitir um Saque e um Depósito Simultâneos, mas nunca 2 Saques ou 2 Depósitos Simultâneos.
Para calcular a transação (Saque ou Depósito), o método deve receber o código da conta, o saldo da conta e o valor
a ser transacionado.
Deve-se montar um sistema que deve considerar que 20 transações simultâneas serão enviadas ao sistema (aleatoriamente 
essas transações podem ser qualquer uma das opções) e tratar todas as transações, de acordo com as regras acima.*/

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