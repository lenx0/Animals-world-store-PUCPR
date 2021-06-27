package application.entities;

public class Cachorro extends Animal {
	private static final long serialVersionUID = 1L;

	private String adestrado;

	public Cachorro(String nome, String especie, String dono, String adestrado) {
		super(nome, especie, dono);
		this.adestrado = adestrado;
	}

	public String getAdestrado() {
		return adestrado;
	}

	public void setAdestrado(String adestrado) {
		this.adestrado = adestrado;
	}

	@Override
	public String alimentacao() {
		return "ra��o e/ou carnes";
	}

	@Override
	public String cuidadosProprios() {
		return "tosa, banho, remedios, vacinas, exerc�cios";
	}

	public String toString() {
		String info = "";
		info += "Nome do Animal: " + this.nome + "\n";
		info += "Especie: " + this.especie + "\n";
		info += "Dono: " + this.dono + "\n";
		info += "Alimenta��o: " + alimentacao() + "\n";
		info += "Cuidados pr�prios: " + cuidadosProprios() + "\n";
		info += "Adestrado: " + getAdestrado() + "\n";
		return info;
	}

}
