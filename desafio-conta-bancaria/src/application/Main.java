package application;

import banco.Agencia;
import banco.Banco;
import banco.Cliente;
import banco.Conta;
import banco.ContaCorrente;
import banco.ContaPoupanca;

public class Main {

	public static void main(String[] args) {
		// criar banco
		Banco banco = new Banco();

		// Criar agências
		Agencia agencia1 = new Agencia("Centro");
		Agencia agencia2 = new Agencia("Sul");

		// Adicionar agências ao banco
		banco.adicionarAgencia(agencia1);
		banco.adicionarAgencia(agencia2);

		// Criar clientes
		Cliente cliente1 = new Cliente("João Silva", "12345678900");
		Cliente cliente2 = new Cliente("Maria Oliveira", "98765432100");

		// Criar contas
		agencia1.criarContaCorrente(cliente1);
		agencia1.criarContaPoupanca(cliente1);
		agencia2.criarContaCorrente(cliente2);

		// Obter contas
		Conta contaCorrenteJoao = agencia1.getContas().values().stream()
				.filter(c -> c instanceof ContaCorrente && c.getCliente().equals(cliente1)).findFirst().orElse(null);

		Conta contaPoupancaJoao = agencia1.getContas().values().stream()
				.filter(c -> c instanceof ContaPoupanca && c.getCliente().equals(cliente1)).findFirst().orElse(null);

		Conta contaCorrenteMaria = agencia2.getContas().values().stream()
				.filter(c -> c instanceof ContaCorrente && c.getCliente().equals(cliente2)).findFirst().orElse(null);

		// Exibir informações iniciais
		System.out.println("=== Banco ===");
		banco.exibirAgencias();
		System.out.println();

		// Testar operações com João Silva
		System.out.println("=== Teste João Silva ===");

		if (contaCorrenteJoao != null) {
			System.out.println("Agência: " + agencia1.getNome() + " - Código: " + agencia1.getCodigo());
			System.out.println("Cliente: " + agencia1.buscarNomeClientePorCpf("12345678900"));
			contaCorrenteJoao.emitirExtrato();
			if (contaPoupancaJoao != null) {
				contaPoupancaJoao.emitirExtrato();
			}

			System.out.println();
			// Depósito na Conta Corrente
			System.out.println("Depósito na Conta Corrente de João Silva:");
			contaCorrenteJoao.depositar(1000.0);
			contaCorrenteJoao.emitirExtrato();

			// Saque da Conta Corrente
			System.out.println("\nSaque da Conta Corrente de João Silva:");
			contaCorrenteJoao.sacar(200.0);
			contaCorrenteJoao.emitirExtrato();

			// Depósito na Conta Poupança
			if (contaPoupancaJoao != null) {
				System.out.println("\nDepósito na Conta Poupança de João Silva:");
				contaPoupancaJoao.depositar(100.0);
				contaPoupancaJoao.emitirExtrato();
			}

			// Transferência de Conta Corrente para Conta Poupança
			if (contaPoupancaJoao != null) {
				System.out.println("\nTransferência de Conta Corrente para Conta Poupança (mesmo cliente):");
				contaCorrenteJoao.transferir(contaPoupancaJoao, 100.0);
				contaCorrenteJoao.emitirExtrato();
				contaPoupancaJoao.emitirExtrato();
			}

			// Transferência de Conta Corrente para Conta Corrente (cliente diferente)
			System.out.println("\nTransferência de Conta Corrente para Conta Corrente (cliente diferente):");
			if (contaCorrenteMaria != null) {
				contaCorrenteJoao.transferir(contaCorrenteMaria, 100.0);
				contaCorrenteJoao.emitirExtrato();
				System.out.println("Destinatário transferência:");
				System.out.println("Agência: " + agencia2.getNome() + " - Código: " + agencia2.getCodigo());
				System.out.println("Cliente: " + agencia2.buscarNomeClientePorCpf("98765432100"));
				contaCorrenteMaria.emitirExtrato();
			}
		}
	}

}
