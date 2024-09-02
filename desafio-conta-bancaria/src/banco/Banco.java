package banco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Banco {
	private static final Random RANDOM = new Random();
	private static final Set<Integer> CODIGOS_UTILIZADOS = new HashSet<>();

	private int codigo;

	private List<Agencia> agencias = new ArrayList<>();

	public Banco() {
		this.codigo = gerarCodigoUnico();
	}

	public int getCodigo() {
		return codigo;
	}

	private int gerarCodigoUnico() {
		int codigo;
		do {
			codigo = 1000 + RANDOM.nextInt(9000);
		} while (CODIGOS_UTILIZADOS.contains(codigo));
		CODIGOS_UTILIZADOS.add(codigo);
		return codigo;
	}

	public void adicionarAgencia(Agencia agencia) {
		agencias.add(agencia);
	}

	public void exibirAgencias() {
		for (Agencia agencia : agencias) {
			System.out.println("Agência: " + agencia.getNome() + " - Código: " + agencia.getCodigo());
		}
	}

	public Agencia pesquisarPorCodigo(int codigo) {
		for (Agencia agencia : agencias) {
			if (agencia.getCodigo() == codigo) {
				return agencia;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Banco [codigo=" + codigo + ", agencias=" + agencias + "]";
	}

}
