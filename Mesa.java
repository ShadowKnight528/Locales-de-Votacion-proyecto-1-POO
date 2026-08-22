package package_00;
import java.util.HashMap;
import java.util.ArrayList;

public class Mesa {
	
	private int numeroMesa;
	private int capMax;
	private ArrayList<Votante> listaVotantes;
	private HashMap<String, Integer> conteoVotos;
	
	public Mesa(int numeroMesa, int capMax, HashMap<String, Integer> conteoVotos) {
		setNumeroMesa(numeroMesa);
		setCapMax(capMax);
		this.listaVotantes = new ArrayList<Votante>(); 
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
	
	public void setListaVotantes(ArrayList<Votante> listaVotantes) {
		if (listaVotantes == null) {
			System.out.println("Valor invalido");
		} else {
			this.listaVotantes = listaVotantes;
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
	
	public ArrayList<Votante> getListaVotantes() {
		return listaVotantes;
	}
	
	public HashMap<String, Integer> getConteoVotos(){
		return conteoVotos;
	}
	
	public boolean agregarVotante(Votante votante) {
		if (listaVotantes.size() < capMax) {
			listaVotantes.add(votante);
			return true;
		} else {
			return false;
		}
	}
}
