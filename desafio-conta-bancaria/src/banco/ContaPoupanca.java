package banco;

public class ContaPoupanca extends Conta {
	public ContaPoupanca(Cliente cliente, Agencia agencia) {
		super(cliente, agencia);
	}

	@Override
	public void emitirExtrato() {
		System.out.println("Extrato da Conta Poupança");
		System.out.println("Código: " + codigo);
		System.out.println("Saldo: " + saldo);
	}

	@Override
	public void transferir(Conta destino, double valor) {
		if (destino instanceof ContaPoupanca && destino.cliente.equals(this.cliente)) {
			if (valor > 0 && valor <= saldo) {
				sacar(valor);
				destino.depositar(valor);
				System.out.println("Valor da transferência: " + valor);
			} else {
				System.out.println("Saldo insuficiente ou valor inválido.");
			}
		} else {
			System.out.println("Transferência permitida apenas para contas poupança do mesmo cliente.");
		}
	}
}
