package package_00;
import java.util.HashMap;
import java.util.ArrayList;

/** 
 * Simula una mesa de votacion de una sede especifica, la clase
 * cuenta con un numero de mesa, el cual es unico en cada sede y sirve como identificador,
 * una capacidad maxima que indica cuantos votantes como maximo puede tener asignados esa mesa,
 * un ArrayList<> de objetos de la Votante que almacena los votantes asignados a la mesa, y
 * un HashMap<String, Integer> que sirve para que cada mesa lleve su propio conteo de los votos
 * siendo la clave el nombre del candidato y el valor un entero que corresponde a la cantidad de votos
 * que lleva dicho candidato
 */

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
	
	/** 
	 *  El metodo verifica la capacidad maxima de la mesa y la compara con
	 *  el tamaño del ArrayLista<> de votantes que esta posee para comprobar si la
	 *  mesa cuenta con mas capacidad para seguir agregando votantes
	 * 
	 * @param votante El metodo agrega el objeto de la clase de votante al ArrayList<>
	 *        de votantes que posee la mesa si se encuentra que el votante ingresado
	 *        no es null y si la mesa sigue teniendo capacidad para seguir agregando
	 *        votantes
	 * @return El metodo retorna true si la mesa posee capacidad para agregar 
	 * 		   mas votantes y false en caso contrario
	 * 
	 * */
	
	public boolean agregarVotante(Votante votante) {
		if (listaVotantes.size() < capMax && votante != null) {
			listaVotantes.add(votante);
			return true;
		} else {
			return false;
		}
	}
}
