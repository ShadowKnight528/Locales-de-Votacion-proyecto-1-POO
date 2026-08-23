package package_00;
import java.util.HashMap;
import java.util.ArrayList;

public class MenuInteractivo {
	
	public void agregarVotanteAMesa(Votante votante, Mesa mesa) {
		if (votante == null || mesa == null)	{
			System.out.println("La mesa y/o el votante ingresados no existen");
			return;
		} else {
			if (mesa.agregarVotante(votante) == true) {
				System.out.println("El votante ha sido añadido con exito a la mesa seleccionada");
			} else {
				System.out.println("La mesa que usted ha seleccionado no posee mas cupos");
			}
		}
	}
	
	public void agregarMesaASede(Mesa mesa, Sede sede) {
		if (sede == null) {
			System.out.println("La sede no existe");
			return;
		} else {
			sede.agregarMesa(mesa);
			return;
		}
	}
	
	public void listarVotantesMesa(Mesa mesa) {
		if (mesa == null) {
			System.out.println("Esta mesa no existe");
		} else {
			ArrayList<Votante> listaVotantes = mesa.getListaVotantes();
			if (listaVotantes == null) {
				System.out.println("Esta mesa no tiene votantes");
			} else {
				for (Votante votante : listaVotantes) {
					System.out.println(votante.obtenerDatos());
				}
			}
		}
	}
	
	public void listarMesasSede(Sede sede) {
		if (sede == null) {
			System.out.println("La sede no existe");
		} else {
			HashMap<Integer, Mesa> mapaMesas = sede.getMapaMesas();
			if (mapaMesas == null) {
				System.out.println("La sede no cuenta con mesas disponibles");
			} else {
				for (Mesa mesa : mapaMesas.values()) {
					System.out.println("Numero de mesa: " + mesa.getNumeroMesa() + " Capacidad maxima: " + mesa.getCapMax());
				}
			}
		}
	}
	
	public Votante buscarVotanteEnMesa(String rut, Mesa mesa) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null) {
			System.out.println("La mesa ingresada no existe, la mesa no tiene votantes asignados, o bien el rut ingresado no es valido");
			return null;
		} else {
			for (Votante votante : mesa.getListaVotantes()) {
				if ((votante.getRut().equals(rut)) == true) {
					return votante;
				}
			}
			return null;
		}
	}
	
	public Mesa buscarMesaEnSede(int numeroMesa, Sede sede) {
		if (sede == null || sede.getMapaMesas() == null) {
			System.out.println("La sede ingresada no existe, o bien no tiene mesas disponibles");
			return null;
		} else {
			return sede.getMapaMesas().get(numeroMesa);
		}
	}
}
