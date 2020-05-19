package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread{
	
	private int idTransacao;
	private Semaphore semaforo;
	private static Boolean saqueOperando = false; 
	private static Boolean depositoOperando = false; 
	int codConta = (int)((Math.random() * 9999) + 1000);
	int saldoConta = (int)((Math.random() * 5000) + 1);
	int valorTransacao = (int)((Math.random() * 5000) + 1);
	String operacoes[] = new String[] {"saque", "depósito"};
	
	public ThreadBanco(int idTransacao, Semaphore semaforo) {
		this.idTransacao = idTransacao;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {

		int operacao = 0;
		try {
			
			operacao = sistemaEntrada();
			semaforo.acquire();
			sistemaOperando(operacao);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			sistemaSaida(operacao);
		}
	}
	
	private int sistemaEntrada() throws InterruptedException {
		sleep(saldoConta + valorTransacao); 
		
		Random aleatorio = new Random();
		int j = aleatorio.nextInt(2);
		int tempo = 300;
		
		if (j == 0) { 
			while (saqueOperando) { 
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			saqueOperando = true; 
		} else if (j == 1) {
			while (depositoOperando) {
				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			depositoOperando = true;
		}
		
		return j;
	}
	
	private void sistemaOperando(int operacao) {
		try {
			System.out.println("# " + idTransacao + " Transação " + operacoes[operacao] + 
					" iniciada.\nCódigo da conta: " + codConta + "\nSaldo da conta: " + saldoConta + 
					"\nValor da transação: " + valorTransacao);
			
			int tempo = 5000; 
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void sistemaSaida(int operacao) {
		
		if (operacao == 0) { 
			saqueOperando = false; 
		} else if (operacao == 1) {
			depositoOperando = false;
		}
		
		System.out.println("# " + idTransacao + " Transação " + operacoes[operacao] + " finalizada");
	}
	
}