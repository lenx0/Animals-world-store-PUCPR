package application.entities;

public class Cavalo extends Animal {

	private static final long serialVersionUID = 1L;
	private String cor;

	public Cavalo(String nome, String especie, String dono, String cor) {
		super(nome, especie, dono);
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String alimentacao() {
		return "Ração, aveia, alfafa";
	}

	@Override
	public String cuidadosProprios() {
		return "Corrida, trote, cascos";
	}

	public String toString() {
		String info = "";
		info += "Nome: " + this.nome + "\n";
		info += "Especie: " + this.especie + "\n";
		info += "Dono: " + this.dono + "\n";
		info += "Alimentação: " + alimentacao() + "\n";
		info += "Cuidados próprios: " + cuidadosProprios() + "\n";
		info += "Cor: " + getCor() + "\n";
		return info;
	}
}
