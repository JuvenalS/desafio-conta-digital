package banco;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Conta {
	private static final Random RANDOM = new Random();
	private static final Set<Integer> CODIGOS_UTILIZADOS = new HashSet<>();

	protected int codigo;
	protected double saldo;
	protected Cliente cliente;
	protected Agencia agencia;

	public Conta(Cliente cliente, Agencia agencia) {
		this.cliente = cliente;
		this.agencia = agencia;
		this.codigo = gerarCodigoUnico();
	}

	private int gerarCodigoUnico() {
		int codigo;
		do {
			codigo = 10000000 + RANDOM.nextInt(90000000);
		} while (CODIGOS_UTILIZADOS.contains(codigo));
		CODIGOS_UTILIZADOS.add(codigo);
		return codigo;
	}

	public abstract void emitirExtrato();

	public void sacar(double valor) {
		if (valor > 0 && valor <= saldo) {
			saldo -= valor;
			System.out.println("Valor do saque: " + valor);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido.");
		}
	}

	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;
			System.out.println("Valor do depósito: " + valor);
		} else {
			System.out.println("Valor inválido.");
		}
	}

	public abstract void transferir(Conta destino, double valor);

	public double getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getCodigo() {
		return codigo;
	}
}
