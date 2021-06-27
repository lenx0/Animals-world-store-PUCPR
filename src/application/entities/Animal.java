package application.entities;

import java.io.Serializable;

public abstract class Animal implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String especie;
	protected String dono;

	public Animal(String nome, String especie, String dono) {
		super();
		this.nome = nome;
		this.especie = especie;
		this.dono = dono;
	}

	public abstract String alimentacao();

	public abstract String cuidadosProprios();

}
