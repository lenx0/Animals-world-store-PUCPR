package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.entities.Animal;
import application.entities.Cachorro;
import application.entities.Cavalo;
import application.entities.Passaro;

public class animalsWorldStore {
	private ArrayList<Animal> animais;

	public animalsWorldStore() {
		this.animais = new ArrayList<Animal>();
	}

	public String[] leValores(String[] dadosIn) {
		String[] dadosOut = new String[dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");

		return dadosOut;
	}

	public Cavalo cadastroCavalo() {

		String[] valores = new String[4];
		String[] nomeVal = { "Nome", "Espécie", "Dono", "Cor" };
		valores = leValores(nomeVal);

		Cavalo cavalo = new Cavalo(valores[0], valores[1], valores[2], valores[3]);
		return cavalo;
	}

	public Cachorro cadastroCachorro() {

		String[] valores = new String[4];
		String[] nomeVal = { "Nome", "Espécie", "Dono", "Adestrado [sim/não]" };
		valores = leValores(nomeVal);

		Cachorro cachorro = new Cachorro(valores[0], valores[1], valores[2], valores[3]);
		return cachorro;
	}

	public Passaro cadastroPassaro() {

		String[] valores = new String[4];
		String[] nomeVal = { "Nome", "Espécie", "Dono", "Raridade: comum/raro/muito raro" };
		valores = leValores(nomeVal);

		Passaro passaro = new Passaro(valores[0], valores[1], valores[2], valores[3]);
		return passaro;
	}

	private boolean inteiroValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int retornaUmNumeroInteiro(String entrada) {

		while (!this.inteiroValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAnimais(ArrayList<Animal> animais) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\animalsWorldStore.dados"));
			for (int i = 0; i < animais.size(); i++)
				outputStream.writeObject(animais.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Animal> recuperaAnimais() {
		ArrayList<Animal> animaisTemp = new ArrayList<Animal>();

		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new FileInputStream("c:\\temp\\animalsWorldStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Animal) {
					animaisTemp.add((Animal) obj);
				}
			}
		} catch (EOFException ex) {
			System.out.println("FINALIZADO");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo com animais não encontrado!!!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return animaisTemp;
		}
	}

	public void menuPetStore() {

		String menu = "";
		String entrada;
		int opc1, opc2;

		do {
			menu = "Aplicativo para cadastro de animais\n" + "Opções:\n" + "1. Cadastrar Animal\n"
					+ "2. Exibir Animais\n" + "3. Limpar Animais\n" + "4. Salvar Animais cadastrados\n"
					+ "5. Recuperar Animais\n" + "9. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaUmNumeroInteiro(entrada);

			switch (opc1) {
			case 1:
				menu = "Cadastro de Animais \n" + "Opções:\n" + "1. Cavalo\n" + "2. Cachorro\n" + "3. Passaro\n";

				entrada = JOptionPane.showInputDialog(menu + "\n\n");
				opc2 = this.retornaUmNumeroInteiro(entrada);

				switch (opc2) {
				case 1:
					animais.add((Animal) cadastroCavalo());
					break;
				case 2:
					animais.add((Animal) cadastroCachorro());
					break;
				case 3:
					animais.add((Animal) cadastroPassaro());
					break;
				default:
					JOptionPane.showMessageDialog(null, "Escolha um animal para cadastrar!");
				}

				break;
			case 2:
				if (animais.size() == 0) {
					JOptionPane.showMessageDialog(null, "Cadastre um animal primeiro");
					break;
				}
				String dados = "";
				for (int i = 0; i < animais.size(); i++) {
					dados += animais.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null, dados);
				break;
			case 3:
				if (animais.size() == 0) {
					JOptionPane.showMessageDialog(null, "Não há nenhum animal para apagar");
					break;
				}
				animais.clear();
				JOptionPane.showMessageDialog(null, "Dados apagados com sucesso!");
				break;
			case 4:
				if (animais.size() == 0) {
					JOptionPane.showMessageDialog(null, "Cadastre algum animal para gravar");
					break;
				}
				salvaAnimais(animais);
				JOptionPane.showMessageDialog(null, "Animais salvos com sucesso!");
				break;
			case 5:
				animais = recuperaAnimais();
				if (animais.size() == 0) {
					JOptionPane.showMessageDialog(null, "Nenhum dado para recuperar encontrado");
					break;
				}
				JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Fim do app, até a próxima!");
				break;
			}
		} while (opc1 != 9);
	}

	public static void main(String[] args) {

		animalsWorldStore pet = new animalsWorldStore();
		pet.menuPetStore();

	}

}
