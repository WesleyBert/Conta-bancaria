package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class contaController implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	
	
	@Override
	public void procurarPorNumero(int numero) {
	 var conta = buscarNaCollection(numero);
	 
	 if(conta != null) {
		 conta.visualizar();
	 }else {
		 System.out.println("\n Conta número: " + numero + "não foi encontrada");
	 }
	}

	@Override
	public void listarTodas() {
		for(var conta: listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\n A conta número: " + conta.getNumero() + "foi criado com sucesso ");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\n Conta numero: " + conta.getNumero() + " atualizado com sucesso!");
		}else {
			System.out.println("\n Conta numero: " + conta.getNumero() + " não foi encontrada!");
		}
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true) {
				System.out.println("\n conta numero: "+ numero + " foi deletado com sucesso");
			}
			else {
				System.out.println("\n conta numero: " + numero + " não foi encontrada");
			}
		}
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(conta.sacar(valor) == true) {
				System.out.println("O saque da conta numero " + numero + " foi realizado com sucesso!");
			}else {
				System.out.println("Conta não encontrada!");
			}
		}
		
	}

	@Override
	public void depositar(int numero, float valor) {

		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.depositar(valor);
			System.out.println("Deposito realizado com sucesso!");
		}else {
			System.out.println("Erro ao depositar, conta numero " + numero+ " não foi encontrada!");
		}
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("A transferencia realizada com sucesso!");
			}
			else {
				System.out.println("Conta de origem ou destinada não encontrada! tente novamente!");
			}
		}
		
	}

	public int gerarNumero() {
		return ++ numero;
	}

	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
}
