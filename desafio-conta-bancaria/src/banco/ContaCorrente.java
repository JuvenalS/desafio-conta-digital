package banco;

public class ContaCorrente extends Conta {
	public ContaCorrente(Cliente cliente, Agencia agencia) {
		super(cliente, agencia);
	}

	@Override
	public void emitirExtrato() {
		System.out.println("Extrato da Conta Corrente");
		System.out.println("Código: " + codigo);
		System.out.println("Saldo: " + saldo);
	}

	@Override
	public void transferir(Conta destino, double valor) {
		if (valor > 0 && valor <= saldo) {
			sacar(valor);
			destino.depositar(valor);
			System.out.println("Valor da transferência: " + valor);
		} else {
			System.out.println("Saldo insuficiente ou valor inválido.");
		}
	}
}
