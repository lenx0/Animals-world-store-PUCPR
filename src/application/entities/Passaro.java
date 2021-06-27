package application.entities;

public class Passaro extends Animal {
	private static final long serialVersionUID = 1L;

	private String raridade;

	public Passaro(String nome, String especie, String dono, String raridade) {
		super(nome, especie, dono);
		this.raridade = raridade;
	}

	public String getRaridade() {
		return raridade;
	}

	public void setRaridade(String raridade) {
		this.raridade = raridade;
	}

	@Override
	public String alimentacao() {
		return "Alpiste, frutas";
	}

	@Override
	public String cuidadosProprios() {
		return "Proteger de sol, chuva e frio, puleiros, companheiro";
	}

	public String toString() {
		String info = "";
		info += "Nome: " + this.nome + "\n";
		info += "Especie: " + this.especie + "\n";
		info += "Dono: " + this.dono + "\n";
		info += "Alimentação: " + alimentacao() + "\n";
		info += "Raridade: " + getRaridade() + "\n";
		return info;
	}
}
