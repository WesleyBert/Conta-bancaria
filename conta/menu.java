package conta;

import java.util.Scanner;

import conta.controller.contaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		contaController contas = new contaController();

		Scanner leia = new Scanner(System.in);
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular = null;
		float saldo ,limite,valor;

		System.out.println("\n Criar contas\n");
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123,1, "joao da silva",100f,100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 123,1, "maria da silva",100f,100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123,2, "julia da silva",100f,15);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 123,2, "julia da silva",100f,15);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
		
		
		while (true) {
			
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     "); 
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");
			opcao = leia.nextInt();
			
			if(opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar conta \n\n");
				
				System.out.println("Digite o numero da agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do titular: ");
				leia.skip("\\n?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o tipo da conta (1-CC ou 2 -CP)");
					tipo = leia.nextInt();
				}while(tipo < 1 && tipo > 2);
				System.out.println("Digite o saldo da conta (R$): ");
				saldo = leia.nextFloat();
					switch(tipo) {
					case 1 : {
						System.out.println("Digite o limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia,tipo,titular,saldo, limite));
					}
					case 2:{
						System.out.println("Digite o dia do Aniversario da conta :");
						aniversario = leia.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia, tipo, titular, saldo, aniversario));
						}
					}
				
                 break;
			case 2:
				System.out.println("\n Listar todas as Contas");
				contas.listarTodas();
                 break;
			case 3:
				System.out.println("\n Buscar Conta por número");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
				break;
			case 4:
				System.out.println("\n Atualizar dados da Conta");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);
				
				if(buscaConta != null) {
					tipo = buscaConta.getTipo();
					
					System.out.println("Digite o numero da agencia: ");
					agencia = leia.nextInt();
					System.out.println("digite o nome do titular: ");
					leia.skip("\\R?");
					
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
					case 1:{
						System.out.println("Digite o limite de crédito (R$): ");
						limite = leia.nextFloat();
						
						contas.atualizar(new ContaCorrente(numero, agencia, tipo,titular,saldo,limite));
					}
					case 2: {
						System.out.println("digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();
						
						contas.atualizar(new ContaPoupanca(numero,agencia, tipo, titular, saldo, aniversario));
					}
					default: {
						System.out.println("Tipo de conta inválida!");
					}
				}
						
				}else {
					System.out.println("A conta não foi encontrada! ");
				}
				
                 break;
			case 5:
				System.out.println("\n Apagar Conta");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
                 break;
			case 6:
				System.out.println("\n Sacar");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$) ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.sacar(numero, valor);
				
				break;
             case 7:
				System.out.println("\n Depositar");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do Depósito: ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				
				contas.depositar(numero, valor);
				
				break;
             case 8:
				System.out.println("\n Transferir");
				System.out.println("Digite o numero da conta de origem: ");
				numero = leia.nextInt();
				System.out.println("/digite o numero da conta destinada: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da transferencia: ");
					valor = leia.nextFloat();
					
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				break;
			default:
				System.out.println("\nOpção Inválida");
                 break;
			}
        }
	}

}