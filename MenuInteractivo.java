package package_00;
import java.util.HashMap;
import java.util.ArrayList;

public class MenuInteractivo {
	
	public void agregarVotanteAMesa(Votante votante, Mesa mesa) {
		if (votante == null || mesa == null)	{
			System.out.println("La mesa y/o el votante ingresados no existen");
			return;
		} else {
			try {
				mesa.agregarVotante(votante);
				System.out.println("El votante ha sido agregado con exito");
			} catch (ExcedeCapacidadException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void agregarMesaASede(Mesa mesa, Sede sede) {
		if (sede == null) {
			System.out.println("La sede no existe");
			return;
		} else {
			try {
				sede.agregarMesa(mesa);
			    System.out.println("La mesa ha sido agregada con exito");
			} catch (ExcedeCapacidadException e) {
				System.out.println(e.getMessage());
			}
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
	
	public Votante eliminarVotanteDeMesa(String rut, Mesa mesa) {
		if (rut == null || mesa == null || mesa.getListaVotantes() == null) {
			System.out.println("La mesa o el rut ingresados no existen, o bien la mesa no cuenta con votantes asignados");
			return null;
		} else {
			Votante eliminado = buscarVotanteEnMesa(rut, mesa);
			if (eliminado == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
			} else {
				mesa.getListaVotantes().remove(eliminado);
				System.out.println("El votante ha sido eliminado con exito de la lista de votantes de la mesa seleccionada");
			}
			return eliminado;
		}
	}
	public Mesa eliminarMesaDeSede(int numeroMesa, Sede sede) {
		if (sede == null || sede.getMapaMesas() == null) {
			System.out.println("La sede ingresada no existe o bien no cuenta con mesas disponibles");
			return null;
		} else {
			Mesa eliminada = buscarMesaEnSede(numeroMesa, sede);
			if (eliminada == null) {
				System.out.println("El numero de mesa ingresado no corresponde a una mesa de la sede");
			} else {
				sede.getMapaMesas().remove(numeroMesa, eliminada);
				System.out.println("La mesa ha sido eliminada con exito");
			}
			return eliminada;
		}
	}
	
	public void modificarNombreVotante(String rut, Mesa mesa, String nuevoNombre) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null || nuevoNombre == null) {
			System.out.println("La mesa ingresada no existe o bien no tiene votantes asignados, el rut ingresado no es valido, o el nuevo nombre tampoco");
		} else {
			Votante votanteAModificarNombre = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarNombre == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarNombre.setNombre(nuevoNombre);
				System.out.println("Nombre actualizado con exito");
				return;
			}
		}
	}
	
	public void modificarNombreVotante(Votante votante, String nuevoNombre) {
		if (votante == null || nuevoNombre == null) {
			System.out.println("El votante no existe o bien el nuevo nombre no es valido");
			return;
		} else {
			votante.setNombre(nuevoNombre);
			System.out.println("Nombre actualizado con exito");
			return;
		}
	}
	
	public void modificarResidenciaVotante(String rut, Mesa mesa, Coordenadas nuevoDomicilio) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null || nuevoDomicilio == null) {
			System.out.println("La mesa no existe o no cuenta con votantes asignados, el rut ingresado no es valido, o el domicilio ingresado no existe");
			return;
		} else {
			Votante votanteAModificarDomicilio = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarDomicilio == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarDomicilio.setResidencia(nuevoDomicilio);
				return;
			}
		}
	}
	
	public void modificarResidenciaVotante(String rut, Mesa mesa, double xNuevoDomicilio, double yNuevoDomicilio) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null) {
			System.out.println("La mesa no existe o no cuenta con votantes asignados, o bien el rut ingresado no es valido");
			return;
		} else {
			Votante votanteAModificarDomicilio = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarDomicilio == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarDomicilio.setResidencia(xNuevoDomicilio, yNuevoDomicilio);
				return;
			}
		}
	}
	
	public void modificarResidenciaVotante(Votante votante, Coordenadas nuevoDomicilio) {
		if (votante == null || nuevoDomicilio == null) {
			System.out.println("El votante o el nuevo domicilio ingresados no existen");
			return;
		} else {
			votante.setResidencia(nuevoDomicilio);
			return;
		}
	}
	
	public void modificarResidenciaVotante(Votante votante, double xNuevoDomicilio, double yNuevoDomicilio) {
		if (votante == null) {
			System.out.println("El votante ingresado no existe");
			return;
		} else {
			votante.setResidencia(xNuevoDomicilio, yNuevoDomicilio);
			return;
		}
	}
	
	public void modificarCapMaxMesa(int numeroMesa, Sede sede, int nuevaCapMax) {
		if (sede == null || sede.getMapaMesas() == null) {
			System.out.println("La sede ingresada no existe, o bien no tiene mesas disponibles");
			return;
		} else {
			Mesa mesaAModificarCapMax = buscarMesaEnSede(numeroMesa, sede);
			if (mesaAModificarCapMax == null) {
				System.out.println("El numero de mesa ingresado no hace referencia a ninguna mesa de la sede");
			} else {
				int auxCapMax = mesaAModificarCapMax.getCapMax();
				mesaAModificarCapMax.setCapMax(nuevaCapMax);
				if (sede.sumarCapacidadMesa() > sede.getCapMax()) {
					System.out.println("La nueva capacidad maxima de la mesa excede la capacidad maxima de la sede");
					mesaAModificarCapMax.setCapMax(auxCapMax);
					return;
				} else {
					System.out.println("La capacidad maxima de la mesa ha sido modificada con exito");
					return;
				}
			}
		}
	}
}
