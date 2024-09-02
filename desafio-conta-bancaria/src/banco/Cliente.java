package banco;

import java.util.Objects;

public class Cliente {
	private String nome;
	private String cpf;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Cliente cliente = (Cliente) obj;
		return cpf.equals(cliente.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
}
