package package_00;
import java.util.HashMap;

public class Mesa {
	
	private int numeroMesa;
	private int capMax;
	private Votante[] arregloVotantes; // Decidir despues si se quedara asi o como ArrayList
	private HashMap<String, Integer> conteoVotos;
	
	public Mesa(int numeroMesa, int capMax, Votante[] arregloVotantes, HashMap<String, Integer> conteoVotos) {
		setNumeroMesa(numeroMesa);
		setCapMax(capMax);
		setArregloVotantes(arregloVotantes);
		setConteoVotos(conteoVotos);
	}
	
	public void setNumeroMesa(int numeroMesa) {
		if (numeroMesa <= 0) {
			System.out.println("Valor invalido"); // Despues manejar con excepciones cuando se repase ese contenido
		} else {
			this.numeroMesa = numeroMesa;
		}
	}
	
	public void setCapMax(int capMax) {
		if (capMax <= 0) {
			System.out.println("Valor invalido");
		} else {
			this.capMax = capMax;
		}
	}
	
	public void setArregloVotantes(Votante[] arregloVotantes) {
		if (arregloVotantes == null) {
			System.out.println("Valor invalido");
		} else {
			this.arregloVotantes = arregloVotantes;
		}
	}
	
	public void setConteoVotos(HashMap<String, Integer> conteoVotos) {
		if (conteoVotos == null) {
			System.out.println("Valor invalido");
		} else {
			this.conteoVotos = conteoVotos;
		}
	}
	
	public int getNumeroMesa() {
		return numeroMesa;
	}
	
	public int getCapMax() {
		return capMax;
	}
	
	public Votante[] getArregloVotantes() {
		return arregloVotantes;
	}
	
	public HashMap<String, Integer> getConteoVotos(){
		return conteoVotos;
	}
}
