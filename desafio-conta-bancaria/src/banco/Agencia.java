package banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Agencia {
	private static final Random RANDOM = new Random();
	private static final Set<Integer> CODIGOS_UTILIZADOS = new HashSet<>();

	private int codigo;
	private String nome;
	private List<Cliente> clientes = new ArrayList<>();
	private Map<Integer, Conta> contas = new HashMap<>();

	public Agencia(String nome) {
		this.nome = nome;
		this.codigo = gerarCodigoUnico();
	}

	private int gerarCodigoUnico() {
		int codigo;
		do {
			codigo = 100000 + RANDOM.nextInt(900000);
		} while (CODIGOS_UTILIZADOS.contains(codigo));
		CODIGOS_UTILIZADOS.add(codigo);
		return codigo;
	}

	public void criarContaCorrente(Cliente cliente) {
        adicionarCliente(cliente);
        Conta conta = new ContaCorrente(cliente, this);
        contas.put(conta.getCodigo(), conta);
    }

    public void criarContaPoupanca(Cliente cliente) {
        adicionarCliente(cliente);
        Conta conta = new ContaPoupanca(cliente, this);
        contas.put(conta.getCodigo(), conta);
    }

    private void adicionarCliente(Cliente cliente) {
        if (!clientes.contains(cliente)) {
            clientes.add(cliente);
        }
    }
	
	public String buscarNomeClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return cliente.getNome();
            }
        }
        return null;
    }

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Map<Integer, Conta> getContas() {
		return contas;
	}

	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return "Agencia [codigo=" + codigo + ", nome=" + nome + ", clientes=" + clientes + ", contas=" + contas + "]";
	}
	
}
